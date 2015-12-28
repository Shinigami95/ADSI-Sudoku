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
	
	//Se ejecutara cada uno de los metodos en cada pestaña de la ventana
	public String[] obtenerHistorialSudokus(String pJugador) throws ExcepcionConectarBD {
		int j = 0;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU,FECHA,COMPLETADO,PTO,SEGUNDOS FROM JUGADO WHERE NOMBRE_JUG = '"+pJugador+"' ORDER BY FECHA;");
		String[] historialS = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array sobre el que meter datos
			//multiplicando las tuplas que hay por las columnas de cada tupla
			historialS = new String[result.getFetchSize()*columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int i=1;i<=columnas;i++){
					//Orden de objetos del array: 1. id_sudoku, 2. fecha, 3. completado, 4. pto
					historialS[j] = result.getString(i);
					j++;
				}
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialS;
	}
	
	
	public String[] obtenerHistorialRetos(String pJugador) throws ExcepcionConectarBD{
		int j = 0;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT * FROM RETO WHERE NOMBRE_RETADOR = '"+pJugador+"' AND NOMBRE_RETADO = '"+pJugador+"' ORDER BY FECHA;");
		String[] historialR = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array sobre el que meter datos
			//multiplicando las tuplas que hay por las columnas de cada tupla
			historialR = new String[result.getFetchSize()*columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int i=1;i<=columnas;i++){
					//Orden de objetos del array: 1. id_reto, 2. nombre_retador, 3. nombre_retado
					//4. id_sudoku, 5. estado, 6. fecha
					historialR[j] = result.getString(i);
					j++;
				}
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialR;
	}
	
	
	public String[] obtenerHistorialLogros(String pJugador) throws ExcepcionConectarBD{
		int j = 0;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION, ID_SUDOKU, FECHA FROM RETO INNER JOIN JUGADO WHERE NOMBRE_JUG = '"+pJugador+"' ORDER BY FECHA;");
		String[] historialL = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tamaño del array sobre el que meter datos
			//multiplicando las tuplas que hay por las columnas de cada tupla
			historialL = new String[result.getFetchSize()*columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int i=1;i<=columnas;i++){
					//Orden de objetos del array: 1. id_sudoku, 2. fecha, 3. completado, 4. pto
					historialL[j] = result.getString(i);
					j++;
				}
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialL;
	}
}
