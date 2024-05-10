package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOImplements.ProveedorDAOImpl;
import com.tienda.data_access_layer.ProveedorDAO;
import com.tienda.entity.Proveedor;
import com.tienda.presentation_layer.ProveedorPanel;
import com.tienda.utilities.ServiceUtilities;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase implementa la interfaz ProveedorService y gestiona las operaciones
 * de proveedor en la capa de servicio.
 *
 * @author isai_
 */
public final class ProveedorServiceImpl extends ServiceUtilities implements ActionListener, FrameService {

    private static volatile ProveedorServiceImpl instanceOfProveedorServiceImpl;

    private final ProveedorPanel instanceOfProveedorPanel;

    private final JButton btnRegistrar, btnRegresar, btnModificar, btnLimpiar, btnRefresh;
    private final ButtonGroup bgEstado;
    private final JTextField txtRuc, txtRazonSocial, txtDireccion, txtWeb, txtTelefono, txtCorreo;
    private final JTextArea txtDescripcion, txtContacto, txtObservaciones;
    private final JComboBox cbCategoria;
    private final JRadioButton rbActivo, rbInactivo;
    private final JTable jtbProveedores;
    private final JPopupMenu jpmOptions;
    private final JMenuItem jmiEliminar;

//    private final Icon iconoMostrar;
    private ProveedorServiceImpl() {
        instanceOfProveedorPanel = ProveedorPanel.getInstance();
        btnRegistrar = instanceOfProveedorPanel.getBtnRegistrar();
        btnRegresar = instanceOfProveedorPanel.getBtnRegresar();
        btnModificar = instanceOfProveedorPanel.getBtnModificar();
        btnLimpiar = instanceOfProveedorPanel.getBtnLimpiar();
        btnRefresh = instanceOfProveedorPanel.getBtnRefresh();
        bgEstado = instanceOfProveedorPanel.getBgEstado();
        txtRuc = instanceOfProveedorPanel.getTxtRuc();
        txtRazonSocial = instanceOfProveedorPanel.getTxtRazonSocial();
        txtDireccion = instanceOfProveedorPanel.getTxtDireccion();
        txtWeb = instanceOfProveedorPanel.getTxtWeb();
        txtTelefono = instanceOfProveedorPanel.getTxtTelefono();
        txtContacto = instanceOfProveedorPanel.getTxtContacto();
        txtCorreo = instanceOfProveedorPanel.getTxtCorreo();
        txtDescripcion = instanceOfProveedorPanel.getTxtDescripcion();
        txtObservaciones = instanceOfProveedorPanel.getTxtObservaciones();
        cbCategoria = instanceOfProveedorPanel.getCbCategoria();
        rbActivo = instanceOfProveedorPanel.getRbActivo();
        rbInactivo = instanceOfProveedorPanel.getRbInactivo();
        jtbProveedores = instanceOfProveedorPanel.getJtbProveedores();
        jpmOptions = instanceOfProveedorPanel.getJpmOptions();
        jmiEliminar = instanceOfProveedorPanel.getMiEliminar();

    }

    public void loadPanel() {
        addPanelToFrame(instanceOfProveedorPanel);
        // Deshabilitar el botón de modificar inicialmente
        btnModificar.setEnabled(false);
        // Método para el frame al cerrar
        Close(instanceOfFrame);
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
        //Hacer no editable la tabla Proveedores
        configurarTablaNoEditable(jtbProveedores);
        listarCategorias();
    }

