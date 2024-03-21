package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.entity.User;
import java.io.Serializable;

import java.sql.*;
import java.util.function.Function;

/**
 * Implementación del DAO de usuario para acceder a la base de datos.
 */
public class UserDAOImpl extends CRUD<UserDTO, User> implements Serializable {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombreCompleto = resultSet.getString("nombre_completo");
        String username = resultSet.getString("username");
        byte[] dbHashedPassword = resultSet.getBytes("hashed_password");
        byte[] dbSalt = resultSet.getBytes("salt");
        return new User(id, nombreCompleto, username, dbHashedPassword, dbSalt);
    }

    public UserDTO getUserByUsername(UserDTO dto) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username COLLATE utf8_bin = ?";
        connectionFactory = new MySqlConnectionFactory();
        objConnection = connectionFactory.getConnection();
        try (PreparedStatement statement = objConnection.prepareStatement(query)) {
            statement.setString(1, dto.getUser());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Obtener los datos del usuario desde el ResultSet
                    User usuarioBD = extractFromResultSet(resultSet);
                    return new UserDTO(usuarioBD);
                }
            }
        } finally {
            connectionFactory.closeConnection(); // Cerrar la conexión después de utilizarla
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

//    public Function<ResultSet, User> getExtractFunctionRow() throws SQLException, ClassNotFoundException {
//        String[] tableColumns = getTableColumns("users");
//        return (ResultSet resultSet) -> {
//            try {
//                Object[] row = new Object[tableColumns.length];
//                for (int i = 0; i < tableColumns.length; i++) {
//                    row[i] = resultSet.getObject(i + 1);
//                }
//                return new User((int) row[0], row[1].toString(), row[2].toString(), (byte[]) row[3], (byte[]) row[4]);
//            } catch (SQLException e) {
//                alerta.manejarErrorConexion(UserDAOImpl.this.getClass(), e);
//                return null;
//            }
//        };
//    }

//    public Function<User, UserDTO> getMapFunctionRow() {
//        return user -> new UserDTO(user);
//    }

}
