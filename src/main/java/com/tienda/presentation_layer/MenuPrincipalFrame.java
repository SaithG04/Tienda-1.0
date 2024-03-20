package com.tienda.presentation_layer;

import com.tienda.utilities.CommonUtilities;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author isai_
 */
public class MenuPrincipalFrame extends javax.swing.JFrame {

    private static volatile MenuPrincipalFrame instance;

    private MenuPrincipalFrame() {
        initComponents();
    }

    public static MenuPrincipalFrame getInstance() {
        if (instance == null) {
            synchronized (MenuPrincipalFrame.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new MenuPrincipalFrame();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnTransacciones = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnVendedores = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnProductos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnUsuarios = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(" Administrador");
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 153));
        jPanel2.setMinimumSize(new java.awt.Dimension(850, 550));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTransacciones.setBackground(new java.awt.Color(0, 0, 0));
        btnTransacciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTransacciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 250, 140));

        lblTitle.setBackground(new java.awt.Color(255, 153, 51));
        lblTitle.setFont(new java.awt.Font("Swis721 BT", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Has iniciado sesión como:");
        lblTitle.setOpaque(true);
        jPanel2.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 810, 40));

        btnVendedores.setBackground(new java.awt.Color(51, 153, 255));
        try {
            // Obtiene la ruta absoluta del archivo de imagen
            java.nio.file.Path path = java.nio.file.Paths.get("D:", "Documentos", "NetBeansProjects", "ElAlgarrobo1.0", "src", "main", "java", "Capa_de_recursos", "Imagenes", "proveedor.png");
            // Crea un URL a partir de la ruta del archivo
            java.net.URL imageUrl = path.toUri().toURL();
            // Establece el icono del botón con la imagen cargada desde el URL
        } catch (java.net.MalformedURLException e) {
            // Maneja el error de URL mal formado
            e.printStackTrace();
        }
        btnVendedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVendedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnVendedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 250, 140));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Swis721 BT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("VENDEDORES");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 167, 25));

        btnProductos.setBackground(new java.awt.Color(102, 51, 255));
        btnProductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 250, 140));

        jLabel6.setFont(new java.awt.Font("Swis721 BT", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PRODUCTOS");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, 25));

        btnCerrarSesion.setBackground(new java.awt.Color(102, 51, 255));
        btnCerrarSesion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 250, 145));

        jLabel2.setFont(new java.awt.Font("Swis721 BT", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TRANSACCIONES");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 167, 30));

        jLabel10.setFont(new java.awt.Font("Swis721 BT", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CERRAR SESIÓN");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 167, 30));

        btnUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 250, 140));

        jLabel11.setFont(new java.awt.Font("Swis721 BT", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("USUARIOS");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 167, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 551));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @Override
    public Image getIconImage() {
        //Icono del programa
        return CommonUtilities.IMG;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public JButton getBtnProductos() {
        return btnProductos;
    }

    public JButton getBtnProveedores() {
        return btnVendedores;
    }

    public JButton getBtnUsuarios() {
        return btnUsuarios;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JButton getBtnTransacciones() {
        return btnTransacciones;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVendedores;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
