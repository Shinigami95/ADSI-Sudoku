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

public class VentanaRecuperarPass3 extends JDialog {

	private JPanel contentPane;
	private JLabel lblPass;
	private JPasswordField textPass;
	private JLabel lblRepPass;
	private JPasswordField textRepPass;
	private JPanel panelOK;
	private JButton btnOK;
	private JPanel panel;
	private JButton btnCancel;
	private ControladorRegistro controlador;
	private String usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRecuperarPass3 frame = new VentanaRecuperarPass3("PRUEB");
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
	public VentanaRecuperarPass3(String pUsuario) {
		usuario=pUsuario;
		initialize();
	}
	private void initialize() {
		setTitle("Registro");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 350, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 15));
		contentPane.add(getLblPass());
		contentPane.add(getTextPass());
		contentPane.add(getLblRepPass());
		contentPane.add(getTextRepPass());
		contentPane.add(getPanelOK());
		contentPane.add(getPanel());
		addWindowListener(getControlador());
		this.setVisible(true);
	}
	private JLabel getLblPass() {
		if (lblPass == null) {
			lblPass = new JLabel("Nueva password");
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
				//comprobar si el user ya esta en el sistema
				//comprobar si las password coinciden, getPassword devuelve char[]
				if(textPass.getPassword().length<1){
					new VentanaError("Introduzca una password.");
				}
				else if(textRepPass.getPassword().length<1){
					new VentanaError("Repite la password.");
				}
				else if(String.valueOf(textPass.getPassword()).compareTo(String.valueOf(textRepPass.getPassword()))!=0){
					new VentanaError("Las passwords no coinciden");
				}
				else{
					String pass= new String(textPass.getPassword());
					try{
						GestorJugadores.getGestor().actualizarPass(usuario, pass);
						new VentanaExito("La password ha sido actualizada.");
						dispose();
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
