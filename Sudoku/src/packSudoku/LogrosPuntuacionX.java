package packSudoku;

public class LogrosPuntuacionX extends Logros{
	
	int puntuacion;
	
	public LogrosPuntuacionX(int puntos){
		super(codSudoku, descripcionLogro);
		setPuntuacion(puntos);
	}
	
	private int getPuntuacion(){
		return this.puntuacion;
	}
	
	private void setPuntuacion(int puntos){
		this.puntuacion=puntos;
	}
}
