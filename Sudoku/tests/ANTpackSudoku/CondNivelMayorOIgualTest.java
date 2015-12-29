/**
 * 
 */
package ANTpackSudoku;

import ANTpackSudokuAntiguo.CondNivelMayorOIgual;
import ANTpackSudokuAntiguo.Sudoku;
import junit.framework.TestCase;

/**
 * @author mikel
 *
 */
public class CondNivelMayorOIgualTest extends TestCase
{
    private CondNivelMayorOIgual cond1 = null;
    private CondNivelMayorOIgual cond2 = null;
    private CondNivelMayorOIgual cond3 = null;
    
    private Sudoku sudoku1 = null;
    private Sudoku sudoku2 = null;
    private Sudoku sudoku3 = null;
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	sudoku1 = new Sudoku("Sudoku1",1);
	sudoku2 = new Sudoku("Sudoku2",2);
	sudoku3 = new Sudoku("Sudoku3",3);
	
	cond1 = new CondNivelMayorOIgual(1);
	cond2 = new CondNivelMayorOIgual(2);
	cond3 = new CondNivelMayorOIgual(3);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.CondNivelMayorOIgual#CondNivelMayorOIgual(int)}.
     */
    public void testCondNivelMayorOIgual()
    {
	assertNotNull(cond1);
	assertNotNull(cond2);
	assertNotNull(cond3);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.CondNivelMayorOIgual#satisfaceCondicion(ANTpackSudokuAntiguo.Sudoku)}.
     */
    public void testSatisfaceCondicion()
    {
	assertTrue(cond1.satisfaceCondicion(sudoku1));
	assertTrue(cond1.satisfaceCondicion(sudoku2));
	assertTrue(cond1.satisfaceCondicion(sudoku3));
	assertFalse(cond2.satisfaceCondicion(sudoku1));
	assertTrue(cond2.satisfaceCondicion(sudoku2));
	assertTrue(cond2.satisfaceCondicion(sudoku3));
	assertFalse(cond3.satisfaceCondicion(sudoku1));
	assertFalse(cond3.satisfaceCondicion(sudoku2));
	assertTrue(cond3.satisfaceCondicion(sudoku3));
	
    }

}
