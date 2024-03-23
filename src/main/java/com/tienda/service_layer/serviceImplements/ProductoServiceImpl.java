package com.tienda.service_layer.serviceImplements;

import com.tienda.data_access_layer.DAOimplements.ProductoDAOImpl;
import com.tienda.data_access_layer.ProductoDAO;
import com.tienda.entity.Producto;
import com.tienda.presentation_layer.ProductosFrame;
import com.tienda.service_layer.ProductoService;
import com.tienda.utilities.ServiceUtilities;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProductoServiceImpl extends ServiceUtilities implements ProductoService, ActionListener {

    private static volatile ProductoServiceImpl instanciaImpl;

    private final ProductosFrame instanciaFrame;
    private final JButton btnUpdate, tbnDelete, btnAdd, btnHome, btnFind;
    private final JTable tbProducto;
    private final JTextField txtNombre, txtCantidad, txtTipo;
    private final JComboBox<String> cbProveedor;

    private ProductoServiceImpl() {

        instanciaFrame = ProductosFrame.getProcFrame();

        this.btnUpdate = instanciaFrame.getBtnEdit();
        this.tbnDelete = instanciaFrame.getTbnDelete();
        this.btnAdd = instanciaFrame.getBtnAdd();
        this.btnHome = instanciaFrame.getBtninicio();
        this.btnFind = instanciaFrame.getBtnFind();
        this.tbProducto = instanciaFrame.getTabla();
        this.txtCantidad = instanciaFrame.getTxtCantidad();
        this.txtNombre = instanciaFrame.getTxtProducto();
        this.txtTipo = instanciaFrame.getTxtTipo();
        this.cbProveedor = instanciaFrame.getCbProveedor();
    }

    public static ProductoServiceImpl getInstance() {
        if (instanciaImpl == null) {
            synchronized (ProductoServiceImpl.class) { // Sincronización para hilos
                if (instanciaImpl == null) {
                    instanciaImpl = new ProductoServiceImpl();
                }
            }
        }
        return instanciaImpl;
    }

    @Override
    public ProductosFrame GetInstanceOfFrame() {
        instanciaFrame.setLocationRelativeTo(null);
        Close(instanciaFrame);
        // Establecer el enfoque en el campo de nombre completo al abrir el formulario
        instanciaFrame.requestFocus();
        // Agregar ActionListener a los botones y cargar los usuarios en un hilo separado
        CargarActionListeners();
        tbProducto.setModel(new DefaultTableModel(0, 0));
        new Thread(() -> {
            tbProducto.setModel(cargarProducto());
        }).start();
        return instanciaFrame;
    }

    @Override
    public void CargarActionListeners() {
        QuitActionListeners();
        btnUpdate.addActionListener(this);
        tbnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnHome.addActionListener(this);
        btnFind.addActionListener(this);
    }

    @Override
    public void CargarKeyListeners() {}

    @Override
    public void CargarMouseListeners() {}

    @Override
    public void QuitActionListeners() {
        btnUpdate.removeActionListener(this);
        tbnDelete.removeActionListener(this);
        btnAdd.removeActionListener(this);
        btnHome.removeActionListener(this);
        btnFind.removeActionListener(this);
    }

    @Override
    public void QuitKeyListener(Component componente) {}

    @Override
    public void QuitMouseListener(Component componente) {}

    @Override
    public DefaultTableModel cargarProducto() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Producto","Proveedor" ,"Cantidad" , "Precio", "Unidad Medida"}, 0);
        try {

            ProductoDAO productoDAO = new ProductoDAOImpl(new Producto());

            // Obtener la lista de usuarios desde la base de datos usando las funciones definidas en UserDAOImpl
            List<Producto> lista = productoDAO.listar();
            lista.forEach(producto -> {
                model.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getProveedor(), producto.getCantidad(), producto.getPrecio(), producto.getMedida()});
            });
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante la carga de usuarios
            alerta.manejarErrorConexion(this.getClass(), e);
        }
        return model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
