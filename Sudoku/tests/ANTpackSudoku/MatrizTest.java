/**
 * 
 */
package ANTpackSudoku;

import ANTpackSudokuAntiguo.Matriz;
import ANTpackSudokuAntiguo.TipoArea;
import packExcepciones.ExcepcionCoordenadaNoValida;
import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;
import packExcepciones.ExcepcionValorNoValido;
import junit.framework.TestCase;

/**
 * @author mikel
 */
public class MatrizTest extends TestCase {

    private Matriz matriz1 = null;

    private Matriz matrizSolucion = null;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	matriz1 = new Matriz();
	matrizSolucion = new Matriz();
	int[][] datos = new int[][] { { 9, 4, 1, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 4, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };
	setValues(matrizSolucion, datos);

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#Matriz()}.
     */
    public void testMatriz() {
	assertNotNull(matriz1);
	assertNotNull(matrizSolucion);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#setValorInicial(int, int, int)}.
     */
    public void testSetValorInicial() {
	try {
	    assertFalse(matriz1.isInicial(1, 1));
	    matriz1.setValorInicial(1, 1, 1);
	    assertEquals(1, matriz1.getValor(1, 1));
	    assertTrue(matriz1.isInicial(1, 1));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    matriz1.setValorInicial(1, 1, 3);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	}

	try {
	    assertFalse(matriz1.isInicial(2, 2));
	    matriz1.setValorInicial(2, 2, 2);
	    assertEquals(2, matriz1.getValor(2, 2));
	    assertTrue(matriz1.isInicial(2, 2));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(3, 3));
	    matriz1.setValorInicial(3, 3, 3);
	    assertEquals(3, matriz1.getValor(3, 3));
	    assertTrue(matriz1.isInicial(3, 3));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(4, 4));
	    matriz1.setValorInicial(4, 4, 4);
	    assertEquals(4, matriz1.getValor(4, 4));
	    assertTrue(matriz1.isInicial(4, 4));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(5, 5));
	    matriz1.setValorInicial(5, 5, 5);
	    assertEquals(5, matriz1.getValor(5, 5));
	    assertTrue(matriz1.isInicial(5, 5));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(6, 6));
	    matriz1.setValorInicial(6, 6, 6);
	    assertEquals(6, matriz1.getValor(6, 6));
	    assertTrue(matriz1.isInicial(6, 6));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(7, 7));
	    matriz1.setValorInicial(7, 7, 7);
	    assertEquals(7, matriz1.getValor(7, 7));
	    assertTrue(matriz1.isInicial(7, 7));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(8, 8));
	    matriz1.setValorInicial(8, 8, 8);
	    assertEquals(8, matriz1.getValor(8, 8));
	    assertTrue(matriz1.isInicial(8, 8));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(9, 9));
	    matriz1.setValorInicial(9, 9, 9);
	    assertEquals(9, matriz1.getValor(9, 9));
	    assertTrue(matriz1.isInicial(9, 9));
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}

	try {
	    matriz1.setValorInicial(0, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValorInicial(1, 0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValorInicial(10, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValorInicial(1, 10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValorInicial(0, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1 = new Matriz();
	    matriz1.setValorInicial(1, 1, 0);
	    fail();
	} catch (ExcepcionValorNoValido e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1 = new Matriz();
	    matriz1.setValorInicial(1, 1, 10);
	    fail();
	} catch (ExcepcionValorNoValido e) {
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#setValor(int, int, int)}.
     */
    public void testSetValor() {
	try {
	    assertFalse(matriz1.isInicial(1, 1));
	    assertTrue(matriz1.isLibre(1, 1));
	    matriz1.setValor(1, 1, 1);
	    assertEquals(1, matriz1.getValor(1, 1));
	    assertFalse(matriz1.isInicial(1, 1));
	    assertFalse(matriz1.isLibre(1, 1));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(2, 2));
	    assertTrue(matriz1.isLibre(2, 2));
	    matriz1.setValor(2, 2, 2);
	    assertEquals(2, matriz1.getValor(2, 2));
	    assertFalse(matriz1.isInicial(2, 2));
	    assertFalse(matriz1.isLibre(2, 2));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(3, 3));
	    assertFalse(matriz1.isInicial(3, 3));
	    matriz1.setValor(3, 3, 3);
	    assertEquals(3, matriz1.getValor(3, 3));
	    assertFalse(matriz1.isInicial(3, 3));
	    assertFalse(matriz1.isLibre(3, 3));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(4, 4));
	    assertFalse(matriz1.isInicial(4, 4));
	    matriz1.setValor(4, 4, 4);
	    assertEquals(4, matriz1.getValor(4, 4));
	    assertFalse(matriz1.isInicial(4, 4));
	    assertFalse(matriz1.isLibre(4, 4));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(5, 5));
	    assertFalse(matriz1.isInicial(5, 5));
	    matriz1.setValor(5, 5, 5);
	    assertEquals(5, matriz1.getValor(5, 5));
	    assertFalse(matriz1.isInicial(5, 5));
	    assertFalse(matriz1.isLibre(5, 5));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(6, 6));
	    assertFalse(matriz1.isInicial(6, 6));
	    matriz1.setValor(6, 6, 6);
	    assertEquals(6, matriz1.getValor(6, 6));
	    assertFalse(matriz1.isInicial(6, 6));
	    assertFalse(matriz1.isLibre(6, 6));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(7, 7));
	    assertFalse(matriz1.isInicial(7, 7));
	    matriz1.setValor(7, 7, 7);
	    assertEquals(7, matriz1.getValor(7, 7));
	    assertFalse(matriz1.isInicial(7, 7));
	    assertFalse(matriz1.isLibre(7, 7));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(8, 8));
	    assertFalse(matriz1.isInicial(8, 8));
	    matriz1.setValor(8, 8, 8);
	    assertEquals(8, matriz1.getValor(8, 8));
	    assertFalse(matriz1.isInicial(8, 8));
	    assertFalse(matriz1.isLibre(8, 8));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(9, 9));
	    assertFalse(matriz1.isInicial(9, 9));
	    matriz1.setValor(9, 9, 9);
	    assertEquals(9, matriz1.getValor(9, 9));
	    assertFalse(matriz1.isInicial(9, 9));
	    assertFalse(matriz1.isLibre(9, 9));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(0, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValor(1, 0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(10, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValor(1, 10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(0, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValor(1, 1, 0);
	    fail();
	} catch (ExcepcionValorNoValido e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValor(1, 1, 10);
	    fail();
	} catch (ExcepcionValorNoValido e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.setValor(10, 10, 10);
	    fail();
	} catch (Exception e) {
	}

	try {
	    matriz1.setValorInicial(1, 1, 1);
	    matriz1.setValor(1, 1, 2);
	    fail();
	} catch (ExcepcionValorInicial e) {
	} catch (Exception e) {
	    fail();
	}

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#quitarValor(int, int)}.
     */
    public void testQuitarValor() {
	try {
	    matriz1.quitarValor(1, 1);
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.quitarValor(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.quitarValor(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.quitarValor(1, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	try {
	    matriz1.quitarValor(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(1, 1, 1);
	    assertFalse(matriz1.isLibre(1, 1));
	    matriz1.quitarValor(1, 1);
	    assertTrue(matriz1.isLibre(1, 1));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValorInicial(2, 2, 2);
	    assertFalse(matriz1.isLibre(2, 2));
	    matriz1.quitarValor(2, 2);
	    fail();
	} catch (ExcepcionValorInicial e) {
	} catch (Exception e) {
	    fail();
	}

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#isLibre(int, int)}.
     */
    public void testIsLibre() {
	try {
	    assertTrue(matriz1.isLibre(1, 1));
	    matriz1.setValor(1, 1, 1);
	    assertFalse(matriz1.isLibre(1, 1));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertTrue(matriz1.isLibre(2, 2));
	    matriz1.setValorInicial(2, 2, 2);
	    assertFalse(matriz1.isLibre(2, 2));
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#isInicial(int, int)}.
     */
    public void testIsInicial() {
	try {
	    assertFalse(matriz1.isInicial(8, 8));
	    matriz1.setValor(8, 8, 8);
	    assertFalse(matriz1.isInicial(8, 8));
	} catch (Exception e) {
	    fail();
	}

	try {
	    assertFalse(matriz1.isInicial(9, 9));
	    matriz1.setValorInicial(9, 9, 9);
	    assertTrue(matriz1.isInicial(9, 9));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.isInicial(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}

	try {
	    matriz1.isInicial(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}

	try {
	    matriz1.isInicial(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}

	try {
	    matriz1.isInicial(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#getValor(int, int)}.
     */
    public void testGetValor() {

	try {
	    matriz1.getValor(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}

	try {
	    matriz1.getValor(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}

	try {
	    matriz1.getValor(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}

	try {
	    matriz1.getValor(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
	try {
	    matriz1.getValor(1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	}

	try {
	    matriz1.setValor(1, 1, 1);
	    assertEquals(1, matriz1.getValor(1, 1));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(2, 2, 2);
	    assertEquals(2, matriz1.getValor(2, 2));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(3, 3, 3);
	    assertEquals(3, matriz1.getValor(3, 3));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(4, 4, 4);
	    assertEquals(4, matriz1.getValor(4, 4));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(5, 5, 5);
	    assertEquals(5, matriz1.getValor(5, 5));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(6, 6, 6);
	    assertEquals(6, matriz1.getValor(6, 6));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(7, 7, 7);
	    assertEquals(7, matriz1.getValor(7, 7));
	} catch (Exception e) {
	   fail();
	}

	try {
	    matriz1.setValor(8, 8, 8);
	    assertEquals(8, matriz1.getValor(8, 8));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(9, 9, 9);
	    assertEquals(9, matriz1.getValor(9, 9));
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#hayInconsistencias(char, int)}.
     */
    public void testDescubrirInconsistencias() {
	try {
	    matriz1.hayInconsistencias(TipoArea.COLUMNA, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
	
	try {
	    matriz1.hayInconsistencias(TipoArea.COLUMNA, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
	
	try {
	    matriz1.hayInconsistencias(TipoArea.FILA, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
	
	try {
	    matriz1.hayInconsistencias(TipoArea.FILA, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
	
	
	try {
	    matriz1.hayInconsistencias(TipoArea.ZONA, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
	
	try {
	    matriz1.hayInconsistencias(TipoArea.ZONA,10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	}
	
	
	try {
	    for (int i = 1; i <= 9; i++) {
	      	assertFalse(matrizSolucion.hayInconsistencias(TipoArea.ZONA, i));
	      	assertFalse(matrizSolucion.hayInconsistencias(TipoArea.FILA, i));
	      	assertFalse(matrizSolucion.hayInconsistencias(TipoArea.COLUMNA, i));
	    }
	} catch (ExcepcionCoordenadaNoValida e1) {
	    fail();
	}

	try {
	    matrizSolucion.setValor(3, 3, 4);
	    for (int i = 1; i <= 9; i++) {
	        if (i == 1) {
	    	assertTrue(matrizSolucion.hayInconsistencias(TipoArea.ZONA, i));

	        } else {
	    	assertFalse(matrizSolucion.hayInconsistencias(TipoArea.ZONA, i));

	        }
	        if (i == 3) {
	    	assertTrue(matrizSolucion.hayInconsistencias(TipoArea.FILA, i));
	    	assertTrue(matrizSolucion.hayInconsistencias(TipoArea.COLUMNA,
	    		i));

	        } else {
	    	assertFalse(matrizSolucion.hayInconsistencias(TipoArea.FILA, i));
	    	assertFalse(matrizSolucion.hayInconsistencias(TipoArea.COLUMNA,
	    		i));

	        }
	    }
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	}
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.Matriz#getNumErrores(ANTpackSudokuAntiguo.Matriz)}.
     */
    public void testGetNumErrores() {
	assertEquals(0, matriz1.getNumErrores(matrizSolucion));
	assertEquals(0, matrizSolucion.getNumErrores(matrizSolucion));
	int[][] datos1 = new int[][] { { 8, 4, 1, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 4, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };
	setValues(matriz1, datos1);
	assertEquals(1, matriz1.getNumErrores(matrizSolucion));

	int[][] datos2 = new int[][] { { 8, 8, 1, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 4, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };
	setValues(matriz1, datos2);
	assertEquals(2, matriz1.getNumErrores(matrizSolucion));

	int[][] datos3 = new int[][] { { 8, 8, 8, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 4, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };
	setValues(matriz1, datos3);
	assertEquals(3, matriz1.getNumErrores(matrizSolucion));

	int[][] datos4 = new int[][] { { 8, 8, 8, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 5, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };
	setValues(matriz1, datos4);
	assertEquals(4, matriz1.getNumErrores(matrizSolucion));

	int[][] datos5 = new int[][] { { 8, 8, 8, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 5, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 5 } };
	setValues(matriz1, datos5);
	assertEquals(5, matriz1.getNumErrores(matrizSolucion));

	int[][] datos6 = new int[][] {  { 3, 1, 6, 2, 7, 9, 8, 5, 4 }, { 4, 9, 5, 1, 3, 8, 7, 2, 6 },
		    { 7, 8, 2, 6, 4, 5, 9, 3, 1 }, { 2, 3, 7, 9, 5, 4, 1, 6, 8 },
		    { 8, 6, 4, 3, 1, 7, 2, 9, 5 }, { 9, 5, 1, 8, 6, 2, 3, 4, 7 },
		    { 1, 2, 9, 5, 8, 6, 4, 7, 3 }, { 6, 4, 3, 7, 2, 1, 5, 8, 9 },
		    { 5, 7, 8, 4, 9, 3, 6, 1, 2 } };
	matriz1 = new Matriz();
	setValues(matriz1, datos6);
	assertEquals(76, matriz1.getNumErrores(matrizSolucion));
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Matriz#isCompleto()}.
     */
    public void testIsCompleto() {
	assertFalse(matriz1.isCompleto());
	try {
	    matriz1.setValorInicial(1, 1, 1);
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	}
	try {
	    matriz1.setValor(1, 2, 2);
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	}
	matriz1 = new Matriz();
	assertFalse(matriz1.isCompleto());
	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    matriz1.setValor(i, j, 1);
		} catch (ExcepcionValorNoValido e) {
		    fail();
		} catch (ExcepcionCoordenadaNoValida e) {
		    fail();
		} catch (ExcepcionValorInicial e) {
		    fail();
		}
	    }
	}
	assertTrue(matriz1.isCompleto());
	assertTrue(matrizSolucion.isCompleto());
    }

    /**
     * Sets the values.
     * 
     * @param pMatriz
     *            the matriz
     * @param pValues
     *            the values
     */
    private void setValues(Matriz pMatriz, int[][] pValues) {
	for (int i = 0; i < pValues.length; i++) {
	    for (int j = 0; j < pValues[i].length; j++) {
		try {
		    pMatriz.setValor(i + 1, j + 1, pValues[i][j]);
		} catch (ExcepcionValorNoValido e) {
		    fail();
		} catch (ExcepcionCoordenadaNoValida e) {
		    fail();
		} catch (ExcepcionValorInicial e) {
		    fail();
		}
	    }
	}
    }

}
