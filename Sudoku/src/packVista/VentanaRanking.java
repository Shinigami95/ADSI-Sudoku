package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;

import packModelo.CatalogoUsuarios;

import java.awt.event.KeyEvent;

public class VentanaRanking extends JFrame{

	private static final long serialVersionUID = 1L;
	private static VentanaRanking mVRanking;
	private JPanel contentPane;
	private JPanel panelRanking;
	private JPanel panelPuntuaciones;
	private JPanel panelMostrar;
	private JLabel lblPuntuacionesAMostrar;
	private JScrollPane scrollPanePuntuaciones;
	private JLabel lblTitulo;
	private JTextField textNumeroPuntuaciones;
	private JButton btnMostrar;
	private JTextArea textArea;
	private Controlador controlador;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRanking frame = getVentanaRanking();
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
	private VentanaRanking() {
		super();
		initialize();
	}
	
	public static VentanaRanking getVentanaRanking(){
		if(mVRanking==null){
			mVRanking = new VentanaRanking();
		}
		return mVRanking;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Sudoku - Ranking");
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelRanking(), BorderLayout.NORTH);
		contentPane.add(getPanelPuntuaciones(), BorderLayout.CENTER);
		addWindowListener(getControlador());
	}

	private JPanel getPanelRanking() {
		if (panelRanking == null) {
			panelRanking = new JPanel();
			FlowLayout fl_panelRanking = (FlowLayout) panelRanking.getLayout();
			fl_panelRanking.setVgap(15);
			fl_panelRanking.setHgap(15);
			panelRanking.setBackground(Color.WHITE);
			panelRanking.add(getLblTitulo());
		}
		return panelRanking;
	}
	private JPanel getPanelPuntuaciones() {
		if (panelPuntuaciones == null) {
			panelPuntuaciones = new JPanel();
			panelPuntuaciones.setBackground(Color.WHITE);
			panelPuntuaciones.setLayout(new BorderLayout(0, 0));
			panelPuntuaciones.add(getPanelMostrar(), BorderLayout.NORTH);
			panelPuntuaciones.add(getScrollPanePuntuaciones(), BorderLayout.CENTER);
		}
		return panelPuntuaciones;
	}
	private JPanel getPanelMostrar() {
		if (panelMostrar == null) {
			panelMostrar = new JPanel();
			panelMostrar.setBackground(Color.WHITE);
			panelMostrar.add(getLblPuntuacionesAMostrar());
			panelMostrar.add(getTextNumeroPuntuaciones());
			panelMostrar.add(getBtnMostrar());
		}
		return panelMostrar;
	}
	private JLabel getLblPuntuacionesAMostrar() {
		if (lblPuntuacionesAMostrar == null) {
			lblPuntuacionesAMostrar = new JLabel("Puntuaciones a mostrar");
			lblPuntuacionesAMostrar.setBackground(Color.WHITE);
		}
		return lblPuntuacionesAMostrar;
	}
	private JScrollPane getScrollPanePuntuaciones() {
		if (scrollPanePuntuaciones == null) {
			scrollPanePuntuaciones = new JScrollPane();
			scrollPanePuntuaciones.setViewportView(getTextArea());
		}
		return scrollPanePuntuaciones;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("RANKING");
			lblTitulo.setBackground(Color.WHITE);
		}
		return lblTitulo;
	}
	private JTextField getTextNumeroPuntuaciones() {
		if (textNumeroPuntuaciones == null) {
			textNumeroPuntuaciones = new JTextField();
			textNumeroPuntuaciones.addKeyListener(getControlador());
			textNumeroPuntuaciones.setToolTipText("N\u00FAmero de puntuaciones que se quieran mostrar");
			textNumeroPuntuaciones.setBackground(Color.WHITE);
			textNumeroPuntuaciones.setColumns(3);
		}
		return textNumeroPuntuaciones;
	}
	
	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("MOSTRAR");
			btnMostrar.setBackground(Color.WHITE);
			btnMostrar.addActionListener(getControlador());
			btnMostrar.setActionCommand("PRESS_btnMostrar");
		}
		return btnMostrar;
	}
	
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
	
	private void mostrarElementos(){
		try{
			int numero = Integer.parseInt(textNumeroPuntuaciones.getText());
			if(numero<=0||numero>1000){
				throw new Exception();
			}
		    getTextArea().setText(CatalogoUsuarios.getCatalogo().obtenerDatosDeLosMejores(numero));
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Tienes que introducir un número entre 1 y 1000");
		}
	}
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador ;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener, KeyListener{

		@Override
		public void windowClosing(WindowEvent e) {
			VentanaLogin.getVentanaLogin().setVisible(true);
			VentanaRanking.getVentanaRanking().dispose();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String action = arg0.getActionCommand();
			if(action.equals("PRESS_btnMostrar")){
				mostrarElementos();
			}
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				mostrarElementos();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
}
