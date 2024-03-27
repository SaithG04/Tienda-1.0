package com.tienda.data_access_layer;

import com.tienda.entity.Proveedor;
import java.sql.SQLException;

/**
 *
 * @author isai_
 */
public interface ProveedorDAO extends CRUD<Proveedor>{
    Proveedor getProveedorByRuc() throws SQLException, ClassNotFoundException;
}
