package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ComponentCasillaInicial  extends ComponentCasillaGenerica{

	private static final long serialVersionUID = 1L;
	JLabel text;
	
	public ComponentCasillaInicial(){
		text = new JLabel();
		text.setMaximumSize(new Dimension(50, 50));
		text.setMinimumSize(new Dimension(50, 50));
		text.setSize(new Dimension(50, 50));
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("Helvetica", Font.BOLD, 16));
		text.setForeground(Color.BLACK);
		text.setFocusable(false);
		text.setText("-");
		text.setBorder(new LineBorder(new Color(0,0,0),1));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.WHITE);
		this.add(text);
	}

	@Override
	public void escribirTexto(String pTexto) {
		this.text.setText(pTexto);
	}
}
