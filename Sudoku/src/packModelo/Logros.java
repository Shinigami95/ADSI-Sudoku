package packModelo;

import javax.swing.JOptionPane;

import packControladores.ConexionBD;
import packControladores.GestorLogros;
import packExcepciones.ExcepcionConectarBD;


public class Logros {
	 private static Logros mLogro;
	public Logros(){}
	//Singelton
	public static Logros getLogros(){
		if(mLogro==null){
			mLogro=new Logros();
		}
		return mLogro;
	}
	//Este metodo mete en la bd los datos basicos del logro(id,descripcion y sudoku al que pertenece) y clasifica el logro segun sus caracteristicas.
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para meterlos en la bd.
	 *Postcondicion: Los datos se han metido en la bd correctamente.
	 * */
	public void tipoLogro(String codL,String codS,String descripcion,String puntos,String numJug) throws ExcepcionConectarBD{
		//Primero se crea una variable a la que le damos el valor del id del logro que se nos pasa.
		String log=codL;
		//Esta parte es la clasificacion del logro. Si la puntuacion que se nos pasa es mayor que 0 puede ser dos tipos de logro.
		if(Integer.parseInt(puntos)>0){
			//Si el numero de jugadores que la pueden conseguir es 0 este logro se consigue al llegar a una puntuacion x.
			if(Integer.parseInt(numJug)<1){
				//En tal caso hacemos que el ID del logro tenga un primer caracter X que me ayuda a clasificarlo en otros metodos.
				log="X"+log;
				//Se mira si el logro estaba creado
				if(!GestorLogros.getGestor().estaLogro(log)){
					//Ahora se aÃ±ade a la bd en Logros y llamamos a un metodo que lo mete en la bd como un logro de tipo LogroPuntuacionX
					ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+log+"','"+codS+"','"+descripcion+"');");
					LogrosPuntuacionX.getLogroX().logrosPuntuacionX(log, puntos);
				}
			}
			//Si el numero de jugadores que la pueden conseguir es mayor que 0 este logro se consigue al ser uno de los y primeros en llegar a una puntuacion x.
			else{
				//En tal caso hacemos que el ID del logro tenga un primer caracter Y que me ayuda a clasificarlo en otros metodos.
				log="Y"+log;
				//Se mira si el logro estaba creado
				if(!GestorLogros.getGestor().estaLogro(log)){
					//Ahora se aÃ±ade a la bd en Logros y llamamos a un metodo que lo mete en la bd como un logro de tipo LogroPuntuacionXY
					ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+log+"','"+codS+"','"+descripcion+"');");
					LogrosPuntuacionXY.getLogroY().logrosPuntuacionXY(log, puntos, numJug);} 
			}
		}
		//Si la puntuacion que se nos de es 0 entoces es un logro que se consigue al ser uno de los x primeros en completar el sudoku.
		else{
			//En tal caso hacemos que el ID del logro tenga un primer caracter R que me ayuda a clasificarlo en otros metodos.
			log="R"+log;
			//Se mira si el logro estaba creado
			if(!GestorLogros.getGestor().estaLogro(log)){
				//Ahora se aÃ±ade a la bd en Logros y llamamos a un metodo que lo mete en la bd como un logro de tipo LogroResolucion
				ConexionBD.getConexionBD().actualizarBD("INSERT INTO LOGRO(ID_L,ID_SUDOKU,DESCRIPCION) VALUES('"+log+"','"+codS+"','"+descripcion+"');");
				LogrosResolucion.getLogrosR().logrosResolucion(log, numJug);}
		}
	}
	//Este metodo se encarga de modificar los datos de un logro. AVISO: No se puede cambiar el tipo de logro. Si es un logro de resolucion no se modifica para que sea de puntuacionx
	//En tal caso se debe eliminar y crear uno nuevo.
	/*Precondicion: Se le pasan unos datos que cumplen las especificaciones necesarias para que sean modificados en la bd.
	 *Postcondicion: Los datos se han modificado en la bd correctamente.
	 * */
	public void modificarLogros(String codL,String codS,String descripcion,String puntos,String numJug) throws ExcepcionConectarBD{
		//Primero modifica el logro en si(la descripcion y el sudoku asociado)
		ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO SET ID_SUDOKU='"+codS+"',DESCRIPCION='"+descripcion+"' WHERE ID_L='"+codL+"';");
		//Luego se encarga de mirar el primer caracter del id para saber que tipo de logro es y como hay que modificarlo.
		if(codL.charAt(0)=='X'){
			//Por ultimo se hacen llamadas a los metodos que modifican el tipo de logro.
			LogrosPuntuacionX.getLogroX().modificarLogro(codL, puntos);
		}
		else if (codL.charAt(0)=='Y') {
			LogrosPuntuacionXY.getLogroY().modificarLogro(codL, puntos, numJug);
		}
		else if (codL.charAt(0)=='R') {
			LogrosResolucion.getLogrosR().modificarLogro(codL, numJug);
		}
		else{JOptionPane.showMessageDialog(null, "Datos incorrectos.");}
	}


}
