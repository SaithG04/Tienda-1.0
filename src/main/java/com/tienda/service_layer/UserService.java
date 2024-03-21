package com.tienda.service_layer;

import com.tienda.presentation_layer.UsersFrame;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz que define los servicios relacionados con la gestión de usuarios.
 */
public interface UserService extends FrameService<UsersFrame> {

    /**
     * Método para registrar un nuevo usuario.
     */
    void RegistrarUsuario();

    /**
     * Método para cargar la información de los usuarios en un modelo de tabla.
     *
     * @return DefaultTableModel con la información de los usuarios.
     */
    DefaultTableModel CargarUsuarios();
}
