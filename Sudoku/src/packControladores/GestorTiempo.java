package packControladores;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class GestorTiempo extends Observable{
	private boolean pausado;
	private int tiempoSeg, tiempoMin, tiempoHor;
	private static GestorTiempo mGestor;
	private Timer timer;
	
	private GestorTiempo(){
		pausado = true;
		tiempoSeg = 0;
		tiempoMin = 0;
		tiempoHor = 0;
		TimerTask  timerTask = new TimerTask() {
			@Override
			public void run() {
				updateSeconds();				
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	public static GestorTiempo getGestor(){
		if(mGestor == null){
			mGestor = new GestorTiempo();
		}
		return mGestor;
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
	
	public void setTiempo(int pSeg){
		tiempoHor=pSeg/3600;
		tiempoMin=(pSeg%3600)/60;
		tiempoSeg=(pSeg%3600)%60;
		this.setChanged();
		this.notifyObservers();	}
	
	public void pausar() {
		this.pausado = true;
	}

	public void reanudar() {
		this.pausado = false;
	}
}
