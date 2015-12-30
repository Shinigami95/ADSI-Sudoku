package packVista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import packControladores.GestorJugadores;
import packExcepciones.ExcepcionConectarBD;

public class VentanaRegistro extends JDialog {

	private JPanel contentPane;
	private JLabel lblUsuario;
	private JTextField textUsuario;
	private JLabel lblPass;
	private JPasswordField textPass;
	private JLabel lblRepPass;
	private JPasswordField textRepPass;
	private JLabel lblPregunta;
	private JTextField textPregunta;
	private JLabel lblRespuesta;
	private JTextField textRespuesta;
	private JPanel panelOK;
	private JButton btnOK;
	private JPanel panel;
	private JButton btnCancel;
	private ControladorRegistro controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		initialize();
	}
	private void initialize() {
		setTitle("Registro");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 15));
		contentPane.add(getLblUsuario());
		contentPane.add(getTextUsuario());
		contentPane.add(getLblPass());
		contentPane.add(getTextPass());
		contentPane.add(getLblRepPass());
		contentPane.add(getTextRepPass());
		contentPane.add(getLblPregunta());
		contentPane.add(getTextPregunta());
		contentPane.add(getLblRespuesta());
		contentPane.add(getTextRespuesta());
		contentPane.add(getPanelOK());
		contentPane.add(getPanel());
		addWindowListener(getControlador());
		this.setVisible(true);
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario");
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
	private JLabel getLblPass() {
		if (lblPass == null) {
			lblPass = new JLabel("Password");
		}
		return lblPass;
	}
	private JPasswordField getTextPass() {
		if (textPass == null) {
			textPass = new JPasswordField();
			textPass.setColumns(10);
		}
		return textPass;
	}
	private JLabel getLblRepPass() {
		if (lblRepPass == null) {
			lblRepPass = new JLabel("Repite password");
		}
		return lblRepPass;
	}
	private JPasswordField getTextRepPass() {
		if (textRepPass == null) {
			textRepPass = new JPasswordField();
			textRepPass.setColumns(10);
		}
		return textRepPass;
	}
	private JLabel getLblPregunta() {
		if (lblPregunta == null) {
			lblPregunta = new JLabel("Pregunta de seguridad");
		}
		return lblPregunta;
	}
	private JTextField getTextPregunta() {
		if (textPregunta == null) {
			textPregunta = new JTextField();
			textPregunta.setColumns(10);
		}
		return textPregunta;
	}
	private JLabel getLblRespuesta() {
		if (lblRespuesta == null) {
			lblRespuesta = new JLabel("Respuesta");
		}
		return lblRespuesta;
	}
	private JTextField getTextRespuesta() {
		if (textRespuesta == null) {
			textRespuesta = new JTextField();
			textRespuesta.setColumns(10);
		}
		return textRespuesta;
	}
	private JPanel getPanelOK() {
		if (panelOK == null) {
			panelOK = new JPanel();
			panelOK.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelOK.add(getBtnOK());
		}
		return panelOK;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setActionCommand("OK");
			btnOK.addActionListener(getControlador());
		}
		return btnOK;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnCancel());
		}
		return panel;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setActionCommand("Cancel");
			btnCancel.addActionListener(getControlador());
		}
		return btnCancel;
	}
	
	private ControladorRegistro getControlador() {
		if (controlador == null) {
			controlador = new ControladorRegistro();
		}
		return controlador;
	}
	
	private class ControladorRegistro extends WindowAdapter implements ActionListener {

		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
		}

		public void actionPerformed(ActionEvent arg0){
			
			if(arg0.getActionCommand().equals("OK")){
				if(textUsuario.getText().length()<1){
					new VentanaError("Introduzca un nombre de usuario.");
				}
				else if(textPass.getPassword().length<1){
					new VentanaError("Introduzca una password.");
				}
				else if(textRepPass.getPassword().length<1){
					new VentanaError("Repite la password.");
				}
				//comprobar si las password coinciden, getPassword devuelve char[]
				else if(String.valueOf(textPass.getPassword()).compareTo(String.valueOf(textRepPass.getPassword()))!=0){
					new VentanaError("Las passwords no coinciden");
				}
				else if(textPregunta.getText().length()<1){
					new VentanaError("Debes introducir una pregunta de seguridad.");
				}
				else if(textRespuesta.getText().length()<1){
					new VentanaError("Debes introducir una respuesta de seguridad.");
				}
				else{
					String pass= new String(textPass.getPassword());
					try{
						String nombre=GestorJugadores.getGestor().registrarJugador(textUsuario.getText(), pass, textPregunta.getText(), textRespuesta.getText());
						if(nombre.length()>0){
							dispose();
							new VentanaExito("Bienvenido "+nombre+".");
						}
						else{
							new VentanaError("Ya existe un usuario con ese nombre.");
						}
					}
					catch(ExcepcionConectarBD e){
						System.out.println(e.getMessage());
					}
				}
			}
			else if(arg0.getActionCommand().equals("Cancel")){
				dispose();
			}
		}
	}
}
