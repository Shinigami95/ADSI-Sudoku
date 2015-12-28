package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import packControladores.GestorEstadisticas;
import packControladores.GestorJugadores;
import packControladores.GestorSudokus;
import packExcepciones.ExcepcionConectarBD;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaEstadisticasAdministrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane textPaneDatosJugador;
	private JTextPane textPaneDatosSudoku;
	private JLabel lblSudokus;
	private JPanel panelSelectSudoku;
	private JList<String> listSudokus;
	private DefaultListModel<String> defListModelSudokus;
	private DefaultListModel<String> defListModelJugadores;
	private Controlador controlador;
	private JButton btnVerEstSudoku;
	private static VentanaEstadisticasAdministrador mVent = null;
	private JPanel panelDatosJugador;
	private JPanel panelDatosSudoku;
	private JPanel panelSelectJugador;
	private JLabel lblJugadores;
	private JButton btnVerEstJug;
	private JList<String> listJugadores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstadisticasAdministrador frame = VentanaEstadisticasAdministrador.getVentana();
					VentanaEstadisticasAdministrador.getVentana().cargarDatos(); // cargar datos
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
	private VentanaEstadisticasAdministrador() {
		initialize();
	}
	
	public static VentanaEstadisticasAdministrador getVentana(){
		if(mVent==null){
			mVent = new VentanaEstadisticasAdministrador();
		}
		return mVent;
	}
	
	private void cargarDatos(){
		try {
			this.setTitle("Estad\u00EDsticas administrador");
			this.cargarJugadoresEnLista();
			this.cargarSudokusEnLista();
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private void cargarJugadoresEnLista() throws ExcepcionConectarBD{
		String[] lista = GestorJugadores.getGestor().getJugadores();
		for(int i = 0; i<lista.length; i++){
			getDefListModelJugadores().addElement(lista[i]);
		}
		getListJugadores().setSelectedIndex(0);
	}
	
	private void cargarSudokusEnLista() throws ExcepcionConectarBD{
		String[] lista = GestorSudokus.getGestor().getSudokus();
		for(int i = 0; i<lista.length; i++){
			getDefListModelSudokus().addElement(lista[i]);
		}
		getListSudokus().setSelectedIndex(0);
	}
	
	public void cargarDatosSudoku(String pIdSudoku){
		try {
			String datosSud = GestorEstadisticas.getGestor().getHTMLEstadisticasSudoku(pIdSudoku);
			this.getTextPaneDatosSudoku().setText(datosSud);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	public void cargarDatosJugador(String pNombre){
		try {
			String datosSud = GestorEstadisticas.getGestor().getHTMLEstadisticasJugador(pNombre);
			this.getTextPaneDatosJugador().setText(datosSud);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 430);
		setMinimumSize(new Dimension(790, 430));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelDatosJugador(), BorderLayout.WEST);
		contentPane.add(getPanelDatosSudoku(), BorderLayout.EAST);
	}
	
	private JTextPane getTextPaneDatosJugador() {
		if (textPaneDatosJugador == null) {
			textPaneDatosJugador = new JTextPane();
			textPaneDatosJugador.setEditable(false);
			textPaneDatosJugador.setContentType("text/html");
			textPaneDatosJugador.setText("Estad\u00EDsticas del\r\njugador de la sesi\u00F3n ");
			textPaneDatosJugador.setBackground(Color.WHITE);
		}
		return textPaneDatosJugador;
	}

	private JTextPane getTextPaneDatosSudoku() {
		if (textPaneDatosSudoku == null) {
			textPaneDatosSudoku = new JTextPane();
			textPaneDatosSudoku.setContentType("text/html");
			textPaneDatosSudoku.setEditable(false);
			textPaneDatosSudoku.setText("Estad\u00EDsticas del\r\nsudoku elegido");
			textPaneDatosSudoku.setBackground(Color.WHITE);
		}
		return textPaneDatosSudoku;
	}
	
	private JLabel getLblSudokus() {
		if (lblSudokus == null) {
			lblSudokus = new JLabel("   Sudokus: ");
			lblSudokus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblSudokus;
	}
	
	private JPanel getPanelSelectSudoku() {
		if (panelSelectSudoku == null) {
			panelSelectSudoku = new JPanel();
			panelSelectSudoku.setBackground(Color.WHITE);
			panelSelectSudoku.setLayout(new BorderLayout(0, 0));
			panelSelectSudoku.add(getLblSudokus(), BorderLayout.NORTH);
			panelSelectSudoku.add(getListSudokus(), BorderLayout.CENTER);
			panelSelectSudoku.add(getBtnVerEstSudoku(), BorderLayout.SOUTH);
		}
		return panelSelectSudoku;
	}
	
	private DefaultListModel<String> getDefListModelSudokus(){
		if (defListModelSudokus == null) {
			defListModelSudokus = new DefaultListModel<String>();
		}
		return defListModelSudokus;
	}
	
	private DefaultListModel<String> getDefListModelJugadores(){
		if (defListModelJugadores == null) {
			defListModelJugadores = new DefaultListModel<String>();
		}
		return defListModelJugadores;
	}
	
	private JList<String> getListSudokus() {
		if (listSudokus == null) {
			listSudokus = new JList<String>(getDefListModelSudokus());
			listSudokus.setBorder(new LineBorder(getForeground(), 2));
			listSudokus.setName("List_Sudokus");
			listSudokus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSudokus.addListSelectionListener(getControlador());
		}
		return listSudokus;
	}
	
	private JButton getBtnVerEstSudoku() {
		if (btnVerEstSudoku == null) {
			btnVerEstSudoku = new JButton("Ver estad\u00EDsticas");
			btnVerEstSudoku.setActionCommand("PRESS_btnVerEstSud");
			btnVerEstSudoku.addActionListener(getControlador());
		}
		return btnVerEstSudoku;
	}
	
	private Controlador getControlador(){
		if(controlador==null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private JPanel getPanelDatosJugador() {
		if (panelDatosJugador == null) {
			panelDatosJugador = new JPanel();
			panelDatosJugador.setBackground(Color.WHITE);
			panelDatosJugador.setLayout(new BorderLayout(0, 0));
			panelDatosJugador.add(getPanelSelectJugador(), BorderLayout.WEST);
			panelDatosJugador.add(getTextPaneDatosJugador(), BorderLayout.CENTER);
		}
		return panelDatosJugador;
	}
	
	private JPanel getPanelDatosSudoku() {
		if (panelDatosSudoku == null) {
			panelDatosSudoku = new JPanel();
			panelDatosSudoku.setBackground(Color.WHITE);
			panelDatosSudoku.setLayout(new BorderLayout(0, 0));
			panelDatosSudoku.add(getPanelSelectSudoku(), BorderLayout.WEST);
			panelDatosSudoku.add(getTextPaneDatosSudoku());
		}
		return panelDatosSudoku;
	}
	
	private JPanel getPanelSelectJugador() {
		if (panelSelectJugador == null) {
			panelSelectJugador = new JPanel();
			panelSelectJugador.setBackground(Color.WHITE);
			panelSelectJugador.setLayout(new BorderLayout(0, 0));
			panelSelectJugador.add(getLblJugadores(), BorderLayout.NORTH);
			panelSelectJugador.add(getListJugadores(), BorderLayout.CENTER);
			panelSelectJugador.add(getBtnVerEstJug(), BorderLayout.SOUTH);
		}
		return panelSelectJugador;
	}
	private JLabel getLblJugadores() {
		if (lblJugadores == null) {
			lblJugadores = new JLabel("   Jugadores: ");
			lblJugadores.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblJugadores;
	}
	private JButton getBtnVerEstJug() {
		if (btnVerEstJug == null) {
			btnVerEstJug = new JButton("Ver estad\u00EDsticas");
			btnVerEstJug.setActionCommand("PRESS_btnVerEstJug");
			btnVerEstJug.addActionListener(getControlador());
		}
		return btnVerEstJug;
	}
	private JList<String> getListJugadores() {
		if (listJugadores == null) {
			listJugadores = new JList<String>(getDefListModelJugadores());
			listJugadores.setBorder(new LineBorder(getForeground(), 2));
			listJugadores.setName("List_Jugadores");
			listJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listJugadores.addListSelectionListener(getControlador());
		}
		return listJugadores;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener,ListSelectionListener{
		private String selItemSud = "";
		private String selItemJug = "";
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("PRESS_btnVerEstSud")){
				VentanaEstadisticasAdministrador.getVentana().cargarDatosSudoku(selItemSud);
			} else if(e.getActionCommand().equals("PRESS_btnVerEstJug")){
				VentanaEstadisticasAdministrador.getVentana().cargarDatosJugador(selItemJug);
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() instanceof JList){
				JList<String> list = (JList<String>)e.getSource();
				if(list.getName().equals("List_Sudokus")){
					selItemSud = list.getSelectedValue();
				} else if(list.getName().equals("List_Jugadores")){
					selItemJug = list.getSelectedValue();
				}
			}
		}
	}
}
