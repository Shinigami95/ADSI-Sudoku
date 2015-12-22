package packControladores;

import packExcepciones.ExcepcionConectarBD;
import packModelo.Logros;

//
public class GestorLogros {
	
public void añadirLogro(String iDLogro,String iDSudoku,String descripcion,int puntos,int numJug) throws ExcepcionConectarBD
{
	Logros.tipoLogro(iDLogro, iDSudoku, descripcion, puntos, numJug);
	}	

public void eliminar(String iDLogro) throws ExcepcionConectarBD{
	ConexionBD.getConexionBD().actualizarBD("DELETE FROM LOGRO WHERE ID_L='"+iDLogro+"'");
}

}
