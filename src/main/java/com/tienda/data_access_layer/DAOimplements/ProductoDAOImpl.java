package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.data_transfer_layer.ProductoDTO;
import com.tienda.entity.User;
import java.io.Serializable;

import java.sql.*;

/**
 * Implementación del DAO de usuario para acceder a la base de datos.
 */
public class ProductoDAOImpl extends MySqlConnectionFactory implements Serializable {

    private final ProductoDTO dto;

    /**
     * Constructor que inicializa el DAO con un DTO de usuario.
     *
     * @param dto El DTO de usuario utilizado para la comunicación con el DAO.
     */
    public ProductoDAOImpl(ProductoDTO dto) {
        this.dto = dto;
    }
    
    public void getProductos(){}
}
