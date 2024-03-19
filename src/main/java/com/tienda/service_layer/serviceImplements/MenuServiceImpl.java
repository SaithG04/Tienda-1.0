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
public class MenuServiceImpl extends CommonUtilities implements MenuService {

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
        mpFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (alerta.confirmacion("¿Salir de la aplicación?") == 0) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Manejar eventos del mouse en los botones.
     */
    public final void MouseListeners() {
        // Agregar MouseListeners al botón de cerrar sesión
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                System.out.println("caca");
                if (alerta.confirmacion("¿Cerrar sesión?") == 0) {
                    mpFrame.dispose();
                    new LoginServiceImpl().loadFrame();
                }
            }
        });

        // Agregar MouseListeners al botón de usuarios
        btnUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                mpFrame.dispose();
                new UserServiceImpl().loadFrame();
            }
        });
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
        close(); // Cerrar la aplicación si se intenta cerrar el formulario
        lblTitle.setText("Has iniciado sesión como: " + LoginServiceImpl.userLogued.getUsername().toUpperCase()); // Mostrar el nombre de usuario en el título del formulario
    }
}
