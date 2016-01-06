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

	private void setPartida(Partida pGame){
		this.game = pGame;
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
	
	public Integer getReto(){
		return this.game.getReto();
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
	
	public void cargarSudokuMANUAL() {
		int id = 111;
		String solSud = "792615384583742691164398527948263715275481963631957248857129436326874159419536872";
		String sinRes = "000000084500042600004000020040063700000001003630957200050009006320800109009500800";
		Sudoku sud;
		try {
			sud = new Sudoku(id, 1, solSud, sinRes, true);
			this.game = new Partida(sud, null, 90, 5);
			GestorTiempo.getGestor().setTiempo(0);
			GestorTiempo.getGestor().reanudar();
		} catch (NoValidoException e) {
			e.printStackTrace();
		}
	}

	public void cargarSudParaUsSesion(int pD) throws ExcepcionNoHaySudokuCargado, ExcepcionConectarBD{
		try{
			String user = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT ID_S, DIFICULTAD, M_INIC, M_SOL FROM SUDOKU "
						+ "WHERE ACTIVO='S' AND DIFICULTAD="+pD+" "
						+ "AND ID_S NOT IN ("
						+ "SELECT ID_SUDOKU FROM JUGADO WHERE NOMBRE_JUG='"+user+"' "
						+ "UNION "
						+ "SELECT ID_SUDOKU FROM RETO WHERE NOMBRE_RETADO='"+user+"' AND ESTADO<>'R')";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int idS, dif;
			String mI, mS;
			if(result.next()){
				idS = result.getInt("ID_S");
				dif = result.getInt("DIFICULTAD");
				mI = result.getString("M_INIC");
				mS = result.getString("M_SOL");
				try {
					Sudoku sud = new Sudoku(idS, dif, mS, mI, true);
					this.setPartida(new Partida(sud, null, 81, 5)); //TODO poner ayudas a 5
					GestorTiempo.getGestor().reanudar();		
				} catch (NoValidoException e) {
					e.printStackTrace();
				}
				
			} else {
				throw new ExcepcionNoHaySudokuCargado();
			}
			ConexionBD.getConexionBD().closeResult(result);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public boolean tienePartidaPendienteUserSesion() throws ExcepcionConectarBD{
		try{
			String user = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT * FROM PARTIDA WHERE NOMBRE_JUG='"+user+"'";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			boolean hayPartida = result.next();
			ConexionBD.getConexionBD().closeResult(result);
			return hayPartida;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public void cargarPartidaPendienteParaUsSesion() throws ExcepcionNoHaySudokuCargado, ExcepcionConectarBD{
		try{
			String user = GestorSesion.getGestor().getUserSesion();
			
			String sql = "SELECT PARTIDA.ID_SUDOKU AS IDS, PARTIDA.MATRIZ_TABLERO AS MTAB, PARTIDA.NUM_AYUDAS AS NA, "
						+ "PARTIDA.NUM_COMPR AS NC, PARTIDA.TIEMPO AS TS, PARTIDA.RETO AS RT, "
						+ "SUDOKU.DIFICULTAD AS DIF, SUDOKU.M_INIC AS MI, SUDOKU.M_SOL AS MS "
						+ "FROM PARTIDA INNER JOIN SUDOKU ON PARTIDA.ID_SUDOKU=SUDOKU.ID_S "
						+ "WHERE PARTIDA.NOMBRE_JUG='"+user+"'";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			if(result.next()){
				int ids = result.getInt("IDS");
				String mtab = result.getString("MTAB");
				int na = result.getInt("NA");
				int nc = result.getInt("NC");
				int ts = result.getInt("TS");
				Integer rt = result.getInt("RT");
				if(rt==0)rt=null;
				int dif = result.getInt("DIF");
				String mi = result.getString("MI");
				String ms = result.getString("MS");
				ConexionBD.getConexionBD().closeResult(result);
				Sudoku sud = new Sudoku(ids, dif, ms, mi, true);
				Partida part = new Partida(sud, rt, na, nc);
				part.setMatrizPartida(mtab);
				this.game = part;
				GestorTiempo.getGestor().setTiempo(ts);
				GestorTiempo.getGestor().reanudar();
			} else {
				throw new ExcepcionNoHaySudokuCargado();
			}
		} catch (SQLException e){
			System.out.println(e.getMessage());
		} catch (NoValidoException e) {
			e.printStackTrace();
		}
	}
	
	public void anadirSudokuJugadoAUsuarioSesion(){
		//TODO
	}
	
	//falta revisarlo
	//hay que modificar la BD para lo de RETO
	public void guardarPartida() throws ExcepcionConectarBD{
		String jugador = GestorSesion.getGestor().getUserSesion();
		try{
			String sql = "SELECT NOMBRE_JUG FROM PARTIDA WHERE NOMBRE_JUG='"+jugador+"';";
			ResultSet result=ConexionBD.getConexionBD().consultaBD(sql);
			Integer reto = this.game.getReto();
			int idSud = this.game.getIdSud();
			String mPartida = this.game.toStringMatrizPartida();
			int numAyudas = this.game.getNumAyudas();
			int numCompr = this.game.getNumComprobaciones();
			int seg = GestorTiempo.getGestor().tiempoASegundos();
			if(reto == null){
				if(!result.next()){
					sql = "INSERT INTO PARTIDA(NOMBRE_JUG, ID_SUDOKU, MATRIZ_TABLERO, NUM_AYUDAS, NUM_COMPR, TIEMPO, RETO) "
						+ "VALUES('"+jugador+"',"+idSud+",'"+mPartida+"',"+numAyudas+","+numCompr+","+seg+",NULL);";
					ConexionBD.getConexionBD().actualizarBD(sql);
				}
				else{
					sql = "UPDATE PARTIDA SET ID_SUDOKU="+idSud+", MATRIZ_TABLERO='"+mPartida+"',"
						+ " NUM_AYUDAS="+numAyudas+", NUM_COMPR="+numCompr+", TIEMPO="+seg+", RETO=NULL"
						+ " WHERE NOMBRE_JUG='"+jugador+"';";
					ConexionBD.getConexionBD().actualizarBD(sql);
				}
			} else {
				if(!result.next()){
					sql = "INSERT INTO PARTIDA(NOMBRE_JUG, ID_SUDOKU, MATRIZ_TABLERO, NUM_AYUDAS, NUM_COMPR, TIEMPO, RETO) "
						+ "VALUES('"+jugador+"',"+idSud+",'"+mPartida+"',"+numAyudas+","+numCompr+","+seg+","+reto+");";
					ConexionBD.getConexionBD().actualizarBD(sql);
				}
				else{
					sql = "UPDATE PARTIDA SET ID_SUDOKU="+idSud+", MATRIZ_TABLERO='"+mPartida+"',"
						+ " NUM_AYUDAS="+numAyudas+", NUM_COMPR="+numCompr+", TIEMPO="+seg+", RETO="+reto
						+ " WHERE NOMBRE_JUG='"+jugador+"';";
					ConexionBD.getConexionBD().actualizarBD(sql);
				}
			}
			ConexionBD.getConexionBD().closeResult(result);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	//POST: Puntuacion = 1000 + [8999*exp(-%Dificultad * Tiempo)] - Penalizacion*Ayudas
	public int calcularPuntuacion(){
		int tiempo = GestorTiempo.getGestor().tiempoASegundos();
		int dificultad = this.game.getDificultad();
		//int pistasAyudas= (5-this.game.getNumAyudas())+(5-this.game.getNumComprobaciones());
		int pistasAyudas= 0;
		double puntuacion = 1000+8999*Math.exp(-(getPorcentajeDificultad(dificultad)*tiempo))-pistasAyudas*getPorcentajePenalizacion(dificultad);
		return (int)puntuacion;
	}
	
	//0,0018 facil, 0,0009 medio, 0,0005 dificil
	private double getPorcentajeDificultad(int pDificultad){
		double porcentaje=0.0018;
		if(pDificultad==2){
			porcentaje=0.0009;
		}
		else if(pDificultad==3){
			porcentaje=0.0005;
		}
		return porcentaje;
	}
	
	//100 facil, 75 medio, 50 dificil
	private int getPorcentajePenalizacion(int pDificultad){
		int penalizacion=100;
		if(pDificultad==2){
			penalizacion=75;
		}
		else if(pDificultad==3){
			penalizacion=50;
		}
		return penalizacion;
	}

	public void borrarPartidaUsuarioSesion() {
		try {
			String jugador = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT ID_SUDOKU, RETO FROM PARTIDA WHERE NOMBRE_JUG='"+jugador+"';";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			if(result.next()){
				String ids = result.getInt("ID_SUDOKU")+"";
				Integer reto = result.getInt("RETO");
				ConexionBD.getConexionBD().closeResult(result);
				if(reto != 0){
					sql = "UPDATE RETO SET ESTADO='T' WHERE ID_R="+reto+";";
					ConexionBD.getConexionBD().actualizarBD(sql);
				}
				sql = "DELETE FROM PARTIDA WHERE NOMBRE_JUG='"+jugador+"';";
				ConexionBD.getConexionBD().actualizarBD(sql);
				sql = "INSERT INTO JUGADO (NOMBRE_JUG, ID_SUDOKU, COMPLETADO, PTO, SEGUNDOS)"
					+ " VALUES ('"+jugador+"', "+ids+", 'N', 0, 0);";
				ConexionBD.getConexionBD().actualizarBD(sql);
			} else {
				ConexionBD.getConexionBD().closeResult(result);
			}
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarPartidaCompletadaUsuarioSesion() {
		try {
			String jugador= GestorSesion.getGestor().getUserSesion();
			int ids = this.getIdSud();
			int pto = this.calcularPuntuacion();
			GestorJugadores.getGestor().actualizarPuntuacion(pto, jugador);
			int tiempo = GestorTiempo.getGestor().tiempoASegundos();
			String sql = "DELETE FROM PARTIDA WHERE NOMBRE_JUG='"+jugador+"';";
			ConexionBD.getConexionBD().actualizarBD(sql);
			sql = "INSERT INTO JUGADO (NOMBRE_JUG, ID_SUDOKU, COMPLETADO, PTO, SEGUNDOS)"
				+ " VALUES ('"+jugador+"', "+ids+", 'S', "+pto+", "+tiempo+");";
			Integer reto = this.getReto();
			if(reto!=null){
				sql = "UPDATE RETO SET ESTADO = 'T' WHERE ID_R="+reto+";";
				ConexionBD.getConexionBD().actualizarBD(sql);
			}
			ConexionBD.getConexionBD().actualizarBD(sql);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	public void cargarRetoParaUsSesion(int pD, int pIdReto) throws ExcepcionNoHaySudokuCargado, ExcepcionConectarBD{
		try{
			String user = GestorSesion.getGestor().getUserSesion();
			String sql = "SELECT ID_S, DIFICULTAD, M_INIC, M_SOL FROM SUDOKU "
						+ "WHERE ACTIVO='S' AND DIFICULTAD="+pD+" "
						+ "AND ID_S NOT IN ("
						+ "SELECT ID_SUDOKU FROM JUGADO WHERE NOMBRE_JUG='"+user+"' "
						+ "UNION "
						+ "SELECT ID_SUDOKU FROM RETO WHERE NOMBRE_RETADO='"+user+"' AND ESTADO<>'R')";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			int idS, dif;
			String mI, mS;
			if(result.next()){
				idS = result.getInt("ID_S");
				dif = result.getInt("DIFICULTAD");
				mI = result.getString("M_INIC");
				mS = result.getString("M_SOL");
				try {
					Sudoku sud = new Sudoku(idS, dif, mS, mI, true);
					this.setPartida(new Partida(sud, pIdReto, 5, 5));
					GestorTiempo.getGestor().reanudar();		
				} catch (NoValidoException e) {
					e.printStackTrace();
				}
				
			} else {
				throw new ExcepcionNoHaySudokuCargado();
			}
			ConexionBD.getConexionBD().closeResult(result);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
