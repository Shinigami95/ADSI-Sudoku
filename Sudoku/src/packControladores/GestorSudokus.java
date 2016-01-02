package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;
import packExcepciones.NoValidoException;
import packModelo.Sudoku;

public class GestorSudokus {
	
	private static GestorSudokus mGestor = null;
	
	private GestorSudokus(){}
	
	public static GestorSudokus getGestor(){
		if(mGestor==null){
			mGestor = new GestorSudokus();
		}
		return mGestor;
	}
	
	public String[] getSudokus() throws ExcepcionConectarBD{
		try{
			String sql = "SELECT ID_S FROM SUDOKU;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.last();
			String[] data = new String[result.getRow()];
			result.beforeFirst();
			for(int i = 0;result.next();i++){
				data[i] = String.valueOf(result.getInt("ID_S"));
			}
			ConexionBD.getConexionBD().closeResult(result);
			return data;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public String[] getSudokusUsuarioSesion() throws ExcepcionConectarBD{
		try{
			String jugador = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT ID_SUDOKU FROM JUGADO WHERE NOMBRE_JUG='"+jugador+"';";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.last();
			String[] data = new String[result.getRow()];
			result.beforeFirst();
			for(int i = 0;result.next();i++){
				data[i] = String.valueOf(result.getInt("ID_SUDOKU"));
			}
			ConexionBD.getConexionBD().closeResult(result);
			return data;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String[] getDificultades() throws ExcepcionConectarBD {
		try{
			String sql = "SELECT DIFICULTAD FROM SUDOKU GROUP BY DIFICULTAD;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.last();
			String[] data = new String[result.getRow()];
			result.beforeFirst();
			for(int i = 0;result.next();i++){
				data[i] = String.valueOf(result.getInt("DIFICULTAD"));
			}
			ConexionBD.getConexionBD().closeResult(result);
			return data;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean existeSudoku(String pCompleta, String pInCompleta){
		String sql = "SELECT * FROM SUDOKU WHERE M_SOL='"+pCompleta+"' AND M_INIC='"+pInCompleta+"';";
		try {
			ResultSet rs = ConexionBD.getConexionBD().consultaBD(sql);
			if(rs.next()){
				//Si existe
				ConexionBD.getConexionBD().closeResult(rs);
				return true;				
			}else{
				//Si no existe
				ConexionBD.getConexionBD().closeResult(rs);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
		return true;
	}
	
		
	public Sudoku buscarSudokuPorCodigo(int pCodigo){
		String sql = "SELECT * FROM SUDOKU WHERE ID_S="+pCodigo;
		ResultSet rs;
		Sudoku s = null;
		try {
			rs = ConexionBD.getConexionBD().consultaBD(sql);
			if(!rs.next()) return null;
			int dificultad = rs.getInt("DIFICULTAD");		
			String completa = rs.getString("M_SOL");
			String incompleta = rs.getString("M_INIC");
			String activo = rs.getString("ACTIVO");
			boolean estaActivo = activo.equals("S");
			try {
				s = new Sudoku(pCodigo,dificultad,completa,incompleta, estaActivo);
			} catch (NoValidoException e) {
				e.printStackTrace();
			}
			rs.close();
		} catch (ExcepcionConectarBD e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return s;
	}
}
