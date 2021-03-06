package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;

import packExcepciones.ExcepcionConectarBD;

public class GestorJugadores {

	private static GestorJugadores miGestorJugadores;
	
	private GestorJugadores() {}

	public static GestorJugadores getGestor(){
		if(miGestorJugadores==null){
			miGestorJugadores= new GestorJugadores();
		}
		return miGestorJugadores;
	}
	
	//POST: vacio si ya existe usuario, si no existe devuelve su nombre.
	public String registrarJugador(String pNombre, String pPass, String pPregunta, String pRespuesta) throws ExcepcionConectarBD{
		String respuesta="";
		if(!buscarJugador(pNombre)){
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO USUARIO VALUES('"+pNombre+"','"+pPass+"','"+pPregunta+"','"+pRespuesta+"');");
			ConexionBD.getConexionBD().actualizarBD("INSERT INTO JUGADOR(NOMBRE, PTO_TOTAL) VALUES('"+pNombre+"',0);");
			respuesta=pNombre;
		}
		return respuesta;
	}
	
	//POST: true si existe usuario con ese nombre
	private boolean buscarJugador(String pNombre) throws ExcepcionConectarBD{
		boolean esta= false;
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE FROM USUARIO WHERE NOMBRE='"+pNombre+"';");
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
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE FROM USUARIO WHERE NOMBRE='"+pNombre+"' AND CONTR='"+pPass+"';");
		try{
			//comprobamos si es usuario
			if(result.next()){
				ConexionBD.getConexionBD().closeResult(result);
				ResultSet result2=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE FROM JUGADOR WHERE NOMBRE='"+pNombre+"';");
				//comprobamos si es jugador
				if(result2.next()){
					tipoSesion="jugador";
				}
				//si no es jugador, seguro que es admin
				else{
					tipoSesion="admin";
				}
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return tipoSesion;
	}
	
	//PRE: existe un usuario con pNombre
	//POST: devuelve su pregunta de seguridad, si no tiene String vac�o
	public String buscarPreguntaJugador(String pNombre) throws ExcepcionConectarBD{
		String pregunta= "";
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT PREG FROM USUARIO WHERE NOMBRE='"+pNombre+"';");
		try{
			if(result.next()){
				pregunta=result.getString("PREG");}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return pregunta;
	}

	//PRE: existe un usuario con pNombre
	//POST: true si respuesta correcta, si no false
	public boolean comprobarRespuesta(String pNombre,String pRespuesta) throws ExcepcionConectarBD{
		boolean correcta= false;
		ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT RESP FROM USUARIO WHERE NOMBRE='"+pNombre+"';");
		try{
			if(result.next()){
				//case sensitive
				if(result.getString("RESP").compareTo(pRespuesta)==0){
					correcta=true;}
			}	
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return correcta;
	}

	//PRE: existe usuario pNombre
	//POST: actualiza su password
	public void actualizarPass(String pNombre, String pPass) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("UPDATE USUARIO SET CONTR='"+pPass+"' WHERE NOMBRE='"+pNombre+"';");
	}
	
	public String[] getJugadores() throws ExcepcionConectarBD {
		try{
			String sql = "SELECT NOMBRE FROM JUGADOR;";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.last();
			String[] data = new String[result.getRow()];
			result.beforeFirst();
			for(int i = 0;result.next();i++){
				data[i] = result.getString("NOMBRE");
			}
			ConexionBD.getConexionBD().closeResult(result);
			return data;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//PRE: jugador resuelve correctamente el sudoku
	//POST: actualiza la puntuacion de un jugador (sumandola si ya tenia puntos)
	public void actualizarPuntuacion(int pPuntuacion, String pJugador) throws ExcepcionConectarBD {
		try{
			int puntuacion=pPuntuacion;
			//calculamos la puntuacion que ya tiene (si nunca ha jugado tiene 0 puntos)
			String sql = "SELECT PTO_TOTAL FROM JUGADOR WHERE NOMBRE='"+pJugador+"';";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			result.next();
			//sumamos las dos puntuaciones
			puntuacion+=Integer.parseInt(result.getString("PTO_TOTAL"));
			ConexionBD.getConexionBD().closeResult(result);
			//actualizamos con el nuevo valor
			String sql2= "UPDATE JUGADOR SET PTO_TOTAL='"+puntuacion+"' WHERE NOMBRE='"+pJugador+"';";
			ConexionBD.getConexionBD().actualizarBD(sql2);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
