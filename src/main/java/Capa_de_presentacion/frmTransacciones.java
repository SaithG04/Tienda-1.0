package Capa_de_presentacion;

import Capa_de_logica_de_negocio.CommonUtilities;
import java.awt.Image;
import javax.swing.JTable;

/**
 *
 * @author isai_
 */
public class frmTransacciones extends javax.swing.JFrame {

    public frmTransacciones() {
        initComponents();
    }
    
    @Override
    public Image getIconImage() {
       //Icono del programa
       return CommonUtilities.IMG;
    }

    public JTable getJtbTransacciones() {
        return jtbTransacciones;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbTransacciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Transacciones");
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtbTransacciones.setBackground(new java.awt.Color(255, 255, 255));
        jtbTransacciones.setForeground(new java.awt.Color(0, 0, 0));
        jtbTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Transaccion", "Monto", "Cantidad", "Unidad", "Proveedor", "Fecha Caducidad", "Fecha Transaccion", "Usuario"
            }
        ));
        jScrollPane1.setViewportView(jtbTransacciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 810, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbTransacciones;
    // End of variables declaration//GEN-END:variables
}
