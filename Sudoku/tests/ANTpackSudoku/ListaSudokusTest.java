/**
 * 
 */
package ANTpackSudoku;

import java.io.FileNotFoundException;
import java.util.Iterator;

import ANTpackSudokuAntiguo.ListaSudokus;
import ANTpackSudokuAntiguo.Sudoku;
import packExcepciones.ExcepcionCoordenadaNoValida;
import packExcepciones.ExcepcionNoHayFicheroSudokus;
import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;
import packExcepciones.ExcepcionValorNoValido;
import junit.framework.TestCase;

/**
 * @author mikel
 */
public class ListaSudokusTest extends TestCase {

    private ListaSudokus listaSudokus = null;

    private Sudoku sudoku1 = null;

    private Sudoku sudoku2 = null;

    private Sudoku sudoku3 = null;

    private int[][] solucionSudoku1 = new int[][] {
	    { 9, 4, 1, 5, 8, 2, 3, 7, 6 }, { 8, 7, 3, 4, 9, 6, 5, 2, 1 },
	    { 6, 2, 5, 1, 7, 3, 9, 8, 4 }, { 3, 9, 4, 8, 6, 7, 1, 5, 2 },
	    { 1, 5, 2, 3, 4, 9, 7, 6, 8 }, { 7, 8, 6, 2, 5, 1, 4, 3, 9 },
	    { 2, 3, 7, 9, 1, 8, 6, 4, 5 }, { 5, 1, 8, 6, 3, 4, 2, 9, 7 },
	    { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };

    private boolean[][] casillasInSud1 = new boolean[][] {
	    { false, false, false, false, false, false, false, false, false },
	    { false, true, false, false, true, false, false, false, false },
	    { true, false, true, false, false, true, false, true, true },
	    { false, false, true, false, false, false, false, true, true },
	    { false, true, false, true, false, false, true, false, false },
	    { false, false, false, false, false, true, true, false, false },
	    { false, true, true, false, false, true, false, false, true },
	    { false, false, false, false, false, true, false, false, false },
	    { true, false, true, true, true, false, false, false, true } };

    private int[][] solucionSudoku2 = new int[][] {
	    { 3, 1, 6, 2, 7, 9, 8, 5, 4 }, { 4, 9, 5, 1, 3, 8, 7, 2, 6 },
	    { 7, 8, 2, 6, 4, 5, 9, 3, 1 }, { 2, 3, 7, 9, 5, 4, 1, 6, 8 },
	    { 8, 6, 4, 3, 1, 7, 2, 9, 5 }, { 9, 5, 1, 8, 6, 2, 3, 4, 7 },
	    { 1, 2, 9, 5, 8, 6, 4, 7, 3 }, { 6, 4, 3, 7, 2, 1, 5, 8, 9 },
	    { 5, 7, 8, 4, 9, 3, 6, 1, 2 } };

    private boolean[][] casillasInSud2 = new boolean[][] {
	    { false, false, true, false, true, true, true, false, false },
	    { false, false, false, true, false, false, false, false, false },
	    { false, false, true, true, true, false, false, false, false },
	    { false, false, false, false, false, false, false, false, true },
	    { false, false, true, false, false, true, true, true, false },
	    { false, true, false, true, true, false, true, false, false },
	    { true, false, true, false, false, false, true, true, false },
	    { false, true, false, false, false, false, false, false, true },
	    { true, false, false, false, false, false, false, true, false } };

    private int[][] solucionSudoku3 = new int[][] {
	    { 9, 4, 6, 3, 2, 1, 7, 8, 5 }, { 3, 8, 5, 6, 4, 7, 1, 2, 9 },
	    { 1, 7, 2, 9, 5, 8, 4, 6, 3 }, { 5, 1, 8, 4, 7, 2, 3, 9, 6 },
	    { 6, 9, 4, 1, 8, 3, 5, 7, 2 }, { 2, 3, 7, 5, 6, 9, 8, 1, 4 },
	    { 4, 5, 9, 8, 1, 6, 2, 3, 7 }, { 7, 6, 1, 2, 3, 4, 9, 5, 8 },
	    { 8, 2, 3, 7, 9, 5, 6, 4, 1 } };

    private boolean[][] casillasInSud3 = new boolean[][] {
	    { false, false, true, true, false, false, true, false, true },
	    { false, false, false, false, false, false, false, true, false },
	    { true, true, true, false, false, false, false, false, false },
	    { false, false, false, true, true, false, false, true, false },
	    { false, false, true, true, false, false, true, false, false },
	    { false, true, true, true, false, false, false, false, true },
	    { false, false, false, false, false, true, false, true, false },
	    { false, false, false, false, false, false, false, false, true },
	    { false, false, true, true, true, true, true, false, false } };

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	listaSudokus = ListaSudokus.INSTANCIA;
	sudoku1 = new Sudoku("S001", 1);
	cargarSudoku(sudoku1, casillasInSud1, solucionSudoku1);
	sudoku2 = new Sudoku("s003", 2);
	cargarSudoku(sudoku2, casillasInSud2, solucionSudoku2);
	sudoku3 = new Sudoku("s004", 3);
	cargarSudoku(sudoku3, casillasInSud3, solucionSudoku3);
	try {
	    listaSudokus.cargar("kk.txt");
	} catch (ExcepcionNoHayFicheroSudokus e) {
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.ListaSudokus#cargar(java.lang.String)}.
     */
    public void testCargar() {
	Iterator<Sudoku> iteradorSudokus = listaSudokus.getIterador(1);
	assertFalse(iteradorSudokus.hasNext());
	try {
	    listaSudokus.cargar("kk.txt");
	    fail();
	} catch (ExcepcionNoHayFicheroSudokus e) {
	}
	try {
	    listaSudokus.cargar("sudokuak.txt");
	    iteradorSudokus = listaSudokus.getIterador(1);
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku1, iteradorSudokus.next());
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku2, iteradorSudokus.next());
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku3, iteradorSudokus.next());
	    assertFalse(iteradorSudokus.hasNext());
	} catch (ExcepcionNoHayFicheroSudokus e) {
	    fail();
	}

    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.ListaSudokus#getIterador(int)}.
     */
    public void testGetIterador() {
        try {
	    listaSudokus.cargar("sudokuak.txt");
	    Iterator<Sudoku> iteradorSudokus = listaSudokus.getIterador(1);
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku1, iteradorSudokus.next());
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku2, iteradorSudokus.next());
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku3, iteradorSudokus.next());
	    assertFalse(iteradorSudokus.hasNext());

	    iteradorSudokus = listaSudokus.getIterador(2);
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku2, iteradorSudokus.next());
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku3, iteradorSudokus.next());
	    assertFalse(iteradorSudokus.hasNext());

	    iteradorSudokus = listaSudokus.getIterador(3);
	    assertTrue(iteradorSudokus.hasNext());
	    comprobarEsIgual(sudoku3, iteradorSudokus.next());
	    assertFalse(iteradorSudokus.hasNext());
	} catch (ExcepcionNoHayFicheroSudokus e) {
	}
    }

    /**
     * @param pSudoku1
     * @param pNext
     * @return
     */
    private void comprobarEsIgual(Sudoku pSudoku1, Sudoku pSudoku2) {
	assertEquals(pSudoku1.getIdentificador(), pSudoku2.getIdentificador());
	assertEquals(pSudoku1.getDificultad(), pSudoku2.getDificultad());
	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    assertEquals(pSudoku1.isInicial(i, j), pSudoku2.isInicial(
			    i, j));
		} catch (ExcepcionCoordenadaNoValida e) {
		    fail();
		}
		try {
		    assertEquals(pSudoku1.getValor(i, j), pSudoku2.getValor(i,
			    j));
		} catch (ExcepcionCoordenadaNoValida e) {
		    fail();
		} catch (ExcepcionNoHayValorEnCasilla e) {
		    fail();
		}
	    }
	}
    }

    private void cargarSudoku(Sudoku pSudoku, boolean[][] pCasillasInic,
	    int[][] pDatosSolucion) {
	for (int i = 0; i < pDatosSolucion.length; i++) {
	    for (int j = 0; j < pDatosSolucion[i].length; j++) {
		if (pCasillasInic[i][j]) {
		    try {
			pSudoku.setValorInicial(i + 1, j + 1,
				pDatosSolucion[i][j]);
		    } catch (ExcepcionCoordenadaNoValida e) {
			fail();
		    } catch (ExcepcionValorNoValido e) {
			fail();
		    } catch (ExcepcionValorInicial e) {
			fail();
		    }
		} else {
		    try {
			pSudoku.setValor(i + 1, j + 1, pDatosSolucion[i][j]);
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
}
