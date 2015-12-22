/**
 * 
 */
package packSudoku;

import ANTpackSudokuAntiguo.Matriz;
import ANTpackSudokuAntiguo.Sudoku;
import packExcepciones.ExcepcionCoordenadaNoValida;
import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;
import packExcepciones.ExcepcionValorNoValido;
import junit.framework.TestCase;

/**
 * @author mikel
 */
public class SudokuTest extends TestCase {
    private Sudoku sudoku1 = null;

    private Sudoku sudoku2 = null;

    private Sudoku sudoku3 = null;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	sudoku1 = new Sudoku("Sudoku1", 1);
	sudoku2 = new Sudoku("Sudoku2", 2);
	sudoku3 = new Sudoku("Sudoku3", 3);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Sudoku#Sudoku(java.lang.String, int)}.
     */
    public void testSudoku() {
	assertNotNull(sudoku1);
	assertEquals("Sudoku1", sudoku1.getIdentificador());
	assertEquals(1, sudoku1.getDificultad());

	assertNotNull(sudoku2);
	assertEquals("Sudoku2", sudoku2.getIdentificador());
	assertEquals(2, sudoku2.getDificultad());

	assertNotNull(sudoku3);
	assertEquals("Sudoku3", sudoku3.getIdentificador());
	assertEquals(3, sudoku3.getDificultad());
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Sudoku#getDificultad()}.
     */
    public void testGetDificultad() {
	assertEquals(1, sudoku1.getDificultad());
	assertEquals(2, sudoku2.getDificultad());
	assertEquals(3, sudoku3.getDificultad());
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Sudoku#getIdentificador()}.
     */
    public void testGetIdentificador() {
	assertEquals("Sudoku1", sudoku1.getIdentificador());
	assertEquals("Sudoku2", sudoku2.getIdentificador());
	assertEquals("Sudoku3", sudoku3.getIdentificador());
    }

    /**
     * Test get valor.
     */
    public void testGetValor() {
	try {
	    sudoku1.getValor(1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	}

	try {
	    sudoku1.getValor(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}
	try {
	    sudoku1.getValor(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}

	try {
	    sudoku1.getValor(1, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}
	try {
	    sudoku1.getValor(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHayValorEnCasilla e1) {
	    fail();
	}

	try {
	    for (int i = 1; i <= 9; i++) {
		for (int j = 1; j <= 9; j++) {
		    for (int k = 1; k <= 9; k++) {
			sudoku1.setValor(i, j, k);
			assertEquals(k, sudoku1.getValor(i, j));
		    }
		}
	    }

	    sudoku1.setValorInicial(1, 1, 5);
	    assertEquals(5, sudoku1.getValor(1, 1));
	} catch (ExcepcionValorNoValido e) {
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionValorInicial e) {
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Sudoku#setValorInicial(int, int, int)}.
     */
    public void testSetValorInicial() {
	try {
	    sudoku1.setValorInicial(0, 1, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    sudoku1.setValorInicial(10, 1, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    sudoku1.setValorInicial(1, 0, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    sudoku1.setValorInicial(1, 10, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}
	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    sudoku1.setValorInicial(i, j, 0);
		    fail();
		} catch (ExcepcionCoordenadaNoValida e1) {
		    fail();
		} catch (ExcepcionValorNoValido e1) {
		} catch (ExcepcionValorInicial e1) {
		    fail();
		}

		try {
		    sudoku1.setValorInicial(i, j, 10);
		    fail();
		} catch (ExcepcionCoordenadaNoValida e1) {
		    fail();
		} catch (ExcepcionValorNoValido e1) {
		} catch (ExcepcionValorInicial e1) {
		    fail();
		}
		try {
		    assertFalse(sudoku1.isInicial(i, j));
		    sudoku1.setValorInicial(i, j, j);
		    assertTrue(sudoku1.isInicial(i, j));
		    assertEquals(j, sudoku1.getValor(i, j));
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
		    sudoku1.setValorInicial(i, j, 5);
		    fail();
		} catch (ExcepcionCoordenadaNoValida e) {
		    fail();
		} catch (ExcepcionValorNoValido e) {
		    fail();
		} catch (ExcepcionValorInicial e) {
		}
	    }
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Sudoku#setValor(int, int, int)}.
     */
    public void testSetValor() {
	try {
	    sudoku1.setValor(0, 1, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    sudoku1.setValor(10, 1, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    sudoku1.setValor(1, 0, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    sudoku1.setValor(1, 10, 5);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		for (int k = 1; k <= 9; k++) {
		    try {
			sudoku1.setValor(i, j, k);
			assertEquals(k, sudoku1.getValor(i, j));
		    } catch (Exception e) {
			fail();
		    }
		}
		try {
		    sudoku1.setValorInicial(i, j, 5);
		    try {
			sudoku1.setValor(i, j, 6);
			fail();
		    } catch (ExcepcionValorInicial e) {
		    }
		} catch (Exception e) {
		    fail();
		}
	    }
	}
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.Sudoku#getNumErrores(ANTpackSudokuAntiguo.Matriz)}.
     */
    public void testGetNumErrores() {
	int[][] datos1 = new int[][] { { 9, 4, 1, 5, 8, 2, 3, 7, 6 },
		{ 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
		{ 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 4, 9, 7, 6, 8 },
		{ 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
		{ 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };
	setValues(sudoku1, datos1);
	Matriz matriz1 = new Matriz();

	assertEquals(0, sudoku1.getNumErrores(matriz1));

	try {
	    matriz1.setValor(1, 1, 8);
	    assertEquals(1, sudoku1.getNumErrores(matriz1));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(5, 5, 5);
	    assertEquals(2, sudoku1.getNumErrores(matriz1));
	} catch (Exception e) {
	    fail();
	}

	try {
	    matriz1.setValor(9, 9, 9);
	    assertEquals(3, sudoku1.getNumErrores(matriz1));
	} catch (Exception e) {
	    fail();
	}

	setValues(matriz1,datos1);
	assertEquals(0, sudoku1.getNumErrores(matriz1));
	
	matriz1 = new Matriz();
	int errores = 0;
	assertEquals(0, sudoku1.getNumErrores(matriz1));
	for (int i = 1; i <=9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    matriz1.setValor(i, j, datos1[i-1][j-1]!=1?1:2);
		    assertEquals(++errores, sudoku1.getNumErrores(matriz1));
		} catch (Exception e) {
		    fail();
		}
	    }    
	}
    }

    /**
     * Sets the values.
     * 
     * @param pSudoku
     *            the sudoku
     * @param pValues
     *            the values
     */
    private void setValues(Sudoku pSudoku, int[][] pValues) {
	for (int i = 0; i < pValues.length; i++) {
	    for (int j = 0; j < pValues[i].length; j++) {
		try {
		    pSudoku.setValor(i + 1, j + 1, pValues[i][j]);
		} catch (Exception e) {
		    fail();
		}
	    }
	}
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
		} catch (Exception e) {
		    fail();
		}
	    }
	}
    }

}
