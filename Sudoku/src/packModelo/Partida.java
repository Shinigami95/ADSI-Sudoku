package packModelo;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class Partida extends Observable{
	private int tiempoSeg, tiempoMin, tiempoHor;
	private int numAyudas, numComprobaciones;
	private Timer timer;
	private Sudoku sudoku;
	private MatrizPartida matrizPartida;
	private boolean pausado;
	
	public Partida(Sudoku pSud, int pT, int pA, int pC) {
		pausado = false;
		numAyudas = pA;
		numComprobaciones = pC;
		matrizPartida = new MatrizPartida(pSud.toStringMatrizInicial());
		tiempoHor=pT/3600;
		tiempoMin=(pT%3600)/60;
		tiempoSeg=(pT%3600)%60;
		TimerTask  timerTask = new TimerTask() {
			@Override
			public void run() {
				updateSeconds();				
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	private void updateSeconds(){
		if(!this.pausado){	
			tiempoSeg++;
			if(tiempoSeg==60){
				tiempoMin++;
				tiempoSeg=0;
				if(tiempoMin==60){
					tiempoHor++;
					tiempoMin=0;
				}
			}
			this.setChanged();
			this.notifyObservers(tiempoHor+":"+tiempoMin+":"+tiempoSeg);
	
		}
	}
	
	private int tiempoASegundos(){
		return tiempoSeg + tiempoMin*60 + tiempoHor*3600;
	}

	public boolean estaPerfecto() {
		return this.sudoku.estaPerfecto();
	}
	
	public boolean esCorrecto() {
		return this.sudoku.esCorrecto();
	}
	
	public String getId() {
		return this.sudoku.getId();
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
	
	public void rellenarCasillaVacia(){
		int posX = 0;
		int posY = 0;
		boolean encontrada=false;
		for(int i=0;i<9 && !encontrada;i++){
			for(int j=0;j<9 && !encontrada;j++){
				if(this.sudoku.getValor(i, j)=='0'){
					posX=i;
					posY=j;
					encontrada=true;
				}
			}
		}
		char valor = this.sudoku.getValorSolucion(posX, posY);
		this.sudoku.setValor(valor, posX, posY);
	}
	
	public void addObserver(Observer pO, int pX, int pY){
		this.matrizPartida.addObserver(pO, pX, pY);
	}

	public void pausar() {
		// TODO Auto-generated method stub
		this.pausado = true;
	}

	public void reanudar() {
		// TODO Auto-generated method stub
		this.pausado = false;
	}
}
