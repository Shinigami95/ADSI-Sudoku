package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorRetos {
	
	private static GestorRetos miGestorRetos;

	private GestorRetos() {
		// TODO Auto-generated constructor stub
	}

	public static GestorRetos getGestor(){
		if(miGestorRetos == null){
			miGestorRetos = new GestorRetos();
		}
		return miGestorRetos;
	}
	
	@Deprecated
	public void addReto(String pRetador, String pRetado, int pIdSudoku) throws ExcepcionConectarBD{
		//No se puede retar a un mismo usuario a un sudoku mas de una vez aunque el reto sea de diferentes users
		if(!buscarRetoPorSudoku(pIdSudoku)){
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO RETO(NOMBRE_RETADOR,NOMBRE_RETADO,ID_SUDOKU,ESTADO) VALUES('"+pRetador+"','"+pRetado+"','"+pIdSudoku+"','P');");
		}
	}
	@Deprecated
	public String obtenerListadoRetos(String pRetado) throws ExcepcionConectarBD{
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT RETO.ID_SUDOKU FROM RETO INNER JOIN JUGADO WHERE NOMBRE_JUG='"+pRetado+"' AND ESTADO = 'P' ORDER BY JUGADO.FECHA DESC;");
		String listaRetos = "";
		try{
			while(result.next()){
				listaRetos += result.getString("RETO.ID_SUDOKU") + "\n";
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return listaRetos;
	}

	@Deprecated
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

	@Deprecated
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
	
	public String[] getUsuariosRetablesAlSudoku(int idSud) throws ExcepcionConectarBD{
		try{
			String userSesion = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT NOMBRE FROM JUGADOR "
					+ "WHERE NOMBRE NOT IN ( "
					+ "SELECT NOMBRE_JUG FROM JUGADO WHERE ID_SUDOKU="+idSud+" "
					+ "UNION "
					+ "SELECT NOMBRE_JUG FROM PARTIDA WHERE ID_SUDOKU="+idSud+" "
					+ "UNION "
					+ "SELECT NOMBRE_RETADO FROM RETO WHERE NOMBRE_RETADOR='"+userSesion+"' AND ID_SUDOKU="+idSud+" "
					+ ")";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.last();
			int nRow = result.getRow();
			result.beforeFirst();
			String[] list = new String[nRow];
			for(int i=0; result.next(); i++){
				list[i] = result.getString("NOMBRE");
			}
			ConexionBD.getConexionBD().closeResult(result);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public static void retarJugadorAlSudokuHechoPorUsuarioSesion(String pJugador) throws ExcepcionConectarBD {
		String userSesion = GestorSesion.getGestor().getUserSesion();
		int idSud = GestorPartida.getGestor().getIdSud();
		String sql = "INSERT INTO RETO(NOMBRE_RETADOR, NOMBRE_RETADO, ID_SUDOKU, ESTADO) "
				+ "VALUES('"+userSesion+"','"+pJugador+"',"+idSud+",'P');";
		ConexionBD.getConexionBD().actualizarBD(sql);
	}
	
	public void aceptarReto(String pIdReto) throws ExcepcionConectarBD{
		// Se acepta el reto
		String sql = "UPDATE RETO SET ESTADO='A' WHERE ID_R='"+pIdReto+"';";
		ConexionBD.getConexionBD().actualizarBD(sql);
		String user = GestorSesion.getGestor().getUserSesion();
		// Se rechazan los que se asociaban a ese mismo sudoku
		try {
			sql = "SELECT ID_SUDOKU FROM RETO WHERE ID_R="+pIdReto+";";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			if(result.next()){
				int idSud = result.getInt("ID_SUDOKU");
				sql = "UPDATE RETO SET ESTADO='R' "
					+ "WHERE NOMBRE_RETADO='"+user+"' AND "
					+ "ID_SUDOKU="+idSud+" AND ESTADO='P';";
				ConexionBD.getConexionBD().actualizarBD(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rechazarReto(int pIdReto) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("UPDATE RETO SET ESTADO='R' WHERE ID_R='"+pIdReto+"';");
	}
	
	public String[] obtenerListadoRetosPendientesUsuarioSesion() throws ExcepcionConectarBD{
		try{
			String user = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT ID_R FROM RETO WHERE NOMBRE_RETADO='"+user+"' AND ESTADO='P' ORDER BY FECHA DESC;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.last();
			int nRow = result.getRow();
			result.beforeFirst();
			String[] list = new String[nRow];
			for(int i=0; result.next(); i++){
				list[i] = result.getInt("ID_R")+"";
			}
			ConexionBD.getConexionBD().closeResult(result);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param pIdR - id del reto del que se quiere
	 * obtener informaci&oacute;n
	 * @return El resultado de la select en un array<br>
	 * [ID_R, NOMBRE_RETADOR, ID_SUDOKU, ESTADO, FECHA ]
	 * @throws ExcepcionConectarBD
	 */
	public String[] getInfoReto(String pIdR) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT ID_R, NOMBRE_RETADOR, ID_SUDOKU, ESTADO, FECHA FROM RETO WHERE ID_R="+pIdR+";";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			String[] reto = null;
			if(result.next()){
				int nCol = result.getMetaData().getColumnCount();
				reto = new String[nCol];
				reto[0] = result.getInt("ID_R")+"";
				reto[1] = result.getString("NOMBRE_RETADOR");
				reto[2] = result.getInt("ID_SUDOKU")+"";
				reto[3] = result.getString("ESTADO");
				reto[4] = result.getString("FECHA");
			}
			ConexionBD.getConexionBD().closeResult(result);
			return reto;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
