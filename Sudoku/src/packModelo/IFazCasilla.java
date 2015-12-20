package packModelo;

import java.util.Observable;
import java.util.Observer;

public abstract class IFazCasilla extends Observable{
	
	private Observer observ;
	
	public abstract void setValor(char pV);
	public abstract char getValor();
	public abstract boolean esInicial();
	public abstract String toStringValores();
	
	@Override
	public void addObserver(Observer pO){
		super.deleteObservers();
		super.addObserver(pO);
		this.observ = pO;
		//this.setChanged();
		//this.notifyObservers(this.toStringValores());
	}
	
	public Observer getObserver(){
		return this.observ;
	}
}
