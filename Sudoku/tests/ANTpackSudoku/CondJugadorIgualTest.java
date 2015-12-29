/**
 * 
 */
package ANTpackSudoku;

import ANTpackSudokuAntiguo.CondJugadorIgual;
import ANTpackSudokuAntiguo.Jugador;
import junit.framework.TestCase;

/**
 * @author mikel
 *
 */
public class CondJugadorIgualTest extends TestCase
{
    private Jugador jugador1 = null;
    private Jugador jugador2 = null;
    private Jugador jugador3 = null;
    private Jugador jugador4 = null;
    private Jugador jugador1Bis = null;
    private Jugador jugador1Bis2 = null;
    
    private CondJugadorIgual condJugador1 = null;
 

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	jugador1 = new Jugador("Jugador1");
	jugador1.setPuntos(15);
	jugador2 = new Jugador("Jugador2");
	jugador2.setPuntos(15);
	jugador3 = new Jugador("Jugador3");
	jugador3.setPuntos(10);
	jugador4 = new Jugador("Jugador4");
	jugador4.setPuntos(20);
	jugador1Bis = new Jugador("Jugador1");
	jugador1Bis.setPuntos(15);
	jugador1Bis2 = new Jugador("Jugador1");
	jugador1Bis2.setPuntos(20);
	condJugador1 = new CondJugadorIgual(jugador1);
	
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.CondJugadorIgual#CondJugadorIgual(ANTpackSudokuAntiguo.Jugador)}.
     */
    public void testCondJugadorIgual()
    {
	assertNotNull(condJugador1);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.CondJugadorIgual#satisfaceCondicion(ANTpackSudokuAntiguo.Jugador)}.
     */
    public void testSatisfaceCondicion()
    {
	assertTrue(condJugador1.satisfaceCondicion(jugador1));
	assertTrue(condJugador1.satisfaceCondicion(jugador1Bis));
	assertFalse(condJugador1.satisfaceCondicion(jugador1Bis2));
	assertFalse(condJugador1.satisfaceCondicion(jugador2));
	assertFalse(condJugador1.satisfaceCondicion(jugador3));
	assertFalse(condJugador1.satisfaceCondicion(jugador4));
     }

}
