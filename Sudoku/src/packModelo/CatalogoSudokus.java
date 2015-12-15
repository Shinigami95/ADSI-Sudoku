package packModelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import packSudoku.excepciones.ExcepcionNoHayFicheroSudokus;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;

public class CatalogoSudokus {

	private static CatalogoSudokus mCatalogo;
	private ArrayList<ListaSudokus> lista;

	private CatalogoSudokus(){
		this.lista = new ArrayList<ListaSudokus>();
		this.cargarFicheros();
	}

	public static CatalogoSudokus getCatalogo(){
		if(mCatalogo==null){
			mCatalogo = new CatalogoSudokus();
		}
		return mCatalogo;
	}

	public void cargarFicheros(){
		try{
			this.cargarFichero("sudoku_facil.txt",Dificultad.FACIL);
		} catch (ExcepcionNoHayFicheroSudokus e){
			System.out.println("No se ha encontrado el fichero sudoku_facil.txt");
		}
		try{
			this.cargarFichero("sudoku_medio.txt",Dificultad.MEDIO);
		} catch (ExcepcionNoHayFicheroSudokus e){
			System.out.println("No se ha encontrado el fichero sudoku_medio.txt");
		}
		try{
			this.cargarFichero("sudoku_dificil.txt",Dificultad.DIFICIL);
		} catch (ExcepcionNoHayFicheroSudokus e){
			System.out.println("No se ha encontrado el fichero sudoku_dificil.txt");
		}

	}

	public void cargarFichero(String pF, Dificultad pD) throws ExcepcionNoHayFicheroSudokus{
		System.out.println("Cargando sudokus del fichero "+pF+" ...");
		try{
			File archivo = new File(".",pF);
			Scanner entrada = new Scanner(new FileReader(archivo));
			
			String linea;
			String[] tokens;
			
			ListaSudokus lSud = new ListaSudokus(pD);
			this.addLista(lSud);
			Sudoku sud;
			
			while (entrada.hasNext()) {
				linea = entrada.nextLine();
				tokens = linea.split("\\s+\\\\\\s+");
				sud = new Sudoku(tokens[0], tokens[1], tokens[2]);
				lSud.addSudoku(sud);
			}
			entrada.close();
		} 
		catch(FileNotFoundException e){throw new ExcepcionNoHayFicheroSudokus();}
		catch(NullPointerException e){e.printStackTrace();}
		catch(Exception e){e.printStackTrace();}
		System.out.println("Fin carga\n");
	}
	
	private Iterator<ListaSudokus> getIterador() {
		return this.lista.iterator();
	}

	/**
	 * 
	 * @param pNivel
	 */
	private ListaSudokus buscarLista(Dificultad pNivel) {
		Iterator<ListaSudokus> itr = this.getIterador();
		ListaSudokus lAux;
		while(itr.hasNext()){
			lAux = itr.next();
			if(lAux.esDificultad(pNivel)){
				return lAux;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param pD
	 * @param pU
	 */
	public Sudoku obtenerSudokuParaUsuario(Dificultad pD, Usuario pU) throws ExcepcionNoHaySudokuCargado{
		ListaSudokus lAux = this.buscarLista(pD);
		if(lAux==null){
			System.out.println("No hay sudokus de esa dificultad");
			throw new ExcepcionNoHaySudokuCargado();
		}
		else{
			Sudoku sud = lAux.obtenerSudoku(pU);
			if(sud==null) throw new ExcepcionNoHaySudokuCargado();
			return sud;
		}
	}

	private void addLista(ListaSudokus pL){
		this.lista.add(pL);
	}
}