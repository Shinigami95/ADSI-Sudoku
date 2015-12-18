package packModelo;

public class Borrador implements IFazCasilla{
	
	boolean[] lista;
	
	public Borrador(){
		lista = new boolean[9];
		for (int i = 0;i<lista.length;i++){
			lista[i]=false;
		}
	}
	
	@Override
	public void setValor(char pV) {
		if(pV>=1 && pV<=9){
			lista[pV-1] = !lista[pV-1];
		}
	}

	@Override
	public String toStringValores() {
		String result = "";
		for (int i = 0;i<lista.length;i++){
			if(lista[i]==false) result += " ";
			else result += Integer.toString(i-1);
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
}
