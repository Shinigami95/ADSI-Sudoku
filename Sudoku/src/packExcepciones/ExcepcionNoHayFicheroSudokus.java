/**
 * 
 */
package packExcepciones;

/**
 * @author mikel
 * 
 */
public class ExcepcionNoHayFicheroSudokus extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1056741812444225627L;

    /**
     * 
     */
    public ExcepcionNoHayFicheroSudokus() {
    }

    /**
     * @param pMessage
     */
    public ExcepcionNoHayFicheroSudokus(String pMessage) {
	super(pMessage);
    }

    /**
     * @param pCause
     */
    public ExcepcionNoHayFicheroSudokus(Throwable pCause) {
	super(pCause);
    }

    /**
     * @param pMessage
     * @param pCause
     */
    public ExcepcionNoHayFicheroSudokus(String pMessage, Throwable pCause) {
	super(pMessage, pCause);
    }

}
