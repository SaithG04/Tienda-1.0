package com.tienda.presentation_layer;

import com.tienda.utilities.ServiceUtilities;
import java.awt.*;
import javax.swing.*;

/**
 * La clase LoginFrame representa la ventana de inicio de sesión del sistema.
 * 
 * @author isai_
 */
public class LoginFrame extends javax.swing.JFrame {

    public static volatile LoginFrame instance;

    /**
     * Constructor privado para evitar la instanciación directa de la clase.
     */
    private LoginFrame() {
        initComponents();
    }

    /**
     * Método estático para obtener una única instancia de la clase LoginFrame
     * (patrón Singleton).
     *
     * @return Instancia única de LoginFrame.
     */
    public static LoginFrame getInstance() {
        if (instance == null) {
            synchronized (LoginFrame.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new LoginFrame();
                }
            }
        }
        return instance;
    }

    /**
     * Método para obtener el icono de la ventana.
     *
     * @return Imagen del icono de la ventana.
     */
    @Override
    public Image getIconImage() {
        //Icono del programa
        return ServiceUtilities.IMG;
    }

    /**
     * Método generado automáticamente que inicializa los componentes de la
     * ventana.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Container = new javax.swing.JPanel();
        javax.swing.JLabel lblRecuperar3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Acceder");
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Container.setBackground(new java.awt.Color(153, 255, 204));
        Container.setPreferredSize(new java.awt.Dimension(850, 550));
        Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRecuperar3.setBackground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setFont(new java.awt.Font("BankGothic Lt BT", 1, 12)); // NOI18N
        lblRecuperar3.setForeground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecuperar3.setText("Tienda-version-1.1 -SNAPSHOT");
        Container.add(lblRecuperar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 220, 40));

        jLabel3.setBackground(new java.awt.Color(0, 204, 204));
        jLabel3.setFont(new java.awt.Font("Swis721 Cn BT", 1, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGISTRO DEL SISTEMA");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);
        Container.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 830, 90));

        jLabel5.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("USUARIO:");
        Container.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 120, 60));

        jLabel6.setFont(new java.awt.Font("Swis721 BT", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("CONTRASEÑA:");
        Container.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, -1, 60));

        txtUsuario.setBackground(new java.awt.Color(0, 102, 102));
        txtUsuario.setFont(new java.awt.Font("Swis721 Ex BT", 0, 13)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(153, 255, 204));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Container.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 270, 60));

        txtContraseña.setBackground(new java.awt.Color(0, 102, 102));
        txtContraseña.setFont(new java.awt.Font("Swis721 Ex BT", 0, 10)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(153, 255, 204));
        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContraseña.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Container.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 270, 60));

        btnAceptar.setBackground(new java.awt.Color(0, 102, 102));
        btnAceptar.setFont(new java.awt.Font("Swis721 BT", 1, 15)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(204, 255, 204));
        btnAceptar.setText("ACEPTAR");
        btnAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Container.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, 330, 58));

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setFont(new java.awt.Font("Swis721 BT", 1, 15)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(204, 255, 204));
        btnSalir.setText("SALIR");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Container.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 330, 58));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grupo.png"))); // NOI18N
        Container.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 150, 160));

        getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para obtener el panel principal de la ventana.
     *
     * @return Panel principal de la ventana.
     */
    public JPanel getContainer() {
        return Container;
    }

    /**
     * Método para obtener el botón de "Aceptar".
     *
     * @return Botón de "Aceptar".
     */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    /**
     * Método para obtener el botón de "Salir".
     *
     * @return Botón de "Salir".
     */
    public JButton getBtnSalir() {
        return btnSalir;
    }

    /**
     * Método para obtener el campo de texto de la contraseña.
     *
     * @return Campo de texto de la contraseña.
     */
    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }

    /**
     * Método para obtener el campo de texto del usuario.
     *
     * @return Campo de texto del usuario.
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
