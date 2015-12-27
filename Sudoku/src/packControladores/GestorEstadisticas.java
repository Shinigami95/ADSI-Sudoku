package packControladores;

public class GestorEstadisticas {
	private static GestorEstadisticas mGestor = null;
	
	private GestorEstadisticas(){}
	
	public static GestorEstadisticas getGestor(){
		if(mGestor==null){
			mGestor = new GestorEstadisticas();
		}
		return mGestor;
	}
}
