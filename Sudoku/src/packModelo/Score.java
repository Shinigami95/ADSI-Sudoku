package packModelo;

public class Score {

	private int puntuacion;
	private int numFacil;
	private int numMedio;
	private int numDificil;

	public Score() {
		this.puntuacion = 0;
		this.numFacil = 0;
		this.numMedio = 0;
		this.numDificil = 0;
	}

	/**
	 * 
	 * @param pF
	 * @param pM
	 * @param pD
	 * @param pP
	 */
	public Score(int pP, int pF, int pM, int pD) {
		this.puntuacion = pP;
		this.numFacil = pF;
		this.numMedio = pM;
		this.numDificil = pD;
	}

	public String toString() {
		return "score: "+this.puntuacion+"\t -> \t nF: "+this.numFacil+"\t nM: "+this.numMedio+"\t nD: "+this.numDificil;
	}

	public String toStringParaFichero() {
		return this.puntuacion+" \\ "+this.numFacil+" \\ "+this.numMedio+" \\ "+this.numDificil;
	}

	public void icrNumFacil() {
		this.numFacil++;
		this.puntuacion+=50;
	}

	public void icrNumMedio() {
		this.numMedio++;
		this.puntuacion+=150;
	}

	public void icrNumDificil() {
		this.numDificil++;
		this.puntuacion+=400;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}
}