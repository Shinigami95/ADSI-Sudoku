package ANTpackSudokuAntiguo;

import packExcepciones.ExcepcionCoordenadaNoValida;
import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;
import packExcepciones.ExcepcionValorNoValido;


public class Sudoku {

    // Identificador del sudoku
    private String identificador;
    // Nivel de dificultad
    private int dificultad;
    // Matriz de casillas
    private Matriz matriz;

    /**
     * Sudoku
     * <p> POST: crea un sudoku con el identificador y dificultad indicados y con
     *         matriz de casillas vac�a.<p>
     * @param pIdentificador String
     * @param pDificultad int
     */
    public Sudoku(String pIdentificador, int pDificultad) {
        identificador = pIdentificador;
        dificultad = pDificultad;
        matriz = new Matriz();
    }

    /**
     * obtDificultad
     *
     * <p> POST: devuelve el nivel de dificultad del sudoku.
     *
     * <p>
     *
     * @return int
     * @todo Implement this packsudoku.ISudoku method
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * obtIdentificador
     *
     * <p> POST: devuelve el identificador del sudoku.
     *
     * <p>
     *
     * @return String
     * @todo Implement this packsudoku.ISudoku method
     */
    public String getIdentificador() {
        return identificador;
    }

   

    /**
     * asgValorInicial
     *
     * <p> POST: asigna el valor inicial a la casilla que est� en la
     * coordenada indicada.
     *
     * @param pFila int
     * @param pColumna int
     * @param pValor int
     * @throws ExcepcionValorInicial 
     * @throws ExcepcionValorNoValido 
     * @throws ExcepcionCoordenadaNoValida 
     * @todo Implement this packsudoku.ISudoku method
     */
    public void setValorInicial(int pFila, int pColumna, int pValor) throws ExcepcionCoordenadaNoValida, ExcepcionValorNoValido, ExcepcionValorInicial {
        matriz.setValorInicial(pFila,pColumna,pValor);
    }

    /**
     * asgValor
     *
     * <p> POST: asigna el valor inicial a la casilla que est� en la
     * coordenada indicada.
     *
     * @param pFila int
     * @param pColumna int
     * @param pValor int
     * @throws ExcepcionValorInicial 
     * @throws ExcepcionCoordenadaNoValida 
     * @throws ExcepcionValorNoValido 
     * @todo Implement this packsudoku.ISudoku method
     */
    public void setValor(int pFila, int pColumna, int pValor) throws ExcepcionValorNoValido, ExcepcionCoordenadaNoValida, ExcepcionValorInicial {
        matriz.setValor(pFila,pColumna,pValor);
    }

    /**
     * @param pMatrizJuego
     * @return
     */
    public int getNumErrores(Matriz pMatrizJuego)
    {
	return pMatrizJuego.getNumErrores(matriz);
    }

    /**
     * @param pFila
     * @param pColumna
     * @return
     * @throws ExcepcionCoordenadaNoValida 
     */
    public boolean isInicial(int pFila, int pColumna) throws ExcepcionCoordenadaNoValida
    {
	return matriz.isInicial(pFila, pColumna);
    }
    
    public int getValor(int pFila, int pColumna) throws ExcepcionCoordenadaNoValida, ExcepcionNoHayValorEnCasilla
    {
	return matriz.getValor(pFila, pColumna);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
	    return String.format("(%1$s,%2$d)", getIdentificador(),getDificultad());
    }
}
