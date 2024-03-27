package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOimplements.ProductoDAOImpl;
import com.tienda.data_access_layer.ProductoDAO;
import com.tienda.entity.Producto;
import com.tienda.presentation_layer.ProductosFrame;
import com.tienda.service_layer.FrameService;
import com.tienda.utilities.ServiceUtilities;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class ProductoServiceImpl extends ServiceUtilities implements ActionListener, FrameService<ProductosFrame> {

    private static volatile ProductoServiceImpl instanciaImpl;

    private final ProductosFrame instanciaFrame;
    private final JButton btnUpdate, btnDelete, btnAdd, btnHome, btnFind;
    private final JTable tbProducto;
    private final JTextField txtNombre, txtCantidad, txtUnidad, txtPrecio;
    private final JComboBox<String> cbProveedor;
    private static Map<Integer, String> proveedores;

    private ProductoServiceImpl() {

        instanciaFrame = ProductosFrame.getProcFrame();

        this.btnUpdate = instanciaFrame.getBtnEdit();
        this.btnDelete = instanciaFrame.getTbnDelete();
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
    public ProductosFrame getInstanceOfFrame() {
        instanciaFrame.setLocationRelativeTo(null);
        Close(instanciaFrame);
        // Establecer el enfoque en el campo de nombre completo al abrir el formulario
        instanciaFrame.requestFocus();
        // Agregar ActionListener a los botones y cargar los usuarios en un hilo separado
        cargarActionListeners();
        tbProducto.setModel(new DefaultTableModel(0, 0));
        new Thread(() -> {
            tbProducto.setModel(cargarProducto());
        }).start();
        return instanciaFrame;
    }

    @Override
    public void cargarActionListeners() {
        quitActionListeners();
        quitKeyListener(instanciaFrame);
        quitMouseListener(instanciaFrame);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnHome.addActionListener(this);
        btnFind.addActionListener(this);
        cargarMouseListeners();
        cargarKeyListeners();
    }

    @Override
    public void cargarKeyListeners() {
        tbProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (tbProducto.getSelectedRowCount() > 1) {
                    limpiarTxt();
                } else {
                    rellenoCamposSeleccionados();
                }
            }
        });

    }

    @Override
    public void cargarMouseListeners() {
        tbProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rellenoCamposSeleccionados();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (tbProducto.getSelectedRowCount() > 1) {
                    limpiarTxt();
                }
            }
        });
    }

    @Override
    public void quitActionListeners() {
        btnUpdate.removeActionListener(this);
        btnDelete.removeActionListener(this);
        btnAdd.removeActionListener(this);
        btnHome.removeActionListener(this);
        btnFind.removeActionListener(this);
    }

    @Override
    public void quitKeyListener(Component componente) {
        for (KeyListener kl : componente.getKeyListeners()) {
            componente.removeKeyListener(kl); // Eliminar los MouseListeners del componente
        }
    }

    @Override
    public void quitMouseListener(Component componente) {
        for (MouseListener ml : componente.getMouseListeners()) {
            componente.removeMouseListener(ml); // Eliminar los MouseListeners del componente
        }
    }

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
            errorSQL(this.getClass(), e);
        }
        return model;
    }

    public void RegistrarProducto() {
        try {
            String nombre = txtNombre.getText();
            String unidad = txtUnidad.getText();
            double cantidad = Double.parseDouble(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            if (nombre.isEmpty() || unidad.isEmpty() || cantidad == 0 || cbProveedor.getSelectedIndex() == 0) {
                alerta.advertencia("Por favor, complete todos los campos.");
                return;
            }
            Producto ProductoCreated = new Producto();
            ProductoCreated.setId(0);
            ProductoCreated.setNombre(nombre);
            ProductoCreated.setCantidad(cantidad);
            ProductoCreated.setMedida(unidad);
            ProductoCreated.setProveedor(getIdProveedor());
            ProductoCreated.setPrecio(precio);

            ProductoDAO producDAO = new ProductoDAOImpl(ProductoCreated);
            limpiarTxt();
            boolean registrado = producDAO.registrar();
            if (registrado) {
                tbProducto.setModel(cargarProducto());
                // Mostrar un mensaje de registro exitoso
                alerta.aviso("Registro exitoso.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            errorSQL(this.getClass(), e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            RegistrarProducto();
        } else if (e.getSource() == btnHome) {
            instanciaFrame.dispose();
            MenuServiceImpl.getInstance().getInstanceOfFrame().setVisible(true);
        } else if (e.getSource() == btnUpdate) {
            actualizarProducto();
        } else if (e.getSource() == btnDelete) {
            eliminarProducto();
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
        cbProveedor.setSelectedIndex(0);
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
        if (verificarValores()) {
            return;
        }

        String nombre = txtNombre.getText();
        double cantidad = Double.parseDouble(txtCantidad.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        String undMedida = txtUnidad.getText();
        int idProveedor = getProvTabla(2);

        if (esSelecionMayor(tbProducto)) {
            alerta.advertencia("Cuidado!, esta seleccionando varios productos.");
            return;
        }
        if (nombre.equals("") || undMedida.equals("") || idProveedor < 1) {
            alerta.faltanDatos();
            return;
        }

        try {
            Producto prodUpdate = new Producto();
            prodUpdate.setId((int) tbProducto.getValueAt(tbProducto.getSelectedRow(), 0));

            ProductoDAO prodDAO = new ProductoDAOImpl(prodUpdate);
            Producto prodFind = prodDAO.getById();

            prodFind.setCantidad(cantidad);
            prodFind.setMedida(undMedida);
            prodFind.setNombre(nombre);
            prodFind.setProveedor(idProveedor);
            prodFind.setPrecio(precio);

            prodDAO = new ProductoDAOImpl(prodFind);
            limpiarTxt();
            boolean actualizado = prodDAO.actualizar();
            if (actualizado) {
                tbProducto.setModel(cargarProducto());
                alerta.aviso("Actualización exitosa.");
            }
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

    private int getProvTabla(int row) {
        int index = 0;
        String proveedor = tbProducto.getValueAt(row, 2).toString();
        for (int i = 0; i <= cbProveedor.getItemCount(); i++) {
            if (cbProveedor.getItemAt(i) == proveedor) {
                index = i;
            }
        }
        return index;
    }

    public boolean esSelecionMayor(JTable tabla) {
        if (tabla.getSelectedRowCount() > 1) {
            return true;
        } else {
            return false;
        }
    }

    private void eliminarProducto() {
        try {
            if (alerta.confirmacion("¿Está seguro de eliminar este producto?") == 0) {
                // Obtener el ID del producto a eliminar
                Producto productoForDelete = new Producto();
                productoForDelete.setId((int) tbProducto.getValueAt(tbProducto.getSelectedRow(), 0));
                // Crear un objeto productoDAOImpl para realizar operaciones de base de datos
                ProductoDAO producDAO = new ProductoDAOImpl(productoForDelete);
                limpiarTxt();
                // Eliminar el producto de la base de datos
                boolean eliminado = producDAO.eliminar();
                if (eliminado) {
                    // Actualizar tabla
                    tbProducto.setModel(cargarProducto());
                    alerta.aviso("Producto eliminado correctamente.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de errores de conexión
            alerta.manejarErrorConexion(this.getClass(), e);
        }
    }

    private boolean verificarValores() {
        for (int i = 0; i < txtCantidad.getText().length(); i++) {
            if (Character.isLetter(txtCantidad.getText().charAt(i))) {
                alerta.advertencia("Ups, parece que tienes un valor erroneo");
                txtCantidad.requestFocus();
                return true;
            }
        }
        for (int i = 0; i < txtPrecio.getText().length(); i++) {
            if (Character.isLetter(txtPrecio.getText().charAt(i))) {
                alerta.advertencia("Ups, parece que tienes un valor erroneo");
                txtPrecio.requestFocus();
                return true;
            }
        }
        return false;
    }

//    private void buscarProducto() {
//        DefaultTableModel model = new DefaultTableModel();
//
//        String nombre = txtNombre.getText();
//        String cantidad = txtCantidad.getText();
//        String precio = txtPrecio.getText();
//        String undMedida = txtUnidad.getText();
//        int idProveedor = getProveedorBuscado();
//
//        if (!nombre.equals("")){
//            model.addRow(buscarEnTabla(nombre));
//        }else if (!cantidad.equals("")){
//            buscarEnTabla(nombre);
//        }else if (!precio.equals("")){
//            buscarEnTabla(nombre);
//        }else if (!undMedida.equals("")){
//            buscarEnTabla(nombre);
//        }
//        
//    }
    private int getProveedorBuscado() {
        int proveedor = 0;
        String seleccionado = cbProveedor.getSelectedItem().toString();
        for (int i = 0; i < cbProveedor.getItemCount(); i++) {
            if (cbProveedor.getSelectedItem().toString().equals(seleccionado)) {
                proveedor = i;
            }
        }
        return proveedor;
    }

    private Producto buscarEnTabla(String busqueda) {
        Producto productoEncontrado = new Producto();

        for (int i = 0; i < tbProducto.getRowCount(); i++) {
            for (int j = 0; j < tbProducto.getColumnCount(); j++) {
                if (busqueda.equals(tbProducto.getValueAt(i, j))) {
                    productoEncontrado.setId((int) tbProducto.getValueAt(i, 0));
                    productoEncontrado.setNombre((String) tbProducto.getValueAt(i, 1));
                    productoEncontrado.setProveedor(getProveedorBuscado());
                    productoEncontrado.setCantidad((double) tbProducto.getValueAt(i, 3));
                    productoEncontrado.setPrecio((double) tbProducto.getValueAt(i, 4));
                    productoEncontrado.setMedida((String) tbProducto.getValueAt(i, 5));
                    return productoEncontrado;
                }

            }
        }
        return null;

    }
}
