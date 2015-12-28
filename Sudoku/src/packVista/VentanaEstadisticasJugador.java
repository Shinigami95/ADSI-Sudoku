package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import packControladores.GestorEstadisticas;
import packControladores.GestorSudokus;
import packExcepciones.ExcepcionConectarBD;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class VentanaEstadisticasJugador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPaneSelectSudoku;
	private JTextPane textPaneDatosJugador;
	private JTextPane textPaneSelectSudoku;
	private JLabel lblNewLabel;
	private JPanel panelSelectSudoku;
	private JList<String> listSudokus;
	private DefaultListModel<String> defListModelSudokus;
	private Controlador controlador;
	private JButton btnVerEst;
	private static VentanaEstadisticasJugador mVent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstadisticasJugador frame = new VentanaEstadisticasJugador();
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
		cargarDatos();
	}
	
	public static VentanaEstadisticasJugador getVentana(){
		if(mVent==null){
			mVent = new VentanaEstadisticasJugador();
		}
		return mVent;
	}
	
	private void cargarDatos(){
		try {
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

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 430);
		setMinimumSize(new Dimension(760, 430));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getTextPaneDatosJugador(), BorderLayout.WEST);
		contentPane.add(getTextPaneSelectSudoku(), BorderLayout.EAST);
		contentPane.add(getPanelSelectSudoku(), BorderLayout.CENTER);
	}
	private JScrollPane getScrollPaneSelectSudoku() {
		if (scrollPaneSelectSudoku == null) {
			scrollPaneSelectSudoku = new JScrollPane();
			scrollPaneSelectSudoku.setViewportView(getListSudokus());
		}
		return scrollPaneSelectSudoku;
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
			lblNewLabel = new JLabel("Elige sudoku: ");
		}
		return lblNewLabel;
	}
	private JPanel getPanelSelectSudoku() {
		if (panelSelectSudoku == null) {
			panelSelectSudoku = new JPanel();
			panelSelectSudoku.setBackground(Color.WHITE);
			panelSelectSudoku.setLayout(new BorderLayout(0, 0));
			panelSelectSudoku.add(getLblNewLabel(), BorderLayout.NORTH);
			panelSelectSudoku.add(getScrollPaneSelectSudoku());
			panelSelectSudoku.add(getBtnVerEst(), BorderLayout.SOUTH);
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
			listSudokus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listSudokus;
	}
	private JButton getBtnVerEst() {
		if (btnVerEst == null) {
			btnVerEst = new JButton("Ver estad\u00EDsticas");
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
	private class Controlador extends WindowAdapter implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
