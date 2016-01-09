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

	public int getIdSud(){
		return this.game.getIdSud();
	}
	
	public Integer getReto(){
		return this.game.getReto();
	}
	
	public boolean getBorradorActivo(){
		return this.borradorActivo;
	}
	
	//POST: Si el borrador estaba activo se quita o se pone el valor indicado en la posicion indicada.
	//		Si el borrador no esta activo se pone el valor tal cual en la casilla indicada.
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
	
	//Get valor de la casilla de matriz partida 
	//(en la que se juega)
	public char getValorCasillaSudoku(int pX, int pY){
		return this.game.getValor(pX, pY);
	}
	
	//Obtener el valor de la solucion de una casilla
	public char getValorCasillaSudokuSolucion(int pX, int pY){
		return this.game.getValorSolucion(pX, pY);
	}
	
	//Para observar el numero de ayudas y comprobaciones
	public void addObserver(Observer pO){
		this.game.addObserver(pO);
	}
	
	//Para observar una casilla en particular
	public void addObserver(Observer pO, int pX, int pY){
		this.game.addObserver(pO, pX, pY);
	}
	
	//POST: activa/desactiva el borrador
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

	//POST: devuelve true si el usuario ha completado el sudoku.
	public boolean haTerminado() {
		return this.game.haTerminado();
	}

	//POST: carga en GestorPartida un nuevo sudoku para el usuario de la sesion.
	//		Este sudoku sera de la dificultad escogida y no sera un reto pendiente ni estara jugado.
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
					this.setPartida(new Partida(sud, null, 5, 5));
					GestorTiempo.getGestor().setTiempo(0);
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
	
	//POST: Devuelve true si el usuario de la sesion tiene una partida pendiente, false en caso contrario
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
	
	//POST: Carga en el gestor la partida pendiente del usuario de la sesion, si no habia partida se lanza excepcion.
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
	
	//PRE: jugador abandona sudoku o cierra ventana
	//POST: guarda partida, si partida no es reto RETO=NULL si lo es almacena su ID
	public void guardarPartida() throws ExcepcionConectarBD{
		String jugador = GestorSesion.getGestor().getUserSesion();
		String mPartida = this.game.toStringMatrizPartida();
		Integer reto = this.game.getReto();
		int idSud = this.game.getIdSud();
		int numAyudas = this.game.getNumAyudas();
		int numCompr = this.game.getNumComprobaciones();
		int seg = GestorTiempo.getGestor().tiempoASegundos();
		try{
			//comprobamos si tiene ya una partida pendiente
			String sql = "SELECT NOMBRE_JUG FROM PARTIDA WHERE NOMBRE_JUG='"+jugador+"';";
			ResultSet result=ConexionBD.getConexionBD().consultaBD(sql);
			//si no es reto guardamos null
			if(reto == null){
				//si no tiene partida insert into
				if(!result.next()){
					sql = "INSERT INTO PARTIDA(NOMBRE_JUG, ID_SUDOKU, MATRIZ_TABLERO, NUM_AYUDAS, NUM_COMPR, TIEMPO, RETO) "
						+ "VALUES('"+jugador+"',"+idSud+",'"+mPartida+"',"+numAyudas+","+numCompr+","+seg+",NULL);";
				}
				//si ya tiene partida update
				else{
					sql = "UPDATE PARTIDA SET ID_SUDOKU="+idSud+", MATRIZ_TABLERO='"+mPartida+"',"
						+ " NUM_AYUDAS="+numAyudas+", NUM_COMPR="+numCompr+", TIEMPO="+seg+", RETO=NULL"
						+ " WHERE NOMBRE_JUG='"+jugador+"';";
				}
			}
			//si es reto guardamos el id del reto
			else {
				//si no tiene partida insert into
				if(!result.next()){
					sql = "INSERT INTO PARTIDA(NOMBRE_JUG, ID_SUDOKU, MATRIZ_TABLERO, NUM_AYUDAS, NUM_COMPR, TIEMPO, RETO) "
						+ "VALUES('"+jugador+"',"+idSud+",'"+mPartida+"',"+numAyudas+","+numCompr+","+seg+","+reto+");";
				}
				//si ya tiene partida update
				else{
					sql = "UPDATE PARTIDA SET ID_SUDOKU="+idSud+", MATRIZ_TABLERO='"+mPartida+"',"
						+ " NUM_AYUDAS="+numAyudas+", NUM_COMPR="+numCompr+", TIEMPO="+seg+", RETO="+reto
						+ " WHERE NOMBRE_JUG='"+jugador+"';";
				}
			}
			ConexionBD.getConexionBD().actualizarBD(sql);
			ConexionBD.getConexionBD().closeResult(result);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	//PRE: jugador resuelve correctamente el sudoku
	//POST: Puntuacion = 1000 + [8999*exp(-%Dificultad * Tiempo)] - Penalizacion*Ayudas
	public int calcularPuntuacion(){
		int tiempo = GestorTiempo.getGestor().tiempoASegundos();
		int dificultad = this.game.getDificultad();
		int pistasAyudas= (5-this.game.getNumAyudas())+(5-this.game.getNumComprobaciones());
		double puntuacion = 1000+8999*Math.exp(-(getPorcentajeDificultad(dificultad)*tiempo))-pistasAyudas*getPorcentajePenalizacion(dificultad);
		//redondeo primer decimal, 1.5= 2
		return 	(int)Math.round(puntuacion);
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
	
	//POST: Se ha borrado la partida del usuario de la sesion y se guarda en jugado como
	//		no completado, si era reto se indica que se ha terminado.
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
	
	//POST: se actualizara la BD con la informacion de la partida completada.
	//		se introduce la informacion en la tabla jugado y si era reto se indica que esta terminado.
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
			ConexionBD.getConexionBD().actualizarBD(sql);
			Integer reto = this.getReto();
			if(reto!=null){
				sql = "UPDATE RETO SET ESTADO = 'T' WHERE ID_R="+reto+";";
				ConexionBD.getConexionBD().actualizarBD(sql);
			}
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	//POST: Se carga al gestor partida el sudoku del reto indicado. 
	public void cargarRetoParaUsSesion(int pIdReto) throws ExcepcionConectarBD{
		try{
			String sql = "SELECT ID_S, DIFICULTAD, M_INIC, M_SOL "
					+ "FROM RETO INNER JOIN SUDOKU ON SUDOKU.ID_S=RETO.ID_SUDOKU "
					+ "WHERE ID_R ="+pIdReto+";";
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
					GestorTiempo.getGestor().setTiempo(0);
					GestorTiempo.getGestor().reanudar();		
				} catch (NoValidoException e) {
					e.printStackTrace();
				}
			}
			ConexionBD.getConexionBD().closeResult(result);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
