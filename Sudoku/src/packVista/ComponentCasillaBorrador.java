package packVista;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.Color;

public class ComponentCasillaBorrador extends ComponentCasillaGenerica{

	private static final long serialVersionUID = 1L;
	private JTextField[] listaTextField;
	
	public ComponentCasillaBorrador() {
		setLayout(new GridLayout(0, 3, 0, 0));
		listaTextField = new JTextField[9];
		JTextField textAux;
		for(int i = 0; i<listaTextField.length; i++){
			textAux = new JTextField();
			listaTextField[i] = textAux;
			textAux.setHorizontalAlignment(SwingConstants.CENTER);
			textAux.setFont(new Font("Helvetica", Font.PLAIN, 10));
			textAux.setForeground(Color.BLUE);
			this.add(textAux);
			textAux.setColumns(10);	
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
