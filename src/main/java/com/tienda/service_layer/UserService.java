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

    /**
     * Método para autocompletar los campos de texto con la información del
     * usuario seleccionado en la tabla de usuarios.
     */
    void autocompletarCampos();

    /**
     * Método para limpiar los campos de texto y habilitar el botón de registrar
     * nuevo usuario.
     */
    void limpiarCampos();

    /**
     * Método para limpiar los campos de texto sin afectar la tabla de usuarios.
     */
    void limpiarCamposSinTabla();

    /**
     * Método para bloquear múltiples modificaciones simultáneas, garantizando
     * la integridad de los datos de usuario.
     *
     * @return true si se bloquearon las modificaciones, false si no se pudo
     * bloquear.
     */
    boolean bloquearMultipleModificacion();

    /**
     * Método para establecer el cursor del mouse en un componente dado.
     *
     * @param comp El componente en el que se establecerá el cursor.
     * @param cursor El cursor que se establecerá.
     */
    void setCursores(Component comp, Cursor cursor);

    /**
     * Método para verificar si los campos de nombre de usuario y nombre
     * completo están vacíos.
     *
     * @param nombreCompleto El nombre completo del usuario.
     * @param user El nombre de usuario.
     * @return true si algún campo está vacío, false si ambos campos están
     * llenos.
     */
    boolean camposVacios(String nombreCompleto, String user);

    /**
     * Método para verificar si los campos de nombre de usuario, nombre completo
     * y contraseña están vacíos.
     *
     * @param nombreCompleto El nombre completo del usuario.
     * @param user El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si algún campo está vacío, false si todos los campos están
     * llenos.
     */
    boolean camposVacios(String nombreCompleto, String user, String password);
}
