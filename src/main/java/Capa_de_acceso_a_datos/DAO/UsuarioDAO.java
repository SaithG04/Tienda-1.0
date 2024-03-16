package Capa_de_acceso_a_datos.DAO;

import Capa_de_acceso_a_datos.Modelo_de_datos.Usuario;
import java.sql.SQLException;

/**
 * Interface for Data Access Object (DAO) responsible for handling login
 * operations.
 */
public interface UsuarioDAO {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The user object if found, or null if not found.
     * @throws SQLException If a database access error occurs.
     */
    Usuario ObtenerPorUsername(String username) throws SQLException;

    /**
     * Creates a new user with the provided information.
     *
     * @param usuario The user object to create.
     * @param contrasena The user's password.
     * @throws SQLException If a database access error occurs.
     */
    void RegistrarUsuario(Usuario usuario, String contrasena) throws SQLException;
    void GuardarUsuario(Usuario usuario) throws SQLException;

    /**
     * Deletes a user from the database.
     *
     * @param usuario The user object to delete.
     * @throws SQLException If a database access error occurs.
     */
    void EliminarUsuario(Usuario usuario) throws SQLException;
}
