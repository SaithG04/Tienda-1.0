package com.tienda.entity;

import com.tienda.data_transfer_layer.UserDTO;
import com.tienda.utilities.CommonUtilities;

/**
 * La clase User representa a un usuario en el sistema.
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
     * Constructor para inicializar un objeto User a partir de un objeto
     * UserDTO.
     *
     * @param userDTO Objeto UserDTO que contiene los datos del usuario.
     */
    public User(UserDTO userDTO) {
        this.nombreCompleto = userDTO.getNombreCompleto();
        this.username = userDTO.getUser();
        CommonUtilities utilities = new CommonUtilities();
        this.salt = utilities.generateSalt();
        this.hashed_password = utilities.hashPassword(userDTO.getPassword(), this.salt);
    }

    public User() {
        
    }

    // Getters y setters para los campos de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(byte[] hashed_password) {
        this.hashed_password = hashed_password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nombreCompleto=" + nombreCompleto + ", username=" + username + ", hashed_password=" + hashed_password + ", salt=" + salt + '}';
    }
    
}
