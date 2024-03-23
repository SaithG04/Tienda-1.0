package com.tienda.service_layer;

import com.tienda.presentation_layer.ProductosFrame;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author VILLA
 */
public interface ProductoService extends FrameService<ProductosFrame>{
    DefaultTableModel cargarProducto();
}
