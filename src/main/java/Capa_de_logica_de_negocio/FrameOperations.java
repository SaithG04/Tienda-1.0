package Capa_de_logica_de_negocio;

/**
 *
 * @author isai_ The FrameOperations interface defines common operations for
 * working with frames. Any class implementing this interface should provide
 * implementations for the methods.
 */
public interface FrameOperations {

    /**
     * Close the frame or application.
     */
    void close();

    /**
     * Load the frame.
     */
    void loadFrame();
}
