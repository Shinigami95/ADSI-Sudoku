package packControladores;

public class GestorSesion {
	private static GestorSesion miGestor;
	private String user;
	
	private GestorSesion() {
		user=null;
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


