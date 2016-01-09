package packVista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import packControladores.GestorJugadores;

public class VentanaRecuperarPass1 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textUsuario;
	private JLabel lblUsuario;
	private JButton btnOK;
	private JButton btnCancel;
	private ControladorRecuperar controlador;


	public VentanaRecuperarPass1() {
		initialize();
	}
	private void initialize() {
		setModal(true);
		setResizable(false);
		setTitle("Recuperar password");
		setBounds(100, 100, 375, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 35));
		contentPanel.add(getLblUsuario());
		contentPanel.add(getTextUsuario());
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.add(getBtnCancel());
		buttonPane.add(getBtnOK());
		addWindowListener(getControladorRecuperar());
		this.setVisible(true);
	}
	
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setToolTipText("Introduzca el usuario, para recuperar su contraseña.");
		}
		return lblUsuario;
	}
	
	private JTextField getTextUsuario() {
		if (textUsuario == null) {
			textUsuario = new JTextField();
			textUsuario.setColumns(10);
		}
		return textUsuario;
	}
	
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setActionCommand("OK");
			btnOK.addActionListener(getControladorRecuperar());
		}
		return btnOK;
	}
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setActionCommand("Cancel");
			btnCancel.addActionListener(getControladorRecuperar());
		}
		return btnCancel;
	}
	
	private ControladorRecuperar getControladorRecuperar() {
		if (controlador == null) {
			controlador = new ControladorRecuperar();
		}
		return controlador;
	}
	
	private class ControladorRecuperar extends WindowAdapter implements ActionListener {

		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
		}

		public void actionPerformed(ActionEvent arg0){
			
			if(arg0.getActionCommand().equals("OK")){
				try{
					if(textUsuario.getText().length()==0){
						new VentanaError("Introduzca un usuario.");
					}
					//todo correcto, comprobamos si existe usuario y cogemos la pregunta de seguridad
					else{
						String pregunta = GestorJugadores.getGestor().buscarPreguntaJugador(textUsuario.getText());
						if(pregunta.length()>0){
							dispose();
							//pasamos la pregunta a mostrar y el usuario
							new VentanaRecuperarPass2(pregunta,textUsuario.getText());
						}
						else{
							/*en el registro obligamos a que introduzca una pregunta de seguridad
							si no hay pregunta no existe ese usuario*/
							new VentanaError("No existe ese usuario.");
						}
					}
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if(arg0.getActionCommand().equals("Cancel")){
				dispose();
			}
		}
	}

}
