package packControladores;

import packExcepciones.ExcepcionConectarBD;
import packExcepciones.NoValidoException;
import packExcepciones.YaExisteException;
import packModelo.Sudoku;


public class GestorAdministrador {

	
	private static GestorAdministrador mGestor = new GestorAdministrador();
	
	private GestorAdministrador() {
		 
	}
	
	public static GestorAdministrador getGestor(){
		return mGestor;
	}
	
	public void anadirSudoku(String mCompleta, String mIncompleta , int dificultad, boolean estaActivo) throws YaExisteException, NoValidoException{
		
		// Si consigue crearlo seguira, si no lanzara NoValidoException
		new Sudoku(1,dificultad,mCompleta,mIncompleta,estaActivo);
		
		String activo;
		if(estaActivo) activo="S";
		else activo="N";
		String sql;
			
		boolean existeSudoku = GestorSudokus.getGestor().existeSudoku(mCompleta,mIncompleta);
		if(!existeSudoku){			
			sql="INSERT INTO SUDOKU(DIFICULTAD,M_INIC,M_SOL,ACTIVO) VALUES("+dificultad+",'"+mIncompleta+"','"+mCompleta+"','"+activo+"')";
			try {
				ConexionBD.getConexionBD().actualizarBD(sql);
			} catch (ExcepcionConectarBD e) {
				e.printStackTrace();
			}
		}
		else{
			throw new YaExisteException();
		}
	}
	
	public void modificarSudoku(int pCodigo,String mCompleta,String mIncompleta,int dificultad) throws NoValidoException{
		
		// Si consigue crearlo seguira, si no lanzara NoValidoException
		new Sudoku(pCodigo,dificultad,mCompleta,mIncompleta, true);
		
		String sql="UPDATE SUDOKU SET DIFICULTAD="+dificultad+", M_INIC='"+mIncompleta+"',M_SOL='"+mCompleta+"' WHERE ID_S="+pCodigo;
		try {
			ConexionBD.getConexionBD().actualizarBD(sql);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	public void setActivoSudoku(int pCodigo, boolean pActivo){
		try {
			String activo;
			if(pActivo) activo="S";
			else activo="N";
			String sql = "UPDATE SUDOKU SET ACTIVO='"+activo+"' WHERE ID_S="+pCodigo;
			ConexionBD.getConexionBD().actualizarBD(sql);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		} 
	}
	
	public void borrarSudoku(int pCodigo){
		String sql = "DELETE FROM SUDOKU WHERE ID_S="+pCodigo;
		try {
			ConexionBD.getConexionBD().actualizarBD(sql);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}	
	}
}
