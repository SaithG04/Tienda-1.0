package com.tienda.data_access_layer.DAOImplements;

import com.tienda.data_access_layer.DetallePedidoDAO;
import com.tienda.entity.*;
import com.tienda.utilities.DataAccessUtilities;
import java.sql.SQLException;
import java.util.List;

public class DetallePedidoDAOImpl extends DataAccessUtilities implements DetallePedidoDAO {

    private DetallePedido detallePedido;
    private static final String NAMETABLE = "detalle_pedidos";

    public DetallePedidoDAOImpl(DetallePedido transaccion) {
        this.detallePedido = transaccion;
    }

    @Override
    public void setEntity(DetallePedido transaccion) {
        this.detallePedido = transaccion;
    }

    @Override
    public DetallePedido getById() throws SQLException, ClassNotFoundException {
        return getByIdGeneric(detallePedido.getId(), NAMETABLE);
    }

    @Override
    public boolean registrar() throws ClassNotFoundException, SQLException {
        return registrarGeneric(NAMETABLE, detallePedido);
    }

    @Override
    public List<DetallePedido> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    @Override
    public boolean actualizar() throws ClassNotFoundException, SQLException {
        return actualizarGeneric(NAMETABLE, detallePedido);
    }

    @Override
    public boolean eliminar() throws ClassNotFoundException, SQLException {
        return eliminarGeneric(NAMETABLE, detallePedido.getId());
    }

    @Override
    public String getProveedor() {
        try {
            String query = "SELECT razon_social FROM proveedores WHERE id=?";
            return getByOtherParameter(query, detallePedido.getId_proveedor(), "proveedores").toString();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public String getProducto(){
        try {
            String query = "SELECT nombre FROM productos WHERE id=?";
            return getByOtherParameter(query, detallePedido.getId_proveedor(), "productos").toString();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
