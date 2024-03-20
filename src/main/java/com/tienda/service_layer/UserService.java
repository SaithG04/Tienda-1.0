package com.tienda.service_layer;

import com.tienda.presentation_layer.UsersFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai_
 */
public interface UserService extends FrameService<UsersFrame> {

    void RegistrarUsuario();
    DefaultTableModel CargarUsuarios();
}
