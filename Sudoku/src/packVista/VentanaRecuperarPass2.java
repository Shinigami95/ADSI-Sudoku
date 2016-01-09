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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Color;

public class VentanaRecuperarPass2 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textRespuesta;
	private JLabel lblRespuesta;
	private JButton btnOK;
	private JButton btnCancel;
	private ControladorRecuperar controlador;
	private String usuario;
	private JTextArea textPregunta;


	public VentanaRecuperarPass2(String pPregunta, String pUsuario) {
		usuario=pUsuario;
		initialize(pPregunta);
	}
	private void initialize(String pPregunta) {
		setModal(true);
		setResizable(false);
		setTitle("Recuperar password");
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(111)
							.addComponent(getLblUsuario())
							.addGap(35)
							.addComponent(getTextUsuario(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(33)
							.addComponent(getTextPregunta(), GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(getTextPregunta(), GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(getTextUsuario(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(getLblUsuario()))
					.addGap(23))
		);
		contentPanel.setLayout(gl_contentPanel);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.add(getBtnCancel());
		buttonPane.add(getBtnOK());
		addWindowListener(getControladorRecuperar());
		textPregunta.setText(pPregunta+"?");
		this.setVisible(true);
	}
	
	private JLabel getLblUsuario() {
		if (lblRespuesta == null) {
			lblRespuesta = new JLabel("Respuesta");
			lblRespuesta.setToolTipText("Introduzca la respuesta de seguridad.");
		}
		return lblRespuesta;
	}
	
	private JTextField getTextUsuario() {
		if (textRespuesta == null) {
			textRespuesta = new JTextField();
			textRespuesta.setColumns(10);
		}
		return textRespuesta;
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
	
	private JTextArea getTextPregunta() {
		if (textPregunta == null) {
			textPregunta = new JTextArea();
			textPregunta.setWrapStyleWord(true);
			textPregunta.setForeground(Color.BLACK);
			textPregunta.setLineWrap(true);
			textPregunta.setEditable(false);
			textPregunta.setOpaque(false);
		}
		return textPregunta;
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
					if(textRespuesta.getText().length()==0){
						new VentanaError("Introduzca una respuesta.");
					}
					//todo correcto, comprobamos respuesta
					else{
						if(GestorJugadores.getGestor().comprobarRespuesta(usuario, textRespuesta.getText())){
							dispose();
							//pasamos el usuario
							new VentanaRecuperarPass3(usuario);
						}
						else{
							new VentanaError("Respuesta incorrecta.");
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
