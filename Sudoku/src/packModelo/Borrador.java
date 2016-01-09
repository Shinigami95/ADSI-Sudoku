package packModelo;

//import java.util.Observer;

public class Borrador extends IFazCasilla{
	
	//Lista de 9 booleanos, si es true indica que 
	//el valor de esa posicion +1 esta escrita en el borrador
	boolean[] lista;
	
	public Borrador(){
		super();
		lista = new boolean[9];
		for (int i = 0;i<lista.length;i++){
			lista[i]=false;
		}
	}
	
	//POST: Invierte el valor del borrador
	//		en la posicion indicada
	@Override
	public void setValor(char pV) {
		int i = Integer.parseInt(pV+"");
		if(i>=1 && i<=9){
			lista[i-1] = !lista[i-1];
			this.setChanged();
			this.notifyObservers(this.toStringValores());
		}
	}

	//POST: Devuelve los valores del borrador en un string,
	//		los numeros si estan, espacios en blanco si no estan
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

	//Ningun borrador puede ser inicial
	@Override
	public boolean esInicial() {
		return false;
	}
	
}
