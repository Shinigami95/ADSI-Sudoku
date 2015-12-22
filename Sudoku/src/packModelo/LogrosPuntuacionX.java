package packModelo;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;

public class LogrosPuntuacionX extends Logros{
	
	int puntuacion;
	
	public LogrosPuntuacionX(String codL,String codS,String descripcion,int puntos){
		super(codS,codL, descripcion);
		this.puntuacion=puntos;
		
	}
	public static void logrosPuntuacionX(String codL,int puntos) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO_PTOX(ID_L,PTO) VALUES('"+codL+"','"+puntos+"')");
	}
}
