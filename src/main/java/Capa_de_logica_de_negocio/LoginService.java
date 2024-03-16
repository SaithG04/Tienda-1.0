package Capa_de_logica_de_negocio;

import Capa_de_acceso_a_datos.DAOImplements.UsuarioDAOImpl;
import Capa_de_acceso_a_datos.Modelo_de_datos.Usuario;
import Capa_de_presentacion.FrmLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.SQLException;
import Capa_de_acceso_a_datos.DAO.UsuarioDAO;

/**
 * LoginService class for handling user login.
 */
public class LoginService extends CommonUtilities implements ActionListener, FrameOperations {

    private UsuarioDAO UsuarioDAO;
    public static Usuario usuario_logueado;
    private final FrmLogin logueo;
    private int intentos = 0;

    private final JButton btnAceptar, btnSalir;
    private final JPasswordField txtContrasena;
    private final JTextField txtUsuario;
    private final JPanel container;

    /**
     * Constructor to initialize the login service.
     */
    public LoginService() {
        // Create an instance of the login form (FrmLogin)
        logueo = new FrmLogin();
        container = logueo.getContainer();
        btnAceptar = logueo.getBtnAceptar();
        btnSalir = logueo.getBtnSalir();
        txtContrasena = logueo.getTxtContraseña();
        txtUsuario = logueo.getTxtUsuario();

        // Add action listeners to buttons
        btnAceptar.addActionListener(this);
        btnSalir.addActionListener(this);
    }

    /**
     * Method to handle user login.
     */
    private void iniciarSesion() {
        try {
            // Get the entered username and password
            String user = txtUsuario.getText();
            String contrasena = String.valueOf(txtContrasena.getPassword());

            if (user.isEmpty() || contrasena.isEmpty()) {
                // Validate empty fields and display an error message if necessary
                txtUsuario.requestFocus();
                txtContrasena.setText(null);
                oA.faltanDatos();
            } else {
                intentos++;
                // Create an instance of the usuario_logueado controller (Usuario)
                UsuarioDAO = new UsuarioDAOImpl(Conectar());
                usuario_logueado = UsuarioDAO.ObtenerPorUsername(user);

                if (usuario_logueado == null) {
                    // User does not exist, display an error message
                    txtUsuario.requestFocus();
                    txtContrasena.setText(null);
                    oA.mostrarError(LoginService.class, "The user does not exist.", null);
                } else {
                    byte[] inputHashedContraseña = hashPassword(contrasena, usuario_logueado.getSalt());
                    if (MessageDigest.isEqual(usuario_logueado.getHashed_password(), inputHashedContraseña)) {
                        logueo.dispose();
                        new MenuService().loadFrame();
                        intentos = 0;
                    } else {
                        // Incorrect password, display an error message
                        txtContrasena.requestFocus();
                        txtContrasena.setText(null);
                        oA.mostrarError(LoginService.class, "Incorrect password. Please verify.", null);
                    }
                }

                if (intentos == 3) {
                    // If the limit of attempts is exceeded, display an error message and exit the application
                    oA.mostrarError(LoginService.class, "Limit of attempts exceeded.", null);
                    System.exit(0);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            oA.manejarErrorConexion(Usuario.class, ex);
        }
    }

    /**
     * close the application.
     */
    @Override
    public final void close() {
        System.exit(0);
    }

    /**
     * Load the login form.
     */
    @Override
    public void loadFrame() {
        // Configure the location and visibility of the usuario_logueado form
        logueo.setLocationRelativeTo(null);
        logueo.setLayout(new BorderLayout());
        logueo.add(container);
        logueo.setVisible(true);
        txtUsuario.requestFocus();
        KeyListeners();
    }

    /**
     * Handle button events.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            // Handle click event on the Accept button
            iniciarSesion();
        } else if (e.getSource() == btnSalir) {
            // Handle click event on the Exit button
            close();
        }
    }

    /**
     * Add close KeyListener to the password field to handle "Enter" key press.
     */
    private void KeyListeners() {
        txtContrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Call iniciarSesion() when "Enter" is pressed in the password field
                    iniciarSesion();
                }
            }
        });
    }
}
