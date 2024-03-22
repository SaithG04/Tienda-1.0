package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.User;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Implementación del DAO de usuario para acceder a la base de datos.
 */
public class UserDAOImpl extends MySqlConnectionFactory implements UserDAO, Serializable {

    private final User usuario;
    private static final String NAMETABLE = "users";

    public UserDAOImpl(User usuario) {
        this.usuario = usuario;
    }

    @Override
    public User getById(int id) throws SQLException, ClassNotFoundException {
        return getByIdGeneric(id, NAMETABLE);
    }

    @Override
    public void registrar(User entity) throws ClassNotFoundException, SQLException {
        Registrar(NAMETABLE, entity);
    }

    @Override
    public List<User> listar() throws ClassNotFoundException, SQLException {
        return Listar(NAMETABLE);
    }

    @Override
    public void actualizar(User entity) throws ClassNotFoundException, SQLException {

    }

    @Override
    public void eliminar(int id) throws ClassNotFoundException, SQLException {
        Eliminar(NAMETABLE, id);
    }

    @Override
    public User getUserByUsername() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username COLLATE utf8_bin = ?";
        try (Connection con = new MySqlConnectionFactory().getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, usuario.getUsername());
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    return new User(extractRowFromResultSet(resultSet, NAMETABLE, con));
                }
            }
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

}
