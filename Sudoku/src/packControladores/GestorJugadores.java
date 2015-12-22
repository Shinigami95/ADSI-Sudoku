package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorJugadores {

	private static GestorJugadores miGestorJugadores;
	
	private GestorJugadores() {
		// TODO Auto-generated constructor stub
	}

	public static GestorJugadores getGestorJugadores(){
		if(miGestorJugadores==null){
			miGestorJugadores= new GestorJugadores();
		}
		return miGestorJugadores;
	}
	
	public static void main(String args[]) throws ExcepcionConectarBD{
		getGestorJugadores().registrarJugador("PRUEB", "FACIL", "1", "2");
		//ConexionBD.getConexionBD().actualizarBD("INSERT INTO USUARIO(NOMBRE,CONTR) VALUES('PRUEB5','Q')");
		//System.out.println(getGestorJugadores().identificarUsuario("PRUEBA3", "FACIL"));
	}
	
	//problema no introduce en jugador, devuelve duplicate key
	public void registrarJugador(String pNombre, String pPass, String pPregunta, String pRespuesta) throws ExcepcionConectarBD{
		if(!buscarJugador(pNombre)){
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO USUARIO VALUES('"+pNombre+"','"+pPass+"','"+pPregunta+"','"+pRespuesta+"')");
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO JUGADOR(NOMBRE) VALUES('"+pNombre+"')");
		}
	}
	
	//POST: true si existe
	private boolean buscarJugador(String pNombre) throws ExcepcionConectarBD{
		boolean esta= false;
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE FROM USUARIO WHERE NOMBRE='"+pNombre+"'");
		try{
			if(result.next()){
				esta=true;}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return esta;
	}
	
	//PRE: siempre un usuario es o jugador o admin
	/*POST: incorrecta= no existe jugador o pass incorrecta
	 * 		jugador = tipo sesion jugador
	 * 		admin = tipo sesion administrador 
	 */
	public String identificarUsuario(String pNombre, String pPass) throws ExcepcionConectarBD{
		String tipoSesion = "incorrecta";
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE FROM USUARIO WHERE NOMBRE='"+pNombre+"' AND CONTR='"+pPass+"'");
		try{
			if(result.next()){
				ConexionBD.getConexionBD().closeResult(result);
				ResultSet result2=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE FROM JUGADOR WHERE NOMBRE='"+pNombre+"'");
				if(result2.next()){
					tipoSesion="jugador";
				}
				else{
					tipoSesion="admin";
				}
				System.out.println(result2.getString("NOMBRE"));
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return tipoSesion;
	}
	
	public String buscarPreguntaJugador(String pNombre) throws ExcepcionConectarBD{
		String pregunta= "";
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT PREG FROM USUARIO WHERE NOMBRE='"+pNombre+"'");
		try{
			if(result.next()){
				pregunta=result.getString("PREG");}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return pregunta;
	}
	
	public void actualizarPass(String pNombre, String pPass) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("UPDATE USUARIO SET CONTR='"+pPass+"' WHERE NOMBRE='"+pNombre+"'");
	}
	
	public boolean comprobarRespuesta(String pNombre,String pRespuesta) throws ExcepcionConectarBD{
		boolean correcta= false;
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT RESP FROM USUARIO WHERE NOMBRE='"+pNombre+"'");
		try{
			if(result.next()){
				if(result.getString("RESP").compareTo(pRespuesta)==0){
					correcta=true;}
			}	
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return correcta;
	}
}
