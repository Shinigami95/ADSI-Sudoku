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
	
	//Rellena las varibles con los datos para conectarse a la bd
	/*Precondicion:
	 *Postcondicion:
	 * */
	private ConexionBD(){
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://galan.ehu.eus/Xmalboniga002_SUDOKU_BD";
		user="Xmalboniga002";
		pass="mqDNfeYCa";
	}
	
	//Singelton
	/*Precondicion:
	 *Postcondicion:
	 * */
	public static ConexionBD getConexionBD(){
		if (miConexion==null){
			miConexion = new ConexionBD();
		}
		return miConexion;
	}
	
	//A este metodo se le pasa un String con la consulta y nos devuelve el resultado de esta.
	/*Precondicion: Poner una consulta SQL sin errores.
	 *Postcondicion:
	 * */
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
	//Este metodo se llama despues de hacer una consulta. Cierra la conexion que habia quedado abierta.
	/*Precondicion: Pasarle el resultado de una consulta que se habia hecho.
	 *Postcondicion: La conexion a la bd queda cerrada.
	 * */
	public void closeResult(ResultSet result){
		try {
			result.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Metodo al que se le pasan sentencias SQL que impliquen modificaciones en la bd(añadir,eliminar o modificar).
	/*Precondicion: Pasarle una sentencia SQL valida.
	 *Postcondicion: La bd se ha actualizado correctamente.
	 * */
	public void actualizarBD(String sentencia)throws ExcepcionConectarBD{
		try{
			Class.forName(driver);
			Connection conexion=DriverManager.getConnection(url,user,pass);
			Statement state=(Statement) conexion.createStatement();
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
