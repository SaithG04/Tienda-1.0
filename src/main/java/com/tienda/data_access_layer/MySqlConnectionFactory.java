package com.tienda.data_access_layer;

import com.tienda.utilities.CommonUtilities;
import java.sql.*;

/**
 * The ConnectionClass provides methods for database connection and related
 * operations. It includes methods to connect to the database with and without a
 * username and password.
 */
public class MySqlConnectionFactory extends CommonUtilities implements ConnectionFactory {

    // Variables estáticas para el usuario y variables de instancia para la contraseña y los detalles de la base de datos
    private final String driver = "com.mysql.cj.jdbc.Driver"; // Driver JDBC para MySQL
    private final String type = "jdbc:mysql://"; // Protocolo JDBC para MySQL
    private final String host = "sql10.freesqldatabase.com"; // Dirección del host de la base de datos
    private final String port = "3306"; // Dirección del puerto de la base de datos
    private final String bdName = "sql10692438"; // Nombre de la base de datos

    private static String USERBD;
    private String passwordBD;
    private Connection objConnection;

    // Constructor por defecto, establece las credenciales por defecto
    public MySqlConnectionFactory() {
        USERBD = "sql10692438";
        passwordBD = "rKmVRFuRS4";
    }

    // Constructor que permite pasar las credenciales
    public MySqlConnectionFactory(String userBD, String passwordBD) {
        MySqlConnectionFactory.USERBD = userBD; // Uso de la referencia de clase para la variable estática userBD
        this.passwordBD = passwordBD; // Establecimiento de la contraseña proporcionada
    }

    /**
     * Establece la conexión con la base de datos.
     *
     * @return Objeto Connection para interactuar con la base de datos.
     * @throws ClassNotFoundException Si no se encuentra el driver de la base de
     * datos.
     * @throws SQLException Si ocurre un error al conectar a la base de datos.
     */
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // Cargar el driver de la base de datos
        Class.forName(driver);
        // Establecer la conexión utilizando los parámetros proporcionados
        objConnection = DriverManager.getConnection(type + host + ":" + port + "/" + bdName, USERBD, passwordBD);

        return objConnection;
    }

    @Override
    public void closeConnection() throws ClassNotFoundException, SQLException {
        if (objConnection != null) {
            if (!objConnection.isClosed()) {
                objConnection.close();
            }
        }
    }
//
//    public String getBdName() {
//        return bdName;
//    }

}
