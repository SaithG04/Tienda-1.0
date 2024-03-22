package com.tienda.data_access_layer;

import java.sql.*;
import java.util.*;

/**
 * Interfaz CRUD para operaciones básicas de acceso a datos.
 *
 * @param <T> Tipo de objeto Entity
 */
public interface CRUD<T> {

    T getById(int id) throws SQLException, ClassNotFoundException;


    void registrar(T entity) throws ClassNotFoundException, SQLException;

    List<T> listar() throws ClassNotFoundException, SQLException;


    void actualizar(T entity) throws ClassNotFoundException, SQLException;


    void eliminar(int id) throws ClassNotFoundException, SQLException;
}
