package com.tienda.service_layer.serviceImplements;

import com.tienda.utilities.CommonUtilities;
import com.tienda.presentation_layer.MenuPrincipalFrame;
import com.tienda.service_layer.MenuService;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * La clase MenuServiceImpl implementa la interfaz MenuService y gestiona las
 * operaciones del menú principal.
 */
public class MenuServiceImpl extends CommonUtilities implements MenuService, ActionListener {

    private static volatile MenuServiceImpl instanceOfMenuServiceImpl;

    // Instancia del formulario del menú principal
    private final MenuPrincipalFrame instanceOfMenuPrincipalFrame;

    // Componentes de la interfaz de usuario
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnUsuarios;

    /**
     * Constructor privado para el patrón Singleton.
     */
    private MenuServiceImpl() {
        // Obtener la instancia del formulario del menú principal
        instanceOfMenuPrincipalFrame = MenuPrincipalFrame.getInstance();

        // Obtener los componentes del formulario
        lblTitle = instanceOfMenuPrincipalFrame.getLblTitle();
        btnCerrarSesion = instanceOfMenuPrincipalFrame.getBtnCerrarSesion();
        btnUsuarios = instanceOfMenuPrincipalFrame.getBtnUsuarios();
    }

    /**
     * Método estático para obtener la instancia única de MenuServiceImpl.
     *
     * @return Instancia única de MenuServiceImpl.
     */
    public static MenuServiceImpl getInstance() {
        if (instanceOfMenuServiceImpl == null) {
            synchronized (MenuServiceImpl.class) {
                if (instanceOfMenuServiceImpl == null) {
                    instanceOfMenuServiceImpl = new MenuServiceImpl();
                }
            }
        }
        return instanceOfMenuServiceImpl;
    }

    /**
     * Retorna una instancia del formulario del menú principal.
     *
     * @return Instancia del formulario del menú principal.
     */
    @Override
    public MenuPrincipalFrame GetInstanceOfFrame() {
        // Ubicar el formulario en el centro de la pantalla
        instanceOfMenuPrincipalFrame.setLocationRelativeTo(null);

        // Cargar los ActionListeners para los botones
        CargarActionListeners();

        // Método para cerrar el formulario del menú principal
        Close(instanceOfMenuPrincipalFrame);

        // Actualizar el título del formulario con el nombre de usuario
        lblTitle.setText("Bienvenido Sr(a): " + LoginServiceImpl.userLogued.getNombreCompleto());

        return instanceOfMenuPrincipalFrame;
    }

    /**
     * Carga los ActionListeners para los botones del menú principal.
     */
    @Override
    public void CargarActionListeners() {
        // Eliminar los ActionListeners existentes para evitar duplicados
        QuitActionListeners();

        // Agregar ActionListeners para los botones
        btnCerrarSesion.addActionListener(this);
        btnUsuarios.addActionListener(this);
    }

    /**
     * Carga los KeyListeners para los botones del menú principal.
     */
    @Override
    public void CargarKeyListeners() {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto
    }

    /**
     * Carga los Mouseisteners para los botones del menú principal.
     */
    @Override
    public void CargarMouseListeners() {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto
    }

    /**
     * Elimina los ActionListeners de los botones del menú principal.
     */
    @Override
    public void QuitActionListeners() {
        // Eliminar los ActionListeners de los botones
        btnCerrarSesion.removeActionListener(this);
        btnUsuarios.removeActionListener(this);
    }

    /**
     * Elimina los KeyListeners de los componentes del menú principal.
     *
     * @param componente
     */
    @Override
    public void QuitKeyListener(Component componente) {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar KeyListeners en este contexto
    }

    /**
     * Elimina los MouseListeners de los componentes del menú principal.
     *
     * @param componente
     */
    @Override
    public void QuitMouseListener(Component componente) {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto
    }

    /**
     * Implementación de actionPerformed para gestionar los eventos de los
     * botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar si el evento proviene del botón "Cerrar Sesión"
        if (e.getSource() == btnCerrarSesion) {
            // Mostrar una confirmación antes de cerrar la sesión
            if (alerta.confirmacion("¿Cerrar sesión?") == 0) {
                // Ocultar el formulario del menú principal
                instanceOfMenuPrincipalFrame.dispose();

                // Mostrar el formulario de inicio de sesión
                LoginServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true);

                // Colocar el foco en el formulario de inicio de sesión
                LoginServiceImpl.getInstance().GetInstanceOfFrame().requestFocus();
                LoginServiceImpl.getInstance().GetInstanceOfFrame().getTxtUsuario().requestFocus();
            }
        } // Verificar si el evento proviene del botón "Usuarios"
        else if (e.getSource() == btnUsuarios) {
            // Ocultar el formulario del menú principal
            instanceOfMenuPrincipalFrame.dispose();

            // Mostrar el formulario de gestión de usuarios
            UserServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true);
        }
    }
}
