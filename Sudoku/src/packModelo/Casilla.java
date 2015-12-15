package packModelo;

public class Casilla {

	private char valor;
	private boolean inicial;

	/**
	 * 
	 * @param pV
	 * @param pInic
	 */
	public Casilla(char pV, boolean pInic) {
		this.valor = pV;
		this.inicial = pInic;
	}

	public boolean esInicial() {
		return this.inicial;
	}

	public char getValor() {
		return this.valor;
	}

	/**
	 * 
	 * @param pV
	 */
	public void setValor(char pV) {
		if(!this.esInicial()){
			this.valor = pV;
		}
	}

}