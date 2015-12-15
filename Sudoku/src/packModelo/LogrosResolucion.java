package packModelo;

public class LogrosResolucion extends Logros{
	
	int jugadores;
	
	public LogrosResolucion(String codS,String codL,String descripcion,int jugador){
		super(codS,codL, descripcion);
		setJugadores(jugador);
	}
	
	private int getJugadores(){
		return this.jugadores;
	}
	
	private void setJugadores(int jugador){
		this.jugadores=jugador;
	}
	
}
