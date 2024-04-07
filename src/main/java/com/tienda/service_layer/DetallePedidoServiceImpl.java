package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOImplements.DetallePedidoDAOImpl;
import com.tienda.data_access_layer.DetallePedidoDAO;
import com.tienda.entity.DetallePedido;
import com.tienda.presentation_layer.DetallePedidoPanel;
import com.tienda.utilities.ServiceUtilities;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase implementa la interfaz UserService y gestiona las operaciones de
 * usuario en la capa de servicio.
 *
 * @author isai_
 */
public final class DetallePedidoServiceImpl extends ServiceUtilities implements ActionListener, FrameService {

    // Declaración de variables miembro
    private static volatile DetallePedidoServiceImpl instanceOfDetallePedidoServiceImpl;

    // Instancia del frame de usuarios
    private final DetallePedidoPanel instanceOfDetallePedidoPanel;

    // Componentes de la interfaz de usuario
    private final JButton btnRegresar;
    private final JTable jtbDetallePedido;
    private final int idDetalle;

    // Constructor privado para garantizar una única instancia de UserServiceImpl
    private DetallePedidoServiceImpl(int idDetalle) {
        instanceOfDetallePedidoPanel = DetallePedidoPanel.getInstance();
        btnRegresar = instanceOfDetallePedidoPanel.getBtnRegresar();
        jtbDetallePedido = instanceOfDetallePedidoPanel.getJtbDetallePedido();
        this.idDetalle = idDetalle;
    }

    public void loadPanel() {
        addPanelToFrame(instanceOfDetallePedidoPanel);
        Close(instanceOfFrame);
        // Cargar listeners de acciones
        cargarActionListeners();
        // Cargar listeners de mouse
        cargarMouseListeners();
        // Cargar listeners de teclado
        cargarKeyListeners();
        // Limpiar tabla de usuarios
        jtbDetallePedido.setModel(new DefaultTableModel(0, 0));
        // Foco en el campo de nombre completo
        // Cargar usuarios en una nueva hebra para evitar bloqueos
        new Thread(() -> {
            jtbDetallePedido.setModel(cargarDetallePedido());
        }).start();
        //Hacer no editable la tabla Usuarios
        configurarTablaNoEditable(jtbDetallePedido);
    }

    /**
     * Método para obtener una instancia única de UserServiceImpl (patrón
     * Singleton).
     *
     * @param idDetalle
     * @return Una instancia única de UserServiceImpl.
     */
    @SuppressWarnings("DoubleCheckedLocking")
    public static DetallePedidoServiceImpl getInstance(int idDetalle) {
        if (instanceOfDetallePedidoServiceImpl == null) {
            synchronized (DetallePedidoServiceImpl.class) { // Sincronización para hilos
                if (instanceOfDetallePedidoServiceImpl == null) {
                    instanceOfDetallePedidoServiceImpl = new DetallePedidoServiceImpl(idDetalle);
                }
            }
        }
        return instanceOfDetallePedidoServiceImpl;
    }

    /**
     * Método para cargar los listeners de acciones en los componentes de la
     * interfaz.
     */
    @Override
    public void cargarActionListeners() {
        quitActionListeners();
        btnRegresar.addActionListener(this);
    }

    @Override
    public void cargarKeyListeners() {
    }

    @Override
    public void cargarMouseListeners() {
    }

    /**
     * Método para eliminar los listeners de acciones de los componentes de la
     * interfaz.
     */
    @Override
    public void quitActionListeners() {
        btnRegresar.removeActionListener(this);
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
            // Limpiar campos y volver al menú principal al hacer clic en el botón de regresar
            limpiarCamposGeneric(jtbDetallePedido, instanceOfDetallePedidoServiceImpl);
            TransaccionServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanceOfDetallePedidoPanel);
        }
    }

    /**
     * Método para cargar la lista de usuarios en la tabla.
     *
     * @return Un modelo de tabla con los usuarios cargados.
     */
    private DefaultTableModel cargarDetallePedido() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Estado", "Proveedor", "Producto", "Fecha Pedido", "Fecha entrega",
            "Cantidad", "Unidad", "Monto Total", "Observaciones"}, 0);
        try {
            // Crear un objeto UserDAOImpl para realizar operaciones de base de datos
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(idDetalle);
            DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl(detallePedido);
            detallePedido = detallePedidoDAO.getById();
            detallePedidoDAO.setEntity(detallePedido);
            String proveedor = detallePedidoDAO.getProveedor();
            String producto = detallePedidoDAO.getProducto();
            model.addRow(new Object[]{detallePedido.getId(), detallePedido.getEstado(), proveedor, producto, detallePedido.getFecha_pedido(),
                detallePedido.getFecha_entrega(), detallePedido.getCantidad(), detallePedido.getUnidad(), detallePedido.getMonto_total(), detallePedido.getObservaciones()});
        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        }
        return model;
    }

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(instanceOfDetallePedidoPanel.getComponents(), cursor);
    }
}
