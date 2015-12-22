/**
 * 
 */
package ANTpackSudokuAntiguo;

import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import packExcepciones.ExcepcionNoHaySudokuCargado;

public class Sesion extends Observable implements Observer {
    private static Sesion mSesion = new Sesion();
    private String nombreUsuario;
    private Iterator<Sudoku> iter;
    private Date horaInicio;
    private int puntos;

    /**
     * Instantiates a new sesion.
     */
    private Sesion() {
	Tablero.getTablero().addObserver(this);
	nombreUsuario = "Anonimo";
    }

    /**
     * Obt sesion.
     * 
     * @return the sesion
     */
    public static Sesion obtSesion() {
	return mSesion;
    }

    /**
     * Iniciar juego.
     */
    public void iniciarJuego() {
	try {
	    Tablero.getTablero().inicializar(iter.next());
	    horaInicio = new Date();
	} catch (RuntimeException e) {
	    horaInicio = null;
	}
    }

    /**
     * Obt nombre usuario.
     * 
     * @return the string
     */
    public String obtNombreUsuario() {
	return nombreUsuario;
    }

    /**
     * Pon nivel.
     * 
     * @param pNivel
     *            the nivel
     */
    public void ponNivel(int pNivel) {
	iter = ListaSudokus.INSTANCIA.getIterador(pNivel);
    }

    /**
     * Pon nombre usuario.
     * 
     * @param pNombre
     *            the nombre
     */
    public void ponNombreUsuario(String pNombre) {
	nombreUsuario = pNombre;
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable pObservador, Object pObjeto) {
	Tablero tablero = Tablero.getTablero();
	try {
	    if (tablero.finalDeJuego()) {
		int puntosPartida = 0;
		if (tablero.getNumErrores() == 0) {
		    Date horaFinal = new Date();
		    int tiempo = (int) (horaFinal.getTime() - horaInicio
			    .getTime()) / 1000;
		    try {
			puntosPartida = (30000 * tablero.getNivel() / tiempo);
			this.puntos = Math.max(puntos, puntosPartida);
		    } catch (ExcepcionNoHaySudokuCargado e) {
		    }
		}
		horaInicio = null;
	    }
	} catch (ExcepcionNoHaySudokuCargado e) {
	}

    }

    /**
     * @return
     */
    public int obtNivel() {
	try {
	    return Tablero.getTablero().getNivel();
	} catch (ExcepcionNoHaySudokuCargado e) {
	    return 1;
	}
    }

    /**
	 * 
	 */
    public void actualizarPuntuaciones() {
	Jugador jugador = new Jugador(nombreUsuario);
	jugador.setPuntos(puntos);

	ListaJugadores.getListaJugadores().anadirJugador(jugador);

    }

}
