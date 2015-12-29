package packModelo;

import javax.swing.JOptionPane;

import packControladores.ConexionBD;
import packExcepciones.ExcepcionConectarBD;


public class Logros {
	
	String codSudoku;
	String codLogro;
	String descripcionLogro;
	
	public Logros(String codS,String codL,String descripcion){
		this.codLogro=codL;
		this.codSudoku=codS;
		this.descripcionLogro=descripcion;
	}
	
	public static void tipoLogro(String codL,String codS,String descripcion,String puntos,String numJug) throws ExcepcionConectarBD{
		String log=codL;
		if(Integer.parseInt(puntos)>0){
			if(Integer.parseInt(numJug)<1){
				log="X"+log;
				ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+log+"','"+codS+"','"+descripcion+"');");
				LogrosPuntuacionX.logrosPuntuacionX(log, puntos);
			}
			else{
				log="Y"+log;
				ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+log+"','"+codS+"','"+descripcion+"');");
				LogrosPuntuacionXY.logrosPuntuacionXY(log, puntos, numJug); 
			}
		}
		else{
			log="R"+log;
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+log+"','"+codS+"','"+descripcion+"');");
			LogrosResolucion.logrosResolucion(log, numJug);
		}
	}
	
	public static void modificarLogros(String codL,String codS,String descripcion,String puntos,String numJug) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO SET ID_SUDOKU='"+codS+"',DESCRIPCION='"+descripcion+"' WHERE ID_L='"+codL+"';");
		if(codL.charAt(0)=='X'){
			LogrosPuntuacionX.modificarLogro(codL, puntos);
		}
		else if (codL.charAt(0)=='Y') {
			LogrosPuntuacionXY.modificarLogro(codL, puntos, numJug);
		}
		else if (codL.charAt(0)=='R') {
			LogrosResolucion.modificarLogro(codL, numJug);
		}
		else{JOptionPane.showMessageDialog(null, "Datos incorrectos.");}
	}


}
