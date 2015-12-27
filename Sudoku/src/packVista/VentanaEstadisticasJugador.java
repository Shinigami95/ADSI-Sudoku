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

public class VentanaEstadisticasJugador extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPaneSelectSudoku;
	private JPanel panelDatosJugador;
	private JPanel panelDatosSudoku;
	private JLabel lblResPorCat;
	private JLabel lblFaciles;
	private JLabel lblNormales;
	private JLabel lblDificiles;
	private JSeparator separator;
	private JLabel lblNumSudokusResueltos;
	private JSeparator separator_1;
	private JLabel lblNumFacil;
	private JLabel lblNumNormal;
	private JLabel lblNumDificil;
	private JLabel lblNumRes;
	private JLabel lblPorcentajeResueltos;
	private JLabel lblPorcentRes;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelDatosJugador(), BorderLayout.WEST);
		contentPane.add(getScrollPaneSelectSudoku(), BorderLayout.CENTER);
		contentPane.add(getPanelDatosSudoku(), BorderLayout.EAST);
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
			panelDatosJugador.setLayout(new BoxLayout(panelDatosJugador, BoxLayout.Y_AXIS));
			panelDatosJugador.add(getLblResPorCat());
			panelDatosJugador.add(getLblFaciles());
			panelDatosJugador.add(getLblNumFacil());
			panelDatosJugador.add(getLblNormales());
			panelDatosJugador.add(getLblNumNormal());
			panelDatosJugador.add(getLblDificiles());
			panelDatosJugador.add(getLblNumDificil());
			panelDatosJugador.add(getSeparator());
			panelDatosJugador.add(getLblNumSudokusResueltos());
			panelDatosJugador.add(getLblNumRes());
			panelDatosJugador.add(getLblPorcentajeResueltos());
			panelDatosJugador.add(getLblPorcentRes());
			panelDatosJugador.add(getSeparator_1());
		}
		return panelDatosJugador;
	}
	private JPanel getPanelDatosSudoku() {
		if (panelDatosSudoku == null) {
			panelDatosSudoku = new JPanel();
		}
		return panelDatosSudoku;
	}
	private JLabel getLblResPorCat() {
		if (lblResPorCat == null) {
			lblResPorCat = new JLabel("Resoluci\u00F3n por categor\u00EDa: ");
		}
		return lblResPorCat;
	}
	private JLabel getLblFaciles() {
		if (lblFaciles == null) {
			lblFaciles = new JLabel("F\u00E1ciles:");
		}
		return lblFaciles;
	}
	private JLabel getLblNormales() {
		if (lblNormales == null) {
			lblNormales = new JLabel("Normales:");
		}
		return lblNormales;
	}
	private JLabel getLblDificiles() {
		if (lblDificiles == null) {
			lblDificiles = new JLabel("Dificiles:");
		}
		return lblDificiles;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JLabel getLblNumSudokusResueltos() {
		if (lblNumSudokusResueltos == null) {
			lblNumSudokusResueltos = new JLabel("Num. sudokus resueltos: ");
		}
		return lblNumSudokusResueltos;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JLabel getLblNumFacil() {
		if (lblNumFacil == null) {
			lblNumFacil = new JLabel("<NumFacil>");
		}
		return lblNumFacil;
	}
	private JLabel getLblNumNormal() {
		if (lblNumNormal == null) {
			lblNumNormal = new JLabel("<NumNormal>");
		}
		return lblNumNormal;
	}
	private JLabel getLblNumDificil() {
		if (lblNumDificil == null) {
			lblNumDificil = new JLabel("<NumDif\u00EDcil>");
		}
		return lblNumDificil;
	}
	private JLabel getLblNumRes() {
		if (lblNumRes == null) {
			lblNumRes = new JLabel("<NumRes>");
		}
		return lblNumRes;
	}
	private JLabel getLblPorcentajeResueltos() {
		if (lblPorcentajeResueltos == null) {
			lblPorcentajeResueltos = new JLabel("Porcentaje resueltos: ");
		}
		return lblPorcentajeResueltos;
	}
	private JLabel getLblPorcentRes() {
		if (lblPorcentRes == null) {
			lblPorcentRes = new JLabel("<NumPorcentRes>");
		}
		return lblPorcentRes;
	}
}
