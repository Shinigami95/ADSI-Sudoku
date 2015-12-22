/**
 * 
 */
package packSudoku;

import ANTpackSudokuAntiguo.Sudoku;
import ANTpackSudokuAntiguo.Tablero;
import ANTpackSudokuAntiguo.TipoArea;
import packExcepciones.ExcepcionCoordenadaNoValida;
import packExcepciones.ExcepcionNoHaySudokuCargado;
import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;
import packExcepciones.ExcepcionValorNoValido;
import junit.framework.TestCase;

/**
 * @author mikel
 */
public class TableroTest extends TestCase {
    private Tablero tablero = null;

    private Sudoku sudoku1 = null;

    private Sudoku sudoku2 = null;

    // Datos para los sudokus
    int[][] solucionSudoku1 = new int[][] { { 9, 4, 1, 5, 8, 2, 3, 7, 6 },
	    { 8, 7, 3, 4, 9, 6, 5, 2, 1 }, { 6, 2, 5, 1, 7, 3, 9, 8, 4 },
	    { 3, 9, 4, 8, 6, 7, 1, 5, 2 }, { 1, 5, 2, 3, 4, 9, 7, 6, 8 },
	    { 7, 8, 6, 2, 5, 1, 4, 3, 9 }, { 2, 3, 7, 9, 1, 8, 6, 4, 5 },
	    { 5, 1, 8, 6, 3, 4, 2, 9, 7 }, { 4, 6, 9, 7, 2, 5, 8, 1, 3 } };

    boolean[][] casillasInSud1 = new boolean[][] {
	    { false, false, false, false, false, false, false, false, false },
	    { false, true, false, false, true, false, false, false, false },
	    { true, false, true, false, false, true, false, true, true },
	    { false, false, true, false, false, false, false, true, true },
	    { false, true, false, true, false, false, true, false, false },
	    { false, false, false, false, false, true, true, false, false },
	    { false, true, true, false, false, true, false, false, true },
	    { false, false, false, false, false, true, false, false, false },
	    { true, false, true, true, true, false, false, false, true } };

    int[][] solucionSudoku2 = new int[][] { { 3, 1, 6, 2, 7, 9, 8, 5, 4 },
	    { 4, 9, 5, 1, 3, 8, 7, 2, 6 }, { 7, 8, 2, 6, 4, 5, 9, 3, 1 },
	    { 2, 3, 7, 9, 5, 4, 1, 6, 8 }, { 8, 6, 4, 3, 1, 7, 2, 9, 5 },
	    { 9, 5, 1, 8, 6, 2, 3, 4, 7 }, { 1, 2, 9, 5, 8, 6, 4, 7, 3 },
	    { 6, 4, 3, 7, 2, 1, 5, 8, 9 }, { 5, 7, 8, 4, 9, 3, 6, 1, 2 } };

    boolean[][] casillasInSud2 = new boolean[][] {
	    { false, false, true, false, true, true, true, false, false },
	    { false, false, false, true, false, false, false, false, false },
	    { false, false, true, true, true, false, false, false, false },
	    { false, false, false, false, false, false, false, false, true },
	    { false, false, true, false, false, true, true, true, false },
	    { false, true, false, true, true, false, true, false, false },
	    { true, false, true, false, false, false, true, true, false },
	    { false, true, false, false, false, false, false, false, true },
	    { true, false, false, false, false, false, false, true, false } };

