package com.tienda.test;

import com.tienda.service_layer.LoginServiceImpl;
import com.tienda.utilities.ServiceUtilities;

/**
 * Clase principal que contiene el método main.
 */
public class Main {

    /**
     * Método principal que se ejecuta al iniciar el programa. Crea una
     * instancia de LoginServiceImpl y carga el formulario de inicio de sesión.
     * Solicita el enfoque en el campo de texto del usuario en el formulario de
     * inicio de sesión.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        ServiceUtilities.instanceOfServiceUtilities.configureTheme();
        LoginServiceImpl.getInstance().loadPanel();
        ServiceUtilities.instanceOfFrame.setVisible(true);
    }
}
