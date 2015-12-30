package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

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
}
