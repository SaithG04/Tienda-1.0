package com.tienda.presentation_layer;

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
    
    private ProductosFrame() {
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
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 440, 520));

        txtCantidad.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 260, 50));

        txtProducto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 260, 50));

        txtTipo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 260, 50));

        jLabel5.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CANTIDAD:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 110, 80));

        jLabel6.setBackground(new java.awt.Color(153, 255, 204));
        jLabel6.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PRODUCTO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 110, 80));

        jLabel7.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("UND MEDIDA:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 110, 80));

        jLabel8.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("PROVEEDOR:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 110, 80));

        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<SELECCIONAR>" }));
        jPanel1.add(cbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 260, 50));

        btninicio.setBackground(new java.awt.Color(0, 102, 102));
        btninicio.setForeground(new java.awt.Color(204, 255, 204));
        btninicio.setText("INICIO");
        btninicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btninicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btninicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, 140, 40));

        btnAdd.setBackground(new java.awt.Color(0, 102, 102));
        btnAdd.setForeground(new java.awt.Color(204, 255, 204));
        btnAdd.setText("AÑADIR");
        btnAdd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 140, 40));

        btnEdit.setBackground(new java.awt.Color(0, 102, 102));
        btnEdit.setForeground(new java.awt.Color(204, 255, 204));
        btnEdit.setText("EDITAR");
        btnEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 140, 40));

        tbnDelete.setBackground(new java.awt.Color(0, 102, 102));
        tbnDelete.setForeground(new java.awt.Color(204, 255, 204));
        tbnDelete.setText("ELIMINAR");
        tbnDelete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(tbnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 140, 40));

        jLabel9.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("PRECIO:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, 110, 80));

        txtPrecio.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 260, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 550));

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

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btninicio;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JButton tbnDelete;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
