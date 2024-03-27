package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOImplements.ProveedorDAOImpl;
import com.tienda.data_access_layer.ProveedorDAO;
import com.tienda.entity.Proveedor;
import com.tienda.presentation_layer.ProveedorFrame;
import com.tienda.service_layer.FrameService;
import com.tienda.utilities.ServiceUtilities;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase implementa la interfaz ProveedorService y gestiona las operaciones
 * de proveedor en la capa de servicio.
 *
 * @author isai_
 */
public class ProveedorServiceImpl extends ServiceUtilities implements ActionListener, FrameService<ProveedorFrame> {

    private static volatile ProveedorServiceImpl instanceOfProveedorServiceImpl;

    private final ProveedorFrame instanceOfProveedorFrame;

    private final JButton btnRegistrar, btnRegresar, btnModificar, btnLimpiar, btnRefresh;
    private final JTable jtbProveedores;
    private final JPopupMenu jpmOptions;
    private final JMenuItem jmiEliminar;

//    private final Icon iconoMostrar;
    private ProveedorServiceImpl() {
        instanceOfProveedorFrame = ProveedorFrame.getInstance();
        btnRegistrar = instanceOfProveedorFrame.getBtnRegistrar();
        btnRegresar = instanceOfProveedorFrame.getBtnRegresar();
        jtbProveedores = instanceOfProveedorFrame.getJtbProveedores();
        jpmOptions = instanceOfProveedorFrame.getJpmOptions();
        jmiEliminar = instanceOfProveedorFrame.getMiEliminar();
        btnModificar = instanceOfProveedorFrame.getBtnModificar();
        btnLimpiar = instanceOfProveedorFrame.getBtnLimpiar();
        btnRefresh = instanceOfProveedorFrame.getBtnRefresh();
//        iconoMostrar = icono("/images/mostrar_eye.jpg", 40, 40);
    }

    /**
     * Método para obtener una instancia única de UserServiceImpl (patrón
     * Singleton).
     *
     * @return Una instancia única de UserServiceImpl.
     */
    public static ProveedorServiceImpl getInstance() {
        if (instanceOfProveedorServiceImpl == null) {
            synchronized (ProveedorServiceImpl.class) { // Sincronización para hilos
                if (instanceOfProveedorServiceImpl == null) {
                    instanceOfProveedorServiceImpl = new ProveedorServiceImpl();
                }
            }
        }
        return instanceOfProveedorServiceImpl;
    }

    /**
     * Método para obtener una instancia del frame de usuarios. Este método
     * configura el frame de usuarios y carga los listeners y datos necesarios.
     *
     * @return Una instancia del frame de usuarios.
     */
    @Override
    public ProveedorFrame getInstanceOfFrame() {
        instanceOfProveedorFrame.setLocationRelativeTo(null);
        // Deshabilitar el botón de modificar inicialmente
        btnModificar.setEnabled(false);
        // Cerrar el frame al cerrar
        Close(instanceOfProveedorFrame);
        // Cargar listeners de acciones
        cargarActionListeners();
        // Cargar listeners de mouse
        cargarMouseListeners();
        // Cargar listeners de teclado
        cargarKeyListeners();
        // Limpiar tabla de usuarios
        jtbProveedores.setModel(new DefaultTableModel(0, 0));
        // Foco en el campo de nombre completo
        // Cargar usuarios en una nueva hebra para evitar bloqueos
        new Thread(() -> {
            jtbProveedores.setModel(cargarProveedores());
        }).start();
        return instanceOfProveedorFrame;
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
        btnRefresh.addActionListener(this);
    }

