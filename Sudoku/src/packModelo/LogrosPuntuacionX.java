package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;


public class LogrosPuntuacionX extends Logros{
	
	private static LogrosPuntuacionX logrox;
	private LogrosPuntuacionX(){}
	//Singelton
	public static LogrosPuntuacionX getLogroX(){
		if (logrox==null){
			logrox=new LogrosPuntuacionX();
		}
		return logrox;
	}
	//Metodo que mete el tipo de logro puntuacion x en la bd
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para meterlos en la bd.
	 *Postcondicion: Los datos se han metido en la bd correctamente.
	 * */
	public void logrosPuntuacionX(String codL,String puntos) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOX(ID_L,PTO) VALUES('"+codL+"','"+puntos+"');");
	}
	//Metodo que modifica los datos  del logro de tipo logro puntuacion x en la bd
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para que sean modificados en la bd.
	 *Postcondicion: Los datos se han modificado en la bd correctamente.
	 * */
	public void modificarLogro(String codL,String puntos) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOX SET PTO='"+puntos+"' WHERE ID_L='"+codL+"';");
		}
}
