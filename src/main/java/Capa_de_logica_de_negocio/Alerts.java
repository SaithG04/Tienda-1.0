package Capa_de_logica_de_negocio;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class Alerts {

    private final String b[] = {"Aceptar"};
    private final String b2[] = {"Aceptar", "Cancelar"};
    private static final String ERRORCONECTION
            = "Communications link failure\n"
            + "\nThe last packet sent successfully to the server was 0 milliseconds ago. "
            + "The driver has not received any packets from the server."; //Mensaje por defecto de error de conexion

    public void mostrarError(Class<?> clase, String mensaje, Exception ex) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, mensaje, "Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/Resources/den.png", 30, 30), b, b[0]);
        if (ex != null) {
            Logger.getLogger(clase.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void manejarErrorConexion(Class<?> clase, Exception ex) {
        String error = ex.getMessage();
        if (error.equals(ERRORCONECTION)) {
            mostrarError(clase, "Usted no se encuentra conectado a la base de datos.", ex);
            System.exit(0);
        }else if(ex instanceof SQLException){
            mostrarError(clase, "Error de la base de datos. Contacte al administrador.", ex);
        } else {
            mostrarError(clase, "Error desconocido.", ex);
        }
    }

    public void aviso(String mensaje) {
        JOptionPane.showOptionDialog(null, mensaje, " Aviso", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/Resources/profesionales.png", 30, 30), b, b[0]);
    }

    public void info(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, " Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void faltanDatos() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, "Faltan datos.", " Advertencia", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/Resources/den.png", 30, 30), b, b[0]);
    }

    public int confirmación(String txt) {
        Toolkit.getDefaultToolkit().beep();
        return JOptionPane.showOptionDialog(null, txt, " Confirmar", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/Resources/den.png", 30, 30), b2, b2[1]);
    }

    public String entrada(String mensaje, String titulo) {
        // Crear un panel personalizado con un campo de texto y un mensaje
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensaje));
        JTextField textField = new JTextField(10);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                CommonUtilities.validarValorDecimal(evt, textField.getText());
            }
        });

        panel.add(textField);
        String texto = "";

        // Mostrar el cuadro de diálogo de entrada con el panel personalizado
        int option = JOptionPane.showOptionDialog(null, panel, titulo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        // Obtener el valor ingresado por el usuario
        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        }
        return texto;
    }

}
