package packControladores;


import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.xml.bind.ParseConversionEvent;

import packExcepciones.ExcepcionConectarBD;
import packModelo.Logros;


//
public class GestorLogros {
	
public static void anadirLogro(String iDLogro,String iDSudoku,String descripcion,String puntos,String numJug) throws ExcepcionConectarBD
{		
	Logros.tipoLogro(iDLogro, iDSudoku, descripcion, puntos, numJug);
	}	

public static void eliminar(String iDLogro) throws ExcepcionConectarBD{
	ConexionBD.getConexionBD().actualizarBD("DELETE FROM LOGRO WHERE ID_L='"+iDLogro+"';");
	
}

public static void modificarLogros(String iDLogro,String iDSudoku,String descripcion,String puntos,String numJug)throws ExcepcionConectarBD{
	Logros.modificarLogros(iDLogro, iDSudoku, descripcion, puntos, numJug);
}

public static DefaultListModel llenarLista(){
	DefaultListModel l=new DefaultListModel();
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
public static Vector<String> metodoSudoku(){
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

public static boolean datos(String iDLogro,String descripcion){
	boolean flag=false;
	if(iDLogro.length()<5 && iDLogro.length()>0 && descripcion.length()>0 && descripcion.length()<101 ){
		flag=true;
	}
	else{JOptionPane.showMessageDialog(null, "Datos Incorrectos.");}
	return flag;
}

public static DefaultListModel logrosConseguidos(String nick,int idSudoku,int pPuntos){
	DefaultListModel logros=new DefaultListModel();
	try{
		ResultSet lista=ConexionBD.getConexionBD().consultaBD("SELECT ID_L FROM LOGRO WHERE ID_SUDOKU='"+idSudoku+"';");
		while(lista.next()){
			if(lista.getString(1).charAt(0)=='X'){
				ResultSet x=ConexionBD.getConexionBD().consultaBD("SELECT PTO FROM LOGRO_PTOX WHERE ID_L='"+lista.getString(1)+"';");
				x.next();
				if(x.getInt(1)<=pPuntos){
					ConexionBD.getConexionBD().actualizarBD("INSERT INTO TIENE(ID_LOGRO,NOMBRE_JUG) VALUES('"+lista.getString(1)+"','"+nick+"');");
					logros.addElement(lista.getString(1));
				}
				ConexionBD.getConexionBD().closeResult(x);
			}
			else if (lista.getString(1).charAt(0)=='Y') {
				ResultSet y=ConexionBD.getConexionBD().consultaBD("SELECT PTO,NUM_JUG FROM LOGRO_PTOXY WHERE ID_L='"+lista.getString(1)+"';");
				y.next();
				if(y.getInt(1)<=pPuntos){
					if(y.getInt(2)>0){
						int numjug=y.getInt(2)-1;
						ConexionBD.getConexionBD().actualizarBD("INSERT INTO TIENE(ID_LOGRO,NOMBRE_JUG) VALUES('"+lista.getString(1)+"','"+nick+"');");
						ConexionBD.getConexionBD().actualizarBD("UPDATE LOGRO_PTOXY SET NUM_JUG='"+Integer.toString(numjug)+"' WHERE ID_L='"+lista.getString(1)+"';");
						logros.addElement(lista.getString(1));
					}
				}
				ConexionBD.getConexionBD().closeResult(y);
			}
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
			}
			
		}
		ConexionBD.getConexionBD().closeResult(lista);
	}
	catch(Exception e){e.printStackTrace();}
	return logros;
}

public static DefaultListModel verLogros(String nick){
	DefaultListModel logros=new DefaultListModel();
	try{
		ResultSet lista=ConexionBD.getConexionBD().consultaBD("SELECT ID_LOGRO FROM TIENE WHERE NOMBRE_JUG='"+nick+"';");
		while(lista.next()){
			ResultSet logs=ConexionBD.getConexionBD().consultaBD("SELECT ID_L,ID_SUDOKU,DESCRIPCION FROM LOGRO WHERE ID_L='"+lista.getString(1)+"';");
			Logros logro=new Logros(Integer.toString(logs.getInt(2)), logs.getString(1), logs.getString(3));
			logros.addElement(logro);
			ConexionBD.getConexionBD().closeResult(logs);
		}
		ConexionBD.getConexionBD().closeResult(lista);
	}catch(Exception e){e.printStackTrace();}
	
	return logros;
}
}
