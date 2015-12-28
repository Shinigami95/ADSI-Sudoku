package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import packExcepciones.ExcepcionConectarBD;

public class GestorEstadisticas {
	private static GestorEstadisticas mGestor = null;
	
	private GestorEstadisticas(){}
	
	public static void main(String[] args){
		try {
			String est = GestorEstadisticas.getGestor().getHTMLEstadisticasJugador("Jorge");
			System.out.println(est);
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static GestorEstadisticas getGestor(){
		if(mGestor==null){
			mGestor = new GestorEstadisticas();
		}
		return mGestor;
	}
	
	public String getHTMLEstadisticasJugadorSesion() throws ExcepcionConectarBD{
		return this.getHTMLEstadisticasJugador(GestorSesion.getGestor().getUserSesion());
	}
	
	public String getHTMLEstadisticasJugador(String pJugador) throws ExcepcionConectarBD{
		String res = "<html>"; 
		res += "<h1>Jugador: "+pJugador+"</h1>";
		res += this.getHTMLTiempoMedioResolucionPorCategoriaDelJugador(pJugador)+"\n\n";
		res += this.getHTMLInfoSudokusResueltosDelJugador(pJugador);
		res += "</html>";
		return res;
	}
	
	private String getHTMLTiempoMedioResolucionPorCategoriaDelJugador(String pJugador) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT SUDOKU.DIFICULTAD AS DIF, AVG(JUGADO.SEGUNDOS) AS MEDIA, COUNT(*) AS NUM "
					+ "FROM JUGADO INNER JOIN SUDOKU ON SUDOKU.ID_S = JUGADO.ID_SUDOKU "
					+ "WHERE JUGADO.NOMBRE_JUG = '"+pJugador+"' AND JUGADO.COMPLETADO = 'S' GROUP BY DIF ORDER BY DIF;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			String data = "<h2>Tiempo medio por categor&iacute;a:</h2>";
			data += "<table border=1><tr><th>Dificultad</th><th>Tiempo Medio</th><th>Num. Hechos</th></tr>";
			int dif;
			int media;
			int num;
			while(result.next()){
				dif = result.getInt("DIF");
				media = result.getInt("MEDIA");
				num = result.getInt("NUM");
				data += "<tr><td>"+dif+"</td><td>"+tiempoAString(media)+"</td><td>"+num+"</td></tr>";
			}
			data += "</table>";
			ConexionBD.getConexionBD().closeResult(result);
			return data;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	private String getHTMLInfoSudokusResueltosDelJugador(String pJugador) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT JUGADO.COMPLETADO AS COMP, COUNT(*) AS NUM "
					+ "FROM JUGADO INNER JOIN SUDOKU ON SUDOKU.ID_S = JUGADO.ID_SUDOKU "
					+ "WHERE JUGADO.NOMBRE_JUG = '"+pJugador+"' GROUP BY COMP;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			String data = "<h2>Info. sudokus realizados: </h2>";
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
				data += "<ul>";
				data += "<li>Total sudokus: "+totalSudokus+"</li>";
				data += "<li>Resueltos: "+sudCompletos+"</li>";
				data += "<li>Incompletos: "+sudIncompletos+"</li>";
				DecimalFormat decf = new DecimalFormat("0.00");
				double porcentComp = ((double)sudCompletos/(double)totalSudokus)*100;
				String porcent = decf.format(porcentComp);
				data += "<li>&Iacute;ndice de exito: "+porcent+"%</li>";
				data += "</ul>";
			}
			return data;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return "";
		}
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
	
	public String getEstadisticasSudoku(int pIdSud){
		return null;
	}
}
