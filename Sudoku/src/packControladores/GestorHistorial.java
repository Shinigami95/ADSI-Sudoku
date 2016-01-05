package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorHistorial {
	
	private static GestorHistorial miGestorHistorial;

	private GestorHistorial() {
		// TODO Auto-generated constructor stub
	}

	public static GestorHistorial getGestorHistorial(){
		if(miGestorHistorial == null){
			miGestorHistorial = new GestorHistorial();
		}
		return miGestorHistorial;
	}
	
	public String obtenerHistorialSudokus(String pJugador) throws ExcepcionConectarBD {
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU,FECHA,COMPLETADO,PTO FROM JUGADO WHERE NOMBRE_JUG = '"+pJugador+"' ORDER BY FECHA;");
		String historialS = "";
		try{
			while(result.next()){
				historialS += "Sudoku: " + result.getString("ID_SUDOKU") + ", Completado: " + result.getString("COMPLETADO") + ", Puntos: " + result.getString("PTO") + ", Fecha: " + result.getString("FECHA") + "\n";
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialS;
	}
	
	public String obtenerHistorialRetos(String pJugador) throws ExcepcionConectarBD{
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_R,NOMBRE_RETADOR,NOMBRE_RETADO,ID_SUDOKU,ESTADO,FECHA FROM RETO WHERE NOMBRE_RETADOR ='"+pJugador+"' AND NOMBRE_RETADO = '"+pJugador+"' ORDER BY FECHA;");
		String historialR = "";
		try{
			while(result.next()){
				historialR += "Reto: " + result.getString("ID_R") + ", Retador: " + result.getString("NOMBRE_RETADOR") + ", Retado: " + result.getString("NOMBRE_RETADO") + ", SudokuJugado: " + result.getString("ID_SUDOKU") + ", Estado: " + result.getString("ESTADO") + ", Fecha: " + result.getString("FECHA") + "\n";
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialR;
	}
}
