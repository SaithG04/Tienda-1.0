package com.tienda.service_layer.serviceImplements;

import com.tienda.data_access_layer.DAOimplements.UserDAOImpl;
import com.tienda.utilities.CommonUtilities;
import com.tienda.presentation_layer.UsersFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.service_layer.UserService;
import javax.swing.table.DefaultTableModel;

/**
 * La clase UserServiceImpl se encarga de gestionar la creación de usuarios.
 */
public class UserServiceImpl extends CommonUtilities implements ActionListener, UserService {

    // Instancia única de UserServiceImpl para implementar el patrón Singleton
    private static volatile UserServiceImpl instanceOfUserServiceImpl;

    // Componentes de la interfaz de usuario
    private final UsersFrame instanceOfUsersFrame;
    private final JButton btnRegistrar, btnRegresar;
    private final JTextField txtUsuario, txtNombreCompleto;
    private final JPasswordField txtPassword;
    private final JTable jtbUsuarios;

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
        String nombreCompleto = txtNombreCompleto.getText().trim();
        String user = txtUsuario.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();

        // Verificar si se han completado todos los campos
        if (nombreCompleto.isEmpty() || user.isEmpty() || password.isEmpty()) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }
        
        try {
            // Instanciar el DAO para registrar el usuario en la base de datos
            UserDTO dtoSent = new UserDTO(user, password, nombreCompleto);
            UserDAOImpl userDAO = new UserDAOImpl();

            // Crear un arreglo de valores para la inserción
//            Object[] values = {null, nombreCompleto, user, password, null}; // El último valor es para el salt, que se generará en la base de datos
            // Insertar el nuevo usuario en la base de datos
            userDAO.Registrar("users", dtoSent);

//            if (success) {
            // Limpiar los campos de texto después del registro exitoso
            limpiarCampos();
            // Actualizar la tabla de usuarios después del registro
            jtbUsuarios.setModel(CargarUsuarios());
            // Mostrar un mensaje de registro exitoso
            alerta.aviso("Registro exitoso.");
            
        } catch (SQLException | ClassNotFoundException e) {
            // Manejar cualquier error de conexión durante el registro
            alerta.manejarErrorConexion(this.getClass(), e);
        }
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
            // Crear una instancia del DAO apropiado para la entidad User
            UserDAOImpl userDAO = new UserDAOImpl();

            // Obtener la lista de usuarios desde la base de datos usando las funciones definidas en UserDAOImpl
            List<UserDTO> lista = userDAO.Listar("users");

            // Iterar sobre la lista de usuarios y agregar cada uno al modelo de la tabla
            lista.forEach(userDTO -> {
                model.addRow(new Object[]{userDTO.getUsuario().getId(), userDTO.getNombreCompleto(), userDTO.getUser()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante la carga de usuarios
            alerta.manejarErrorConexion(this.getClass(), e);
        }
        return model;
    }

    /**
     * Limpia los campos de texto de nombre completo, usuario y contraseña.
     */
    private void limpiarCampos() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
    }

    /**
     * Agrega ActionListener a los botones de la interfaz de usuario.
     */
    @Override
    public void CargarActionListeners() {
        QuitActionListeners(); // Eliminar los ActionListener anteriores para evitar duplicados
        btnRegistrar.addActionListener(this);
        btnRegresar.addActionListener(this);
    }
    
    @Override
    public void CargarKeyListeners() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void CargarMouseListeners() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Elimina los ActionListener de los botones de la interfaz de usuario.
     */
    @Override
    public void QuitActionListeners() {
        btnRegistrar.removeActionListener(this);
        btnRegresar.removeActionListener(this);
    }
    
    @Override
    public void QuitKeyListener(Component componente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void QuitMouseListener(Component componente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Maneja los eventos de los botones de la interfaz de usuario.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            RegistrarUsuario();
        } else if (e.getSource() == btnRegresar) {
            // Limpiar los campos y regresar al menú principal
            txtUsuario.setText("");
            txtPassword.setText("");
            instanceOfUsersFrame.dispose(); // Cerrar el formulario de usuarios
            MenuServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true); // Mostrar el menú principal
        }
    }
    
}
