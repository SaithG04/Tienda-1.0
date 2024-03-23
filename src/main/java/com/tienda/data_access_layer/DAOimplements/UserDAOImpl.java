package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.User;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

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
    public void registrar() throws ClassNotFoundException, SQLException {
        registrarGeneric(NAMETABLE, usuario);
    }

    @Override
    public List<User> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    @Override
    public void actualizar() throws ClassNotFoundException, SQLException {
        actualizarGeneric(NAMETABLE, usuario.getId(), usuario);
    }

    @Override
    public void eliminar(int id) throws ClassNotFoundException, SQLException {
        eliminarGeneric(NAMETABLE, id);
    }

    @Override
    public User getUserByUsername() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username COLLATE utf8_bin = ?";
        try (Connection con = new MySqlConnectionFactory().getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, usuario.getUsername());
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    return (User) getRowGeneric(NAMETABLE, con).apply(resultSet);
                }
            }
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

}
