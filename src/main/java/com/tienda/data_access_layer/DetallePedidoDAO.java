package com.tienda.data_access_layer;

import com.tienda.entity.*;
import java.sql.SQLException;

/**
 * Interfaz que define operaciones específicas para manipular datos de usuarios
 * en la base de datos.
 *
 * @author isai_
 */
public interface DetallePedidoDAO extends CRUD<DetallePedido> {

    String getProveedor() throws ClassNotFoundException, SQLException;

    String getProducto() throws ClassNotFoundException, SQLException;

    String getUnidadMedida() throws ClassNotFoundException, SQLException;
}
