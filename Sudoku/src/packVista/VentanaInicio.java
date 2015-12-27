package packVista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;

import packControladores.GestorJugadores;

import java.awt.Font;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblPassword;
	private JPasswordField textPass;
	private JButton buttonRecuperar;
	private JLabel lblPregunta;
	private JButton buttonRegistro;
	private JButton buttonSesion;
	private ControladorInicio controlador;
	private static VentanaInicio mVentana = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
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
	private VentanaInicio() {
		initialize();
	}
	
	public static VentanaInicio getVentanaInicio(){
		if(mVentana == null){
			mVentana = new VentanaInicio();
		}
		return mVentana;
	}
	
	private void initialize() {
		setTitle("Juego Sudoku");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(getButtonRecuperar(), GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(getLblPregunta(), GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getButtonRegistro(), GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(getLblNombre(), GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(getLblPassword(), GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(getTextPass())
								.addComponent(getTextNombre())
								.addComponent(getButtonSesion(), GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(getLblNombre(), GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(getLblPassword(), GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(getTextNombre(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(getTextPass(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(getButtonSesion())
					.addGap(67)
					.addComponent(getButtonRecuperar(), GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getLblPregunta(), GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(getButtonRegistro(), GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(222))
		);
		contentPane.setLayout(gl_contentPane);
		addWindowListener(getControlador());
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
		}
		return lblNombre;
	}
	private JTextField getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextField();
			textNombre.setColumns(10);
		}
		return textNombre;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
		}
		return lblPassword;
	}
	private JTextField getTextPass() {
		if (textPass == null) {
			textPass = new JPasswordField();
			textPass.setColumns(10);
		}
		return textPass;
	}
	private JButton getButtonRecuperar() {
		if (buttonRecuperar == null) {
			buttonRecuperar = new JButton("¿No puedes acceder a tu cuenta?");
			buttonRecuperar.setActionCommand("Recuperar");
			buttonRecuperar.addActionListener(getControlador());
			}
		return buttonRecuperar;
	}
	private JLabel getLblPregunta() {
		if (lblPregunta == null) {
			lblPregunta = new JLabel("¿Eres nuevo?");
			lblPregunta.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return lblPregunta;
	}
	private JButton getButtonRegistro() {
		if (buttonRegistro == null) {
			buttonRegistro = new JButton("Regístrese ahora");
			buttonRegistro.setActionCommand("Registro");
			buttonRegistro.addActionListener(getControlador());
		}
		return buttonRegistro;
	}
	private JButton getButtonSesion() {
		if (buttonSesion == null) {
			buttonSesion = new JButton("Iniciar sesión");
			buttonSesion.setActionCommand("Iniciar");
			buttonSesion.addActionListener(getControlador());
		}
		return buttonSesion;
	}
	
	private ControladorInicio getControlador() {
		if (controlador == null) {
			controlador = new ControladorInicio();
		}
		return controlador;
	}
	
	private class ControladorInicio extends WindowAdapter implements ActionListener {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent arg0){
			
			if(arg0.getActionCommand().equals("Iniciar")){
				//comprobar si el user y pass correctos
				//si admin otra ventana
				try{
					String pass= new String(textPass.getPassword());
					String tipoSesion=GestorJugadores.getGestorJugadores().identificarUsuario(textNombre.getText(), pass);
					if(tipoSesion.compareTo("admin")==0){
						//TODO: iniciarSesion y abrir VentanaAdmin
						System.out.println("ADMIN");
					}
					else if(tipoSesion.compareTo("jugador")==0){
						//TODO: iniciarSesion y abrir VentanaJugador
						System.out.println("JUGADOR");
					}
					else{
						new VentanaError("Clave o usuario no correcto.");
					}
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if(arg0.getActionCommand().equals("Recuperar")){
				new VentanaRecuperarPass1();
			}
			else if(arg0.getActionCommand().equals("Registro")){
				new VentanaRegistro();
			}	
		}
	}
}