    /**
     * Método para obtener una instancia única de UserServiceImpl (patrón
     * Singleton).
     *
     * @return Una instancia única de UserServiceImpl.
     */
    @SuppressWarnings("DoubleCheckedLocking")
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
        quitKeyListener(txtRuc);
        quitKeyListener(txtRazonSocial);
        quitKeyListener(txtDireccion);
        quitKeyListener(txtTelefono);
        quitKeyListener(txtCorreo);
        quitKeyListener(txtWeb);
        jtbProveedores.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                boolean value = bloquearMultipleModificacionGeneric(jtbProveedores, instanceOfProveedorServiceImpl);
                if (value) {
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
                        autocompletarCampos();
                    }
                }
            }
        });
        txtRuc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarSoloNumeros(evt, txtRuc.getText(), (short) 11);
            }
        });
        txtRazonSocial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarLength(evt, txtRazonSocial.getText(), (short) 255);
            }
        });
        txtDireccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarLength(evt, txtDireccion.getText(), (short) 255);
            }
        });
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarLength(evt, txtTelefono.getText(), (short) 100);
            }
        });
        txtCorreo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarLength(evt, txtCorreo.getText(), (short) 255);
            }
        });
        txtWeb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarLength(evt, txtWeb.getText(), (short) 255);
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
                bloquearMultipleModificacionGeneric(jtbProveedores, instanceOfProveedorServiceImpl);
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
            limpiarCamposGeneric(jtbProveedores, instanceOfProveedorServiceImpl);
            MenuServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanceOfProveedorPanel);
        } else if (e.getSource() == jmiEliminar) {
            // Eliminar un usuario al seleccionar la opción de eliminar en el menú contextual
            eliminarProveedor();
        } else if (e.getSource() == btnLimpiar) {
            // Limpiar campos al hacer clic en el botón de limpiar
            limpiarCamposGeneric(jtbProveedores, instanceOfProveedorServiceImpl);
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

    public void limpiarCamposSinTabla() {
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtDescripcion.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtWeb.setText("");
        txtContacto.setText("");
        txtObservaciones.setText("");
        cbCategoria.setSelectedIndex(-1);
        bgEstado.clearSelection();
        btnRegistrar.setEnabled(true);
        btnModificar.setEnabled(false);
    }

    private DefaultTableModel cargarProveedores() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "RUC", "Razón social", "Descripción", "Dirección", "Telefono",
            "Correo", "Web", "Contacto", "Categoría", "Estado", "Fecha registro", "Observaciones"}, 0);
        try {
            setCursores(waitCursor);
            ProveedorDAO proveedorDAO = new ProveedorDAOImpl(new Proveedor());
            List<Proveedor> lista = proveedorDAO.listar();
            lista.forEach(proveedores -> {
                model.addRow(new Object[]{proveedores.getId(), proveedores.getRuc(), proveedores.getRazon_social(), proveedores.getDescripcion(),
                    proveedores.getDireccion(), proveedores.getTelefono(), proveedores.getCorreo(), proveedores.getWeb(), proveedores.getContacto(),
                    proveedores.getCategoria(), proveedores.getEstado(), proveedores.getFecha_registro(), proveedores.getObservaciones()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        } finally{
            setCursores(defaultCursor);
        }
        return model;
    }

    private void registrarProveedor() {
        try {
            setCursores(waitCursor);
            String ruc = txtRuc.getText();
            String razonSocial = txtRazonSocial.getText();
            String descripcion = txtDescripcion.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String web = txtWeb.getText();
            String contacto = txtContacto.getText();
            String observaciones = txtObservaciones.getText();
            if (algunCampoVacioGeneric(ruc, razonSocial, direccion, telefono, correo, contacto)) {
                alerta.faltanDatos();
                return;
            }

            if (cbCategoria.getSelectedIndex() == -1) {
                alerta.faltanDatos();
                return;
            }
            String categoria = cbCategoria.getSelectedItem().toString();
            Proveedor proveedorCreated = new Proveedor();

            proveedorCreated.setRuc(ruc);
            proveedorCreated.setRazon_social(razonSocial);
            proveedorCreated.setDescripcion(descripcion);
            proveedorCreated.setDireccion(direccion);
            proveedorCreated.setTelefono(telefono);
            proveedorCreated.setCorreo(correo);
            proveedorCreated.setWeb(web);
            proveedorCreated.setContacto(contacto);
            proveedorCreated.setObservaciones(observaciones);
            proveedorCreated.setCategoria(categoria);
            proveedorCreated.setEstado("Activo");

            LocalDate fechaActual = LocalDate.now();
            Date fechaSQL = Date.valueOf(fechaActual);
            proveedorCreated.setFecha_registro(fechaSQL);

            ProveedorDAO proveedorDAO = new ProveedorDAOImpl(proveedorCreated);
            Proveedor proveedor = proveedorDAO.getProveedorByRuc();

            if (proveedor != null) {
                alerta.advertencia("Ya existe un proveedor con ese número de ruc.");
                txtRuc.setText("");
                txtRuc.requestFocus();
            } else {
                boolean registrado = proveedorDAO.registrar();
                limpiarCamposGeneric(jtbProveedores, instanceOfProveedorServiceImpl);
                if (registrado) {
                    jtbProveedores.setModel(cargarProveedores());
                    alerta.aviso("Registro exitoso.");
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
    private void actualizarProveedor() {

        try {
            setCursores(waitCursor);
            String ruc = txtRuc.getText();
            String razonSocial = txtRazonSocial.getText();
            String descripcion = txtDescripcion.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String web = txtWeb.getText();
            String contacto = txtContacto.getText();
            String observaciones = txtObservaciones.getText();
            if (algunCampoVacioGeneric(ruc, razonSocial, direccion, telefono, correo, contacto)) {
                alerta.faltanDatos();
                return;
            }

            if (cbCategoria.getSelectedIndex() == -1) {
                alerta.faltanDatos();
                return;
            }
            String categoria = cbCategoria.getSelectedItem().toString();
            if (!rbActivo.isSelected() && !rbInactivo.isSelected()) {
                alerta.faltanDatos();
                return;
            }
            String estado = rbActivo.isSelected() ? "Activo" : "Inactivo";

            Proveedor proveedorForUpdate = new Proveedor();
            proveedorForUpdate.setId((int) jtbProveedores.getValueAt(jtbProveedores.getSelectedRow(), 0));
            ProveedorDAO proveedorDAO = new ProveedorDAOImpl(proveedorForUpdate);
            proveedorForUpdate = proveedorDAO.getById();

            proveedorForUpdate.setRuc(ruc);
            proveedorForUpdate.setRazon_social(razonSocial);
            proveedorForUpdate.setDescripcion(descripcion);
            proveedorForUpdate.setDireccion(direccion);
            proveedorForUpdate.setTelefono(telefono);
            proveedorForUpdate.setCorreo(correo);
            proveedorForUpdate.setWeb(web);
            proveedorForUpdate.setContacto(contacto);
            proveedorForUpdate.setObservaciones(observaciones);
            proveedorForUpdate.setCategoria(categoria);
            proveedorForUpdate.setEstado(estado);
            limpiarCamposGeneric(jtbProveedores, instanceOfProveedorServiceImpl);

            proveedorDAO.setEntity(proveedorForUpdate);
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
    private void eliminarProveedor() {
        if (alerta.confirmacion("¿Está seguro de eliminar este proveedor?") == 0) {
            try {
                setCursores(waitCursor);
                Proveedor proveedorForDelete = new Proveedor();
                proveedorForDelete.setId((int) jtbProveedores.getValueAt(jtbProveedores.getSelectedRow(), 0));
                ProveedorDAO proveedorDAO = new ProveedorDAOImpl(proveedorForDelete);
                // Eliminar el usuario de la base de datos
                limpiarCamposGeneric(jtbProveedores, instanceOfProveedorServiceImpl);
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
    private void autocompletarCampos() {
        int rowSelected = jtbProveedores.getSelectedRow();

        String ruc = txtRuc.getText();
        String razonSocial = txtRazonSocial.getText();
        String descripcion = txtDescripcion.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String web = txtWeb.getText();
        String contacto = txtContacto.getText();
        String observaciones = txtObservaciones.getText();

        boolean todosVacios = todosCamposVacios(ruc, razonSocial, descripcion, direccion,
                telefono, correo, web, contacto, observaciones);

        if (rowSelected != -1 && todosVacios && cbCategoria.getSelectedIndex() == -1 && !rbActivo.isSelected() && !rbInactivo.isSelected()) {
            // Obtener datos de la fila seleccionada y completar campos de texto
            ruc = jtbProveedores.getValueAt(rowSelected, 1).toString();
            razonSocial = jtbProveedores.getValueAt(rowSelected, 2).toString();
            descripcion = jtbProveedores.getValueAt(rowSelected, 3).toString();
            direccion = jtbProveedores.getValueAt(rowSelected, 4).toString();
            telefono = jtbProveedores.getValueAt(rowSelected, 5).toString();
            correo = jtbProveedores.getValueAt(rowSelected, 6).toString();
            web = jtbProveedores.getValueAt(rowSelected, 7).toString();
            contacto = jtbProveedores.getValueAt(rowSelected, 8).toString();
            String categoria = jtbProveedores.getValueAt(rowSelected, 9).toString();
            String estado = jtbProveedores.getValueAt(rowSelected, 10).toString();
            observaciones = jtbProveedores.getValueAt(rowSelected, 12).toString();
            txtRuc.setText(ruc);
            txtRazonSocial.setText(razonSocial);
            txtDescripcion.setText(descripcion);
            txtDireccion.setText(direccion);
            txtTelefono.setText(telefono);
            txtCorreo.setText(correo);
            txtWeb.setText(web);
            txtContacto.setText(contacto);
            cbCategoria.setSelectedItem(categoria);
            if (estado.equals("Activo")) {
                rbActivo.setSelected(true);
            } else {
                rbInactivo.setSelected(true);
            }
            txtObservaciones.setText(observaciones);
            btnRegistrar.setEnabled(false);
            btnModificar.setEnabled(true);
        }
    }

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(instanceOfProveedorPanel.getComponents(), cursor);
    }

    private void listarCategorias() {
        cbCategoria.removeAllItems();
        String[] categorías = {"Productos alimenticios", "Maquinaria TI"};
        addAllItems(cbCategoria, categorías);
        cbCategoria.setSelectedIndex(-1);
    }
}
