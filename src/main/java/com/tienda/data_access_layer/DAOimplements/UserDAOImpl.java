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
     * Obtiene un usuario de la base de datos por su nombre de usuario.
     *
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    @Override
    public User getUserByUsername() throws SQLException, ClassNotFoundException {
        // Consulta SQL para obtener un usuario por nombre de usuario
        String query = "SELECT * FROM users WHERE username COLLATE utf8_bin = ?";
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            statement.setString(1, dto.getUser());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Si se encuentra un usuario, obtener los datos de la base de datos
                    byte[] dbHashedPassword = resultSet.getBytes("hashed_password");
                    byte[] dbSalt = resultSet.getBytes("salt");
                    return new User(0, dto.getUser(), dbHashedPassword, dbSalt);
                }
            }
        } finally {
            // Cerrar la conexión después de utilizarla
            connectionFactory.closeConnection();
        }
        // Devolver nulo si no se encuentra el usuario
        return null;
    }

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param user El usuario a guardar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    @Override
    public void saveUser(User user) throws SQLException, ClassNotFoundException {
        // Inicializar un objeto de la fábrica de conexiones MySQL
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        // Obtener una conexión a la base de datos
        objConnection = connectionFactory.getConnection();
        // Consulta SQL para insertar un nuevo usuario en la base de datos
        String query = "INSERT INTO users (username, hashed_password, salt) VALUES (?, ?, ?)";
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            // Establecer los parámetros en la consulta preparada
            statement.setString(1, user.getUsername());
            statement.setBytes(2, user.getHashed_password());
            statement.setBytes(3, user.getSalt());
            // Ejecutar la consulta de inserción
            statement.executeUpdate();
        } finally {
            // Cerrar la conexión después de utilizarla
            connectionFactory.closeConnection();
        }
    }
    
    @Override
    public List<User> listarUsuarios() throws ClassNotFoundException, SQLException {
        List<User> listaUser = new ArrayList<>();
        String query = "SELECT * FROM users";
        MySqlConnectionFactory connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        Object[] fila = new Object[4];

        PreparedStatement statement = objConnection.prepareStatement(query);

        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            fila[0] = result.getInt(1);
            fila[1] = result.getString(2);
            fila[2] = result.getBytes(3);
            fila[3] = result.getBytes(4);
            User usuario = new User((int) fila[0], fila[1].toString(), (byte[]) fila[2], (byte[]) fila[3]);
            listaUser.add(usuario);
        }
        return listaUser;
    }
}
