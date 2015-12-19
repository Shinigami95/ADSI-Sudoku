package packModelo;

import java.util.Observable;
import java.util.Observer;

public abstract class IFazCasilla extends Observable{
	
	public abstract void setValor(char pV);
	public abstract char getValor();
	public abstract boolean esInicial();
	public abstract String toStringValores();
	
	@Override
	public void addObserver(Observer pO){
		super.addObserver(pO);
		//this.setChanged();
		//this.notifyObservers(this.toStringValores());
	}
}
