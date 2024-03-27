package com.tienda.service_layer;

import com.tienda.presentation_layer.UsersFrame;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz que define los servicios relacionados con la gestión de usuarios.
 *
 * @author isai_
 */
public interface UserService extends FrameService<UsersFrame> {

    /**
     * Método para cargar la información de los usuarios en un modelo de tabla.
     *
     * @return Un DefaultTableModel con la información de los usuarios.
     */
    DefaultTableModel cargarUsuarios();

    /**
     * Método para registrar un nuevo usuario en el sistema.
     */
    void registrarUsuario();

    /**
     * Método para actualizar la información de un usuario existente en el
     * sistema.
     */
    void actualizarUsuario();

    /**
     * Método para eliminar un usuario del sistema.
     */
    void eliminarUsuario();

    /**
     * Método para desconectar el servicio de usuario, cerrando cualquier
     * conexión abierta o liberando recursos.
     */
    void desconectar();
}
