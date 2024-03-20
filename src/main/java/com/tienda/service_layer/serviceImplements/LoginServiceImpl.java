package com.tienda.service_layer.serviceImplements;

import com.tienda.utilities.CommonUtilities;
import com.tienda.data_access_layer.DAOimplements.UserDAOImpl;
import com.tienda.presentation_layer.LoginFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.SQLException;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import com.tienda.service_layer.LoginService;

/**
 * Clase para manejar el inicio de sesión de usuario.
 */
public class LoginServiceImpl extends CommonUtilities implements ActionListener, LoginService {

    private static volatile LoginServiceImpl instance;

    // Atributo estático para almacenar el usuario que ha iniciado sesión
    public static User userLogued;

    // Instancia del formulario de inicio de sesión
    private final LoginFrame loginFrame;

    // Contador de intentos de inicio de sesión
    private int intentos = 0;

    // Componentes de la interfaz de usuario
    private final JButton btnAceptar, btnSalir;
    private final JPasswordField txtPassword;
    private final JTextField txtUsuario;
    private final JPanel container;

    /**
     * Constructor para inicializar el servicio de inicio de sesión.
     */
    private LoginServiceImpl() {
        // Crear una instancia del formulario de inicio de sesión (LoginFrame)
        loginFrame = LoginFrame.getInstance();
        container = loginFrame.getContainer();
        btnAceptar = loginFrame.getBtnAceptar();
        btnSalir = loginFrame.getBtnSalir();
        txtPassword = loginFrame.getTxtContraseña();
        txtUsuario = loginFrame.getTxtUsuario();
    }

    public static LoginServiceImpl getInstance() {
        if (instance == null) {
            synchronized (LoginServiceImpl.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new LoginServiceImpl();
                }
            }
        }
        return instance;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    /**
     * Método para iniciar sesión.
     */
    @Override
    public void iniciarSesion() {
        try {
            // Obtener el usuario y la contraseña ingresados
            String usuario = txtUsuario.getText();
            String contraseña = String.valueOf(txtPassword.getPassword());

            // Validar que ambos campos no estén vacíos
            if (usuario.isEmpty() || contraseña.isEmpty()) {
                txtUsuario.requestFocus();
                txtPassword.setText(null);
                alerta.faltanDatos();
            } else {
                // Incrementar el contador de intentos de inicio de sesión
                intentos++;

                // Crear un DTO con los datos del usuario
                UserDTO dto = new UserDTO(usuario, contraseña);

                // Obtener el DAO de usuario e intentar buscar el usuario en la base de datos
                UserDAO dao = new UserDAOImpl(dto);
                User usuarioBd = dao.getUserByUsername();

                // Validar si el usuario existe en la base de datos
                if (usuarioBd == null) {
                    txtUsuario.requestFocus();
                    txtPassword.setText(null);
                    alerta.mostrarError(LoginServiceImpl.class, "El usuario no existe.", null);
                } else {
                    // Validar si la contraseña es correcta
                    byte[] inputHashedPassword = hashPassword(dto.getPassword(), usuarioBd.getSalt());
                    if (MessageDigest.isEqual(usuarioBd.getHashed_password(), inputHashedPassword)) {
                        // Cerrar la ventana de inicio de sesión y cargar el menú principal
                        txtUsuario.setText("");
                        txtPassword.setText("");                      
                        intentos = 0;
                        userLogued = usuarioBd;
                        loginFrame.setVisible(false);
                        MenuServiceImpl.getInstance().reloadFrame().setVisible(true);
                    } else {
                        // Mostrar un error si la contraseña es incorrecta
                        txtPassword.requestFocus();
                        txtPassword.setText(null);
                        alerta.mostrarError(LoginServiceImpl.class, "Contraseña incorrecta. Verifique nuevamente.", null);
                    }
                }
            }

            // Validar si se han superado los intentos máximos de inicio de sesión
            if (intentos == 3) {
                alerta.mostrarError(LoginServiceImpl.class, "Límite de intentos excedido.", null);
                System.exit(0); // Cerrar la aplicación si se superan los intentos máximos
            }
        } catch (SQLException ex) {
            // Manejar errores de base de datos
            alerta.mostrarError(LoginServiceImpl.class, "Error al acceder a la base de datos.", ex);
        } catch (ClassNotFoundException ex) {
            // Manejar errores de configuración del sistema
            alerta.mostrarError(LoginServiceImpl.class, "Error de configuración del sistema.", ex);
        }
    }

    /**
     * Método para cerrar la ventana de inicio de sesión.
     *
     */
    @Override
    public final void close() {
        // Cerrar la aplicación
        System.exit(0);
    }

    /**
     * Cargar el formulario de inicio de sesión.
     */
    @Override
    public void loadFrame() {
        // Configurar la ubicación y visibilidad del formulario de inicio de sesión
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.add(container);
        loginFrame.setVisible(true);
        txtUsuario.requestFocus();
        KeyListeners();
        ActionListeners();
//        close();
    }

    /**
     * Manejar eventos de los botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            iniciarSesion();
        } else if (e.getSource() == btnSalir) {
            close();
        }
    }

    /**
     * Agregar un KeyListener al campo de contraseña para detectar la tecla
     * "Enter" presionada.
     */
    private void KeyListeners() {
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        });
    }

    /**
     * Agregar ActionListeners a los botones.
     */
    private void ActionListeners() {
        btnAceptar.addActionListener(this);
        btnSalir.addActionListener(this);
    }
}
