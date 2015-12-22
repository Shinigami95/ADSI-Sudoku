/**
 * 
 */
package packExcepciones;

/**
 * @author mikel
 *
 */
public class ExcepcionCoordenadaNoValida extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 8004815784417016868L;

    /**
     * 
     */
    public ExcepcionCoordenadaNoValida()
    {
    }

    /**
     * @param pMessage
     */
    public ExcepcionCoordenadaNoValida(String pMessage)
    {
	super(pMessage);
    }

    /**
     * @param pCause
     */
    public ExcepcionCoordenadaNoValida(Throwable pCause)
    {
	super(pCause);
    }

    /**
     * @param pMessage
     * @param pCause
     */
    public ExcepcionCoordenadaNoValida(String pMessage, Throwable pCause)
    {
	super(pMessage, pCause);
    }

}
