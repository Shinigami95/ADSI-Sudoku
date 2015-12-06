package packSudoku;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import packSudoku.excepciones.ExcepcionConectarBD;
//https://www.youtube.com/watch?v=OyN1Uocw2AU
public class ConexionBD {
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://galan.ehu.eus/Xmalboniga002_SUDOKU_BD";//TODO No se si esto esta bien
	private String user="Xmalboniga002";
	private String pass="mqDNfeYCa";
	public Connection conexion;
	
	public ConexionBD() throws ExcepcionConectarBD{
		try{
		Class.forName(driver);
		conexion=DriverManager.getConnection(url, user, pass);
		JOptionPane.showMessageDialog(null, "Se ha establecido la conexion.");
		}
		catch(Exception e){
			throw new ExcepcionConectarBD();
			
		}
	}
}
