package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ComponentCasillaNormal extends ComponentCasillaGenerica{

	private static final long serialVersionUID = 1L;
	JLabel text;
	
	public ComponentCasillaNormal(){
		text = new JLabel();
		text.setMaximumSize(new Dimension(50, 50));
		text.setMinimumSize(new Dimension(50, 50));
		text.setSize(new Dimension(50, 50));
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("Helvetica", Font.PLAIN, 16));
		text.setForeground(Color.RED);
		text.setFocusable(false);
		text.setText("-");
		text.setBorder(new LineBorder(new Color(0,0,0),1));
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setLayout(new BorderLayout(0, 0));
		this.add(text);
	}

	//Introducira el unico valor de la casilla
	@Override
	public void escribirTexto(String pTexto) {
		if(pTexto.charAt(0)=='0') this.text.setText(" ");
		else this.text.setText(pTexto);
	}
}
