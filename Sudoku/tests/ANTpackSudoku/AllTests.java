/**
 * 
 */
package ANTpackSudoku;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author mikel
 *
 */
public class AllTests
{

    /**
     * Suite.
     * 
     * @return the test
     */
    public static Test suite()
    {
	TestSuite suite = new TestSuite("Test for packSudoku");
	//$JUnit-BEGIN$
	suite.addTestSuite(CasillaTest.class);
	suite.addTestSuite(JugadorTest.class);
	suite.addTestSuite(MatrizTest.class);
	suite.addTestSuite(SudokuTest.class);
	suite.addTestSuite(TableroTest.class);
	suite.addTestSuite(ComparadorJugadoresTest.class);
	suite.addTestSuite(ComparadorSudokusTest.class);
	suite.addTestSuite(CondJugadorIgualTest.class);
	suite.addTestSuite(CondNivelMayorOIgualTest.class);
	suite.addTestSuite(ListaJugadoresTest.class);
	suite.addTestSuite(ListaSudokusTest.class);
	//$JUnit-END$
	return suite;
    }

}
