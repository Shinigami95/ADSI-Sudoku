package packVista;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class ComponentCasillaBorrador extends ComponentCasillaGenerica{

	private static final long serialVersionUID = 1L;
	private JLabel[] listaTextField;
	
	public ComponentCasillaBorrador() {
		setLayout(new GridLayout(0, 3, 0, 0));
		listaTextField = new JLabel[9];
		JLabel textAux;
		for(int i = 0; i<listaTextField.length; i++){
			textAux = new JLabel();
			listaTextField[i] = textAux;
			textAux.setHorizontalAlignment(SwingConstants.CENTER);
			textAux.setFont(new Font("Helvetica", Font.PLAIN, 10));
			textAux.setForeground(Color.BLUE);
			this.setBorder(new LineBorder(new Color(0,0,0),1));
			this.setBackground(Color.WHITE);
			this.add(textAux);
		}
	}

	@Override
	public void escribirTexto(String pTexto) {
		for(int i = 0; i<listaTextField.length; i++){
			listaTextField[i].setText(pTexto.charAt(i)+"");
		}
		System.out.println("Borrador -> Escribir");
	}
}