    /**
     * Método para cargar los listeners de teclado en el campo de contraseña. Se
     * valida la fortaleza de la contraseña mientras se escribe.
     */
    @Override
    public void cargarKeyListeners() {
        jtbProveedores.addKeyListener(new KeyAdapter() {
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
        jtbProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    if (evt.getClickCount() == 1) {
                        // Mostrar menú contextual para eliminar usuario al hacer clic derecho
                        jmiEliminar.setText("Eliminar");
                        jpmOptions.show(jtbProveedores, evt.getX(), evt.getY());
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
        btnRefresh.removeActionListener(this);
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
            registrarProveedor();
        } else if (e.getSource() == btnRegresar) {
            // Limpiar campos y volver al menú principal al hacer clic en el botón de regresar
            limpiarCampos();
            instanceOfProveedorFrame.dispose();
            MenuServiceImpl.getInstance().getInstanceOfFrame().setVisible(true);
        } else if (e.getSource() == jmiEliminar) {
            // Eliminar un usuario al seleccionar la opción de eliminar en el menú contextual
            eliminarProveedor();
        } else if (e.getSource() == btnLimpiar) {
            // Limpiar campos al hacer clic en el botón de limpiar
            limpiarCampos();
        } else if (e.getSource() == btnModificar) {
            // Actualizar un usuario existente al hacer clic en el botón de modificar
            actualizarProveedor();
        } else if (e.getSource() == btnRefresh) {
            try {
                setCursores(waitCursor);
                jtbProveedores.setModel(new DefaultTableModel(0, 0));
                jtbProveedores.setModel(cargarProveedores());
            } finally {
                setCursores(defaultCursor);
            }
        }
    }

    public DefaultTableModel cargarProveedores() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "RUC", "Razón social", "Descripción", "Dirección", "Telefono",
            "Correo", "Web", "Contacto", "Categoría", "Estado", "Fecha registro", "Observaciones"}, 0);
        try {
            // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
            ProveedorDAO proveedorDAO = new ProveedorDAOImpl(new Proveedor());
            // Obtener la lista de usuarios
            List<Proveedor> lista = proveedorDAO.listar();
            // Agregar cada usuario a la tabla
            lista.forEach(proveedores -> {
                model.addRow(new Object[]{proveedores.getId(), proveedores.getRuc(), proveedores.getRazon_social(), proveedores.getDescripcion(),
                    proveedores.getDireccion(), proveedores.getTelefono(), proveedores.getCorreo(), proveedores.getWeb(), proveedores.getContacto(),
                    proveedores.getCategoria(), proveedores.getEstado(), proveedores.getFecha_registro(), proveedores.getObservaciones()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        }
        return model;
    }

    /**
     * Método para registrar un nuevo usuario. Se obtienen los datos de los
     * campos de texto y se valida su integridad antes de proceder al registro.
     */
    public void registrarProveedor() {
        try {
            setCursores(waitCursor);
//            if (camposVaciosGeneric(nombreCompleto, user, password)) {
//                return;
//            }
            Proveedor proveedorCreated = new Proveedor();

            ProveedorDAO proveedorDAO = new ProveedorDAOImpl(proveedorCreated);
//            Proveedor userByUsername = userDAO.getUserByUsername();

            if (proveedorDAO != null) {
                alerta.advertencia("El nombre de usuario no está disponible");
            } else {
                boolean registrado = proveedorDAO.registrar();
                limpiarCampos();
                if (registrado) {
                    alerta.aviso("Registro exitoso.");
                    jtbProveedores.setModel(cargarProveedores());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            errorSQL(this.getClass(), e);
        } finally {
            setCursores(defaultCursor);
        }
    }

    /**
     * Método para actualizar un usuario existente. Se obtienen los datos de los
     * campos de texto y se valida su integridad antes de proceder a la
     * actualización.
     */
    public void actualizarProveedor() {

        try {
            setCursores(waitCursor);

//            if (camposVaciosGeneric(nombreCompleto, user)) {
//                return;
//            }
            Proveedor proveedorForUpdate = new Proveedor();
            proveedorForUpdate.setId((int) jtbProveedores.getValueAt(jtbProveedores.getSelectedRow(), 0));
            ProveedorDAO proveedorDAO = new ProveedorDAOImpl(proveedorForUpdate);
            Proveedor proveedorFind = proveedorDAO.getById();

            proveedorDAO = new ProveedorDAOImpl(proveedorFind);
            limpiarCampos();
            boolean actualizado = proveedorDAO.actualizar();

            if (actualizado) {
                jtbProveedores.setModel(cargarProveedores());
                alerta.aviso("Actualización exitosa.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            errorSQL(this.getClass(), e);
        } finally {
            setCursores(defaultCursor);
        }
    }

    /**
     * Método para eliminar un usuario. Se muestra un mensaje de confirmación
     * antes de realizar la eliminación.
     */
    public void eliminarProveedor() {
        if (alerta.confirmacion("¿Está seguro de eliminar este proveedor?") == 0) {
            try {
                setCursores(waitCursor);
                Proveedor proveedorForDelete = new Proveedor();
                proveedorForDelete.setId((int) jtbProveedores.getValueAt(jtbProveedores.getSelectedRow(), 0));
                ProveedorDAO proveedorDAO = new ProveedorDAOImpl(proveedorForDelete);
                // Eliminar el usuario de la base de datos
                limpiarCampos();
                boolean eliminado = proveedorDAO.eliminar();
                if (eliminado) {
                    // Actualizar tabla de usuarios y limpiar campos
                    jtbProveedores.setModel(cargarProveedores());
                    alerta.aviso("Proveedor eliminado correctamente.");
                }

            } catch (SQLException | ClassNotFoundException e) {
                // Manejo de errores de conexión
                errorSQL(this.getClass(), e);
            } finally {
                setCursores(defaultCursor);
            }
        }
    }

    /**
     * Método para autocompletar los campos de texto al hacer clic en la tabla
     * de usuarios.
     */
    public void autocompletarCampos() {
        int rowSelected = jtbProveedores.getSelectedRow();
        if (rowSelected == -1) {
            // No hacer nada si no se selecciona ninguna fila
        } else {
            // Obtener datos de la fila seleccionada y completar campos de texto
            String nombreCompleto = jtbProveedores.getValueAt(rowSelected, 1).toString();
            String username = jtbProveedores.getValueAt(rowSelected, 2).toString();
            btnRegistrar.setEnabled(false);
            btnModificar.setEnabled(true);
        }
    }

    /**
     * Método para limpiar los campos de texto y habilitar el botón de
     * registrar.
     */
    public void limpiarCampos() {
        limpiarCamposSinTabla();
        jtbProveedores.clearSelection();
    }

    public void limpiarCamposSinTabla() {
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(false);
    }

    public boolean bloquearMultipleModificacion() {
        if (jtbProveedores.getSelectedRowCount() > 1) {
            limpiarCamposSinTabla();
            return false;
        }
        return true;
    }

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(new Component[]{instanceOfProveedorFrame, btnRegistrar, btnModificar, btnRefresh}, cursor);
    }

}
