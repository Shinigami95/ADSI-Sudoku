package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaHistorial extends JFrame {

	private static VentanaHistorial mVHistorial;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnVolver_2;
	private JTextArea textArea_2;
	private JButton btnVolver_1;
	private JTextArea textArea_1;
	private JButton btnVolver;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHistorial frame = new VentanaHistorial();
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
	public VentanaHistorial() {
		initialize();
	}
	
	public static VentanaHistorial getVentanaHistorial(){
		if(mVHistorial==null){
			mVHistorial = new VentanaHistorial();
		}
		return mVHistorial;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Historial");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(getTabbedPane());
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Sudokus", null, getPanel(), null);
			tabbedPane.addTab("Retos", null, getPanel_1(), null);
			tabbedPane.addTab("Logros", null, getPanel_2(), null);
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
							.addContainerGap(330, Short.MAX_VALUE)
							.addComponent(getBtnVolver())
							.addContainerGap())
						.addComponent(getTextArea(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
				);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(getBtnVolver()))
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
						.addContainerGap(330, Short.MAX_VALUE)
						.addComponent(getBtnVolver_1())
						.addContainerGap())
					.addComponent(getTextArea_1(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
						.addComponent(getTextArea_1(), GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(getBtnVolver_1()))
			);
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			GroupLayout gl_panel_2 = new GroupLayout(panel_2);
			gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap(330, Short.MAX_VALUE)
						.addComponent(getBtnVolver_2())
						.addContainerGap())
					.addComponent(getTextArea_2(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
			);
			gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
						.addComponent(getTextArea_2(), GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(getBtnVolver_2()))
			);
			panel_2.setLayout(gl_panel_2);
		}
		return panel_2;
	}
	private JButton getBtnVolver_2() {
		if (btnVolver_2 == null) {
			btnVolver_2 = new JButton("Volver");
		}
		return btnVolver_2;
	}
	private JTextArea getTextArea_2() {
		if (textArea_2 == null) {
			textArea_2 = new JTextArea();
			textArea_2.setEditable(false);
		}
		return textArea_2;
	}
	private JButton getBtnVolver_1() {
		if (btnVolver_1 == null) {
			btnVolver_1 = new JButton("Volver");
		}
		return btnVolver_1;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setEditable(false);
		}
		return textArea_1;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
		}
		return btnVolver;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
}
