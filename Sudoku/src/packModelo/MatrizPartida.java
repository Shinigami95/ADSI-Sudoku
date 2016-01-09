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

	//POST: Anade el valor en la posicion indicada.
	//		Si habia borrador lo cambia a normal
	public void anadirNumero(char pV, int pX, int pY) {
		IFazCasilla cAux = this.casillas[pX][pY];
		if(cAux.esInicial()==false){
			this.crearCasillaSiNoHay(pX, pY);
			this.casillas[pX][pY].setValor(pV);
		}
	}
	
	//POST: Anade el valor al borrador en la posicion indicada.
	//		Si habia casilla normal la cambia a borrador
	public void anadirBorrador(char pV, int pX, int pY) {
		IFazCasilla cAux = this.casillas[pX][pY];
		if(cAux.esInicial()==false){
			this.crearBorradorSiNoHay(pX, pY);
			this.casillas[pX][pY].setValor(pV);
		}
	}
	
	//POST: devuelve el valor de la casilla
	//		0 si es borrador
	public char getValor(int pX, int pY) {
		if (pX<0 || pX>=MAX_FIL || pY<0 || pY>=MAX_COL){
			System.out.println("Posicion incorrecta");
			return ' ';
		}
		IFazCasilla cAux = this.casillas[pX][pY];
		return cAux.getValor();
	}
	
	//POST: Si habia borrador en la posicion indicada
	//		lo cambia a casilla normal
	private void crearCasillaSiNoHay(int pX, int pY){
		if (casillas[pX][pY] instanceof Borrador){
			Observer obs = casillas[pX][pY].getObserver();
			casillas[pX][pY] = new Casilla('0', false);
			casillas[pX][pY].addObserver(obs);
		}
	}
	
	//POST: Si habia casilla normal en la posicion indicada
	//		la cambia a borrador
	private void crearBorradorSiNoHay(int pX, int pY){
		if (casillas[pX][pY] instanceof Casilla){
			Observer obs = casillas[pX][pY].getObserver();
			casillas[pX][pY] = new Borrador();
			casillas[pX][pY].addObserver(obs);
		}
	}
	
	//POST: Devuelve valores de las casillas en un String
	//		Si era borrador devuelve 0
	public String toStringCasillas(){
		String s = "";
		for(int i = 0; i<MAX_FIL;i++){
			for(int j = 0; j<MAX_COL;j++){
				s += this.casillas[i][j].getValor()+"";
			}
		}
		return s;
	}
	
	//POST: Anade el observer a la casilla
	public void addObserver(Observer pO, int pX, int pY){
		this.casillas[pX][pY].addObserver(pO);
	}
	
	//Introduce valores directamente desde un String
	//se usa al cargar partida pendiente
	public void setValues(String mtab) {
		for (int i=0; i<MAX_FIL; i++){
			for (int j=0; j<MAX_COL; j++){
				casillas[i][j].setValor(mtab.charAt(i*MAX_FIL + j));
			}
		}
	}
}
