package packControladores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packExcepciones.ExcepcionConectarBD;
import packExcepciones.NoValidoException;
import packExcepciones.SudokuActivadoException;
import packExcepciones.SudokuDesactivadoException;
import packExcepciones.YaExisteException;
import packModelo.Sudoku;


public class GestorAdministrador {

	
	private static GestorAdministrador miGestor = new GestorAdministrador();
	
	private GestorAdministrador() {
		
	}
	
	public static GestorAdministrador getGestorAdministrador(){
		return miGestor;
	}
	
	
	public void añadirSudoku(String mCompleta, String mIncompleta , int dificultad) throws YaExisteException,NoValidoException{
		
		Sudoku s = new Sudoku(1,dificultad,mCompleta,mIncompleta);
		boolean b = s.comprobarSudoku();
		String sql;
		//Si es valido
		if(b){
			
			boolean b2 = GestorAdministrador.getGestorAdministrador().comprobarSiExiste(mCompleta);
			
			if(b2){
				//Si no existe se sube a la BD				
				sql="INSERT INTO SUDOKU(DIFICULTAD,M_INIC,M_SOL,ACTIVO) VALUES(%"+dificultad+",%"+mIncompleta+",%"+mCompleta+",'T')";
				try {
					ConexionBD.getConexionBD().actualizarBD(sql);
				} catch (ExcepcionConectarBD e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}else
			{
				//Si existe salta una excepcion
				throw new YaExisteException("El sudoku ya existe");
			}
			
		}else
		{
		  	//Si no es valido salta una excepcion
			throw new NoValidoException("El sudoku no es valido");
		}
		
		
	}
	
	private boolean comprobarSiExiste(String pCompleta){
		String sql = "SELECT * FROM SUDOKU WHERE M_SOL=%"+pCompleta;
		
		
		try {
			ResultSet rs = ConexionBD.getConexionBD().consultaBD(sql);
			//Si existe
			if(rs.next()){
				rs.close();
				return true;				
			}else{
				//Si no existe
				rs.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
		
	public Sudoku buscarSudokuPorCodigo(int pCodigo){
		String sql = "SELECT * FROM SUDOKU WHERE ID_S=%"+pCodigo;
		ResultSet rs;
		Sudoku s = null;
		try {
			rs = ConexionBD.getConexionBD().consultaBD(sql);
			rs.next();
			int dificultad = rs.getInt("DIFICULTAD");		
			String completa = rs.getString("M_SOL");
			String incompleta = rs.getString("M_INIC");
			s = new Sudoku(pCodigo,dificultad,completa,incompleta);
			rs.close();
			
		} catch (ExcepcionConectarBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
				
		return s;
	}
	public void modificarSudoku(int pCodigo,String mCompleta,String mIncompleta,int dificultad) throws NoValidoException{
		Sudoku s = new Sudoku(pCodigo,dificultad,mCompleta,mIncompleta);
		boolean b = s.comprobarSudoku();
		//Si es valido
		if(b){
			
			String sql="UPDATE SUDOKU SET DIFICULTAD=%"+dificultad+",M_INIC=%"+mIncompleta+",M_SOL=%"+mCompleta+" WHERE ID_S=%"+pCodigo;
			try {
				ConexionBD.getConexionBD().actualizarBD(sql);
			} catch (ExcepcionConectarBD e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			//Si no es valido
			throw new NoValidoException("El sudoku no es valido");
		}
	}
	
	
	public void activarSudoku(int pCodigo) throws SudokuActivadoException{
		String sql = "SELECT * FROM SUDOKU WHERE ID_S=%"+pCodigo+" AND ACTIVO='T'";
		
		try {
			ResultSet rs = ConexionBD.getConexionBD().consultaBD(sql);
			//Si hay tupla y esta activada
			if(rs.next()){
				rs.close();
				throw new SudokuActivadoException("Ya esta el sudoku desactivado");
			}else
			{
				rs.close();
				//Actualizamos en BD
				sql = "UPDATE SUDOKU SET ACTIVO='T' WHERE ID_S=%"+pCodigo;
				ConexionBD.getConexionBD().actualizarBD(sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void desactivarSudoku(int pCodigo) throws SudokuDesactivadoException{
		String sql = "SELECT * FROM SUDOKU WHERE ID_S=%"+pCodigo+" AND ACTIVO='F'";
		
		
		try {
			ResultSet rs = ConexionBD.getConexionBD().consultaBD(sql);
			//Si hay tupla y esta desactivada
			if(rs.next()){
				rs.close();
				throw new SudokuDesactivadoException("Ya esta el sudoku desactivado");
			}else
			{
				rs.close();
				//Actualizamos en BD
				sql = "UPDATE SUDOKU SET ACTIVO='F' WHERE ID_S=%"+pCodigo;
				ConexionBD.getConexionBD().actualizarBD(sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void borrarSudoku(int pCodigo){
		String sql = "DELETE FROM SUDOKU WHERE ID_S=%"+pCodigo;
		try {
			ConexionBD.getConexionBD().actualizarBD(sql);
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public ArrayList<String> getListaCodigosSudoku(){
		ArrayList<String> lista = new ArrayList<String>();
		
		String sql = "SELECT ID_S FROM SUDOKU";
		
		//Recorremos la lista
		try {
			ResultSet rs = ConexionBD.getConexionBD().consultaBD(sql);
			while(rs.next()){			
				lista.add(rs.getString("ID_S"));
			}
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return lista;
	}
	
}
