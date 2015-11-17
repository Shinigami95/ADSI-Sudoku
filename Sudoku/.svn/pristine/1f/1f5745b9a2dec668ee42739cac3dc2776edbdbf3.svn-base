package packSudoku;

import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

import packSudoku.excepciones.ExcepcionCoordenadaNoValida;
import packSudoku.excepciones.ExcepcionNoHayFicheroSudokus;
import packSudoku.excepciones.ExcepcionValorInicial;
import packSudoku.excepciones.ExcepcionValorNoValido;

public enum ListaSudokus
{
    /** The INSTANCIA. */
    INSTANCIA;
    private ListaOrdenadaGenerica<Sudoku> listaSudokus;

    /**
     * Instantiates a new lista sudokus.
     */
    private ListaSudokus()
    {
	listaSudokus = new ListaOrdenadaGenerica<Sudoku>(
		new ComparadorSudokus());
    }

    /**
     * cargar
     * <p>
     * PRE: pFichero contiene el nombre del fichero que contiene la lista de
     * sudokus
     * <p>
     * <p>
     * POST: Se ha cargado la lista de sudokus
     * <p>
     * 
     * @param pFichero
     *            String
     * @throws ExcepcionNoHayFicheroSudokus 
     * @todo Implement this packSudoku.IListaSudokus method
     */
    public void cargar(String pFichero) throws ExcepcionNoHayFicheroSudokus 
    {
	
	try {
	    Scanner entradaSudokus = null;
	    entradaSudokus = new Scanner(new File(pFichero));
	    listaSudokus = new ListaOrdenadaGenerica<Sudoku>(
	    	new ComparadorSudokus());

	    while (entradaSudokus.hasNext())
	    {
	        listaSudokus.anadirElemento(cargarSudoku(entradaSudokus));
	    }
	    entradaSudokus.close();
	} catch (FileNotFoundException e) {
	   throw new ExcepcionNoHayFicheroSudokus();
	}
    }

    private Sudoku cargarSudoku(Scanner pEntrada)
    {
	String id = pEntrada.next();
	int dificultad = pEntrada.nextInt();
	Sudoku sudoku = new Sudoku(id, dificultad);
	// Cargar Matriz sudoku
	cargarMatrizSudoku(sudoku, pEntrada, true);
	cargarMatrizSudoku(sudoku, pEntrada, false);
	return sudoku;
    }

    private void cargarMatrizSudoku(Sudoku pSudoku, Scanner pEntrada,
	    boolean pValoresIniciales)
    {
	String linea;
	for (int i = 1; i <= 9; i++)
	{
	    linea = pEntrada.next();
	    cargarFilaSudoku(pSudoku, i, linea, pValoresIniciales);
	}
    }

    private void cargarFilaSudoku(Sudoku pSudoku, int pFila,
	    String pNumerosFila, boolean pValoresIniciales)
    {
	int valor;
	for (int columna = 1; columna <= 9; columna++)
	{
	    valor = Integer.parseInt(pNumerosFila.substring(columna - 1,
		    columna));
	    if (pValoresIniciales)
	    {
		if (valor != 0)
		{

		    try
		    {
			pSudoku.setValorInicial(pFila, columna, valor);
		    } catch (ExcepcionCoordenadaNoValida e)
		    {
		    } catch (ExcepcionValorNoValido e)
		    {
		    } catch (ExcepcionValorInicial e)
		    {
		    }
		}
	    } else
	    {
		try
		{
		    if (!pSudoku.isInicial(pFila, columna))
		    {
		        pSudoku.setValor(pFila, columna, valor);
		    }
		} catch (ExcepcionCoordenadaNoValida e)
		{
		} catch (ExcepcionValorNoValido e)
		{
		} catch (ExcepcionValorInicial e)
		{
		}
	    }
	}
    }

    /**
     * getIterador
     * <p>
     * PRE: pDific contiene el nivel de dificultad del sudoku deseado
     * <p>
     * <p>
     * POST: Devuelve un sudoku que apunta al primer sudoku con el nivel de
     * dificultad especificado
     * <p>
     * 
     * @return Iterator
     * @param pDific
     *            int
     * @todo Implement this packSudoku.IListaSudokus method
     */
    public Iterator<Sudoku> getIterador(int pDific)
    {
	return listaSudokus.getIterador(new CondNivelMayorOIgual(pDific));

    }

    /**
     * @return
     * @see packSudoku.ListaOrdenadaGenerica#toString()
     */
    public String toString()
    {
	return listaSudokus.toString();
    }

}
