package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import packControladores.GestorAdministrador;
import packExcepciones.NoValidoException;
import packExcepciones.SudokuActivadoException;
import packExcepciones.SudokuDesactivadoException;
import packExcepciones.YaExisteException;
import packModelo.Sudoku;

public class VentanaAdminSudoku extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JList listaCodigos1;
	private JLabel lblNewLabel;
	private JButton BotonActivarSudoku;
	private JList listaCodigos2;
	private JLabel label;
	private JButton botonDesactivarSudoku;
	private JList listaCodigos3;
	private JLabel label_1;
	private JButton botonBorrarSudoku;
	private JPanel panel_3;
	private JLabel lblDificultad;
	private JLabel lblSudokuResuelto;
	private JTextField campoSudokuIncompleto;
	private JLabel lblSudokuResuelto_1;
	private JTextField CampoSudokuCompleto;
	private JButton botonAñadirSudoku;
	private JComboBox campoDificultad;
	private JPanel panel_4;
	private JList listaCodigos4;
	private JLabel label_2;
	private JLabel label_3;
	private JComboBox campoDificultad2;
	private JTextField campoSudokuIncompleto2;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField campoSudokuCompleto2;
	private JButton botonVerSudoku;
	private JButton botonModificar;
	private static VentanaAdminSudoku mVentana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdminSudoku frame = VentanaAdminSudoku.getVentana();
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
	private VentanaAdminSudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTabbedPane());
	}
	
	public static VentanaAdminSudoku getVentana(){
		if(mVentana == null){
			mVentana = new VentanaAdminSudoku();
		}
		return mVentana;
	}
	
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Activar Sudoku", null, getPanel(), null);
			tabbedPane.addTab("Desactivar Sudoku", null, getPanel_1(), null);
			tabbedPane.addTab("Borrar Sudoku", null, getPanel_2(), null);
			tabbedPane.addTab("A\u00F1adir Sudoku", null, getPanel_3(), null);
			tabbedPane.addTab("Modificar Sudoku", null, getPanel_4(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getListaCodigos1());
			panel.add(getLblNewLabel());
			panel.add(getBotonActivarSudoku());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getList_1());
			panel_1.add(getLabel());
			panel_1.add(getBotonDesactivarSudoku());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.add(getListaCodigos3());
			panel_2.add(getLabel_1());
			panel_2.add(getBotonBorrarSudoku());
		}
		return panel_2;
	}
	private JList getListaCodigos1() {
		
		if (listaCodigos1 == null) {
			listaCodigos1 = new JList();
			listaCodigos1.setBorder(new LineBorder(new Color(0, 0, 0)));
			listaCodigos1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//Añadimos la lista codigos 
			ArrayList<String> lista = GestorAdministrador.getGestorAdministrador().getListaCodigosSudoku();
			DefaultListModel l = new DefaultListModel();
			for (int i = 0; i < lista.size(); i++) {
				l.addElement(lista.get(i));				
			}
			listaCodigos1.setModel(l);
			
			
			listaCodigos1.setBounds(29, 44, 90, 258);
		}
		return listaCodigos1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seleccione un codigo");
			lblNewLabel.setBounds(29, 5, 131, 26);
		}
		return lblNewLabel;
	}
	private JButton getBotonActivarSudoku() {
		if (BotonActivarSudoku == null) {
			BotonActivarSudoku = new JButton("Activar Sudoku");
			BotonActivarSudoku.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(listaCodigos1.isSelectionEmpty()){
						JOptionPane.showMessageDialog(null, "Seleccione un numero de la lista.");
					}else
					{
					int codigo = Integer.parseInt(listaCodigos1.getSelectedValue().toString());							
					
					try {
						GestorAdministrador.getGestorAdministrador().activarSudoku(codigo);
						JOptionPane.showMessageDialog(null, "Sudoku activado con exito");
					} catch (SudokuActivadoException e) {
						JOptionPane.showMessageDialog(null, "El sudoku ya esta activado.");
						e.printStackTrace();
					}
					}
				}
			});
			BotonActivarSudoku.setBounds(587, 273, 131, 25);
		}
		return BotonActivarSudoku;
	}
	private JList getList_1() {
		if (listaCodigos2 == null) {
			listaCodigos2 = new JList();			
			listaCodigos2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaCodigos2.setBorder(new LineBorder(new Color(0, 0, 0)));
			listaCodigos2.setBounds(25, 52, 90, 258);
			//Añadimos la lista codigos 
			ArrayList<String> lista = GestorAdministrador.getGestorAdministrador().getListaCodigosSudoku();
			DefaultListModel l = new DefaultListModel();
			for (int i = 0; i < lista.size(); i++) {
				l.addElement(lista.get(i));				
			}
			listaCodigos2.setModel(l);
		}
		return listaCodigos2;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Seleccione un codigo");
			label.setBounds(25, 13, 131, 26);
		}
		return label;
	}
	private JButton getBotonDesactivarSudoku() {
		if (botonDesactivarSudoku == null) {
			botonDesactivarSudoku = new JButton("Desactivar Sudoku");
			botonDesactivarSudoku.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listaCodigos2.isSelectionEmpty()){
						JOptionPane.showMessageDialog(null, "Seleccione un numero de la lista.");
					}else
					{
					int codigo = Integer.parseInt(listaCodigos2.getSelectedValue().toString());							
					
					try {
						GestorAdministrador.getGestorAdministrador().desactivarSudoku(codigo);
						JOptionPane.showMessageDialog(null, "Sudoku desactivado con exito.");
					} catch (SudokuDesactivadoException e1) {
						JOptionPane.showMessageDialog(null, "El sudoku ya se encuentra desactivado");
						e1.printStackTrace();
					}
					}
				}
			});
			botonDesactivarSudoku.setBounds(556, 281, 158, 25);
		}
		return botonDesactivarSudoku;
	}
	private JList getListaCodigos3() {
		if (listaCodigos3 == null) {
			listaCodigos3 = new JList();
			listaCodigos3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaCodigos3.setBorder(new LineBorder(new Color(0, 0, 0)));
			listaCodigos3.setBounds(28, 52, 90, 258);
			//Añadimos la lista codigos 
			ArrayList<String> lista = GestorAdministrador.getGestorAdministrador().getListaCodigosSudoku();
			DefaultListModel l = new DefaultListModel();
			for (int i = 0; i < lista.size(); i++) {
				l.addElement(lista.get(i));				
			}
			listaCodigos3.setModel(l);
		}
		return listaCodigos3;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Seleccione un codigo");
			label_1.setBounds(28, 13, 131, 26);
		}
		return label_1;
	}
	private JButton getBotonBorrarSudoku() {
		if (botonBorrarSudoku == null) {
			botonBorrarSudoku = new JButton("Borrar Sudoku");
			botonBorrarSudoku.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listaCodigos3.isSelectionEmpty()){
						JOptionPane.showMessageDialog(null, "Seleccione un numero de la lista.");
					}else
					{
					int codigo = Integer.parseInt(listaCodigos3.getSelectedValue().toString());							
					
						GestorAdministrador.getGestorAdministrador().borrarSudoku(codigo);;
						JOptionPane.showMessageDialog(null, "Sudoku borrado con exito.");
					}
					
				}
			});
			botonBorrarSudoku.setBounds(586, 281, 131, 25);
		}
		return botonBorrarSudoku;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.add(getLblDificultad());
			panel_3.add(getLblSudokuResuelto());
			panel_3.add(getCampoSudokuIncompleto());
			panel_3.add(getLblSudokuResuelto_1());
			panel_3.add(getCampoSudokuCompleto());
			panel_3.add(getBotonAñadirSudoku());
			panel_3.add(getCampoDificultad());
		}
		return panel_3;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			lblDificultad = new JLabel("Dificultad");
			lblDificultad.setBounds(36, 28, 56, 16);
		}
		return lblDificultad;
	}
	private JLabel getLblSudokuResuelto() {
		if (lblSudokuResuelto == null) {
			lblSudokuResuelto = new JLabel("Sudoku sin resolver");
			lblSudokuResuelto.setBounds(36, 74, 123, 16);
		}
		return lblSudokuResuelto;
	}
	private JTextField getCampoSudokuIncompleto() {
		if (campoSudokuIncompleto == null) {
			campoSudokuIncompleto = new JTextField();
			campoSudokuIncompleto.setBounds(168, 71, 531, 22);
			campoSudokuIncompleto.setColumns(10);
		}
		return campoSudokuIncompleto;
	}
	private JLabel getLblSudokuResuelto_1() {
		if (lblSudokuResuelto_1 == null) {
			lblSudokuResuelto_1 = new JLabel("Sudoku resuelto");
			lblSudokuResuelto_1.setBounds(36, 119, 120, 16);
		}
		return lblSudokuResuelto_1;
	}
	private JTextField getCampoSudokuCompleto() {
		if (CampoSudokuCompleto == null) {
			CampoSudokuCompleto = new JTextField();
			CampoSudokuCompleto.setBounds(168, 116, 531, 22);
			CampoSudokuCompleto.setColumns(10);
		}
		return CampoSudokuCompleto;
	}
	private JButton getBotonAñadirSudoku() {
		if (botonAñadirSudoku == null) {
			botonAñadirSudoku = new JButton("A\u00F1adir");
			botonAñadirSudoku.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int dificultad = campoDificultad.getSelectedIndex()+1;
					String mCompleta = CampoSudokuCompleto.getText();
					String mIncompleta = campoSudokuIncompleto.getText();
					if(mCompleta.length() < 81 || mIncompleta.length() < 81){
						JOptionPane.showMessageDialog(null, "El sudoku debe contener 81 valores.");
					}else {
						try {
							GestorAdministrador.getGestorAdministrador().añadirSudoku(mCompleta, mIncompleta, dificultad);
							JOptionPane.showMessageDialog(null, "Sudoku añadidido.");
						} catch (YaExisteException e1) {
							JOptionPane.showMessageDialog(null, "El sudoku ya existe.");
							e1.printStackTrace();
						} catch (NoValidoException e1) {
							JOptionPane.showMessageDialog(null, "El sudoku no es valido.");
							e1.printStackTrace();
						}
					}
					
					
				}
			});
			botonAñadirSudoku.setBounds(601, 284, 97, 25);
		}
		return botonAñadirSudoku;
	}
	private JComboBox getCampoDificultad() {
		if (campoDificultad == null) {
			campoDificultad = new JComboBox();
			campoDificultad.setModel(new DefaultComboBoxModel(new String[] {"Facil", "Medio", "Dificil"}));
			campoDificultad.setSelectedIndex(0);
			campoDificultad.setBounds(168, 25, 92, 22);
		}
		return campoDificultad;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.add(getListaCodigos4());
			panel_4.add(getLabel_2());
			panel_4.add(getLabel_3());
			panel_4.add(getCampoDificultad2());
			panel_4.add(getCampoSudokuIncompleto2());
			panel_4.add(getLabel_4());
			panel_4.add(getLabel_5());
			panel_4.add(getCampoSudokuCompleto2());
			panel_4.add(getBotonVerSudoku());
			panel_4.add(getBotonModificar());
		}
		return panel_4;
	}
	private JList getListaCodigos4() {
		if (listaCodigos4 == null) {
			listaCodigos4 = new JList();
			listaCodigos4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaCodigos4.setBorder(new LineBorder(new Color(0, 0, 0)));
			listaCodigos4.setBounds(31, 52, 90, 258);
			//Añadimos los codigos
			ArrayList<String> lista = GestorAdministrador.getGestorAdministrador().getListaCodigosSudoku();
			DefaultListModel l = new DefaultListModel();
			for (int i = 0; i < lista.size(); i++) {
				l.addElement(lista.get(i));				
			}
			listaCodigos4.setModel(l);
		}
		return listaCodigos4;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Seleccione un codigo");
			label_2.setBounds(31, 13, 131, 26);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Dificultad");
			label_3.setBounds(169, 55, 56, 16);
		}
		return label_3;
	}
	private JComboBox getCampoDificultad2() {
		if (campoDificultad2 == null) {
			campoDificultad2 = new JComboBox();
			campoDificultad2.setModel(new DefaultComboBoxModel(new String[] {"Facil", "Medio", "Dificil"}));
			campoDificultad2.setSelectedIndex(0);
			campoDificultad2.setBounds(301, 52, 92, 22);
		}
		return campoDificultad2;
	}
	private JTextField getCampoSudokuIncompleto2() {
		if (campoSudokuIncompleto2 == null) {
			campoSudokuIncompleto2 = new JTextField();
			campoSudokuIncompleto2.setColumns(10);
			campoSudokuIncompleto2.setBounds(301, 98, 440, 22);
		}
		return campoSudokuIncompleto2;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Sudoku sin resolver");
			label_4.setBounds(169, 101, 123, 16);
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Sudoku resuelto");
			label_5.setBounds(169, 146, 120, 16);
		}
		return label_5;
	}
	private JTextField getCampoSudokuCompleto2() {
		if (campoSudokuCompleto2 == null) {
			campoSudokuCompleto2 = new JTextField();
			campoSudokuCompleto2.setColumns(10);
			campoSudokuCompleto2.setBounds(301, 143, 440, 22);
		}
		return campoSudokuCompleto2;
	}
	private JButton getBotonVerSudoku() {
		if (botonVerSudoku == null) {
			botonVerSudoku = new JButton("Ver Sudoku");
			botonVerSudoku.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(listaCodigos4.isSelectionEmpty()){
						JOptionPane.showMessageDialog(null, "Seleccione un numero de la lista.");
					}else
					{
						int codigo = Integer.parseInt(listaCodigos4.getSelectedValue().toString());					
					
						Sudoku s = GestorAdministrador.getGestorAdministrador().buscarSudokuPorCodigo(codigo);
						campoDificultad2.setSelectedIndex(s.getDificultad()-1);
						campoSudokuIncompleto2.setText(s.toStringMatrizInicial());
						campoSudokuCompleto2.setText(s.toStringMatrizSolucion());
						
					}
				}
			});
			botonVerSudoku.setBounds(449, 285, 123, 25);
		}
		return botonVerSudoku;
	}
	private JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton("Modificar");
			botonModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listaCodigos4.isSelectionEmpty()){
						JOptionPane.showMessageDialog(null, "Seleccione un numero de la lista.");
					}else
					{
					     int codigo = Integer.parseInt(listaCodigos4.getSelectedValue().toString());							
					     
					     int dificultad = campoDificultad2.getSelectedIndex()+1;
					     String completa = campoSudokuCompleto2.getText();
					     String incompleta = campoSudokuIncompleto2.getText();
						
					     if(completa.length() < 81 || incompleta.length() < 81){
								JOptionPane.showMessageDialog(null, "El sudoku debe contener 81 valores.");
						}else {
							
							try {
								GestorAdministrador.getGestorAdministrador().modificarSudoku(codigo, completa, incompleta, dificultad);
							} catch (NoValidoException e1) {
								JOptionPane.showMessageDialog(null, "El sudoku no es valido.");
								e1.printStackTrace();
							}
						}
					}
					
				}
			});
			botonModificar.setBounds(599, 285, 97, 25);
		}
		return botonModificar;
	}
}
