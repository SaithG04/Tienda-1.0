package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.DetallePedido;
import com.tienda.utilities.*;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

public class DetallePedidoDAOImpl extends DataAccessUtilities implements DetallePedidoDAO, Serializable {

    private DetallePedido detallePedido;
    private static final String NAMETABLE = "detalle_pedidos";

    /**
     * Constructor que inicializa la instancia del DAO con un objeto de usuario.
     *
     * @param detallePedido
     */
    public DetallePedidoDAOImpl(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    @Override
    public void setEntity(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
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
    public String getProveedor() throws ClassNotFoundException, SQLException {
        String query = "SELECT razon_social FROM proveedores where id=?";
        return getByOtherParameter(query, detallePedido.getId_proveedor(), NAMETABLE).toString();
    }

    @Override
    public String getProducto() throws ClassNotFoundException, SQLException {
        String query = "SELECT nombre FROM productos where id=?";
        return getByOtherParameter(query, detallePedido.getId_producto(), NAMETABLE).toString();
    }
    
    @Override
    public String getUnidadMedida() throws ClassNotFoundException, SQLException {
        String query = "SELECT medida FROM productos where id=?";
        return getByOtherParameter(query, detallePedido.getId_producto(), NAMETABLE).toString();
    }
}
