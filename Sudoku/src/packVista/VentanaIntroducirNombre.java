package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import packControladores.ConexionBD;
import packControladores.GestorRetos;
import packControladores.GestorSesion;
import packExcepciones.ExcepcionConectarBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIntroducirNombre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnRetar;
	private JButton btnVolver;
	private JList<String> list;
	private JLabel lblNombre;
	private JLabel lblUsuarioARetar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIntroducirNombre frame = new VentanaIntroducirNombre();
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
	public VentanaIntroducirNombre() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getList());
			list.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					String usuario = list.getSelectedValue().toString();
					lblNombre.setText(usuario);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return scrollPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnRetar());
			panel.add(getBtnVolver());
			panel.add(getLblNombre());
			panel.add(getLblUsuarioARetar());
		}
		return panel;
	}
	private JButton getBtnRetar() {
		if (btnRetar == null) {
			btnRetar = new JButton("Retar");
			btnRetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(lblNombre.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "¡No estás retando a nadie!");
					}else{
						String retador = GestorSesion.getGestor().getUserSesion();
						String retado = lblNombre.getText().toString();
						int idSudoku;
						try {
							ResultSet res = ConexionBD.getConexionBD().consultaBD("SELECT ID_SUDOKU FROM JUGADO WHERE NOMBRE_JUG ='"+retador+"' ORDER BY FECHA;");
							res.next();
							idSudoku = Integer.parseInt(res.getString("ID_SUDOKU"));
							ConexionBD.getConexionBD().closeResult(res);
							GestorRetos.getGestorRetos().addReto(retador, retado, idSudoku);
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ExcepcionConectarBD e) {
							e.printStackTrace();
						}
					}
				}
			});
			btnRetar.setBounds(10, 126, 89, 23);
		}
		return btnRetar;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO no se a que ventana redirige
				}
			});
			btnVolver.setBounds(125, 126, 89, 23);
		}
		return btnVolver;
	}
	private JList<String> getList() {
		if (list == null) {
			list = new JList<String>();
			list.setVisibleRowCount(100);
		}
		return list;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("");
			lblNombre.setBounds(95, 41, 46, 14);
		}
		return lblNombre;
	}
	private JLabel getLblUsuarioARetar() {
		if (lblUsuarioARetar == null) {
			lblUsuarioARetar = new JLabel("UsuarioARetar:");
			lblUsuarioARetar.setBounds(10, 41, 75, 14);
		}
		return lblUsuarioARetar;
	}
}
