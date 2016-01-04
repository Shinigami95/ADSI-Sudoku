package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import packControladores.ConexionBD;
import packControladores.GestorLogros;
import packControladores.GestorPartida;
import packControladores.GestorSesion;
import packControladores.GestorTiempo;
import packControladores.GestorTwitter;
import packExcepciones.ExcepcionConectarBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaFinal extends JFrame {

	private JPanel contentPane;
	private static VentanaFinal mVentana;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JPanel panel_1;
	private JButton btnRetar;
	private JButton btnFinalizar;
	private JPanel panel_2;
	private JList list_1;
	private JLabel lblSudoku;
	private JLabel lblDescripcin;
	private JLabel label_12;
	private JLabel label_14;
	private JButton btnCompartir;
	private JButton btnCompartirLogro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorPartida.getGestor().cargarSudokuMANUAL();
					getVentana().setVisible(true);;
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
	private VentanaFinal() throws ExcepcionConectarBD, SQLException {
		initialize();
	}
	
	private void initialize() throws ExcepcionConectarBD, SQLException {
		addWindowListener(new WindowAdapter() {
			//Al cerrar la ventana se nos lleva a la ventanaJugador
			@Override
			public void windowClosed(WindowEvent arg0) {
					dispose();
					VentanaJugador.getVentana().setVisible(true);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getPanel_2(), BorderLayout.CENTER);
	}

	public static VentanaFinal getVentana() throws ExcepcionConectarBD, SQLException{
		if(mVentana==null){
			mVentana = new VentanaFinal();
		}
		return mVentana;
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
					try {
						if(list_1.getSelectedIndex()>-1){btnCompartirLogro.setEnabled(true);
						ResultSet tes=ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
						tes.next();
						String ses=tes.getString("ID_SUDOKU");
						ConexionBD.getConexionBD().closeResult(tes);
						label_12.setText(ses);
						ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
						res.next();
						String des=res.getString("DESCRIPCION");
						ConexionBD.getConexionBD().closeResult(res);
						label_14.setText(des);
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
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.add(getLabel_1());
			panel.add(getLabel());
			panel.add(getLabel_3());
			panel.add(getLabel_2());
			panel.add(getBtnCompartir());
		}
		return panel;
	}
	private JLabel getLabel() {
		//En este label se aÃ±ade el tiempo que ha tardado en hacer el ultimo sudoku
		if (label == null) {
			label = new JLabel(GestorTiempo.getGestor().tiempoAString());
			label.setBounds(0, 0, 51, 14);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Tiempo:");
			label_1.setBounds(0, 0, 38, 14);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		//En estÃ© label se mete los puntos que se han conseguido en el ultimo sudoku
		if (label_2 == null) {
			label_2 = new JLabel(Integer.toString(GestorPartida.getGestor().calcularPuntuacion()));
			label_2.setBounds(0, 0, 49, 14);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Puntuaci\u00F3n:");
			label_3.setBounds(0, 0, 57, 14);
		}
		return label_3;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setHgap(50);
			panel_1.add(getBtnRetar());
			panel_1.add(getBtnFinalizar());
		}
		return panel_1;
	}
	private JButton getBtnRetar() {
		//Este boton llama a la ventana que permite retar a otros usuarios
		if (btnRetar == null) {
			btnRetar = new JButton("Retar");
			btnRetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentanaIntroducirNombre.getVentana().setVisible(true);
					} catch (ExcepcionConectarBD e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnRetar;
	}
	private JButton getBtnFinalizar() {
		//Este boton nos lleva a la ventanaJugador
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
							dispose();
							VentanaJugador.getVentana().setVisible(true);	
				}});
		}
		return btnFinalizar;
	}
	private JPanel getPanel_2() throws SQLException, ExcepcionConectarBD {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.add(getLblSudoku());
			panel_2.add(getLblDescripcin());
			panel_2.add(getLabel_12());
			panel_2.add(getLabel_14());
			panel_2.add(getBtnCompartirLogro());
		}
		return panel_2;
	}
	private JList getList_1() throws ExcepcionConectarBD {
		//La lista se llena con los IDs de los logros existentes
		if (list_1 == null) {
			list_1 = new JList();
			list_1.setVisibleRowCount(100);
		}
		list_1.setModel(GestorLogros.logrosConseguidos(GestorSesion.getGestor().getUserSesion(), GestorPartida.getGestor().getIdSud(), GestorPartida.getGestor().calcularPuntuacion()));
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
			lblDescripcin.setBounds(10, 36, 75, 14);
		}
		return lblDescripcin;
	}
	private JLabel getLabel_12() throws SQLException, ExcepcionConectarBD {
		//Este label se llena con el ID del sudoku al cual pertenece el logro que se ha seleccionado en la lista
		if (label_12 == null) {
			label_12 = new JLabel("");
			if(list_1.getSelectedValue()!=null){
				ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
				res.next();
				String des=res.getString("ID_SUDOKU");
				ConexionBD.getConexionBD().closeResult(res);
			label_12.setText(des);}
		}
		
		return label_12;
	}
	private JLabel getLabel_14() throws ExcepcionConectarBD, SQLException {
		//Este label se llena con la descripcion del logro que se ha seleccionado en la lista
		if (label_14 == null) {
			label_14 = new JLabel("");
			if(list_1.getSelectedValue()!=null){
				ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+list_1.getSelectedValue().toString()+"';");
				res.next();
				String des=res.getString("DESCRIPCION");
				ConexionBD.getConexionBD().closeResult(res);
				label_14.setText(des);}
		}
		return label_14;
	}
	private JButton getBtnCompartir() {
		//Lama al metodo que comparte la puntuacion en Twitter
		if (btnCompartir == null) {
			btnCompartir = new JButton("Compartir");
			btnCompartir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						GestorTwitter.getGestorTwitter().compartirEnTwitter(GestorSesion.getGestor().getUserSesion()+" ha conseguido "+label_2.getText()+
								" puntos en el sudoku "+Integer.toString(GestorPartida.getGestor().getIdSud()));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			});
		}
		return btnCompartir;
	}
	private JButton getBtnCompartirLogro() {
		//Lama al metodo que comparte el logro que se ha seleccionado en Twitter
		if (btnCompartirLogro == null) {
			btnCompartirLogro = new JButton("Compartir Logro");
			btnCompartirLogro.setEnabled(false);
			btnCompartirLogro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(list_1.getSelectedIndex()>-1){
					try {
						GestorTwitter.getGestorTwitter().compartirEnTwitter(GestorSesion.getGestor().getUserSesion()+" ha conseguido el logro "+list_1.getSelectedValue().toString()+" por: "+label_14.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}}else{JOptionPane.showMessageDialog(null, "Seleccione un logro de la lista para compartirlo.");}
				}
			});
			btnCompartirLogro.setBounds(38, 151, 152, 23);
		}
		return btnCompartirLogro;
	}
}

