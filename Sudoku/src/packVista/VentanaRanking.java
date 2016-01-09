package packVista;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import packControladores.ConexionBD;
import packControladores.GestorRanking;
import packExcepciones.ExcepcionConectarBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaRanking mVRanking;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JPanel panel_3;
	private JList<Integer> list;
	private JLabel lblDificultad;
	private JLabel lblDificultad2;
	private JLabel lblActivo;
	private JLabel lblActivo2;
	private JLabel lblRanking;
	private JTextArea textArea;
	private JButton btnVolver;
	private JButton btnVolver1;
	private JTextArea textArea_2;
	private JTextArea textArea_1;
	private JButton btnVolver2;
	private JLabel lblRnkPtos;
	private JLabel lblRankingPorRetos;

	/**
	 * Create the frame.
	 * @throws ExcepcionConectarBD 
	 * @throws SQLException 
	 */
	public VentanaRanking() throws ExcepcionConectarBD, SQLException {
		initialize();
		textArea_1.setEditable(false);
        obtenerRankingPuntos();
        textArea.setEditable(false);
        obtenerRankingRetos();
	}
	
	public static VentanaRanking getVentana() throws ExcepcionConectarBD, SQLException{
		if(mVRanking==null){
			mVRanking = new VentanaRanking();
		}
		return mVRanking;
	}
	
	private void initialize() throws ExcepcionConectarBD, SQLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				VentanaJugador.getVentana().setVisible(true);
				dispose();
				mVRanking = null;
			}
		});
		setBounds(100, 100, 600, 600);
		setTitle("Ranking");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(getTabbedPane());
	}

	private JTabbedPane getTabbedPane() throws ExcepcionConectarBD, SQLException {
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
			panel.setLayout(null);
			panel.add(getTextArea_1());
			panel.add(getBtnVolver2());
			panel.add(getLblRnkPtos());
			
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
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap()
								.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addGap(54)
								.addComponent(getBtnVolver(), GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(102)
								.addComponent(getLblRankingPorRetos(), GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(64, Short.MAX_VALUE))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(getLblRankingPorRetos(), GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addComponent(getBtnVolver())
								.addGap(244))))
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
	
	private JScrollPane getScrollPane() throws ExcepcionConectarBD, SQLException {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getList());
			list.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent arg0) {}

				@Override
				public void mouseEntered(MouseEvent arg0) {}

				@Override
				public void mouseExited(MouseEvent arg0) {}
				//seleccionamos un id del sudoku para ver su ranking
				@Override
				public void mousePressed(MouseEvent arg0) {
					int idSudoku = list.getSelectedValue().intValue(); //Cogemos el valor
					try {
						String ranking = GestorRanking.getGestorRanking().obtenerRankingUnSudoku(idSudoku);
						if(ranking.length() > 0){
							textArea_2.setText(ranking);
						}else{
							textArea_2.setText("¡No hay nadie en el ranking de este sudoku!");
						}
						//Cogemos los respectivos valores para ponerlos en las etiquetas de las ventanas
						ResultSet res = ConexionBD.getConexionBD().consultaBD("SELECT DIFICULTAD FROM JUGADO INNER JOIN SUDOKU ON ID_SUDOKU=ID_S WHERE ID_SUDOKU='"+list.getSelectedValue().toString()+"';");
						res.next();
						String id = res.getString("DIFICULTAD");
						ConexionBD.getConexionBD().closeResult(res);
						lblDificultad2.setText(id);
						ResultSet res1 = ConexionBD.getConexionBD().consultaBD("SELECT ACTIVO FROM JUGADO INNER JOIN SUDOKU ON ID_SUDOKU=ID_S WHERE ID_SUDOKU='"+list.getSelectedValue().toString()+"';");
						res1.next();
						String activo = res1.getString("ACTIVO");
						ConexionBD.getConexionBD().closeResult(res1);
						lblActivo2.setText(activo);
					} catch (ExcepcionConectarBD e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
		}
		return scrollPane;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.add(getLblDificultad());
			panel_3.add(getLblDificultad2());
			panel_3.add(getLblActivo());
			panel_3.add(getLblActivo2());
			panel_3.add(getLblRanking());
			panel_3.add(getBtnVolver1());
			panel_3.add(getTextArea_2());
		}
		return panel_3;
	}
	private JList<Integer> getList() throws ExcepcionConectarBD, SQLException {
		if (list == null) {
			list = new JList<Integer>();
			DefaultListModel<Integer> listModel = new DefaultListModel<Integer>();
			ResultSet res = ConexionBD.getConexionBD().consultaBD("SELECT ID_S FROM SUDOKU;");
			while(res.next()){
				//Cogemos el entero de todos los sudokus que haya
				int id = Integer.parseInt(res.getString("ID_S"));
				//los añadimos al DefaultListModel para poder escogerlos e imprimirlos en la ventana
				listModel.addElement(id);
			}
			list.setModel(listModel);
			list.setVisibleRowCount(listModel.getSize());
		}
		return list;
	}
	private JLabel getLblDificultad() {
		if (lblDificultad == null) {
			lblDificultad = new JLabel("Dificultad:");
			lblDificultad.setBounds(10, 11, 62, 14);
		}
		return lblDificultad;
	}
	private JLabel getLblDificultad2() {
		if (lblDificultad2 == null) {
			lblDificultad2 = new JLabel("");
			lblDificultad2.setBounds(82, 11, 46, 14);
		}
		return lblDificultad2;
	}
	private JLabel getLblActivo() {
		if (lblActivo == null) {
			lblActivo = new JLabel("Activo:");
			lblActivo.setBounds(10, 40, 39, 14);
		}
		return lblActivo;
	}
	private JLabel getLblActivo2() {
		if (lblActivo2 == null) {
			lblActivo2 = new JLabel("");
			lblActivo2.setBounds(59, 40, 46, 14);
		}
		return lblActivo2;
	}
	private JLabel getLblRanking() {
		if (lblRanking == null) {
			lblRanking = new JLabel("Ranking del sudoku");
			lblRanking.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			lblRanking.setBounds(44, 69, 163, 25);
		}
		return lblRanking;
	}
	private JTextArea getTextArea() {
		//Retos
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(10, 57, 351, 456);
		}
		return textArea;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaJugador.getVentana().setVisible(true);
					dispose();
					mVRanking = null;
				}
			});
		}
		return btnVolver;
	}
	private JButton getBtnVolver1() {
		if (btnVolver1 == null) {
			btnVolver1 = new JButton("Volver");
			btnVolver1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaJugador.getVentana().setVisible(true);
					dispose();
					mVRanking = null;
				}
			});
			btnVolver1.setBounds(268, 490, 91, 23);
		}
		return btnVolver1;
	}
	private void obtenerRankingPuntos(){
        String ranking;
        try {
            ranking = GestorRanking.getGestorRanking().obtenerRankingPuntuacion();
            if(ranking.length() > 0){
                textArea_1.setText(ranking);
            }else{
                textArea_1.setText("¡No hay nadie en el ranking de puntos!");
            }
        } catch (ExcepcionConectarBD e) {
            e.printStackTrace();
        }
    }
	private void obtenerRankingRetos(){
		String ranking;
		try{
			ranking = GestorRanking.getGestorRanking().obtenerRankingRetos();
            if(ranking.length() > 0){
                textArea.setText(ranking);
            }else{
                textArea.setText("¡No hay nadie en el ranking de retos!");
            }
        } catch (ExcepcionConectarBD e) {
            e.printStackTrace();
        }
	}
	private JTextArea getTextArea_2() {
		if (textArea_2 == null) {
			textArea_2 = new JTextArea();
			textArea_2.setBounds(10, 105, 221, 370);
		}
		return textArea_2;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setBounds(10, 57, 351, 456);
		}
		return textArea_1;
	}
	private JButton getBtnVolver2() {
		if (btnVolver2 == null) {
			btnVolver2 = new JButton("Volver");
			btnVolver2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaJugador.getVentana().setVisible(true);
					dispose();
					mVRanking = null;
				}
			});
			btnVolver2.setBounds(415, 258, 89, 23);
		}
		return btnVolver2;
	}
	private JLabel getLblRnkPtos() {
		if (lblRnkPtos == null) {
			lblRnkPtos = new JLabel("Ranking por puntos");
			lblRnkPtos.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			lblRnkPtos.setBounds(103, 11, 148, 23);
		}
		return lblRnkPtos;
	}
	private JLabel getLblRankingPorRetos() {
		if (lblRankingPorRetos == null) {
			lblRankingPorRetos = new JLabel("Ranking por retos");
			lblRankingPorRetos.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		}
		return lblRankingPorRetos;
	}
}
