package packControladores;

public class GestorSesion {
	private static GestorSesion miGestor;
	private String user;
	
	//TODO: habrá que poner el user a null (de momento "Jorge" para pruebas)
	private GestorSesion() {
		user="Jorge";
	}

	public static GestorSesion getGestor(){
		if(miGestor==null){
			miGestor = new GestorSesion();
		}
		return miGestor;
	}

	public void iniciarSesion(String pJugador){
		this.user=pJugador;
	}
	
	public void cerrarSesion(){
		this.user=null;
	}
	
	public String getUserSesion(){
		return user;
	}
}


