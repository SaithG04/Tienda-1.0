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

    /**
     * Obtiene un objeto por su ID.
     *
     * @param id El ID del objeto a buscar.
     * @return El objeto encontrado por su ID, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    T getById(int id) throws SQLException, ClassNotFoundException;

    /**
     * Registra un nuevo objeto en la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    void registrar() throws ClassNotFoundException, SQLException;

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
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    void actualizar() throws ClassNotFoundException, SQLException;

    /**
     * Elimina un objeto de la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    void eliminar() throws ClassNotFoundException, SQLException;
}
