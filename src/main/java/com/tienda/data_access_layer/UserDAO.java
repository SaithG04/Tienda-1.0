package com.tienda.data_access_layer;

import com.tienda.entity.User;
import java.sql.*;

/**
 *
 * @author isai_
 */
public interface UserDAO extends CRUD<User> {

    User getUserByUsername() throws SQLException, ClassNotFoundException;

}
