package com.tienda.service_layer;

import java.awt.*;

/**
 * La interfaz FrameService proporciona métodos para cerrar y cargar un marco
 * (frame).
 *
 * @author isai_
 */
public interface FrameService {
    /**
     * Método para cargar action listeners en el marco.
     */
    void cargarActionListeners();

    /**
     * Método para cargar key listeners en el marco.
     */
    void cargarKeyListeners();

    /**
     * Método para cargar mouse listeners en el marco.
     */
    void cargarMouseListeners();

    /**
     * Método para quitar action listeners del marco.
     */
    void quitActionListeners();

    /**
     * Método para quitar key listeners de un componente del marco.
     *
     * @param componente El componente del cual quitar los key listeners.
     */
    void quitKeyListener(Component componente);

    /**
     * Método para quitar mouse listeners de un componente del marco.
     *
     * @param componente El componente del cual quitar los mouse listeners.
     */
    void quitMouseListener(Component componente);
}
