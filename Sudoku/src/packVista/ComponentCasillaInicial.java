package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ComponentCasillaInicial  extends ComponentCasillaAbstracta{

	private static final long serialVersionUID = 1L;
	JTextField text;
	
	public ComponentCasillaInicial(JPopupMenu pPopupMenu){
		text = new JTextField();
		text.setMaximumSize(new Dimension(50, 50));
		text.setMinimumSize(new Dimension(50, 50));
		text.setSize(new Dimension(50, 50));
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("Helvetica", Font.BOLD, 16));
		text.setFocusable(false);
		this.setBackground(Color.WHITE);
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
