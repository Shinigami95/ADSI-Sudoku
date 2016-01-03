package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorRanking {
	
	private static GestorRanking miGestorRanking;

	private GestorRanking() {
		// TODO Auto-generated constructor stub
	}

	public static GestorRanking getGestorRanking(){
		if(miGestorRanking == null){
			miGestorRanking = new GestorRanking();
		}
		return miGestorRanking;
	}
	
	//Se ejecutara cada uno de los metodos en cada pestaña de la ventana
	public String[][] obtenerRankingPuntuacion() throws ExcepcionConectarBD{
		int i = 0; //contador de tuplas
		String[][] rankingP = null;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE,PTO_TOTAL FROM JUGADOR ORDER BY PTO_TOTAL;");
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array doble con las tuplas y las columnas
			rankingP = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				int k = 0; //contador de valores por tupla
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=1;j<=columnas;j++){
					//[i][0] = nombre, [i][1] = pto_total
					rankingP[i][k] = result.getString(j);
					k++;
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rankingP;
	}
	
	public String[][] obtenerRankingRetos() throws ExcepcionConectarBD{
		int i = 0; //contador de tuplas
		String[][] rankingR = null;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE_RETADOR,NOMBRE_RETADO,ID_SUDOKU,TIEMPO FROM RETO INNER JOIN PARTIDA ON RETO.ID_SUDOKU=PARTIDA.ID_SUDOKU WHERE ESTADO=’A’ ORDER BY TIEMPO;");
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array doble con las tuplas y las columnas
			rankingR = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				int k = 0; //contador de valores por tupla
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=1;j<=columnas;j++){
					//[i][0] = nombre_retador, [i][1] = nombre_retado, [i][2] = id_sudoku, [i][3] = tiempo
					rankingR[i][k] = result.getString(j);
					k++;
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rankingR;
	}
	
	public String[][] obtenerRankingUnSudoku(int pIdSudoku) throws ExcepcionConectarBD{
		int i = 0; //contador de tuplas
		String[][] rankingR = null;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU,NOMBRE_JUG,PTO FROM JUGADO WHERE ID_SUDOKU='"+pIdSudoku+"' ORDER BY PTO;");
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array doble con las tuplas y las columnas
			rankingR = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				int k = 0; //contador de valores por tupla
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=1;j<=columnas;j++){
					//[i][0] = id_sudoku, [i][1] = nombre_jug, [i][2] = pto
					rankingR[i][k] = result.getString(j);
					k++;
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rankingR;
	}
}
