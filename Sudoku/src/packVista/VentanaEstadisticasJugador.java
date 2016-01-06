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
import packControladores.GestorSesion;
import packControladores.GestorSudokus;
import packExcepciones.ExcepcionConectarBD;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JScrollPane;

public class VentanaEstadisticasJugador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane textPaneDatosJugador;
	private JTextPane textPaneSelectSudoku;
	private JLabel lblNewLabel;
	private JPanel panelSelectSudoku;
	private JList<String> listSudokus;
	private DefaultListModel<String> defListModelSudokus;
	private Controlador controlador;
	private JButton btnVerEst;
	private static VentanaEstadisticasJugador mVent = null;
	private JPanel panelDatosJugador;
	private JPanel panelDatosSudoku;
	private JScrollPane scrollPaneListSud;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstadisticasJugador frame = VentanaEstadisticasJugador.getVentana();
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
	private VentanaEstadisticasJugador() {
		initialize();
	}
	
	public static VentanaEstadisticasJugador getVentana(){
		if(mVent==null){
			mVent = new VentanaEstadisticasJugador();
			mVent.cargarDatos();
		}
		return mVent;
	}
	
	private void cargarDatos(){
		try {
			this.setTitle("Estad\u00EDsticas del jugador: "+ GestorSesion.getGestor().getUserSesion());
			this.getTextPaneDatosJugador().setText(GestorEstadisticas.getGestor().getHTMLEstadisticasJugadorSesion());
			this.cargarSudokusEnLista();
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private void cargarSudokusEnLista() throws ExcepcionConectarBD{
		String[] lista = GestorSudokus.getGestor().getSudokusUsuarioSesion();
		for(int i = 0; i<lista.length; i++){
			getDefListModelSudokus().addElement(lista[i]);
		}
		getListSudokus().setSelectedIndex(0);
	}
	
	private void cargarDatosSudoku(String pIdSudoku){
		try {
			String datosSud = GestorEstadisticas.getGestor().getHTMLEstadisticasSudoku(pIdSudoku);
			this.getPanelDatosSudoku().removeAll();
			this.getTextPaneSelectSudoku().setText(datosSud);
			this.getPanelDatosSudoku().add(this.getTextPaneSelectSudoku());
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		setBounds(100, 100, 700, 430);
		setMinimumSize(new Dimension(700, 430));
		addWindowListener(getControlador());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelSelectSudoku(), BorderLayout.CENTER);
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

	private JTextPane getTextPaneSelectSudoku() {
		if (textPaneSelectSudoku == null) {
			textPaneSelectSudoku = new JTextPane();
			textPaneSelectSudoku.setContentType("text/html");
			textPaneSelectSudoku.setEditable(false);
			textPaneSelectSudoku.setText("Estad\u00EDsticas del\r\nsudoku elegido");
			textPaneSelectSudoku.setBackground(Color.WHITE);
		}
		return textPaneSelectSudoku;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("   Sudokus jugados: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblNewLabel;
	}
	
	private JPanel getPanelSelectSudoku() {
		if (panelSelectSudoku == null) {
			panelSelectSudoku = new JPanel();
			panelSelectSudoku.setBackground(Color.WHITE);
			panelSelectSudoku.setLayout(new BorderLayout(0, 0));
			panelSelectSudoku.add(getLblNewLabel(), BorderLayout.NORTH);
			panelSelectSudoku.add(getBtnVerEst(), BorderLayout.SOUTH);
			panelSelectSudoku.add(getScrollPaneListSud(), BorderLayout.CENTER);
		}
		return panelSelectSudoku;
	}
	
	private DefaultListModel<String> getDefListModelSudokus(){
		if (defListModelSudokus == null) {
			defListModelSudokus = new DefaultListModel<String>();
		}
		return defListModelSudokus;
	}
	
	private JList<String> getListSudokus() {
		if (listSudokus == null) {
			listSudokus = new JList<String>(getDefListModelSudokus());
			listSudokus.setBorder(new LineBorder(getForeground(), 2));
			listSudokus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSudokus.addListSelectionListener(getControlador());
		}
		return listSudokus;
	}
	
	private JButton getBtnVerEst() {
		if (btnVerEst == null) {
			btnVerEst = new JButton("Ver estad\u00EDsticas");
			btnVerEst.setActionCommand("PRESS_btnVerEst");
			btnVerEst.addActionListener(getControlador());
		}
		return btnVerEst;
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
			panelDatosJugador.add(getTextPaneDatosJugador());
		}
		return panelDatosJugador;
	}
	
	private JPanel getPanelDatosSudoku() {
		if (panelDatosSudoku == null) {
			panelDatosSudoku = new JPanel();
			panelDatosSudoku.setBackground(Color.WHITE);
			panelDatosSudoku.add(getTextPaneSelectSudoku());
		}
		return panelDatosSudoku;
	}
	
	private JScrollPane getScrollPaneListSud() {
		if (scrollPaneListSud == null) {
			scrollPaneListSud = new JScrollPane();
			scrollPaneListSud.setViewportView(getListSudokus());
		}
		return scrollPaneListSud;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener,ListSelectionListener{
		private String selItem = "";
		
		@Override
		public void windowClosing(WindowEvent e) {
			VentanaJugador.getVentana().setVisible(true);
			VentanaEstadisticasJugador.getVentana().dispose();
			mVent=null;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("PRESS_btnVerEst")){
				VentanaEstadisticasJugador.getVentana().cargarDatosSudoku(selItem);
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() instanceof JList){
				JList<String> list = (JList<String>)e.getSource();
				selItem = list.getSelectedValue();
			}
		}
	}
}
