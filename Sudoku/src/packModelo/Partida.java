package packModelo;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Partida extends Observable{
	private int tiempoSeg, tiempoMin, tiempoHor;
	private Timer timer;
	private Sudoku sudoku;
	private MatrizPartida matrizPartida;
	
	private Partida(String pSud, int pT) {
		matrizPartida = new MatrizPartida(pSud);
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
}
