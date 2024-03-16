package Capa_de_logica_de_negocio;

import Capa_de_presentacion.FrmMenuPrincipal;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author isai_
 */
public class MenuService extends CommonUtilities implements FrameOperations{

    private final FrmMenuPrincipal a;
    private final JLabel lblTitle;
    private final JButton btnCerrarSesion, btnProductos, btnProveedores, btnTransacciones, btnUsuarios;
    boolean value = false;

    public MenuService() {
        a = new FrmMenuPrincipal();
        lblTitle = a.getLblTitle();
        btnCerrarSesion = a.getBtnCerrarSesion();
        btnProductos = a.getBtnProductos();
        btnProveedores = a.getBtnProveedores();
        btnTransacciones = a.getBtnTransacciones();
        btnUsuarios = a.getBtnUsuarios();
    }

    @Override
    public final void close() {
        a.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (!value) {
                    if (oA.confirmación("¿Salir de la aplicación?") == 0) {
                        System.exit(0);
                    }
                }
            }
        });
    }

    public final void MouseListeners() {
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (oA.confirmación("¿Cerrar sesión?") == 0) {
                    value = true;
                    a.dispose();
                    new LoginService().loadFrame();
                    value = false;
                }
            }
        });
        btnProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mProveedores().loadFrame();
            }
        });
        btnProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mProductos().loadFrame();
            }
        });
        btnTransacciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new mTransaccion().CargarTransacciones();
            }
        });
        btnUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                a.dispose();
                new UsuariosService().loadFrame();
            }
        });
    }

    @Override
    public void loadFrame() {
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        MouseListeners();
        close();
        lblTitle.setText("Has iniciado sesión como: " + LoginService.usuario_logueado.getUsername().toUpperCase());
    }
}
