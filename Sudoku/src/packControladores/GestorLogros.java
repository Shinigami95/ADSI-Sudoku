package packControladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import packExcepciones.ExcepcionConectarBD;
import packModelo.Logros;

public class GestorLogros {
	private static GestorLogros mGest;
	
	private GestorLogros(){}
	//Singelton
	public static GestorLogros getGestor(){
		if(mGest==null){
			mGest = new GestorLogros();
		}
		return mGest;
	}
	//Llama al metodo que clasifica los logros y los mete en la bd.
	/*Precondicion: Esnecesario que el metodo datos(abajo) compruebe que los datos cumplen ciertas condiciones.
	 *Postcondicion:Los datos se han metido en la bd correctamente.
	 * */
	public void anadirLogro(String iDLogro,String iDSudoku,String descripcion,String puntos,String numJug) throws ExcepcionConectarBD
	{		
		Logros.getLogros().tipoLogro(iDLogro, iDSudoku, descripcion, puntos, numJug);
		}	
	//Llama al metodo que elimina los logros en la bd.
	/*Precondicion: El String que se le pasa tenga menos de 5 caracteres.
	 *Postcondicion: Se ha eliminado el logro con el id que se le pasa de la bd.
	 * */
	public void eliminar(String iDLogro) throws ExcepcionConectarBD{
		ConexionBD.getConexionBD().actualizarBD("DELETE FROM LOGRO WHERE ID_L='"+iDLogro+"';");
		
	}
	//Llama al metodo que hace modificadiones de los logros en la bd.
	/*Precondicion: Los datos que se le pasan tienen que cumplir las condiciones que se especifican en el metodo datos(abajo).
	 *Postcondicion: Los datos se modifican correctamente en la bd.
	 * */
	public void modificarLogros(String iDLogro,String iDSudoku,String descripcion,String puntos,String numJug)throws ExcepcionConectarBD{
		Logros.getLogros().modificarLogros(iDLogro, iDSudoku, descripcion, puntos, numJug);
	}
	//Devuleve una lista con los IDs de todos los logros existentes. Se utiliza para llenar las JLists que tienen los Ids de los Logros.
	/*Precondicion:
	 *Postcondicion:
	 * */
	public DefaultListModel<String> llenarLista(){
		DefaultListModel<String> l=new DefaultListModel<String>();
		try{
			ConexionBD con= ConexionBD.getConexionBD();
			ResultSet resul=con.consultaBD("SELECT ID_L FROM LOGRO;");
			int i=0;
			while(resul.next()){
				l.add(i, resul.getString("ID_L"));
				i++;
			}
			ConexionBD.getConexionBD().closeResult(resul);
			}catch(Exception e){
				e=new ExcepcionConectarBD();
			}
		return l;
	}
	//https://www.youtube.com/watch?v=v8zKLCec-Tk
	//Devuleve una lista con los IDs de todos los sudokus existentes, esten activos o no. Se utiliza para llenar los combobox de VentanaAdminLogros
	/*Precondicion:
	 *Postcondicion:
	 * */
	public Vector<String> metodoSudoku(){
		Vector<String> vec=new Vector<String>();
		try{
			ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT ID_S FROM SUDOKU;");
			while(res.next()){
				vec.add(Integer.toString(res.getInt(1)));
			}
			ConexionBD.getConexionBD().closeResult(res);
		}catch(Exception e){e.printStackTrace();}
		return vec;
	}
	//Comprueba que los datos que se introducen cumplen unas caracteristicas para luego asi meterlos en la bd.
	/*Precondicion: Se le pasan los datos a comprobar.
	 *Postcondicion:
	 * */
	public boolean datos(String iDLogro,String descripcion,String puntos,String jug) {
		boolean flag=false;
		if(iDLogro.length()<5 && iDLogro.length()>0 && descripcion.length()>0 && descripcion.length()<101 && puntos.length()>0 && jug.length()>0 ){
			if (Integer.parseInt(puntos)==0 && Integer.parseInt(jug)==0){
				JOptionPane.showMessageDialog(null, "Datos Incorrectos.");
			}
			else{flag=true;}
		}
		else{JOptionPane.showMessageDialog(null, "Datos Incorrectos.");}
		return flag;
	}
	//Este metodo devuelve una lista con los IDs de los logros que ha conseguido el usuario al terminar un sudoku.
	/*Precondicion: Se le pasa un usuario y un sudoku que estan en la bd.
	 *Postcondicion: Se ha asociado el usuario con el logro conseguido.
	 * */
	public DefaultListModel<String> logrosConseguidos(String nick,String idSudoku,String pPuntos){
		//Primero se crea la lista a devolver.
		DefaultListModel<String> logros=new DefaultListModel<String>();
		try{//Se cogen de la bd los logros asociados al sudoku que se ha hecho.
			ResultSet lista=ConexionBD.getConexionBD().consultaBD("SELECT ID_L FROM LOGRO WHERE ID_SUDOKU='"+idSudoku+"';");
			while(lista.next()){
				//Se mira si el usuario tiene el logro
				ResultSet tiene=ConexionBD.getConexionBD().consultaBD("SELECT ID_LOGRO FROM TIENE WHERE ID_LOGRO='"+lista.getString(1)+"' AND NOMBRE_JUG='"+nick+"';");
				if(tiene.next()){}
				else{
				//Dependiendo del ID del logro se hacen unas cosas u otras ya que los tipos de logro se diferencian en el ID del logro.
				//Si el primer caracter del ID es una X el logro se consigue al llegar a x puntuacion en ese sudoku, si es Y el logro se consigue al
				//ser de los y primeros en conseguir x puntos en el sudoku y R se le concede el logro a los x primeros en resolver el sudoku.
				if(lista.getString(1).charAt(0)=='X'){
					//Se escoge el logro con el ID en la bd.
					ResultSet x=ConexionBD.getConexionBD().consultaBD("SELECT PTO FROM LOGRO_PTOX WHERE ID_L='"+lista.getString(1)+"';");
					x.next();
					//Se comprueba que el usuario ha conseguido la puntuacion necesaria para conseguir el logro.
					if(x.getInt(1)<=Integer.parseInt(pPuntos)){
						//Si es asi se mete la relacion del logro con el usuario en la base de datos para saber que lo ha conseguido.
						ConexionBD.getConexionBD().actualizarBD("INSERT INTO TIENE(ID_LOGRO,NOMBRE_JUG) VALUES('"+lista.getString(1)+"','"+nick+"');");
						//Por ultimo se aÃƒÂ±ade el logro a la lista que se devolvera luego.
						logros.addElement(lista.getString(1));
					}
					ConexionBD.getConexionBD().closeResult(x);
				}
				//En los dos casos siguientes deifieren dos aspectos.
				else if (lista.getString(1).charAt(0)=='Y') {
					ResultSet y=ConexionBD.getConexionBD().consultaBD("SELECT PTO,NUM_JUG FROM LOGRO_PTOXY WHERE ID_L='"+lista.getString(1)+"';");
					y.next();
					//Despues de coger el logro y comprobar que ha superado la puntuacion necesaria se comprueba que el numero de jugadores a los que se le puede dar sea mayor que 0.
					if(y.getInt(1)<=Integer.parseInt(pPuntos)){
						if(y.getInt(2)>0){
							//Si es asi a parte de aÃƒÂ±adir la relacion a la bd se modifica el logro restando 1 a la cantidad de jugadores que pueden conseguirlo. 
							//De esta manera controlo que se le de al numero adecuado de usuarios.
							int numjug=y.getInt(2)-1;
							ConexionBD.getConexionBD().actualizarBD("INSERT INTO TIENE(ID_LOGRO,NOMBRE_JUG) VALUES('"+lista.getString(1)+"','"+nick+"');");
							ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOXY SET NUM_JUG='"+Integer.toString(numjug)+"' WHERE ID_L='"+lista.getString(1)+"';");
							logros.addElement(lista.getString(1));
						}
					}
					ConexionBD.getConexionBD().closeResult(y);
				}
				//En este ultimo pasa lo mismo pero sin la parte de comprobar la puntuacion.
				else if (lista.getString(1).charAt(0)=='R') {
					ResultSet r=ConexionBD.getConexionBD().consultaBD("SELECT NUM_JUG FROM LOGRO_RES WHERE ID_L='"+lista.getString(1)+"';");
					r.next();
					if(r.getInt(1)>0){
						int numjug1=r.getInt(1)-1;
						ConexionBD.getConexionBD().actualizarBD("INSERT INTO TIENE(ID_LOGRO,NOMBRE_JUG) VALUES('"+lista.getString(1)+"','"+nick+"');");
						ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_RES SET NUM_JUG='"+Integer.toString(numjug1)+"' WHERE ID_L='"+lista.getString(1)+"';");
						logros.addElement(lista.getString(1));
					}
					ConexionBD.getConexionBD().closeResult(r);
				}}
				//Por ultimo se cierran las conexiones y se devuele la lista con los IDs de los logros conseguidos.
			}
			ConexionBD.getConexionBD().closeResult(lista);
		}
		catch(Exception e){e.printStackTrace();}
		return logros;
	}
	//Devuelve los IDs de los logros que ha conseguido un usuario mediante una DefaultListModel que aparece en el historial.
	/*Precondicion: Se le pasa un usuario que exista en la bd.
	 *Postcondicion:
	 * */
	public DefaultListModel<String> verLogros(String nick){
		DefaultListModel<String> logros=new DefaultListModel<String>();
		try{
			ResultSet lista=ConexionBD.getConexionBD().consultaBD("SELECT ID_LOGRO FROM TIENE WHERE NOMBRE_JUG='"+nick+"';");
			while(lista.next()){
				logros.addElement(lista.getString(1));
			}
			ConexionBD.getConexionBD().closeResult(lista);
		}catch(Exception e){e.printStackTrace();}
		
		return logros;
	}
	//Este metodo devuelve el id del sudoku al cual pertenece logro que se le pasa
		/*Precondicion: Se le pasa un id de un logro que exista en la bd.
		 *Postcondicion:
		 * */
	public String getSudoku(String pIdL) throws ExcepcionConectarBD, SQLException {
	ResultSet tes=ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM LOGRO WHERE ID_L='"+pIdL+"';");
	tes.next();
	String ses=tes.getString("ID_SUDOKU");
	ConexionBD.getConexionBD().closeResult(tes);
	return ses;}
	//Este metodo devuelve la descripcion del logro que se le pasa
		/*Precondicion: Se le pasa un id de un logro que exista en la bd.
		 *Postcondicion:
		 * */
	public String getDescripcionDe(String pIdL) throws ExcepcionConectarBD {
		try{
			String sql = "SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+pIdL+"';";
			ResultSet result = ConexionBD.getConexionBD().consultaBD(sql);
			String descr = null;
			if(result.next()){
				descr = result.getString("DESCRIPCION");
			}
			ConexionBD.getConexionBD().closeResult(result);
			return descr;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	//Verifica que el logro no est� creado.
	//POST: true si existe un logro con ese ID
	public boolean estaLogro(String idLogro){
		boolean esta=false;
		String sql="SELECT * FROM LOGRO WHERE ID_L='"+idLogro+"';";
		try{
			ResultSet result= ConexionBD.getConexionBD().consultaBD(sql);
			if(result.next()){
				esta=true;
			}
			result.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return esta;
	}
}
