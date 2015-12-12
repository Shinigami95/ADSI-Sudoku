package packSudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import packSudoku.excepciones.ExcepcionConectarBD;
//https://www.youtube.com/watch?v=OyN1Uocw2AU
public class ConexionBD {
	private static String driver;
	//private String url="jdbc:mysql://galan.ehu.eus/Xmalboniga002_SUDOKU_BD";//TODO No se conecta y busco el porque
	private String url;
	private String user;
	private String pass;
	public static ConexionBD miConexion;
	
	private ConexionBD(){
/*		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://galan.ehu.eus";
		user="Xmalboniga002";
		pass="mqDNfeYCa";
*/
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://galan.ehu.eus/Xmalboniga002_user_anime_bd";
		user="Xmalboniga002";
		pass="mqDNfeYCa";
	}
	
	public static void main(String[] args){
		System.out.println("Start");
		try {
			ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT * FROM SERIE;");
			while(result.next()){
				System.out.println(result.getString("NOMBRE"));
			}
			ConexionBD.getConexionBD().closeResult(result);
			System.out.println("End");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConexionBD getConexionBD(){
		if (miConexion==null){
			miConexion = new ConexionBD();
		}
		return miConexion;
	}
	
	public ResultSet consultaBD(String consulta) throws ExcepcionConectarBD{
		ResultSet result=null;
		try{
			Class.forName(driver);
			Connection conexion=DriverManager.getConnection(url,user,pass);
			Statement state = (Statement) conexion.createStatement();
			conexion.setAutoCommit(false);
			result=(ResultSet) state.executeQuery(consulta);
			conexion.commit();
		}
		catch(Exception e){
			throw new ExcepcionConectarBD();
		}
		return result;
	}
	
	public void closeResult(ResultSet result){
		try {
			result.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
