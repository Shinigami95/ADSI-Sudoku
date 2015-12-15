package packModelo;

public class Logros {
	
	String codSudoku;
	String codLogro;
	String descripcionLogro;
	
	public Logros(String codS,String codL,String descripcion){
		setCodLogro(codL);
		setDescripcionLogro(descripcion);
	}
	
	private String getCodSudoku(){
		return this.codSudoku;
	}
	
	private String getCodLogro(){
		return this.codLogro;
	}
	
	private String getDescripcionLogro(){
		return descripcionLogro;
	}
	
	private void setCodSudoku(String codS){
		this.codSudoku=codS;
	}
	
	private void setCodLogro(String codL){
		this.codLogro=codL;
	}
	
	private void setDescripcionLogro(String descripcion){
		this.descripcionLogro=descripcion;
	}

}
