package packModelo;

import java.util.Observer;

public class Sudoku{

	private String id;
	private MatrizSudoku solucion;
	private MatrizSudoku matriz;

	/**
	 * 
	 * @param pID
	 * @param pSolucion
	 * @param pSinRes
	 */
	public Sudoku(String pID, String pSolucion, String pSinRes) {
		this.id = pID;
		this.solucion = new MatrizSudoku(pSolucion);
		this.matriz = new MatrizSudoku(pSinRes);
	}

	public boolean estaPerfecto() {
		return this.matriz.equalsValues(this.solucion);
	}
	
	public boolean esCorrecto() {
		return this.matriz.esCorrecto();
	}

	public boolean compararConSolucion() {
		return this.solucion.contiene(matriz);
	}

	/**
	 * 
	 * @param pV
	 * @param pX
	 * @param pY
	 */
	public void setValor(char pV, int pX, int pY) {
		this.matriz.setValor(pV, pX, pY);
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public char getValor(int pX, int pY) {
		return this.matriz.getValor(pX, pY);
	}
	
	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public char getValorSolucion(int pX, int pY) {
		return this.solucion.getValor(pX, pY);
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @param pId
	 */
	public boolean tieneId(String pId) {
		return this.id.equals(pId);
	}
	
	public Sudoku clone(){
		return new Sudoku(this.id,this.solucion.toString(),this.matriz.toString());
	}
	
	/**
	 * 
	 * @param pO
	 */
	public void addObserver(Observer pO){
		this.matriz.addObserver(pO);
	}
}