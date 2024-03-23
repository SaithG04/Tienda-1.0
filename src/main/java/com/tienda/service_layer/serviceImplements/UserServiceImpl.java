package com.tienda.service_layer.serviceImplements;

import com.tienda.data_access_layer.DAOimplements.UserDAOImpl;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.entity.User;
import com.tienda.utilities.ServiceUtilities;
import com.tienda.presentation_layer.UsersFrame;
import java.awt.Component;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import com.tienda.service_layer.UserService;
import javax.swing.table.DefaultTableModel;

/**
 * La clase UserServiceImpl se encarga de gestionar la creación de usuarios.
 */
public class UserServiceImpl extends ServiceUtilities implements ActionListener, UserService {

    // Instancia única de UserServiceImpl para implementar el patrón Singleton
    private static volatile UserServiceImpl instanceOfUserServiceImpl;

    // Componentes de la interfaz de usuario
    private final UsersFrame instanceOfUsersFrame;
    private final JButton btnRegistrar, btnRegresar;
    private final JTextField txtUsuario, txtNombreCompleto;
    private final JPasswordField txtPassword;
    private final JTable jtbUsuarios;
    private final JPopupMenu jpmOptions;
    private final JMenuItem jmiModificar, jmiEliminar;

    // Constructor privado para garantizar la instancia única
    private UserServiceImpl() {
        // Crear una instancia del formulario de usuario (UsersFrame)
        instanceOfUsersFrame = UsersFrame.getInstance();
        // Obtener referencias a los componentes de la interfaz de usuario
        btnRegistrar = instanceOfUsersFrame.getBtnRegistrar();
        txtPassword = instanceOfUsersFrame.getTxtPassword();
        txtUsuario = instanceOfUsersFrame.getTxtUser();
        txtNombreCompleto = instanceOfUsersFrame.getTxtNombreCompleto();
        btnRegresar = instanceOfUsersFrame.getBtnRegresar();
        jtbUsuarios = instanceOfUsersFrame.getJtbUsuarios();
        jpmOptions = instanceOfUsersFrame.getJpmOptions();
        jmiModificar = instanceOfUsersFrame.getMiModificar();
        jmiEliminar = instanceOfUsersFrame.getMiEliminar();

    }

    // Método estático para obtener la única instancia de UserServiceImpl
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
        // Configurar la ubicación del formulario de usuarios y cerrar la instancia anterior si existe
        instanceOfUsersFrame.setLocationRelativeTo(null);
        Close(instanceOfUsersFrame);
        // Establecer el enfoque en el campo de nombre completo al abrir el formulario
        txtNombreCompleto.requestFocus();
        // Agregar ActionListener a los botones y cargar los usuarios en un hilo separado
        CargarActionListeners();
        CargarMouseListeners();
        jtbUsuarios.setModel(new DefaultTableModel(0, 0));
        new Thread(() -> {
            jtbUsuarios.setModel(CargarUsuarios());
        }).start();
        return instanceOfUsersFrame;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     */
    @Override
    public void RegistrarUsuario() {
        // Obtener los datos ingresados por el usuario
        String nombreCompleto = txtNombreCompleto.getText();
        String user = txtUsuario.getText();
        String password = String.valueOf(txtPassword.getPassword());

        // Verificar si se han completado todos los campos
        if (nombreCompleto.isEmpty() || user.isEmpty() || password.isEmpty()) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }

