package packControladores;

import java.util.Observer;

import packModelo.Dificultad;
import packModelo.Partida;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;

public class GestorPartida {

	private static GestorPartida mGestor;
	private Partida game;
	private boolean borradorActivo;

	private GestorPartida() {
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
		return this.game.estaPerfecto();
	}
	
	public boolean esCorrectoSudoku() {
		return this.game.esCorrecto();
	}

	public String getSudokuId(){
		return this.game.getId();
	}
	
	public void setValor(char pV, int pX, int pY) {
		if(borradorActivo){
			this.game.anadirBorrador(pV, pX, pY);
		} else{
			this.game.anadirNumero(pV, pX, pY);
		}
	}

	public void cargarSudParaUs(Dificultad pD, String pU) throws ExcepcionNoHaySudokuCargado{
		//TODO
	}
	
	public void anadirSudokuAUsuario(){
		//TODO
	}
	
	public char getValorCasillaSudoku(int pX, int pY){
		return this.game.getValor(pX, pY);
	}
	
	public char getValorCasillaSudokuSolucion(int pX, int pY){
		return this.game.getValorSolucion(pX, pY);
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