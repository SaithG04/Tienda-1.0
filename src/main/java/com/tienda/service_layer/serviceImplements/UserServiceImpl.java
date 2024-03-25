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
 * Esta clase implementa la interfaz UserService y gestiona las operaciones de
 * usuario en la capa de servicio.
 *
 * @author isai_
 */
public class UserServiceImpl extends ServiceUtilities implements ActionListener, UserService {

    // Declaración de variables miembro
    private static volatile UserServiceImpl instanceOfUserServiceImpl;

    // Instancia del frame de usuarios
    private final UsersFrame instanceOfUsersFrame;

    // Componentes de la interfaz de usuario
    private final JButton btnRegistrar, btnRegresar, btnModificar, btnLimpiar;
    private final JTextField txtUsuario, txtNombreCompleto;
    private final JPasswordField txtPassword;
    private final JTable jtbUsuarios;
    private final JPopupMenu jpmOptions;
    private final JMenuItem jmiEliminar;
    private final JLabel lblPassword;
    private final JToggleButton btnRevelar;

    private final Icon iconoMostrar, iconoOcultar;

    // Constructor privado para garantizar una única instancia de UserServiceImpl
    private UserServiceImpl() {
        instanceOfUsersFrame = UsersFrame.getInstance();
        btnRegistrar = instanceOfUsersFrame.getBtnRegistrar();
        txtPassword = instanceOfUsersFrame.getTxtPassword();
        txtUsuario = instanceOfUsersFrame.getTxtUser();
        txtNombreCompleto = instanceOfUsersFrame.getTxtNombreCompleto();
        btnRegresar = instanceOfUsersFrame.getBtnRegresar();
        jtbUsuarios = instanceOfUsersFrame.getJtbUsuarios();
        jpmOptions = instanceOfUsersFrame.getJpmOptions();
        jmiEliminar = instanceOfUsersFrame.getMiEliminar();
        btnModificar = instanceOfUsersFrame.getBtnModificar();
        btnLimpiar = instanceOfUsersFrame.getBtnLimpiar();
        lblPassword = instanceOfUsersFrame.getLblPassword();
        btnRevelar = instanceOfUsersFrame.getBtnRevelar();
        iconoMostrar = icono("/images/mostrar_eye.jpg", 40, 40);
        iconoOcultar = icono("/images/ocultar_eye.jpg", 40, 40);
    }

    /**
     * Método para obtener una instancia única de UserServiceImpl (patrón
     * Singleton).
     *
     * @return Una instancia única de UserServiceImpl.
     */
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

    /**
     * Método para obtener una instancia del frame de usuarios. Este método
     * configura el frame de usuarios y carga los listeners y datos necesarios.
     *
     * @return Una instancia del frame de usuarios.
     */
    @Override
    public UsersFrame getInstanceOfFrame() {
        // Configuración de la ubicación del frame en el centro de la pantalla
        instanceOfUsersFrame.setLocationRelativeTo(null);
        // Icono para revelar/ocultar la contraseña
        btnRevelar.setIcon(iconoMostrar);
        // Deshabilitar el botón de revelar inicialmente
        btnRevelar.setEnabled(false);
        // Deshabilitar el botón de modificar inicialmente
        btnModificar.setEnabled(false);
        // Cerrar el frame al cerrar
        Close(instanceOfUsersFrame);
        // Cargar listeners de acciones
        cargarActionListeners();
        // Cargar listeners de mouse
        cargarMouseListeners();
        // Cargar listeners de teclado
        cargarKeyListeners();
        // Limpiar tabla de usuarios
        jtbUsuarios.setModel(new DefaultTableModel(0, 0));
        // Foco en el campo de nombre completo
        txtNombreCompleto.requestFocus();
        // Cargar usuarios en una nueva hebra para evitar bloqueos
        new Thread(() -> {
            jtbUsuarios.setModel(cargarUsuarios());
        }).start();
        return instanceOfUsersFrame;
    }

