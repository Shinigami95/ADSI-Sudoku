package packVista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaExito extends JDialog {
	private JPanel panel;
	private JPanel panel_1;
	private JTextArea textError;
	private JButton btnOK;
	private Controlador controlador;

	/**
	 * Create the dialog.
	 */
	public VentanaExito(String pError) {
		initialize(pError);
	}
	private void initialize(String pError) {
		setResizable(false);
		setTitle("EXITO");
		setModal(true);
		setBounds(100, 100, 398, 150);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		getContentPane().add(getPanel_1(), BorderLayout.SOUTH);
		getTextArea().setText(pError);
		addWindowListener(getControlador());
		this.setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(51)
						.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(54, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(30)
						.addComponent(getTextArea(), GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getBtnOK());
		}
		return panel_1;
	}
	private JTextArea getTextArea() {
		if (textError == null) {
			textError = new JTextArea();
			textError.setWrapStyleWord(true);
			textError.setLineWrap(true);
			textError.setEditable(false);
			textError.setOpaque(false);
		}
		return textError;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setActionCommand("OK");
			btnOK.addActionListener(getControlador());
		}
		return btnOK;
	}
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener {

		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
		}

		public void actionPerformed(ActionEvent arg0){		
			if(arg0.getActionCommand().equals("OK")){
				dispose();
			}
		}
	}
}
