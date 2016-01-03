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
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE_RETADO,ID_SUDOKU,TIEMPO FROM RETO INNER JOIN PARTIDA ON RETO.ID_SUDOKU=PARTIDA.ID_SUDOKU WHERE ESTADO=’A’ ORDER BY TIEMPO DESC;");
		try{
			//Los 10 primeros
			while(result.next()&&puesto<11){
				rankingR+=puesto + "- "+ result.getString("NOMBRE_RETADO")+ " ha tardado " + result.getShort("TIEMPO") + " en resolver " + result.getString("ID_SUDOKU") + "\n";
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
		ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU,NOMBRE_JUG,PTO FROM JUGADO WHERE ID_SUDOKU='"+pIdSudoku+"' ORDER BY PTO;");
		try{
			//Los 10 primeros
			while(result.next()&&puesto<11){
				rankingUS+=puesto + "- "+ result.getString("NOMBRE_JUG")+ " ha conseguido " + result.getShort("TIEMPO") + " en " + result.getShort("ID_SUDOKU");
				puesto++;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rankingUS;
	}
}