    /**
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	tablero = Tablero.getTablero();
	sudoku1 = new Sudoku("Identificador1", 1);
	cargarSudoku(sudoku1, casillasInSud1, solucionSudoku1);
	sudoku2 = new Sudoku("Indetificador2", 2);
	cargarSudoku(sudoku2, casillasInSud2, solucionSudoku2);
	try {
	    tablero.inicializar(null);
	} catch (NullPointerException e) {
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#getTablero()}.
     */
    public void testGetTablero() {
	tablero = null;
	assertNull(tablero);
	tablero = Tablero.getTablero();
	assertNotNull(tablero);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#inicializar(ANTpackSudokuAntiguo.Sudoku)}
     * .
     */
    public void testInicializar() {
	try {
	    tablero.inicializar(sudoku1);
	    assertEquals(sudoku1.getIdentificador(), tablero.getIdSudoku());
	    assertEquals(sudoku1.getDificultad(), tablero.getNivel());
	    assertFalse(tablero.finalDeJuego());
	    for (int i = 0; i < casillasInSud1.length; i++) {
		for (int j = 0; j < casillasInSud1[i].length; j++) {
		    try {
			assertEquals(casillasInSud1[i][j], tablero
				.isValorInicial(i + 1, j + 1));
			if (casillasInSud1[i][j]) {
			    assertFalse(tablero.isLibre(i + 1, j + 1));
			    assertEquals(solucionSudoku1[i][j], tablero
				    .getValor(i + 1, j + 1));
			} else {
			    assertTrue(tablero.isLibre(i + 1, j + 1));
			}
		    } catch (ExcepcionCoordenadaNoValida e) {
			fail();
		    } catch (ExcepcionNoHayValorEnCasilla e) {
			fail();
		    }
		}
	    }

	    tablero.inicializar(sudoku2);
	    assertEquals(sudoku2.getIdentificador(), tablero.getIdSudoku());
	    assertEquals(sudoku2.getDificultad(), tablero.getNivel());
	    assertFalse(tablero.finalDeJuego());
	    for (int i = 0; i < casillasInSud2.length; i++) {
		for (int j = 0; j < casillasInSud2[i].length; j++) {
		    try {
			assertEquals(casillasInSud2[i][j], tablero
				.isValorInicial(i + 1, j + 1));
			if (casillasInSud2[i][j]) {
			    assertFalse(tablero.isLibre(i + 1, j + 1));
			    assertEquals(solucionSudoku2[i][j], tablero
				    .getValor(i + 1, j + 1));
			} else {
			    assertTrue(tablero.isLibre(i + 1, j + 1));
			}
		    } catch (ExcepcionCoordenadaNoValida e) {
			fail();
		    } catch (ExcepcionNoHayValorEnCasilla e) {
			fail();
		    }
		}
	    }
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#setValor(int, int, int)}.
     */
    public void testSetValor() {
	try {
	    tablero.setValor(1, 1, 1);
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	} catch (Exception e) {
	    fail();
	}

	Sudoku sudoku = new Sudoku("Identificador1", 1);
	tablero.inicializar(sudoku);

	try {
	    tablero.setValor(0, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.setValor(10, 1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.setValor(1, 0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.setValor(1, 10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    tablero.setValor(i, j, 0);
		    fail();
		} catch (ExcepcionNoHaySudokuCargado e1) {
		    fail();
		} catch (ExcepcionValorNoValido e1) {
		} catch (ExcepcionCoordenadaNoValida e1) {
		    fail();
		} catch (ExcepcionValorInicial e1) {
		    fail();
		}

		try {
		    assertTrue(tablero.isLibre(i, j));
		    tablero.setValor(i, j, j);
		    assertFalse(tablero.isLibre(i, j));
		    assertFalse(tablero.isValorInicial(i, j));
		    assertEquals(j, tablero.getValor(i, j));
		} catch (ExcepcionCoordenadaNoValida e) {
		    fail();
		} catch (ExcepcionNoHaySudokuCargado e) {
		    fail();
		} catch (ExcepcionValorNoValido e) {
		    fail();
		} catch (ExcepcionValorInicial e) {
		    fail();
		} catch (ExcepcionNoHayValorEnCasilla e) {
		    fail();
		}
	    }
	}
	tablero.inicializar(sudoku1);
	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		if (casillasInSud1[i - 1][j - 1]) {
		    try {
			tablero.setValor(i, j, 5);
			fail();
		    } catch (ExcepcionValorInicial e) {
		    } catch (Exception e) {
			fail();
		    }
		}
	    }
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#quitarValor(int, int)}.
     */
    public void testQuitarValor() {
	Sudoku sudoku = new Sudoku("Identificador1", 1);
	try {
	    tablero.quitarValor(1, 1);
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	} catch (Exception e) {
	    fail();
	}
	tablero.inicializar(sudoku);
	try {
	    tablero.quitarValor(1, 1);
	    fail();
	} catch (ExcepcionNoHayValorEnCasilla e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.quitarValor(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e1) {
	    fail();
	}

	try {
	    tablero.quitarValor(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.quitarValor(1, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e1) {
	    fail();
	}

	try {
	    tablero.quitarValor(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    for (int i = 1; i <= 9; i++) {
		for (int j = 1; j <= 9; j++) {
		    assertTrue(tablero.isLibre(i, j));
		    tablero.setValor(i, j, j);
		    assertFalse(tablero.isLibre(i, j));
		    tablero.quitarValor(i, j);
		    assertTrue(tablero.isLibre(i, j));
		}
	    }
	} catch (Exception e) {
	    fail();
	}

	tablero.inicializar(sudoku1);
	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    tablero.quitarValor(i, j);
		    fail();
		} catch (ExcepcionValorInicial e) {
		    if (!casillasInSud1[i - 1][j - 1]) {
			fail();
		    }
		} catch (ExcepcionNoHayValorEnCasilla e) {
		    if (casillasInSud1[i - 1][j - 1]) {
			fail();
		    }
		} catch (Exception e) {
		}
	    }
	}
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.Tablero#hayInconsistencias(ANTpackSudokuAntiguo.TipoArea, int)}.
     */
    public void testHayInconsistencias() {
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.ZONA, 1));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	}
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.FILA, 1));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	}
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.COLUMNA, 1));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	}

	tablero.inicializar(sudoku1);
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.ZONA, 0));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}

	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.FILA, 0));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.COLUMNA, 0));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.ZONA, 10));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.FILA, 10));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    assertFalse(tablero.hayInconsistencias(TipoArea.COLUMNA, 10));
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}

	try {
	    for (int i = 1; i <= 9; i++) {
		assertFalse(tablero.hayInconsistencias(TipoArea.ZONA, i));
		assertFalse(tablero.hayInconsistencias(TipoArea.FILA, i));
		assertFalse(tablero.hayInconsistencias(TipoArea.COLUMNA, i));
	    }

	    tablero.setValor(1, 3, 5);
	    for (int i = 1; i <= 9; i++) {
		if (i == 1) {
		    assertTrue(tablero.hayInconsistencias(TipoArea.ZONA, i));
		} else {
		    assertFalse(tablero.hayInconsistencias(TipoArea.ZONA, i));
		    assertFalse(tablero.hayInconsistencias(TipoArea.FILA, i));

		}
		if (i == 3) {
		    assertTrue(tablero.hayInconsistencias(TipoArea.COLUMNA, i));

		} else {
		    assertFalse(tablero.hayInconsistencias(TipoArea.COLUMNA, i));

		}
	    }
	    tablero.setValor(1, 4, 5);
	    for (int i = 1; i <= 9; i++) {
		if (i == 1) {
		    assertTrue(tablero.hayInconsistencias(TipoArea.ZONA, i));
		    assertTrue(tablero.hayInconsistencias(TipoArea.FILA, i));

		} else {
		    assertFalse(tablero.hayInconsistencias(TipoArea.ZONA, i));
		    assertFalse(tablero.hayInconsistencias(TipoArea.FILA, i));

		}
		if (i == 3) {
		    assertTrue(tablero.hayInconsistencias(TipoArea.COLUMNA, i));

		} else {
		    assertFalse(tablero.hayInconsistencias(TipoArea.COLUMNA, i));

		}
	    }
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#getNumErrores()}.
     */
    public void testGetNumErrores() {
	try {
	    tablero.getNumErrores();
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e1) {
	}
	tablero.inicializar(sudoku1);
	try {
	    for (int i = 0; i < solucionSudoku1.length; i++) {
		for (int j = 0; j < solucionSudoku1[i].length; j++) {
		    if (!casillasInSud1[i][j]) {
			tablero.setValor(i + 1, j + 1, solucionSudoku1[i][j]);
			assertEquals(0, tablero.getNumErrores());
		    }
		}
	    }
	} catch (Exception e) {
	    fail();
	}
	tablero.inicializar(sudoku1);
	int errores = 0;

	try {
	    assertEquals(0, tablero.getNumErrores());
	    for (int i = 0; i < solucionSudoku1.length; i++) {
		for (int j = 0; j < solucionSudoku1[i].length; j++) {
		    if (!casillasInSud1[i][j]) {
			tablero.setValor(i + 1, j + 1,
				solucionSudoku1[i][j] != 1 ? 1 : 2);
			assertEquals(++errores, tablero.getNumErrores());
		    }
		}
	    }
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#isValorInicial(int, int)}.
     */
    public void testIsValorInicial() {
	try {
	    tablero.isValorInicial(1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	}

	tablero.inicializar(sudoku1);
	try {
	    tablero.isValorInicial(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHaySudokuCargado e1) {
	    fail();
	}
	try {
	    tablero.isValorInicial(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHaySudokuCargado e1) {
	    fail();
	}
	try {
	    tablero.isValorInicial(1, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHaySudokuCargado e1) {
	    fail();
	}
	try {
	    tablero.isValorInicial(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e1) {
	} catch (ExcepcionNoHaySudokuCargado e1) {
	    fail();
	}

	try {
	    for (int i = 0; i < casillasInSud1.length; i++) {
		for (int j = 0; j < casillasInSud1[i].length; j++) {
		    assertEquals(casillasInSud1[i][j], tablero.isValorInicial(
			    i + 1, j + 1));
		}
	    }
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#isLibre(int, int)}.
     */
    public void testIsLibre() {
	try {
	    tablero.isLibre(1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	}
	Sudoku sudoku = new Sudoku("Identificador1", 1);
	tablero.inicializar(sudoku);
	try {
	    tablero.isLibre(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    tablero.isLibre(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    tablero.isLibre(1, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	try {
	    tablero.isLibre(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}
	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    assertTrue(tablero.isLibre(i, j));
		    tablero.setValor(i, j, j);
		    assertFalse(tablero.isLibre(i, j));
		} catch (Exception e) {
		    fail();
		}
	    }
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#getNivel()}.
     */
    public void testGetNivel() {
	try {
	    tablero.getNivel();
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e1) {
	}
	Sudoku sudoku;
	try {
	    sudoku = new Sudoku("Identificador1", 1);
	    tablero.inicializar(sudoku);
	    assertEquals(sudoku.getDificultad(), tablero.getNivel());
	} catch (ExcepcionNoHaySudokuCargado e) {
	}

	try {
	    sudoku = new Sudoku("Identificador2", 2);
	    tablero.inicializar(sudoku);
	    assertEquals(sudoku.getDificultad(), tablero.getNivel());
	} catch (ExcepcionNoHaySudokuCargado e) {
	}

	try {
	    sudoku = new Sudoku("Identificador3", 3);
	    tablero.inicializar(sudoku);
	    assertEquals(sudoku.getDificultad(), tablero.getNivel());
	} catch (ExcepcionNoHaySudokuCargado e) {
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#finalDeJuego()}.
     */
    public void testFinalDeJuego() {
	try {
	    tablero.finalDeJuego();
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e1) {
	}
	tablero.inicializar(sudoku2);
	try {
	    for (int i = 0; i < casillasInSud2.length; i++) {
		for (int j = 0; j < casillasInSud2[i].length; j++) {
		    if (!casillasInSud2[i][j]) {
			assertFalse(tablero.finalDeJuego());
			tablero.setValor(i + 1, j + 1, solucionSudoku2[i][j]);
		    }

		}
	    }
	    assertTrue(tablero.finalDeJuego());

	    for (int i = 0; i < casillasInSud2.length; i++) {
		for (int j = 0; j < casillasInSud2[i].length; j++) {
		    if (!casillasInSud2[i][j]) {
			tablero.quitarValor(i + 1, j + 1);
			assertFalse(tablero.finalDeJuego());

		    }

		}
	    }
	} catch (Exception e) {
	    fail();
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#getValor(int, int)}.
     */
    public void testGetValor() {
	Sudoku sudoku = new Sudoku("", 1);
	try {
	    tablero.getValor(1, 1);
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	} catch (Exception e) {
	    fail();
	}
	tablero.inicializar(sudoku);
	try {
	    tablero.getValor(1, 0);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.getValor(1, 10);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.getValor(0, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.getValor(10, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	} catch (Exception e) {
	    fail();
	}

	try {
	    tablero.getValor(1, 1);
	    fail();
	} catch (ExcepcionCoordenadaNoValida e) {
	    fail();
	} catch (Exception e1) {
	}

	for (int i = 1; i <= 9; i++) {
	    for (int j = 1; j <= 9; j++) {
		try {
		    tablero.setValor(i, j, j);
		    assertEquals(j, tablero.getValor(i, j));
		} catch (Exception e) {
		    fail();
		}
	    }
	}
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.Tablero#getIdSudoku()}.
     */
    public void testGetIdSudoku() {
	try {
	    tablero.getIdSudoku();
	    fail();
	} catch (ExcepcionNoHaySudokuCargado e) {
	}
	Sudoku sudoku;
	try {
	    sudoku = new Sudoku("Identificador1", 1);
	    tablero.inicializar(sudoku);
	    assertEquals(sudoku.getIdentificador(), tablero.getIdSudoku());
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}

	try {
	    sudoku = new Sudoku("Identificador2", 2);
	    tablero.inicializar(sudoku);
	    assertEquals(sudoku.getIdentificador(), tablero.getIdSudoku());
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}

	try {
	    sudoku = new Sudoku("Identificador3", 3);
	    tablero.inicializar(sudoku);
	    assertEquals(sudoku.getIdentificador(), tablero.getIdSudoku());
	} catch (ExcepcionNoHaySudokuCargado e) {
	    fail();
	}

    }

    /**
     * @param pSudoku
     * @param pCasillasInic
     * @param pDatosSolucion
     */
    private void cargarSudoku(Sudoku pSudoku, boolean[][] pCasillasInic,
	    int[][] pDatosSolucion) {
	for (int i = 0; i < pDatosSolucion.length; i++) {
	    for (int j = 0; j < pDatosSolucion[i].length; j++) {
		try {
		    if (pCasillasInic[i][j]) {
			pSudoku.setValorInicial(i + 1, j + 1,
				pDatosSolucion[i][j]);
		    } else {
			pSudoku.setValor(i + 1, j + 1, pDatosSolucion[i][j]);
		    }
		} catch (Exception e) {
		    fail();
		}
	    }
	}
    }
}
