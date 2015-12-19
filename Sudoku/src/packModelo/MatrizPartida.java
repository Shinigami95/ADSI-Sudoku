package packModelo;

import java.util.Observable;
import java.util.Observer;

public class MatrizPartida  extends Observable{
	private IFazCasilla[][] casillas;
	private static final int MAX_FIL = 9;
	private static final int MAX_COL = 9;
	
	public MatrizPartida(String pSud) {
		this.casillas = new IFazCasilla[MAX_FIL][MAX_COL];
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

	public void anadirNumero(char pV, int pX, int pY) {
		IFazCasilla cAux = this.casillas[pX][pY];
		if(cAux.esInicial()==false){
			this.crearCasillaSiNoHay(pX, pY);
			this.casillas[pX][pY].setValor(pV);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void anadirBorrador(char pV, int pX, int pY) {
		IFazCasilla cAux = this.casillas[pX][pY];
		if(cAux.esInicial()==false){
			this.crearBorradorSiNoHay(pX, pY);
			this.casillas[pX][pY].setValor(pV);
			this.setChanged();
			this.notifyObservers();
		}
	}
	public char getValor(int pX, int pY) {
		if (pX<0 || pX>=MAX_FIL || pY<0 || pY>=MAX_COL){
			System.out.println("Posicion incorrecta");
			return ' ';
		}
		IFazCasilla cAux = this.casillas[pX][pY];
		return cAux.getValor();
	}
	
	private void crearCasillaSiNoHay(int pX, int pY){
		if (casillas[pX][pY] instanceof Borrador){
			casillas[pX][pY] = new Casilla('0', false);
		}
	}
	
	private void crearBorradorSiNoHay(int pX, int pY){
		if (casillas[pX][pY] instanceof Casilla){
			casillas[pX][pY] = new Borrador();
		}
	}
	
	public String toStringCasillas(){
		String s = "";
		for(int i = 0; i<MAX_FIL;i++){
			for(int j = 0; j<MAX_COL;j++){
				s += this.casillas[i][j].getValor()+"";
			}
		}
		return s;
	}
	
	@Override
	public void addObserver(Observer pO){
		super.addObserver(pO);
	}
}