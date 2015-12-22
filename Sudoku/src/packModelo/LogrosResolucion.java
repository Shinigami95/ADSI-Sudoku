package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;

public class LogrosResolucion extends Logros{
	
	int jugadores;
	
	public LogrosResolucion(String codL,String codS,String descripcion,int jugador){
		super(codS,codL, descripcion);
		this.jugadores=jugador;
		
	}
	public static void logrosResolucion(String codL,int jugador) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_RES(ID_L,NUM_JUG) VALUES('"+codL+"','"+jugador+"')");
	}
}
