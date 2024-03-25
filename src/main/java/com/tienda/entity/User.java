package com.tienda.entity;

import java.util.Arrays;

/**
 * La clase User representa a un usuario en el sistema.
 * 
 * @author isai_
 */
public class User {

    private int id; // Identificador único del usuario
    private String nombreCompleto;
    private String username; // Nombre de usuario
    private byte[] hashed_password; // Contraseña del usuario en formato hash
    private byte[] salt; // Valor de sal utilizado en la encriptación de la contraseña

    /**
     * Constructor para inicializar un objeto User con su ID, nombre de usuario,
     * contraseña hasheada y sal.
     *
     * @param id Identificador único del usuario.
     * @param nombreCompleto Nombre completo del usuario.
     * @param username Username del usuario.
     * @param hashed_password Contraseña del usuario en formato hash.
     * @param salt Valor de salt utilizado en la encriptación de la contraseña.
     */
    public User(int id, String nombreCompleto, String username, byte[] hashed_password, byte[] salt) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    /**
     * Constructor por defecto de la clase User.
     */
    public User() {
    }

    // Getters y setters para los campos de la clase
    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El nuevo identificador único del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombreCompleto El nuevo nombre completo del usuario.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username El nuevo nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario en formato hash.
     *
     * @return La contraseña del usuario en formato hash.
     */
    public byte[] getHashed_password() {
        return hashed_password;
    }

    /**
     * Establece la contraseña del usuario en formato hash.
     *
     * @param hashed_password La nueva contraseña del usuario en formato hash.
     */
    public void setHashed_password(byte[] hashed_password) {
        this.hashed_password = hashed_password;
    }

    /**
     * Obtiene el valor de salt utilizado en la encriptación de la contraseña.
     *
     * @return El valor de salt utilizado en la encriptación de la contraseña.
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Establece el valor de salt utilizado en la encriptación de la contraseña.
     *
     * @param salt El nuevo valor de salt utilizado en la encriptación de la
     * contraseña.
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /**
     * Devuelve una representación en forma de cadena de texto del objeto User.
     *
     * @return Una cadena de texto que representa el objeto User.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", nombreCompleto='" + nombreCompleto + '\''
                + ", username='" + username + '\''
                + ", hashed_password=" + Arrays.toString(hashed_password)
                + ", salt=" + Arrays.toString(salt)
                + '}';
    }
}
