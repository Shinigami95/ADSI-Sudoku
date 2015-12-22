/**
 * 
 */
package packExcepciones;

/**
 * @author mikel
 *
 */
public class ExcepcionNoHaySudokuCargado extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 6867141442404497515L;

    /**
     * 
     */
    public ExcepcionNoHaySudokuCargado()
    {
    }

    /**
     * @param pArg0
     */
    public ExcepcionNoHaySudokuCargado(String pArg0)
    {
	super(pArg0);
    }

    /**
     * @param pArg0
     */
    public ExcepcionNoHaySudokuCargado(Throwable pArg0)
    {
	super(pArg0);
    }

    /**
     * @param pArg0
     * @param pArg1
     */
    public ExcepcionNoHaySudokuCargado(String pArg0, Throwable pArg1)
    {
	super(pArg0, pArg1);
    }

}
