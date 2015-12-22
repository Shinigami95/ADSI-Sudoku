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
		getGestorJugadores().comprobarRespuesta("MIKEL3", "ADIOS");
	}
	
	public void addJugador(String pNombre, String pPass, String pPregunta, String pRespuesta) throws ExcepcionConectarBD{
		if(!buscarJugador(pNombre)){
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO USUARIO VALUES('"+pNombre+"','"+pPass+"','"+pPregunta+"','"+pRespuesta+"')");
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO JUGADOR VALUES('"+pNombre+"',0)");
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
	
	public String identificarJugador(String pNombre, String pPass){
		//TODO
		return "0";
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
