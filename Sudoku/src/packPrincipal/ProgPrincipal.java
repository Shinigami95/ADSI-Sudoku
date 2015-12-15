/**
 * 
 */
package packPrincipal;

import packInterfazGrafica.*;
import packSudoku.excepciones.ExcepcionNoHayFicheroSudokus;
import packSudokuAntiguo.*;

public class ProgPrincipal
{

    /**
     * @param args
     */
    public static void main(String[] pArgs)
    {
	System.out.println("Iniciando Sesión");
	
	Sesion sesion = Sesion.obtSesion();
	    ListaJugadores.getListaJugadores().cargar("jokalariak.txt");
	
	try
	{
	    ListaSudokus.INSTANCIA.cargar("sudokuak.txt");
	} catch (ExcepcionNoHayFicheroSudokus e)
	{
	    e.printStackTrace();
	}
	VentanaInicial.obtVentanaInicial().mostrar();
	boolean seguir = true;
	while (seguir) {
		sesion.iniciarJuego();
		VentanaTablero.obtVentanaTablero().mostrar();
		// Interfaz: Desea Seguir
		seguir =VentanaSiNo.obtVentanaSiNo().preguntar("Desea Seguir?");
	}
	sesion.actualizarPuntuaciones();
	VentanaPuntuaciones.obtVentanaPuntuaciones().mostrar();
	ListaJugadores.getListaJugadores().guardar("jokalariak.txt");
	System.out.println("Final");

    }

}
