package Capa_de_acceso_a_datos.Modelo_de_datos;

import Capa_de_logica_de_negocio.Alerts;
import java.sql.*;

/**
 * The cSQL class provides methods for database connection and related
 * operations. It includes methods to connect to the database with and without a
 * username and password.
 */
public class Conexion {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/";
    private final String BD = "bd_algarrobo";
    public Connection conex;
    protected final Alerts oA = new Alerts();

    /**
     * Connects to the database with the provided username and password for
     * permission validation.
     *
     * @param usuario The username for the database connection.
     * @param contraseña The password for the database connection.
     * @return The established database connection.
     * @throws ClassNotFoundException If the database driver class is not found.
     * @throws SQLException If a database access error occurs.
     */
    public Connection Conectar(String usuario, String contraseña) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER); // Load the database driver.
        conex = DriverManager.getConnection(HOST + BD, usuario, contraseña); // Establish the connection.
        return conex;
    }

    /**
     * Connects to the database using default credentials.
     *
     * @return The established database connection.
     * @throws ClassNotFoundException If the database driver class is not found.
     * @throws SQLException If a database access error occurs.
     */
    public Connection Conectar() throws ClassNotFoundException, SQLException {
        // Connection used only for user lookup.
        Class.forName(DRIVER); // Load the database driver.
        conex = DriverManager.getConnection(HOST + BD, "owner", ""); // Establish the connection.
        return conex;
    }
}
