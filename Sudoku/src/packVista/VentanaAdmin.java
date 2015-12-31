package packVista;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import packControladores.GestorSesion;
import packExcepciones.ExcepcionConectarBD;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class VentanaAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAdminSud;
	private JButton btnAdminLogros;
	private JButton btnEstadisticas;
	private JLabel lblHolaAdministrador;
	private static VentanaAdmin ventana;
	private Controlador controlador;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getVentana().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static VentanaAdmin getVentana(){
		if(ventana==null){
			ventana=new VentanaAdmin();
		}
		return ventana;
	}
	private VentanaAdmin(){
		initialize();
	}
	private void initialize() {
		setTitle("Men\u00FA administrador");
		addWindowListener(getControlador());
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAdminSud());
		contentPane.add(getBtnAdminLogros());
		contentPane.add(getBtnEstadisticas());
		contentPane.add(getLblHolaAdministrador());
	}
	private JButton getBtnAdminSud() {
		if (btnAdminSud == null) {
			btnAdminSud = new JButton("Administrar Sudokus");
			btnAdminSud.setActionCommand("PRESS_btnAdminSud");
			btnAdminSud.addActionListener(getControlador());
			btnAdminSud.setBounds(129, 83, 200, 23);
		}
		return btnAdminSud;
	}
	private JButton getBtnAdminLogros() {
		if (btnAdminLogros == null) {
			btnAdminLogros = new JButton("Administrar Logros");
			btnAdminLogros.setActionCommand("PRESS_btnAdminLogros");
			btnAdminLogros.addActionListener(getControlador());
			btnAdminLogros.setBounds(129, 136, 200, 23);
		}
		return btnAdminLogros;
	}
	private JButton getBtnEstadisticas() {
		if (btnEstadisticas == null) {
			btnEstadisticas = new JButton("Estad\u00EDsticas");
			btnEstadisticas.setActionCommand("PRESS_btnEstadisticas");
			btnEstadisticas.addActionListener(getControlador());
			btnEstadisticas.setBounds(129, 187, 200, 23);
			
		}
		return btnEstadisticas;
	}
	private JLabel getLblHolaAdministrador() {
		if (lblHolaAdministrador == null) {
			lblHolaAdministrador = new JLabel("Hola Administrador");
			lblHolaAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 27));
			lblHolaAdministrador.setBounds(84, 11, 237, 50);
		}
		return lblHolaAdministrador;
	}
	
	private Controlador getControlador(){
		if(controlador==null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener{

		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
			GestorSesion.getGestor().cerrarSesion();
			VentanaInicio.getVentanaInicio().setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("PRESS_btnAdminSud")){
				
			} else if (e.getActionCommand().equals("PRESS_btnAdminLogros")){
				try {
					VentanaAdminLogros.getVentana().setVisible(true);
					VentanaAdmin.getVentana().setVisible(false);
				} catch (ExcepcionConectarBD e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			} else if (e.getActionCommand().equals("PRESS_btnEstadisticas")){
				VentanaEstadisticasAdministrador.getVentana().setVisible(true);
				VentanaAdmin.getVentana().setVisible(false);
			}
		}
		
	}
}
