package com.tienda.data_access_layer;

import com.tienda.utilities.DataAccessUtilities;
import java.sql.*;

/**
 * Esta clase implementa la interfaz ConnectionFactory y proporciona métodos
 * para obtener y cerrar conexiones MySQL.
 * 
 * @author isai_
 */
public class MySqlConnectionFactory implements ConnectionFactory {

    private static MySqlConnectionFactory instance;

    private final String driver = "com.mysql.cj.jdbc.Driver"; // Driver JDBC para MySQL
    private final String type = "jdbc:mysql://"; // Protocolo JDBC para MySQL
    private final String[] properties;

    private static String userBD;
    private static String passwordBD;
    private Connection objConnection;

    /**
     * Constructor privado para el patrón Singleton. Obtiene las propiedades de
     * la base de datos y establece las credenciales de usuario.
     */
    private MySqlConnectionFactory() {
        properties = new DataAccessUtilities().getProperties();
        userBD = properties[3];
        passwordBD = properties[4];
    }

    /**
     * Constructor privado adicional para el patrón Singleton, que permite
     * proporcionar credenciales de usuario personalizadas.
     *
     * @param userBD Nombre de usuario de la base de datos.
     * @param passwordBD Contraseña del usuario de la base de datos.
     */
    private MySqlConnectionFactory(String userBD, String passwordBD) {
        properties = new DataAccessUtilities().getProperties();
        MySqlConnectionFactory.userBD = userBD;
        MySqlConnectionFactory.passwordBD = passwordBD;
    }

    /**
     * Método estático que proporciona una instancia única de
     * MySqlConnectionFactory utilizando el patrón Singleton.
     *
     * @return La instancia única de MySqlConnectionFactory.
     */
    public static MySqlConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (MySqlConnectionFactory.class) {
                if (instance == null) {
                    instance = new MySqlConnectionFactory();
                }

            }
        }
        return instance;
    }

    /**
     * Método estático que proporciona una instancia única de
     * MySqlConnectionFactory utilizando el patrón Singleton y permite
     * personalizar las credenciales de usuario.
     *
     * @param userBD Nombre de usuario de la base de datos.
     * @param passBD Contraseña del usuario de la base de datos.
     * @return La instancia única de MySqlConnectionFactory.
     */
    public static MySqlConnectionFactory getInstance(String userBD, String passBD) {
        if (instance == null) {
            synchronized (instance.getClass()) {
                if (instance == null) {
                    instance = new MySqlConnectionFactory(userBD, passBD);
                }

            }
        }
        return instance;
    }

    /**
     * Actualiza las credenciales de usuario de la instancia existente. Este
     * método puede ser llamado después de haber obtenido la instancia para
     * actualizar las credenciales de usuario.
     *
     * @param userBD El nuevo nombre de usuario de la base de datos.
     * @param passwordBD La nueva contraseña del usuario de la base de datos.
     */
    public static void updateCredentials(String userBD, String passwordBD) {
        MySqlConnectionFactory.userBD = userBD;
        MySqlConnectionFactory.passwordBD = passwordBD;
    }

    /**
     * Obtiene una conexión a la base de datos MySQL utilizando las propiedades
     * de conexión especificadas en el archivo de configuración.
     *
     * @return Una conexión a la base de datos MySQL.
     * @throws ClassNotFoundException Si no se encuentra el driver JDBC.
     * @throws SQLException Si ocurre un error de SQL durante la conexión.
     */
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        objConnection = DriverManager.getConnection(type + properties[0] + ":" + properties[1] + "/" + properties[2], userBD, passwordBD);

        return objConnection;
    }

    /**
     * Cierra la conexión activa con la base de datos, si está abierta.
     *
     * @throws ClassNotFoundException Si no se encuentra el driver JDBC.
     * @throws SQLException Si ocurre un error de SQL durante el cierre de la
     * conexión.
     */
    @Override
    public void closeConnection() throws ClassNotFoundException, SQLException {
        if (objConnection != null) {
            if (!objConnection.isClosed()) {
                objConnection.close();
            }
        }
    }
}
