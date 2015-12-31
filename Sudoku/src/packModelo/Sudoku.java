package packModelo;

public class Sudoku{

	private int id;
	private int dif;
	private MatrizSudoku solucion;
	private MatrizSudoku inicial;

	public Sudoku(int pID, int pDif, String pSolucion, String pSinRes) {
		this.id = pID;
		this.dif = pDif;
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
		return this.solucion.getValor(pX, pY);
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean tieneId(int pId) {
		return this.id == pId;
	}
	
	public String toStringMatrizInicial() {
		return this.inicial.toStringValores();
	}
	
	public String toStringMatrizSolucion() {
		return this.solucion.toStringValores();
	}
	
	public int getDificultad(){
		return dif;
	}
}