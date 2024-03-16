package Capa_de_presentacion;

import Capa_de_logica_de_negocio.CommonUtilities;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class frmAdministradorProductos extends javax.swing.JFrame { 
    
    public frmAdministradorProductos() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
       //Icono del programa
       return CommonUtilities.IMG;
    }
    
    public ButtonGroup getbGrupo1() {
        return bGrupo1;
    }

    public ButtonGroup getbGrupo2() {
        return bGrupo2;
    }

    public ButtonGroup getbGrupo3() {
        return bGrupo3;
    }

    public JButton getBtnRProducto() {
        return btnRProducto;
    }

    public JComboBox<String> getCbCategoria() {
        return cbCategoria;
    }

    public JComboBox<String> getCbProducto() {
        return cbProducto;
    }

    public JComboBox<String> getCbProveedor() {
        return cbProveedor;
    }

    public JTable getJtbProductos() {
        return jtbProductos;
    }

    public JPopupMenu getPmOpciones() {
        return pmOpciones;
    }

    public JRadioButton getRbElegir1() {
        return rbElegir1;
    }

    public JRadioButton getRbElegir2() {
        return rbElegir2;
    }

    public JRadioButton getRbEscribir1() {
        return rbEscribir1;
    }

    public JRadioButton getRbEscribir2() {
        return rbEscribir2;
    }

    public JTextField getTxtStock() {
        return txtStock;
    }

    public JTextField getTxtCategoria() {
        return txtCategoria;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTextField getTxtImporte() {
        return txtImporte;
    }

    public JTextField getTxtProducto() {
        return txtProducto;
    } 

    public JTextField getTxtTipo() {
        return txtTipo;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGrupo1 = new javax.swing.ButtonGroup();
        bGrupo2 = new javax.swing.ButtonGroup();
        bGrupo3 = new javax.swing.ButtonGroup();
        pmOpciones = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        txtStock = new javax.swing.JTextField();
        cbProducto = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbEscribir1 = new javax.swing.JRadioButton();
        rbElegir1 = new javax.swing.JRadioButton();
        txtCategoria = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rbEscribir2 = new javax.swing.JRadioButton();
        rbElegir2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProductos = new javax.swing.JTable();
        btnRProducto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStock.setBackground(new java.awt.Color(153, 204, 255));
        txtStock.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 240, 30));

        cbProducto.setBackground(new java.awt.Color(153, 204, 255));
        cbProducto.setForeground(new java.awt.Color(0, 0, 0));
        cbProducto.setEnabled(false);
        jPanel1.add(cbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 243, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PRODUCTO:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 88, -1));

        txtProducto.setBackground(new java.awt.Color(153, 204, 255));
        txtProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtProducto.setEnabled(false);
        jPanel1.add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 94, 240, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CATEGORÍA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 88, 29));

        rbEscribir1.setBackground(new java.awt.Color(0, 0, 0));
        bGrupo1.add(rbEscribir1);
        rbEscribir1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbEscribir1.setForeground(new java.awt.Color(255, 255, 255));
        rbEscribir1.setText("Nuevo");
        rbEscribir1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(rbEscribir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        rbElegir1.setBackground(new java.awt.Color(0, 0, 0));
        bGrupo1.add(rbElegir1);
        rbElegir1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbElegir1.setForeground(new java.awt.Color(255, 255, 255));
        rbElegir1.setText("Seleccionar");
        rbElegir1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(rbElegir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, 40));

        txtCategoria.setBackground(new java.awt.Color(153, 204, 255));
        txtCategoria.setForeground(new java.awt.Color(0, 0, 0));
        txtCategoria.setEnabled(false);
        jPanel1.add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 34, 240, 30));

        cbCategoria.setBackground(new java.awt.Color(153, 204, 255));
        cbCategoria.setForeground(new java.awt.Color(0, 0, 0));
        cbCategoria.setEnabled(false);
        jPanel1.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 243, 40));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TIPO DE UNIDAD:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 130, 35));

        rbEscribir2.setBackground(new java.awt.Color(0, 0, 0));
        bGrupo2.add(rbEscribir2);
        rbEscribir2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbEscribir2.setForeground(new java.awt.Color(255, 255, 255));
        rbEscribir2.setText("Nuevo");
        rbEscribir2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(rbEscribir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        rbElegir2.setBackground(new java.awt.Color(0, 0, 0));
        bGrupo2.add(rbElegir2);
        rbElegir2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rbElegir2.setForeground(new java.awt.Color(255, 255, 255));
        rbElegir2.setText("Seleccionar");
        rbElegir2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rbElegir2.setEnabled(false);
        jPanel1.add(rbElegir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, 40));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PROVEEDOR:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 259, -1, 40));

        cbProveedor.setBackground(new java.awt.Color(153, 204, 255));
        cbProveedor.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(cbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 243, 40));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("STOCK:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 30));

        txtImporte.setBackground(new java.awt.Color(153, 204, 255));
        txtImporte.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 240, 30));

        jtbProductos.setBackground(new java.awt.Color(102, 153, 255));
        jtbProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Categoría", "Stock", "Unidad"
            }){
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class,
                    java.lang.String.class};

                boolean[] canEdit = new boolean [] {
                    false, false, false, false,false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            }
        );
        jtbProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbProductos.setAutoscrolls(false);
        jScrollPane1.setViewportView(jtbProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 890, 350));

        btnRProducto.setBackground(new java.awt.Color(153, 204, 255));
        btnRProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnRProducto.setText("REGISTRAR PRODUCTO");
        jPanel1.add(btnRProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 400, 44));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FECHA CADUCIDAD:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, 30));

        txtFecha.setBackground(new java.awt.Color(153, 204, 255));
        txtFecha.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 200, 30));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IMPORTE:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 30));

        txtTipo.setBackground(new java.awt.Color(153, 204, 255));
        txtTipo.setForeground(new java.awt.Color(0, 0, 0));
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, 150, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/IMG-20211202-WA0091.jpg"))); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(960, 690));
        jLabel9.setRequestFocusEnabled(false);
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setPreferredSize(new java.awt.Dimension(960, 690));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGrupo1;
    private javax.swing.ButtonGroup bGrupo2;
    private javax.swing.ButtonGroup bGrupo3;
    private javax.swing.JButton btnRProducto;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbProducto;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProductos;
    private javax.swing.JPopupMenu pmOpciones;
    private javax.swing.JRadioButton rbElegir1;
    private javax.swing.JRadioButton rbElegir2;
    private javax.swing.JRadioButton rbEscribir1;
    private javax.swing.JRadioButton rbEscribir2;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
