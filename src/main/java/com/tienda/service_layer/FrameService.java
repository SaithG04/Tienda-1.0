package com.tienda.service_layer;

/**
 * La interfaz FrameService proporciona métodos para cerrar y cargar un marco
 * (frame).
 */
public interface FrameService {

    /**
     * Método para cerrar el marco.
     */
    void close();

    /**
     * Método para cargar el marco.
     */
    void loadFrame();
}
