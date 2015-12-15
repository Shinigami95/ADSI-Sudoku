package packSudokuAntiguo;

import packSudoku.excepciones.ExcepcionCoordenadaNoValida;
import packSudoku.excepciones.ExcepcionNoHayValorEnCasilla;
import packSudoku.excepciones.ExcepcionValorInicial;
import packSudoku.excepciones.ExcepcionValorNoValido;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Jon A. Elorriaga
 * @author Bego Ferrero
 * @author Mikel Larra�aga
 * @version 1.0
 */
public class Matriz
{

    // ATRIBUTOS DE LA CLASE
    // Dimensi�n del Sudoko
    private static final int DIMENSION = 9;

    // Matriz para recoger los valores del Sudoku
    private Casilla[][]      matrizCasillas;

    // Constructora
    /**
     * Instantiates a new matriz.
     */
    public Matriz()
    {
	matrizCasillas = new Casilla[DIMENSION][DIMENSION];
	for (int i = 0; i < DIMENSION; i++)
	{
	    for (int j = 0; j < DIMENSION; j++)
	    {
		matrizCasillas[i][j] = new Casilla();

	    }
	}
    }

    /**
     * asgValorInicial
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @param pValor
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionValorNoValido
     * @throws ExcepcionValorInicial
     * @todo Implement this packsudoku.IMatriz method
     */
    public void setValorInicial(int pFila, int pColumna, int pValor)
	    throws ExcepcionCoordenadaNoValida, ExcepcionValorNoValido,
	    ExcepcionValorInicial
    {
	if (pValor < 1 || pValor > DIMENSION)
	{
	    throw new ExcepcionValorNoValido();
	} else
	{
	    Casilla unaCasilla = getCasilla(pFila, pColumna);
	    unaCasilla.setValorInicial(pValor);
	}
    }

    /**
     * asgValor
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @param pValor
     *            int
     * @throws ExcepcionValorNoValido
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionValorInicial
     * @todo Implement this packsudoku.IMatriz method
     */
    public void setValor(int pFila, int pColumna, int pValor)
	    throws ExcepcionValorNoValido, ExcepcionCoordenadaNoValida,
	    ExcepcionValorInicial
    {
	if (pValor < 1 || pValor > DIMENSION)
	{
	    throw new ExcepcionValorNoValido();
	} else
	{
	    Casilla unaCasilla = getCasilla(pFila, pColumna);
	    unaCasilla.setValor(pValor);
	}
    }

