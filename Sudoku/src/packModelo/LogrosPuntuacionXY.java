package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;


public class LogrosPuntuacionXY extends Logros {
	//Metodo que mete el tipo de logro puntuacion xy en la bd
	public static void logrosPuntuacionXY(String codL,String puntos,String jugadores) throws ExcepcionConectarBD 
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOXY(ID_L,PTO,NUM_JUG) VALUES('"+codL+"','"+puntos+"','"+jugadores+"');");
	}
	//Metodo que modifica los datos  del logro de tipo logro puntuacion xy en la bd
	public static void modificarLogro(String codL,String puntos,String jugadores) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOXY SET NUM_JUG='"+jugadores+"', PTO='"+puntos+"' WHERE ID_L='"+codL+"';");
		}
}
