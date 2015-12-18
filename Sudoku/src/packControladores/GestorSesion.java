package packControladores;

public class GestorSesion {
	private static GestorSesion miGestor;
	private String user;
	
	private GestorSesion() {user = "Jorge";}

	public static GestorSesion getGestor(){
		if(miGestor==null){
			miGestor = new GestorSesion();
		}
		return miGestor;
	}
	
	public String getUserSesion(){
		return user;
	}
}
