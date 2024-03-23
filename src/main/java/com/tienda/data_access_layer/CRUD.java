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


    void registrar() throws ClassNotFoundException, SQLException;

    List<T> listar() throws ClassNotFoundException, SQLException;


    void actualizar() throws ClassNotFoundException, SQLException;


    void eliminar() throws ClassNotFoundException, SQLException;
}
