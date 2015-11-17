package packSudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

/**
 * The Class ListaJugadores.
 */
public class ListaJugadores extends Observable {

    /** The mi lista jugadores. */
    private static ListaJugadores miListaJugadores = new ListaJugadores();

    /** The lista jugadores. */
    private ListaOrdenadaGenerica<Jugador> listaJugadores;

    /**
     * @return
     * @see packSudoku.ListaOrdenadaGenerica#toString()
     */
    public String toString() {
	return listaJugadores.toString();
    }

    /**
     * Instantiates a new lista jugadores.
     */
    private ListaJugadores() {
	listaJugadores = new ListaOrdenadaGenerica<Jugador>(
		new ComparadorJugadores());
    }

    /**
     * Get lista jugadores.
     * 
     * @return the lista jugadores
     */
    public static ListaJugadores getListaJugadores() {
	return miListaJugadores;
    }

    /**
     * cargar.
     * 
     * @param pFichero
     *            String
     * 
     * @throws ExcepcionListaLlena
     *             the excepcion lista llena
     */
    public void cargar(String pFichero) {
	listaJugadores = new ListaOrdenadaGenerica<Jugador>(
		new ComparadorJugadores());
	try {
	    Scanner entrada = new Scanner(new File(pFichero));
	    System.out.println(new File(pFichero).getAbsolutePath());
	    int puntos;
	    String nombre;
	    Jugador jugador;
	    while (entrada.hasNext()) {
	        puntos = entrada.nextInt();
	        nombre = entrada.next();
	        jugador = new Jugador(nombre);
	        jugador.setPuntos(puntos);
	        anadirJugador(jugador);
	    }
	    entrada.close();
	} catch (FileNotFoundException e) {

	}

    }

    /**
     * guardar.
     * 
     * @param pFichero
     *            String
     */
    public void guardar(String pFichero) {
	try {
	    PrintWriter salida = new PrintWriter(new FileWriter(pFichero));
	    Jugador jugador;
	    Iterator<Jugador> iterador = listaJugadores.getIterador();
	    while (iterador.hasNext()) {
		jugador = iterador.next();
		salida.println(jugador.getPuntos() + " " + jugador.getNombre());
	    }
	    salida.close();
	    // listaJugadores.clear();
	} catch (IOException ex) {
	}
    }

    /**
     * anadirJugador.
     * 
     * @param pJugador
     *            Jugador
     * 
     * @throws ExcepcionListaLlena
     *             the excepcion lista llena
     */
    public void anadirJugador(Jugador pJugador) {
	    if (!listaJugadores
		    .existeElementoSatisfaceCondicion(new CondJugadorIgual(
			    pJugador))) {
		listaJugadores.anadirElemento(pJugador);
		setChanged();
		notifyObservers();
	    }
	
    }

    /**
     * Get iterador.
     * 
     * @return the iterator< jugador>
     */
    public Iterator<Jugador> getIterador() {
	return listaJugadores.getIterador();
    }
}
