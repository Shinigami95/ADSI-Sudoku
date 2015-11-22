package packSudoku;

public class Logros {
	
	static String codSudoku;
	static String descripcionLogro;
	
	public Logros(String cod,String descripcion){
		setCodSudoku(cod);
		setDescripcionLogro(descripcion);
	}
	
	private String getCodSudoku(){
		return this.codSudoku;
	}
	
	private String getDescripcionLogro(){
		return descripcionLogro;
	}
	
	private void setCodSudoku(String cod){
		this.codSudoku=cod;
	}
	
	private void setDescripcionLogro(String descripcion){
		this.descripcionLogro=descripcion;
	}

}
