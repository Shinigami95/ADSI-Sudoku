package packModelo;

import java.util.Observer;

public class Borrador extends IFazCasilla{
	
	boolean[] lista;
	
	public Borrador(){
		super();
		lista = new boolean[9];
		for (int i = 0;i<lista.length;i++){
			lista[i]=false;
		}
	}
	
	@Override
	public void setValor(char pV) {
		int i = Integer.parseInt(pV+"");
		if(i>=1 && i<=9){
			lista[i-1] = !lista[i-1];
			this.setChanged();
			this.notifyObservers(this.toStringValores());
		}
	}

	@Override
	public String toStringValores() {
		String result = "";
		for (int i = 0;i<lista.length;i++){
			if(lista[i]==false) result = result+" ";
			else result = result+(i+1);
		}
		return result;
	}

	@Override
	public char getValor() {
		return '0';
	}

	@Override
	public boolean esInicial() {
		return false;
	}
	
	@Override
	public void addObserver(Observer pO){
		super.addObserver(pO);
	}
}
