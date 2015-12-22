package packModelo;

import java.util.Observer;

public class MatrizPartida{
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
		}
	}
	
	public void anadirBorrador(char pV, int pX, int pY) {
		IFazCasilla cAux = this.casillas[pX][pY];
		if(cAux.esInicial()==false){
			this.crearBorradorSiNoHay(pX, pY);
			this.casillas[pX][pY].setValor(pV);
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
			Observer obs = casillas[pX][pY].getObserver();
			casillas[pX][pY] = new Casilla('0', false);
			casillas[pX][pY].addObserver(obs);
		}
	}
	
	private void crearBorradorSiNoHay(int pX, int pY){
		if (casillas[pX][pY] instanceof Casilla){
			Observer obs = casillas[pX][pY].getObserver();
			casillas[pX][pY] = new Borrador();
			casillas[pX][pY].addObserver(obs);
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
	
	public void addObserver(Observer pO, int pX, int pY){
		this.casillas[pX][pY].addObserver(pO);
	}
}
