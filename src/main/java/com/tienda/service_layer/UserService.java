package com.tienda.service_layer;

import com.tienda.presentation_layer.UsersFrame;
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
     * Método para registrar un nuevo usuario.
     */
    void registrarUsuario();

    /**
     * Método para actualizar un usuario existente.
     */
    void actualizarUsuario();

    /**
     * Método para eliminar un usuario.
     */
    void eliminarUsuario();

    /**
     * Método para autocompletar los campos de texto con la información del
     * usuario seleccionado en la tabla.
     */
    void autocompletarCampos();

    /**
     * Método para limpiar los campos de texto y habilitar el botón de
     * registrar.
     */
    void limpiarCampos();
}
