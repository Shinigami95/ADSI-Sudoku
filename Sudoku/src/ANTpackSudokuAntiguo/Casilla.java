package ANTpackSudokuAntiguo;

import packExcepciones.ExcepcionNoHayValorEnCasilla;
import packExcepciones.ExcepcionValorInicial;

public class Casilla
{

    // Indica si es un valor inicial
    private boolean valorInicial;

    // Valor contenido en la casilla
    private int     valor;

    // Estado de ocupaci�n de la casilla
    private boolean ocupada;

    /**
     * Casilla
     * <p>
     * POST: crea una casilla que est� libre y no contiene un valor inicial.
     * <p>
     */
    public Casilla()
    {
	valorInicial = false;
	ocupada = false;
    }

    /**
     * asgValorInicial
     * <p>
     * POST: inicializa el valor de la casilla con el valor indicado, pasando a
     * estar ocupada.
     * 
     * @param pValor
     *            int
     * @throws ExcepcionValorInicial
     */
    public void setValorInicial(int pValor) throws ExcepcionValorInicial
    {
	if (isInicial())
	{
	    throw new ExcepcionValorInicial();
	} else
	{
	    valor = pValor;
	    ocupada = true;
	    valorInicial = true;
	}
    }

    /**
     * asgValor
     * <p>
     * POST: inicializa el valor de la casilla con el valor indicado.
     * 
     * @param pValor
     *            int
     * @throws ExcepcionValorInicial
     */
    public void setValor(int pValor) throws ExcepcionValorInicial
    {
	if (isInicial())
	{
	    throw new ExcepcionValorInicial();
	} else
	{
	    valor = pValor;
	    ocupada = true;
	    valorInicial = false;
	}
    }

    /**
     * quitarValor
     * <p>
     * POST: indica que la casilla no est� ocupada.
     * <p>
     * 
     * @throws ExcepcionValorInicial
     * @throws ExcepcionNoHayValorEnCasilla
     */
    public void quitarValor() throws ExcepcionValorInicial,
	    ExcepcionNoHayValorEnCasilla
    {
	if (isInicial())
	{
	    throw new ExcepcionValorInicial();
	} else if (isLibre())
	{
	    throw new ExcepcionNoHayValorEnCasilla();
	} else
	{
	    ocupada = false;
	}
    }

    /**
     * estaLibre
     * <p>
     * POST: devuelve true si la casilla esta libre y false en caso contrario
     * <p>
     * 
     * @return boolean
     */
    public boolean isLibre()
    {
	return !ocupada;
    }

    /**
     * esInicial
     * <p>
     * POST: devuelve true si la casilla contiene un valor inicial y false en
     * caso contrario.
     * <p>
     * 
     * @return boolean
     */
    public boolean isInicial()
    {
	return valorInicial;
    }

    /**
     * obtValor
     * <p>
     * PRE: La casilla est� ocupada.
     * <p>
     * <p>
     * POST: devuelve el valor contenido en la casilla.
     * <p>
     * 
     * @return int
     * @throws ExcepcionNoHayValorEnCasilla 
     */
    public int getValor() throws ExcepcionNoHayValorEnCasilla
    {
	if (isLibre())
	{
	    throw new ExcepcionNoHayValorEnCasilla();
	} else
	{
	    return valor;
	}
    }

    /**
     * mismoValor
     * <p>
     * PRE: ambas casillas est�n ocupadas.
     * <p>
     * <p>
     * POST: devuelve true si ambas casillas contienen el mismo valor y false en
     * caso contrario.
     * <p>
     * 
     * @param pCasilla
     *            Casilla
     * @return boolean
     * @throws ExcepcionNoHayValorEnCasilla 
     */
    public boolean mismoValor(Casilla pCasilla) throws ExcepcionNoHayValorEnCasilla
    {
	return this.getValor() == pCasilla.getValor();
    }

}
