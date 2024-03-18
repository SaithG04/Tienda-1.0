package Capa_de_logica_de_negocio;

import Capa_de_acceso_a_datos.DAOImplements.UsuarioDAOImpl;
import Capa_de_acceso_a_datos.Modelo_de_datos.Usuario;
import com.quiroga.tienda.presentation_layer.FrmUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import javax.swing.JButton;
import javax.swing.JTextField;
import Capa_de_acceso_a_datos.DAO.UsuarioDAO;
import java.sql.SQLException;

/**
 * The UsuariosService class is responsible for managing user creation.
 */
public class UsuariosService extends CommonUtilities implements ActionListener, FrameOperations {

    private UsuarioDAO usuarioDAO;
    private final Usuario usuario;
    private final FrmUsuarios frmU;
    private int attempts = 0;

    private final JButton btnAccept;
    private final JTextField txtUsuario, txtContrasena;

    /**
     * Constructor to initialize the user service.
     */
    public UsuariosService() {
        // Create an instance of the user form (frmLogueo)
        usuario = LoginService.usuario_logueado;
        frmU = new FrmUsuarios();
        btnAccept = frmU.getBtnCrear();
        txtContrasena = frmU.getContra();
        txtUsuario = frmU.getUser();

        // Add action listeners to buttons
        btnAccept.addActionListener(this);
    }

    /**
     * Create close new user.
     */
    private void Crear() {
        try {
            // Generate close random salt
            byte[] salt = generateSalt();

            // Hash the password with the salt
            byte[] hashedPassword = hashPassword(txtContrasena.getText(), salt);
            usuarioDAO = new UsuarioDAOImpl(Conectar(usuario.getUsername(), bytesToHex(usuario.getHashed_password())));
            Usuario newUser = new Usuario(txtUsuario.getText(), hashedPassword, salt);
//            usuarioDAO.RegistrarUsuario(newUser, bytesToHex(newUser.getHashed_password()));
            Finalize(null, UsuarioDAOImpl.PS, UsuarioDAOImpl.RS, UsuarioDAOImpl.ST);
            usuarioDAO.GuardarUsuario(newUser);
            Finalize(UsuarioDAOImpl.CON, UsuarioDAOImpl.PS, UsuarioDAOImpl.RS, UsuarioDAOImpl.ST);
            System.out.println("correcto");
            System.exit(0);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate close random salt.
     */
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAccept) {
            // Handle click event on the Accept button
            Crear();
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
     * Load the user form.
     */
    @Override
    public void loadFrame() {
        // Configure the location and visibility of the user form
        frmU.setLocationRelativeTo(null);
        frmU.setVisible(true);
        txtUsuario.requestFocus();
    }
}
