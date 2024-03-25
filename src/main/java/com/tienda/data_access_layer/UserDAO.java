package com.tienda.data_access_layer;

import com.tienda.entity.User;
import java.sql.*;

/**
 * Interfaz que define operaciones específicas para manipular datos de usuarios
 * en la base de datos.
 * 
 * @author isai_
 */
public interface UserDAO extends CRUD<User> {

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @return El usuario encontrado por su nombre de usuario, o null si no se
     * encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    User getUserByUsername() throws SQLException, ClassNotFoundException;

}
