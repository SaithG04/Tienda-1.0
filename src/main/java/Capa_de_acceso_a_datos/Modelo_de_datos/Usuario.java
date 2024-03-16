package Capa_de_acceso_a_datos.Modelo_de_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Represents a user's login information including username, hashed password,
 * and salt.
 */
public class Usuario extends Conexion {

    // Class variables
    private String username;
    private byte[] hashed_password;
    private byte[] salt;

    /**
     * Constructor for creating a Login object.
     *
     * @param username The username of the user.
     * @param hashed_password The hashed password of the user.
     * @param salt The salt used in password hashing.
     */
    public Usuario(String username, byte[] hashed_password, byte[] salt) {
        this.username = username;
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    /**
     * Get the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the hashed password.
     *
     * @return The hashed password.
     */
    public byte[] getHashed_password() {
        return hashed_password;
    }

    /**
     * Set the hashed password.
     *
     * @param hashed_password The hashed password to set.
     */
    public void setHashed_password(byte[] hashed_password) {
        this.hashed_password = hashed_password;
    }

    /**
     * Get the salt used in password hashing.
     *
     * @return The salt.
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Set the salt used in password hashing.
     *
     * @param salt The salt to set.
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
