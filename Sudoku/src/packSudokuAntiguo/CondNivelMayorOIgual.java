/**
 * 
 */
package packSudokuAntiguo;

public class CondNivelMayorOIgual implements ICondicion<Sudoku>
{
    private int nivel;
    public CondNivelMayorOIgual(int pDif)
    {
	nivel = pDif;
    }

    /* (non-Javadoc)
     * @see packSudoku.ICondicion#satisfaceCondicion(java.lang.Object)
     */
    @Override
    public boolean satisfaceCondicion(Sudoku pElem)
    {
	
	return pElem.getDificultad() >= nivel;
    }

}
