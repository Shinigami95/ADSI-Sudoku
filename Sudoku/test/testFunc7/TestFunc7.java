package testFunc7;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packControladores.ConexionBD;
import packControladores.GestorEstadisticas;
import packExcepciones.ExcepcionConectarBD;

public class TestFunc7 {
	
	//IMPORTANTE PONER LOS METODOS CORRESPONDIENTES DE LA CLASE GESTORESTADISTICAS PUBLICOS
	String jug1, jug2;
	String idS1, idS2;
	
	@Before
	public void setUp() throws Exception {
		jug2 = "Jorge";
		jug2 = "asdf";
		idS1 = "2";
		idS2 = "-1";
	}

	@After
	public void tearDown() throws Exception {
		jug2 = null;
		jug2 = null;
		idS1 = null;
		idS2 = null;
	}

	//Obtenemos el tiempo total que ha tardado el usuario en hacer sudokus de la dificultad que sea
	private int obtenerTiempoDeSudokusHechosPorDificultad(int pDif, String pJugador){
		int hechos = 0;
		try {
			ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT SUM(SEGUNDOS) AS TIEMPO FROM JUGADO INNER JOIN SUDOKU ON SUDOKU.ID_S = JUGADO.ID_SUDOKU WHERE JUGADO.NOMBRE_JUG = '"+pJugador+"' AND SUDOKU.DIFICULTAD = '"+pDif+"' AND JUGADO.COMPLETADO = 'S';");
			if(result.next()){
				hechos = Integer.parseInt(result.getString("TIEMPO"));
			}else{
				System.out.println("Este usuario no tiene sudokus jugados en esta dificultad");
			}
			ConexionBD.getConexionBD().closeResult(result);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hechos;
	}
	
	@Test
	public void testTiempoMedioResolucionPorCategoriaDelJugador() {
		try{
			int[][] resul = GestorEstadisticas.getGestor().getTiempoMedioResolucionPorCategoriaDelJugador(jug1);
			/*
			 * [i][0] -> Dificultad ([0] es dificultad 1, [1] es 2...)
			 * [i][1] -> Tiempo medio
			 * [i][2] -> Sudokus hechos en total
			 * siendo i las tuplas que el resultado da
			 */
			if(resul.length>0){
				for(int i=0; i<resul.length;i++){
					//De cada tupla cogemos la dificultad, tiempo medio y sudokus jugados
					int dif = resul[i][0];
					int tiempoMedio = resul[i][1];
					int sudJugados = resul[i][2];
					//Vemos los datos
					System.out.println("Dificultad: " + dif);
					System.out.println("Tiempo medio: " + tiempoMedio);
					System.out.println("Sudokus jugados: " + sudJugados);
					System.out.println("-----------------");
					//Obtenemos el tiempo total de los sudokus que el usuario ha jugado de la dificultad
					int tiempoTotal = obtenerTiempoDeSudokusHechosPorDificultad(dif, jug1);
					//Dividimos el tiempo total entre los sudokus jugados y comprobamos si coincide con el valor de la BD
					assertEquals(tiempoMedio, tiempoTotal/sudJugados);
				}
			}else{
				System.out.println("Este usuario no ha jugado sudokus");
			}
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		}
	}

	@Test
	public void testInfoSudokusResueltosDelJugador() {
		try{
			int[] resul = GestorEstadisticas.getGestor().getInfoSudokusResueltosDelJugador(jug1);
			/*
			 * [0] -> totalSudokus
			 * [1] -> sudCompletos
			 * [2] -> sudIncompletos
			 */
			//Cogemos los sudokus totales, los completos y los incompletos a traves de consultas
			ResultSet result1 = ConexionBD.getConexionBD().consultaBD("SELECT COUNT(*) AS TOTAL FROM JUGADO INNER JOIN SUDOKU ON ID_SUDOKU=ID_S WHERE NOMBRE_JUG='"+jug1+"';");
			result1.next();
			int total = result1.getInt("TOTAL");
			ConexionBD.getConexionBD().closeResult(result1);
			ResultSet result2 = ConexionBD.getConexionBD().consultaBD("SELECT COUNT(*) AS COMPLETADOS FROM JUGADO INNER JOIN SUDOKU ON ID_SUDOKU=ID_S WHERE NOMBRE_JUG='"+jug1+"' AND JUGADO.COMPLETADO='S';");
			result2.next();
			int completos = result2.getInt("COMPLETADOS");
			ConexionBD.getConexionBD().closeResult(result2);
			ResultSet result3 = ConexionBD.getConexionBD().consultaBD("SELECT COUNT(*) AS INCOMPLETOS FROM JUGADO INNER JOIN SUDOKU ON ID_SUDOKU=ID_S WHERE NOMBRE_JUG='"+jug1+"' AND JUGADO.COMPLETADO='N';");
			result3.next();
			int incompletos = result3.getInt("INCOMPLETOS");
			ConexionBD.getConexionBD().closeResult(result3);
			//Comparamos los resultados de las consultas a los del metodo a probar
			assertEquals(resul[0], total);
			assertEquals(resul[1], completos);
			assertEquals(resul[2], incompletos);
			System.out.println("Total sudokus: " + total);
			System.out.println("Sudokus completados: " + completos);
			System.out.println("Sudokus no completados: " + incompletos);
			System.out.println("---------------------");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
			fail("ERROR");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCuantosCompletadoYRendidoSudoku() {
		try{
			int[] resul = GestorEstadisticas.getGestor().getCuantosCompletadoYRendidoSudoku(idS1);
			/*
			 * [0] -> jugadores que lo han completado
			 * [1] -> jugadores que se han rendido
			 */
			//Cogemos los numeros de cuantos usuarios han completado y se han rendido en este sudoku
			ResultSet result1 = ConexionBD.getConexionBD().consultaBD("SELECT COUNT(*) AS COMPLETADOS FROM JUGADO WHERE ID_SUDOKU='"+idS1+"' AND COMPLETADO='S';");
			result1.next();
			int completados = result1.getInt("COMPLETADOS");
			ConexionBD.getConexionBD().closeResult(result1);
			ResultSet result2 = ConexionBD.getConexionBD().consultaBD("SELECT COUNT(*) AS RENDIDOS FROM JUGADO WHERE ID_SUDOKU='"+idS1+"' AND COMPLETADO='N';");
			result2.next();
			int rendidos = result2.getInt("RENDIDOS");
			ConexionBD.getConexionBD().closeResult(result2);
			//Hacemos la comparativa de las consultas con los resultados del metodo a probar
			assertEquals(resul[0], completados);
			assertEquals(resul[1], rendidos);
			System.out.println("El sudoku " + idS1 + " lo ha(n) completado " + completados + " usuario(s)");
			System.out.println("En el sudoku " + idS1 + " se ha(n) rendido " + rendidos + " usuario(s)");
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getTiempoMedioResolucionSudoku() {
		try{
			int resul = GestorEstadisticas.getGestor().getTiempoMedioResolucionSudoku(idS1);
			if(resul != 0){
				//Cogemos los sudokus jugados y el tiempo total en los que se han jugado esos sudokus
				ResultSet result = ConexionBD.getConexionBD().consultaBD("SELECT COUNT(*) AS JUGADOS FROM JUGADO WHERE ID_SUDOKU='"+idS1+"' AND COMPLETADO='S';");
				result.next();
				int jugados = result.getInt("JUGADOS");
				ConexionBD.getConexionBD().closeResult(result);
				ResultSet result1 = ConexionBD.getConexionBD().consultaBD("SELECT SUM(SEGUNDOS) AS TIEMPO FROM JUGADO WHERE ID_SUDOKU='"+idS1+"' AND COMPLETADO='S';");
				result1.next();
				int tiempoTotal = result1.getInt("TIEMPO");
				//Comprobamos el resultado del metodo con el resultado de tiempoTotal/jugados
				assertEquals(resul, tiempoTotal/jugados);
				System.out.println("El sudoku " + idS1 + " se ha resuelto en una media de " + resul + " segundos");
				ConexionBD.getConexionBD().closeResult(result1);
			}else{
				System.out.println("Este sudoku no esta en la base de datos");
			}
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
