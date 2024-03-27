package com.tienda.service_layer;

import com.tienda.presentation_layer.ProveedorFrame;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public interface ProveedorService extends FrameService<ProveedorFrame> {

    DefaultTableModel cargarProveedores();

    void registrarProveedor();

    void actualizarProveedor();

    void eliminarProveedor();
    void autocompletarCampos();
      void limpiarCampos();
     void limpiarCamposSinTabla();
     boolean bloquearMultipleModificacion();
}