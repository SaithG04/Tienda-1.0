package Capa_de_logica_de_negocio;

import Capa_de_acceso_a_datos.Modelo_de_datos.Conexion;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 * Base class for shared general functionalities.
 */
public class CommonUtilities extends Conexion {

    // Constants for logo image and error color
    //public static final Image IMG = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("D:/Documentos/NetBeansProjects/ElAlgarrobo1.0/src/main/java/Capa_de_recursos/Imagenes/logo.jpg"));
    //public static final Image IMG = Toolkit.getDefaultToolkit().getImage(CommonUtilities.class.getResource("/Capa_de_recursos/Imagenes/logo.jpg"));
    public static final Image IMG;

    static {
        Image tempImg = null;
        try {
            tempImg = Toolkit.getDefaultToolkit().getImage(CommonUtilities.class.getResource("/Capa_de_recursos/Imagenes/logo.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        IMG = tempImg;
    }

    public static final Color COLORERROR = new Color(255, 153, 153);

    // Object for displaying alerts
    public final Alerts oA = new Alerts();

    /**
     * Method to get an Icon from an image file.
     *
     * @param path Image file path.
     * @param width Desired width of the icon.
     * @param height Desired height of the icon.
     * @return Icon object with the scaled image.
     */
    public static Icon icono(String path, int width, int height) {
//        Image img = new ImageIcon(CommonUtilities.class.getResource(path))
//                .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
//        Icon ic = new ImageIcon(img);
//        return ic;
        return null;
    }

    /**
     * Method to validate and restrict character input to letters.
     *
     * @param evt Key event.
     * @param txt Current text in the field.
     * @param max Maximum number of allowed characters.
     */
    public void ValidarSoloLetras(KeyEvent evt, String txt, short max) {
        int key = evt.getKeyChar();
        boolean uppercase = key >= 65 && key <= 90;
        boolean lowercase = key >= 97 && key <= 122;
        boolean space = key == 32;
        if (!(uppercase || lowercase || space) || txt.length() >= max) {
            evt.consume();
        }
    }

    /**
     * Method to validate and restrict character input to numbers.
     *
     * @param evt Key event.
     * @param txt Current text in the field.
     * @param max Maximum number of allowed characters.
     */
    public void validarSoloNumeros(KeyEvent evt, String txt, short max) {
        int key = evt.getKeyChar();
        boolean numbers = key >= 48 && key <= 57;
        if (!numbers || (txt.length() >= max)) {
            evt.consume();
        }
    }

    /**
     * Method to validate and restrict character input to decimal numbers.
     *
     * @param evt Key event.
     * @param txt Current text in the field.
     */
    public static void validarValorDecimal(KeyEvent evt, String txt) {
        char key = evt.getKeyChar();
        boolean numbers = key >= '0' && key <= '9';
        if (key == '.') {
            if (txt.length() == 0) {
                evt.consume();
            } else {
                int counter = 0;
                for (int i = 0; i < txt.length(); i++) {
                    if (txt.charAt(i) == '.') {
                        counter++;
                    }
                }
                if (counter > 0) {
                    evt.consume();
                }
            }
        } else {
            if (!numbers || (txt.length() >= 11)) {
                evt.consume();
            }
        }
    }

    /**
     * Method to limit the maximum number of digits in a field.
     *
     * @param evt Key event.
     * @param txt Current text in the field.
     * @param max Maximum number of digits allowed.
     */
    public void validarLength(KeyEvent evt, String txt, short max) {
        if (txt.length() == max) {
            evt.consume();
        }
    }

    /**
     * Converts a byte array to a hexadecimal string representation.
     *
     * @param bytes The byte array to be converted.
     * @return The hexadecimal string representation of the byte array.
     */
    public String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Hashes a password using SHA-256 algorithm with the provided salt.
     *
     * @param password The password to be hashed.
     * @param salt The salt value to enhance security.
     * @return The hashed password as a byte array.
     */
    public byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return hashedPassword;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Closes the database connection, PreparedStatement, ResultSet, and
     * Statement objects.
     *
     * @param conex The database connection to close.
     * @param ps The PreparedStatement to close.
     * @param rs The ResultSet to close.
     * @param st The Statement to close.
     * @throws SQLException If a database access error occurs while closing
     * objects.
     */
    public void Finalize(Connection conex, PreparedStatement ps, ResultSet rs, Statement st) throws SQLException {
        if (conex != null) {
            conex.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
    }

}
