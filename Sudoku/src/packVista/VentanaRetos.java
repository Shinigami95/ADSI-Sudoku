package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packControladores.ConexionBD;
import packControladores.GestorRetos;
import packControladores.GestorSesion;
import packExcepciones.ExcepcionConectarBD;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

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
	 */
	public VentanaRetos() throws ExcepcionConectarBD {
		initialize();
	}
	
	private void initialize() throws ExcepcionConectarBD {
		setBounds(100, 100, 450, 300);
		setTitle("Lista de retos");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	public static VentanaRetos getVentanaRetos() throws ExcepcionConectarBD, SQLException{
		if(mVRetos==null){
			mVRetos = new VentanaRetos();
		}
		return mVRetos;
	}
	private JScrollPane getScrollPane() throws ExcepcionConectarBD {
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
						//TODO CORREGIR CONSULTAS
						ResultSet tes=ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM LOGRO WHERE ID_L='"+list.getSelectedValue().toString()+"';");
						tes.next();
						String ses=tes.getString("ID_SUDOKU");
						ConexionBD.getConexionBD().closeResult(tes);
						lblSudoku.setText(ses);
						ResultSet res=ConexionBD.getConexionBD().consultaBD("SELECT DESCRIPCION FROM LOGRO WHERE ID_L='"+list.getSelectedValue().toString()+"';");
						res.next();
						String des=res.getString("FECHA");
						ConexionBD.getConexionBD().closeResult(res);
						lblFecha.setText(des);
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
		}
		return panel;
	}
	private JList<String> getList() throws ExcepcionConectarBD {
		if (list == null) {
			String[][] retos = GestorRetos.getGestorRetos().obtenerListadoRetos(GestorSesion.getGestor().getUserSesion());
			String retado ="";
			String[] listadoItems = new String[retos.length];
			int j = 0;
			for(int i=0;i<retos.length;i++){
				retado = retos[i][1];
				listadoItems[j] = retado;
				j++;
			}
//			list = new JList<String>(listadoItems);
			list.setVisibleRowCount(listadoItems.length);
		}
		return list;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("Sudoku:");
			lblSudoku.setBounds(10, 11, 39, 14);
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
	//TODO ACTION PERFORMED
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBounds(10, 142, 89, 23);
		}
		return btnAceptar;
	}
	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.setBounds(109, 142, 89, 23);
		}
		return btnRechazar;
	}
}
