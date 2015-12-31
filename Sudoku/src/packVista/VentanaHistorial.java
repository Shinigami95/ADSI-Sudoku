package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

import packControladores.ConexionBD;
import packControladores.GestorLogros;
import packControladores.GestorSesion;
import packExcepciones.ExcepcionConectarBD;

import javax.swing.JLabel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaHistorial extends JFrame {

	private static VentanaHistorial mVHistorial;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTextArea textArea_1;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JPanel panel_3;
	private JList list_1;
	private JLabel lblSudoku;
	private JLabel lblDescripcin;
	private JLabel label;
	private JLabel label_1;
	

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
	 * @throws ExcepcionConectarBD 
	 * @throws SQLException 
	 */
	public VentanaHistorial() throws ExcepcionConectarBD, SQLException {
		initialize();
	}
	
	public static VentanaHistorial getVentanaHistorial() throws ExcepcionConectarBD, SQLException{
		if(mVHistorial==null){
			mVHistorial = new VentanaHistorial();
		}

		return mVHistorial;
	}
	
	private void initialize() throws ExcepcionConectarBD, SQLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					getVentanaHistorial().setVisible(false);
					VentanaJugador.getVentana();
				} catch (ExcepcionConectarBD e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		setBounds(100, 100, 450, 300);
		setTitle("Historial");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(getTabbedPane());
	}

	private JTabbedPane getTabbedPane() throws ExcepcionConectarBD, SQLException {
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
					.addComponent(getTextArea(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
						.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE))
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
					.addComponent(getTextArea_1(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
						.addComponent(getTextArea_1(), GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}
	private JPanel getPanel_2() throws ExcepcionConectarBD, SQLException {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getScrollPane(), BorderLayout.WEST);
			panel_2.add(getPanel_3(), BorderLayout.CENTER);
		}
		return panel_2;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setEditable(false);
		}
		return textArea_1;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JScrollPane getScrollPane() throws ExcepcionConectarBD {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getList_1());
			//https://www.youtube.com/watch?v=twkRNQ2Vs6g
			list_1.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					try {
						ResultSet tes=ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
						tes.next();
						String ses=tes.getString("ID_SUDOKU");
						ConexionBD.getConexionBD().closeResult(tes);
						label.setText(ses);
						ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
						res.next();
						String des=res.getString("DESCRIPCION");
						ConexionBD.getConexionBD().closeResult(res);
						label_1.setText(des);
						
						
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
		
	
	private JPanel getPanel_3() throws ExcepcionConectarBD, SQLException {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.add(getLblSudoku());
			panel_3.add(getLblDescripcin());
			panel_3.add(getLabel());
			panel_3.add(getLabel_1());
		}
		return panel_3;
	}
	
	private JList getList_1() throws ExcepcionConectarBD {
		if (list_1 == null) {
			list_1 = new JList();
			list_1.setVisibleRowCount(100);
		}
		list_1.setModel(GestorLogros.verLogros(GestorSesion.getGestor().getUserSesion()));
		return list_1;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("Sudoku:");
			lblSudoku.setBounds(10, 11, 46, 14);
		}
		return lblSudoku;
	}
	private JLabel getLblDescripcin() {
		if (lblDescripcin == null) {
			lblDescripcin = new JLabel("Descripci\u00F3n:");
			lblDescripcin.setBounds(10, 48, 74, 14);
		}
		return lblDescripcin;
	}
	private JLabel getLabel() throws ExcepcionConectarBD, SQLException {
		if (label == null) {
			label = new JLabel("");
			label.setBounds(66, 11, 46, 14);
			if(list_1.getSelectedValue()!=null){
				ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
				res.next();
				String des=res.getString("ID_SUDOKU");
				ConexionBD.getConexionBD().closeResult(res);
				label.setText(des);}
		}
		return label;
	}
	private JLabel getLabel_1() throws ExcepcionConectarBD, SQLException {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setBounds(10, 73, 46, 14);
			if(list_1.getSelectedValue()!=null){
				ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
				res.next();
				String des=res.getString("DESCRPCION");
				ConexionBD.getConexionBD().closeResult(res);
				label.setText(des);}
		}
		return label_1;
	}
}