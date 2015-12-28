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
import packControladores.GestorSesion;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class VentanaRecuperarPass2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textRespuesta;
	private JLabel lblRespuesta;
	private JButton btnOK;
	private JButton btnCancel;
	private ControladorRecuperar controlador;
	private JLabel lblPregunta;
	private String usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaRecuperarPass2 dialog = new VentanaRecuperarPass2("Pregunta de prueba","PRUEB");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
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
							.addGap(90)
							.addComponent(getLblUsuario())
							.addGap(65)
							.addComponent(getTextUsuario(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(69)
							.addComponent(getLblPregunta(), GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(getLblPregunta())
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(getTextUsuario(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(getLblUsuario()))
					.addGap(42))
		);
		contentPanel.setLayout(gl_contentPanel);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.add(getBtnCancel());
		buttonPane.add(getBtnOK());
		addWindowListener(getControladorRecuperar());
		lblPregunta.setText(pPregunta+"?");
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
	
	private JLabel getLblPregunta() {
		if (lblPregunta == null) {
			lblPregunta = new JLabel("New label");
			lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPregunta;
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
					String pregunta = GestorJugadores.getGestor().buscarPreguntaJugador(usuario);
					if(pregunta.length()>0){
						if(GestorJugadores.getGestor().comprobarRespuesta(usuario, textRespuesta.getText())){
							new VentanaRecuperarPass3(usuario);
						}
						else{
							new VentanaError("Respuesta incorrecta.");
						}
					}
					else{
						new VentanaError("No existe ese usuario.");
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
