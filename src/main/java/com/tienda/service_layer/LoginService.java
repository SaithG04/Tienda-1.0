package com.tienda.service_layer;

import com.tienda.presentation_layer.LoginFrame;
import java.awt.AWTEvent;

/**
 * La interfaz LoginService extiende FrameService y proporciona un método para iniciar sesión.
 * 
 * @author isai_
 */
public interface LoginService extends FrameService<LoginFrame> {

    /**
     * Método para iniciar sesión.
     * @param evt
     */
    void IniciarSesion(AWTEvent evt);

}
