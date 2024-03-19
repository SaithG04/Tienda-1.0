package com.tienda.entity;

/**
 * La clase User representa a un usuario en el sistema.
 */
public class User {

    private int id; // Identificador único del usuario
    private String username; // Nombre de usuario
    private byte[] hashed_password; // Contraseña del usuario en formato hash
    private byte[] salt; // Valor de sal utilizado en la encriptación de la contraseña

    /**
     * Constructor para inicializar un objeto User con su ID, nombre de usuario,
     * contraseña hasheada y sal.
     *
     * @param id Identificador único del usuario.
     * @param username Nombre de usuario.
     * @param hashed_password Contraseña del usuario en formato hash.
     * @param salt Valor de sal utilizado en la encriptación de la contraseña.
     */
    public User(int id, String username, byte[] hashed_password, byte[] salt) {
        this.id = id;
        this.username = username;
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id ID del usuario a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username Nombre de usuario a establecer.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario en formato hash.
     *
     * @return Contraseña del usuario en formato hash.
     */
    public byte[] getHashed_password() {
        return hashed_password;
    }

    /**
     * Establece la contraseña del usuario en formato hash.
     *
     * @param hashed_password Contraseña del usuario en formato hash a
     * establecer.
     */
    public void setHashed_password(byte[] hashed_password) {
        this.hashed_password = hashed_password;
    }

    /**
     * Obtiene el valor de sal utilizado en la encriptación de la contraseña.
     *
     * @return Valor de sal utilizado en la encriptación de la contraseña.
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Establece el valor de sal utilizado en la encriptación de la contraseña.
     *
     * @param salt Valor de sal a establecer.
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
