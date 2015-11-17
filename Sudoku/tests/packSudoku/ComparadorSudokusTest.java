/**
 * 
 */
package packSudoku;

import junit.framework.TestCase;

/**
 * @author mikel
 *
 */
public class ComparadorSudokusTest extends TestCase
{

    private ComparadorSudokus compSudokus = null;
    private Sudoku sudoku1 = null;
    private Sudoku sudoku2 = null;
    private Sudoku sudoku3 = null;
    
    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	compSudokus = new  ComparadorSudokus();
	sudoku1 = new Sudoku("Sudoku1",1);
	sudoku2 = new Sudoku("Sudoku2",2);
	sudoku3 = new Sudoku("Sudoku3",3);
    }

    /**
     * Test method for {@link packSudoku.ComparadorSudokus#compare(packSudoku.Sudoku, packSudoku.Sudoku)}.
     */
    public void testCompare()
    {
	assertEquals(0, compSudokus.compare(sudoku1, sudoku1));
	assertTrue(compSudokus.compare(sudoku1, sudoku2)>0);
	assertTrue(compSudokus.compare(sudoku2, sudoku1)<0);
	assertTrue(compSudokus.compare(sudoku2, sudoku3)>0);
	assertTrue(compSudokus.compare(sudoku3, sudoku2)<0);
	assertTrue(compSudokus.compare(sudoku1, sudoku3)>0);
	assertTrue(compSudokus.compare(sudoku3, sudoku1)<0);	
    }
}
