package com.tienda.data_access_layer;

import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import java.sql.*;

/**
 * Interfaz para el acceso a datos de los usuarios.
 */
public interface UserDAO extends CRUD<UserDTO> {

    /**
     * Obtiene un objeto UserDTO basado en el nombre de usuario.
     *
     * @return El UserDTO encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    UserDTO getUserByUsername() throws SQLException, ClassNotFoundException;

    /**
     * Extrae un objeto User a partir de un conjunto de resultados de la base de
     * datos.
     *
     * @param resultSet Conjunto de resultados de la consulta SQL.
     * @return El objeto User extraído de los resultados.
     * @throws SQLException Si ocurre un error al acceder a los datos en el
     * conjunto de resultados.
     */
    User extractUserFromResultSet(ResultSet resultSet) throws SQLException;
}
