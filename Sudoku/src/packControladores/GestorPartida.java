package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observer;

import packExcepciones.ExcepcionConectarBD;
import packExcepciones.ExcepcionNoHaySudokuCargado;
import packModelo.Partida;
import packModelo.Sudoku;

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

	public int getSudokuId(){
		return this.game.getId();
	}
	
	public boolean getBorradorActivo(){
		return this.borradorActivo;
	}
	
	public void setValor(char pV, int pX, int pY) {
		if(this.getBorradorActivo()){
			this.game.anadirBorrador(pV, pX, pY);
		} else{
			this.game.anadirNumero(pV, pX, pY);
		}
	}
	
	public void quitarValor(int pX, int pY) {
		this.game.anadirNumero('0', pX, pY);
	}
	
	public void cargarSudokuMANUAL() {
		System.out.println("GestorPartida.cargarSudoku:");
		int id = 111;
		String solSud = "792615384583742691164398527948263715275481963631957248857129436326874159419536872";
		String sinRes = "000000084500042600004000020040063700000001003630957200050009006320800109009500800";
		Sudoku sud = new Sudoku(id, 1, solSud, sinRes);
		this.game = new Partida(sud, false, 0, 90, 5);
	}

	public void cargarSudParaUs(int pD, String pU) throws ExcepcionNoHaySudokuCargado, ExcepcionConectarBD{
		//TODO
		try{
			String sql = "SELECT ID_S, DIFICULTAD, M_INIC, M_SOL FROM SUDOKU "
						+ "WHERE ACTIVO='S' AND DIFICULTAD="+pD+" "
						+ "AND ID_S NOT IN (SELECT ID_SUDOKU FROM JUGADO WHERE NOMBRE_JUG='"+pU+"') ";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int idS, dif;
			String mI, mS;
			Sudoku sud;
			Partida part;
			if(result.next()){ // coge el primero, del resto pasa por ahora
				idS = result.getInt("ID_S");
				dif = result.getInt("DIFICULTAD");
				mI = result.getString("M_INIC");
				mS = result.getString("M_SOL");
				sud = new Sudoku(idS, dif, mS, mI);
			//TODO	part = new Partida(pSud, pEsReto, pT, pA, pC)
				
			}
			ConexionBD.getConexionBD().closeResult(result);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void cargarPartidaPendienteDelUsuario(String pU){
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
		return this.game.comprobar(pCorX,pCorY);
	}

	public int getNumCompr() {
		return this.game.getNumCompr();
	}
	
	public void guardarPartida() throws ExcepcionConectarBD{
		try{
			this.game.guardarPartida();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public boolean haTerminado() {
		return this.game.haTerminado();
	}
	
	//TODO elegir desde donde se llama a calcular puntuacion
	public int calcularPuntuacion(){
		return this.game.calcularPuntuacion();
	}
}
