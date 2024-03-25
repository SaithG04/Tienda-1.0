
import com.tienda.service_layer.serviceImplements.LoginServiceImpl;

/**
 * La clase Main es la clase principal de la aplicación que inicia la interfaz
 * de inicio de sesión.
 * 
 * @author isai_
 */
public class Main {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en
     * este caso).
     */
    public static void main(String[] args) {
        // Crear una instancia de LoginServiceImpl y cargar el formulario de inicio de sesión
        LoginServiceImpl.getInstance().getInstanceOfFrame().setVisible(true);
        LoginServiceImpl.getInstance().getInstanceOfFrame().getTxtUsuario().requestFocus();
    }

}
