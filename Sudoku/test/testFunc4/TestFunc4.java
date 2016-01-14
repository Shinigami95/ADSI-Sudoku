package testFunc4;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packControladores.ConexionBD;
import packControladores.GestorJugadores;
import packControladores.GestorLogros;
import packExcepciones.ExcepcionConectarBD;

public class TestFunc4 {

	/* POSIBLES FALLOS
	 * asegurarse que los id de los logros referencian a un id sudoku que existe
	 * asegurarse que los id sudokus no tengan ya logros asignados
	 * asegurarse que el usuario PruebaLogros no tiene logros conseguidos
	 */
	
	@Before
	public void setUp() throws Exception {
		//nos aseguramos que no tiene logros
		borrarLogrosJugador("PruebaLogros");
		//nos aseguramos que existe jugador
		//registrarJugador comprueba si existe, para no add dos veces
		GestorJugadores.getGestor().registrarJugador("PruebaLogros", "a", "a", "a");
	}

	@After
	public void tearDown() throws Exception {
		//borramos todos los logros creados del catalogo
		if(GestorLogros.getGestor().estaLogro("RPRUE")){
			GestorLogros.getGestor().eliminar("RPRUE");
		}
		if(GestorLogros.getGestor().estaLogro("RPRU1")){
			GestorLogros.getGestor().eliminar("RPRU1");
		}
		if(GestorLogros.getGestor().estaLogro("RPRU2")){
			GestorLogros.getGestor().eliminar("RPRU2");
		}
		if(GestorLogros.getGestor().estaLogro("XPRU2")){
			GestorLogros.getGestor().eliminar("XPRU2");
		}
		if(GestorLogros.getGestor().estaLogro("YPRU1")){
			GestorLogros.getGestor().eliminar("YPRU1");
		}
		//borramos todos los logros del jugador
		borrarLogrosJugador("PruebaLogros");
	}

