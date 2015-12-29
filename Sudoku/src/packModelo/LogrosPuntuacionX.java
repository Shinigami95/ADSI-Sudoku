package packModelo;

import packControladores.ConexionBD;
import packSudoku.excepciones.ExcepcionConectarBD;

public class LogrosPuntuacionX extends Logros{
	
	String puntuacion;
	
	public LogrosPuntuacionX(String codL,String codS,String descripcion,String puntos){
		super(codS,codL, descripcion);
		this.puntuacion=puntos;
		
	}
	public static void logrosPuntuacionX(String codL,String puntos) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOX(ID_L,PTO) VALUES('"+codL+"','"+puntos+"');");
	}
	public static void modificarLogro(String codL,String puntos) throws ExcepcionConectarBD
	{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOX SET PTO='"+puntos+"' WHERE ID_L='"+codL+"';");
		}
}
