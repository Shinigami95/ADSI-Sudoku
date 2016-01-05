package packControladores;

public class GestorSesion {
	private static GestorSesion miGestor;
	private String user;
	private String tipoSesion;//admin o usuario
	
	//TODO: habrá que poner el user a null (de momento "Jorge" para pruebas)
	private GestorSesion() {
		user="Jorge";
		tipoSesion=null;
	}

	public static GestorSesion getGestor(){
		if(miGestor==null){
			miGestor = new GestorSesion();
		}
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


