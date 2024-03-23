/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
