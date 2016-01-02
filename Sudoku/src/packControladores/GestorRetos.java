package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.fabric.xmlrpc.base.Array;

import packExcepciones.ExcepcionConectarBD;

public class GestorRetos {
	
	private static GestorRetos miGestorRetos;

	private GestorRetos() {
		// TODO Auto-generated constructor stub
	}

	public static GestorRetos getGestorRetos(){
		if(miGestorRetos == null){
			miGestorRetos = new GestorRetos();
		}
		return miGestorRetos;
	}
	
	public void addReto(String pRetador, String pRetado, int pIdSudoku) throws ExcepcionConectarBD{
		//No se puede retar a un mismo usuario a un sudoku mas de una vez aunque el reto sea de diferentes users
		if(!buscarRetoPorSudoku(pIdSudoku)){
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO RETO(NOMBRE_RETADOR,NOMBRE_RETADO,ID_SUDOKU,ESTADO) VALUES('"+pRetador+"','"+pRetado+"','"+pIdSudoku+"','P');");
		}
	}
	
	public String[][] obtenerListadoRetos(String pRetado) throws ExcepcionConectarBD{
		int i = 0; //contador de tuplas
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_R,NOMBRE_RETADO,RETO.ID_SUDOKU,RETO.FECHA FROM RETO INNER JOIN JUGADO WHERE NOMBRE_JUG='"+pRetado+"' AND ESTADO = 'P' ORDER BY FECHA;");
		String[][] historialLR = null;
		try{
			//Cogemos las columnas que tiene la tupla
			int columnas = result.getMetaData().getColumnCount();
			//Obtenemos el tama�o del array doble con las tuplas y las columnas
			historialLR = new String[result.getFetchSize()][columnas];
			//mientras haya tuplas en la base de datos
			while(result.next()){
				int k = 0;
				//vamos cogiendo las filas de cada tupla y guardando la informacion en el array
				for(int j=1;j<=columnas;j++){
					//[i][0] = id_reto, [i][1] = nombre_retado, [i][2] = id_sudoku, [i][3] = fecha, 
					historialLR[i][k] = result.getString(j);
					k++;
				}
				i++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return historialLR;
	}
	
	public void aceptarReto(int pIdReto) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("UPDATE RETO SET ESTADO='A' WHERE ID_R='"+pIdReto+"';");
	}
	
	public void rechazarReto(int pIdReto) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("UPDATE RETO SET ESTADO='R' WHERE ID_R='"+pIdReto+"';");
	}
	
	public String obtenerGanadorReto(int pIdReto) throws ExcepcionConectarBD{
		//Cogemos el sudoku que el retador y el retado han jugado
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE_RETADOR,NOMBRE_RETADO,ID_SUDOKU FROM RETO WHERE ID_R='"+pIdReto+"';");
		String ganador="";
		String retador="";
		String retado="";
		String id_sudoku="";
		String matrizRetador="";
		String matrizRetado="";
		String matrizSolucion="";
		int tiempoRetador = 0;
		int tiempoRetado = 0;
		try{
			if(result.next()){
				//De esta primera consulta cogemos los datos que nos interesen para la siguiente
				id_sudoku = result.getString("ID_SUDOKU");
				retador = result.getString("NOMBRE_RETADOR");
				retado = result.getString("NOMBRE_RETADO");
			}
			//Cogemos la matriz inicial del retador y solucion para ambos, ya que el sudoku jugado es el mismo
			ResultSet result1 = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE_JUG,MATRIZ_TABLERO,M_SOL FROM PARTIDA INNER JOIN JUGADO ON SUDOKU.ID_S=PARTIDA.ID_SUDOKU WHERE ID_S='"+id_sudoku+"' AND NOMBRE_JUG='"+retador+"';");
			//Cogemos matriz inicial del retado
			ResultSet result2 = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE_JUG,MATRIZ_TABLERO FROM PARTIDA INNER JOIN JUGADO ON SUDOKU.ID_S=PARTIDA.ID_SUDOKU WHERE ID_S='"+id_sudoku+"' AND NOMBRE_JUG='"+retado+"';");
			if(result1.next()){
				matrizRetador = result1.getString("MATRIZ_TABLERO");
				matrizSolucion = result1.getString("M_SOL");
			}
			if(result2.next()){
				matrizRetado = result2.getString("MATRIZ_TABLERO");
			}
			//Si las matrices iniciales de ambos coinciden con la solucion
			if((matrizRetador.equals(matrizSolucion)) && (matrizRetado.equals(matrizSolucion))){
				//Cogemos el tiempo de partida del retador y el retado, respectivamente
				ResultSet result3 = ConexionBD.getConexionBD().consultaBD("SELECT TIEMPO FROM PARTIDA WHERE ID_S='"+id_sudoku+"' AND NOMBRE_JUG='"+retador+"';");
				ResultSet result4 = ConexionBD.getConexionBD().consultaBD("SELECT TIEMPO FROM PARTIDA WHERE ID_S='"+id_sudoku+"' AND NOMBRE_JUG='"+retado+"';");
				if(result3.next()){
					tiempoRetador = result3.getInt("TIEMPO");
				}
				if(result4.next()){
					tiempoRetado = result4.getInt("TIEMPO");
				}
				//Comprobamos el tiempo que han tardado y damos a uno u otro por ganador
				if(tiempoRetador >  tiempoRetado){
					ganador = retador;
				}else{
					ganador = retado;
				}
			}
			//Si la matriz del retador ES valida y la del retado NO
			else if((matrizRetador.equals(matrizSolucion)) && !(matrizRetado.equals(matrizSolucion))){
				ganador = retador;
			}
			//Si la matriz del retador NO es valida y la del retado SI
			else if(!(matrizRetador.equals(matrizSolucion)) && (matrizRetado.equals(matrizSolucion))){
				ganador = retado;
			}
			//Si ninguna de las dos matrices es valida
			else{
				ganador = "Este reto no tiene ganador";
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return ganador;
	}
	
	private boolean buscarRetoPorSudoku(int pIdSudoku) throws ExcepcionConectarBD{
		//Queremos que solo se reciba un unico reto de un sudoku
		boolean esta = false;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT * FROM RETO WHERE ID_SUDOKU='"+pIdSudoku+"'");
		try{
			if(result.next()){
				esta=true;}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return esta;
	}
}