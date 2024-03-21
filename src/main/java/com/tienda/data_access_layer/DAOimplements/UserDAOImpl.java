package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import java.io.Serializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de usuario para acceder a la base de datos.
 */
public class UserDAOImpl extends MySqlConnectionFactory implements UserDAO, Serializable {

    private final UserDTO dto;

    /**
     * Constructor que inicializa el DAO con un DTO de usuario.
     *
     * @param dto El DTO de usuario utilizado para la comunicación con el DAO.
     */
    public UserDAOImpl(UserDTO dto) {
        this.dto = dto;
    }

    /**
     * Obtiene un usuario de la base de datos por su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    @Override
    public UserDTO GetById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE id = ?";
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Obtener los datos del usuario desde la base de datos
                    User usuarioBD = extractUserFromResultSet(resultSet);
                    return new UserDTO(usuarioBD);
                }
            }
        } finally {
            connectionFactory.closeConnection(); // Cerrar la conexión después de utilizarla
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

    /**
     * Obtiene un usuario de la base de datos por su nombre de usuario.
     *
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    @Override
    public UserDTO getUserByUsername() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username COLLATE utf8_bin = ?";
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            statement.setString(1, dto.getUser());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Obtener los datos del usuario desde el ResultSet
                    User usuarioBD = extractUserFromResultSet(resultSet);
                    return new UserDTO(usuarioBD);
                }
            }
        } finally {
            connectionFactory.closeConnection(); // Cerrar la conexión después de utilizarla
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param userDTO El usuario a guardar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    @Override
    public void Registrar(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User(userDTO);
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        String query = "INSERT INTO users (nombre_completo, username, hashed_password, salt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            // Establecer los parámetros en la consulta preparada
            statement.setString(1, user.getNombreCompleto());
            statement.setString(2, user.getUsername());
            statement.setBytes(3, user.getHashed_password());
            statement.setBytes(4, user.getSalt());
            // Ejecutar la consulta de inserción
            statement.executeUpdate();
        } finally {
            connectionFactory.closeConnection(); // Cerrar la conexión después de utilizarla
        }
    }

    /**
     * Lista todos los usuarios de la base de datos.
     *
     * @return La lista de usuarios.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    @Override
    public List<UserDTO> Listar() throws ClassNotFoundException, SQLException {
        List<UserDTO> listaUser = new ArrayList<>();
        String query = "SELECT * FROM users";
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    // Obtener los datos del usuario desde el ResultSet
                    User usuarioBD = extractUserFromResultSet(result);
                    listaUser.add(new UserDTO(usuarioBD));
                }
            }
        } finally {
            connectionFactory.closeConnection(); // Cerrar la conexión después de utilizarla
        }
        return listaUser;
    }

    /**
     * Extrae los datos de un usuario a partir de un ResultSet.
     *
     * @param resultSet El ResultSet que contiene los datos del usuario.
     * @return El objeto User creado a partir de los datos del ResultSet.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet.
     */
    @Override
    public User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombreCompleto = resultSet.getString("nombre_completo");
        String username = resultSet.getString("username");
        byte[] dbHashedPassword = resultSet.getBytes("hashed_password");
        byte[] dbSalt = resultSet.getBytes("salt");
        return new User(id, nombreCompleto, username, dbHashedPassword, dbSalt);
    }
}
