package com.tienda.data_access_layer;

import java.sql.*;

/**
 * La interfaz ConnectionFactory proporciona métodos para la conexión a la base
 * de datos y operaciones relacionadas.
 * 
 * @author isai_
 */
public interface ConnectionFactory {

    /**
     * Establece una conexión con la base de datos.
     *
     * @return Objeto Connection para interactuar con la base de datos.
     * @throws ClassNotFoundException Si no se encuentra el driver de la base de
     * datos.
     * @throws SQLException Si ocurre un error al conectar a la base de datos.
     */
    Connection getConnection() throws ClassNotFoundException, SQLException;

    /**
     * Cierra la conexión a la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra el driver de la base de
     * datos.
     * @throws SQLException Si ocurre un error al cerrar la conexión a la base
     * de datos.
     */
    void closeConnection() throws ClassNotFoundException, SQLException;
}
