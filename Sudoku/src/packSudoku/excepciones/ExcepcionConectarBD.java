package packSudoku.excepciones;

import packInterfazGrafica.VentanaError;

public class ExcepcionConectarBD extends Exception{
	//
	public ExcepcionConectarBD(){
		VentanaError.obtVentanaError().mostrar("No se ha conseguido conectar a la Base de Datos.");
	}
	
}
