package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packControladores.ConexionBD;
import packControladores.GestorPartida;
import packControladores.GestorRetos;
import packControladores.GestorSesion;
import packControladores.GestorSudokus;
import packExcepciones.ExcepcionConectarBD;
import packExcepciones.ExcepcionNoHaySudokuCargado;
import packModelo.Sudoku;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRetos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaRetos mVRetos;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JList<String> list;
	private JLabel lblSudoku;
	private JLabel lblFecha;
	private JButton btnAceptar;
	private JButton btnRechazar;
	private JLabel lblEstado;
	private JLabel lblSudoku2;
	private JLabel lblEstado2;
	private JLabel lblFecha2;
	private JLabel lblReto2;
	private JLabel lblReto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRetos frame = new VentanaRetos();
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
	 * @throws IOException 
	 */
	public VentanaRetos() throws ExcepcionConectarBD, IOException {
		initialize();
	}
	
	private void initialize() throws ExcepcionConectarBD, IOException {
		setBounds(100, 100, 450, 300);
		setTitle("Lista de retos");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	public static VentanaRetos getVentanaRetos() throws ExcepcionConectarBD, SQLException, IOException{
		if(mVRetos==null){
			mVRetos = new VentanaRetos();
		}
		return mVRetos;
	}
	private JScrollPane getScrollPane() throws ExcepcionConectarBD, IOException {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getList());
			list.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					try {
						ResultSet res = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM RETO WHERE NOMBRE_RETADOR='"+list.getSelectedValue().toString()+"';");
						res.next();
						String idS = res.getString("ID_SUDOKU");
						ConexionBD.getConexionBD().closeResult(res);
						lblSudoku2.setText(idS);
						ResultSet res1 = ConexionBD.getConexionBD().consultaBD("SELECT ESTADO FROM RETO WHERE NOMBRE_RETADOR='"+list.getSelectedValue().toString()+"';");
						res1.next();
						String estado = res1.getString("ESTADO");
						ConexionBD.getConexionBD().closeResult(res1);
						lblEstado2.setText(estado);
						ResultSet res2 = ConexionBD.getConexionBD().consultaBD("SELECT FECHA FROM RETO WHERE NOMBRE_RETADOR='"+list.getSelectedValue().toString()+"';");
						res2.next();
						String fecha = res2.getString("FECHA");
						ConexionBD.getConexionBD().closeResult(res2);
						lblFecha2.setText(fecha);
						ResultSet res3 = ConexionBD.getConexionBD().consultaBD("SELECT ID_R FROM RETO WHERE NOMBRE_RETADOR='"+list.getSelectedValue().toString()+"';");
						res3.next();
						String idR = res3.getString("ID_R");
						ConexionBD.getConexionBD().closeResult(res3);
						lblReto2.setText(idR);
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
			panel.setLayout(null);
			panel.add(getLblSudoku());
			panel.add(getLblFecha());
			panel.add(getBtnAceptar());
			panel.add(getBtnRechazar());
			panel.add(getLblEstado());
			panel.add(getLblSudoku2());
			panel.add(getLblEstado2());
			panel.add(getLblFecha2());
			panel.add(getLblReto2());
			panel.add(getLblReto());
		}
		return panel;
	}
	private JList<String> getList() throws ExcepcionConectarBD, IOException {
		if (list == null) {
			String retos = GestorRetos.getGestorRetos().obtenerListadoRetos(GestorSesion.getGestor().getUserSesion());
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			BufferedReader bufReader = new BufferedReader(new StringReader(retos));
			String linea = null;
			while((linea=bufReader.readLine()) != null){
				listModel.addElement(linea);
			}
			list.setModel(listModel);
			list.setVisibleRowCount(listModel.getSize());
		}
		return list;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("Sudoku:");
			lblSudoku.setBounds(10, 11, 46, 14);
		}
		return lblSudoku;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(10, 62, 39, 14);
		}
		return lblFecha;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!lblSudoku2.getText().isEmpty()){
						int idS = Integer.parseInt(lblSudoku2.getText());
						int idR = Integer.parseInt(lblReto2.getText());
						try {
							GestorRetos.getGestorRetos().aceptarReto(idR);
							Sudoku sudoku = GestorSudokus.getGestor().buscarSudokuPorCodigo(idS);
							int dif = sudoku.getDificultad();
							GestorPartida.getGestor().cargarRetoParaUsSesion(dif, idR);
							VentanaSudoku.getVentana().setVisible(true);
							dispose();
						} catch (ExcepcionConectarBD e1) {
							e1.printStackTrace();
						} catch (ExcepcionNoHaySudokuCargado e1) {
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "¡Selecciona primero un reto!");
					}
				}
			});
			btnAceptar.setBounds(10, 142, 89, 23);
		}
		return btnAceptar;
	}
	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int idR = Integer.parseInt(lblReto2.getText());
					try {
						GestorRetos.getGestorRetos().rechazarReto(idR);
						JOptionPane.showMessageDialog(null, "Has rechazado el reto. Gallina.");
					} catch (ExcepcionConectarBD e1) {
						e1.printStackTrace();
					}
				}
			});
			btnRechazar.setBounds(109, 142, 89, 23);
		}
		return btnRechazar;
	}
	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(10, 36, 46, 14);
		}
		return lblEstado;
	}
	private JLabel getLblSudoku2() {
		if (lblSudoku2 == null) {
			lblSudoku2 = new JLabel("");
			lblSudoku2.setBounds(66, 11, 46, 14);
		}
		return lblSudoku2;
	}
	private JLabel getLblEstado2() {
		if (lblEstado2 == null) {
			lblEstado2 = new JLabel("");
			lblEstado2.setBounds(66, 36, 46, 14);
		}
		return lblEstado2;
	}
	private JLabel getLblFecha2() {
		if (lblFecha2 == null) {
			lblFecha2 = new JLabel("");
			lblFecha2.setBounds(59, 62, 46, 14);
		}
		return lblFecha2;
	}
	private JLabel getLblReto2() {
		if (lblReto2 == null) {
			lblReto2 = new JLabel("");
			lblReto2.setBounds(66, 87, 46, 14);
		}
		return lblReto2;
	}
	private JLabel getLblReto() {
		if (lblReto == null) {
			lblReto = new JLabel("Reto:");
			lblReto.setBounds(10, 87, 39, 14);
		}
		return lblReto;
	}
}
