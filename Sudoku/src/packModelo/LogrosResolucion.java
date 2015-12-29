package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;


public class LogrosResolucion extends Logros{
	
	String jugadores;
	
	public LogrosResolucion(String codL,String codS,String descripcion,String jugador){
		super(codS,codL, descripcion);
		this.jugadores=jugador;
		
	}
	public static void logrosResolucion(String codL,String jugador) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_RES(ID_L,NUM_JUG) VALUES('"+codL+"','"+jugador+"');");
	}
	
	public static void modificarLogro(String codL,String numJug) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_RES SET NUM_JUG='"+numJug+"' WHERE ID_L='"+codL+"';");
		}
}
