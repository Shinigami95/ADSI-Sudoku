package packControladores;

import java.util.Observer;

import packModelo.CatalogoSudokus;
import packModelo.CatalogoUsuarios;
import packModelo.Dificultad;
import packModelo.Sudoku;
import packModelo.Usuario;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;

public class GestorPartida {

	private static GestorPartida mGestor;
	private Usuario jugador;
	private Sudoku sudoku;
	private Dificultad nivel;

	private GestorPartida() {
		this.jugador = null;
		this.sudoku = null;
	}

	public static GestorPartida getGestor() {
		if(mGestor==null){
			mGestor = new GestorPartida();
		}
		return mGestor;
	}

	public boolean estaPerfectoSudoku(){
		return this.sudoku.estaPerfecto();
	}
	
	public boolean esCorrectoSudoku() {
		return this.sudoku.esCorrecto();
	}

	public String getSudokuId(){
		return this.sudoku.getId();
	}
	
	public String getNombreUsuario(){
		return this.jugador.getNombre();
	}
	
	public Dificultad getDificultad(){
		return this.nivel;
	}
	
	/**
	 * 
	 * @param pV
	 * @param pX
	 * @param pY
	 */
	public void setValor(char pV, int pX, int pY) {
		this.sudoku.setValor(pV, pX, pY);
	}

	/**
	 * 
	 * @param pD
	 * @param pU
	 */
	public void cargarSudParaUs(Dificultad pD, String pU) throws ExcepcionNoHaySudokuCargado{
		Usuario userSelected = CatalogoUsuarios.getCatalogo().obtenerUsuario(pU);
		Sudoku sud = CatalogoSudokus.getCatalogo().obtenerSudokuParaUsuario(pD, userSelected);
		this.sudoku = sud.clone();
		this.nivel = pD;
		this.jugador = userSelected;
	}
	
	public void anadirSudokuAUsuario(){
		this.jugador.addIDSudoku(this.sudoku.getId());
		if(this.nivel==Dificultad.FACIL) this.jugador.icrNumFacil();
		else if (this.nivel==Dificultad.MEDIO) this.jugador.icrNumMedio();
		else if (this.nivel==Dificultad.DIFICIL) this.jugador.icrNumDificil();
	}
	
	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public char getValorCasillaSudoku(int pX, int pY){
		return this.sudoku.getValor(pX, pY);
	}
	
	/**
	 * 
	 * @param pX
	 * @param pY
	 */
	public char getValorCasillaSudokuSolucion(int pX, int pY){
		return this.sudoku.getValorSolucion(pX, pY);
	}
	
	/**
	 * 
	 * @param pO
	 */
	public void addObserver(Observer pO){
		this.sudoku.addObserver(pO);
	}
	
	public String toString(){
		String s = "Nombre usuario: " + this.getNombreUsuario() + "\n" + "Sudoku: " + this.sudoku.getId();
		for(int i=0; i<9;i++){
			s += "\n";
			for(int j=0; j<9; j++){
				s += this.getValorCasillaSudoku(i, j);
			}
			s +="\t";
			for(int j=0; j<9; j++){
				s += this.getValorCasillaSudokuSolucion(i, j);
			}
		}
		return s;
	}
}