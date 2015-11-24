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

public class VentanaAdminLogros extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JPanel panel_3;
	private JLabel lblCdigo;
	private JLabel lblVarCod;
	private JLabel lblDescripcin;
	private JLabel lblVarDescrip;
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
			panel_1.add(getPanel_3(), BorderLayout.CENTER);
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
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
		}
		return scrollPane;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			GroupLayout gl_panel_3 = new GroupLayout(panel_3);
			gl_panel_3.setHorizontalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_3.createSequentialGroup()
						.addGap(18)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(getLblCdigo())
								.addGap(33)
								.addComponent(getLblVarCod()))
							.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(getLblDescripcin())
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(getLblVarDescrip())))
						.addContainerGap(278, Short.MAX_VALUE))
			);
			gl_panel_3.setVerticalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_3.createSequentialGroup()
						.addGap(23)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblCdigo())
							.addComponent(getLblVarCod()))
						.addGap(18)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblDescripcin())
							.addComponent(getLblVarDescrip()))
						.addContainerGap(131, Short.MAX_VALUE))
			);
			panel_3.setLayout(gl_panel_3);
		}
		return panel_3;
	}
	private JLabel getLblCdigo() {
		if (lblCdigo == null) {
			lblCdigo = new JLabel("C\u00F3digo:");
		}
		return lblCdigo;
	}
	private JLabel getLblVarCod() {
		if (lblVarCod == null) {
			lblVarCod = new JLabel("Var cod");
		}
		return lblVarCod;
	}
	private JLabel getLblDescripcin() {
		if (lblDescripcin == null) {
			lblDescripcin = new JLabel("Descripci\u00F3n:");
		}
		return lblDescripcin;
	}
	private JLabel getLblVarDescrip() {
		if (lblVarDescrip == null) {
			lblVarDescrip = new JLabel("Var descrip");
		}
		return lblVarDescrip;
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
						.addContainerGap()
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addComponent(getLblDescripcin_1())
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getTextField_3(), 388, 388, 388))
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGap(104)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_6.createSequentialGroup()
										.addGap(71)
										.addComponent(getLabel_9(), GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addGap(5)
										.addComponent(getTextField_9(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_6.createSequentialGroup()
										.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblPuntuacin())
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getTextField_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_6.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_panel_6.createSequentialGroup()
													.addComponent(getLblCodsudoku())
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblNivelSudoku())
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getComboBox_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_6.createSequentialGroup()
												.addComponent(getLblNumJugadores())
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getTextField_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
						.addGap(73))
			);
			gl_panel_6.setVerticalGroup(
				gl_panel_6.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_6.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_9()))
							.addComponent(getTextField_9(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_6.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblCodsudoku())
									.addComponent(getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED))
							.addGroup(gl_panel_6.createSequentialGroup()
								.addGap(18)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
									.addComponent(getLblNivelSudoku())
									.addComponent(getComboBox_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblPuntuacin())
							.addComponent(getTextField_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblNumJugadores())
							.addComponent(getTextField_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addComponent(getTextField_3(), GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblDescripcin_1()))
						.addContainerGap())
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
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(187)
								.addComponent(getLabel_10(), GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(getTextField_9_1(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(137)
								.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_7.createSequentialGroup()
										.addComponent(getLabel_2(), GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(getTextField_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_7.createSequentialGroup()
										.addComponent(getLabel(), GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(getComboBox_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(60)
										.addComponent(getLabel_1(), GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(getComboBox_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(34)
								.addComponent(getLabel_3(), GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(getTextField_5(), GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(42, Short.MAX_VALUE))
			);
			gl_panel_7.setVerticalGroup(
				gl_panel_7.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_7.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_10()))
							.addComponent(getTextField_9_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel()))
							.addComponent(getComboBox_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_1()))
							.addComponent(getComboBox_3(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_7.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_2()))
							.addComponent(getTextField_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
							.addComponent(getLabel_3())
							.addComponent(getTextField_5(), GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGap(98)
										.addComponent(getLabel_4(), GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(getComboBox_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(60)
										.addComponent(getLabel_5(), GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGap(98)
										.addComponent(getLabel_6(), GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(getTextField_6(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(getLabel_7(), GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(getTextField_7(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_10.createSequentialGroup()
										.addComponent(getLabel_8(), GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getTextField_8(), GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap())
			);
			gl_panel_10.setVerticalGroup(
				gl_panel_10.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_10.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblCodsudoku_1())
							.addComponent(getLblVarSudoku()))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblDescripcin_2())
							.addComponent(getLblVarDescripcin()))
						.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
						.addGap(46)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_4()))
							.addComponent(getComboBox_4(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_5()))
							.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_6()))
							.addComponent(getTextField_6(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(3)
								.addComponent(getLabel_7()))
							.addComponent(getTextField_7(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addComponent(getLabel_8())
							.addComponent(getTextField_8(), GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGap(22))
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
}
