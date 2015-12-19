package packVista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import packModelo.Borrador;
import packModelo.Casilla;

import java.awt.BorderLayout;

public class ComponentCasillaAbstracta extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private ComponentCasillaAbstracta mCasilla;
	
	public ComponentCasillaAbstracta(){}
	
	public ComponentCasillaAbstracta(JPopupMenu pPopupMenu){
		setLayout(new BorderLayout(0, 0));
		this.setComponentPopupMenu(pPopupMenu);
		this.setFocusable(false);
		this.mCasilla = new ComponentCasillaNormal();
		this.add(mCasilla);
	}
	
	public void setCasilla(ComponentCasillaAbstracta pCas){
		this.remove(mCasilla);
		this.mCasilla = pCas;
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		mCasilla = null;
	}
	
	public void escribirTexto(String pTexto) {}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Casilla){
			Casilla cas = (Casilla) o;
			if(cas.esInicial() && !(mCasilla instanceof ComponentCasillaInicial)){
				this.setCasilla(new ComponentCasillaInicial());
			} 
			else if(!(mCasilla instanceof ComponentCasillaNormal)){
				this.setCasilla(new ComponentCasillaNormal());
			}
		}
		else if(o instanceof Borrador && !(mCasilla instanceof ComponentCasillaBorrador)){
			this.setCasilla(new ComponentCasillaBorrador());
		}
		System.out.println((String)arg);
		this.mCasilla.escribirTexto((String)arg);
	}
}
