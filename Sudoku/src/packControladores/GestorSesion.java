package packControladores;

public class GestorSesion {
  	private static GestorSesion miGestor;
  	private String user;
	private String tipoSesion;//admin o usuario
  	
	//habrá que modificarlo
  	private GestorSesion() {user = "Jorge";}
  
  	public static GestorSesion getGestor(){
  		return miGestor;
  	}
  	
	public void iniciarSesion(String pJugador, String pTipoSesion){
		this.user=pJugador;
		this.tipoSesion=pTipoSesion;
	}
	
 	public void cerrarSesion(){
		this.user=null;
 		this.tipoSesion=null;
 	}
 	
  	public String getUserSesion(){
  		return user;
  	}
}