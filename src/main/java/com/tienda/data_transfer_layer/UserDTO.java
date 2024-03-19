package com.tienda.data_transfer_layer;

/**
 * La clase UserDTO representa un objeto de transferencia de datos para la
 * información del usuario.
 */
public class UserDTO {

    private String user; // Nombre de usuario
    private String password; // Contraseña del usuario

    /**
     * Constructor para inicializar un objeto UserDTO con el nombre de usuario y
     * la contraseña.
     *
     * @param user Nombre de usuario.
     * @param password Contraseña del usuario.
     */
    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param user Nombre de usuario a establecer.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password Contraseña del usuario a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
