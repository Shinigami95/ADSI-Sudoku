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
	
	public String[][] obtenerHistorialSudokus(String pJugador) throws ExcepcionConectarBD {
		int i = 0; //contador de tuplas
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU,FECHA,COMPLETADO,PTO FROM JUGADO WHERE NOMBRE_JUG = '"+pJugador+"' ORDER BY FECHA;");
		String[][] historialS = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array doble con las tuplas y las columnas
			historialS = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=0;j<columnas;j++){
					//[i][0] = id_sudoku, [i][1] = fecha, [i][2] = completado, [i][3] = pto
					historialS[i][j] = result.getString(i);
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialS;
	}
	
	public String[][] obtenerHistorialRetos(String pJugador) throws ExcepcionConectarBD{
		int i = 0; //contador de tuplas
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_R,NOMBRE_RETADOR,NOMBRE_RETADO,ID_SUDOKU,ESTADO,FECHA FROM RETO WHERE NOMBRE_RETADOR ='"+pJugador+"' AND NOMBRE_RETADO = '"+pJugador+"' ORDER BY FECHA;");
		String[][] historialR = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array doble con las tuplas y las columnas
			historialR = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=0;j<columnas;j++){
					//[i][0] = id_reto, [i][1] = nombre_retador, [i][2] = nombre_retado, [i][3] = id_sudoku
					//[i][4] = estado, [i][5] = fecha
					historialR[i][j] = result.getString(i);
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialR;
	}
	
	public String[][] obtenerHistorialLogros(String pJugador) throws ExcepcionConectarBD{
		int i = 0; //contador de tuplas
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION,ID_SUDOKU,FECHA FROM LOGRO INNER JOIN JUGADO WHERE NOMBRE_JUG = '"+pJugador+"' ORDER BY FECHA;");
		String[][] historialL = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array doble con las tuplas y las columnas
			historialL = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=0;j<columnas;j++){
					//[i][0] = descripcion, [i][1] = id_sudoku, [i][2] = fecha,
					historialL[i][j] = result.getString(i);
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialL;
	}
}
