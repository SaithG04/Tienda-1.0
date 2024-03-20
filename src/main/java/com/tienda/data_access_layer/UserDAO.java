package com.tienda.data_access_layer;

import com.tienda.entity.User;
import java.sql.SQLException;
import java.util.List;

/**
 * La interfaz UserDAO proporciona métodos para acceder y manipular datos de
 * usuario en la base de datos.
 */
public interface UserDAO {

    /**
     * Obtiene un usuario por nombre de usuario.
     *
     * @return El usuario encontrado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase de la base de
     * datos.
     */
    User getUserByUsername() throws SQLException, ClassNotFoundException;

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param user El usuario a guardar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase de la base de
     * datos.
     */
    void saveUser(User user) throws SQLException, ClassNotFoundException;

    List<User> listarUsuarios() throws ClassNotFoundException, SQLException;
}
