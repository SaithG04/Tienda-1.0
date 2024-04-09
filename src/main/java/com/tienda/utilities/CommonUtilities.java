package com.tienda.utilities;

import com.tienda.data_access_layer.MySqlConnectionFactory;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.*;
import javax.swing.*;

/**
 * Clase base para funcionalidades generales compartidas.
 */
public class CommonUtilities extends MySqlConnectionFactory {

    public static final Image IMG;
//    public static final Color COLORERROR = new Color(255, 153, 153);

    static {
        Image tempImg = null;
        try {
            tempImg = Toolkit.getDefaultToolkit().getImage(CommonUtilities.class.getResource("/images/logo.jpg"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        IMG = tempImg;
    }

    // Objeto para mostrar alertas
    public final AlertsClass alerta = AlertsClass.getAlert();

    /**
     * Método para obtener un icono de un archivo de imagen.
     *
     * @param path Ruta del archivo de imagen.
     * @param width Ancho deseado del icono.
     * @param height Altura deseada del icono.
     * @return Objeto Icon con la imagen escalada.
     */
    public static Icon icono(String path, int width, int height) {
        Image img = new ImageIcon(CommonUtilities.class.getResource(path))
                .getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        Icon ic = new ImageIcon(img);
        return ic;
    }

    /**
     * Método para validar y restringir la entrada de caracteres a letras.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     * @param max Número máximo de caracteres permitidos.
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
     * Método para validar y restringir la entrada de caracteres a números.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     * @param max Número máximo de caracteres permitidos.
     */
    public void validarSoloNumeros(KeyEvent evt, String txt, short max) {
        int key = evt.getKeyChar();
        boolean numbers = key >= 48 && key <= 57;
        if (!numbers || (txt.length() >= max)) {
            evt.consume();
        }
    }

    /**
     * Método para validar y restringir la entrada de caracteres a números
     * decimales.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
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
     * Método para limitar el número máximo de dígitos en un campo.
     *
     * @param evt Evento de tecla.
     * @param txt Texto actual en el campo.
     * @param max Número máximo de dígitos permitidos.
     */
    public void validarLength(KeyEvent evt, String txt, short max) {
        if (txt.length() == max) {
            evt.consume();
        }
    }

    /**
     * Convierte una matriz de bytes en una representación de cadena
     * hexadecimal.
     *
     * @param bytes La matriz de bytes que se va a convertir.
     * @return La representación de cadena hexadecimal de la matriz de bytes.
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
     * Hashea una contraseña utilizando el algoritmo SHA-256 con la salt
     * proporcionada.
     *
     * @param password La contraseña que se va a hashear.
     * @param salt El valor de salt para mejorar la seguridad.
     * @return La contraseña hasheada como una matriz de bytes.
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
     * Genera una salt aleatoria.
     *
     * @return La salt generada.
     */
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

}
