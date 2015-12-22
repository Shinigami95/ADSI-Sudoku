/**
 * 
 */
package packSudoku;

import java.util.Comparator;
import java.util.Iterator;

import ANTpackSudokuAntiguo.ICondicion;
import ANTpackSudokuAntiguo.ListaOrdenadaGenerica;
import junit.framework.TestCase;

/**
 * @author mikel
 */
public class ListaOrdenadaGenericaTest extends TestCase
{

    private ListaOrdenadaGenerica<Integer> listaNumeros = null;

    private ListaOrdenadaGenerica<String>  listaStrings = null;

    private Comparator<Integer>	compNumeros  = new Comparator<Integer>()
		{
		    public int compare(Integer pO1, Integer pO2)
		    {
			return pO1 - pO2;
		    }
		 };

    private Comparator<String> compStrings  = new Comparator<String>()
		{
		    public int compare(String pO1, String pO2)
		    {
			return pO1.compareTo(pO2);
		    }
		};

    /**
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	listaNumeros = new ListaOrdenadaGenerica<Integer>(compNumeros);
	listaStrings= new ListaOrdenadaGenerica<String>(compStrings);
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaOrdenadaGenerica#ListaOrdenadaGenerica(java.util.Comparator)}
     * .
     */
    public void testListaOrdenadaGenerica()
    {
	assertNotNull(listaNumeros);
	assertNotNull(listaStrings);
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaOrdenadaGenerica#anadirElemento(java.lang.Object)}
     * .
     */
    public void testAnadirElemento()
    {
	Iterator<Integer> itNumeros = listaNumeros.getIterador();
	assertFalse(itNumeros.hasNext());
	
	listaNumeros.anadirElemento(5);
	itNumeros = listaNumeros.getIterador();
	assertTrue(itNumeros.hasNext());
	assertEquals(5, itNumeros.next().intValue());
	
	listaNumeros.anadirElemento(1);
	itNumeros = listaNumeros.getIterador();
	assertTrue(itNumeros.hasNext());
	assertEquals(5, itNumeros.next().intValue());
	assertEquals(1, itNumeros.next().intValue());
	
	listaNumeros.anadirElemento(3);
	itNumeros = listaNumeros.getIterador();
	assertTrue(itNumeros.hasNext());
	assertEquals(5, itNumeros.next().intValue());
	assertEquals(3, itNumeros.next().intValue());
	assertEquals(1, itNumeros.next().intValue());	
	
	Iterator<String> itString = listaStrings.getIterador();
	assertFalse(itString.hasNext());
	
	listaStrings.anadirElemento("Cadena1");
	itString = listaStrings.getIterador();
	assertTrue(itString.hasNext());
	assertEquals("Cadena1", itString.next());

	listaStrings.anadirElemento("Cadena3");
	itString = listaStrings.getIterador();
	assertTrue(itString.hasNext());
	assertEquals("Cadena3", itString.next());
	assertEquals("Cadena1", itString.next());
	
	listaStrings.anadirElemento("Cadena2");
	itString = listaStrings.getIterador();
	assertTrue(itString.hasNext());
	assertEquals("Cadena3", itString.next());
	assertEquals("Cadena2", itString.next());
	assertEquals("Cadena1", itString.next());
	
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaOrdenadaGenerica#existeElementoSatisfaceCondicion(ANTpackSudokuAntiguo.ICondicion)}
     * .
     */
    public void testExisteElementoSatisfaceCondicion()
    {
	ICondicion<Integer> condNumeros = new ICondicion<Integer>() {
	    public boolean satisfaceCondicion(Integer pElem)
	    {
		return pElem %2 == 0;
	    }
	};
	
	assertFalse(listaNumeros.existeElementoSatisfaceCondicion(condNumeros));
	listaNumeros.anadirElemento(1);
	assertFalse(listaNumeros.existeElementoSatisfaceCondicion(condNumeros));
	listaNumeros.anadirElemento(5);
	assertFalse(listaNumeros.existeElementoSatisfaceCondicion(condNumeros));
	listaNumeros.anadirElemento(4);
	assertTrue(listaNumeros.existeElementoSatisfaceCondicion(condNumeros));
	
	condNumeros = new ICondicion<Integer>() {
	    public boolean satisfaceCondicion(Integer pElem)
	    {
		return pElem > 10;
	    }
	};

	assertFalse(listaNumeros.existeElementoSatisfaceCondicion(condNumeros));
	listaNumeros.anadirElemento(14);
	assertTrue(listaNumeros.existeElementoSatisfaceCondicion(condNumeros));

	
	ICondicion<String> condString = new ICondicion<String>() {
	    public boolean satisfaceCondicion(String pElem)
	    {
		return pElem.endsWith("3");
	    }	    
	};
	
	assertFalse(listaStrings.existeElementoSatisfaceCondicion(condString));
	listaStrings.anadirElemento("Cadena2");
	assertFalse(listaStrings.existeElementoSatisfaceCondicion(condString));
	listaStrings.anadirElemento("Cadena1");
	assertFalse(listaStrings.existeElementoSatisfaceCondicion(condString));
	listaStrings.anadirElemento("Cadena3");
	assertTrue(listaStrings.existeElementoSatisfaceCondicion(condString));
	
	condString = new ICondicion<String>() {
	    public boolean satisfaceCondicion(String pElem)
	    {
		return pElem.equalsIgnoreCase("NuevaCadena");
	    }	    
	};
	assertFalse(listaStrings.existeElementoSatisfaceCondicion(condString));
	listaStrings.anadirElemento("NuevaCadena");
	assertTrue(listaStrings.existeElementoSatisfaceCondicion(condString));
	
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.ListaOrdenadaGenerica#getIterador()}.
     */
    public void testGetIterador()
    {
	Iterator<Integer> itNumeros = listaNumeros.getIterador();
	assertFalse(itNumeros.hasNext());
	
	listaNumeros.anadirElemento(5);
	listaNumeros.anadirElemento(1);
	listaNumeros.anadirElemento(3);
	itNumeros = listaNumeros.getIterador();
	assertTrue(itNumeros.hasNext());
	assertEquals(5, itNumeros.next().intValue());
	assertEquals(3, itNumeros.next().intValue());
	assertEquals(1, itNumeros.next().intValue());	
	
	Iterator<String> itString = listaStrings.getIterador();
	assertFalse(itString.hasNext());
	
	listaStrings.anadirElemento("Cadena1");
	listaStrings.anadirElemento("Cadena3");
	listaStrings.anadirElemento("Cadena2");
	itString = listaStrings.getIterador();
	assertTrue(itString.hasNext());
	assertEquals("Cadena3", itString.next());
	assertEquals("Cadena2", itString.next());
	assertEquals("Cadena1", itString.next());    
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaOrdenadaGenerica#getIterador(ANTpackSudokuAntiguo.ICondicion)}
     * .
     */
    public void testGetIteradorICondicionOfE()
    {
	
	ICondicion<Integer> condNumeros1 = new ICondicion<Integer>() {
	    public boolean satisfaceCondicion(Integer pElem)
	    {
		return pElem %2 == 0;
	    }
	};
	
	ICondicion<Integer> condNumeros2 = new ICondicion<Integer>() {
	    public boolean satisfaceCondicion(Integer pElem)
	    {
		return pElem %5 == 0;
	    }
	};
	
	Iterator<Integer> iterNum1 = listaNumeros.getIterador(condNumeros1);
	Iterator<Integer> iterNum2 = listaNumeros.getIterador(condNumeros2);
	
	assertFalse(iterNum1.hasNext());
	assertFalse(iterNum2.hasNext());
	
	listaNumeros.anadirElemento(1);
	listaNumeros.anadirElemento(2);
	listaNumeros.anadirElemento(3);
	listaNumeros.anadirElemento(4);
	listaNumeros.anadirElemento(5);
	listaNumeros.anadirElemento(6);
	
	iterNum1 = listaNumeros.getIterador(condNumeros1);
	
	assertTrue(iterNum1.hasNext());
	assertEquals(6, iterNum1.next().intValue());
	assertTrue(iterNum1.hasNext());
	assertEquals(5, iterNum1.next().intValue());
	assertTrue(iterNum1.hasNext());
	assertEquals(4, iterNum1.next().intValue());
	assertTrue(iterNum1.hasNext());
	assertTrue(iterNum1.hasNext());
	assertEquals(3, iterNum1.next().intValue());
	assertEquals(2, iterNum1.next().intValue());
	assertTrue(iterNum1.hasNext());
	assertEquals(1, iterNum1.next().intValue());
	assertFalse(iterNum1.hasNext());
	
	iterNum2 = listaNumeros.getIterador(condNumeros2);	
	assertTrue(iterNum2.hasNext());
	assertEquals(5, iterNum2.next().intValue());
	assertTrue(iterNum2.hasNext());
	assertEquals(4, iterNum2.next().intValue());
	assertTrue(iterNum2.hasNext());
	assertTrue(iterNum2.hasNext());
	assertEquals(3, iterNum2.next().intValue());
	assertEquals(2, iterNum2.next().intValue());
	assertTrue(iterNum2.hasNext());
	assertEquals(1, iterNum2.next().intValue());
	assertFalse(iterNum2.hasNext());
	
	listaStrings.anadirElemento("Cadena1");
	listaStrings.anadirElemento("Cadena2");
	listaStrings.anadirElemento("Cadena3");
	listaStrings.anadirElemento("Cadena4");
	listaStrings.anadirElemento("CadenaNueva");
	
	ICondicion<String> condString1 = new ICondicion<String>() {
	    public boolean satisfaceCondicion(String pElem)
	    {
		return pElem.endsWith("3");
	    }
	};
	
	ICondicion<String> condString2 = new ICondicion<String>() {
	    public boolean satisfaceCondicion(String pElem)
	    {
		return pElem.equalsIgnoreCase("CadenaNueva");
	    }
	};
	
	Iterator<String> iterString1 = listaStrings.getIterador(condString1);
	Iterator<String> iterString2 = listaStrings.getIterador(condString2);
	
	assertTrue(iterString1.hasNext());
	assertEquals("Cadena3", iterString1.next());
	assertTrue(iterString1.hasNext());
	assertEquals("Cadena2", iterString1.next());
	assertTrue(iterString1.hasNext());
	assertEquals("Cadena1", iterString1.next());
	assertFalse(iterString1.hasNext());
	
	assertTrue(iterString2.hasNext());
	assertEquals("CadenaNueva", iterString2.next());
	assertTrue(iterString2.hasNext());
	assertEquals("Cadena4", iterString2.next());
	assertTrue(iterString2.hasNext());
	assertEquals("Cadena3", iterString2.next());
	assertTrue(iterString2.hasNext());
	assertEquals("Cadena2", iterString2.next());
	assertTrue(iterString2.hasNext());
	assertEquals("Cadena1", iterString2.next());
	assertFalse(iterString2.hasNext());
    }
}
