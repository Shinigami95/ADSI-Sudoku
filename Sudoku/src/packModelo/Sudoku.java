package packModelo;

import java.util.Observer;

public class Sudoku{

	private String id;
	private MatrizSudoku solucion;
	private MatrizSudoku inicial;

	public Sudoku(String pID, String pSolucion, String pSinRes) {
		this.id = pID;
		this.solucion = new MatrizSudoku(pSolucion);
		this.inicial = new MatrizSudoku(pSinRes);
	}

	public boolean estaPerfecto() {
		return this.solucion.equalsValues(this.inicial);
	}
	
	public boolean esCorrecto() {
		return this.inicial.esCorrecto();
	}

	public boolean compararConSolucion() {
		return this.solucion.contiene(inicial);
	}

	public void setValor(char pV, int pX, int pY) {
		this.inicial.setValor(pV, pX, pY);
	}

	public char getValor(int pX, int pY) {
		return this.inicial.getValor(pX, pY);
	}
	
	public char getValorSolucion(int pX, int pY) {
		return this.inicial.getValor(pX, pY);
	}
	
	public String getId() {
		return this.id;
	}
	
	public boolean tieneId(String pId) {
		return this.id.equals(pId);
	}
	
	public Sudoku clone(){
		return new Sudoku(this.id,this.solucion.toString(),this.inicial.toString());
	}

	public String toStringMatrizInicial() {
		return this.inicial.toStringValores();
	}
}