    /**
     * Método para cargar los listeners de acciones en los componentes de la
     * interfaz.
     */
    @Override
    public void cargarActionListeners() {
        quitActionListeners();
        btnRegistrar.addActionListener(this);
        btnRegresar.addActionListener(this);
        jmiEliminar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnRevelar.addActionListener(this);
    }

    /**
     * Método para cargar los listeners de teclado en el campo de contraseña. Se
     * valida la fortaleza de la contraseña mientras se escribe.
     */
    @Override
    public void cargarKeyListeners() {
        quitKeyListener(txtPassword);
//        quitKeyListener(jtbUsuarios);
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                // Validar la fortaleza de la contraseña
                String psw = String.valueOf(txtPassword.getPassword());
                if (psw.length() > 0) {
                    btnRevelar.setEnabled(true);
                } else {
                    btnRevelar.setEnabled(false);
                    lblPassword.setText(""); // Limpiar el texto del JLabel cuando no hay contraseña
                }
                if (psw.length() != 0) {
                    if (psw.length() < 8) {
                        lblPassword.setText("La contraseña debe contener al menos 8 caracteres.");
                    } else if (!contieneMayuscula(psw)) {
                        lblPassword.setText("La contraseña debe contener al menos 1 mayúscula.");
                    } else if (!contieneNumero(psw)) {
                        lblPassword.setText("La contraseña debe contener al menos 1 número.");
                    } else if (!contieneSigno(psw)) {
                        lblPassword.setText("La contraseña debe contener al menos 1 signo.");
                    } else if (contieneEspacioBlanco(psw)) {
                        lblPassword.setText("La contraseña no debe contener espacios en blanco.");
                    } else if (contieneCaracteresNoPermitidos(psw)) {
                        lblPassword.setText("La contraseña contiene caracteres no permitidos.");
                    } else {
                        lblPassword.setText("");
                    }
                }

            }
        });
        jtbUsuarios.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                boolean value = bloquearMultipleModificacion();
                if (value) {
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
                        autocompletarCampos();
                    }
                }
            }
        });
    }

    /**
     * Método para cargar los listeners de mouse en la tabla de usuarios. Se
     * manejan eventos de clic derecho y clic izquierdo.
     */
    @Override
    public void cargarMouseListeners() {
        jtbUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    if (evt.getClickCount() == 1) {
                        // Mostrar menú contextual para eliminar usuario al hacer clic derecho
                        jmiEliminar.setText("Eliminar");
                        jpmOptions.add(jmiEliminar);
                        jpmOptions.show(jtbUsuarios, evt.getX(), evt.getY());
                    }
                } else {
                    // Autocompletar campos de texto al hacer clic izquierdo en la tabla
                    autocompletarCampos();
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                bloquearMultipleModificacion();
            }
        });
    }

    /**
     * Método para eliminar los listeners de acciones de los componentes de la
     * interfaz.
     */
    @Override
    public void quitActionListeners() {
        btnRegistrar.removeActionListener(this);
        btnRegresar.removeActionListener(this);
        jmiEliminar.removeActionListener(this);
        btnLimpiar.removeActionListener(this);
        btnModificar.removeActionListener(this);
        btnRevelar.removeActionListener(this);
    }

    /**
     * Método para eliminar un listener de teclado de un componente.
     */
    @Override
    public void quitKeyListener(Component componente) {
        // No se implementa en esta versión
    }

    /**
     * Método para eliminar un listener de mouse de un componente.
     */
    @Override
    public void quitMouseListener(Component componente) {
        // No se implementa en esta versión
    }

    /**
     * Método para manejar eventos de acción en los componentes de la interfaz.
     *
     * @param e Evento de acción generado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Determinar la fuente del evento y ejecutar la acción correspondiente
        if (e.getSource() == btnRegistrar) {
            // Registrar un nuevo usuario al hacer clic en el botón de registrar
            registrarUsuario();
        } else if (e.getSource() == btnRegresar) {
            // Limpiar campos y volver al menú principal al hacer clic en el botón de regresar
            limpiarCampos();
            instanceOfUsersFrame.dispose();
            MenuServiceImpl.getInstance().getInstanceOfFrame().setVisible(true);
        } else if (e.getSource() == jmiEliminar) {
            // Eliminar un usuario al seleccionar la opción de eliminar en el menú contextual
            eliminarUsuario();
        } else if (e.getSource() == btnLimpiar) {
            // Limpiar campos al hacer clic en el botón de limpiar
            limpiarCampos();
        } else if (e.getSource() == btnModificar) {
            // Actualizar un usuario existente al hacer clic en el botón de modificar
            actualizarUsuario();
        } else if (e.getSource() == btnRevelar) {
            // Mostrar u ocultar la contraseña al activar/desactivar el botón de revelar
            if (btnRevelar.isSelected()) {
                txtPassword.setEchoChar((char) 0);
                btnRevelar.setIcon(iconoOcultar);
            } else {
                txtPassword.setEchoChar('\u2022');
                btnRevelar.setIcon(iconoMostrar);
            }
        }
    }

    /**
     * Método para cargar la lista de usuarios en la tabla.
     *
     * @return Un modelo de tabla con los usuarios cargados.
     */
    @Override
    public DefaultTableModel cargarUsuarios() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre Completo", "Usuario"}, 0);
        try {
            // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
            UserDAO userDAO = new UserDAOImpl(new User());
            // Obtener la lista de usuarios
            List<User> lista = userDAO.listar();
            // Agregar cada usuario a la tabla
            lista.forEach(user -> {
                model.addRow(new Object[]{user.getId(), user.getNombreCompleto(), user.getUsername()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            // Manejo de errores de conexión
            alerta.manejarErrorConexion(this.getClass(), e);
        }
        return model;
    }

    /**
     * Método para registrar un nuevo usuario. Se obtienen los datos de los
     * campos de texto y se valida su integridad antes de proceder al registro.
     */
    @Override
    public void registrarUsuario() {
        // Obtener datos de los campos de texto
        String nombreCompleto = txtNombreCompleto.getText();
        String user = txtUsuario.getText();
        String password = String.valueOf(txtPassword.getPassword());

        // Validar que no haya campos vacíos
        if (nombreCompleto.isEmpty() || user.isEmpty() || password.isEmpty()) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }

        try {
            // Crear un nuevo objeto User con los datos ingresados
            User userCreated = new User();
            userCreated.setId(0);
            userCreated.setNombreCompleto(nombreCompleto);
            userCreated.setUsername(user);
            userCreated.setSalt(generateSalt());
            userCreated.setHashed_password(hashPassword(password, userCreated.getSalt()));

            // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
            UserDAO userDAO = new UserDAOImpl(userCreated);
            // Verificar si el nombre de usuario ya existe en la base de datos
            User userByUsername = userDAO.getUserByUsername();
            if (userByUsername != null) {
                alerta.advertencia("El nombre de usuario no está disponible");
                txtUsuario.setText("");
                txtPassword.setText("");
                txtUsuario.requestFocus();
            } else {
                // Si el nombre de usuario no existe, registrar el nuevo usuario
                userDAO.registrar();
                // Limpiar campos y actualizar tabla de usuarios
                limpiarCampos();
                jtbUsuarios.setModel(cargarUsuarios());
                alerta.aviso("Registro exitoso.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de errores de conexión
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    /**
     * Método para actualizar un usuario existente. Se obtienen los datos de los
     * campos de texto y se valida su integridad antes de proceder a la
     * actualización.
     */
    @Override
    public void actualizarUsuario() {
        // Obtener datos de los campos de texto
        String nombreCompleto = txtNombreCompleto.getText();
        String user = txtUsuario.getText();
        String password = String.valueOf(txtPassword.getPassword());

        // Validar que no haya campos vacíos
        if (nombreCompleto.isEmpty() || user.isEmpty()) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }

        try {
            // Crear un nuevo objeto User con los datos ingresados
            User userForUpdate = new User();
            userForUpdate.setId((int) jtbUsuarios.getValueAt(jtbUsuarios.getSelectedRow(), 0));

            // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
            UserDAO userDAO = new UserDAOImpl(userForUpdate);
            // Obtener el usuario existente a actualizar
            User userFind = userDAO.getById(userForUpdate.getId());

            // Actualizar los datos del usuario
            userFind.setNombreCompleto(nombreCompleto);
            userFind.setUsername(user);
            userFind.setSalt(password.isEmpty() ? userFind.getSalt() : generateSalt());
            userFind.setHashed_password(password.isEmpty() ? userFind.getHashed_password() : hashPassword(password, userFind.getSalt()));

            // Actualizar el usuario en la base de datos
            userDAO = new UserDAOImpl(userFind);
            userDAO.actualizar();
            limpiarCampos();

            if (userFind.getId() == LoginServiceImpl.userLogued.getId()) {
                // Si se actualiza el usuario logueado, mostrar mensaje y redirigir al inicio de sesión
                alerta.aviso("Actualización exitosa. Inicie sesión nuevamente");
                instanceOfUsersFrame.dispose();
                LoginServiceImpl.getInstance().getInstanceOfFrame().setVisible(true);

            } else {
                // Si se actualiza otro usuario, actualizar tabla de usuarios y mostrar mensaje
                jtbUsuarios.setModel(cargarUsuarios());
                alerta.aviso("Actualización exitosa.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de errores de conexión
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    /**
     * Método para eliminar un usuario. Se muestra un mensaje de confirmación
     * antes de realizar la eliminación.
     */
    @Override
    public void eliminarUsuario() {
        try {
            if (alerta.confirmacion("¿Está seguro de eliminar este usuario?") == 0) {
                // Obtener el ID del usuario a eliminar
                User userForDelete = new User();
                userForDelete.setId((int) jtbUsuarios.getValueAt(jtbUsuarios.getSelectedRow(), 0));
                // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
                UserDAO userDAO = new UserDAOImpl(userForDelete);
                // Obtener el usuario a eliminar
                User userFind = userDAO.getById(userForDelete.getId());
                if (userFind.getId() == LoginServiceImpl.userLogued.getId()) {
                    // Evitar eliminar el usuario logueado
                    alerta.advertencia("No puedes eliminar tu propio usuario.");
                } else {
                    // Eliminar el usuario de la base de datos
                    userDAO.eliminar();
                    // Actualizar tabla de usuarios y limpiar campos
                    jtbUsuarios.setModel(cargarUsuarios());
                    limpiarCampos();
                    alerta.aviso("Usuario eliminado correctamente.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de errores de conexión
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    /**
     * Método para autocompletar los campos de texto al hacer clic en la tabla
     * de usuarios.
     */
    @Override
    public void autocompletarCampos() {
        int rowSelected = jtbUsuarios.getSelectedRow();
        if (rowSelected == -1) {
            // No hacer nada si no se selecciona ninguna fila
        } else {
            // Obtener datos de la fila seleccionada y completar campos de texto
            String nombreCompleto = jtbUsuarios.getValueAt(rowSelected, 1).toString();
            String username = jtbUsuarios.getValueAt(rowSelected, 2).toString();
            txtNombreCompleto.setText(nombreCompleto);
            txtUsuario.setText(username);
            txtPassword.setText("");
            lblPassword.setText("");
            btnRevelar.setIcon(iconoMostrar);
            btnRevelar.setEnabled(false);
            btnRegistrar.setEnabled(false);
            btnModificar.setEnabled(true);
        }
    }

    /**
     * Método para limpiar los campos de texto y habilitar el botón de
     * registrar.
     */
    @Override
    public void limpiarCampos() {
        limpiarCamposSinTabla();
        jtbUsuarios.clearSelection();
    }

    public void limpiarCamposSinTabla() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        lblPassword.setText("");
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnRevelar.setEnabled(false);
    }

    public boolean bloquearMultipleModificacion() {
        if (jtbUsuarios.getSelectedRowCount() > 1) {
            limpiarCamposSinTabla();
            return false;
        }
        return true;
    }
}
