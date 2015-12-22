package packControladores;

import java.util.Observer;

import packExcepciones.ExcepcionNoHaySudokuCargado;
import packModelo.Dificultad;
import packModelo.Partida;
import packModelo.Sudoku;

public class GestorPartida {

	private static GestorPartida mGestor;
	private Partida game;
	private boolean borradorActivo;

	private GestorPartida() {
		this.game = null;
		this.borradorActivo = false;
		this.cargarSudoku();
		System.out.println("NUEVO GESTORPARTIDA");
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
	
	public void quitarValor(int pX, int pY) {
		this.game.anadirNumero('0', pX, pY);
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
	
	public void addObserver(Observer pO, int pX, int pY){
		this.game.addObserver(pO, pX, pY);
	}
	
	public void switchBorrador(){
		this.borradorActivo = !this.borradorActivo;
	}
	
	public boolean estaActivoBorrador(){
		return this.borradorActivo;
	}

	public void ayudar(){
		this.game.ayudar();
	}

	private void cargarSudoku() {
		System.out.println("GestorPartida.cargarSudoku:");
		String id = "1111";
		String solSud = "792615384583742691164398527948263715275481963631957248857129436326874159419536872";
		String sinRes = "000000084500042600004000020040063700000001003630957200050009006320800109009500800";
		Sudoku sud = new Sudoku(id, solSud, sinRes);
		this.game = new Partida(sud, 0, 5, 5);
	}

	public void pausar() {
		this.game.pausar();
	}

	public void reanudar() {
		this.game.reanudar();
	}

	public String tiempoAString() {
		return this.game.tiempoAString();
	}

	public int getNumAyudas() {
		return this.game.getNumAyudas();
	}

	public boolean comprobar(int pCorX, int pCorY) {
		// TODO Auto-generated method stub
		return this.game.comprobar(pCorX,pCorY);
	}

	public int getNumCompr() {
		return this.game.getNumCompr();
	}
}
