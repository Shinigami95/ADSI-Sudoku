package packVista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import packModelo.Borrador;
import packModelo.Casilla;

import java.awt.BorderLayout;

public class ComponentCasillaGenerica extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private ComponentCasillaGenerica mCasilla;
	private int corX, corY;
	
	public ComponentCasillaGenerica(){}
	
	public ComponentCasillaGenerica(JPopupMenu pPopupMenu, int pX, int pY){
		this.corX = pX;
		this.corY = pY;
		this.setLayout(new BorderLayout(0, 0));
		this.setComponentPopupMenu(pPopupMenu);
		this.setFocusable(false);
		this.mCasilla = new ComponentCasillaNormal();
		this.add(mCasilla);
	}
	
	public int getCorX(){return corX;}
	
	public int getCorY(){return corY;}
	
	//Permite cambiar el tipo de casilla en el panel
	public void setCasilla(ComponentCasillaGenerica pCas){
		this.remove(mCasilla);
		this.mCasilla = pCas;
		this.add(mCasilla);
	}
	
	public boolean esBorrador(){
		return this.mCasilla instanceof ComponentCasillaBorrador;
	}
	
	//Escribira los numeros que aparecen en el modelo
	//aprobechandose del polimorfismo de mCasilla
	//(inicial, normal o borrador)
	//mostrara los valores de un modo u otro
	public void escribirTexto(String pTexto) {
		if(this.mCasilla!=null)this.mCasilla.escribirTexto(pTexto);
	}
	
	//Al observar la casilla del modelo tendra en cuenta si hay que
	//cambiar el tipo de casilla en la vista.
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Casilla){
			Casilla cas = (Casilla) o;
			if(cas.esInicial() && !(mCasilla instanceof ComponentCasillaInicial)){
				this.setCasilla(new ComponentCasillaInicial());
				this.setComponentPopupMenu(null);
			} 
			else if(!(mCasilla instanceof ComponentCasillaNormal)){
				this.setCasilla(new ComponentCasillaNormal());
			}
		}
		else if(o instanceof Borrador && !(mCasilla instanceof ComponentCasillaBorrador)){
			this.setCasilla(new ComponentCasillaBorrador());
		}
		this.escribirTexto((String)arg);
	}
}
