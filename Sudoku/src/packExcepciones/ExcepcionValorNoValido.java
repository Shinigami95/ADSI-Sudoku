/**
 * 
 */
package packExcepciones;

/**
 * @author mikel
 *
 */
public class ExcepcionValorNoValido extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 849643703760578333L;

    /**
     * 
     */
    public ExcepcionValorNoValido()
    {
    }

    /**
     * @param pMessage
     */
    public ExcepcionValorNoValido(String pMessage)
    {
	super(pMessage);
    }

    /**
     * @param pCause
     */
    public ExcepcionValorNoValido(Throwable pCause)
    {
	super(pCause);
    }

    /**
     * @param pMessage
     * @param pCause
     */
    public ExcepcionValorNoValido(String pMessage, Throwable pCause)
    {
	super(pMessage, pCause);
    }

}
