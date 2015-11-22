package packSudoku;

public class LogrosPuntuacionXY extends Logros {
	
	int puntuacion;
	int jugadores;
	
	public LogrosPuntuacionXY(int puntos,int jugadores) {
		super(codSudoku, descripcionLogro);
		setPuntuacion(puntos);
		setJugadores(jugadores);
	}
	
	private int getPuntuacion(){
		return this.puntuacion;
	}
	
	private void setPuntuacion(int puntos){
		this.puntuacion=puntos;
	}
	
	private int getJugadores(){
		return this.jugadores;
	}
	
	private void setJugadores(int jugadores){
		this.jugadores=jugadores;
	}
	
}


