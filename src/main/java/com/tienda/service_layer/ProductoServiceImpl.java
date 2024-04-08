package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOImplements.ProductoDAOImpl;
import com.tienda.data_access_layer.ProductoDAO;
import com.tienda.entity.Producto;
import com.tienda.presentation_layer.ProductosPanel;
import com.tienda.utilities.ServiceUtilities;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class ProductoServiceImpl extends ServiceUtilities implements ActionListener, FrameService {

    private static volatile ProductoServiceImpl instanciaImpl;

    private final ProductosPanel instanciaPanel;

    private final JButton btnUpdate, btnDelete, btnAdd, btnHome;
    private final JTable tbProducto;
    private final JTextField txtNombre, txtCantidad, txtUnidad, txtPrecio;
    private final JComboBox<String> cbProveedor;
    private static Map<Integer, String> proveedores;

    private ProductoServiceImpl() {

        instanciaPanel = ProductosPanel.getProcPanel();

        this.btnUpdate = instanciaPanel.getBtnEdit();
        this.btnDelete = instanciaPanel.getTbnDelete();
        this.btnAdd = instanciaPanel.getBtnAdd();
        this.btnHome = instanciaPanel.getBtninicio();
        this.tbProducto = instanciaPanel.getTabla();
        this.txtCantidad = instanciaPanel.getTxtCantidad();
        this.txtNombre = instanciaPanel.getTxtProducto();
        this.txtUnidad = instanciaPanel.getTxtTipo();
        this.txtPrecio = instanciaPanel.getTxtPrecio();
        this.cbProveedor = instanciaPanel.getCbProveedor();
    }

    public void loadPanel() {
        Close(instanceOfFrame);
        addPanelToFrame(instanciaPanel);
        // Establecer el enfoque en el campo de nombre completo al abrir el formulario
        instanciaPanel.requestFocus();
        // Agregar ActionListener a los botones y cargar los usuarios en un hilo separado
        cargarActionListeners();
        tbProducto.setModel(new DefaultTableModel(0, 0));
        new Thread(() -> {
            tbProducto.setModel(cargarProducto());
        }).start();
    }

    @SuppressWarnings("DoubleCheckedLocking")
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
    public void cargarActionListeners() {
        quitActionListeners();
        quitKeyListener(instanciaPanel);
        quitMouseListener(instanciaPanel);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnAdd.addActionListener(this);
        btnHome.addActionListener(this);
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
        txtCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarValorDecimal(e, txtCantidad.getText());
            }
        });
        txtPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validarValorDecimal(e, txtPrecio.getText());
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
            setCursores(waitCursor);
            ProductoDAO productoDAO = new ProductoDAOImpl(new Producto());
            actualizarProveedores();
            List<Producto> lista = productoDAO.listar();

            lista.forEach(producto -> {
                model.addRow(new Object[]{producto.getId(), producto.getNombre(), proveedores.get(producto.getProveedor()), producto.getCantidad(), producto.getPrecio(), producto.getMedida()});
            });

        } catch (ClassNotFoundException | SQLException e) {
            errorSQL(this.getClass(), e);
        } finally {
            setCursores(defaultCursor);
        }
        return model;
    }

    public void RegistrarProducto() {
        try {
            setCursores(waitCursor);
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
        } finally {
            setCursores(defaultCursor);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            RegistrarProducto();
        } else if (e.getSource() == btnHome) {
            limpiarTxt();
            MenuServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanciaPanel);
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
            cbProveedor.addItem(proveedores.get((int) clave));
        }
    }

    private void actualizarProducto() {
        if (verificarValores()) {
            return;
        }
        int idProducto = (int) tbProducto.getValueAt(tbProducto.getSelectedRow(), 0);
        String nombre = txtNombre.getText();
        double cantidad = Double.parseDouble(txtCantidad.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        String undMedida = txtUnidad.getText();
        int idProveedor = getProvTabla();

        if (esSelecionMayor(tbProducto)) {
            alerta.advertencia("Cuidado!, esta seleccionando varios productos.");
            return;
        }
        if (nombre.equals("") || undMedida.equals("") || idProveedor < 1) {
            alerta.faltanDatos();
            return;
        }

        try {
            setCursores(waitCursor);
            Producto prodUpdate = new Producto();

            ProductoDAO prodDAO = new ProductoDAOImpl(prodUpdate);
//            Producto prodFind = prodDAO.getById();
            prodUpdate.setId(idProducto);
            prodUpdate.setCantidad(cantidad);
            prodUpdate.setMedida(undMedida);
            prodUpdate.setNombre(nombre);
            prodUpdate.setProveedor(idProveedor);
            prodUpdate.setPrecio(precio);

            prodDAO.setEntity(prodUpdate);
            System.out.println(prodUpdate.toString());
            limpiarTxt();
            boolean actualizado = prodDAO.actualizar();
            if (actualizado) {
                tbProducto.setModel(cargarProducto());
                alerta.aviso("Actualización exitosa.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            alerta.manejarErrorConexion(this.getClass(), e);
        } finally {
            setCursores(defaultCursor);
        }
    }

    public void rellenoCamposSeleccionados() {
        int row = tbProducto.getSelectedRow();
        if (row != -1) {
            txtNombre.setText(tbProducto.getValueAt(row, 1).toString());
            cbProveedor.setSelectedItem(tbProducto.getValueAt(row, 2));
            txtCantidad.setText(tbProducto.getValueAt(row, 3).toString());
            txtPrecio.setText(tbProducto.getValueAt(row, 4).toString());
            txtUnidad.setText(tbProducto.getValueAt(row, 5).toString());
        }
    }

    private int getProvTabla() {
        try {
            proveedores = ProductoDAOImpl.getNameProveedor();
            String proveedor = cbProveedor.getSelectedItem().toString();
            for (int a : proveedores.keySet()) {
                if (proveedores.get(a).equals(proveedor)) {
                    return a;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            alerta.aviso("Error al intentar encontrar el proveedor");
        }
        return -1;
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
            setCursores(waitCursor);
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
        } finally {
            setCursores(defaultCursor);
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

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(instanciaPanel.getComponents(), cursor);
    }
}
