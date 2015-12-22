package packModelo;

import java.util.Observer;

public class Casilla extends IFazCasilla{

	private char valor;
	private boolean inicial;

	public Casilla(char pV, boolean pInic) {
		super();
		this.valor = pV;
		this.inicial = pInic;
	}

	public boolean esInicial() {
		return this.inicial;
	}

	public char getValor() {
		return this.valor;
	}

	public void setValor(char pV) {
		if(!this.esInicial()){
			this.valor = pV;
			this.setChanged();
			this.notifyObservers(this.toStringValores());
System.out.println("Casilla.setValor: " + pV);
		}
	}

	@Override
	public String toStringValores() {
		return this.valor+"";
	}
	
	@Override
	public void addObserver(Observer pO){
		super.addObserver(pO);
		this.setChanged();
		this.notifyObservers(this.toStringValores());
	}
}