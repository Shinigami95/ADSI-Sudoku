package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observer;

import packExcepciones.ExcepcionConectarBD;
import packExcepciones.ExcepcionNoHaySudokuCargado;
import packExcepciones.NoValidoException;
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

	public int getIdSud(){
		return this.game.getIdSud();
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
		int id = 111;
		String solSud = "792615384583742691164398527948263715275481963631957248857129436326874159419536872";
		String sinRes = "000000084500042600004000020040063700000001003630957200050009006320800109009500800";
		Sudoku sud;
		try {
			sud = new Sudoku(id, 1, solSud, sinRes, true);
			this.game = new Partida(sud, false, 90, 5);
			GestorTiempo.getGestor().setTiempo(0);
			GestorTiempo.getGestor().reanudar();
		} catch (NoValidoException e) {
			e.printStackTrace();
		}
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
				try {
					sud = new Sudoku(idS, dif, mS, mI, true);
				} catch (NoValidoException e) {
					e.printStackTrace();
				}
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

	public int getNumAyudas() {
		return this.game.getNumAyudas();
	}

	public boolean comprobar(int pCorX, int pCorY) {
		return this.game.comprobar(pCorX,pCorY);
	}

	public int getNumComprobaciones() {
		return this.game.getNumComprobaciones();
	}

	public boolean haTerminado() {
		return this.game.haTerminado();
	}
	
	//falta revisarlo
	//hay que modificar la BD para lo de RETO
	public void guardarPartida() throws ExcepcionConectarBD{
		String jugador = GestorSesion.getGestor().getUserSesion();
		try{
			ResultSet result=ConexionBD.getConexionBD().consultaBD("SELECT NOMBRE_JUG FROM PARTIDA WHERE NOMBRE_JUG='"+jugador+"';");
			int reto=0;
			if(this.game.getEsReto()){
				reto=1;
			}
			if(!result.next()){
				ConexionBD.getConexionBD().actualizarBD("INSERT INTO PARTIDA VALUES('"+jugador+"',"+this.game.getIdSud()+",'"+this.game.toStringMatrizPartida()+"',"+this.game.getNumAyudas()+","+this.game.getNumComprobaciones()+","+GestorTiempo.getGestor().tiempoASegundos()+","+reto+");");
			}
			else{
				ConexionBD.getConexionBD().actualizarBD("UPDATE PARTIDA SET ID_SUDOKU="+this.game.getIdSud()+", MATRIZ_TABLERO='"+this.game.toStringMatrizPartida()+"', NUM_AYUDAS="+this.game.getNumAyudas()+", NUM_COMPR="+this.game.getNumComprobaciones()+", TIEMPO="+GestorTiempo.getGestor().tiempoASegundos()+", RETO="+reto+" WHERE NOMBRE_JUG='"+jugador+"';");
			}
			ConexionBD.getConexionBD().closeResult(result);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	//TODO definir mejor la formula
	public int calcularPuntuacion(){
		int tiempo = GestorTiempo.getGestor().tiempoASegundos();
		int dificultad = this.game.getDificultad();
		int pistasAyudas= (5-this.game.getNumAyudas())+(5-this.game.getNumComprobaciones());
		double puntuacion = 9999*Math.exp(-(getPorcentajeDificultad()*tiempo))-pistasAyudas*getPorcentajePenalizacion(dificultad);
		return (int)puntuacion;
	}
	
	//0,075 facil, 0,050 medio, 0,025 dificil
	private double getPorcentajeDificultad(){
		return 0.1-this.game.getDificultad()*0.025;
	}
	
	//100 facil, 50 medio, 25 dificil
	private int getPorcentajePenalizacion(int pDificultad){
		int penalizacion=100;
		if(pDificultad==2){
			penalizacion=50;
		}
		else if(pDificultad==3){
			penalizacion=25;
		}
		return penalizacion;
	}
}
