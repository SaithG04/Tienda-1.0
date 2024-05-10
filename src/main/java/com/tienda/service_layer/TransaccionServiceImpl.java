package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOimplements.TransaccionDAOImpl;
import com.tienda.data_access_layer.TransaccionDAO;
import com.tienda.entity.Transaccion;
import com.tienda.utilities.ServiceUtilities;
import com.tienda.presentation_layer.TransaccionPanel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase implementa la interfaz UserService y gestiona las operaciones de
 * usuario en la capa de servicio.
 *
 * @author isai_
 */
public final class TransaccionServiceImpl extends ServiceUtilities implements ActionListener, FrameService {

    // Declaración de variables miembro
    private static volatile TransaccionServiceImpl instanceOfTransaccionServiceImpl;

    // Instancia del frame de usuarios
    private final TransaccionPanel instanceOfTransaccionPanel;

    // Componentes de la interfaz de usuario
    private final JButton btnRegresar, btnRefresh;
    private final JTable jtbTransacciones;
    private final JPopupMenu jpmOptions;
    private final JMenuItem jmiVerDetalle;

    // Constructor privado para garantizar una única instancia de UserServiceImpl
    private TransaccionServiceImpl() {
        instanceOfTransaccionPanel = TransaccionPanel.getInstance();
        btnRegresar = instanceOfTransaccionPanel.getBtnRegresar();
        jtbTransacciones = instanceOfTransaccionPanel.getJtbTransacciones();
        jpmOptions = instanceOfTransaccionPanel.getJpmOptions();
        jmiVerDetalle = instanceOfTransaccionPanel.getMiVerDetalle();
        btnRefresh = instanceOfTransaccionPanel.getBtnRefresh();

    }

    public void loadPanel() {
        addPanelToFrame(instanceOfTransaccionPanel);
        Close(instanceOfFrame);
        // Cargar listeners de acciones
        cargarActionListeners();
        // Cargar listeners de mouse
        cargarMouseListeners();
        // Cargar listeners de teclado
        cargarKeyListeners();
        // Limpiar tabla de usuarios
        jtbTransacciones.setModel(new DefaultTableModel(0, 0));
        // Foco en el campo de nombre completo
        // Cargar usuarios en una nueva hebra para evitar bloqueos
        new Thread(() -> {
            jtbTransacciones.setModel(cargarTransacciones());
        }).start();
        //Hacer no editable la tabla Usuarios
        configurarTablaNoEditable(jtbTransacciones);
    }

    /**
     * Método para obtener una instancia única de UserServiceImpl (patrón
     * Singleton).
     *
     * @return Una instancia única de UserServiceImpl.
     */
    @SuppressWarnings("DoubleCheckedLocking")
    public static TransaccionServiceImpl getInstance() {
        if (instanceOfTransaccionServiceImpl == null) {
            synchronized (TransaccionServiceImpl.class) { // Sincronización para hilos
                if (instanceOfTransaccionServiceImpl == null) {
                    instanceOfTransaccionServiceImpl = new TransaccionServiceImpl();
                }
            }
        }
        return instanceOfTransaccionServiceImpl;
    }

    /**
     * Método para cargar los listeners de acciones en los componentes de la
     * interfaz.
     */
    @Override
    public void cargarActionListeners() {
        quitActionListeners();
        btnRegresar.addActionListener(this);
        jmiVerDetalle.addActionListener(this);
        btnRefresh.addActionListener(this);
    }

    @Override
    public void cargarKeyListeners() {
    }

    /**
     * Método para cargar los listeners de mouse en la tabla de usuarios. Se
     * manejan eventos de clic derecho y clic izquierdo.
     */
    @Override
    public void cargarMouseListeners() {
        jtbTransacciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    if (evt.getClickCount() == 1) {
                        // Mostrar menú contextual para eliminar usuario al hacer clic derecho
                        jmiVerDetalle.setText("Ver más detalles de la transacción.");
                        jpmOptions.show(jtbTransacciones, evt.getX(), evt.getY());
                    }
                }
            }
        });
    }

    /**
     * Método para eliminar los listeners de acciones de los componentes de la
     * interfaz.
     */
    @Override
    public void quitActionListeners() {
        btnRegresar.removeActionListener(this);
        jmiVerDetalle.removeActionListener(this);
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
        if (e.getSource() == btnRegresar) {
            MenuServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanceOfTransaccionPanel);
        } else if (e.getSource() == btnRefresh) {
            try {
                setCursores(waitCursor);
                jtbTransacciones.setModel(new DefaultTableModel(0, 0));
                jtbTransacciones.setModel(cargarTransacciones());
            } finally {
                setCursores(defaultCursor);
            }
        } else if (e.getSource() == jmiVerDetalle) {
            try {
                setCursores(waitCursor);
                new Thread(() -> {
                    int idDetalle = getIdDetalle();
                    DetallePedidoServiceImpl.getInstance(idDetalle).loadPanel();
                    removePanelFromFrame(instanceOfTransaccionPanel);
                }).start();
            } catch (Exception ex) {
                errorSQL(this.getClass(), ex);
            } finally {
                setCursores(defaultCursor);
            }
        }
    }

    /**
     * Método para cargar la lista de usuarios en la tabla.
     *
     * @return Un modelo de tabla con los usuarios cargados.
     */
    private DefaultTableModel cargarTransacciones() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Tipo", "Fecha de registro", "Monto"}, 0);
        try {
            setCursores(waitCursor);
            // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
            TransaccionDAO transaccionDAO = new TransaccionDAOImpl(new Transaccion());
            // Obtener la lista de usuarios
            List<Transaccion> lista = transaccionDAO.listar();
            // Agregar cada usuario a la tabla
            lista.forEach(transaccion -> {
                model.addRow(new Object[]{transaccion.getId(), transaccion.getTipo(), transaccion.getFecha_registro(), transaccion.getMonto()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        } finally {
            setCursores(defaultCursor);
        }
        return model;
    }

    private int getIdDetalle() {
        try {
            Transaccion transacBuscada = new Transaccion();
            transacBuscada.setId((int) jtbTransacciones.getValueAt(jtbTransacciones.getSelectedRow(), 0));
            TransaccionDAO transaccionDAO = new TransaccionDAOImpl(transacBuscada);
            return transaccionDAO.getById().getId_detalle();
        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        }
        return -1;
    }

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(instanceOfTransaccionPanel.getComponents(), cursor);
    }
}
