package Capa_de_acceso_a_datos.DAOImplements;

import Capa_de_acceso_a_datos.Modelo_de_datos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Capa_de_acceso_a_datos.DAO.UsuarioDAO;
import java.sql.Statement;

/**
 * Data Access Object (DAO) implementation for User-related operations. This
 * class handles database operations related to user data.
 *
 * @author isai_
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    // Class variable declarations
    public static PreparedStatement PS;
    public static Statement ST;
    public static ResultSet RS;
    public static Connection CON;

    /**
     * Constructor that receives a database connection.
     *
     * @param CON The database connection to use.
     * @throws SQLException If a SQL-related error occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public UsuarioDAOImpl(Connection CON) throws SQLException, ClassNotFoundException {
        this.CON = CON;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return A User object if found, or null if not found.
     * @throws SQLException If a SQL-related error occurs.
     */
    @Override
    public Usuario ObtenerPorUsername(String username) throws SQLException {

        // Prepare SQL statement to select user by username
        PS = CON.prepareStatement("SELECT username, hashed_password, salt FROM usuarios WHERE username COLLATE utf8_bin = ?");
        PS.setString(1, username);

        // Execute the query
        RS = PS.executeQuery();
        Usuario log = null;
        if (RS.next()) {
            // Retrieve the hashed password and salt from the database
            byte[] dbHashedPassword = RS.getBytes("hashed_password");
            byte[] dbSalt = RS.getBytes("salt");
            log = new Usuario(username, dbHashedPassword, dbSalt);
        }
        return log;
    }

    /**
     * Creates a new user and grants privileges in the database.
     *
     * @param usuario The User object representing the new user.
     * @param contrasena The user's password.
     * @throws SQLException If a SQL-related error occurs.
     */
    @Override
    public void RegistrarUsuario(Usuario usuario, String contrasena) throws SQLException {

        // Create a user in the database and grant privileges
//        ST = CON.createStatement();
//        ST.execute("CREATE USER '" + usuario.getUsername() + "'@'localhost' IDENTIFIED BY '" + contrasena + "';");
//        ST.execute("GRANT ALL PRIVILEGES ON * . * TO '" + usuario.getUsername() + "'@'localhost' WITH GRANT OPTION");
//        ST.execute("FLUSH PRIVILEGES");
        /*
            % - Remote Connection.
            localhost - Local Connection.
         */
    }

    @Override
    public void GuardarUsuario(Usuario usuario) throws SQLException {

        // Prepare SQL statement to insert user data into the database
        PS = CON.prepareStatement("INSERT INTO usuarios (username, hashed_password, salt) VALUES (?, ?, ?)");
        PS.setString(1, usuario.getUsername());
        PS.setBytes(2, usuario.getHashed_password());
        PS.setBytes(3, usuario.getSalt());
        PS.execute();
    }

    /**
     * Deletes a user from the database.
     *
     * @param usuario The User object representing the user to delete.
     * @throws SQLException If a SQL-related error occurs.
     */
    @Override
    public void EliminarUsuario(Usuario usuario) throws SQLException {

    }

}
