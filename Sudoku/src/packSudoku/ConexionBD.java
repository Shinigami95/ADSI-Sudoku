package packSudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;



import com.mysql.jdbc.Statement;

import packSudoku.excepciones.ExcepcionConectarBD;
//https://www.youtube.com/watch?v=OyN1Uocw2AU
public class ConexionBD {
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://galan.ehu.eus/Xmalboniga002_SUDOKU_BD";//TODO No se conecta y busco el porque
	private String user="Xmalboniga002";
	private String pass="mqDNfeYCa";
	public Connection conexion;
	
	public ConexionBD(){}
	
	public ResultSet consultaBD(String consulta) throws ExcepcionConectarBD{
		ResultSet resul=null;
		try{
		Class.forName(driver);
		conexion=DriverManager.getConnection(url, user, pass);
		Statement state= (Statement) conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		resul=(ResultSet) state.executeQuery(consulta);
		conexion.commit();
		state.close();
		conexion.close();
		
		}
		catch(Exception e){
			throw new ExcepcionConectarBD();
			
		}return resul;
	}
}
