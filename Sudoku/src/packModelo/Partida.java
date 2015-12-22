package packModelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
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
		sudoku = pSud;
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
			this.notifyObservers();
		}
	}
	
	public String tiempoAString(){
		String ts =Integer.toString(tiempoSeg);
		String tm =Integer.toString(tiempoMin);
		String th =Integer.toString(tiempoHor);
		if (tiempoSeg<10) ts = "0"+ts;
		if (tiempoMin<10) tm = "0"+tm;
		if (tiempoHor<10) th = "0"+th;
		
		return th +":"+ tm +":"+ ts;
	}
	
	public int tiempoASegundos(){
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

	public void pausar() {
		// TODO Auto-generated method stub
		this.pausado = true;
	}

	public void reanudar() {
		// TODO Auto-generated method stub
		this.pausado = false;
	}

	public int getNumAyudas() {
		return this.numAyudas;
	}
	
	public void ayudar(){
		if(numAyudas>0){
			ArrayList<Point> listaPuntos = new ArrayList<Point>();
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(this.matrizPartida.getValor(i, j)=='0'){
						listaPuntos.add(new Point(i,j));
						System.out.print("("+i+","+j+")");
					}
				}
			}
			Random rn = new Random();
			Point cas = listaPuntos.get(rn.nextInt(listaPuntos.size()));
			char valor = this.sudoku.getValorSolucion(cas.x, cas.y);
			System.out.println("\n("+cas.x+","+cas.y+") ->"+valor);
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

	public int getNumCompr() {
		return this.numComprobaciones;
	}
}
