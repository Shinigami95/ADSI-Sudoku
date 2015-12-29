package packModelo;

import packControladores.ConexionBD;
import packSudoku.excepciones.ExcepcionConectarBD;

public class LogrosPuntuacionXY extends Logros {
	
	String puntuacion;
	String jugadores;
	
	public LogrosPuntuacionXY(String codL,String codS,String descripcion,String puntos,String jugadores)  {
		super(codS,codL, descripcion);
		this.puntuacion=puntos;
		this.jugadores=jugadores;
		
	}
	
	public static void logrosPuntuacionXY(String codL,String puntos,String jugadores) throws ExcepcionConectarBD 
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOXY(ID_L,PTO,NUM_JUG) VALUES('"+codL+"','"+puntos+"','"+jugadores+"');");
	}
	public static void modificarLogro(String codL,String puntos,String jugadores) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOXY SET NUM_JUG='"+jugadores+"', PTO='"+puntos+"' WHERE ID_L='"+codL+"';");
		}
}


