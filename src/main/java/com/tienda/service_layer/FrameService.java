package com.tienda.service_layer;

import java.awt.Component;

/**
 * La interfaz FrameService proporciona métodos para cerrar y cargar un marco
 * (frame).
 */
public interface FrameService<T> {

    /**
     * Método para cargar el marco.
     */
    T GetInstanceOfFrame();
    
    void CargarActionListeners();

    void CargarKeyListeners();

    void CargarMouseListeners();

    void QuitActionListeners();

    void QuitKeyListener(Component componente);

    void QuitMouseListener(Component componente);

}
