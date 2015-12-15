/**
 * 
 */
package packSudokuAntiguo;

import java.util.Comparator;

public class ComparadorSudokus implements Comparator<Sudoku>
{

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Sudoku pO1, Sudoku pO2)
    {
	return pO2.getDificultad() -pO1.getDificultad();
    }

}
