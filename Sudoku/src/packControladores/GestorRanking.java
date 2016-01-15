package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorRanking {
	
	private static GestorRanking miGestorRanking;

	private GestorRanking() {}

	public static GestorRanking getGestorRanking(){
		if(miGestorRanking == null){
			miGestorRanking = new GestorRanking();
		}
		return miGestorRanking;
	}
	
	public String obtenerRankingPuntuacion() throws ExcepcionConectarBD{
		String rankingP = "";
        int puesto = 1;
        ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE,PTO_TOTAL FROM JUGADOR ORDER BY PTO_TOTAL DESC;");
        try{
            //sacamos solo los 10 primeros
            while(result.next()&&puesto<11){
                rankingP += puesto + "- " + result.getString("NOMBRE") + ": " + result.getShort("PTO_TOTAL") + "\n";
                puesto++;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return rankingP;
	}
	
	public String obtenerRankingRetos() throws ExcepcionConectarBD{
		String rankingR = "";
		int puesto = 1;
		String sql = "SELECT NOMBRE_RETADOR,NOMBRE_RETADO,RETO.ID_SUDOKU,SEGUNDOS "
				+ "FROM RETO INNER JOIN JUGADO ON RETO.ID_SUDOKU=JUGADO.ID_SUDOKU AND RETO.NOMBRE_RETADO=JUGADO.NOMBRE_JUG "
				+ "WHERE ESTADO='T' ORDER BY SEGUNDOS DESC;";
		ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
		try{
			//Los 10 primeros
			while(result.next()&&puesto<11){
				String retado = result.getString("NOMBRE_RETADO");
				int seg = result.getInt("SEGUNDOS");
				int idSud = result.getInt("ID_SUDOKU");
				String retador = result.getString("NOMBRE_RETADOR");
				rankingR+=puesto + "- "+retado+ " ha tardado " + seg + " en resolver " + idSud+ ", le reto "+retador+"\n";
				puesto++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rankingR;
	}
	
	public String obtenerRankingUnSudoku(int pIdSudoku) throws ExcepcionConectarBD{
		String rankingUS = "";
		int puesto = 1;
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU,NOMBRE_JUG,PTO FROM JUGADO WHERE ID_SUDOKU='"+pIdSudoku+"' ORDER BY PTO DESC;");
		try{
			//Los 10 primeros
			while(result.next()&&puesto<11){
				rankingUS+=puesto + "- "+ result.getString("NOMBRE_JUG")+ " ha conseguido " + result.getShort("PTO") + " en " + result.getShort("ID_SUDOKU") + "\n";
				puesto++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rankingUS;
	}
}
