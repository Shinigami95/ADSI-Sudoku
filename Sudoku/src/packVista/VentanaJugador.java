package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;

import packControladores.GestorSesion;
import packControladores.GestorSudokus;
import packExcepciones.ExcepcionConectarBD;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class VentanaJugador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBienvenido;
	private JPanel panelBienvenido;
	private JLabel lblNombreJug;
	private JPanel panelOpciones;
	private JButton btnVerHistorial;
	private JButton btnVerEstadisticas;
	private JButton btnVerRanking;
	private JComboBox<String> comboBoxDif;
	private JLabel lblDificultad;
	private JButton btnJugar;
	private Controlador controlador;
	private static VentanaJugador mVent;
	private JButton btnVerRetos;
	private JButton btnContinuar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJugador frame = VentanaJugador.getVentana();
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
	public VentanaJugador() {
		initialize();
	}
	private void initialize() {
		setTitle("Men\u00FA Jugador");
		addWindowListener(getControlador());
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelBienvenido(), BorderLayout.NORTH);
		contentPane.add(getPanelOpciones(), BorderLayout.CENTER);
	}

	public static VentanaJugador getVentana(){
		if(mVent == null){
			mVent = new VentanaJugador();
			mVent.cargarDatos();
		}
		return mVent;
	}
	private void cargarDatos(){
		String nombre = GestorSesion.getGestor().getUserSesion();
		this.getLblNombreJug().setText(nombre);
		try {
			String[] dificultades = GestorSudokus.getGestor().getDificultades();
			for (int i = 0; i < dificultades.length; i++) {
				this.getComboBoxDif().addItem(dificultades[i]);
			}
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	private Controlador getControlador() {
		if(controlador==null){
			controlador = new Controlador();
		}
		return controlador;
	}

	private JLabel getLblBienvenido() {
		if (lblBienvenido == null) {
			lblBienvenido = new JLabel("Bienvenido: ");
			lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblBienvenido;
	}
	private JPanel getPanelBienvenido() {
		if (panelBienvenido == null) {
			panelBienvenido = new JPanel();
			panelBienvenido.setBackground(Color.WHITE);
			panelBienvenido.add(getLblBienvenido());
			panelBienvenido.setBorder(new LineBorder(Color.BLACK, 2));
			panelBienvenido.add(getLblNombreJug());
		}
		return panelBienvenido;
	}
	private JLabel getLblNombreJug() {
		if (lblNombreJug == null) {
			lblNombreJug = new JLabel("<Nombre>");
			lblNombreJug.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombreJug;
	}
	private JPanel getPanelOpciones() {
		if (panelOpciones == null) {
			panelOpciones = new JPanel();
			panelOpciones.setBackground(Color.WHITE);
			GroupLayout gl_panelOpciones = new GroupLayout(panelOpciones);
			gl_panelOpciones.setHorizontalGroup(
				gl_panelOpciones.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelOpciones.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelOpciones.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(getBtnVerRetos(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(getBtnVerEstadisticas(), Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(getBtnVerRanking(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(getBtnVerHistorial(), Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGap(96)
						.addGroup(gl_panelOpciones.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panelOpciones.createSequentialGroup()
								.addComponent(getLblDificultad())
								.addGap(18)
								.addComponent(getComboBoxDif(), GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addComponent(getBtnJugar(), GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addComponent(getBtnContinuar(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(25))
			);
			gl_panelOpciones.setVerticalGroup(
				gl_panelOpciones.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelOpciones.createSequentialGroup()
						.addGap(31)
						.addGroup(gl_panelOpciones.createParallelGroup(Alignment.LEADING)
							.addComponent(getLblDificultad())
							.addComponent(getBtnVerHistorial())
							.addComponent(getComboBoxDif(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelOpciones.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelOpciones.createSequentialGroup()
								.addGap(7)
								.addComponent(getBtnVerEstadisticas())
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getBtnVerRanking())
								.addGap(29)
								.addComponent(getBtnVerRetos()))
							.addGroup(gl_panelOpciones.createSequentialGroup()
								.addGap(18)
								.addComponent(getBtnJugar())
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getBtnContinuar())))
						.addContainerGap(65, Short.MAX_VALUE))
			);
			panelOpciones.setLayout(gl_panelOpciones);
		}
		return panelOpciones;
	}
	private JButton getBtnVerHistorial() {
		if (btnVerHistorial == null) {
			btnVerHistorial = new JButton("Ver Historial");
			btnVerHistorial.setActionCommand("PRESS_btnVerHistorial");
			btnVerHistorial.addActionListener(getControlador());
		}
		return btnVerHistorial;
	}
	private JButton getBtnVerEstadisticas() {
		if (btnVerEstadisticas == null) {
			btnVerEstadisticas = new JButton("Ver Estad\u00EDsticas");
			btnVerEstadisticas.setActionCommand("PRESS_btnVerEstadisticas");
			btnVerEstadisticas.addActionListener(getControlador());
		}
		return btnVerEstadisticas;
	}
	private JButton getBtnVerRanking() {
		if (btnVerRanking == null) {
			btnVerRanking = new JButton("Ver Ranking");
			btnVerRanking.setActionCommand("PRESS_btnVerRanking");
			btnVerRanking.addActionListener(getControlador());
		}
		return btnVerRanking;
	}
	private JComboBox<String> getComboBoxDif() {
		if (comboBoxDif == null) {
			comboBoxDif = new JComboBox<String>();
		}
		return comboBoxDif;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			lblDificultad = new JLabel("Dificultad:");
		}
		return lblDificultad;
	}
	private JButton getBtnJugar() {
		if (btnJugar == null) {
			btnJugar = new JButton("Jugar");
			btnJugar.setActionCommand("PRESS_btnJugar");
			btnJugar.addActionListener(getControlador());
		}
		return btnJugar;
	}
	
	private JButton getBtnVerRetos() {
		if (btnVerRetos == null) {
			btnVerRetos = new JButton("Ver retos");
			btnVerRetos.setActionCommand("PRESS_btnVerRetos");
			btnVerRetos.addActionListener(getControlador());
		}
		return btnVerRetos;
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
			if(e.getActionCommand().equals("PRESS_btnVerHistorial")){
				try {
					VentanaHistorial.getVentanaHistorial().setVisible(true);
					VentanaJugador.getVentana().setVisible(false);
				} catch (ExcepcionConectarBD e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} else if(e.getActionCommand().equals("PRESS_btnVerEstadisticas")){
				VentanaEstadisticasJugador.getVentana().setVisible(true);
				VentanaJugador.getVentana().setVisible(false);
			} else if(e.getActionCommand().equals("PRESS_btnVerRanking")){
				VentanaRanking.getVentana().setVisible(true);
				VentanaJugador.getVentana().setVisible(false);
			} else if(e.getActionCommand().equals("PRESS_btnVerRetos")){
				
			} else if(e.getActionCommand().equals("PRESS_btnJugar")){
				VentanaJugador.getVentana().jugar();
			}
		}
	}

	public void jugar() {
		//TODO Cargar sudoku para el usuario con la dificultad elegida si es posible
		System.out.println(getComboBoxDif().getSelectedItem());
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
		}
		return btnContinuar;
	}
}
