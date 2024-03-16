package Capa_de_presentacion;

import Capa_de_logica_de_negocio.CommonUtilities;
import java.awt.Image;
import javax.swing.*;

public class frmAdministradorProveedores extends javax.swing.JFrame {

    public frmAdministradorProveedores() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cboDepartamento = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProveedores = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1123, 655));
        setResizable(false);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpiar.setBackground(new java.awt.Color(0, 102, 255));
        btnLimpiar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/limpieza-de-datos.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 146, 42));

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("IdProveedores:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, 20));

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Razón Social:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 130, 29));

        txtId.setBackground(new java.awt.Color(0, 51, 102));
        txtId.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setEnabled(false);
        txtId.setName(""); // NOI18N
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 29, 113, 30));

        txtRazonSocial.setBackground(new java.awt.Color(0, 51, 102));
        txtRazonSocial.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtRazonSocial.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 402, -1));

        jLabel6.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("R.U.C.:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 70, -1));

        txtRUC.setBackground(new java.awt.Color(0, 51, 102));
        txtRUC.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtRUC.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 210, -1));

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dirección:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        txtDireccion.setBackground(new java.awt.Color(0, 51, 102));
        txtDireccion.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 402, 29));

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Contacto:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, -1));

        jLabel5.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Teléfono:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 142, -1));

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Email:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 70, -1));

        jLabel10.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("DEPARTAMENTO:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        txtContacto.setBackground(new java.awt.Color(0, 51, 102));
        txtContacto.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtContacto.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 402, -1));

        txtTelefono.setBackground(new java.awt.Color(0, 51, 102));
        txtTelefono.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 210, -1));

        txtEmail.setBackground(new java.awt.Color(0, 51, 102));
        txtEmail.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 402, -1));

        cboDepartamento.setBackground(new java.awt.Color(0, 51, 102));
        cboDepartamento.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        cboDepartamento.setForeground(new java.awt.Color(255, 255, 255));
        cboDepartamento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(cboDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 168, -1));

        btnAgregar.setBackground(new java.awt.Color(0, 102, 255));
        btnAgregar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/agregar-usuario.png"))); // NOI18N
        btnAgregar.setText("INSERTAR");
        btnAgregar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, 146, 40));

        btnModificar.setBackground(new java.awt.Color(0, 102, 255));
        btnModificar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 146, 42));

        btnEliminar.setBackground(new java.awt.Color(0, 102, 255));
        btnEliminar.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, 146, 42));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Copperplate Gothic Light", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jtbProveedores.setBackground(new java.awt.Color(0, 0, 51));
        jtbProveedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtbProveedores.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jtbProveedores.setForeground(new java.awt.Color(255, 255, 255));
        jtbProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Razon Social", "R.U.C.", "Direccion", "Contacto", "Teléfono", "Email", "Web", "Departamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jtbProveedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbProveedores.setFocusable(false);
        jtbProveedores.setSelectionBackground(new java.awt.Color(153, 255, 153));
        jtbProveedores.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jtbProveedores);
        if (jtbProveedores.getColumnModel().getColumnCount() > 0) {
            jtbProveedores.getColumnModel().getColumn(0).setMinWidth(40);
            jtbProveedores.getColumnModel().getColumn(0).setMaxWidth(40);
            jtbProveedores.getColumnModel().getColumn(2).setMinWidth(101);
            jtbProveedores.getColumnModel().getColumn(2).setMaxWidth(101);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1105, 350));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/819282.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @Override
    public Image getIconImage() {
       //Icono del programa
       return CommonUtilities.IMG;
    }
    public JComboBox<String> getCboDepartamento() {
        return cboDepartamento;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JTable getJtbProveedores() {
        return jtbProveedores;
    }

    public JTextField getTxtContacto() {
        return txtContacto;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtRUC() {
        return txtRUC;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboDepartamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jtbProveedores;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}