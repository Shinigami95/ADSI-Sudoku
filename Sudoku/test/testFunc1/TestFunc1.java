package testFunc1;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packControladores.ConexionBD;
import packControladores.GestorJugadores;
import packExcepciones.ExcepcionConectarBD;

public class TestFunc1 {

	String sql;
	String ident;
	String name1, pass1, preg1, resp1;
	String name2, pass2, preg2, resp2;
	String nameAdmin, passAdmin;
	int pto;
	
	@Before
	public void setUp() throws Exception {
		pto = 0;
		sql = "";
		ident = "";
		name1 = "JugPrueba1";
		pass1 = "contra";
		preg1 = "El perro mas guay?";
		resp1 = "Snoopy";
		name2 = "JugPrueba2";
		pass2 = "contra2";
		preg2 = "El gato mas guay?";
		resp2 = "Garfield";
		nameAdmin = "admin";
		passAdmin = "admin";
	}

	@After
	public void tearDown() throws Exception {
		pto = 0;
		sql = null;
		ident = null;
		name1 = null;
		pass1 = null;
		preg1 = null;
		resp1 = null;
		name2 = null;
		pass2 = null;
		preg2 = null;
		resp2 = null;
		nameAdmin = null;
		passAdmin = null;
	}
	
	@Test
	public void testRegistrarEIdentificarJugador() {
		try {
			//Antes de registrar
			//Identificar usuario que no esta en la BD
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"incorrecta");
			
			GestorJugadores.getGestor().registrarJugador(name1, pass1, preg1, resp1);
			
			//Despues de registrar
			//Identificar jugador que esta en la BD pass correcta
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"jugador");
			
			//Identificar jugador que esta en la BD pass incorrecta
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass2);
			assertEquals(ident,"incorrecta");
			
			//Se eliminara el usuario de la BD para dejarla como estaba
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name1+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			//Se comprueba la eliminacion
			assertEquals(ident,"incorrecta");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}
	
	@Test
	public void testIdentificarAdministrador() {
		try {
			//Identificar administrador que esta en la BD pass correcta
			ident = GestorJugadores.getGestor().identificarUsuario(nameAdmin, passAdmin);
			assertEquals(ident,"admin");
			
			//Identificar administrador que esta en la BD pass incorrecta
			ident = GestorJugadores.getGestor().identificarUsuario(nameAdmin, pass1);
			assertEquals(ident,"incorrecta");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}

	@Test
	public void testBuscarPreguntaJugador() {
		try {
			//Registrar jugadores
			GestorJugadores.getGestor().registrarJugador(name1, pass1, preg1, resp1);
			GestorJugadores.getGestor().registrarJugador(name2, pass2, preg2, resp2);

			//Buscar pregunta del usuario especificado
			String preg1EnBD =GestorJugadores.getGestor().buscarPreguntaJugador(name1);
			String preg2EnBD =GestorJugadores.getGestor().buscarPreguntaJugador(name2);
			assertEquals(preg1,preg1EnBD);
			assertEquals(preg2,preg2EnBD);
			
			//Se eliminara el usuario de la BD para dejarla como estaba
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name1+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name2+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			//Se comprueba la eliminacion
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"incorrecta");
			ident = GestorJugadores.getGestor().identificarUsuario(name2, pass2);
			assertEquals(ident,"incorrecta");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}

	@Test
	public void testComprobarRespuesta() {
		try {
			//Registrar jugadores
			GestorJugadores.getGestor().registrarJugador(name1, pass1, preg1, resp1);
			GestorJugadores.getGestor().registrarJugador(name2, pass2, preg2, resp2);

			//Probar respuesta correcta
			assertTrue(GestorJugadores.getGestor().comprobarRespuesta(name1, resp1));
			assertTrue(GestorJugadores.getGestor().comprobarRespuesta(name2, resp2));
			
			//Probar respuesta incorrecta
			assertFalse(GestorJugadores.getGestor().comprobarRespuesta(name1, resp2));
			assertFalse(GestorJugadores.getGestor().comprobarRespuesta(name2, resp1));
			
			//Se eliminara el usuario de la BD para dejarla como estaba
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name1+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name2+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			//Se comprueba la eliminacion
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"incorrecta");
			ident = GestorJugadores.getGestor().identificarUsuario(name2, pass2);
			assertEquals(ident,"incorrecta");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}

	@Test
	public void testActualizarPass() {
		try {
			//Registrar jugadores
			GestorJugadores.getGestor().registrarJugador(name1, pass1, preg1, resp1);

			//Comprobar identificacion
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"jugador");
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass2);
			assertEquals(ident,"incorrecta");
			
			GestorJugadores.getGestor().actualizarPass(name1, pass2);
			
			//Comprobar identificacion al cambiar password
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"incorrecta");
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass2);
			assertEquals(ident,"jugador");
			
			//Se eliminara el usuario de la BD para dejarla como estaba
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name1+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			//Se comprueba la eliminacion
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"incorrecta");
			ident = GestorJugadores.getGestor().identificarUsuario(name2, pass2);
			assertEquals(ident,"incorrecta");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}

	@Test
	public void testActualizarPuntuacion() {
		try {
			//Registrar jugadores
			GestorJugadores.getGestor().registrarJugador(name1, pass1, preg1, resp1);

			//Comprobar puntuacion inicial
			pto = this.obtenerPuntuacionUsuario(name1);
			assertEquals(pto,0);
			
			GestorJugadores.getGestor().actualizarPuntuacion(1300, name1);
			
			//Comprobar anadir 1300 puntos
			pto = this.obtenerPuntuacionUsuario(name1);
			assertEquals(pto,1300);
			
			GestorJugadores.getGestor().actualizarPuntuacion(700, name1);
			
			//Comprobar anadir 700 puntos
			pto = this.obtenerPuntuacionUsuario(name1);
			assertEquals(pto,2000);
			
			//Se eliminara el usuario de la BD para dejarla como estaba
			sql = "DELETE FROM USUARIO WHERE NOMBRE = '"+name1+"'";
			ConexionBD.getConexionBD().actualizarBD(sql);
			//Se comprueba la eliminacion
			ident = GestorJugadores.getGestor().identificarUsuario(name1, pass1);
			assertEquals(ident,"incorrecta");
			ident = GestorJugadores.getGestor().identificarUsuario(name2, pass2);
			assertEquals(ident,"incorrecta");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}
	
	//No hay ningun metodo en el modelo para ver la puntuacion directamente,
	//se utilizara el siguiente para hacer las pruebas
	private int obtenerPuntuacionUsuario(String pJugador){
		try{
			int puntuacion = 0;
			String sql = "SELECT PTO_TOTAL FROM JUGADOR WHERE NOMBRE='"+pJugador+"';";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			if(result.next()){
				puntuacion = result.getInt("PTO_TOTAL");
			} 
			else {
				puntuacion = -1;
			}
			return puntuacion;
		}catch(SQLException e){
			e.printStackTrace();
			fail("ERROR");
		}catch (ExcepcionConectarBD e){
			e.printStackTrace();
			fail("ERROR");
		}
		return -1;
	}
}
