package com.tienda.service_layer.serviceImplements;

import com.tienda.data_access_layer.DAOimplements.UserDAOImpl;
import com.tienda.utilities.CommonUtilities;
import com.tienda.presentation_layer.UsersFrame;
import java.awt.event.*;
import javax.swing.*;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import com.tienda.service_layer.UserService;
import java.awt.Component;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

/**
 * La clase UserServiceImpl se encarga de gestionar la creación de usuarios.
 */
public class UserServiceImpl extends CommonUtilities implements ActionListener, UserService {

    private static volatile UserServiceImpl instanceOfUserServiceImpl;

    private final UsersFrame instanceOfUsersFrame;
    private final JButton btnRegistrar, btnRegresar;
    private final JTextField txtUsuario, txtNombreCompleto;
    private final JPasswordField txtPassword;
    private final JTable jtbUsuarios;

    private UserServiceImpl() {
        // Crear una instancia del formulario de usuario (UsersFrame)
        instanceOfUsersFrame = UsersFrame.getInstance();
        btnRegistrar = instanceOfUsersFrame.getBtnRegistrar();
        txtPassword = instanceOfUsersFrame.getTxtPassword();
        txtUsuario = instanceOfUsersFrame.getTxtUser();
        txtNombreCompleto = instanceOfUsersFrame.getTxtNombreCompleto();
        btnRegresar = instanceOfUsersFrame.getBtnRegresar();
        jtbUsuarios = instanceOfUsersFrame.getJtbUsuarios();
    }

    public static UserServiceImpl getInstance() {
        if (instanceOfUserServiceImpl == null) {
            synchronized (UserServiceImpl.class) { // Sincronización para hilos
                if (instanceOfUserServiceImpl == null) {
                    instanceOfUserServiceImpl = new UserServiceImpl();
                }
            }
        }
        return instanceOfUserServiceImpl;
    }

    @Override
    public UsersFrame GetInstanceOfFrame() {
        instanceOfUsersFrame.setLocationRelativeTo(null);
        Close(instanceOfUsersFrame);
        txtUsuario.requestFocus();
        CargarActionListeners();
        // Cargar los usuarios en un hilo separado
        new Thread(() -> {
            DefaultTableModel model = CargarUsuarios();
            jtbUsuarios.setModel(model);
        }).start();
        return instanceOfUsersFrame;
    }

    /**
     * Método para registrar un nuevo usuario.
     */
    @Override
    public void RegistrarUsuario() {
        String nombreCompleto = txtNombreCompleto.getText();
        String user = txtUsuario.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();

        if (nombreCompleto.isEmpty() || user.isEmpty() || password.isEmpty()) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }

        try {

            UserDTO dto = new UserDTO(user, password, nombreCompleto);

            byte[] salt = generateSalt();
            byte[] hashedPassword = hashPassword(password, salt);
            UserDAO dao = new UserDAOImpl(dto);

            dao.saveUser(new User(0, dto.getNombreCompleto(), dto.getUser(), hashedPassword, salt));
            txtNombreCompleto.setText("");
            txtUsuario.setText("");
            txtPassword.setText("");
            jtbUsuarios.setModel(CargarUsuarios());
            alerta.aviso("Registro exitoso.");
        } catch (SQLException | ClassNotFoundException e) {
            alerta.manejarErrorConexion(UserServiceImpl.class, e);
        }
    }

    @Override
    public DefaultTableModel CargarUsuarios() {
        DefaultTableModel model = null;
        try {
            String[] titulos = {"Id", "Nombre Completo", "Usuario"};
            model = new DefaultTableModel(titulos, 0);
            UserDAO dao = new UserDAOImpl(new UserDTO());
            List<User> lista = dao.listarUsuarios();
            Object[] row = new Object[3];
            for (User usuario : lista) {
                row[0] = usuario.getId();
                row[1] = usuario.getNombreCompleto();
                row[2] = usuario.getUsername();
                model.addRow(row);
            }
        } catch (ClassNotFoundException | SQLException e) {
            alerta.manejarErrorConexion(UserServiceImpl.class, e);
        }
        return model;
    }

    /**
     * Agregar AddActionListeners a los botones.
     */
    @Override
    public void CargarActionListeners() {
        QuitActionListeners();
        btnRegistrar.addActionListener(this);
        btnRegresar.addActionListener(this);
    }

    @Override
    public void CargarKeyListeners() {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto

    }

    @Override
    public void CargarMouseListeners() {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto

    }

    @Override
    public void QuitActionListeners() {
        btnRegistrar.removeActionListener(this);
        btnRegresar.removeActionListener(this);
    }

    @Override
    public void QuitKeyListener(Component componente) {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto

    }

    @Override
    public void QuitMouseListener(Component componente) {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            RegistrarUsuario();
        } else if (e.getSource() == btnRegresar) {
            txtUsuario.setText("");
            txtPassword.setText("");
            instanceOfUsersFrame.dispose();
            MenuServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true);
        }
    }

}
