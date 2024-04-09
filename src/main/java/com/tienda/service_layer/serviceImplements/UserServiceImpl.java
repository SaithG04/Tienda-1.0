package com.tienda.service_layer.serviceImplements;

import com.tienda.data_access_layer.DAOimplements.UserDAOImpl;
import com.tienda.utilities.CommonUtilities;
import com.tienda.presentation_layer.UsersFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import com.tienda.service_layer.UserService;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

/**
 * La clase UserServiceImpl se encarga de gestionar la creación de usuarios.
 */
public class UserServiceImpl extends CommonUtilities implements ActionListener, UserService {

    private final UsersFrame usersFrame;

    private final JButton btnRegistrar, btnRegresar;
    private final JTextField txtUsuario;
    private final JPasswordField txtPassword;
    private JTable tabla;
    private DefaultTableModel model;

    /**
     * Constructor para inicializar el servicio de usuario.
     */
    public UserServiceImpl() {
        // Crear una instancia del formulario de usuario (UsersFrame)
        usersFrame = UsersFrame.getInstance();
        btnRegistrar = usersFrame.getBtnRegistrar();
        txtPassword = usersFrame.getTxtPassword();
        txtUsuario = usersFrame.getTxtUser();
        btnRegresar = usersFrame.getBtnRegresar();
        tabla = usersFrame.getJtbUsuarios();
    }

    /**
     * Método para registrar un nuevo usuario.
     */
    private void RegistrarUsuario() {
        try {
            // Obtener el nombre de usuario y contraseña ingresados
            String user = txtUsuario.getText();
            String password = String.valueOf(txtPassword.getPassword());

            // Crear un DTO con los datos del usuario
            UserDTO dto = new UserDTO(user, password);

            // Generar una sal y una contraseña hash
            byte[] salt = generateSalt();
            byte[] hashedPassword = hashPassword(dto.getPassword(), salt);

            // Guardar el usuario en la base de datos
            UserDAO dao = new UserDAOImpl(dto);
            User newUser = new User(0, dto.getUser(), hashedPassword, salt);
            dao.saveUser(newUser);

            // Mostrar un mensaje de éxito
            alerta.aviso("Registro exitoso.");
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar errores de conexión a la base de datos
            alerta.manejarErrorConexion(UserServiceImpl.class, e);
        }
    }

    /**
     * Manejar eventos de los botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            RegistrarUsuario();
            actTabla();
        } else if (e.getSource() == btnRegresar) {
            close();
        }
    }

    /**
     * Cerrar la ventana de usuario.
     */
    @Override
    public final void close() {
        usersFrame.dispose();
        new MenuServiceImpl().loadFrame();
    }

    /**
     * Cargar el formulario de usuario.
     */
    @Override
    public void loadFrame() {
        // Configurar la ubicación y visibilidad del formulario de usuario
        usersFrame.setLocationRelativeTo(null);
        usersFrame.setVisible(true);
        txtUsuario.requestFocus();
        tabla.setModel(actTabla());
        ActionListeners();
    }

    /**
     * Agregar ActionListeners a los botones.
     */
    public void ActionListeners() {
        btnRegistrar.addActionListener(this);
        btnRegresar.addActionListener(this);
    }

    public DefaultTableModel actTabla() {
        try {
            String[] titulos = {"id", "Usuario", "Password", "Salt"};
            model = new DefaultTableModel(titulos, 0);
            UserDAO dao = new UserDAOImpl(new UserDTO());
            List<User> lista = dao.listarUsuarios();
            Object[] obj = new Object[4];
            for (User usuario : lista) {
                obj[0] = usuario.getId();
                obj[1] = usuario.getUsername();
                obj[2] = usuario.getHashed_password();
                obj[3] = usuario.getSalt();
                model.addRow(obj);
            }

            usersFrame.getJtbUsuarios().setModel(model);
        } catch (Exception e) {
            alerta.manejarErrorConexion(Exception.class, e);
        }
        return model;
    }
}
