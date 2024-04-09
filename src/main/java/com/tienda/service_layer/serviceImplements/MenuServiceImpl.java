package com.tienda.service_layer.serviceImplements;

import com.tienda.utilities.CommonUtilities;
import com.tienda.presentation_layer.MenuPrincipalFrame;
import com.tienda.service_layer.MenuService;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * La clase MenuServiceImpl se encarga de gestionar el menú principal de la
 * aplicación.
 */
public class MenuServiceImpl extends CommonUtilities implements MenuService, ActionListener {

    private final MenuPrincipalFrame mpFrame;
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnUsuarios;

    /**
     * Constructor para inicializar el servicio de menú.
     */
    public MenuServiceImpl() {
        // Crear una instancia del formulario de menú principal (MenuPrincipalFrame)
        mpFrame = MenuPrincipalFrame.getInstance();
        lblTitle = mpFrame.getLblTitle();
        btnCerrarSesion = mpFrame.getBtnCerrarSesion();
        btnUsuarios = mpFrame.getBtnUsuarios();
    }

    /**
     * Método para cerrar la aplicación.
     */
    @Override
    public final void close() {
        // Agregar un WindowListener para confirmar la salida de la aplicación
            if (alerta.confirmacion("¿Salir de la aplicación?") == 0)
            mpFrame.dispose();
            new LoginServiceImpl().loadFrame();
    }

    /**
     * Manejar eventos del mouse en los botones.
     */
    public final void MouseListeners() {
        // Agregar MouseListeners al botón de cerrar sesión
        btnCerrarSesion.addActionListener(this);
        // Agregar MouseListeners al botón de usuarios
        btnUsuarios.addActionListener(this);
    }

    /**
     * Cargar el formulario del menú principal.
     */
    @Override
    public void loadFrame() {
        // Configurar la ubicación y visibilidad del formulario de menú principal
        mpFrame.setVisible(true);
        mpFrame.setLocationRelativeTo(null);
        MouseListeners(); // Agregar MouseListeners a los botones
        lblTitle.setText("Bienvenido! " + LoginServiceImpl.userLogued.getUsername().toUpperCase()); // Mostrar el nombre de usuario en el título del formulario
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCerrarSesion) {
            close();
        } else if (e.getSource() == btnUsuarios) {
            mpFrame.dispose();
            new UserServiceImpl().loadFrame();
        }
    }
}
