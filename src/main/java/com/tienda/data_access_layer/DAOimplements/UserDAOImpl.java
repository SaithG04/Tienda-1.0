package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.User;
import com.tienda.utilities.DataAccessUtilities;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Implementación concreta de la interfaz UserDAO que proporciona métodos para
 * realizar operaciones CRUD en la tabla de usuarios.
 * 
 * @author isai_
 */
public class UserDAOImpl extends DataAccessUtilities implements UserDAO, Serializable {

    private final User usuario;
    private static final String NAMETABLE = "users";

    /**
     * Constructor que inicializa la instancia del DAO con un objeto de usuario.
     *
     * @param usuario El usuario asociado al DAO.
     */
    public UserDAOImpl(User usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    @Override
    public User getById(int id) throws SQLException, ClassNotFoundException {
        return getByIdGeneric(id, NAMETABLE);
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public void registrar() throws ClassNotFoundException, SQLException {
        registrarGeneric(NAMETABLE, usuario);
    }

    /**
     * Lista todos los usuarios almacenados en la base de datos.
     *
     * @return Una lista de todos los usuarios almacenados.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public List<User> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    /**
     * Actualiza los datos de un usuario en la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public void actualizar() throws ClassNotFoundException, SQLException {
        actualizarGeneric(NAMETABLE, usuario.getId(), usuario);
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public void eliminar() throws ClassNotFoundException, SQLException {
        eliminarGeneric(NAMETABLE, usuario.getId());
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    @Override
    public User getUserByUsername() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username COLLATE utf8_bin = ?";
        try (Connection con = MySqlConnectionFactory.getInstance().getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
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
