package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOimplements.DetallePedidoDAOImpl;
import com.tienda.data_access_layer.DetallePedidoDAO;
import com.tienda.entity.DetallePedido;
import com.tienda.presentation_layer.DetallePedidoPanel;
import com.tienda.utilities.ServiceUtilities;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.*;

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
    private final JLabel lblId, lblEstado, lblProveedor, lblProducto, lblFechaPedido, lblFechaEntrega, lblCantidad, lblUnidad, lblMontoTotal, lblObservaciones;
    private final int idDetalle;

    // Constructor privado para garantizar una única instancia de UserServiceImpl
    private DetallePedidoServiceImpl(int idDetalle) {
        instanceOfDetallePedidoPanel = DetallePedidoPanel.getInstance();
        btnRegresar = instanceOfDetallePedidoPanel.getBtnRegresar();
        lblId = instanceOfDetallePedidoPanel.getLbId();
        lblEstado = instanceOfDetallePedidoPanel.getLbEstado();
        lblProveedor = instanceOfDetallePedidoPanel.getLbProveedor();
        lblProducto = instanceOfDetallePedidoPanel.getLblProducto();
        lblFechaPedido = instanceOfDetallePedidoPanel.getLbFechaPedido();
        lblFechaEntrega = instanceOfDetallePedidoPanel.getLbFechaEntrega();
        lblCantidad = instanceOfDetallePedidoPanel.getLbCantidad();
        lblUnidad = instanceOfDetallePedidoPanel.getLbUnidad();
        lblMontoTotal = instanceOfDetallePedidoPanel.getLbMontoTotal();
        lblObservaciones = instanceOfDetallePedidoPanel.getLbObservaciones();
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
        // Cargar usuarios en una nueva hebra para evitar bloqueos
        new Thread(() -> {
            cargarDetallePedido();
        }).start();
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

    public DetallePedidoPanel getInstanceOfDetallePedidoPanel() {
        return instanceOfDetallePedidoPanel;
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
            // Limpiar campos y volver al hacer clic en el botón de regresar
            limpiarCamposGeneric(null, instanceOfDetallePedidoServiceImpl);
            TransaccionServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanceOfDetallePedidoPanel);
        }
    }

    /**
     * Método para cargar la lista de usuarios en la tabla.
     *
     * @return Un modelo de tabla con los usuarios cargados.
     */
    private void cargarDetallePedido() {
        try {
            setCursores(waitCursor);
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(idDetalle);
            DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl(detallePedido);
            detallePedido = detallePedidoDAO.getById();
            detallePedidoDAO.setEntity(detallePedido);
            System.out.println(detallePedido);

            int id = detallePedido.getId();
            String estado = detallePedido.getEstado();
            String proveedor = detallePedidoDAO.getProveedor();
            String producto = detallePedidoDAO.getProducto();
            Date fecha_pedido = detallePedido.getFecha_pedido();
            Date fecha_entrega = detallePedido.getFecha_entrega();
            double cantidad = detallePedido.getCantidad();
            String unidadMedida = detallePedidoDAO.getUnidadMedida();
            double monto_total = detallePedido.getMonto_total();
            String observaciones = detallePedido.getObservaciones();

            lblId.setText(String.valueOf(id));
            lblEstado.setText(estado);
            lblProveedor.setText(proveedor);
            lblProducto.setText(producto);
            lblFechaPedido.setText(String.valueOf(fecha_pedido));
            lblFechaEntrega.setText(String.valueOf(fecha_entrega));
            lblCantidad.setText(String.valueOf(cantidad));
            lblUnidad.setText(unidadMedida);
            lblMontoTotal.setText(String.valueOf(monto_total));
            lblObservaciones.setText(observaciones);

        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        } finally {
            setCursores(defaultCursor);
        }
    }

    public void limpiarCampos() {
        lblId.setText("");
        lblEstado.setText("");
        lblProveedor.setText("");
        lblProducto.setText("");
        lblFechaPedido.setText(String.valueOf(""));
        lblFechaEntrega.setText(String.valueOf(""));
        lblCantidad.setText(String.valueOf(""));
        lblUnidad.setText("");
        lblMontoTotal.setText(String.valueOf(""));
        lblObservaciones.setText("");
    }

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(instanceOfDetallePedidoPanel.getComponents(), cursor);
    }
}
