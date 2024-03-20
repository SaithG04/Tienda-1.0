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
 * La clase LoginServiceImpl implementa la interfaz LoginService y maneja el
 * inicio de sesión de usuario.
 */
public class LoginServiceImpl extends CommonUtilities implements ActionListener, LoginService {

    // Declaración de variables de instancia
    /**
     * Instancia única de la clase LoginServiceImpl
     */
    private static volatile LoginServiceImpl instanceOfLoginServiceImpl;

    /**
     * Usuario que ha iniciado sesión
     */
    public static User userLogued;

    /**
     * Instancia del formulario de inicio de sesión
     */
    private final LoginFrame instanceOfLoginFrame;

    /**
     * Componentes del formulario de inicio de sesión
     */
    private final JButton btnAceptar, btnSalir;
    private final JPasswordField txtPassword;
    private final JTextField txtUsuario;

    /**
     * Contador de intentos de inicio de sesión
     */
    private int intentos = 0;

    /**
     * Constructor privado para garantizar la implementación del patrón
     * Singleton.
     */
    private LoginServiceImpl() {
        // Obtención de la instancia del formulario de inicio de sesión (LoginFrame)
        instanceOfLoginFrame = LoginFrame.getInstance();
        // Obtención de los componentes del formulario
        btnAceptar = instanceOfLoginFrame.getBtnAceptar();
        btnSalir = instanceOfLoginFrame.getBtnSalir();
        txtPassword = instanceOfLoginFrame.getTxtContraseña();
        txtUsuario = instanceOfLoginFrame.getTxtUsuario();
    }

    /**
     * Método estático para obtener la instancia única de LoginServiceImpl.
     *
     * @return Instancia única de LoginServiceImpl.
     */
    public static LoginServiceImpl getInstance() {
        if (instanceOfLoginServiceImpl == null) {
            synchronized (LoginServiceImpl.class) { // Sincronización para hilos
                if (instanceOfLoginServiceImpl == null) {
                    instanceOfLoginServiceImpl = new LoginServiceImpl();
                }
            }
        }
        return instanceOfLoginServiceImpl;
    }

    /**
     * Método para obtener la instancia del formulario de inicio de sesión.
     *
     * @return Instancia del formulario de inicio de sesión.
     */
    @Override
    public LoginFrame GetInstanceOfFrame() {
        instanceOfLoginFrame.setLocationRelativeTo(null); // Centrar el formulario en pantalla
        txtUsuario.requestFocus(); // Foco en el campo de usuario
        CargarKeyListeners(); // Cargar los KeyListeners para los campos de texto
        CargarActionListeners(); // Cargar los ActionListeners para los botones
        return instanceOfLoginFrame;
    }

    /**
     * Método para iniciar sesión.
     */
    @Override
    public void IniciarSesion() {
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
                        instanceOfLoginFrame.dispose();
                        MenuServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true);
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
     * Método para cargar los ActionListeners de los botones.
     */
    @Override
    public void CargarActionListeners() {
        QuitActionListeners(); // Eliminar los ActionListeners anteriores
        btnAceptar.addActionListener(this); // Agregar ActionListener para el botón de aceptar
        btnSalir.addActionListener(this); // Agregar ActionListener para el botón de salir
    }

    /**
     * Método para cargar los KeyListeners de los campos de texto.
     */
    @Override
    public void CargarKeyListeners() {
        QuitKeyListener(txtPassword); // Eliminar los KeyListeners anteriores del campo de contraseña
        QuitKeyListener(txtUsuario); // Eliminar los KeyListeners anteriores del campo de usuario
        // Agregar KeyListeners para los campos de texto
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    IniciarSesion(); // Iniciar sesión al presionar la tecla Enter en el campo de contraseña
                }
            }
        });
        txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    IniciarSesion(); // Iniciar sesión al presionar la tecla Enter en el campo de usuario
                }
            }
        });
    }

    /**
     * Método para cargar los MouseListeners.
     */
    @Override
    public void CargarMouseListeners() {
        // No tiene MouseListeners
    }

    /**
     * Método para eliminar los ActionListeners de los botones.
     */
    @Override
    public void QuitActionListeners() {
        btnAceptar.removeActionListener(this); // Eliminar ActionListener del botón de aceptar
        btnSalir.removeActionListener(this); // Eliminar ActionListener del botón de salir
    }

    /**
     * Método para eliminar los KeyListeners de un componente.
     *
     * @param componente El componente del cual se eliminarán los KeyListeners.
     */
    @Override
    public void QuitKeyListener(Component componente) {
        for (KeyListener ml : componente.getKeyListeners()) {
            componente.removeKeyListener(ml); // Eliminar los MouseListeners del componente
        }
    }

    /**
     * Método para eliminar los MouseListeners de un componente.
     *
     * @param componente El componente del cual se eliminarán los
     * MouseListeners.
     */
    @Override
    public void QuitMouseListener(Component componente) {
        // No tiene MouseListeners
    }

    /**
     * Método para manejar los eventos de los botones.
     *
     * @param evt El evento que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnAceptar) {
            IniciarSesion(); // Iniciar sesión si se presiona el botón de aceptar
        } else if (evt.getSource() == btnSalir) {
            System.exit(0);
        }
    }
}
