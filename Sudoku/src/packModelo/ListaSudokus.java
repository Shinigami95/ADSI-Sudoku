package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaSudokus {

	private Dificultad nivel;
	private ArrayList<Sudoku> lista;

	/**
	 * 
	 * @param pD
	 */
	public ListaSudokus(Dificultad pD) {
		this.nivel = pD;
		this.lista = new ArrayList<Sudoku>();
	}

	/**
	 * 
	 * @param pU
	 */
	public Sudoku obtenerSudoku(Usuario pU) {
		if(this.lista.size()==0){
			return null;
		}
		Random rn = new Random();
		int index = rn.nextInt(this.lista.size());
		Sudoku sud = this.lista.get(index);
		if(pU.haHechoSudoku(sud.getId())){
			boolean enc = false;
			Iterator<Sudoku> itr = this.getIterador();
			while(itr.hasNext()&&!enc){
				sud=itr.next();
				if(!pU.haHechoSudoku(sud.getId())){
					enc = true;
				}
			}
			if(!enc){
				System.out.println("No hay sudokus disponibles");
				return null;
			}
		}
		return sud;
	}

	private Iterator<Sudoku> getIterador() {
		return this.lista.iterator();
	}

	/**
	 * 
	 * @param pS
	 */
	public void addSudoku(Sudoku pS) {
		this.lista.add(pS);
	}

	/**
	 * 
	 * @param pS
	 */
	public boolean esDificultad(Dificultad pD) {
		return this.nivel.equals(pD);
	}
}