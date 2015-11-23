package packSudoku;

public class LogrosPuntuacionXY extends Logros {
	
	int puntuacion;
	int jugadores;
	
	public LogrosPuntuacionXY(String codS,String codL,String descripcion,int puntos,int jugadores) {
		super(codS,codL, descripcion);
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


