package packVista;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


public class VentanaRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaRanking mVHistorial;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private Controlador controlador;
	private JScrollPane scrollPane;
	private JPanel panel_3;
	private JList list;
	private JLabel lblidSudoku;
	private JLabel lblidSudoku2;
	private JLabel lblDificultad;
	private JLabel lblDificultad2;
	private JLabel lblActivo;
	private JLabel lblActivo2;
	private JLabel lblEstasEnLaPos;
	private JLabel label_1;
	private JLabel lblRanking;
	private JLabel label_2;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JButton btnVolver;
	private JButton button;
	private JButton button_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRanking frame = new VentanaRanking();
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
	public VentanaRanking() {
		initialize();
	}
	
	public static VentanaRanking getVentana(){
		if(mVHistorial==null){
			mVHistorial = new VentanaRanking();
		}
		return mVHistorial;
	}
	
	private void initialize() {
		addWindowListener(getControlador());
		setBounds(100, 100, 600, 600);
		setTitle("Ranking");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(getTabbedPane());
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Puntuacion", null, getPanel(), null);
			tabbedPane.addTab("Retos", null, getPanel_1(), null);
			tabbedPane.addTab("Un sudoku", null, getPanel_2(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(getTextArea_1(), GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap(468, Short.MAX_VALUE)
						.addComponent(getBtnVolver(), GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(getTextArea_1(), GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
						.addComponent(getBtnVolver())
						.addContainerGap())
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(getTextArea(), GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
							.addComponent(getBtnVolver_1(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
						.addComponent(getBtnVolver_1())
						.addContainerGap())
			);
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getScrollPane(), BorderLayout.WEST);
			panel_2.add(getPanel_3(), BorderLayout.CENTER);
		}
		return panel_2;
	}
	private Controlador getControlador(){
		if(controlador==null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			VentanaJugador.getVentana().setVisible(true);
			VentanaRanking.getVentana().setVisible(false);
		}
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.add(getLblidSudoku());
			panel_3.add(getLblidSudoku2());
			panel_3.add(getLblDificultad());
			panel_3.add(getLblDificultad2());
			panel_3.add(getLblActivo());
			panel_3.add(getLblActivo2());
			panel_3.add(getLblEstasEnLaPos());
			panel_3.add(getLabel_1());
			panel_3.add(getLblRanking());
			panel_3.add(getLabel_2());
			panel_3.add(getBtnVolver_2());
		}
		return panel_3;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
		}
		return list;
	}
	private JLabel getLblidSudoku() {
		if (lblidSudoku == null) {
			lblidSudoku = new JLabel("Sudoku:");
			lblidSudoku.setBounds(10, 11, 49, 14);
		}
		return lblidSudoku;
	}
	private JLabel getLblidSudoku2() {
		if (lblidSudoku2 == null) {
			lblidSudoku2 = new JLabel("");
			lblidSudoku2.setBounds(69, 11, 46, 14);
		}
		return lblidSudoku2;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			lblDificultad = new JLabel("Dificultad:");
			lblDificultad.setBounds(10, 40, 62, 14);
		}
		return lblDificultad;
	}
	private JLabel getLblDificultad2() {
		if (lblDificultad2 == null) {
			lblDificultad2 = new JLabel("");
			lblDificultad2.setBounds(79, 40, 46, 14);
		}
		return lblDificultad2;
	}
	private JLabel getLblActivo() {
		if (lblActivo == null) {
			lblActivo = new JLabel("Activo:");
			lblActivo.setBounds(10, 69, 39, 14);
		}
		return lblActivo;
	}
	private JLabel getLblActivo2() {
		if (lblActivo2 == null) {
			lblActivo2 = new JLabel("");
			lblActivo2.setBounds(48, 69, 46, 14);
		}
		return lblActivo2;
	}
	private JLabel getLblEstasEnLaPos() {
		if (lblEstasEnLaPos == null) {
			lblEstasEnLaPos = new JLabel("Est\u00E1s en la posici\u00F3n:");
			lblEstasEnLaPos.setBounds(10, 98, 115, 14);
		}
		return lblEstasEnLaPos;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setBounds(135, 98, 46, 14);
		}
		return label_1;
	}
	private JLabel getLblRanking() {
		if (lblRanking == null) {
			lblRanking = new JLabel("Ranking");
			lblRanking.setBounds(10, 127, 46, 14);
		}
		return lblRanking;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			label_2.setBounds(10, 157, 214, 148);
		}
		return label_2;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
		}
		return textArea_1;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
		}
		return btnVolver;
	}
	private JButton getBtnVolver_1() {
		if (button == null) {
			button = new JButton("Volver");
		}
		return button;
	}
	private JButton getBtnVolver_2() {
		if (button_1 == null) {
			button_1 = new JButton("Volver");
			button_1.setBounds(268, 490, 91, 23);
		}
		return button_1;
	}
}
