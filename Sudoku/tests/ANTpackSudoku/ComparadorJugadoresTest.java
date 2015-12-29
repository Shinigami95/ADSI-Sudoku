/**
 * 
 */
package ANTpackSudoku;

import ANTpackSudokuAntiguo.ComparadorJugadores;
import ANTpackSudokuAntiguo.Jugador;
import junit.framework.TestCase;

/**
 * @author mikel
 *
 */
public class ComparadorJugadoresTest extends TestCase
{
    private Jugador jugador1 = null;
    private Jugador jugador2 = null;
    private Jugador jugador3 = null;
    private Jugador jugador4 = null;
    private ComparadorJugadores compJugadores = null;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	compJugadores = new ComparadorJugadores();
	
	jugador1 = new Jugador("Jugador1");
	jugador1.setPuntos(15);
	jugador2 = new Jugador("Jugador2");
	jugador2.setPuntos(15);
	jugador3 = new Jugador("Jugador3");
	jugador3.setPuntos(10);
	jugador4 = new Jugador("Jugador4");
	jugador4.setPuntos(20);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.ComparadorJugadores#compare(ANTpackSudokuAntiguo.Jugador, ANTpackSudokuAntiguo.Jugador)}.
     */
    public void testCompare()
    {
	assertEquals(0, compJugadores.compare(jugador1, jugador1));
	assertTrue(compJugadores.compare(jugador1, jugador2)>0);
	assertTrue(compJugadores.compare(jugador2, jugador1)<0);
	assertTrue(compJugadores.compare(jugador1, jugador3)>0);
	assertTrue(compJugadores.compare(jugador3, jugador1)<0);
	assertTrue(compJugadores.compare(jugador4, jugador1)>0);
	assertTrue(compJugadores.compare(jugador1, jugador4)<0);
    }

}
