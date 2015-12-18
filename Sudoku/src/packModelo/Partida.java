package packModelo;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Partida extends Observable{
	private int tiempoSeg, tiempoMin, tiempoHor;
	private Timer timer;
	
	
	public static void main(String[] args){
		new Partida(3755);
	}
	
	private Partida(int pT) {
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
}
