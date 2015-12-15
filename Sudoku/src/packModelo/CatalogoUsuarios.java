package packModelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CatalogoUsuarios {

	private static CatalogoUsuarios mCatalogo;
	private ArrayList<Usuario> lista;
	
	private CatalogoUsuarios() {
		this.lista = new ArrayList<Usuario>();
		this.cargarDeFichero();
	}

	public static CatalogoUsuarios getCatalogo() {
		if(mCatalogo == null){
			mCatalogo = new CatalogoUsuarios();
		}
		return mCatalogo;
	}
	
	public void cargarDeFichero() {
		System.out.println("Cargando usuarios de fichero...");
		try{
			File archivo = new File(".","users.txt");
			Scanner entrada = new Scanner(new FileReader(archivo));
			
			String linea, idSud;
			String[] tokens;
			
			Usuario uAux;
			Score sAux;
			int nF,nM,nD,ptos,i;
			
			while (entrada.hasNext()) {
				linea = entrada.nextLine();
				tokens = linea.split("\\s+\\\\\\s+");
				ptos = Integer.parseInt(tokens[1]);
				nF = Integer.parseInt(tokens[2]);
				nM = Integer.parseInt(tokens[3]);
				nD = Integer.parseInt(tokens[4]);
				sAux = new Score(ptos, nF, nM, nD);
				uAux = new Usuario(tokens[0],sAux);
				i = 5;
				boolean sudCargados = false;
				while(!sudCargados){
					try{
						idSud = tokens[i];
						uAux.addIDSudoku(idSud);
						i++;
					}
					catch(ArrayIndexOutOfBoundsException e){
						sudCargados = true;
					}
				}

				this.anadirUsuario(uAux);
			}
			entrada.close();
		} 
		catch(FileNotFoundException e){System.out.println("No se ha encontrado el fichero users.txt\n Fin carga");}
		catch(NullPointerException e){e.printStackTrace();}
		catch(Exception e){e.printStackTrace();}
		System.out.println("Fin carga");
	}

	public void guardarFichero() {
		System.out.println("Guardando usuarios...");
		Usuario uAux;
		String linea;
		FileWriter fw = null;
		PrintWriter pw = null;
		Iterator<Usuario> itr = this.getIterador();
		try {
			File archivo = new File(".","users.txt");
			archivo.createNewFile();
			fw = new FileWriter("users.txt");
			pw = new PrintWriter(fw);
			while (itr.hasNext()){
				uAux = itr.next();
				linea = uAux.toStringParaFichero();
				pw.println(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR, no es un path correcto.");
		} catch (IOException e) {
			System.out.println("ERROR, no se ha leido bien la linea.");
		} finally {
			try {
				if( fw != null ){
					fw.close();
				}
				if( pw != null ){
					pw.close();
				}
			} catch (IOException e2){
				e2.printStackTrace();
			}
		}
		System.out.println("Archivo guardado.");
	}

	private Iterator<Usuario> getIterador(){
		return this.lista.iterator();
	}
	
	/**
	 * 
	 * @param pNombre
	 */
	public Usuario obtenerUsuario(String pNombre) {
		Usuario uAux = this.buscarUsuario(pNombre);
		if (uAux == null){
			uAux = new Usuario(pNombre);
			System.out.println("Se ha creado el usuario: "+pNombre);
			this.anadirUsuario(uAux);
		}
		return uAux;
	}

	/**
	 * 
	 * @param pNombre
	 */
	private Usuario buscarUsuario(String pNombre) {
		Usuario uAux;
		Iterator<Usuario> itr = this.getIterador();
		boolean enc = false;
		while(itr.hasNext() && !enc){
			uAux = itr.next();
			if(uAux.tieneElNombre(pNombre)){
				return uAux;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param pNombre
	 */
	private void anadirUsuario(Usuario pU) {
		this.lista.add(pU);
	}

	/**
	 * 
	 * @param pNum
	 */
	public String obtenerDatosDeLosMejores(int pNum) {
		this.ordenarPorScoreQuick();
		String datos = "";
		int i = 0;
		while (i<pNum && i<this.lista.size()){
			datos+=this.lista.get(i).toString()+"\n";
			i++;
		}
		return datos;
	}

	private void ordenarPorScoreQuick() {
		ordenarPorScoreQuickAux(0, this.lista.size()-1);
	}
	
	private void ordenarPorScoreQuickAux(int pInicio,int pFin){
		if (pFin - pInicio > 0){
			int indiceParticion = particion(pInicio,pFin);
			this.ordenarPorScoreQuickAux(pInicio,indiceParticion-1);
			this.ordenarPorScoreQuickAux(indiceParticion+1,pFin);
		}
	}
	
	private int particion(int i, int f){
		Usuario a1 = this.lista.get(i);
		int izq = i;
		int der = f;
		while(izq<der){
			while(this.lista.get(der).compareTo(a1)<=0&&izq<der){
				der--;
			}
			if (izq<der){
				this.lista.set(izq,this.lista.get(der));
			}
			while(this.lista.get(izq).compareTo(a1)>0&&izq<der){
				izq++;
			}
			if (izq<der){
				this.lista.set(der,this.lista.get(izq));
			}
		}
		this.lista.set(der, a1);
		return der;
	}
}