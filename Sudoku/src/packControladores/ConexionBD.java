package packControladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.mysql.jdbc.Statement;

import packExcepciones.ExcepcionConectarBD;
//https://www.youtube.com/watch?v=OyN1Uocw2AU
public class ConexionBD {
	private static String driver;
	private String url;
	private String user;
	private String pass;
	public static ConexionBD miConexion;
	
	public static void main(String args[]) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("INSERT INTO USUARIO(NOMBRE,CONTR) VALUES('JORGE','123456');");
	}
	
	private ConexionBD(){
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://galan.ehu.eus/Xmalboniga002_SUDOKU_BD";
		user="Xmalboniga002";
		pass="mqDNfeYCa";
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
			e.printStackTrace();
			throw new ExcepcionConectarBD();
		}
		return result;
	}
	
	public void closeResult(ResultSet result){
		try {
			result.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actualizarBD(String sentencia)throws ExcepcionConectarBD{
		try{
			Class.forName(driver);
			Connection conexion=DriverManager.getConnection(url,user,pass);
			Statement state=(Statement) conexion.createStatement();

			state.executeUpdate(sentencia);

			conexion.setAutoCommit(false);
			state.executeUpdate(sentencia);
			conexion.commit();

			state.getConnection().close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ExcepcionConectarBD();
		}
	}
}
