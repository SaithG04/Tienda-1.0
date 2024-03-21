package com.tienda.data_transfer_layer;

import com.tienda.entity.User;

/**
 * La clase UserDTO representa un objeto de transferencia de datos para la
 * información del usuario.
 */
public class UserDTO{

    private String user; // Nombre de usuario
    private String password; // Contraseña del usuario
    private String nombreCompleto; // Nombre completo del usuario
    private User usuario; // Objeto User asociado a este DTO

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
     * Constructor para inicializar un objeto UserDTO con el nombre de usuario,
     * la contraseña y el nombre completo.
     *
     * @param user Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param nombreCompleto Nombre completo del usuario.
     */
    public UserDTO(String user, String password, String nombreCompleto) {
        this.user = user;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.usuario = new User(this);
    }

    /**
     * Constructor para inicializar un objeto UserDTO a partir de un objeto
     * User.
     *
     * @param usuario Objeto User del cual se inicializa el DTO.
     */
    public UserDTO(User usuario) {
        this.user = usuario.getUsername();
        this.nombreCompleto = usuario.getNombreCompleto();
        this.usuario = usuario;
    }

    /**
     * Constructor por defecto.
     */
    public UserDTO() {
        System.out.println("no es nulo");
        usuario = new User();
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

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre completo del usuario.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombreCompleto Nombre completo del usuario a establecer.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el objeto User asociado a este DTO.
     *
     * @return Objeto User asociado a este DTO.
     */
    public User getUsuario() {
        return usuario;
    }

    /**
     * Establece el objeto User asociado a este DTO.
     *
     * @param usuario Objeto User a establecer.
     */
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

}
