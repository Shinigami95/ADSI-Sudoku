/**
 * 
 */
package ANTpackSudoku;

import java.io.File;
import java.util.Iterator;

import ANTpackSudokuAntiguo.Jugador;
import ANTpackSudokuAntiguo.ListaJugadores;
import junit.framework.TestCase;

/**
 * @author mikel
 */
public class ListaJugadoresTest extends TestCase {
    private String nomFich1 = "Jokalariak.txt";

    private String nomFich2 = "Jokalariak2.txt";

    private ListaJugadores listaJugadores = null;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	listaJugadores = ListaJugadores.getListaJugadores();
	listaJugadores.cargar(nomFich1);
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.ListaJugadores#getListaJugadores()}.
     */
    public void testGetListaJugadores() {
	assertNotNull(listaJugadores);
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaJugadores#cargar(java.lang.String)}.
     */
    public void testCargar() {
	listaJugadores.cargar("kk.txt");
	Iterator<Jugador> iterJugadores = listaJugadores.getIterador();
	assertFalse(iterJugadores.hasNext());
	listaJugadores.cargar(nomFich1);
	pruebaIterador();
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaJugadores#guardar(java.lang.String)}.
     */
    public void testGuardar() {
	Jugador nuevoJugador = new Jugador("JugadorNuevo");
	nuevoJugador.setPuntos(2500);
	listaJugadores.anadirJugador(nuevoJugador);
	listaJugadores.guardar(nomFich2);
	listaJugadores.cargar("kk.txt");
	Iterator<Jugador> iter1 = listaJugadores.getIterador();
	assertFalse(iter1.hasNext());
	listaJugadores.cargar(nomFich2);
	pruebaIterador2();
    }

    /**
     * Test method for
     * {@link ANTpackSudokuAntiguo.ListaJugadores#anadirJugador(ANTpackSudokuAntiguo.Jugador)}.
     */
    public void testAnadirJugador() {
	Jugador nuevoJugador = new Jugador("JugadorNuevo");
	nuevoJugador.setPuntos(2500);
	listaJugadores.anadirJugador(nuevoJugador);
	pruebaIterador2();
    }

    /**
     * Test method for {@link ANTpackSudokuAntiguo.ListaJugadores#getIterador()}.
     */
    public void testGetIterador() {
	pruebaIterador();
    }

    private void pruebaIterador() {
	Iterator<Jugador> iterJugadores = listaJugadores.getIterador();
	assertTrue(iterJugadores.hasNext());
	Jugador jugador = iterJugadores.next();
	assertEquals("Juanan", jugador.getNombre());
	assertEquals(3000, jugador.getPuntos());
	assertTrue(iterJugadores.hasNext());
	jugador = iterJugadores.next();
	assertEquals("Bego", jugador.getNombre());
	assertEquals(2000, jugador.getPuntos());
	assertTrue(iterJugadores.hasNext());
	jugador = iterJugadores.next();
	assertEquals("Mikel", jugador.getNombre());
	assertEquals(1000, jugador.getPuntos());
	assertFalse(iterJugadores.hasNext());
    }

    private void pruebaIterador2() {
	Iterator<Jugador> iterJugadores = listaJugadores.getIterador();
	assertTrue(iterJugadores.hasNext());
	Jugador jugador = iterJugadores.next();
	assertEquals("Juanan", jugador.getNombre());
	assertEquals(3000, jugador.getPuntos());
	assertTrue(iterJugadores.hasNext());
	jugador = iterJugadores.next();
	assertEquals("JugadorNuevo", jugador.getNombre());
	assertEquals(2500, jugador.getPuntos());
	assertTrue(iterJugadores.hasNext());
	jugador = iterJugadores.next();
	assertEquals("Bego", jugador.getNombre());
	assertEquals(2000, jugador.getPuntos());
	assertTrue(iterJugadores.hasNext());
	jugador = iterJugadores.next();
	assertEquals("Mikel", jugador.getNombre());
	assertEquals(1000, jugador.getPuntos());
	assertFalse(iterJugadores.hasNext());
    }

}
