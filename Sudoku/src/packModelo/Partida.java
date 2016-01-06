package packModelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Partida extends Observable{
	private int numAyudas, numComprobaciones;
	private Sudoku sudoku;
	private MatrizPartida matrizPartida;
	private Integer reto;
	
	public Partida(Sudoku pSud, Integer pReto, int pA, int pC) {
		reto = pReto;
		numAyudas = pA;
		numComprobaciones = pC;
		sudoku = pSud;
		matrizPartida = new MatrizPartida(pSud.toStringMatrizInicial());
	}

	public boolean estaPerfecto() {
		return this.sudoku.estaPerfecto();
	}
	
	public boolean esCorrecto() {
		return this.sudoku.esCorrecto();
	}
	
	public Integer getReto() {
		return this.reto;
	}
	
	public int getIdSud() {
		return this.sudoku.getId();
	}
	
	public int getDificultad() {
		return this.sudoku.getDificultad();
	}
	
	public void anadirBorrador(char pV, int pX, int pY) {
		this.matrizPartida.anadirBorrador(pV, pX, pY);
	}
	
	public void anadirNumero(char pV, int pX, int pY) {
		this.matrizPartida.anadirNumero(pV, pX, pY);
	}

	public char getValor(int pX, int pY) {
		return this.matrizPartida.getValor(pX, pY);
	}

	public char getValorSolucion(int pX, int pY) {
		return this.sudoku.getValorSolucion(pX, pY);
	}

	public boolean comprobarSiEstaBien(int pX, int pY) {
		return this.matrizPartida.getValor(pX, pY) == this.sudoku.getValorSolucion(pX, pY);
	}
	
	private void dcrAyudas() {
		this.numAyudas--;
		this.setChanged();
		this.notifyObservers();
	}

	private void dcrCompr() {
		this.numComprobaciones--;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addObserver(Observer pO, int pX, int pY){
		this.matrizPartida.addObserver(pO, pX, pY);
	}

	public int getNumAyudas() {
		return this.numAyudas;
	}
	
	public int getNumComprobaciones() {
		return this.numComprobaciones;
	}
	
	public void ayudar(){
		if(numAyudas>0){
			ArrayList<Point> listaPuntos = new ArrayList<Point>();
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(this.matrizPartida.getValor(i, j)=='0' || this.matrizPartida.getValor(i, j)!=this.sudoku.getValorSolucion(i, j)){
						listaPuntos.add(new Point(i,j));
					}
				}
			}
			Random rn = new Random();
			Point cas = listaPuntos.get(rn.nextInt(listaPuntos.size()));
			char valor = this.sudoku.getValorSolucion(cas.x, cas.y);
			this.matrizPartida.anadirNumero(valor, cas.x, cas.y);
			this.dcrAyudas();
		}
	}

	public boolean comprobar(int pCorX, int pCorY) {
		if(numComprobaciones>0){
			this.dcrCompr();
			char valMatriz = this.matrizPartida.getValor(pCorX, pCorY);
			char valSol = this.sudoku.getValorSolucion(pCorX, pCorY);
			return valMatriz == valSol;
		}
		else{
			return false;
		}
	}

	public boolean haTerminado() {
		String mPartida = this.matrizPartida.toStringCasillas();
		String mSol = this.sudoku.toStringMatrizSolucion();
		boolean result = mPartida.equals(mSol);
		return result;
	}

	public String toStringMatrizPartida() {
		return this.matrizPartida.toStringCasillas();
	}

	public void setMatrizPartida(String mtab) {
		this.matrizPartida.setValues(mtab);
	}
}
