package com.tienda.data_access_layer;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz CRUD para operaciones básicas de acceso a datos.
 *
 * @param <T> Tipo de objeto DTO
 */
public interface CRUD<T> {

    /**
     * Obtiene un objeto por su ID.
     *
     * @param id ID del objeto a buscar.
     * @return El objeto encontrado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    T GetById(int id) throws SQLException, ClassNotFoundException;

    /**
     * Registra un nuevo objeto en la base de datos.
     *
     * @param t Objeto a registrar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     */
    void Registrar(T t) throws SQLException, ClassNotFoundException;

    /**
     * Obtiene una lista de todos los objetos.
     *
     * @return Lista de objetos.
     * @throws ClassNotFoundException Si no se encuentra la clase del
     * controlador de la base de datos.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    List<T> Listar() throws ClassNotFoundException, SQLException;
}
