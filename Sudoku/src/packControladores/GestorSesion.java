package packControladores;

public class GestorSesion {
	private static GestorSesion miGestor;
	private String user;
	private String tipoSesion;//admin o usuario
	private boolean esReto;
	
	//habrá que modificarlo
	private GestorSesion() {user = "Jorge";}

	public static GestorSesion getGestor(){
		if(miGestor==null){
			miGestor = new GestorSesion();
		}
		return miGestor;
	}

	public void iniciarSesion(String pJugador, String pTipoSesion){
		this.user=pJugador;
		this.tipoSesion=pTipoSesion;
		this.esReto=false;
	}
	
	public void cerrarSesion(){
		this.user=null;
		this.tipoSesion=null;
	}
	
	public String getUserSesion(){
		return user;
	}
	
	public boolean getEsReto(){
		return esReto;
	}
	
	public void iniciarReto(){
		esReto=true;
	}
	
	public void finReto(){
		esReto=false;
	}
}


