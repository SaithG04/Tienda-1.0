package com.tienda.service_layer;

import com.tienda.presentation_layer.LoginFrame;

/**
 * La interfaz LoginService extiende FrameService y proporciona un método para
 * iniciar sesión.
 */
public interface LoginService extends FrameService<LoginFrame>{

    /**
     * Método para iniciar sesión.
     */
    void IniciarSesion();

}