	@Test
	//id 401, 402 y 403
	public void testPremios() {
		try{
			//nos aseguramos que existan logros con esos ID
			if(!GestorLogros.getGestor().estaLogro("RPRU1")){
				//los 100 primeros
				GestorLogros.getGestor().anadirLogro("PRU1", "9", "prueba", "0", "100");
			}
			if(!GestorLogros.getGestor().estaLogro("XPRU2")){
				//hacer 100 ptos
				GestorLogros.getGestor().anadirLogro("PRU2", "9", "prueba", "100", "0");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//id 401: usuario logra un logro que no tiene
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==1);
		
		//id 402: usuario logra un logro que ya tiene
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==0);
		
		//id 403: usuario logra varios logros en el mismo sudoku
		//nos aseguramos que no tiene logros
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "1000").size()==2);	
	}
	
	@Test
	//id 404, 405, 406 y 407
	public void testPremiosXPtos() {
		try{
			if(!GestorLogros.getGestor().estaLogro("XPRU2")){
				//hacer 100 ptos
				GestorLogros.getGestor().anadirLogro("PRU2", "9", "prueba", "100", "0");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//id 404: usuario realiza mas de X puntos
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "101").size()==1);

		//id 407: usuario realiza mas de X puntos y ya tiene ese logro
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "101").size()==0);
		
		//id 405: usuario realiza X puntos
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "100").size()==1);
		
		//id 406: usuario realiza menos de X puntos
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==0);
	}
	
	@Test
	//id 408, 409, 410 y 411
	public void testPremiosXPtosZPrimeros() {
		try{
			if(!GestorLogros.getGestor().estaLogro("YPRU1")){
				//hacer 100 ptos entre los 1 primeros
				GestorLogros.getGestor().anadirLogro("PRU1", "7", "prueba", "100", "1");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	
		//id 411: usuario realiza menos de X puntos entre los Z primeros
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "7", "99").size()==0);
		
		//id 408: usuario realiza mas de X puntos entre los Z primeros
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "7", "101").size()==1);

		//id 409: usuario realiza mas de X puntos entre los Z primeros y ya tiene ese logro
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "7", "101").size()==0);
		
		//id 410: usuario realiza X puntos despues de los Z primeros
		//borramos logros jugador, como antes lo ha conseguido el contador del logro ha disminuido
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "7", "101").size()==0);		
	}
	
	@Test
	//id 412, 413, 414, 415 y 416
	public void testPremiosZPrimeros() {
		try{
			if(!GestorLogros.getGestor().estaLogro("RPRU2")){
				//los 100 primeros
				GestorLogros.getGestor().anadirLogro("PRU1", "9", "prueba", "0", "3");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	
		//id 412: usuario resuelve el primero
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==1);
		
		//id 415: usuario resuelve entre los Z primeros y ya tiene ese premio
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==0);
		
		//id 414: usuario resuelve entre los Z primeros (puesto 2)
		//borramos logros jugador, como antes lo ha conseguido el contador del logro ha disminuido
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==1);
		
		//id 413: usuario resuelve en el puesto Z (puesto 3)
		//borramos logros jugador, como antes lo ha conseguido el contador del logro ha disminuido
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==1);
		
		//id 416: usuario resuelve despues de los Z
		borrarLogrosJugador("PruebaLogros");
		assertTrue(GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "99").size()==0);
	}
	
	@Test
	//id 417 y 418
	public void testAdd() {
		int sizeAntiguo;
		try{
			//nos aseguramos que no exista un logro con ese ID
			if(GestorLogros.getGestor().estaLogro("RPRUE")){
				GestorLogros.getGestor().eliminar("RPRUE");
			}
			sizeAntiguo= sizeCatalogoLogros();
			//id 417: add nuevo logro tipo "entre los x primeros" (parametro puntos=0)
			GestorLogros.getGestor().anadirLogro("PRUE", "9", "prueba", "0", "100");
			//comprobamos que el size aumenta en uno
			assertTrue((sizeAntiguo+1)==sizeCatalogoLogros());
			//comprobamos que se encuentra en el catalogo
			assertTrue(GestorLogros.getGestor().estaLogro("RPRUE"));
			
			//id 418: add de nuevo
			GestorLogros.getGestor().anadirLogro("PRUE", "3", "prueba", "0", "100");
			//comprobamos que el size sigue como antes
			assertTrue((sizeAntiguo+1)==sizeCatalogoLogros());
		}catch(ExcepcionConectarBD e){
			System.out.println(e.getMessage());
		}
	}

	@Test
	//id 419
	public void testBorrar() {
		int sizeAntiguo;
		try{
			//nos aseguramos que exista un logro con ese ID
			if(!GestorLogros.getGestor().estaLogro("RPRUE")){
				GestorLogros.getGestor().anadirLogro("PRUE", "9", "prueba", "0", "100");
			}
			//nos aseguramos que un usuario logre ese logro
			GestorLogros.getGestor().logrosConseguidos("PruebaLogros", "9", "101").size();
			//id 419: comprobamos que se borra del catalogo
			sizeAntiguo= sizeCatalogoLogros();
			GestorLogros.getGestor().eliminar("RPRUE");
			assertTrue((sizeAntiguo-1)==sizeCatalogoLogros());
			//id 419: comprobamos que se borra del usuario
			assertTrue(GestorLogros.getGestor().verLogros("PruebaLogros").size()==0);
		}catch(ExcepcionConectarBD e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	//id 420
	public void testEditar() {
		ResultSet result;
		int numJug;
		try{
			//nos aseguramos que exista un logro con ese ID
			if(!GestorLogros.getGestor().estaLogro("RPRUE")){
				GestorLogros.getGestor().anadirLogro("PRUE", "9", "prueba", "0", "100");
			}
			//id 420: modificamos el valor numJug
			GestorLogros.getGestor().modificarLogros("RPRUE", "9", "prueba", "0", "50");
			//comprobamos que el valor ha sido modificado
			result=ConexionBD.getConexionBD().consultaBD("SELECT NUM_JUG FROM LOGRO_RES WHERE ID_L='RPRUE';");
			numJug=0;
			if(result.next()){
				numJug=result.getInt(1);
			}
			result.close();
			assertTrue(50==numJug);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}	
	
	//POST: devuelve el total de logros en el sistema
	private int sizeCatalogoLogros(){
		int size=0;
		String sql="SELECT COUNT(ID_SUDOKU) FROM LOGRO;";
		try{
			ResultSet result= ConexionBD.getConexionBD().consultaBD(sql);
			if(result.next()){
				size= result.getInt(1);
			}
			result.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return size;
	}
	
	//POST: borra todos los logras de pUsuario
	private void borrarLogrosJugador(String pUsuario){
		String sql="DELETE FROM TIENE WHERE NOMBRE_JUG='"+pUsuario+"';";
		try{
			ConexionBD.getConexionBD().actualizarBD(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
