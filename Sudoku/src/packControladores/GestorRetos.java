package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorRetos {
	
	private static GestorRetos miGestorRetos;

	private GestorRetos() {
		
	}

	public static GestorRetos getGestor(){
		if(miGestorRetos == null){
			miGestorRetos = new GestorRetos();
		}
		return miGestorRetos;
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
