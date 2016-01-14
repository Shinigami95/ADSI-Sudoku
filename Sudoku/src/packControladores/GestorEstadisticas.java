package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import packExcepciones.ExcepcionConectarBD;

public class GestorEstadisticas {
	private static GestorEstadisticas mGestor = null;
	
	private GestorEstadisticas(){}
	
	public static GestorEstadisticas getGestor(){
		if(mGestor==null){
			mGestor = new GestorEstadisticas();
		}
		return mGestor;
	}
	
	//devuelve en un string las estadisticas del jugador con la sesion iniciada
	public String getHTMLEstadisticasJugadorSesion() throws ExcepcionConectarBD{
		return this.getHTMLEstadisticasJugador(GestorSesion.getGestor().getUserSesion());
	}
	
	//devuelve en un string con formato html el nombre del jugador, con el tiempo medio de
	//resolucion por categoria de los sudokus que ha jugado y el numero de sudokus que ha
	//jugado, resuelto y dejado incompletos
	public String getHTMLEstadisticasJugador(String pJugador) throws ExcepcionConectarBD{
		String res = "<html>"; 
		res += "<h1>Jugador: "+pJugador+"</h1>";
		res += this.getHTMLTiempoMedioResolucionPorCategoriaDelJugador(pJugador)+"\n\n";
		res += this.getHTMLInfoSudokusResueltosDelJugador(pJugador);
		res += "</html>";
		return res;
	}
	
	/**
	 * @param pJugador - nombre del jugador del que se quieren saber las estadísticas
	 * @return El resultado de la select en una matriz de enteros<br>
	 * [Dificultad sudoku, Media tiempo, Sudokus completados de esa dificultad]
	 * @throws ExcepcionConectarBD
	 */
	private int[][] getTiempoMedioResolucionPorCategoriaDelJugador(String pJugador) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT SUDOKU.DIFICULTAD AS DIF, AVG(JUGADO.SEGUNDOS) AS MEDIA, COUNT(*) AS NUM "
					+ "FROM JUGADO INNER JOIN SUDOKU ON SUDOKU.ID_S = JUGADO.ID_SUDOKU "
					+ "WHERE JUGADO.NOMBRE_JUG = '"+pJugador+"' AND JUGADO.COMPLETADO = 'S' GROUP BY DIF ORDER BY DIF;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int nCol = result.getMetaData().getColumnCount();
			result.last();
			int nRow = result.getRow();
			int[][] tablaRes = new int[nRow][nCol];
			result.beforeFirst();
			for(int i = 0; result.next(); i++){
				tablaRes[i][0] = result.getInt("DIF");
				tablaRes[i][1] = result.getInt("MEDIA");
				tablaRes[i][2] = result.getInt("NUM");
			}
			ConexionBD.getConexionBD().closeResult(result);
			return tablaRes;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//devuelve en un string con formato html formando una tabla el tiempo medio
	//de resolucion por categoria de los sudokus que ha jugado un jugador
	//mostrando la dificultad, el tiempo medio y el numero de sudokus realizados
	private String getHTMLTiempoMedioResolucionPorCategoriaDelJugador(String pJugador) throws ExcepcionConectarBD{
		int[][] tablaRes = this.getTiempoMedioResolucionPorCategoriaDelJugador(pJugador);
		String data = "<h2>Tiempo medio por categor&iacute;a:</h2>";
		data += "<table border=1><tr><th>Dificultad</th><th>Tiempo Medio</th><th>Num. Hechos</th></tr>";
		int dif;
		int media;
		int num;
		for(int i = 0; i<tablaRes.length; i++){
			dif = tablaRes[i][0];
			media = tablaRes[i][1];
			num = tablaRes[i][2];
			data += "<tr><td>"+dif+"</td><td>"+tiempoAString(media)+"</td><td>"+num+"</td></tr>";
		}
		data += "</table>";
		return data;
	}
	
