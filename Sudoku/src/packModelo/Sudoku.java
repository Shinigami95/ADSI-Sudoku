package packModelo;

import packExcepciones.NoValidoException;

public class Sudoku{

	private int id;
	private int dif;
	private MatrizSudoku solucion;
	private MatrizSudoku inicial;
	private boolean estaActivo;

	public Sudoku(int pID, int pDif, String pSolucion, String pSinRes, boolean pAct) throws NoValidoException {
		this.id = pID;
		this.dif = pDif;
		this.estaActivo = pAct;
		if(pSolucion.length()==81 && pSinRes.length()==81){
			this.solucion = new MatrizSudoku(pSolucion);
			this.inicial = new MatrizSudoku(pSinRes);
			if(!this.estaBienFormadoSudoku()){
				throw new NoValidoException();
			}
		}
		else{
			throw new NoValidoException();
		}
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
	
	private boolean estaBienFormadoSudoku(){
		if(!this.compararConSolucion()){
			return false;
		}
		if(!this.esCorrecto()){
			return false;
		}
		return true;
	}

	public boolean getEstaActivo() {
		return estaActivo;
	}
}