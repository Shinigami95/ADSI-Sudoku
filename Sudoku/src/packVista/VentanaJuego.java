package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import packControladores.GestorPartida;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;
import packSudoku.excepciones.ExcepcionValorNoValido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;


public class VentanaJuego extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private static VentanaJuego mVJuego;
	private JPanel contentPane;
	private JPanel pan_titulo;
	private JLabel lblTitulo;
	private JPanel pan_botones;
	private JLabel lblIdSud;
	private JButton btnAyuda;
	private JButton btnRendirse;
	private JTextField[][] matrizCasillas;
	private JPanel[][] matrizSecciones;
	private Controlador controlador = null;
	private JPanel pan_sudoku;
	private JButton btnEsCoherente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
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
	private VentanaJuego() {
		initialize();
	}
	public static VentanaJuego getVentanaJuego(){
		if(mVJuego==null){
			mVJuego = new VentanaJuego();
		}
		return mVJuego;
	}

	private void initialize() {
		setBounds(100, 100, 650, 650);
		setTitle("Sudoku - Juego");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPan_sudoku(), BorderLayout.CENTER);
		contentPane.add(getPan_titulo(), BorderLayout.NORTH);
		contentPane.add(getPan_botones(), BorderLayout.SOUTH);
		addWindowListener(getControlador());
	}
	
	private JPanel getPan_sudoku() {
		if (pan_sudoku == null) {
			pan_sudoku = new JPanel();
			pan_sudoku.setBackground(Color.WHITE);
			pan_sudoku.setLayout(new GridLayout(3,3,0,0));
			for(int i=0; i<3;i++){
				for(int j=0; j<3;j++){
					pan_sudoku.add(getPan_Seccion(i, j));
				}
			}
		}
		return pan_sudoku;
	}
	
	private JPanel[][] getMatriz_Secciones() {
		if (matrizSecciones == null) {
			matrizSecciones = new JPanel[3][3];
		}
		return matrizSecciones;
	}
	
	private JPanel getPan_Seccion(int pX,int pY) {
		if (getMatriz_Secciones()[pX][pY] == null) {
			getMatriz_Secciones()[pX][pY] = new JPanel();
			getMatriz_Secciones()[pX][pY].setBackground(Color.WHITE);
			getMatriz_Secciones()[pX][pY].setBorder(new LineBorder(new Color(0, 0, 0), 2));
			getMatriz_Secciones()[pX][pY].setLayout(new GridLayout(3,3));
			
			for(int i=pX*3; i<=pX*3+2;i++){
				for(int j=pY*3; j<=pY*3+2;j++){
					getMatriz_Secciones()[pX][pY].add(getCasillaSud(i, j));
				}
			}
		}
		
		return getMatriz_Secciones()[pX][pY];
	}
	
	private JTextField[][] getMatrizCasillas(){
		if (matrizCasillas == null) {
			matrizCasillas = new JTextField[9][9];
		}
		return matrizCasillas;
	}
	
	private JTextField getCasillaSud(final int pX,final int pY){
		if (getMatrizCasillas()[pX][pY] == null) {
			getMatrizCasillas()[pX][pY] = new JTextField();
			getMatrizCasillas()[pX][pY].setToolTipText("("+pX+","+pY+")");
			getMatrizCasillas()[pX][pY].setBackground(Color.WHITE);
			getMatrizCasillas()[pX][pY].setHorizontalAlignment(SwingConstants.CENTER);
			getMatrizCasillas()[pX][pY].setFont(new Font("Tahoma", Font.PLAIN, 24));
			getMatrizCasillas()[pX][pY].addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					actualizarCasilla(pX,pY);
				}
			});
		}
		return getMatrizCasillas()[pX][pY];
	}
	private JPanel getPan_titulo() {
		if (pan_titulo == null) {
			pan_titulo = new JPanel();
			pan_titulo.setBorder(new LineBorder(new Color(0, 0, 0), 7));
			pan_titulo.setBackground(Color.WHITE);
			pan_titulo.add(getLblTitulo());
			pan_titulo.add(getLblIdSud());
		}
		return pan_titulo;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("SUDOKU: ");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTitulo.setBackground(Color.WHITE);
		}
		return lblTitulo;
	}
	private JPanel getPan_botones() {
		if (pan_botones == null) {
			pan_botones = new JPanel();
			pan_botones.setBackground(Color.WHITE);
			pan_botones.add(getBtnAyuda());
			pan_botones.add(getBtnEsCoherente());
			pan_botones.add(getBtnRendirse());
		}
		return pan_botones;
	}
	private JLabel getLblIdSud() {
		if (lblIdSud == null) {
			lblIdSud = new JLabel("");
			lblIdSud.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblIdSud.setBackground(Color.WHITE);
		}
		return lblIdSud;
	}
	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("Ayuda");
			btnAyuda.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAyuda.setToolTipText("Pulsar para mostrar las casillas incorrectas.");
			btnAyuda.addActionListener(getControlador());
			btnAyuda.setActionCommand("PRESS_btnAyuda");
		}
		return btnAyuda;
	}
	private JButton getBtnRendirse() {
		if (btnRendirse == null) {
			btnRendirse = new JButton("Rendirse");
			btnRendirse.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnRendirse.setToolTipText("Pulsar para mostrar la soluci\u00F3n.");
			btnRendirse.addActionListener(getControlador());
			btnRendirse.setActionCommand("PRESS_btnRendirse");
		}
		return btnRendirse;
	}
	
	private JButton getBtnEsCoherente() {
		if (btnEsCoherente == null) {
			btnEsCoherente = new JButton("Inf. coherencia");
			btnEsCoherente.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnEsCoherente.setToolTipText("Pulsar para saber si no hay filas, columnas o sectores donde se repita un valor.");
			btnEsCoherente.addActionListener(getControlador());
			btnEsCoherente.setActionCommand("PRESS_btnEsCoherente");
		}
		return btnEsCoherente;
	}

	
	public void cargarSudoku(){
		JTextField jtfAux;
		char cAux;
		for(int i=0; i<getMatrizCasillas().length;i++){
			for(int j=0; j<getMatrizCasillas()[i].length;j++){
				jtfAux = this.getCasillaSud(i, j);
				cAux = GestorPartida.getGestor().getValorCasillaSudoku(i, j);
				if(cAux=='0'){
					jtfAux.setText("");
					jtfAux.setFont(new Font("Tahoma", Font.PLAIN, 24));
					jtfAux.setForeground(new Color(0,0,255));
					jtfAux.setEditable(true);
				}
				else{
					jtfAux.setText(cAux+"");
					jtfAux.setFont(new Font("Tahoma", Font.BOLD, 24));
					jtfAux.setForeground(new Color(0,0,0));
					jtfAux.setEditable(false);
				}
			}
		}
		this.getLblTitulo().setText("USUARIO: "+GestorPartida.getGestor().getNombreUsuario()+" // SUDOKU: "+GestorPartida.getGestor().getSudokuId());
		GestorPartida.getGestor().addObserver(this);
	}
	
	private void actualizarCasilla(int pX, int pY){
		try{
			char c = getValorCasilla(pX,pY);
			GestorPartida.getGestor().setValor(c, pX, pY);
		} catch(ExcepcionValorNoValido e){
			JOptionPane.showMessageDialog(this, "El valor no es v\u00E1lido");
			getCasillaSud(pX, pY).setText("");
			GestorPartida.getGestor().setValor('0', pX, pY);
		}
	}
	
	private char getValorCasilla(int pX,int pY) throws ExcepcionValorNoValido{
		int valorNum;
		try{
			String valorDeCasilla = getMatrizCasillas()[pX][pY].getText();
			if(!valorDeCasilla.equals("")){
				valorNum = Integer.parseInt(valorDeCasilla);
				if(valorNum<1||valorNum>9){
					throw new ExcepcionValorNoValido();
				}
				return valorDeCasilla.charAt(0);
			}
			else{
				return '0';
			}
		} catch(NumberFormatException e){
			throw new ExcepcionValorNoValido();
		}
	}
	
	private void ayudarConSudoku() {
		char valorDeCasilla,valorSolucion;
		try{
			for(int i=0; i<getMatrizCasillas().length;i++){
				for(int j=0; j<getMatrizCasillas()[i].length;j++){
					valorDeCasilla = this.getValorCasilla(i, j);
					if(valorDeCasilla!='0'){
						valorSolucion = GestorPartida.getGestor().getValorCasillaSudokuSolucion(i, j);
						if(valorDeCasilla!=valorSolucion){
							getMatrizCasillas()[i][j].setBackground(new Color(255,0,0));
						}
						else{
							getMatrizCasillas()[i][j].setBackground(new Color(255,255,255));
						}
					}
					else{
						getMatrizCasillas()[i][j].setBackground(new Color(255,255,255));
					}
				}
			}
		} catch (ExcepcionValorNoValido e){
			JOptionPane.showMessageDialog(this, "Hay valores erroneos");
		}
	}
	
	private void rendirse(){
		this.obtenerSolucion();
		JOptionPane.showMessageDialog(this, "Gracias por jugar, mucha suerte la pr\u00F3xima vez.");
		VentanaLogin.getVentanaLogin().setVisible(true);
		VentanaJuego.getVentanaJuego().dispose();
	}
	
	private void obtenerSolucion(){
		JTextField jtfAux;
		char cAux;
		for(int i=0; i<getMatrizCasillas().length;i++){
			for(int j=0; j<getMatrizCasillas()[i].length;j++){
				jtfAux = this.getCasillaSud(i, j);
				cAux = GestorPartida.getGestor().getValorCasillaSudokuSolucion(i, j);
				jtfAux.setText(cAux+"");
			}
		}
	}
	
	private void comprobarcoherenciaSudoku() {
		if(GestorPartida.getGestor().esCorrectoSudoku()){
			JOptionPane.showMessageDialog(this, "Por ahora est\u00E1 bien");
		}
		else{
			JOptionPane.showMessageDialog(this, "Alg\u00FAn n\u00FAmero est\u00E1 mal");
		}	
	}
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador ;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener {

		@Override
		public void windowClosing(WindowEvent e) {
			VentanaLogin.getVentanaLogin().setVisible(true);
			VentanaJuego.getVentanaJuego().dispose();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String action = arg0.getActionCommand();
			if(action.equals("PRESS_btnAyuda")){
				VentanaJuego.getVentanaJuego().ayudarConSudoku();
			}
			else if (action.equals("PRESS_btnRendirse")){
				VentanaJuego.getVentanaJuego().rendirse();
			}
			else if (action.equals("PRESS_btnEsCoherente")){
				VentanaJuego.getVentanaJuego().comprobarcoherenciaSudoku();
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.ponerCasillasBlancas();
		VentanaJuego.getVentanaJuego().estaTerminado();
	}
		
	private void ponerCasillasBlancas(){
		for(int i=0; i<getMatrizCasillas().length;i++){
			for(int j=0; j<getMatrizCasillas()[i].length;j++){
				getMatrizCasillas()[i][j].setBackground(new Color(255,255,255));
			}
		}
	}
	
	private void estaTerminado() {
		if(GestorPartida.getGestor().estaPerfectoSudoku()){
			GestorPartida.getGestor().anadirSudokuAUsuario();
			int dialogResponse = JOptionPane.showConfirmDialog(this, "¡Enhorabuena!\n¿Quieres continuar?", "Sudoku finalizado", JOptionPane.YES_NO_OPTION);
			if(dialogResponse == JOptionPane.YES_OPTION){
				try{
					GestorPartida.getGestor().cargarSudParaUs(GestorPartida.getGestor().getDificultad(), GestorPartida.getGestor().getNombreUsuario());
					VentanaJuego.getVentanaJuego().cargarSudoku();
				}
				catch(ExcepcionNoHaySudokuCargado e){
					JOptionPane.showMessageDialog(this, "No hay sudokus disponibles de esta dificultad");
					VentanaLogin.getVentanaLogin().setVisible(true);
					VentanaJuego.getVentanaJuego().dispose();
				}
			}
			else{
				VentanaLogin.getVentanaLogin().setVisible(true);
				VentanaJuego.getVentanaJuego().dispose();
			}
		}
	}
}
