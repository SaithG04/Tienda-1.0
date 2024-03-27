package com.tienda.data_access_layer;

import java.sql.*;
import java.util.*;

/**
 * Interfaz genérica que define operaciones CRUD (Crear, Leer, Actualizar,
 * Eliminar) para manipular datos en la base de datos.
 *
 * @param <T> El tipo de objeto sobre el cual se realizarán las operaciones
 * CRUD.
 *
 * @author isai_
 */
public interface CRUD<T> {
    
    void setEntity(T t);

    /**
     * Obtiene un objeto por su ID.
     *
     * @return El objeto encontrado por su ID, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    T getById() throws SQLException, ClassNotFoundException;

    /**
     * Registra un nuevo objeto en la base de datos.
     *
     * @return 
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    boolean registrar() throws ClassNotFoundException, SQLException;

    /**
     * Lista todos los objetos almacenados en la base de datos.
     *
     * @return Una lista de todos los objetos almacenados.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    List<T> listar() throws ClassNotFoundException, SQLException;

    /**
     * Actualiza los datos de un objeto en la base de datos.
     *
     * @return 
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    boolean actualizar() throws ClassNotFoundException, SQLException;

    /**
     * Elimina un objeto de la base de datos.
     *
     * @return 
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    boolean eliminar() throws ClassNotFoundException, SQLException;
}
