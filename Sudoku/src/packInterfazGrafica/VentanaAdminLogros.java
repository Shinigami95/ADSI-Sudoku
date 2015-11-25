package packInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;




import java.awt.FlowLayout;



import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;

import packSudoku.Logros;

public class VentanaAdminLogros extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnEliminar;
	private JPanel panel_5;
	private JTabbedPane tabbedPane_1;
	private JButton btnAadir;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblPuntuacin;
	private JTextField textField_1;
	private JLabel lblNumJugadores;
	private JTextField textField_2;
	private JLabel lblDescripcin_1;
	private JTextField textField_3;
	private JLabel lblCodsudoku;
	private JComboBox comboBox;
	private JLabel lblNivelSudoku;
	private JComboBox comboBox_1;
	private JLabel label;
	private JComboBox comboBox_2;
	private JLabel label_1;
	private JComboBox comboBox_3;
	private JLabel label_2;
	private JTextField textField_4;
	private JLabel label_3;
	private JTextField textField_5;
	private JPanel panel_8;
	private JScrollPane scrollPane_1;
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel lblCodsudoku_1;
	private JLabel lblVarSudoku;
	private JLabel lblDescripcin_2;
	private JLabel lblVarDescripcin;
	private JButton btnModificar;
	private JLabel label_4;
	private JComboBox comboBox_4;
	private JLabel label_5;
	private JComboBox comboBox_5;
	private JLabel label_6;
	private JTextField textField_6;
	private JLabel label_7;
	private JTextField textField_7;
	private JLabel label_8;
	private JTextField textField_8;
	private JLabel label_9;
	private JTextField textField;
	private JLabel label_10;
	private JTextField textField_9;
	private JList list;
	private JScrollPane scrollPane;
	private JPanel panel_4;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JList list_1;
	private JList list_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdminLogros frame = new VentanaAdminLogros();
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
	private VentanaAdminLogros(){
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 361);
		setSize(800,500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTabbedPane(), BorderLayout.CENTER);
		

	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Eliminar", null, getPanel_1(), null);
			tabbedPane.addTab("A\u00F1adir", null, getPanel(), null);
			tabbedPane.addTab("Modificar", null, getPanel_8(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_5(), BorderLayout.SOUTH);
			panel.add(getTabbedPane_1(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getPanel_2(), BorderLayout.SOUTH);
			panel_1.add(getScrollPane(), BorderLayout.WEST);
			panel_1.add(getPanel_4_1(), BorderLayout.CENTER);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getBtnEliminar());
		}
		return panel_2;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
		}
		return btnEliminar;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.add(getBtnAadir());
		}
		return panel_5;
	}
	private JTabbedPane getTabbedPane_1() {
		if (tabbedPane_1 == null) {
			tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane_1.addTab("Puntuaci\u00F3n", null, getPanel_6(), null);
			tabbedPane_1.addTab("Resoluci\u00F3n", null, getPanel_7(), null);
		}
		return tabbedPane_1;
	}
	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
		}
		return btnAadir;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			GroupLayout gl_panel_6 = new GroupLayout(panel_6);
			gl_panel_6.setHorizontalGroup(
				gl_panel_6.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_6.createSequentialGroup()
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGap(95)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_6.createSequentialGroup()
										.addComponent(getLblDescripcin_1())
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getTextField_3(), GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_6.createSequentialGroup()
										.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblPuntuacin())
												.addGap(18)
												.addComponent(getTextField_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblCodsudoku())
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(54)
										.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblNumJugadores())
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getTextField_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblNivelSudoku())
												.addGap(18)
												.addComponent(getComboBox_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(28))))
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGap(251)
								.addComponent(getLabel_9(), GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(getTextField_9(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(149, Short.MAX_VALUE))
			);
			gl_panel_6.setVerticalGroup(
				gl_panel_6.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_6.createSequentialGroup()
						.addGap(13)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addComponent(getTextField_9(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_9(), GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblCodsudoku())
									.addComponent(getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(27)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblPuntuacin())
									.addComponent(getTextField_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblNivelSudoku())
									.addComponent(getComboBox_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(27)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblNumJugadores())
									.addComponent(getTextField_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addComponent(getLblDescripcin_1())
							.addComponent(getTextField_3(), GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGap(108))
			);
			panel_6.setLayout(gl_panel_6);
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			GroupLayout gl_panel_7 = new GroupLayout(panel_7);
			gl_panel_7.setHorizontalGroup(
				gl_panel_7.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_7.createSequentialGroup()
						.addGap(75)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(140)
								.addComponent(getLabel_10(), GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getTextField_9_1(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(103)
								.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_7.createSequentialGroup()
										.addComponent(getLabel_2(), GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getTextField_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_7.createSequentialGroup()
										.addComponent(getLabel(), GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getComboBox_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(46)
										.addComponent(getLabel_1(), GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getComboBox_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panel_7.createSequentialGroup()
								.addComponent(getLabel_3(), GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(getTextField_5(), GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(209, Short.MAX_VALUE))
			);
			gl_panel_7.setVerticalGroup(
				gl_panel_7.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_7.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
							.addComponent(getTextField_9_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLabel_10()))
						.addGap(18)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(3)
								.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLabel(), GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(getComboBox_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(3)
								.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLabel_1(), GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(getComboBox_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGap(11)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLabel_2())
							.addComponent(getTextField_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(14)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addComponent(getLabel_3())
							.addComponent(getTextField_5(), GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(138, Short.MAX_VALUE))
			);
			panel_7.setLayout(gl_panel_7);
		}
		return panel_7;
	}
	private JLabel getLblPuntuacin() {
		if (lblPuntuacin == null) {
			lblPuntuacin = new JLabel("Puntuaci\u00F3n:");
		}
		return lblPuntuacin;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JLabel getLblNumJugadores() {
		if (lblNumJugadores == null) {
			lblNumJugadores = new JLabel("Num.Jugadores:");
		}
		return lblNumJugadores;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
		}
		return textField_2;
	}
	private JLabel getLblDescripcin_1() {
		if (lblDescripcin_1 == null) {
			lblDescripcin_1 = new JLabel("Descripci\u00F3n:");
		}
		return lblDescripcin_1;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
		}
		return textField_3;
	}
	private JLabel getLblCodsudoku() {
		if (lblCodsudoku == null) {
			lblCodsudoku = new JLabel("Cod.Sudoku:");
		}
		return lblCodsudoku;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
		}
		return comboBox;
	}
	private JLabel getLblNivelSudoku() {
		if (lblNivelSudoku == null) {
			lblNivelSudoku = new JLabel("Nivel Sudoku:");
		}
		return lblNivelSudoku;
	}
	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
		}
		return comboBox_1;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Nivel Sudoku:");
		}
		return label;
	}
	private JComboBox getComboBox_2() {
		if (comboBox_2 == null) {
			comboBox_2 = new JComboBox();
		}
		return comboBox_2;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Cod.Sudoku:");
		}
		return label_1;
	}
	private JComboBox getComboBox_3() {
		if (comboBox_3 == null) {
			comboBox_3 = new JComboBox();
		}
		return comboBox_3;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Num.Jugadores:");
		}
		return label_2;
	}
	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setColumns(10);
		}
		return textField_4;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Descripci\u00F3n:");
		}
		return label_3;
	}
	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setColumns(10);
		}
		return textField_5;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(new BorderLayout(0, 0));
			panel_8.add(getScrollPane_1(), BorderLayout.WEST);
			panel_8.add(getPanel_9(), BorderLayout.SOUTH);
			panel_8.add(getPanel_10(), BorderLayout.CENTER);
		}
		return panel_8;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getList_2());
		}
		return scrollPane_1;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.add(getBtnModificar());
		}
		return panel_9;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			GroupLayout gl_panel_10 = new GroupLayout(panel_10);
			gl_panel_10.setHorizontalGroup(
				gl_panel_10.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_10.createSequentialGroup()
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_10.createSequentialGroup()
										.addComponent(getLblCodsudoku_1())
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getLblVarSudoku()))
									.addGroup(gl_panel_10.createSequentialGroup()
										.addComponent(getLblDescripcin_2())
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(getLblVarDescripcin()))))
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(23)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel_10.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getLabel_8(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getTextField_8()))
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGap(73)
										.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_10.createSequentialGroup()
												.addGap(2)
												.addComponent(getLabel_6(), GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getTextField_6(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getLabel_7(), GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getTextField_7(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_10.createSequentialGroup()
												.addComponent(getLabel_4(), GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getComboBox_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(38)
												.addComponent(getLabel_5(), GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
						.addGap(24))
			);
			gl_panel_10.setVerticalGroup(
				gl_panel_10.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_10.createSequentialGroup()
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblCodsudoku_1())
									.addComponent(getLblVarSudoku()))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblDescripcin_2())
									.addComponent(getLblVarDescripcin()))
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_10.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
										.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
											.addComponent(getComboBox_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(8))
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGap(187)
										.addComponent(getLabel_4())
										.addPreferredGap(ComponentPlacement.RELATED))))
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(232)
								.addComponent(getLabel_5())
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addComponent(getTextField_7(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(3)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
									.addComponent(getLabel_7())
									.addComponent(getTextField_6(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGap(3)
										.addComponent(getLabel_6())))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addComponent(getLabel_8())
							.addComponent(getTextField_8(), GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGap(21))
			);
			panel_10.setLayout(gl_panel_10);
		}
		return panel_10;
	}
	private JLabel getLblCodsudoku_1() {
		if (lblCodsudoku_1 == null) {
			lblCodsudoku_1 = new JLabel("Cod.Sudoku:");
		}
		return lblCodsudoku_1;
	}
	private JLabel getLblVarSudoku() {
		if (lblVarSudoku == null) {
			lblVarSudoku = new JLabel("Var sudoku");
		}
		return lblVarSudoku;
	}
	private JLabel getLblDescripcin_2() {
		if (lblDescripcin_2 == null) {
			lblDescripcin_2 = new JLabel("Descripci\u00F3n:");
		}
		return lblDescripcin_2;
	}
	private JLabel getLblVarDescripcin() {
		if (lblVarDescripcin == null) {
			lblVarDescripcin = new JLabel("Var descripci\u00F3n");
		}
		return lblVarDescripcin;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
		}
		return btnModificar;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Nivel Sudoku:");
		}
		return label_4;
	}
	private JComboBox getComboBox_4() {
		if (comboBox_4 == null) {
			comboBox_4 = new JComboBox();
		}
		return comboBox_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Cod.Sudoku:");
		}
		return label_5;
	}
	private JComboBox getComboBox_5() {
		if (comboBox_5 == null) {
			comboBox_5 = new JComboBox();
		}
		return comboBox_5;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Puntuaci\u00F3n:");
		}
		return label_6;
	}
	private JTextField getTextField_6() {
		if (textField_6 == null) {
			textField_6 = new JTextField();
			textField_6.setColumns(10);
		}
		return textField_6;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("Num.Jugadores:");
		}
		return label_7;
	}
	private JTextField getTextField_7() {
		if (textField_7 == null) {
			textField_7 = new JTextField();
			textField_7.setColumns(10);
		}
		return textField_7;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("Descripci\u00F3n:");
		}
		return label_8;
	}
	private JTextField getTextField_8() {
		if (textField_8 == null) {
			textField_8 = new JTextField();
			textField_8.setColumns(10);
		}
		return textField_8;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("C\u00F3digo:");
		}
		return label_9;
	}
	private JTextField getTextField_9() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("C\u00F3digo:");
		}
		return label_10;
	}
	private JTextField getTextField_9_1() {
		if (textField_9 == null) {
			textField_9 = new JTextField();
			textField_9.setColumns(10);
		}
		return textField_9;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList_1());
		}
		return scrollPane;
	}
	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			GroupLayout gl_panel_4 = new GroupLayout(panel_4);
			gl_panel_4.setHorizontalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
					.addGap(0, 536, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createSequentialGroup()
						.addGap(18)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_4.createSequentialGroup()
								.addComponent(getLabel_11_1())
								.addGap(33)
								.addComponent(getLabel_12_1()))
							.addGroup(gl_panel_4.createSequentialGroup()
								.addComponent(getLabel_13_1())
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(getLabel_14_1())))
						.addContainerGap(397, Short.MAX_VALUE))
			);
			gl_panel_4.setVerticalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
					.addGap(0, 262, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createSequentialGroup()
						.addGap(23)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLabel_11_1())
							.addComponent(getLabel_12_1()))
						.addGap(18)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLabel_13_1())
							.addComponent(getLabel_14_1()))
						.addContainerGap(193, Short.MAX_VALUE))
			);
			panel_4.setLayout(gl_panel_4);
		}
		return panel_4;
	}
	private JLabel getLabel_11_1() {
		if (label_11 == null) {
			label_11 = new JLabel("C\u00F3digo:");
		}
		return label_11;
	}
	private JLabel getLabel_12_1() {
		if (label_12 == null) {
			label_12 = new JLabel("Var cod");
		}
		return label_12;
	}
	private JLabel getLabel_13_1() {
		if (label_13 == null) {
			label_13 = new JLabel("Descripci\u00F3n:");
		}
		return label_13;
	}
	private JLabel getLabel_14_1() {
		if (label_14 == null) {
			label_14 = new JLabel("Var descrip");
		}
		return label_14;
	}
	private JList getList_1() {
		if (list_1 == null) {
			list_1 = new JList();
		}
		return list_1;
		/*/Crear un objeto DefaultListModel
		DefaultListModel listModel = new DefaultListModel();
		//Recorrer el contenido del ArrayList
		for(int i=0; i<arrayList.size(); i++) {
		    //Anadir cada elemento del ArrayList en el modelo de la lista
		    listModel.add(i, arrayList.get(i));
		}
		//Asociar el modelo de lista al JList
		jList1.setModel(listModel);*/
	}
	private JList getList_2() {
		if (list_2 == null) {
			list_2 = new JList();
		}
		return list_2;
	}
}