        try {

            User userCreated = new User();
            userCreated.setId(0);
            userCreated.setNombreCompleto(nombreCompleto);
            userCreated.setUsername(user);
            userCreated.setSalt(generateSalt());
            userCreated.setHashed_password(hashPassword(password, userCreated.getSalt()));

            UserDAO userDAO = new UserDAOImpl(userCreated);
            userDAO.registrar();

            limpiarCampos();
            jtbUsuarios.setModel(CargarUsuarios());
            // Mostrar un mensaje de registro exitoso
            alerta.aviso("Registro exitoso.");

        } catch (SQLException | ClassNotFoundException e) {
            // Manejar cualquier error de conexión durante el registro
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    private void ActualizarUsuario() {
        // Obtener los datos ingresados por el usuario
        String nombreCompleto = txtNombreCompleto.getText();
        String user = txtUsuario.getText();
        String password = String.valueOf(txtPassword.getPassword());

        // Verificar si se han completado todos los campos
        if (nombreCompleto.isEmpty() || user.isEmpty()) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }

        try {

            User userForUpdate = new User();
            userForUpdate.setId((int) jtbUsuarios.getValueAt(jtbUsuarios.getSelectedRow(), 0));

            UserDAO userDAO = new UserDAOImpl(userForUpdate);
            User userFind = userDAO.getById(userForUpdate.getId());
            userFind.setNombreCompleto(nombreCompleto);
            userFind.setUsername(user);
            userForUpdate.setSalt(password.isEmpty() ? userFind.getSalt() : generateSalt());
            userForUpdate.setHashed_password(password.isEmpty() ? userFind.getHashed_password() : hashPassword(password, userForUpdate.getSalt()));

            userDAO.actualizar();

            limpiarCampos();
            jtbUsuarios.setModel(CargarUsuarios());
            // Mostrar un mensaje de registro exitoso
            alerta.aviso("Actualización exitosa exitoso.");

        } catch (SQLException | ClassNotFoundException e) {
            // Manejar cualquier error de conexión durante el registro
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    private void EliminarUsuario() {
        //USUARIO ACTUALIZADO
        System.out.println("USUARIO Eliminado" + jtbUsuarios.getValueAt(jtbUsuarios.getSelectedRow(), 1));
    }

    /**
     * Carga la lista de usuarios desde la base de datos y la presenta en un
     * modelo de tabla.
     *
     * @return El modelo de tabla que contiene la lista de usuarios.
     */
    @Override
    public DefaultTableModel CargarUsuarios() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre Completo", "Usuario"}, 0);
        try {

            UserDAO userDAO = new UserDAOImpl(new User());

            // Obtener la lista de usuarios desde la base de datos usando las funciones definidas en UserDAOImpl
            List<User> lista = userDAO.listar();

            // Iterar sobre la lista de usuarios y agregar cada uno al modelo de la tabla
            lista.forEach(user -> {
                model.addRow(new Object[]{user.getId(), user.getNombreCompleto(), user.getUsername()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante la carga de usuarios
            alerta.manejarErrorConexion(this.getClass(), e);
        }
        return model;
    }

    private void autocompletarCampos() {
        int rowSelected = jtbUsuarios.getSelectedRow();
        if (rowSelected == -1) {

        } else {
            String nombreCompleto = jtbUsuarios.getValueAt(rowSelected, 1).toString();
            String username = jtbUsuarios.getValueAt(rowSelected, 2).toString();
            txtNombreCompleto.setText(nombreCompleto);
            txtUsuario.setText(username);
        }
    }

    private void limpiarCampos() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
    }
    
    @Override
    public void CargarActionListeners() {
        QuitActionListeners(); // Eliminar los ActionListener anteriores para evitar duplicados
        btnRegistrar.addActionListener(this);
        btnRegresar.addActionListener(this);
        jmiModificar.addActionListener(this);
        jmiEliminar.addActionListener(this);
    }

    @Override
    public void CargarKeyListeners() {
    }

    @Override
    public void CargarMouseListeners() {
//        QuitMouseListener(jtbUsuarios);
        jtbUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) { // Verificar si se hizo clic con el botón derecho
                    if (evt.getClickCount() == 1) { // Verificar que sea un único clic
                        jmiModificar.setText("Modificar");
                        jmiEliminar.setText("Eliminar");
                        jpmOptions.add(jmiModificar);
                        jpmOptions.add(jmiEliminar);
                        jpmOptions.show(jtbUsuarios, evt.getX(), evt.getY()); // Mostrar el menú emergente en la posición del clic
                    }
                } else {
                    autocompletarCampos();
                }
            }
        });
    }


    @Override
    public void QuitActionListeners() {
        btnRegistrar.removeActionListener(this);
        btnRegresar.removeActionListener(this);
        jmiModificar.removeActionListener(this);
        jmiEliminar.removeActionListener(this);
    }

    @Override
    public void QuitKeyListener(Component componente) {
    }

    @Override
    public void QuitMouseListener(Component componente) {
        for (MouseListener ml : componente.getMouseListeners()) {
            componente.removeMouseListener(ml); // Eliminar los MouseListeners del componente
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            RegistrarUsuario();
        } else if (e.getSource() == btnRegresar) {
            // Limpiar los campos y regresar al menú principal
            limpiarCampos();
            instanceOfUsersFrame.dispose(); // Cerrar el formulario de usuarios
            MenuServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true); // Mostrar el menú principal
        } else if (e.getSource() == jmiModificar) {
            ActualizarUsuario();
        } else if (e.getSource() == jmiEliminar) {
            EliminarUsuario();
        }
    }

}