	/**
	 * @param pJugador - nombre del jugador del que se quieren saber las estad&iacute;sticas
	 * @return El resultado de la select en una lista de enteros<br>
	 * [Total sudokus, Resueltos, Incompletos]<br>
	 * si el usuario no ha hecho ning&uacute;n sudoku devolver&aacute; 0 en los tres valores
	 * @throws ExcepcionConectarBD
	 */
	private int[] getInfoSudokusResueltosDelJugador(String pJugador) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT JUGADO.COMPLETADO AS COMP, COUNT(*) AS NUM "
					+ "FROM JUGADO INNER JOIN SUDOKU ON SUDOKU.ID_S = JUGADO.ID_SUDOKU "
					+ "WHERE JUGADO.NOMBRE_JUG = '"+pJugador+"' GROUP BY COMP;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int totalSudokus = 0;
			int sudCompletos = 0;
			int sudIncompletos = 0;
			String comp;
			int num;
			while(result.next()){
				comp = result.getString("COMP");
				num = result.getInt("NUM");
				if(comp.equals("S")){
					sudCompletos += num;
					totalSudokus += num;
				} else if(comp.equals("N")){
					sudIncompletos += num;
					totalSudokus += num;
				}
			}
			ConexionBD.getConexionBD().closeResult(result);
			if(totalSudokus>0){
				int[] dataRes = new int[3];
				dataRes[0] = totalSudokus;
				dataRes[1] = sudCompletos;
				dataRes[2] = sudIncompletos;
				return dataRes;
			} else { return new int[]{0,0,0};}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//devuelve en un string con formato html los datos de los sudokus que
	//ha jugado un jugador, es decir, el numero de sudokus que ha jugado,
	//completado, dejado incompletos y un porcentaje de exito
	private String getHTMLInfoSudokusResueltosDelJugador(String pJugador) throws ExcepcionConectarBD{
		int[] tablaRes = this.getInfoSudokusResueltosDelJugador(pJugador);
		String data = "<h2>Info. sudokus realizados: </h2>";
		int totalSudokus = tablaRes[0];
		int sudCompletos = tablaRes[1];
		int sudIncompletos = tablaRes[2];
		if(totalSudokus>0){
			data += "<ul>";
			data += "<li>Total sudokus: "+totalSudokus+"</li>";
			data += "<li>Completos: "+sudCompletos+"</li>";
			data += "<li>Incompletos: "+sudIncompletos+"</li>";
			DecimalFormat decf = new DecimalFormat("0.00");
			double porcentComp = ((double)sudCompletos/(double)totalSudokus)*100;
			String porcent = decf.format(porcentComp);
			data += "<li>&Iacute;ndice de &eacute;xito: "+porcent+"%</li>";
			data += "</ul>";
		}
		return data;
	}
	
	private String tiempoAString(int pT){
		int tiempoHor = pT/3600;
		int tiempoMin = (pT%3600)/60;
		int tiempoSeg = (pT%3600)%60;
		String ts =Integer.toString(tiempoSeg);
		String tm =Integer.toString(tiempoMin);
		String th =Integer.toString(tiempoHor);
		if (tiempoSeg<10) ts = "0"+ts;
		if (tiempoMin<10) tm = "0"+tm;
		if (tiempoHor<10) th = "0"+th;
		return th +":"+ tm +":"+ ts;
	}
	
	//devuelve en un string con formato html las estadisticas de un sudoku dado
	//mostrando su identificador, cuantos lo han jugado, superado y rendido,
	//ademas del indice de exito del propio sudoku y el tiempo medio de resolucion
	public String getHTMLEstadisticasSudoku(String pIdSud) throws ExcepcionConectarBD{
		String data = "<html><h1>ID sudoku: "+pIdSud+"</h1>";
		int[] compYrend = this.getCuantosCompletadoYRendidoSudoku(pIdSud);
		int comp = compYrend[0];
		int rend = compYrend[1];
		int jugado = comp+rend;
		DecimalFormat decf = new DecimalFormat("0.00");
		double porcentComp = ((double)comp/(double)jugado)*100;
		String porcent = decf.format(porcentComp);
		data += "<h2>Info. realizaci&oacute;n: </h2>"
				+"<ul>"
				+"<li>Lo han jugado: "+jugado+"</li>"
				+"<li>Lo han superado: "+comp+"</li>"
				+"<li>Se han rendido: "+rend+"</li>"
				+"<li>&Iacute;ndice de &eacute;xito: "+porcent+"%</li>"
				+"</ul>";
		String tiempoMedio = this.tiempoAString(this.getTiempoMedioResolucionSudoku(pIdSud));
		data += "<h2>Tiempo medio de resoluci&oacute;n: </h2>"
				+"<table border=1 align='center'>"+tiempoMedio+"</table>";
		return data;
	}
	
	//devuelve un array de enteros con el numero de jugadores que han han completado y se han rendido
	//un sudoku a partir de su identificador conectando con la BD
	private int[] getCuantosCompletadoYRendidoSudoku(String pIdSud) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT COMPLETADO AS COMP, COUNT(*) AS NUM "
					+ "FROM JUGADO "
					+ "WHERE JUGADO.ID_SUDOKU = '"+pIdSud+"' GROUP BY COMP;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int[] count = new int[2];
			count[0] = 0; // cuantos jugadores lo han completado
			count[1] = 0; // cuantos jugadores se han rendido
			String comp = "";
			while(result.next()){
				comp = result.getString("COMP");
				if(comp.equals("S")){
					count[0] += result.getInt("NUM");
				} else if(comp.equals("N")){
					count[1] += result.getInt("NUM");
				}
			}
			ConexionBD.getConexionBD().closeResult(result);
			return count;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//calcula el tiempo medio de resolucion de un sudoku a partir de su identificador
	//conectando con la BD y obteniendo los tiempos de los que estan completados
	private int getTiempoMedioResolucionSudoku(String pIdSud) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT AVG(SEGUNDOS) AS MEDIA "
					+ "FROM JUGADO "
					+ "WHERE JUGADO.ID_SUDOKU = '"+pIdSud+"' AND JUGADO.COMPLETADO='S';";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int media = 0;
			for(;result.next();){
				media = result.getInt("MEDIA");
			}
			ConexionBD.getConexionBD().closeResult(result);
			return media;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
}