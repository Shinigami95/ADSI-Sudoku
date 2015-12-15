package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaIDSudokus {

	private ArrayList<String> lista;

	public ListaIDSudokus() {
		this.lista = new ArrayList<String>();
	}

	/**
	 * 
	 * @param pID
	 */
	public void add(String pID) {
		this.lista.add(pID);
	}

	/**
	 * 
	 * @param pID
	 */
	public boolean estaID(String pID) {
		Iterator<String> itr = this.getIterador();
		boolean enc = false;
		String sAux;
		while(itr.hasNext()&&!enc){
			sAux = itr.next();
			if(sAux.equals(pID)){
				enc = true;
			}
		}
		return enc;
	}

	private Iterator<String> getIterador() {
		return this.lista.iterator();
	}

	public String toStringParaFichero() {
		Iterator<String> itr = this.getIterador();
		String sResul = "", sAux;
		while(itr.hasNext()){
			sAux = itr.next();
			sResul += " \\ "+sAux;
		}
		return sResul;
	}

}