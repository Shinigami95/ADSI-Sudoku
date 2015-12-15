/**
 * 
 */
package packSudoku;

import packSudokuAntiguo.Jugador;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class JugadorTest.
 * 
 * @author mikel
 */
public class JugadorTest extends TestCase
{
    
    /** The jugador1. */
    private Jugador jugador1 = null;
    
    /** The jugador2. */
    private Jugador jugador2 = null;
    
    /** The jugador3. */
    private Jugador jugador3 = null;


    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	jugador1 = new Jugador("Jugador1");
	jugador2 = new Jugador("Jugador2");
	jugador3 = new Jugador("Jugador1");
    }

    /**
     * Test method for {@link packSudokuAntiguo.Jugador#Jugador(java.lang.String)}.
     */
    public void testJugador()
    {
	assertNotNull(jugador1);
	assertEquals(0, jugador1.getPuntos());
	assertEquals("Jugador1", jugador1.getNombre());
	
	assertNotNull(jugador2);
	assertEquals(0, jugador2.getPuntos());
	assertEquals("Jugador2", jugador2.getNombre());
	
	assertNotNull(jugador3);
	assertEquals(0, jugador3.getPuntos());
	assertEquals("Jugador1", jugador3.getNombre());
    }

    /**
     * Test method for {@link packSudokuAntiguo.Jugador#setPuntos(int)}.
     */
    public void testSetPuntos()
    {
	jugador1.setPuntos(100);
	assertEquals(100, jugador1.getPuntos());
	jugador1.setPuntos(4000);
	assertEquals(4000, jugador1.getPuntos());
    }

    /**
     * Test method for {@link packSudokuAntiguo.Jugador#getNombre()}.
     */
    public void testGetNombre()
    {
	assertEquals("Jugador1", jugador1.getNombre());
	assertEquals("Jugador2", jugador2.getNombre());
	assertEquals("Jugador1", jugador3.getNombre());

    }

    /**
     * Test method for {@link packSudokuAntiguo.Jugador#getPuntos()}.
     */
    public void testGetPuntos()
    {
	assertEquals(0, jugador1.getPuntos());
	jugador1.setPuntos(100);
	assertEquals(100, jugador1.getPuntos());
	jugador1.setPuntos(4000);
	assertEquals(4000, jugador1.getPuntos());
 
    }

    /**
     * Test method for {@link packSudokuAntiguo.Jugador#masPuntos(packSudokuAntiguo.Jugador)}.
     */
    public void testMasPuntos()
    {
	jugador1.setPuntos(100);
	jugador2.setPuntos(50);
	jugador3.setPuntos(800);
	assertFalse(jugador1.masPuntos(jugador1));
	assertFalse(jugador2.masPuntos(jugador1));
	assertTrue(jugador1.masPuntos(jugador2));
	assertFalse(jugador2.masPuntos(jugador3));
	assertTrue(jugador3.masPuntos(jugador2));
	assertFalse(jugador1.masPuntos(jugador3));
	assertTrue(jugador3.masPuntos(jugador1));
	
    }

    /**
     * Test method for {@link packSudokuAntiguo.Jugador#isIgual(packSudokuAntiguo.Jugador)}.
     */
    public void testEsIgual()
    {
	assertTrue(jugador1.isIgual(jugador1));
	assertFalse(jugador1.isIgual(jugador2));
	assertFalse(jugador2.isIgual(jugador3));
	assertTrue(jugador1.isIgual(jugador3));
	jugador1.setPuntos(500);
	assertFalse(jugador1.isIgual(jugador3));
	jugador3.setPuntos(500);
	assertTrue(jugador1.isIgual(jugador3));
	
    }

}
