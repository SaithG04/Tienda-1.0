package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOImplements.UserDAOImpl;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.presentation_layer.*;
import java.awt.Component;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * La clase MenuServiceImpl implementa la interfaz MenuService y gestiona las
 * operaciones del menú principal.
 *
 * @author isai_
 */
public final class MenuServiceImpl extends com.tienda.utilities.ServiceUtilities implements ActionListener, FrameService {

    private static volatile MenuServiceImpl instanceOfMenuServiceImpl;

    // Instancia del formulario del menú principal
    private final MenuPrincipalPanel instanceOfMenuPrincipalPanel;

    // Componentes de la interfaz de usuario
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnUsuarios, btnProductos, btnProveedores, btnTransacciones, btnLight, btnDark;

    /**
     * Constructor privado para el patrón Singleton.
     */
    private MenuServiceImpl() {
        // Obtener la instancia del formulario del menú principal
        instanceOfMenuPrincipalPanel = MenuPrincipalPanel.getInstance();

        // Obtener los componentes del formulario
        lblTitle = instanceOfMenuPrincipalPanel.getLblTitle();
        btnCerrarSesion = instanceOfMenuPrincipalPanel.getBtnCerrarSesion();
        btnUsuarios = instanceOfMenuPrincipalPanel.getBtnUsuarios();
        btnProductos = instanceOfMenuPrincipalPanel.getBtnProductos();
        btnProveedores = instanceOfMenuPrincipalPanel.getBtnProveedores();
        btnTransacciones = instanceOfMenuPrincipalPanel.getBtnTransacciones();
        btnLight = instanceOfMenuPrincipalPanel.getBtnLight();
        btnDark = instanceOfMenuPrincipalPanel.getBtnDark();
    }

    public void loadPanel() {
        addPanelToFrame(instanceOfMenuPrincipalPanel);
        cargarActionListeners();
        instanceOfFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        instanceOfFrame.setTitle("Menu Principal");
        Close(instanceOfFrame);
        // Actualizar el título del formulario con el nombre de usuario
        lblTitle.setText("Bienvenido Sr(a): " + LoginServiceImpl.userLogued.getNombreCompleto());
    }

    /**
     * Método estático para obtener la instancia única de MenuServiceImpl.
     *
     * @return Instancia única de MenuServiceImpl.
     */
    @SuppressWarnings("DoubleCheckedLocking")
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
     * Carga los ActionListeners para los botones del menú principal.
     */
    @Override
    public void cargarActionListeners() {
        // Eliminar los ActionListeners existentes para evitar duplicados
        quitActionListeners();

        // Agregar ActionListeners para los botones
        btnCerrarSesion.addActionListener(this);
        btnUsuarios.addActionListener(this);
        btnProductos.addActionListener(this);
        btnProveedores.addActionListener(this);
        btnTransacciones.addActionListener(this);
        btnLight.addActionListener(this);
        btnDark.addActionListener(this);
    }

    /**
     * Carga los KeyListeners para los botones del menú principal.
     */
    @Override
    public void cargarKeyListeners() {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto
    }

    /**
     * Carga los Mouseisteners para los botones del menú principal.
     */
    @Override
    public void cargarMouseListeners() {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar MouseListeners en este contexto
    }

    /**
     * Elimina los ActionListeners de los botones del menú principal.
     */
    @Override
    public void quitActionListeners() {
        // Eliminar los ActionListeners de los botones
        btnCerrarSesion.removeActionListener(this);
        btnUsuarios.removeActionListener(this);
        btnProductos.removeActionListener(this);
        btnProveedores.removeActionListener(this);
        btnTransacciones.removeActionListener(this);
        btnLight.removeActionListener(this);
        btnDark.removeActionListener(this);
    }

    /**
     * Elimina los KeyListeners de los componentes del menú principal.
     *
     * @param componente
     */
    @Override
    public void quitKeyListener(Component componente) {
        // Este método no se implementa en esta clase, ya que no se requiere eliminar KeyListeners en este contexto
    }

    /**
     * Elimina los MouseListeners de los componentes del menú principal.
     *
     * @param componente
     */
    @Override
    public void quitMouseListener(Component componente) {
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
                instanceOfMenuPrincipalPanel.setCursor(waitCursor);
                try {
                    LoginServiceImpl.userLogued.setStatus("logged out");
                    UserDAO userDao = new UserDAOImpl(LoginServiceImpl.userLogued);
                    userDao.actualizar();
                    // Ocultar el formulario del menú principal

                    instanceOfMenuPrincipalPanel.setCursor(defaultCursor);
                    // Mostrar el formulario de inicio de sesión
                    LoginServiceImpl.getInstance().loadPanel();
                    removePanelFromFrame(instanceOfMenuPrincipalPanel);
                } catch (ClassNotFoundException | SQLException ex) {
                    alerta.manejarErrorConexion(this.getClass(), ex);
                    if (ex instanceof SQLException) {
                        System.exit(0);
                    }
                }
            }
        } // Verificar si el evento proviene del botón "Usuarios"
        else if (e.getSource() == btnUsuarios) {
            // Mostrar el formulario de gestión de usuarios
            UserServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanceOfMenuPrincipalPanel);
        } else if (e.getSource() == btnProductos) {
            // Mostrar el formulario de gestión de productos
            ProductoServiceImpl.getInstance().loadPanel();
            // Ocultar el formulario del menú principal
            removePanelFromFrame(instanceOfMenuPrincipalPanel);
        } else if (e.getSource() == btnProveedores) {
            // Mostrar el formulario de gestión de proveedores
            ProveedorServiceImpl.getInstance().loadPanel();
            // Ocultar el formulario del menú principal
            removePanelFromFrame(instanceOfMenuPrincipalPanel);
        } else if (e.getSource() == btnTransacciones) {
            // Mostrar el formulario de transacciones
            TransaccionServiceImpl.getInstance().loadPanel();
            // Ocultar el formulario del menú principal
            removePanelFromFrame(instanceOfMenuPrincipalPanel);
        } else if (e.getSource() == btnLight) {
            saveDefaultTheme("light");
            configureTheme();
        } else if (e.getSource() == btnDark) {
            saveDefaultTheme("dark");
            configureTheme();
        }
    }
}
