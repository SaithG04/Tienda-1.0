package Capa_de_logica_de_negocio;

import Capa_de_acceso_a_datos.Modelo_de_datos.cAdministradorProductos;
import Capa_de_acceso_a_datos.Modelo_de_datos.cAdministradorTransaccion;
import Capa_de_presentacion.frmAdministradorProductos;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class mProductos extends CommonUtilities implements FrameOperations{

    final JButton btnRP;
    final ButtonGroup bg1, bg2, bg3;
    final JComboBox<String> cbCtg, cbProd, cbProv;
    final JTable productos;
    final JPopupMenu pmOpc;
    final JRadioButton rbElegir1, rbElegir2, rbEscribir1, rbEscribir2;
    final JTextField stock, categoria, fecha, importe, producto, tipo;
    final frmAdministradorProductos fAP;
    boolean procede = false;
    final Class clase = mProductos.class;

    cAdministradorProductos oProductos = new cAdministradorProductos();
    cAdministradorTransaccion oT = new cAdministradorTransaccion();

    public mProductos() {
        fAP = new frmAdministradorProductos();
        btnRP = fAP.getBtnRProducto();
        bg1 = fAP.getbGrupo1();
        bg2 = fAP.getbGrupo2();
        bg3 = fAP.getbGrupo3();
        cbCtg = fAP.getCbCategoria();
        cbProd = fAP.getCbProducto();
        cbProv = fAP.getCbProveedor();
        productos = fAP.getJtbProductos();
        pmOpc = fAP.getPmOpciones();
        rbElegir1 = fAP.getRbElegir1();
        rbElegir2 = fAP.getRbElegir2();
        rbEscribir1 = fAP.getRbEscribir1();
        rbEscribir2 = fAP.getRbEscribir2();
        stock = fAP.getTxtStock();
        categoria = fAP.getTxtCategoria();
        fecha = fAP.getTxtFecha();
        importe = fAP.getTxtImporte();
        producto = fAP.getTxtProducto();
        tipo = fAP.getTxtTipo();
    }

    void MouseListeners() {
        rbElegir1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbCtg.setEnabled(true);
                categoria.setEnabled(false);
                rbElegir2.setEnabled(true);
            }
        });
        rbElegir2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (rbElegir2.isEnabled()) {
                    cbProd.setEnabled(true);
                    producto.setEnabled(false);
                }
            }
        });
        rbEscribir1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbCtg.setEnabled(false);
                categoria.setEnabled(true);
                rbElegir2.setEnabled(false);
                cbProd.setEnabled(false);
            }
        });
        rbEscribir2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbProd.setEnabled(false);
                producto.setEnabled(true);
            }
        });
        cbProd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (cbProd.isEnabled()) {
                    cbProd.removeAllItems();
                    if (cbCtg.getSelectedIndex() == -1) {
                        oA.mostrarError(clase, "Eliga una categoria primero.", null);
                    } else {
                        try {
                            oProductos.setCategoria(cbCtg.getSelectedItem().toString());
                            MostrarProductosCB();
                        } catch (ClassNotFoundException | SQLException ex) {
                            oA.manejarErrorConexion(clase, ex);
                        }
                    }
                }
            }
        });
        cbCtg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cbProd.removeAllItems();
                cbProd.setSelectedItem(null);
            }
        });
        btnRP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (Validar()) {
                    if (!OnlyProduc() && procede) { //No es solo para crear producto
                        String proveedor = cbProv.getSelectedItem().toString();
                        BigDecimal cantidad = new BigDecimal(stock.getText());
                        BigDecimal Import = new BigDecimal(importe.getText());
                        String uni = tipo.getText();
                        oProductos.setUnidad(uni);

                        if (rbElegir1.isSelected() && rbElegir2.isSelected()) {
                            try {
                                // Un producto existente tendrá una nueva transaccion
                                String prod = cbProd.getSelectedItem().toString();
                                oProductos.setProducto(prod);
                                oProductos.setCantidad(cantidad);
                                oProductos.AgregarStock();
                                java.sql.Timestamp fechaT = new java.sql.Timestamp(System.currentTimeMillis());
                                InsertarTransaccionNueva(prod, proveedor, Import, cantidad,
                                        tipo.getText(), "Compra", fecha.getText(), fechaT);
                                oA.aviso("Su transaccion ha procedido correctamente.");
                            } catch (SQLException | ClassNotFoundException ex) {
                                oA.manejarErrorConexion(clase, ex);
                            }
                        } else if (rbEscribir2.isSelected() && rbElegir1.isSelected()) {
                            try {
                                // Nuevo producto de categoría existente con nueva transaccion
                                String categoria = cbCtg.getSelectedItem().toString();
                                String productoNuevo = producto.getText();
                                oProductos.setProducto(productoNuevo);
                                oProductos.setCategoria(categoria);
                                oProductos.setCantidad(cantidad);
                                oProductos.InsertarNuevo();
                                java.sql.Timestamp fechaT = new java.sql.Timestamp(System.currentTimeMillis());
                                InsertarTransaccionNueva(productoNuevo, proveedor, Import, cantidad,
                                        tipo.getText(), "Compra", fecha.getText(), fechaT);
                                oA.aviso("Su transaccion ha procedido correctamente.");
                            } catch (SQLException | ClassNotFoundException ex) {
                                oA.manejarErrorConexion(clase, ex);
                            }
                        } else if (rbEscribir2.isSelected() && rbEscribir1.isSelected()) {
                            try {
                                // Nuevo producto de nueva categoría con nueva transaccion
                                String categoriaNueva = categoria.getText();
                                String productoNuevo = producto.getText();
                                oProductos.setProducto(productoNuevo);
                                oProductos.setCategoria(categoriaNueva);
                                oProductos.setCantidad(cantidad);
                                oProductos.InsertarNuevo();
                                java.sql.Timestamp fechaT = new java.sql.Timestamp(System.currentTimeMillis());
                                InsertarTransaccionNueva(productoNuevo, proveedor, Import, cantidad,
                                        tipo.getText(), "Compra", fecha.getText(), fechaT);
                                oA.aviso("Su transaccion ha procedido correctamente.");
                            } catch (SQLException | ClassNotFoundException ex) {
                                oA.manejarErrorConexion(clase, ex);
                            }
                        }
                    } else if (OnlyProduc() && procede) { //Es solo para crear producto
                        oProductos.setCantidad(null);
                        oProductos.setUnidad(null);
                        if (rbEscribir2.isSelected() && rbElegir1.isSelected()) {
                            try {
                                // Nuevo producto de categoría existente
                                String categoria = cbCtg.getSelectedItem().toString();
                                String productoNuevo = producto.getText();
                                oProductos.setProducto(productoNuevo);
                                oProductos.setCategoria(categoria);
                                oProductos.InsertarNuevo();
                                oA.aviso("Registro exitoso");
                                Limpiar();
                                MostrarProductos();
                                MostrarCategorias();
                            } catch (SQLException | ClassNotFoundException ex) {
                                oA.manejarErrorConexion(clase, ex);
                            }
                        } else if (rbEscribir2.isSelected() && rbEscribir1.isSelected()) {
                            try {
                                // Nuevo producto de nueva categoría
                                String categoriaNueva = categoria.getText();
                                String productoNuevo = producto.getText();
                                oProductos.setProducto(productoNuevo);
                                oProductos.setCategoria(categoriaNueva);
                                oProductos.InsertarNuevo();
                                oA.aviso("Registro exitoso");
                                Limpiar();
                                MostrarProductos();
                                MostrarCategorias();
                            } catch (SQLException | ClassNotFoundException ex) {
                                oA.manejarErrorConexion(clase, ex);
                            }
                        }
                    }
                }
            }
        });
        importe.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt
            ) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected()
                        || rbEscribir2.isSelected())) {
                    importe.setEnabled(true);
                }
            }
        }
        );
        fecha.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt
            ) {
                if ((rbElegir1.isSelected() || rbEscribir1.isSelected()) && (rbElegir2.isSelected()
                        || rbEscribir2.isSelected())) {
                    fecha.setEnabled(true);
                }
            }
        }
        );
    }

    void KeyListeners() {
        stock.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarValorDecimal(evt, stock.getText());
            }
        });
        importe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validarValorDecimal(evt, importe.getText());
            }
        });
        tipo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                ValidarSoloLetras(evt, tipo.getText(), (short) 10);
            }
        });
    }

    public final void MostrarProveedores() throws SQLException, ClassNotFoundException {
        List<String> ListarProveedores = oProductos.ListarProveedores();

        for (String elemento : ListarProveedores) {
            cbProv.addItem(elemento);
        }
        cbProv.setSelectedItem(null);
    }

    public final void MostrarCategorias() throws ClassNotFoundException, SQLException {
        List<String> ListarCategorias = oProductos.ListarCategorias();
        cbCtg.removeAllItems();
        for (String elemento : ListarCategorias) {
            cbCtg.addItem(elemento);
        }
        cbCtg.setSelectedItem(null);
    }

    public void MostrarProductos() throws SQLException, ClassNotFoundException {
        DefaultTableModel MostrarProductos = oProductos.MostrarProductos(productos.getModel());
        productos.setModel(MostrarProductos);
    }

    public void MostrarProductosCB() throws ClassNotFoundException, SQLException {
        List<String> ListarProductosPorCategoria = oProductos.ListarProductosPorCategoria();
        if (ListarProductosPorCategoria.isEmpty()) {
            oA.mostrarError(clase, "No hay productos en esta categoría.", null);
        } else {
            for (String elemento : ListarProductosPorCategoria) {
                cbProd.addItem(elemento);
            }
            cbProd.setSelectedItem(null);
        }
    }

    private void InsertarTransaccionNueva(String producto, String proveedor, BigDecimal importe, BigDecimal cantidad,
            String unidad, String tipoTrans, String fechaC, java.sql.Timestamp fechaTrans) throws SQLException, ClassNotFoundException {
        oT.setIdTransaccion(0);
        oT.setTipoTrans(tipoTrans);
        oT.setIdProducto(producto);
        oT.setMonto(importe);
        oT.setCantidad(cantidad);
        oT.setUnidad(unidad);
        oT.setIdProveedor(proveedor);
        oT.setFechaCad(fechaC);
        oT.setFechaTrans(fechaTrans);
//        oT.setIdUsuario(LoginService.oL.getUsuario());
        oT.InsertarTransaccion();
        Limpiar();
        MostrarProductos();
        MostrarCategorias();
    }

    void opciones() {

        JMenuItem modificarItem = new JMenuItem("Modificar");
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        JMenuItem verInfoItem = new JMenuItem("Ver información del producto");

        pmOpc.add(modificarItem);
        pmOpc.add(eliminarItem);
        pmOpc.add(verInfoItem);

        productos.setComponentPopupMenu(pmOpc);

        modificarItem.addActionListener((ActionEvent e) -> {
            int fila = productos.getSelectedRow();
            if (fila != -1) {
                if (productos.getValueAt(fila, 3) != null) {
                    try {
                        rbEscribir1.setEnabled(false);
                        rbEscribir2.setEnabled(false);
                        rbElegir1.setEnabled(false);
                        importe.setEnabled(false);
                        fecha.setEnabled(false);

                        String entrada = oA.entrada("Ingrese cantidad de productos salientes:", "");
                        if (!entrada.isEmpty()) {
                            BigDecimal cantidad = new BigDecimal(entrada);
                            oProductos.setCantidad(cantidad);
                            oProductos.setProducto(productos.getValueAt(fila, 1).toString());

                            boolean DisminuirStock = oProductos.DisminuirStock();
                            if (DisminuirStock) {
                                String uni = productos.getValueAt(fila, 4).toString();
                                java.sql.Timestamp fechaT = new java.sql.Timestamp(System.currentTimeMillis());
                                InsertarTransaccionNueva(oProductos.getProducto(), null,
                                        new BigDecimal(0), cantidad, uni, "Venta", null, fechaT);
                                oA.aviso("Stock modificado correctamente");
                                Limpiar();
                                MostrarProductos();
                            }
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        oA.manejarErrorConexion(clase, ex);
                    }
                } else {
                    oA.mostrarError(clase, "Agregue stock primero.", null);
                }

            } else {
                oA.mostrarError(clase, "Seleccione una fila primero.", null);
            }
        });

        eliminarItem.addActionListener((ActionEvent e) -> {
            int fila = productos.getSelectedRow();
            if (fila != -1) {
                int confirmación = oA.confirmación("¿Está seguro de eliminar?");
                if (confirmación == 0) {
                    try {
                        String ID = productos.getValueAt(fila, 0).toString();
                        oProductos.setIdProducto(Integer.parseInt(ID));
                        oProductos.EliminarProducto();
                        oA.aviso("Producto eliminado.");
                        Limpiar();
                        MostrarProductos();
                        MostrarCategorias();
                    } catch (SQLException | ClassNotFoundException ex) {
                        oA.manejarErrorConexion(clase, ex);
                    }
                }
            } else {
                oA.mostrarError(clase, "Seleccione una fila primero.", null);
            }
        });

        verInfoItem.addActionListener((ActionEvent e) -> {
            int fila = productos.getSelectedRow();
            if (fila != -1) {
                try {
                    String nameProducto = productos.getValueAt(fila, 1).toString();
                    oT.setIdProducto(nameProducto);
                    new mTransaccion(oT).loadFrame();
                } catch (ClassNotFoundException | SQLException ex) {
                    oA.manejarErrorConexion(clase, ex);
                }
            } else {
                oA.mostrarError(clase, "Seleccione una fila primero.", null);
            }
        });
    }

    boolean E(JTextField tf) {
        return tf.getText().isEmpty();
    }

    boolean Validar() {
        boolean rbECS = rbElegir1.isSelected(); //Seleccionar categoria
        boolean rbEPS = rbElegir2.isSelected(); //Seleccionar producto
        boolean rbeCS = rbEscribir1.isSelected(); //Ingresar categoria
        boolean rbePS = rbEscribir2.isSelected(); //Ingresar producto
        boolean cS = cbCtg.getSelectedIndex() != -1;
        boolean pS = cbProd.getSelectedIndex() != -1;
        boolean ppS = cbProv.getSelectedIndex() != -1;
        boolean txtVacios = E(stock) && E(tipo) && E(importe) && E(fecha) && (cbProv.getSelectedIndex() == -1);
        boolean txtNoVacios = E(stock) || E(tipo) || E(importe) || E(fecha) || (cbProv.getSelectedIndex() == -1);
        boolean txtLlenos = !E(stock) && !E(tipo) && !E(importe) && !E(fecha) && (cbProv.getSelectedIndex() != -1);
        boolean comb = (rbECS && rbEPS) || (rbECS && rbePS) || (rbeCS && rbePS);

        if (comb && txtVacios) {
            return true; //Para crear un nuevo producto
        } else if ((comb && txtNoVacios) || (comb && txtLlenos)) {
            if (rbECS && rbEPS) { //Agregar stock de producto existente
                if (!cS) {
                    oA.mostrarError(clase, "Seleccione una categoría.", null);
                } else if (!pS) {
                    oA.mostrarError(clase, "Seleccione un producto.", null);
                } else if (E(stock)) {
                    oA.mostrarError(clase, "Ingrese stock de producto.", null);
                } else if (E(tipo)) {
                    oA.mostrarError(clase, "Ingrese unidad de medida.", null);
                } else if (E(importe)) {
                    oA.mostrarError(clase, "Ingrese el monto.", null);
                } else if (E(fecha)) {
                    oA.mostrarError(clase, "Especifique fecha de caducidad.", null);
                } else if (!ppS) {
                    oA.mostrarError(clase, "Seleccione un proveedor.", null);
                } else {
                    return true;
                }
            } else if (rbECS && rbePS) { //Agregar stock de nuevo producto de categoria existente
                if (!cS) {
                    oA.mostrarError(clase, "Seleccione una categoría.", null);
                } else if (E(producto)) {
                    oA.mostrarError(clase, "Ingrese un producto.", null);
                } else if (E(stock)) {
                    oA.mostrarError(clase, "Ingrese stock de producto.", null);
                } else if (E(tipo)) {
                    oA.mostrarError(clase, "Ingrese unidad de medida.", null);
                } else if (E(importe)) {
                    oA.mostrarError(clase, "Ingrese el monto.", null);
                } else if (E(fecha)) {
                    oA.mostrarError(clase, "Especifique fecha de caducidad.", null);
                } else if (!ppS) {
                    oA.mostrarError(clase, "Seleccione un proveedor.", null);
                } else {
                    return true;
                }
            } else { //Agregar stock de nuevo producto de nueva categoria
                if (E(categoria)) {
                    oA.mostrarError(clase, "Ingrese una categoría.", null);
                } else if (E(producto)) {
                    oA.mostrarError(clase, "Ingrese un producto.", null);
                } else if (E(stock)) {
                    oA.mostrarError(clase, "Ingrese stock de producto.", null);
                } else if (E(tipo)) {
                    oA.mostrarError(clase, "Ingrese unidad de medida.", null);
                } else if (E(importe)) {
                    oA.mostrarError(clase, "Ingrese el monto.", null);
                } else if (E(fecha)) {
                    oA.mostrarError(clase, "Especifique fecha de caducidad.", null);
                } else if (!ppS) {
                    oA.mostrarError(clase, "Seleccione un proveedor.", null);
                } else {
                    return true;
                }
            }
        } else if (!comb && txtLlenos) {
            oA.mostrarError(clase, "Faltan datos", null);
        } else {
            oA.mostrarError(clase, "Faltan datos", null);
        }
        return false;
    }

    boolean OnlyProduc() {

        procede = false;
        boolean ctgS = cbCtg.getSelectedIndex() != -1; //Categorias
        boolean pdtS = cbProd.getSelectedIndex() != -1; //Productos
        boolean rbECS = rbElegir1.isSelected(); //Seleccionar categoria
        boolean rbEPS = rbElegir2.isSelected(); //Seleccionar producto
        boolean rbeCS = rbEscribir1.isSelected(); //Ingresar categoria
        boolean rbePS = rbEscribir2.isSelected(); //Ingresar producto
        boolean txtVacios = E(stock) && E(tipo) && E(importe) && E(fecha) && (cbProv.getSelectedIndex() == -1);

        if (rbECS && rbEPS && txtVacios) {
            if (!ctgS) {
                oA.mostrarError(clase, "Seleccione una categoría", null);
            } else if (!pdtS) {
                oA.mostrarError(clase, "Seleccione un producto", null);
            } else {
                oA.mostrarError(clase, "El producto ya existe.", null);
            }
        } else if (rbECS && rbePS && txtVacios) { //Nuevo producto de categoria existente
            if (!ctgS) {
                oA.mostrarError(clase, "Seleccione una categoría", null);
            } else if (producto.getText().isEmpty()) {
                oA.mostrarError(clase, "Ingrese un producto", null);
            } else {
                procede = true;
                return true;
            }
        } else if (rbeCS && rbePS && txtVacios) { //Nuevo producto de nueva categoria
            if (E(categoria)) {
                oA.mostrarError(clase, "Ingrese una categoría", null);
            } else if (E(producto)) {
                oA.mostrarError(clase, "Ingrese un producto", null);
            } else {
                procede = true;
                return true;
            }
        } else {
            procede = true;
            return false;
        }
        return false;
    }

    void Limpiar() {
        bg1.clearSelection();
        bg2.clearSelection();
        bg3.clearSelection();
        rbElegir1.setEnabled(true);
        rbEscribir1.setEnabled(true);
        rbEscribir2.setEnabled(true);
        rbElegir2.setEnabled(false);
        producto.setEnabled(false);
        cbProd.setEnabled(false);
        cbCtg.setEnabled(false);
        categoria.setText(null);
        producto.setText(null);
        stock.setText(null);
        importe.setText(null);
        fecha.setText(null);
        tipo.setText(null);
        cbCtg.setSelectedIndex(-1);
        cbProd.setSelectedIndex(-1);
        cbProv.setSelectedIndex(-1);
    }

    @Override
    public void loadFrame() {
        try {
            fAP.setVisible(true);
            MostrarProductos();
            fAP.setLocationRelativeTo(null);
            fAP.setTitle("Productos");
            fAP.setResizable(false);
            MostrarProveedores();
            MostrarCategorias();
            opciones();
            MouseListeners();
            KeyListeners();
            close();
        } catch (SQLException | ClassNotFoundException ex) {
            oA.manejarErrorConexion(clase, ex);
        }
    }

    @Override
    public void close() {
        fAP.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (oA.confirmación("¿Salir?") == 0) {
                    fAP.dispose();
                    new MenuService().loadFrame();
                }
            }
        });
    }
}
