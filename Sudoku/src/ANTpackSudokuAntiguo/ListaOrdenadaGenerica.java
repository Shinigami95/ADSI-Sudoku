/**
 * 
 */
package ANTpackSudokuAntiguo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ListaOrdenadaGenerica<E>
{
    private List<E>     listaElementos;

    private Comparator<E> comp;

    /**
     * ESPECIFICACION DEL METODO
     */
    public ListaOrdenadaGenerica(Comparator<E> pComp)
    {
	comp = pComp;
	listaElementos = new Vector<E>();
    }

   
    /**
     * ESPECIFICACION DEL METODO
     */
    public void anadirElemento(E pElem)
    {
	int pos = 0;
	if (listaElementos.isEmpty())
	{
	    listaElementos.add(pElem);
	} else
	{
	    while (pos < listaElementos.size()
		    && comp.compare(listaElementos.get(pos), pElem) > 0)
	    {
		pos++;
	    }
	    listaElementos.add(pos, pElem);
	}
    }

    /**
     * ESPECIFICACION DEL METODO
     */
    public boolean existeElementoSatisfaceCondicion(ICondicion<E> pCond)
    {
	boolean encontrado = false;
	Iterator<E> it = getIterador();
	while (!encontrado && it.hasNext())
	{
	    if (pCond.satisfaceCondicion(it.next()))
	    {
		encontrado = true;
	    }
	}
	return encontrado;
    }
    
    public String toString()
    {
	return listaElementos.toString();
    }
    
    /**
     * ESPECIFICACION DEL METODO
     */
    public Iterator<E> getIterador()
    {
	return listaElementos.iterator();
    }

    /**
     * getIterador
     * 
     * <p>Este metodo, dado un objeto que representa una condicion, devuelve
     * un iterador. Con dicho iterador solo se recorren los elementos de la
     * lista que satisfacen la condicion especificada.</p>
     * 
     * @param pCond Condicion que deben cumplir los elementos de la lista             
     * @return Iterator<E> devuelve una nueva instancia de la clase 
     * IteradorCondicion. 
     * @see ANTpackSudokuAntiguo.ListaOrdenadaGenerica.IteradorCondicion IteradorCondicion 
     */
    public Iterator<E> getIterador(ICondicion<E> pCond)
    {
	return new IteradorCondicion(pCond);
    }

    /**
     * 
     * IteradorCondicion
     * 
     * <p>Clase privada interna (private inner class). Esta clase implementa
     * el patron Iterador. Al definir esta clase dentro de la clase 
     * ListaOrdenadaGenerica, se puede acceder a los atributos privados
     * de dicha clase.</p>
     * 
     * <p>Se ha definido esta clase para permitir acceder solo a los elementos
     * de la lista que satisfacen la condicion especificada.</p>
     *
     */
    private class IteradorCondicion implements Iterator<E>
    {
	private int posActual;

	/**
	 * IteradorCondicion
	 * 
	 * <p>Crea una nueva instancia de IteradorCondicion. Este constructor 
	 * recibe como parametro la condicion que deben cumplir los elementos
	 * de la lista para ser accesibles mediante este iterador.</p>
	 * 
	 * @param pCond la condicion
	 */
	public IteradorCondicion(ICondicion<E> pCond)
	{
	    posActual = buscarPosElemento(pCond);
	}

	/**
	 * buscarPosElemento
	 * 
	 * <p> Dada una condicion, devuelve la posición del siguiente elemento de la lista que satisface
	 * dicha condicion.</p>
	 * 
	 * @param pCond la condicion que debe satisfacer el elemento de la lista que se
	 * busca
	 * 
	 * @return la posicion del siguiente elemento que satisface la condicion.
	 * Si no hay más elementos que satisfagan la condición, devuelve
	 * el número de elementos de la lista (posicion del ultimo elemento
	 * +1).
	 */
	private int buscarPosElemento(ICondicion<E> pCond)
	{
	    int pos = 0;
	    boolean encontrado = false;
	    Iterator<E> it = getIterador();
	    while (!encontrado && it.hasNext())
	    {
		if (pCond.satisfaceCondicion(it.next()))
		{
		    encontrado = true;
		} else
		{
		    pos++;
		}
	    }
	    return pos;
	}

	/**
	 * hasNext
	 * 
	 * <p>Devuelve true si existen más elementos accesibles mediante este
	 * iterador en la lista y false en caso contrario.</p>
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext()
	{
	    return posActual < listaElementos.size();
	}

	/**
	 * next
	 * 
	 * <p>Devuelve el siguiente elemento de la lista accesible mediante 
	 * este iterador.</p>
	 * 
	 * @see java.util.Iterator#next()
	 */
	public E next()
	{
	    if (hasNext())
	    {
		return listaElementos.get(posActual++);
	    } else
	    {
		throw new NoSuchElementException();
	    }
	}

	/**
	 * remove
	 * 
	 * <p> Este metodo se utiliza para eliminar el ultimo elemento de la
	 * lista al que se ha accedido con este iterador. En este caso no queremos
	 * permitir el borrado, por lo que este metodo no realiza ninguna operacion.</p>
	 * 
	 * @see java.util.Iterator#remove()
	 */
	public void remove()
	{
	}

    }
}
