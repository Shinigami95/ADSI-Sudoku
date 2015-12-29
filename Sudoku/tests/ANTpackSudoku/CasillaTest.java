/**
 * 
 */
package ANTpackSudoku;

import ANTpackSudokuAntiguo.Casilla;
import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class CasillaTest.
 * 
 * @author mikel
 */
public class CasillaTest extends TestCase {

    /** The casilla1. */
    private Casilla casilla1 = null;

    /** The casilla2. */
    private Casilla casilla2 = null;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	casilla1 = new Casilla();
	casilla2 = new Casilla();
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#Casilla()}.
     */
    public void testCasilla() {
	assertNotNull("ERROR CONSTRUCTOR", casilla1);
	assertTrue("ERROR CONSTRUCTOR. CASILLA NO LIBRE", casilla1.isLibre());
	assertFalse("ERROR CONSTRUCTOR. CASILLA TIENE VALOR INICIAL", casilla1
		.isInicial());
	assertNotNull("ERROR CONSTRUCTOR", casilla2);
	assertTrue("ERROR CONSTRUCTOR. CASILLA NO LIBRE", casilla2.isLibre());
	assertFalse("ERROR CONSTRUCTOR. CASILLA TIENE VALOR INICIAL", casilla2
		.isInicial());
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#setValorInicial(int)}.
     */
    public void testSetValorInicial() {
	try {
	    casilla1.setValorInicial(1);
	    assertTrue(casilla1.isInicial());
	    assertFalse(casilla1.isLibre());
	    assertEquals(1, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValorInicial(2);
	    fail();
	} catch (ExcepcionValorInicial e) {
	}

	try {
	    casilla2.setValorInicial(9);
	    assertTrue(casilla2.isInicial());
	    assertFalse(casilla2.isLibre());
	    assertEquals(9, casilla2.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#setValor(int)}.
     */
    public void testSetValor() {
	try {
	    casilla1.setValor(1);
	    assertEquals(1, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(2);
	    assertEquals(2, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(3);
	    assertEquals(3, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(4);
	    assertEquals(4, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(5);
	    assertEquals(5, casilla1.getValor());
	} catch (ExcepcionValorInicial e5) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e5) {
	    fail();
	}

	try {
	    casilla1.setValor(6);
	    assertEquals(6, casilla1.getValor());
	} catch (ExcepcionValorInicial e4) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e4) {
	    fail();
	}
	try {
	    casilla1.setValor(7);
	    assertEquals(7, casilla1.getValor());
	} catch (ExcepcionValorInicial e3) {
	    // TODO Auto-generated catch block
	    e3.printStackTrace();
	} catch (ExcepcionNoHayValorEnCasilla e3) {
	    // TODO Auto-generated catch block
	    e3.printStackTrace();
	}
	try {
	    casilla1.setValor(8);
	    assertEquals(8, casilla1.getValor());
	} catch (ExcepcionValorInicial e2) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e2) {
	    fail();
	}

	try {
	    casilla1.setValor(9);
	    assertEquals(9, casilla1.getValor());
	} catch (ExcepcionValorInicial e1) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}

	try {
	    casilla2.setValorInicial(8);
	    casilla2.setValor(7);
	    fail();
	} catch (ExcepcionValorInicial e) {
	}

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#quitarValor()}.
     */
    public void testQuitarValor() {
	try {
	    casilla1.quitarValor();
	    fail();
	} catch (ExcepcionValorInicial e1) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	}

	try {
	    casilla1.setValor(8);
	    casilla1.quitarValor();
	    assertTrue(casilla1.isLibre());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla2.setValorInicial(8);
	    casilla2.quitarValor();
	    fail();
	} catch (ExcepcionValorInicial e) {
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#isLibre()}.
     */
    public void testIsLibre() {
	assertTrue(casilla1.isLibre());
	assertTrue(casilla2.isLibre());

	try {
	    casilla1.setValor(8);
	    assertFalse(casilla1.isLibre());
	} catch (ExcepcionValorInicial e) {
	}

	try {
	    casilla2.setValorInicial(8);
	    assertFalse(casilla2.isLibre());
	} catch (ExcepcionValorInicial e) {
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#isInicial()}.
     */
    public void testIsInicial() {
	assertFalse(casilla1.isInicial());
	assertFalse(casilla2.isInicial());
	try {
	    casilla1.setValor(8);
	    assertFalse(casilla1.isInicial());
	} catch (ExcepcionValorInicial e) {
	    fail();
	}
	try {
	    casilla2.setValorInicial(8);
	    assertTrue(casilla2.isInicial());
	} catch (ExcepcionValorInicial e) {
	    fail();
	}

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#getValor()}.
     */
    public void testGetValor() {
	try {
	    casilla1.getValor();
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}

	try {
	    casilla1.setValor(1);
	    assertEquals(1, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}
	try {
	    casilla1.setValor(2);
	    assertEquals(2, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(3);
	    assertEquals(3, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(4);
	    assertEquals(4, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(5);
	    assertEquals(5, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(6);
	    assertEquals(6, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(7);
	    assertEquals(7, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(8);
	    assertEquals(8, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(9);
	    assertEquals(9, casilla1.getValor());
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Casilla#mismoValor(ANTpackSudokuAntiguo.Casilla)}
     * .
     */
    public void testMismoValor() {
	try {
	    assertFalse(casilla1.mismoValor(casilla2));
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}

	try {
	    assertFalse(casilla2.mismoValor(casilla1));
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}

	try {
	    casilla1.setValor(1);
	} catch (ExcepcionValorInicial e1) {
	    fail();
	}
	try {
	    assertFalse(casilla1.mismoValor(casilla2));
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}

	try {
	    assertFalse(casilla2.mismoValor(casilla1));
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}

	try {
	    assertTrue(casilla1.mismoValor(casilla1));
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla2.setValor(1);
	    assertTrue(casilla1.mismoValor(casilla2));
	    assertTrue(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla2.setValor(2);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(3);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    casilla1.setValor(4);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(5);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(6);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(7);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(8);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    casilla1.setValor(9);
	    assertFalse(casilla1.mismoValor(casilla2));
	    assertFalse(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	
	try {
	    casilla1.setValorInicial(9);
	    assertTrue(casilla1.mismoValor(casilla1));
	    assertFalse(casilla1.mismoValor(casilla2));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	
	try {
	    casilla2.setValorInicial(9);
	    assertTrue(casilla1.mismoValor(casilla2));
	    assertTrue(casilla2.mismoValor(casilla1));
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
    }

}
