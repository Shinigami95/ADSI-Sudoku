package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;


public class LogrosPuntuacionXY extends Logros {
	private static LogrosPuntuacionXY logroy;
	
	private LogrosPuntuacionXY(){}
	//Singelton
	public static LogrosPuntuacionXY getLogroY(){
		if (logroy==null){
			logroy=new LogrosPuntuacionXY();
		}
		return logroy;
	}
	
	//Metodo que mete el tipo de logro puntuacion xy en la bd
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para meterlos en la bd.
	 *Postcondicion: Los datos se han metido en la bd correctamente.
	 * */
	public void logrosPuntuacionXY(String codL,String puntos,String jugadores) throws ExcepcionConectarBD 
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOXY(ID_L,PTO,NUM_JUG) VALUES('"+codL+"','"+puntos+"','"+jugadores+"');");
	}
	//Metodo que modifica los datos  del logro de tipo logro puntuacion xy en la bd
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para que sean modificados en la bd.
	 *Postcondicion: Los datos se han modificado en la bd correctamente.
	 * */
	public void modificarLogro(String codL,String puntos,String jugadores) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOXY SET NUM_JUG='"+jugadores+"', PTO='"+puntos+"' WHERE ID_L='"+codL+"';");
		}
}
