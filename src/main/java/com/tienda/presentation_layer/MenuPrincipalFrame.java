package com.tienda.presentation_layer;

import com.tienda.utilities.ServiceUtilities;
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

        container = new javax.swing.JPanel();
        btnTransacciones = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnProveedores = new javax.swing.JButton();
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

        container.setBackground(new java.awt.Color(153, 255, 204));
        container.setMinimumSize(new java.awt.Dimension(850, 550));
        container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTransacciones.setBackground(new java.awt.Color(0, 0, 0));
        btnTransacciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sistema-de-gestion-de-contenidos.png"))); // NOI18N
        btnTransacciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        btnTransacciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        container.add(btnTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 250, 140));

        lblTitle.setBackground(new java.awt.Color(0, 204, 204));
        lblTitle.setFont(new java.awt.Font("Swis721 LtCn BT", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Has iniciado sesión como:");
        lblTitle.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblTitle.setOpaque(true);
        container.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 810, 40));

        btnProveedores.setBackground(new java.awt.Color(0, 0, 0));
        try {
            // Obtiene la ruta absoluta del archivo de imagen
            java.nio.file.Path path = java.nio.file.Paths.get("D:", "Documentos", "NetBeansProjects", "ElAlgarrobo1.0", "src", "main", "java", "Capa_de_recursos", "Imagenes", "proveedor.png");
            // Crea un URL a partir de la ruta del archivo
            java.net.URL imageUrl = path.toUri().toURL();
            // Establece el icono del botón con la imagen cargada desde el URL
            btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proveedor.png"))); // NOI18N
        } catch (java.net.MalformedURLException e) {
            // Maneja el error de URL mal formado
            e.printStackTrace();
        }
        btnProveedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        container.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 250, 140));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Swis721 Cn BT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PROVEEDORES");
        container.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 167, 25));

        btnProductos.setBackground(new java.awt.Color(0, 0, 0));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paquete.png"))); // NOI18N
        btnProductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        container.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 250, 140));

        jLabel6.setFont(new java.awt.Font("Swis721 Cn BT", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PRODUCTOS");
        container.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, 25));

        btnCerrarSesion.setBackground(new java.awt.Color(0, 0, 0));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha.png"))); // NOI18N
        btnCerrarSesion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        container.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 250, 140));

        jLabel2.setFont(new java.awt.Font("Swis721 Cn BT", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TRANSACCIONES");
        container.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 167, 30));

        jLabel10.setFont(new java.awt.Font("Swis721 Cn BT", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CERRAR SESIÓN");
        container.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 167, 30));

        btnUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profesionales.png"))); // NOI18N
        btnUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        btnUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        container.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 250, 140));

        jLabel11.setFont(new java.awt.Font("Swis721 Cn BT", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("USUARIOS");
        container.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 167, 30));

        getContentPane().add(container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 551));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @Override
    public Image getIconImage() {
        //Icono del programa
        return ServiceUtilities.IMG;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public JButton getBtnProductos() {
        return btnProductos;
    }

    public JButton getBtnProveedores() {
        return btnProveedores;
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
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JPanel container;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
