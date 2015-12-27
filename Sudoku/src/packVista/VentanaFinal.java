package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class VentanaFinal extends JFrame {

	private JPanel contentPane;
	private JLabel lblHfhrfth;
	private static VentanaFinal mVentana = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFinal frame = new VentanaFinal();
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
	private VentanaFinal() {
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblHfhrfth(), BorderLayout.CENTER);
	}

	public static VentanaFinal getVentana(){
		if(mVentana==null){
			mVentana = new VentanaFinal();
		}
		return mVentana;
	}
	
	private JLabel getLblHfhrfth() {
		if (lblHfhrfth == null) {
			lblHfhrfth = new JLabel("Final");
		}
		return lblHfhrfth;
	}
}
