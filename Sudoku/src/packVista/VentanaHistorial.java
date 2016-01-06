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
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private JList<String> list_1;
	private JLabel lblSudoku;
	private JLabel lblDescripcin;
	private JLabel label;
	private JLabel label_1;
	private JButton btnVolver;
	private JButton btnVolver1;
	

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
					VentanaJugador.getVentana().setVisible(true);
			}
		});
		setBounds(100, 100, 550, 400);
		setTitle("Historial");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(getTabbedPane());
		setLocationRelativeTo(null);
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
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(321, Short.MAX_VALUE)
						.addComponent(getBtnVolver(), GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(getTextArea(), GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(getBtnVolver())
						.addGap(18))
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
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap(321, Short.MAX_VALUE)
						.addComponent(getBtnVolver1(), GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(getTextArea_1(), GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(getBtnVolver1())
						.addContainerGap())
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
						label.setText(GestorLogros.getGestor().getSudoku(list_1.getSelectedValue().toString()));
						label_1.setText(GestorLogros.getGestor().getDescripcionDe(list_1.getSelectedValue().toString()));
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
			GroupLayout gl_panel_3 = new GroupLayout(panel_3);
			gl_panel_3.setHorizontalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_3.createSequentialGroup()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGap(10)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
									.addComponent(getLblSudoku(), GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLblDescripcin(), GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGap(90)
								.addComponent(getLabel_1()))
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGap(66)
								.addComponent(getLabel())))
						.addContainerGap(129, Short.MAX_VALUE))
			);
			gl_panel_3.setVerticalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_3.createSequentialGroup()
						.addGap(11)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(getLblSudoku())
							.addComponent(getLabel()))
						.addGap(23)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addComponent(getLabel_1())
							.addComponent(getLblDescripcin())))
			);
			panel_3.setLayout(gl_panel_3);
		}
		return panel_3;
	}
	
	private JList<String> getList_1() throws ExcepcionConectarBD {
		//La lista se llena con los IDs de los logros existentes
		if (list_1 == null) {
			list_1 = new JList<String>();
			list_1.setVisibleRowCount(100);
		}
		list_1.setModel(GestorLogros.getGestor().verLogros(GestorSesion.getGestor().getUserSesion()));
		return list_1;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("Sudoku:");
		}
		return lblSudoku;
	}
	private JLabel getLblDescripcin() {
		if (lblDescripcin == null) {
			lblDescripcin = new JLabel("Descripci\u00F3n:");
		}
		return lblDescripcin;
	}
	private JLabel getLabel() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con el ID del sudoku al cual pertenece el logro que se ha seleccionado en la lista
		if (label == null) {
			label = new JLabel("");
			if(list_1.getSelectedValue()!=null){
				label.setText(GestorLogros.getGestor().getSudoku(list_1.getSelectedValue().toString()));}
		}
		return label;
	}
	private JLabel getLabel_1() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con la descripcion del logro que se ha seleccionado en la lista
		if (label_1 == null) {
			label_1 = new JLabel("");
			if(list_1.getSelectedValue()!=null){
				label_1.setText(GestorLogros.getGestor().getDescripcionDe(list_1.getSelectedValue().toString()));}
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
                textArea.setText("No has jugado ningún sudoku!");
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
                textArea_1.setText("¡No has participado en ningún reto!");
            }
        } catch (ExcepcionConectarBD e) {
            e.printStackTrace();
        }
    }
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					VentanaJugador.getVentana().setVisible(true);
				}
			});
		}
		return btnVolver;
	}
	private JButton getBtnVolver1() {
		if (btnVolver1 == null) {
			btnVolver1 = new JButton("Volver");
			btnVolver1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					VentanaJugador.getVentana().setVisible(true);
				}
			});
		}
		return btnVolver1;
	}
}
