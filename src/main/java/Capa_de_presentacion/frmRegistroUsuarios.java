package Capa_de_presentacion;

import Capa_de_logica_de_negocio.CommonUtilities;
import java.awt.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author isai_
 */
public class frmRegistroUsuarios extends javax.swing.JFrame {
    
    public frmRegistroUsuarios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rbAdmin = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rbUser = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar usuarios");
        setIconImage(getIconImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono.setBackground(new java.awt.Color(102, 102, 255));
        txtTelefono.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefono.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 305, 484, 37));

        jLabel8.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("CORREO:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 372, 103, 29));

        txtCorreo.setBackground(new java.awt.Color(102, 102, 255));
        txtCorreo.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 369, 484, 37));

        txtId.setBackground(new java.awt.Color(102, 102, 255));
        txtId.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 157, 93, 32));

        txtApellidos.setBackground(new java.awt.Color(102, 102, 255));
        txtApellidos.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 250, 484, 37));

        jLabel9.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 158, 103, 29));

        rbAdmin.setBackground(new java.awt.Color(102, 102, 255));
        bGrupo.add(rbAdmin);
        rbAdmin.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        rbAdmin.setForeground(new java.awt.Color(0, 0, 0));
        rbAdmin.setText("Administrador");
        jPanel1.add(rbAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 440, -1, -1));

        jLabel2.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("APELLIDO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 253, 103, 29));

        jLabel3.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NOMBRES:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 204, 103, 29));

        rbUser.setBackground(new java.awt.Color(102, 102, 255));
        bGrupo.add(rbUser);
        rbUser.setFont(new java.awt.Font("Swis721 Lt BT", 0, 14)); // NOI18N
        rbUser.setForeground(new java.awt.Color(0, 0, 0));
        rbUser.setText("Usuario");
        jPanel1.add(rbUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 440, 120, -1));

        jLabel4.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("PERMISOS:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 440, 103, 29));

        txtNombres.setBackground(new java.awt.Color(102, 102, 255));
        txtNombres.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        txtNombres.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombres.setMargin(new java.awt.Insets(0, 100, 0, 0));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 201, 484, 37));

        jLabel7.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("TELEFONO:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 308, 103, 29));

        txtContraseña.setBackground(new java.awt.Color(102, 102, 255));
        txtContraseña.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtContraseña.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 340, 35));

        txtUsuario.setBackground(new java.awt.Color(102, 102, 255));
        txtUsuario.setFont(new java.awt.Font("Swis721 Lt BT", 0, 12)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 487, 340, 35));

        jLabel5.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("USUARIO:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 489, 110, 29));

        btnAceptar.setBackground(new java.awt.Color(102, 102, 255));
        btnAceptar.setFont(new java.awt.Font("Swis721 Ex BT", 1, 24)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(0, 0, 0));
        btnAceptar.setText("REGISTRAR");
        btnAceptar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 604, 201, 40));

        jLabel6.setFont(new java.awt.Font("Swis721 Ex BT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("CONTRASEÑA:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 140, 29));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/profesionales.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 140, 130));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/498015.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 670, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Image getIconImage() {
       //Icono del programa
       return CommonUtilities.IMG;
    }
    
    public ButtonGroup getbGrupo() {
        return bGrupo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JRadioButton getRbAdmin() {
        return rbAdmin;
    }

    public JRadioButton getRbUser() {
        return rbUser;
    }

    public JTextField getTxtApellidos() {
        return txtApellidos;
    }

    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombres() {
        return txtNombres;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGrupo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbAdmin;
    private javax.swing.JRadioButton rbUser;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
