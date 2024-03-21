package com.tienda.service_layer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author VILLA
 */
public class ProductosFrame extends javax.swing.JFrame {

    private static ProductosFrame instancia;
    
    public ProductosFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtCantidad = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox<>();
        btninicio = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        tbnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 530));

        txtCantidad.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 260, 50));

        txtProducto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 260, 50));

        txtTipo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 260, 50));

        jLabel5.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CANTIDAD");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 110, 80));

        jLabel6.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PRODUCTO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 110, 80));

        jLabel7.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("TIPO:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 110, 80));

        jLabel8.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("PROVEEDOR:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 110, 80));

        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<SELECCIONAR>" }));
        jPanel1.add(cbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 260, 50));

        btninicio.setText("INICIO");
        jPanel1.add(btninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, 120, 40));

        btnAdd.setText("AÑADIR");
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 150, 50));

        btnEdit.setText("EDITAR");
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 350, 150, 50));

        tbnDelete.setText("ELIMINAR");
        jPanel1.add(tbnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 150, 50));

        btnFind.setText("BUSCAR");
        jPanel1.add(btnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, 150, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static ProductosFrame getProcFrame(){
        if (instancia == null){
            instancia = new ProductosFrame();
        }
        return instancia;
    }
    
    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButton btnEdit) {
        this.btnEdit = btnEdit;
    }

    public JButton getBtnFind() {
        return btnFind;
    }

    public void setBtnFind(JButton btnFind) {
        this.btnFind = btnFind;
    }

    public JButton getBtninicio() {
        return btninicio;
    }

    public void setBtninicio(JButton btninicio) {
        this.btninicio = btninicio;
    }

    public JComboBox<String> getCbProveedor() {
        return cbProveedor;
    }

    public void setCbProveedor(JComboBox<String> cbProveedor) {
        this.cbProveedor = cbProveedor;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JButton getTbnDelete() {
        return tbnDelete;
    }

    public void setTbnDelete(JButton tbnDelete) {
        this.tbnDelete = tbnDelete;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(JTextField txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public JTextField getTxtProducto() {
        return txtProducto;
    }

    public void setTxtProducto(JTextField txtProducto) {
        this.txtProducto = txtProducto;
    }

    public JTextField getTxtTipo() {
        return txtTipo;
    }

    public void setTxtTipo(JTextField txtTipo) {
        this.txtTipo = txtTipo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btninicio;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JButton tbnDelete;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
