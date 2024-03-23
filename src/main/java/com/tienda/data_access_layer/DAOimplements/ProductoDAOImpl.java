package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.Producto;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 * Implementación del DAO de usuario para acceder a la base de datos.
 */
public class ProductoDAOImpl extends MySqlConnectionFactory implements Serializable, ProductoDAO {

    private final Producto producto;
    private static final String NAMETABLE = "productos";

    public ProductoDAOImpl(Producto producto) {
        this.producto = producto;
    }

    @Override
    public Producto getById(int id) throws SQLException, ClassNotFoundException {
        return getByIdGeneric(id, NAMETABLE);
    }

    @Override
    public void registrar() throws ClassNotFoundException, SQLException {
        registrarGeneric(NAMETABLE, producto);
    }

    @Override
    public List<Producto> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    @Override
    public void actualizar() throws ClassNotFoundException, SQLException {
        actualizarGeneric(NAMETABLE, producto.getId(), producto);
    }

    @Override
    public void eliminar() throws ClassNotFoundException, SQLException {
        eliminarGeneric(NAMETABLE, producto.getId());
    }

    public static Map<Integer, String> getNameProveedor() throws ClassNotFoundException, SQLException {
        String query = "SELECT id, razon_social from proveedores";
        try (Connection connection = new MySqlConnectionFactory().getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                // Extraer los datos de la fila del ResultSet usando la función de extracción
                Map<Integer, String> rowData = new HashMap<Integer, String>();
                rowData.put(Integer.parseInt(resultSet.getString("id")),resultSet.getString("razon_social"));
                // Mapear los datos extraídos a un objeto DTO usando la función de mapeo
                return rowData;
            }
            return null;
        }
    }
}
