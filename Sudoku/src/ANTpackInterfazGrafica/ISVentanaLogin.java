package ANTpackInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import packControladores.GestorPartida;
import packExcepciones.ExcepcionNoHaySudokuCargado;
import packModelo.CatalogoUsuarios;
import packModelo.Dificultad;

public class ISVentanaLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private static ISVentanaLogin mVLogin;
	private JPanel contentPane;
	private JPanel panelSudoku;
	private JPanel panelDatos;
	private JPanel panelOK;
	private JPanel panelUsuario;
	private JPanel panelNivel;
	private JLabel lblSudoku;
	private JLabel lblNombreUsuario;
	private JLabel lblNivel;
	private JComboBox<String> cBoxNivel;
	private JTextField tfUsuario;
	private JButton btnOK;
	private JButton btnGuardarDatos;
	private JButton btnRanking;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ISVentanaLogin frame = getVentanaLogin();
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
	private ISVentanaLogin() {
		super();
		initialize();
	}
	
	public static ISVentanaLogin getVentanaLogin(){
		if(mVLogin==null){
			mVLogin = new ISVentanaLogin();
		}
		return mVLogin;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sudoku - Login");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelSudoku(), BorderLayout.NORTH);
		contentPane.add(getPanelDatos(), BorderLayout.CENTER);
		contentPane.add(getPanelOK(), BorderLayout.SOUTH);
	}

	private JPanel getPanelSudoku() {
		if (panelSudoku == null) {
			panelSudoku = new JPanel();
			panelSudoku.setBackground(Color.WHITE);
			panelSudoku.setBorder(new LineBorder(new Color(0, 0, 0), 5));
			panelSudoku.add(getLblSudoku());
		}
		return panelSudoku;
	}
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setBackground(Color.WHITE);
			panelDatos.setLayout(new GridLayout(0, 1, 0, 0));
			panelDatos.add(getPanelUsuario());
			panelDatos.add(getPanelNivel());
		}
		return panelDatos;
	}
	private JPanel getPanelOK() {
		if (panelOK == null) {
			panelOK = new JPanel();
			panelOK.setBackground(Color.WHITE);
			panelOK.add(getBtnOK());
			panelOK.add(getBtnGuardarDatos());
			panelOK.add(getBtnRanking());
		}
		return panelOK;
	}
	private JPanel getPanelUsuario() {
		if (panelUsuario == null) {
			panelUsuario = new JPanel();
			panelUsuario.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelUsuario.getLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(20);
			panelUsuario.add(getLblNombreUsuario());
			panelUsuario.add(getTfUsuario());
		}
		return panelUsuario;
	}
	private JPanel getPanelNivel() {
		if (panelNivel == null) {
			panelNivel = new JPanel();
			panelNivel.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelNivel.getLayout();
			flowLayout.setVgap(30);
			flowLayout.setHgap(30);
			panelNivel.add(getLblNivel());
			panelNivel.add(getCBoxNivel());
		}
		return panelNivel;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("SUDOKU");
			lblSudoku.setFont(new Font("Microsoft Himalaya", Font.BOLD, 24));
		}
		return lblSudoku;
	}
	private JLabel getLblNombreUsuario() {
		if (lblNombreUsuario == null) {
			lblNombreUsuario = new JLabel("Nombre Usuario: ");
			lblNombreUsuario.setFont(new Font("Microsoft New Tai Lue", Font.ITALIC, 15));
		}
		return lblNombreUsuario;
	}
	private JLabel getLblNivel() {
		if (lblNivel == null) {
			lblNivel = new JLabel("Escoger Nivel: ");
			lblNivel.setFont(new Font("Microsoft New Tai Lue", Font.ITALIC, 15));
		}
		return lblNivel;
	}
	private JComboBox<String> getCBoxNivel() {
		if (cBoxNivel == null) {
			cBoxNivel = new JComboBox<String>();
			cBoxNivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cBoxNivel.setModel(new DefaultComboBoxModel<String>(new String[] {"Facil", "Medio", "Dificil"}));
		}
		return cBoxNivel;
	}
	private JTextField getTfUsuario() {
		if (tfUsuario == null) {
			tfUsuario = new JTextField();
			tfUsuario.addKeyListener(getControlador());
			tfUsuario.setColumns(10);
		}
		return tfUsuario;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnOK.addActionListener(getControlador());
			btnOK.setActionCommand("PRESS_btnOK");
		}
		return btnOK;
	}
	
	private JButton getBtnGuardarDatos() {
		if (btnGuardarDatos == null) {
			btnGuardarDatos = new JButton("Guardar");
			btnGuardarDatos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnGuardarDatos.addActionListener(getControlador());
			btnGuardarDatos.setActionCommand("PRESS_btnGuardarDatos");
		}
		return btnGuardarDatos;
	}
	
	private JButton getBtnRanking() {
		if (btnRanking == null) {
			btnRanking = new JButton("Ranking");
			btnRanking.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnRanking.addActionListener(getControlador());
			btnRanking.setActionCommand("PRESS_btnRanking");
		}
		return btnRanking;
	}

	private void guardarDatosUsuarios() {
		CatalogoUsuarios.getCatalogo().guardarFichero();
	}

	private void cargarUsuarioConSudoku() {
		try{
			String userName = tfUsuario.getText();
			String levelSelected = (String) cBoxNivel.getModel().getSelectedItem();
			Dificultad dif;
			if(levelSelected.equals("Dificil")) dif = Dificultad.DIFICIL;
			else if(levelSelected.equals("Medio")) dif = Dificultad.MEDIO;
			else dif = Dificultad.FACIL;
			GestorPartida.getGestor().cargarSudParaUs(dif, userName);
			ISVentanaJuego.getVentanaJuego().cargarSudoku();
			ISVentanaJuego.getVentanaJuego().setVisible(true);
			ISVentanaLogin.getVentanaLogin().dispose();
		}
		catch(ExcepcionNoHaySudokuCargado e){
			JOptionPane.showMessageDialog(this, "No hay sudokus disponibles");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Ha habido error");
			e.printStackTrace();
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
			guardarDatosUsuarios();
			System.exit(0);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String action = arg0.getActionCommand();
			if(action.equals("PRESS_btnOK")){
				cargarUsuarioConSudoku();
			}
			else if (action.equals("PRESS_btnGuardarDatos")){
				guardarDatosUsuarios();
			}
			else if (action.equals("PRESS_btnRanking")){
				ISVentanaRanking.getVentanaRanking().setVisible(true);
				ISVentanaLogin.getVentanaLogin().dispose();
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				cargarUsuarioConSudoku();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
}
