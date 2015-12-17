package packModelo;

import packControladores.ConexionBD;
import packSudoku.excepciones.ExcepcionConectarBD;

public class LogrosPuntuacionXY extends Logros {
	
	int puntuacion;
	int jugadores;
	
	public LogrosPuntuacionXY(String codL,String codS,String descripcion,int puntos,int jugadores)  {
		super(codS,codL, descripcion);
		this.puntuacion=puntos;
		this.jugadores=jugadores;
		
	}
	
	public static void logrosPuntuacionXY(String codL,int puntos,int jugadores) throws ExcepcionConectarBD 
	{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOXY(ID_L,PTO,NUM_JUG) VALUES('"+codL+"','"+puntos+"','"+jugadores+"')");
	}
}


