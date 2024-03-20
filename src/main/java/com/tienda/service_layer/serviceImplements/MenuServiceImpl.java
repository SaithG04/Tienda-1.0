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

    private static volatile MenuServiceImpl instance;

    private final MenuPrincipalFrame mpFrame;
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnUsuarios;

    /**
     * Constructor para inicializar el servicio de menú.
     */
    private MenuServiceImpl() {
        // Crear una instancia del formulario de menú principal (MenuPrincipalFrame)
        mpFrame = MenuPrincipalFrame.getInstance();
        lblTitle = mpFrame.getLblTitle();
        btnCerrarSesion = mpFrame.getBtnCerrarSesion();
        btnUsuarios = mpFrame.getBtnUsuarios();
        loadFrame();
    }

    public static MenuServiceImpl getInstance() {
        if (instance == null) {
            synchronized (MenuServiceImpl.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new MenuServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public MenuPrincipalFrame reloadFrame() {
        mpFrame.setLocationRelativeTo(null);
        MouseListeners(); // Agregar MouseListeners a los botones
        close(); // Cerrar la aplicación si se intenta cerrar el formulario
        lblTitle.setText("Has iniciado sesión como: " + LoginServiceImpl.userLogued.getUsername().toUpperCase()); // Mostrar el nombre de usuario en el título del formulario
        return mpFrame;
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

                if (alerta.confirmacion("¿Cerrar sesión?") == 0) {
                    mpFrame.setVisible(false);
                    LoginServiceImpl.getInstance().getLoginFrame().setVisible(true);
                    LoginServiceImpl.getInstance().getLoginFrame().requestFocus();
                }
            }
        });

        // Agregar MouseListeners al botón de usuarios
        btnUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                mpFrame.setVisible(false);
                UserServiceImpl.getInstance().getUsersFrame().setVisible(true);
            }
        });
    }

}