    /**
     * quitarValor
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHayValorEnCasilla
     * @throws ExcepcionValorInicial
     * @todo Implement this packsudoku.IMatriz method
     */
    public void quitarValor(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida, ExcepcionValorInicial,
	    ExcepcionNoHayValorEnCasilla
    {
	Casilla unaCasilla = getCasilla(pFila, pColumna);
	unaCasilla.quitarValor();
    }

    /**
     * estaLibre
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @todo Implement this packsudoku.IMatriz method
     */
    public boolean isLibre(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida
    {
	Casilla unaCasilla = getCasilla(pFila, pColumna);
	return unaCasilla.isLibre();
    }

    /**
     * esInicial
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @todo Implement this packsudoku.IMatriz method
     */
    public boolean isInicial(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida
    {
	Casilla unaCasilla = getCasilla(pFila, pColumna);
	return unaCasilla.isInicial();
    }

    /**
     * obtValor
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @throws ExcepcionNoHayValorEnCasilla
     * @todo Implement this packsudoku.IMatriz method
     */
    public int getValor(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida, ExcepcionNoHayValorEnCasilla
    {
	Casilla unaCasilla = getCasilla(pFila, pColumna);
	return unaCasilla.getValor();
    }

    /**
     * descubrirInconsistencias
     * 
     * @param pTipo
     *            char
     * @param pIdentificador
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @todo Implement this packsudoku.IMatriz method
     */
    public boolean hayInconsistencias(TipoArea pTipo, int pIdentificador)
	    throws ExcepcionCoordenadaNoValida
    {
	if (pIdentificador < 1 || pIdentificador > 9)
	{
	    throw new ExcepcionCoordenadaNoValida();
	} else
	{
	    switch (pTipo)
	    {
		case FILA:
		    return hayRepetidosFila(pIdentificador);
		case COLUMNA:
		    return hayRepetidosColumna(pIdentificador);
		default:
		    return hayRepetidosZona(pIdentificador);
	    }
	}
    }

    /**
     * comprobarSolucion
     * 
     * @param pMatrizOriginal
     *            Matriz
     * @todo Implement this packsudoku.IMatriz method Supongo que
     *       pMatrizOriginal contiene la matriz completa y el metodo se invoca
     *       desde la matriz correspondiente a la situaci�n actual de la
     *       resoluci�n del sudoku
     */
    public int getNumErrores(Matriz pMatrizOriginal)
    {
	int contador = 0;
	Casilla casillaOriginal, casillaUsuario;
	for (int fila = 1; fila <= DIMENSION; fila++)
	{
	    for (int col = 1; col <= DIMENSION; col++)
	    {
		try
		{
		    casillaOriginal = pMatrizOriginal.getCasilla(fila, col);
		    casillaUsuario = getCasilla(fila, col);
		    try
		    {
			if (!casillaUsuario.mismoValor(casillaOriginal))
			{
			    contador = contador + 1;
			}
		    } catch (ExcepcionNoHayValorEnCasilla e)
		    {
		    }
		} catch (ExcepcionCoordenadaNoValida e)
		{
		}
	    }
	}
	return contador;
    }

    /**
     * obtCasilla
     * 
     * @param pFila
     *            int
     * @param pColumna
     *            int
     * @throws ExcepcionCoordenadaNoValida
     * @todo Implement this packsudoku.IMatriz method
     */
    private Casilla getCasilla(int pFila, int pColumna)
	    throws ExcepcionCoordenadaNoValida
    {
	try {
	    return matrizCasillas[pFila - 1][pColumna - 1];
	}
	catch(ArrayIndexOutOfBoundsException e)
	{
	    throw new ExcepcionCoordenadaNoValida();
	}
    }

    /**
     * hayRepetidosFila
     * 
     * @param pFila
     *            int
     * @todo Implement this packsudoku.IMatriz method
     */
    private boolean hayRepetidosFila(int pFila)
    {
	boolean repe = false;
	int fila = pFila - 1;
	int colIni = 0;
	int colFin = 0;
	Casilla unaCas, dosCas;
	while (colIni < DIMENSION - 1 && !repe)
	{
	    colFin = colIni + 1;
	    unaCas = matrizCasillas[fila][colIni];
	    if (!unaCas.isLibre())
	    {
		while (colFin < DIMENSION && !repe)
		{
		    dosCas = matrizCasillas[fila][colFin];
		    try
		    {
			if (unaCas.mismoValor(dosCas))
			{
			    repe = true;
			}
		    } catch (ExcepcionNoHayValorEnCasilla e)
		    {
		    }

		    colFin = colFin + 1;
		}
	    }
	    colIni = colIni + 1;
	}
	return repe;
    }

    /**
     * hayRepetidosColumna
     * 
     * @param pColumna
     *            int
     * @todo Implement this packsudoku.IMatriz method
     */
    private boolean hayRepetidosColumna(int pColumna)
    {
	boolean repe = false;
	int filaIni = 0;
	int filaFin = 0;
	Casilla unaCas, dosCas;
	int columna = pColumna - 1;
	while (filaIni < DIMENSION - 1 && !repe)
	{
	    filaFin = filaIni + 1;
	    unaCas = matrizCasillas[filaIni][columna];
	    if (!unaCas.isLibre())
	    {
		while (filaFin < DIMENSION && !repe)
		{
		    dosCas = matrizCasillas[filaFin][columna];
		    try
		    {
			if (unaCas.mismoValor(dosCas))
			{
			    repe = true;
			}
		    } catch (ExcepcionNoHayValorEnCasilla e)
		    {
		    }

		    filaFin = filaFin + 1;
		}
	    }
	    filaIni = filaIni + 1;
	}
	return repe;
    }

    /**
     * revisarPivote
     * 
     * @param pIdZona
     *            int
     * @param pFilaPiv
     *            int
     * @param pColPiv
     *            int
     * @throws ExcepcionNoHayValorEnCasilla
     * @todo Implement this packsudoku.IMatriz method
     */
    private boolean revisarPivote(int pIdZona, int pFilaPiv, int pColPiv)
    {
	boolean repe = false;
	int auxZona = pIdZona / 3;
	int filaIni = auxZona * 3;
	int filaFin = filaIni + 2;
	int colIni = (pIdZona % 3) * 3;
	int colFin = colIni + 2;
	Casilla pivote = matrizCasillas[pFilaPiv][pColPiv];
	int fila = pFilaPiv;
	int col = pColPiv + 1;
	try
	{
	    int piv = matrizCasillas[pFilaPiv][pColPiv].getValor();
	    while (fila <= filaFin && !repe)
	    {
		while (col <= colFin && !repe)
		{
		    try
		    {
			if (piv == matrizCasillas[fila][col].getValor())
			{
			    repe = true;
			}
		    } catch (ExcepcionNoHayValorEnCasilla e)
		    {
		    }
		    col = col + 1;
		}
		fila = fila + 1;
		col = colIni;
	    }
	} catch (ExcepcionNoHayValorEnCasilla e)
	{
	}

	return repe;
    }

    /**
     * hayRepetidosZona
     * 
     * @param pIdZona
     *            int
     * @throws ExcepcionNoHayValorEnCasilla
     * @todo Implement this packsudoku.IMatriz method
     */
    private boolean hayRepetidosZona(int pIdZona)
    {
	int zona = pIdZona - 1;// S�lo si las zonas se numeran de 1-9
	boolean repe = false;
	int auxZona = zona / 3;
	int filaIni = auxZona * 3;
	int filaFin = filaIni + 2;
	int colIni = (zona % 3) * 3;
	int colFin = colIni + 2;
	int fila = filaIni;
	int col = colIni;
	while (fila <= filaFin && !repe)
	{
	    while (col <= colFin && !repe)
	    {
		repe = revisarPivote(zona, fila, col);
		col = col + 1;
	    }
	    fila = fila + 1;
	    col = colIni;
	}
	return repe;
    }

    /**
     * Esta completo.
     * 
     * @return true, if successful
     */
    public boolean isCompleto()
    {
	boolean faltan = false;
	int fila = 1;
	int col;
	while (fila <= DIMENSION && !faltan)
	{
	    col = 1;
	    while (col <= DIMENSION && !faltan)
	    {
		try
		{
		    faltan = getCasilla(fila, col).isLibre();
		} catch (ExcepcionCoordenadaNoValida e)
		{
		}
		col = col + 1;
	    }
	    fila = fila + 1;
	}
	return !faltan;
    }

}
