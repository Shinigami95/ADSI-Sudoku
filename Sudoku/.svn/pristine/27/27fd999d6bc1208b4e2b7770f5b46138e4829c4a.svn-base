package packSudoku;

import java.util.Observable;

import packSudoku.excepciones.ExcepcionCoordenadaNoValida;
import packSudoku.excepciones.ExcepcionNoHayValorEnCasilla;
import packSudoku.excepciones.ExcepcionValorInicial;
import packSudoku.excepciones.ExcepcionValorNoValido;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;

/**
 * The Class Tablero.
 */
public class Tablero extends Observable {

    /** The mi tablero. */
    private static Tablero miTablero = new Tablero();

    /** The sudoku. */
    private Sudoku sudoku;

    /** The matriz juego. */
    private Matriz matrizJuego;

    /** The terminado. */
    private boolean terminado = false;

    /**
     * Instantiates a new tablero.
     */
    private Tablero() {
	matrizJuego = new Matriz();
    }

    /**
     * Obt tablero.
     * 
     * @return the tablero
     */
    public static Tablero getTablero() {
	return miTablero;
    }

    /**
     * inicializar.
     * 
     * @param pSudoku
     *            Sudoku
     * @todo Implement this packSudoku.ITablero method
     */
    public void inicializar(Sudoku pSudoku) {
	int valor;
	sudoku = pSudoku;
	matrizJuego = new Matriz();
	terminado = false;
	if (pSudoku != null) {
	    for (int fila = 1; fila <= 9; fila++) {
		for (int columna = 1; columna <= 9; columna++) {
		    try {
			if (pSudoku.isInicial(fila, columna)) {
			    valor = pSudoku.getValor(fila, columna);
			    try {
				matrizJuego.setValorInicial(fila, columna,
					valor);
			    } catch (ExcepcionCoordenadaNoValida e) {
			    } catch (ExcepcionValorNoValido e) {
			    } catch (ExcepcionValorInicial e) {
			    }
			}
		    } catch (ExcepcionCoordenadaNoValida e) {
		    } catch (ExcepcionNoHayValorEnCasilla e) {
		    }
		}
	    }
	setChanged();
	notifyObservers();
	}
	else {
	    setChanged();
	    notifyObservers();
	    throw new NullPointerException();
	}
    }

    /**
     * asgValor.
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @param pValor
     *            int
     * @throws ExcepcionNoHaySudokuCargado
     * @throws ExcepcionValorNoValido
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHaySudokuCargado
     * @throws ExcepcionValorInicial
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionValorNoValido
     * @todo Implement this packSudoku.ITablero method
     */
    public void setValor(int pFila, int pColumna, int pValor)
	    throws ExcepcionNoHaySudokuCargado, ExcepcionValorNoValido,
	    ExcepcionCoordenadaNoValida, ExcepcionValorInicial {
	if (sudoku != null) {
	    matrizJuego.setValor(pFila, pColumna, pValor);
	    if (matrizJuego.isCompleto() && getNumErrores() == 0) {
		terminado = true;
	    }
	    setChanged();
	    notifyObservers();
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * quitarValor.
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHayValorEnCasilla
     * @throws ExcepcionValorInicial
     * @throws ExcepcionNoHaySudokuCargado
     * @todo Implement this packSudoku.ITablero method
     */
    public void quitarValor(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida, ExcepcionValorInicial,
	    ExcepcionNoHayValorEnCasilla, ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    matrizJuego.quitarValor(pFila, pColumna);
	    terminado = false;
	    setChanged();
	    notifyObservers(); 
	} else {
	   throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Hay inconsistencias.
     * 
     * @param pArea
     *            the area
     * @param pIdArea
     *            the id area
     * @return true, if successful
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHaySudokuCargado
     */
    public boolean hayInconsistencias(TipoArea pArea, int pIdArea)
	    throws ExcepcionCoordenadaNoValida, ExcepcionNoHaySudokuCargado {

	if (sudoku != null) {
	    return matrizJuego.hayInconsistencias(pArea, pIdArea);
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Gets the num errores.
     * 
     * @return the num errores
     * @throws ExcepcionNoHaySudokuCargado
     */
    public int getNumErrores() throws ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    return sudoku.getNumErrores(matrizJuego);
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * jugar.
     * 
     * @param pFila
     *            the fila
     * @param pColumna
     *            the columna
     * @return boolean
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHaySudokuCargado
     * @todo Implement this packSudoku.ITablero method
     */

    public boolean isValorInicial(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida, ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    return matrizJuego.isInicial(pFila, pColumna);
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Esta libre.
     * 
     * @param pFila
     *            the fila
     * @param pColumna
     *            the columna
     * @return true, if successful
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHaySudokuCargado
     */
    public boolean isLibre(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida, ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    return matrizJuego.isLibre(pFila, pColumna);
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Obt nivel.
     * 
     * @return the int
     * @throws ExcepcionNoHaySudokuCargado
     *             the no hay sudoku cargado exception
     */
    public int getNivel() throws ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    return sudoku.getDificultad();
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Final de juego.
     * 
     * @return true, if successful
     * @throws ExcepcionNoHaySudokuCargado
     */
    public boolean finalDeJuego() throws ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    return terminado;
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Obt valor casilla.
     * 
     * @param pFila
     *            the fila
     * @param pColumna
     *            the columna
     * @return the int
     * @throws ExcepcionNoHayValorEnCasilla
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHaySudokuCargado 
     */
    public int getValor(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida, ExcepcionNoHayValorEnCasilla, ExcepcionNoHaySudokuCargado {
	if (sudoku != null) {
	    return matrizJuego.getValor(pFila, pColumna);
	} else {
	    throw new ExcepcionNoHaySudokuCargado();
	}
    }

    /**
     * Obt id sudoku.
     * 
     * @return the string
     * @throws ExcepcionNoHaySudokuCargado
     *             the no hay sudoku cargado exception
     */
    public String getIdSudoku() throws ExcepcionNoHaySudokuCargado {
	if (sudoku != null)
	    return sudoku.getIdentificador();
	else
	    throw new ExcepcionNoHaySudokuCargado();
    }

}
