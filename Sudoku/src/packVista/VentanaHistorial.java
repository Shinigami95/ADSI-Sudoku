package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import packControladores.ConexionBD;
import packControladores.GestorHistorial;
import packControladores.GestorLogros;
import packControladores.GestorRanking;
import packControladores.GestorSesion;
import packExcepciones.ExcepcionConectarBD;

import javax.swing.JLabel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaHistorial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		textArea.setEditable(false);
        obtenerHistorialSudokus();
        textArea_1.setEditable(false);
        obtenerHistorialRetos();
	}
	
	public static VentanaHistorial getVentanaHistorial() throws ExcepcionConectarBD, SQLException{
		if(mVHistorial==null){
			mVHistorial = new VentanaHistorial();
		}

		return mVHistorial;
	}
	
	private void initialize() throws ExcepcionConectarBD, SQLException {
		addWindowListener(new WindowAdapter() {
			//Al cerrarse la ventana nos envia a la ventanaJugador
			@Override
			public void windowClosing(WindowEvent arg0) {
					dispose();
					VentanaJugador.getVentana().setVisible(true);;
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
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addComponent(getTextArea(), GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(getTextArea(), GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.addGap(52))
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
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addComponent(getTextArea_1(), GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(getTextArea_1(), GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.addGap(52))
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
			//Esta lista tiene un Listener que al seleccionar el id de un logro actualiza dos de los textFields para que aparezca el ID del sudoku
			//al que pertenece y la descripcion del logro.
			list_1.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					try {if(list_1.getSelectedIndex()>-1){
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
		//La lista se llena con los IDs de los logros existentes
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
		//Este label se llena con el ID del sudoku al cual pertenece el logro que se ha seleccionado en la lista
		if (label == null) {
			label = new JLabel("");
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
		//Este label se llena con la descripcion del logro que se ha seleccionado en la lista
		if (label_1 == null) {
			label_1 = new JLabel("");
			if(list_1.getSelectedValue()!=null){
				ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
				res.next();
				String des=res.getString("DESCRPCION");
				ConexionBD.getConexionBD().closeResult(res);
				label.setText(des);}
		}
		return label_1;
	}
	private void obtenerHistorialSudokus(){
        String historial;
        try {
        	String jugador = GestorSesion.getGestor().getUserSesion();
        	historial = GestorHistorial.getGestorHistorial().obtenerHistorialSudokus(jugador);
            if(historial.length() > 0){
                textArea.setText(historial);
            }else{
                textArea.setText("Â¡No has jugado ningÃºn sudoku!");
            }
        } catch (ExcepcionConectarBD e) {
            e.printStackTrace();
        }
    }
	private void obtenerHistorialRetos(){
        String historial;
        try {
        	historial = GestorRanking.getGestorRanking().obtenerRankingPuntuacion();
            if(historial.length() > 0){
                textArea_1.setText(historial);
            }else{
                textArea_1.setText("Â¡No has participado en ningÃºn reto!");
            }
        } catch (ExcepcionConectarBD e) {
            e.printStackTrace();
        }
    }
}
