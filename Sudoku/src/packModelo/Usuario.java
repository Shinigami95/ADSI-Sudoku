package packModelo;

public class Usuario{

	private String nombre;
	private Score puntos;
	private ListaIDSudokus sudokusHechos;

	/**
	 * 
	 * @param pNombre
	 */
	public Usuario(String pNombre) {
		this.nombre = pNombre;
		this.puntos = new Score();
		this.sudokusHechos = new ListaIDSudokus();
	}

	/**
	 * 
	 * @param pNombre
	 * @param pPunt
	 */
	public Usuario(String pNombre, Score pPunt) {
		this.nombre = pNombre;
		this.puntos = pPunt;
		this.sudokusHechos = new ListaIDSudokus();
	}
	
	public String getNombre(){
		return this.nombre;
	}

	/**
	 * 
	 * @param pNombre
	 */
	public boolean tieneElNombre(String pNombre) {
		return this.nombre.equals(pNombre);
	}

	public String toString() {
		return this.nombre+"\t -> "+this.puntos.toString();
	}

	public String toStringParaFichero() {
		return this.nombre+" \\ "+this.puntos.toStringParaFichero()+this.sudokusHechos.toStringParaFichero();
	}

	public void icrNumFacil() {
		this.puntos.icrNumFacil();
	}

	public void icrNumMedio() {
		this.puntos.icrNumMedio();
	}

	public void icrNumDificil() {
		this.puntos.icrNumDificil();
	}

	public int getPuntuacion() {
		return this.puntos.getPuntuacion();
	}

	public int compareTo(Usuario pU) {
		return this.getPuntuacion()-pU.getPuntuacion();
	}
	
	/**
	 * 
	 * @param pID
	 */
	public boolean haHechoSudoku(String pID) {
		return this.sudokusHechos.estaID(pID);
	}

	/**
	 * 
	 * @param pID
	 */
	public void addIDSudoku(String pID) {
		this.sudokusHechos.add(pID);
	}
}