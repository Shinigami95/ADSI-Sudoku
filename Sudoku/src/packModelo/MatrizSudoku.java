package packModelo;

import java.util.Observable;
import java.util.Observer;

public class MatrizSudoku extends Observable {

	private Casilla[][] casillas;
	private static final int MAX_FIL = 9;
	private static final int MAX_COL = 9;
	
	/**
	 * 
	 * @param pSud
	 */
	public MatrizSudoku(String pSud) {
		this.casillas = new Casilla[MAX_FIL][MAX_COL];
		int sIndex = 0;
		char cNum;
		boolean inic;
		for (int i=0; i<MAX_FIL; i++){
			for (int j=0; j<MAX_COL; j++){
				cNum = pSud.charAt(sIndex);
				if(cNum=='0') inic = false;
				else inic = true;
				casillas[i][j] = new Casilla(cNum,inic);
				sIndex++;
			}
		}
	}

	/**
	 * 
	 * @param pM
	 */
	public boolean contiene(MatrizSudoku pM) {
		boolean result = true;
		int i=0, j=0;
		char c1, c2;
		while(i<MAX_FIL&&result){
			j=0;
			while(j<MAX_COL&&result){
				c1 = this.casillas[i][j].getValor();
				c2 = pM.getValor(i,j);
				if(c1!=c2&&c2!='0'){
					result=false;
				}
				j++;
			}
			i++;
		}
		return result;
	}

	public boolean esCorrecto() {
		boolean result = true;
		int i = 0;
		while(i<MAX_FIL&&result){
			result &= this.evaluarFila(i);
			i++;
		}
		int j = 0;
		while(j<MAX_COL&&result){
			result &= this.evaluarCol(j);
			j++;
		}
		i=0;
		j=0;
		while(i<3&&result){
			j=0;
			while(j<3&&result){
				result &= this.evaluarSec(i,j);
				j++;
			}
			i++;
		}
		return result;
	}
	
	/**
	 * 
	 * @param pF
	 */
	private boolean evaluarFila(int pF) {
		if(pF<0||pF>=MAX_FIL){
			System.out.println("Fila no valida");
			return false;
		}
		boolean result = true;
		char c;
		int[] contadores = new int[MAX_FIL+1];
		for (int i=0;i<MAX_FIL+1;i++){
			contadores[i]=0;
		}
		for (int i=0;i<MAX_FIL;i++){
			c = casillas[pF][i].getValor();
			contadores[Integer.parseInt(c+"")]++;
		}
		for (int i=1;i<MAX_FIL+1;i++){
			if(contadores[i]>1){
				result = false;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param pC
	 */
	private boolean evaluarCol(int pC) {
		if(pC<0||pC>=MAX_COL){
			System.out.println("Fila no valida");
			return false;
		}
		boolean result = true;
		char c;
		int[] contadores = new int[MAX_FIL+1];
		for (int i=0;i<MAX_COL+1;i++){
			contadores[i]=0;
		}
		for (int i=0;i<MAX_COL;i++){
			c = casillas[i][pC].getValor();
			contadores[Integer.parseInt(c+"")]++;
		}
		for (int i=1;i<MAX_COL+1;i++){
			if(contadores[i]>1){
				result = false;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	private boolean evaluarSec(int pX, int pY) {	
		if(pX<0||pX>=3||pY<0||pY>=3){
			System.out.println("Seccion incorrecta");
			return false;
		}
		boolean result = true;
		char c;
		int[] contadores = new int[MAX_FIL+1];
		for (int i=0;i<=MAX_FIL;i++){
			contadores[i]=0;
		}
		for (int i=pX*3;i<pX*3+3;i++){
			for (int j=pY*3;j<pY*3+3;j++){
				c = casillas[i][j].getValor();
				contadores[Integer.parseInt(c+"")]++;
			}
		}
		for (int i=1;i<MAX_FIL+1;i++){
			if(contadores[i]>1){
				result = false;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param pV
	 * @param pX
	 * @param pY
	 */
	public void setValor(char pV, int pX, int pY) {
		Casilla cAux = this.casillas[pX][pY];
		if(cAux.getValor()!=pV){
			cAux.setValor(pV);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public char getValor(int pX, int pY) {
		if (pX<0 || pX>=MAX_FIL || pY<0 || pY>=MAX_COL){
			System.out.println("Posicion incorrecta");
			return ' ';
		}
		Casilla cAux = this.casillas[pX][pY];
		return cAux.getValor();
	}
	
	public boolean equalsValues(MatrizSudoku pM){
		for (int i=0; i<MAX_FIL; i++){
			for (int j=0; j<MAX_COL; j++){
				if (this.getValor(i, j)!=pM.getValor(i, j)) return false;
			}
		}
		return true;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i<MAX_FIL;i++){
			for(int j = 0; j<MAX_COL;j++){
				s += this.casillas[i][j].getValor()+"";
			}
		}
		return s;
	}
	
	/**
	 * 
	 * @param pO
	 */
	@Override
	public void addObserver(Observer pO){
		super.addObserver(pO);
	}
}