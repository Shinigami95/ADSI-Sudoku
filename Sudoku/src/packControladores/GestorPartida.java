package packControladores;

import java.util.Observer;

import packModelo.Dificultad;
import packModelo.Partida;
import packModelo.Sudoku;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;

public class GestorPartida {

	private static GestorPartida mGestor;
	private Partida game;
	private Sudoku sudoku;
	private Dificultad nivel;
	private boolean borradorActivo;

	private GestorPartida() {
		this.sudoku = null;
		this.game = null;
		this.borradorActivo = false;
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
	
	public Dificultad getDificultad(){
		return this.nivel;
	}
	
	public void setValor(char pV, int pX, int pY) {
		this.sudoku.setValor(pV, pX, pY);
	}

	public void cargarSudParaUs(Dificultad pD, String pU) throws ExcepcionNoHaySudokuCargado{
		//TODO
	}
	
	public void anadirSudokuAUsuario(){
		//TODO
	}
	
	public char getValorCasillaSudoku(int pX, int pY){
		return this.sudoku.getValor(pX, pY);
	}
	
	public char getValorCasillaSudokuSolucion(int pX, int pY){
		return this.sudoku.getValorSolucion(pX, pY);
	}
	
	public void addObserver(Observer pO){
		this.game.addObserver(pO);
	}
	
	public void switchBorrador(){
		this.borradorActivo = !this.borradorActivo;
	}
	
	public boolean estaActivoBorrador(){
		return this.borradorActivo;
	}
}