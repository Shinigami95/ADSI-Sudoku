package packSudoku;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorLogros {
	
	ArrayList<Logros> miListaLogros=new ArrayList<Logros>();

	
	private GestorLogros(){
		if(miListaLogros==null){
			miListaLogros=new ArrayList<Logros>();
		}
	}
	
	public void anadirLogro(Logros logro){
		this.miListaLogros.add(logro);
	}
	
	private Iterator<Logros> getIterador() {
		return this.miListaLogros.iterator() ;
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
		this.miListaLogros.remove(buscarLogro(codLogro));	
		}

	
	
}
