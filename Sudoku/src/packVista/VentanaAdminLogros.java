package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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

import packControladores.GestorLogros;
import packExcepciones.ExcepcionConectarBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaAdminLogros extends JFrame {

	private static final long serialVersionUID = 1L;
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
	private JLabel lblPuntuacin;
	private JTextField textField_1;
	private JLabel lblNumJugadores;
	private JTextField textField_2;
	private JLabel lblDescripcin_1;
	private JTextField textField_3;
	private JLabel lblCodsudoku;
	private JComboBox<String> comboBox;
	private JPanel panel_8;
	private JScrollPane scrollPane_1;
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel lblCodsudoku_1;
	private JLabel lblVarSudoku;
	private JLabel lblDescripcin_2;
	private JLabel lblVarDescripcin;
	private JButton btnModificar;
	private JLabel label_5;
	private JComboBox<String> comboBox_5;
	private JLabel label_6;
	private JTextField textField_6;
	private JLabel label_7;
	private JTextField textField_7;
	private JLabel label_8;
	private JTextField textField_8;
	private JLabel label_9;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JPanel panel_4;
	private JLabel lblCdigoSudoku;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JList<String> list_1;
	private JList<String> list_2;
	private static VentanaAdminLogros ventana;


	/**
	 * Create the frame.
	 * @throws ExcepcionConectarBD 
	 * @throws SQLException 
	 */
	public static VentanaAdminLogros getVentana() throws ExcepcionConectarBD, SQLException{
		if(ventana==null){
			ventana = new VentanaAdminLogros();
		}
		return ventana;
	}
	private VentanaAdminLogros() throws ExcepcionConectarBD, SQLException{
		initialize();
	}
	
	private void initialize() throws ExcepcionConectarBD, SQLException {
		//Al cerrar la ventana lleva al usuario a la ventana administrador.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				VentanaAdmin.getVentana().setVisible(true);
			}
		});
		setTitle("Administrar logros");
		setBounds(100, 100, 559, 361);
		setSize(800,500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTabbedPane(), BorderLayout.CENTER);
		

	}

	private JTabbedPane getTabbedPane() throws ExcepcionConectarBD, SQLException {
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
	private JPanel getPanel_1() throws ExcepcionConectarBD, SQLException {
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
			btnEliminar.addActionListener(new ActionListener() {
				//Al seleccionar un logro de la lista y dar a eliminar este se elimina de la base de datos y se actualizan las listas de la ventana.
				public void actionPerformed(ActionEvent arg0) {
					try {
						GestorLogros.getGestor().eliminar(list_1.getSelectedValue().toString());
						getList_1();
						getList_2();
						JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.");
					} catch (ExcepcionConectarBD e) {
						e.printStackTrace();
					}
				}
			});
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
		}
		return tabbedPane_1;
	}
	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.addActionListener(new ActionListener() {
				/*Al hacer clic en el boton aÃƒÂ±adir se comprueban los datos que se han metido en los campos cumplan las especificaciones necesarias.
				 * Si es asi se meten en la base de datos y junto con la actualizacion de las listas sale un mensaje que indica que todo ha ido bien.*/
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(GestorLogros.getGestor().datos(textField.getText(),textField_3.getText(),textField_1.getText(),textField_2.getText())){
							GestorLogros.getGestor().anadirLogro(textField.getText(),comboBox.getSelectedItem().toString() , textField_3.getText(), textField_1.getText(), textField_2.getText());
							getList_1();
							getList_2();
							JOptionPane.showMessageDialog(null, "Se ha a\u00F1adido corectamente.");
						}
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (ExcepcionConectarBD e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
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
										.addComponent(getLabel_9(), GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
										.addGap(5)
										.addComponent(getTextField_9(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
								.addGap(28)))
						.addContainerGap(199, Short.MAX_VALUE))
			);
			gl_panel_6.setVerticalGroup(
				gl_panel_6.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_6.createSequentialGroup()
						.addGap(44)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addComponent(getTextField_9(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_6.createSequentialGroup()
									.addGap(3)
									.addComponent(getLabel_9(), GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(getLblCodsudoku())
								.addComponent(getComboBox(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(27)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(getLblPuntuacin())
								.addComponent(getTextField_1(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(getLblNumJugadores())
								.addComponent(getTextField_2(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
	private JLabel getLblPuntuacin() {
		if (lblPuntuacin == null) {
			lblPuntuacin = new JLabel("Puntuaci\u00F3n:");
		}
		return lblPuntuacin;
	}
	private JTextField getTextField_1() {
		//Al crear este textField se mete un 0 como inicial y tiene un KeyListener que obliga a meter solo caracteres numericos.
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setText("0");
			textField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c=arg0.getKeyChar();
					if(c<'0' || c>'9')arg0.consume();
				}
			});
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
		//Al crear este textField se mete un 0 como inicial y tiene un KeyListener que obliga a meter solo caracteres numericos.
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setText("0");
			textField_2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c=arg0.getKeyChar();
					if(c<'0' || c>'9')arg0.consume();
				}
			});
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
	private JComboBox<String> getComboBox() {
		//Este comboBox se rellena con los IDs de los sudokus existentes.No se mira que esten activos o no.
		if (comboBox == null) {
			DefaultComboBoxModel<String> mdl=new DefaultComboBoxModel<String>(GestorLogros.getGestor().metodoSudoku());
			comboBox = new JComboBox<String>(mdl);
		}
		return comboBox;
	}
	private JPanel getPanel_8() throws ExcepcionConectarBD, SQLException {
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
			scrollPane_1.setPreferredSize(new Dimension(200, 300));
			scrollPane_1.setViewportView(getList_2());
			//https://www.youtube.com/watch?v=twkRNQ2Vs6g
			//Esta lista tiene un Listener que al seleccionar el id de un logro actualiza dos de los textFields para que aparezca el ID del sudoku
			//al que pertenece y la descripcion del logro. 
			list_2.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					try {if(list_2.getSelectedIndex()>-1){
						lblVarSudoku.setText(GestorLogros.getGestor().getSudoku(list_2.getSelectedValue().toString()));
						lblVarDescripcin.setText(GestorLogros.getGestor().getDescripcionDe(list_2.getSelectedValue().toString()));}
					} catch (ExcepcionConectarBD e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					};
					
				}
				
				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
				}
			});
			
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
	private JPanel getPanel_10() throws ExcepcionConectarBD, SQLException {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			GroupLayout gl_panel_10 = new GroupLayout(panel_10);
			gl_panel_10.setHorizontalGroup(
				gl_panel_10.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_10.createSequentialGroup()
						.addContainerGap(28, Short.MAX_VALUE)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
								.addComponent(getLblCodsudoku_1())
								.addComponent(getLblDescripcin_2()))
							.addComponent(getLabel_8(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addGap(36)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING, false)
									.addComponent(getTextField_8())
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGroup(gl_panel_10.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_panel_10.createSequentialGroup()
												.addComponent(getLabel_5(), GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel_10.createSequentialGroup()
												.addComponent(getLabel_6(), GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getTextField_6(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getLabel_7(), GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(getTextField_7(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panel_10.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
									.addComponent(getLblVarSudoku())
									.addComponent(getLblVarDescripcin()))))
						.addGap(93))
			);
			gl_panel_10.setVerticalGroup(
				gl_panel_10.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_10.createSequentialGroup()
						.addGap(51)
						.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_10.createSequentialGroup()
								.addComponent(getLblVarSudoku())
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getLblVarDescripcin())
								.addContainerGap())
							.addGroup(gl_panel_10.createSequentialGroup()
								.addComponent(getLblCodsudoku_1())
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getLblDescripcin_2())
								.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
									.addComponent(getComboBox_5(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_10.createSequentialGroup()
										.addGap(7)
										.addComponent(getLabel_5())))
								.addPreferredGap(ComponentPlacement.UNRELATED)
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
									.addComponent(getTextField_8(), GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLabel_8()))
								.addGap(69))))
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
	private JLabel getLblVarSudoku() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con el ID del sudoku al cual pertenece el logro que se ha seleccionado en la lista
		if (lblVarSudoku == null) {
			lblVarSudoku = new JLabel();
			if(list_2.getSelectedValue()!=null){
			lblVarSudoku.setText(GestorLogros.getGestor().getSudoku(list_2.getSelectedValue().toString()));}
		}
		return lblVarSudoku;
	}
	private JLabel getLblDescripcin_2() {
		if (lblDescripcin_2 == null) {
			lblDescripcin_2 = new JLabel("Descripci\u00F3n:");
		}
		return lblDescripcin_2;
	}
	private JLabel getLblVarDescripcin() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con la descripcion del logro que se ha seleccionado en la lista
		if (lblVarDescripcin == null) {
			lblVarDescripcin = new JLabel();
			if(list_2.getSelectedValue()!=null){
			lblVarDescripcin.setText(GestorLogros.getGestor().getDescripcionDe(list_2.getSelectedValue().toString()));}

		}
		return lblVarDescripcin;
	}
	private JButton getBtnModificar() {
		/*Al hacer clic en el boton modificar se comprueban los datos que se han metido en los campos cumplan las especificaciones necesarias.
		 * Si es asi se meten en la base de datos y junto con la actualizacion de las listas sale un mensaje que indica que todo ha ido bien.*/
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(GestorLogros.getGestor().datos("hola", textField_8.getText(), textField_6.getText(), textField_7.getText())){
								GestorLogros.getGestor().modificarLogros(list_2.getSelectedValue().toString(), comboBox_5.getSelectedItem().toString(), textField_8.getText(), textField_6.getText(), textField_7.getText());
								JOptionPane.showMessageDialog(null, "Se ha modificado corectamente.");
							}
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (ExcepcionConectarBD e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}
		return btnModificar;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Cod.Sudoku:");
		}
		return label_5;
	}
	private JComboBox<String> getComboBox_5() {
		//Este comboBox se rellena con los IDs de los sudokus existentes.No se mira que esten activos o no.
		if (comboBox_5 == null) {
			DefaultComboBoxModel<String> mdl=new DefaultComboBoxModel<String>(GestorLogros.getGestor().metodoSudoku());
			comboBox_5 = new JComboBox<String>(mdl);
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
		//Al crear este textField se mete un 0 como inicial y tiene un KeyListener que obliga a meter solo caracteres numericos.
		if (textField_6 == null) {
			textField_6 = new JTextField();
			textField_6.setText("0");
			textField_6.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c=arg0.getKeyChar();
					if(c<'0' || c>'9')arg0.consume();
				}
			});
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
		//Al crear este textField se mete un 0 como inicial y tiene un KeyListener que obliga a meter solo caracteres numericos.
		if (textField_7 == null) {
			textField_7 = new JTextField();
			textField_7.setText("0");
			textField_7.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c=arg0.getKeyChar();
					if(c<'0' || c>'9')arg0.consume();
				}
			});
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
	private JScrollPane getScrollPane() throws ExcepcionConectarBD {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getList_1());
			//https://www.youtube.com/watch?v=twkRNQ2Vs6g
			//Esta lista tiene un Listener que al seleccionar el id de un logro actualiza dos de los textFields para que aparezca el ID del sudoku
			//al que pertenece y la descripcion del logro. 
			list_1.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					try {if(list_1.getSelectedIndex()>-1){
						label_12.setText(GestorLogros.getGestor().getSudoku(list_1.getSelectedValue().toString()));
						label_14.setText(GestorLogros.getGestor().getDescripcionDe(list_1.getSelectedValue().toString()));
					}
						
					} catch (ExcepcionConectarBD e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					};
					
				}
				
				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
				}
			});
		}
		return scrollPane;
	}
	private JPanel getPanel_4_1() throws ExcepcionConectarBD, SQLException {
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
		if (lblCdigoSudoku == null) {
			lblCdigoSudoku = new JLabel("C\u00F3digo Sudoku:");
		}
		return lblCdigoSudoku;
	}
	private JLabel getLabel_12_1() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con el ID del sudoku al cual pertenece el logro que se ha seleccionado en la lista
		if (label_12 == null) {
			label_12 = new JLabel();
			if(list_1.getSelectedValue()!=null){
				label_12.setText(GestorLogros.getGestor().getSudoku(list_1.getSelectedValue().toString()));}
		}
		return label_12;
	}
	private JLabel getLabel_13_1() {
		if (label_13 == null) {
			label_13 = new JLabel("Descripci\u00F3n:");
		}
		return label_13;
	}
	private JLabel getLabel_14_1() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con la descripcion del logro que se ha seleccionado en la lista
		if (label_14 == null) {
			label_14 = new JLabel();
			if(list_1.getSelectedValue()!=null){
				label_14.setText(GestorLogros.getGestor().getDescripcionDe(list_1.getSelectedValue().toString()));}
		}
		return label_14;
	}

	private JList<String> getList_1() throws ExcepcionConectarBD {
		//La lista se llena con los IDs de los logros existentes
		if (list_1 == null) {
			list_1 = new JList<String>();
			list_1.setVisibleRowCount(100);
		}
		list_1.setModel(GestorLogros.getGestor().llenarLista());
		return list_1;
	}
	private JList<String> getList_2() {
		//La lista se llena con los IDs de los logros existentes
		if (list_2 == null) {
			list_2 = new JList<String>();
			list_2.setVisibleRowCount(100);
		}
		list_2.setModel(GestorLogros.getGestor().llenarLista());
		return list_2;
	}
	
		
	
}
