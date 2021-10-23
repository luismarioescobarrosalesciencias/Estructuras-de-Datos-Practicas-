/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */
package ed.lineales;

/**
 * Indica tamaños no adecuados para la estructura.
 * @author veronica
 */
public class IllegalSizeException extends RuntimeException {

    /**
     * Constructor.
     */
    public IllegalSizeException() {
        super();
    }

    /**
     * Constructor.
     * @param message Información detallada sobre el contexto en que ocurre la excepción.
     */
    public IllegalSizeException(String message){
        super(message);
    }
}