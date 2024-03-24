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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private final JTextField txtNombre, txtCantidad, txtUnidad, txtPrecio;
    private final JComboBox<String> cbProveedor;
    private static Map<Integer, String> proveedores;

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
        this.txtUnidad = instanciaFrame.getTxtTipo();
        this.txtPrecio = instanciaFrame.getTxtPrecio();
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
        CargarMouseListeners();
    }

    @Override
    public void CargarKeyListeners() {
    }

    @Override
    public void CargarMouseListeners() {
        tbProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rellenoCamposSeleccionados();
            }
        });
    }

    @Override
    public void QuitActionListeners() {
        btnUpdate.removeActionListener(this);
        tbnDelete.removeActionListener(this);
        btnAdd.removeActionListener(this);
        btnHome.removeActionListener(this);
        btnFind.removeActionListener(this);
    }

    @Override
    public void QuitKeyListener(Component componente) {
    }

    @Override
    public void QuitMouseListener(Component componente) {
        for (MouseListener ml : componente.getMouseListeners()) {
            componente.removeMouseListener(ml); // Eliminar los MouseListeners del componente
        }
    }

    @Override
    public DefaultTableModel cargarProducto() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Producto", "Proveedor", "Cantidad", "Precio", "Unidad Medida"}, 0);
        try {

            ProductoDAO productoDAO = new ProductoDAOImpl(new Producto());
            actualizarProveedores();
            List<Producto> lista = productoDAO.listar();

            lista.forEach(producto -> {
                model.addRow(new Object[]{producto.getId(), producto.getNombre(), proveedores.get(producto.getProveedor()), producto.getCantidad(), producto.getPrecio(), producto.getMedida()});
            });

        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante la carga de usuarios
            alerta.manejarErrorConexion(this.getClass(), e);
        }
        return model;
    }

    public void RegistrarProducto() {
        String nombre = txtNombre.getText();
        String unidad = txtUnidad.getText();
        double cantidad = Double.parseDouble(txtCantidad.getText());
        double precio = Double.parseDouble(txtPrecio.getText());

        if (nombre.isEmpty() || unidad.isEmpty() || cantidad == 0 || cbProveedor.getSelectedIndex() == 0) {
            alerta.advertencia("Por favor, complete todos los campos.");
            return;
        }
        try {

            Producto ProductoCreated = new Producto();
            ProductoCreated.setId(0);
            ProductoCreated.setNombre(nombre);
            ProductoCreated.setCantidad(cantidad);
            ProductoCreated.setMedida(unidad);
            ProductoCreated.setProveedor(getIdProveedor());
            ProductoCreated.setPrecio(precio);

            ProductoDAO producDAO = new ProductoDAOImpl(ProductoCreated);
            producDAO.registrar();

            limpiarTxt();
            tbProducto.setModel(cargarProducto());
            // Mostrar un mensaje de registro exitoso
            alerta.aviso("Registro exitoso.");

        } catch (SQLException | ClassNotFoundException e) {
            // Manejar cualquier error de conexión durante el registro
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            RegistrarProducto();
        } else if (e.getSource() == btnHome) {
            instanciaFrame.dispose();
            MenuServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true);
        } else if (e.getSource() == btnUpdate) {
            actualizarProducto();
        }
    }

    public int getIdProveedor() {
        String nombre = cbProveedor.getSelectedItem().toString();
        Set<Integer> claves = proveedores.keySet();
        for (Integer clave : claves) {
            if (proveedores.get(clave) == nombre) {
                return clave;
            }
        }
        return 0;
    }

    public void limpiarTxt() {
        txtCantidad.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtUnidad.setText("");
        txtNombre.requestFocus();
    }

    private void actualizarProveedores() throws ClassNotFoundException, SQLException {
        proveedores = ProductoDAOImpl.getNameProveedor();
        Set<Integer> claves = proveedores.keySet();
        cbProveedor.removeAllItems();
        cbProveedor.addItem("<SELECCIONAR>");
        for (Integer clave : claves) {
            cbProveedor.addItem(proveedores.get(clave));
        }
    }

    private void actualizarProducto() {
        String nombre = txtNombre.getText();
        double cantidad = Double.parseDouble(txtCantidad.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        String undMedida = txtUnidad.getText();
        int idProveedor = getProvTabla(2);

        try {
            Producto prodUpdate = new Producto();
            prodUpdate.setId((int) tbProducto.getValueAt(tbProducto.getSelectedRow(), 0));

            ProductoDAO prodDAO = new ProductoDAOImpl(prodUpdate);
            Producto prodFind = prodDAO.getById(prodUpdate.getId());

            prodFind.setCantidad(cantidad);
            prodFind.setMedida(undMedida);
            prodFind.setNombre(nombre);
            prodFind.setProveedor(idProveedor);
            prodFind.setPrecio(precio);

            prodDAO = new ProductoDAOImpl(prodFind);
            prodDAO.actualizar();

            tbProducto.setModel(cargarProducto());
            alerta.aviso("Actualización exitosa.");
            limpiarTxt();
        } catch (SQLException | ClassNotFoundException e) {
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    public void rellenoCamposSeleccionados() {
        int row = tbProducto.getSelectedRow();
        if (row != -1) {
            txtNombre.setText(tbProducto.getValueAt(row, 1).toString());
            cbProveedor.setSelectedIndex(getProvTabla(row));
            txtCantidad.setText(tbProducto.getValueAt(row, 3).toString());
            txtPrecio.setText(tbProducto.getValueAt(row, 4).toString());
            txtUnidad.setText(tbProducto.getValueAt(row, 5).toString());
        }
    }
    
    private int getProvTabla(int row){
        int index = 0;
        String proveedor = tbProducto.getValueAt(row, 2).toString();
        for (int i = 0; i <= cbProveedor.getItemCount(); i++){
            if (cbProveedor.getItemAt(i) == proveedor){
                index = i;
            }
        }
        return index;
    }
}
