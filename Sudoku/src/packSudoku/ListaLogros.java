package packSudoku;

import java.util.ArrayList;
import java.util.Iterator;



public class ListaLogros {
	
	ArrayList<Logros> listaLogros=new ArrayList<Logros>();
	
	public void anadirLogro(Logros logro){
		this.listaLogros.add(logro);
	}
	
	private Iterator<Logros> getIterador() {
		return this.listaLogros.iterator() ;
	}

	public Logros buscarLogro(String codLogro){
		Iterator<Logros> it= getIterador();
		Logros logro = null;
		boolean flag=false;
		while(it.hasNext()&&flag!=true){
			logro= it.next();
			if(logro.codLogro==codLogro){
				flag=true;
			}
			
		}
		return logro;
	}
	
	public void eliminarLogro(String codLogro){
		this.listaLogros.remove(buscarLogro(codLogro));	
		}

}
