package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ComponentCasillaNormal extends ComponentCasillaAbstracta{

	private static final long serialVersionUID = 1L;
	JTextField text;
	
	public ComponentCasillaNormal(JPopupMenu pPopupMenu){
		text = new JTextField();
		text.setMaximumSize(new Dimension(50, 50));
		text.setMinimumSize(new Dimension(50, 50));
		text.setSize(new Dimension(50, 50));
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("Helvetica", Font.PLAIN, 16));
		text.setComponentPopupMenu(pPopupMenu);
		text.setFocusable(false);
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.WHITE);
		this.add(text);
	}

}
