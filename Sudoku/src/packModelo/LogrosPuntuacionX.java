package packModelo;

public class LogrosPuntuacionX extends Logros{
	
	int puntuacion;
	
	public LogrosPuntuacionX(String codS,String codL,String descripcion,int puntos){
		super(codS,codL, descripcion);
		setPuntuacion(puntos);
	}
	
	private int getPuntuacion(){
		return this.puntuacion;
	}
	
	private void setPuntuacion(int puntos){
		this.puntuacion=puntos;
	}
}
