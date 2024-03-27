package com.tienda.utilities;

import com.tienda.presentation_layer.*;
import com.tienda.service_layer.serviceImplements.LoginServiceImpl;
import com.tienda.service_layer.serviceImplements.ProveedorServiceImpl;
import com.tienda.service_layer.serviceImplements.UserServiceImpl;
import java.awt.*;
import java.awt.event.*;
import java.security.*;
import javax.swing.*;

/**
 * Clase base para funcionalidades generales compartidas.
 *
 * @author isai_
 */
public class ServiceUtilities {

    public static final Image IMG;
    public final Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
    public final Cursor textCursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
    public final Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    public final Cursor defaultCursor = Cursor.getDefaultCursor();

    static {
        Image tempImg = null;
        try {
            tempImg = Toolkit.getDefaultToolkit().getImage(ServiceUtilities.class.getResource("/images/logo.jpg"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        IMG = tempImg;
    }

    // Objeto para mostrar alertas
    public static final AlertsClass alerta = AlertsClass.getAlert();

    /**
     * Método para obtener un icono de un archivo de imagen.
     *
     * @param path Ruta del archivo de imagen.
     * @param width Ancho deseado del icono.
     * @param height Altura deseada del icono.
     * @return Objeto Icon con la imagen escalada.
     */
    public static Icon icono(String path, int width, int height) {
        Image img = new ImageIcon(ServiceUtilities.class.getResource(path))
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

    /**
     * Método para cerrar una ventana y confirmar la salida de la aplicación al
     * hacer clic en el botón de cerrar.
     *
     * @param t JFrame que se va a cerrar.
     */
    public void Close(JFrame t) {
        // Eliminar cualquier WindowListener existente
        for (WindowListener wl : t.getWindowListeners()) {
            t.removeWindowListener(wl);
        }

        // Agregar un nuevo WindowListener para confirmar la salida de la aplicación
        t.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                t.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                // Mostrar un mensaje de confirmación antes de salir de la aplicación
                if (alerta.confirmacion("¿Salir de la aplicación?") == 0) {
                    if (LoginServiceImpl.userLogued.getStatus().equals("logged in")) {
                        alerta.advertencia("Cierre sesión primero.");
                    } else {
                        // Salir de la aplicación si se confirma
                        System.exit(0);
                    }
                }
            }
        });
    }

    /**
     * Método para verificar si una cadena de texto contiene al menos una letra
     * mayúscula.
     *
     * @param texto Cadena de texto a verificar.
     * @return true si la cadena contiene al menos una letra mayúscula, false en
     * caso contrario.
     */
    public boolean contieneMayuscula(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isUpperCase(texto.charAt(i))) {
                return true; // Si encuentra al menos una mayúscula, retorna verdadero
            }
        }
        return false; // Si no encuentra ninguna mayúscula, retorna falso
    }

    /**
     * Método para verificar si una cadena de texto contiene al menos un dígito
     * numérico.
     *
     * @param texto Cadena de texto a verificar.
     * @return true si la cadena contiene al menos un dígito numérico, false en
     * caso contrario.
     */
    public boolean contieneNumero(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isDigit(texto.charAt(i))) {
                return true; // Si encuentra al menos un número, retorna verdadero
            }
        }
        return false; // Si no encuentra ningún número, retorna falso
    }

    /**
     * Método para verificar si una cadena de texto contiene al menos un
     * carácter especial.
     *
     * @param texto Cadena de texto a verificar.
     * @return true si la cadena contiene al menos un carácter especial, false
     * en caso contrario.
     */
    public boolean contieneSigno(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return true; // Si encuentra al menos un signo, retorna verdadero
            }
        }
        return false; // Si no encuentra ningún signo, retorna falso
    }

    /**
     * Método para verificar si una cadena de texto contiene al menos un espacio
     * en blanco.
     *
     * @param texto Cadena de texto a verificar.
     * @return true si la cadena contiene al menos un espacio en blanco, false
     * en caso contrario.
     */
    public boolean contieneEspacioBlanco(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isWhitespace(texto.charAt(i))) {
                return true; // Si encuentra al menos un espacio en blanco, retorna verdadero
            }
        }
        return false; // Si no encuentra ningún espacio en blanco, retorna falso
    }

    /**
     * Método para verificar si una cadena de texto contiene caracteres no
     * permitidos.
     *
     * @param texto Cadena de texto a verificar.
     * @return true si la cadena contiene caracteres no permitidos, false en
     * caso contrario.
     */
    public boolean contieneCaracteresNoPermitidos(String texto) {
        // Definir los caracteres no permitidos
        String caracteresNoPermitidos = "!@#$%^&*()+=-[]{},<>?/\\|";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            // Verificar si el carácter actual está en la lista de caracteres no permitidos
            if (caracteresNoPermitidos.indexOf(c) != -1) {
                return true; // Si encuentra al menos un carácter no permitido, retorna verdadero
            }
        }
        return false; // Si no encuentra ningún carácter no permitido, retorna falso
    }

    public void volverLogin(String tableName) {
        if (!LoginFrame.getInstance().isVisible()) {
            alerta.mostrarError(this.getClass(), "Usted ha sido desconectado.", null);
            switch (tableName) {
                case "users" -> {
                    UsersFrame.getInstance().dispose();
                    LoginFrame.getInstance().setVisible(true);
                    LoginFrame.getInstance().getTxtUsuario().requestFocus();
                }
                case "productos" -> {
                    ProductosFrame.getProcFrame().dispose();
                    LoginFrame.getInstance().setVisible(true);
                    LoginFrame.getInstance().getTxtUsuario().requestFocus();
                }
                case "proveedores" -> {
                    ProveedorFrame.getInstance().dispose();
                    LoginFrame.getInstance().setVisible(true);
                    LoginFrame.getInstance().getTxtUsuario().requestFocus();
                }
                default ->
                    throw new AssertionError();
            }
        }
    }

    public void errorSQL(Class clase, Exception ex) {
        if (ex.getMessage().contains("No suitable driver") || ex.getMessage().contains("Communications link failure")) {
            alerta.mostrarError(clase, "Se ha perdido la conexión a Internet.", ex);
            System.exit(0);
        } else {
            alerta.manejarErrorConexion(clase, ex);
        }
    }

    public void setCursoresGeneric(Component[] componentArray, Cursor cursor) {
        for (Component component : componentArray) {
            if (component instanceof JTextField || component instanceof JPasswordField) {
                component.setCursor(cursor.equals(defaultCursor) ? textCursor : cursor);
            } else if (component instanceof JButton) {
                component.setCursor(cursor.equals(defaultCursor) ? handCursor : cursor);
            } else {
                component.setCursor(cursor);
            }
        }
    }

    public <T> boolean bloquearMultipleModificacionGeneric(JTable tabla, T clase) {
        if (tabla.getSelectedRowCount() > 1) {
            switch (clase) {
                case UserServiceImpl userServiceImpl ->
                    userServiceImpl.limpiarCamposSinTabla();
                case ProveedorServiceImpl proveedorServiceImpl ->
                    proveedorServiceImpl.limpiarCamposSinTabla();
                default -> {
                }
            }
            return false;
        }
        return true;
    }

    public <T> void limpiarCamposGeneric(JTable tabla, T clase) {
        switch (clase) {
            case UserServiceImpl userServiceImpl ->
                userServiceImpl.limpiarCamposSinTabla();
            case ProveedorServiceImpl proveedorServiceImpl ->
                proveedorServiceImpl.limpiarCamposSinTabla();
            default -> {
            }
        }
        tabla.clearSelection();
    }

    public boolean camposVaciosGeneric(String[] arrayString) {
        for (String str : arrayString) {
            if (str.isEmpty()) {
                alerta.faltanDatos();
                return true; // Si encuentra un string vacío, muestra alerta y retorna verdadero
            }
        }
        return false; // Si no encuentra ningún string vacío, retorna falso
    }

}
