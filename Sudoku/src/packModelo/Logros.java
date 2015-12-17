package packModelo;

import packControladores.ConexionBD;
import packSudoku.excepciones.ExcepcionConectarBD;

public class Logros {
	
	String codSudoku;
	String codLogro;
	String descripcionLogro;
	
	public Logros(String codS,String codL,String descripcion){
		this.codLogro=codL;
		this.codSudoku=codS;
		this.descripcionLogro=descripcion;
	}
	
	public static void tipoLogro(String codL,String codS,String descripcion,int puntos,int numJug) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+codL+"','"+codS+"','"+descripcion+"')");
		if(puntos>0){
			if(numJug>0){
				LogrosPuntuacionX.logrosPuntuacionX(codL, puntos);
			}
			else{
				LogrosPuntuacionXY.logrosPuntuacionXY(codL, puntos, numJug); 
			}
		}
		else{
			LogrosResolucion.logrosResolucion(codL, numJug);
		}
	}



}
