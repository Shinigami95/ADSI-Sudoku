package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;


public class LogrosResolucion extends Logros{
	//Metodo que mete el tipo de logro resolucion en la bd
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para meterlos en la bd.
	 *Postcondicion: Los datos se han metido en la bd correctamente.
	 * */
	public static void logrosResolucion(String codL,String jugador) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_RES(ID_L,NUM_JUG) VALUES('"+codL+"','"+jugador+"');");
	}
	//Metodo que modifica los datos  del logro de tipo logro resolucion en la bd
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para que sean modificados en la bd.
	 *Postcondicion: Los datos se han modificado en la bd correctamente.
	 * */
	public static void modificarLogro(String codL,String numJug) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_RES SET NUM_JUG='"+numJug+"' WHERE ID_L='"+codL+"';");
		}
}
