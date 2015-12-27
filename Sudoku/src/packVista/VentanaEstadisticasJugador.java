package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Color;

public class VentanaEstadisticasJugador extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPaneSelectSudoku;
	private JPanel panelDatosJugador;
	private JPanel panelDatosSudoku;
	private JTextArea txtrEstadsticasJugador;
	private JTextArea txtrEstadsticasDelSudoku;
	private JPanel panelSelectSudoku;
	private JLabel lblNewLabel;

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
	public VentanaEstadisticasJugador() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelDatosJugador(), BorderLayout.WEST);
		contentPane.add(getPanelDatosSudoku(), BorderLayout.EAST);
		contentPane.add(getPanelSelectSudoku(), BorderLayout.CENTER);
	}
	private JScrollPane getScrollPaneSelectSudoku() {
		if (scrollPaneSelectSudoku == null) {
			scrollPaneSelectSudoku = new JScrollPane();
		}
		return scrollPaneSelectSudoku;
	}
	private JPanel getPanelDatosJugador() {
		if (panelDatosJugador == null) {
			panelDatosJugador = new JPanel();
			panelDatosJugador.setBackground(Color.WHITE);
			panelDatosJugador.setLayout(new BoxLayout(panelDatosJugador, BoxLayout.Y_AXIS));
			panelDatosJugador.add(getTxtrEstadsticasJugador());
		}
		return panelDatosJugador;
	}
	private JPanel getPanelDatosSudoku() {
		if (panelDatosSudoku == null) {
			panelDatosSudoku = new JPanel();
			panelDatosSudoku.setBackground(Color.WHITE);
			panelDatosSudoku.add(getTxtrEstadsticasDelSudoku());
		}
		return panelDatosSudoku;
	}
	private JTextArea getTxtrEstadsticasJugador() {
		if (txtrEstadsticasJugador == null) {
			txtrEstadsticasJugador = new JTextArea();
			txtrEstadsticasJugador.setText("Estad\u00EDsticas del\r\njugador de la sesi\u00F3n ");
		}
		return txtrEstadsticasJugador;
	}
	private JTextArea getTxtrEstadsticasDelSudoku() {
		if (txtrEstadsticasDelSudoku == null) {
			txtrEstadsticasDelSudoku = new JTextArea();
			txtrEstadsticasDelSudoku.setText("Estad\u00EDsticas del \r\nsudoku seleccionado ");
		}
		return txtrEstadsticasDelSudoku;
	}
	private JPanel getPanelSelectSudoku() {
		if (panelSelectSudoku == null) {
			panelSelectSudoku = new JPanel();
			panelSelectSudoku.setBackground(Color.WHITE);
			panelSelectSudoku.setLayout(new BorderLayout(0, 0));
			panelSelectSudoku.add(getScrollPaneSelectSudoku(), BorderLayout.CENTER);
			panelSelectSudoku.add(getLblNewLabel(), BorderLayout.NORTH);
		}
		return panelSelectSudoku;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Elige sudoku: ");
		}
		return lblNewLabel;
	}
}
