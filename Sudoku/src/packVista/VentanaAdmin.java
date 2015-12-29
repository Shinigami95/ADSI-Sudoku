package packVista;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblHolaAdministrador;
	private static VentanaAdmin ventana;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void getVentana(){
		if(ventana==null){
			ventana=new VentanaAdmin();
		}
		ventana.setVisible(true);
	}
	private VentanaAdmin(){
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getLblHolaAdministrador());
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Administrar Sudokus");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnNewButton.setBounds(129, 83, 200, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Administrar Logros");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						VentanaAdminLogros.main(null);
						VentanaAdmin.this.setVisible(false);
				}
			});
			btnNewButton_1.setBounds(129, 136, 200, 23);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Estad\u00EDsticas");
			btnNewButton_2.setBounds(129, 187, 200, 23);
		}
		return btnNewButton_2;
	}
	private JLabel getLblHolaAdministrador() {
		if (lblHolaAdministrador == null) {
			lblHolaAdministrador = new JLabel("Hola Administrador");
			lblHolaAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 27));
			lblHolaAdministrador.setBounds(84, 11, 237, 50);
		}
		return lblHolaAdministrador;
	}
}
