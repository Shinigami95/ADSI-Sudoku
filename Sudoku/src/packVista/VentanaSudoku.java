package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import packControladores.GestorPartida;
import packModelo.Partida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VentanaSudoku extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private static VentanaSudoku mVSudoku;
	private JPanel contentPane;
	private JPanel pan_titulo;
	private JLabel lblTitulo;
	private JPanel pan_botones;
	private JLabel lblIdSud;
	private JButton btnAyuda;
	private JButton btnRendirse;
	private JPopupMenu miPopupMenu;
	private ComponentCasillaGenerica[][] matrizCasillas;
	private JPanel[][] matrizSecciones;
	private Controlador controlador = null;
	private JPanel pan_sudoku;
	private JLabel labelTiempo;
	private JLabel labelTiempoValor;
	private JCheckBox chckbxBorrador;
	private JButton btnParar;
	private JMenuItem[] jmiNumeros;
	private JMenuItem jmiQuitarValor;
	private JMenuItem jmiComprobarValor;
	private JPopupMenu.Separator jpms1;
	private JPopupMenu.Separator jpms2;
	private JLabel lblBorrador;
	private JLabel lblAyudas;
	private JLabel labelAyudasValor;
	private JLabel lblComprobaciones;
	private JLabel labelComprValor;
	private JSeparator separator;
	private JSeparator separator_1;
	
	/**
	 * Launch 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSudoku frame = VentanaSudoku.getVentanaSudoku();
					frame.setVisible(true);
					frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private VentanaSudoku() {
		initialize();
		cargarSudoku();
	}
	
	private void cargarSudoku() {
		System.out.println("Cargando");
		GestorPartida.getGestor().addObserver(this);
		
		for(int i=0; i<this.getMatrizCasillas().length;i++){
			for(int j=0; j<this.getMatrizCasillas()[i].length;j++){
				GestorPartida.getGestor().addObserver(this.getCasillaSud(i, j),i,j);
			}
		}
		System.out.println("Observando");
	}

	public static VentanaSudoku getVentanaSudoku(){
		if(mVSudoku==null){
			mVSudoku = new VentanaSudoku();
		}
		return mVSudoku;
	}

	private void initialize() {
		setBounds(100, 100, 650, 650);
		setMinimumSize(new Dimension(500, 500));
		setTitle("Sudoku - Juego");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPan_sudoku(), BorderLayout.CENTER);
		contentPane.add(getPan_titulo(), BorderLayout.NORTH);
		contentPane.add(getPan_botones(), BorderLayout.WEST);
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
	
	private ComponentCasillaGenerica[][] getMatrizCasillas(){
		if (matrizCasillas == null) {
			matrizCasillas = new ComponentCasillaGenerica[9][9];
		}
		return matrizCasillas;
	}
	
	private ComponentCasillaGenerica getCasillaSud(int pX, int pY){
		if (getMatrizCasillas()[pX][pY] == null) {
			getMatrizCasillas()[pX][pY] = new ComponentCasillaGenerica(getMiPopupMenu(),pX,pY);
		}
		return getMatrizCasillas()[pX][pY];
	}
	
	private JMenuItem[] getJmiNumeros(){
		if (jmiNumeros==null){
			jmiNumeros = new JMenuItem[9];
			JMenuItem menuItem;
			for (int i = 1; i <= jmiNumeros.length; i++) {
	            menuItem = new JMenuItem("" + i + "");
	            menuItem.setActionCommand("asignarValor");
	            menuItem.addMouseListener(this.getControlador());
	            jmiNumeros[i-1] = menuItem;
	        }
		}
		return jmiNumeros;
	}
	
	private JMenuItem getJmiQuitarValor(){
		if (jmiQuitarValor==null){
			jmiQuitarValor = new JMenuItem("Quitar Valor");
			jmiQuitarValor.setActionCommand("quitarValor");
			jmiQuitarValor.addMouseListener(this.getControlador());
		}
		return jmiQuitarValor;
	}
	
	private JMenuItem getJmiComprobarValor(){
		if (jmiComprobarValor==null){
			jmiComprobarValor = new JMenuItem("Comprobar Valor");
			jmiComprobarValor.setActionCommand("comprobarValor");
			jmiComprobarValor.addMouseListener(this.getControlador());
		}
		return jmiComprobarValor;
	}
	
	private JPopupMenu.Separator getSeparador1(){
		if(jpms1==null){
			jpms1 = new JPopupMenu.Separator();
		}
		return jpms1;
	}
	
	private JPopupMenu.Separator getSeparador2(){
		if(jpms2==null){
			jpms2 = new JPopupMenu.Separator();
		}
		return jpms2;
	}
	
	private JPopupMenu getMiPopupMenu() {
		if (miPopupMenu==null){
			miPopupMenu = new JPopupMenu();
	        for (int i = 0; i < getJmiNumeros().length; i++) {
	            miPopupMenu.add(getJmiNumeros()[i]);
	        }
	        miPopupMenu.add(getSeparador1());
	        //Menu quitar valor
	        miPopupMenu.add(getJmiQuitarValor());
	        miPopupMenu.add(getSeparador2());
	        //Menu comprobar valor
	        miPopupMenu.add(getJmiComprobarValor());
	        miPopupMenu.setFocusable(true);
	        miPopupMenu.addPopupMenuListener(this.getControlador());
		}
		return miPopupMenu;
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
			lblTitulo.setForeground(Color.BLACK);
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTitulo.setBackground(Color.WHITE);
		}
		return lblTitulo;
	}
	
	private JPanel getPan_botones() {
		if (pan_botones == null) {
			pan_botones = new JPanel();
			pan_botones.setBackground(Color.WHITE);
			pan_botones.setLayout(new BoxLayout(pan_botones, BoxLayout.PAGE_AXIS));
			pan_botones.add(getLabelTiempo());
			pan_botones.add(getLabelTiempoValor());
			pan_botones.add(getLblAyudas());
			pan_botones.add(getLabelAyudasValor());
			pan_botones.add(getLblComprobaciones());
			pan_botones.add(getLabelComprValor());
			pan_botones.add(getSeparator());
			pan_botones.add(getLblBorrador());
			pan_botones.add(getChckbxBorrador());
			pan_botones.add(getSeparator_1());
			pan_botones.add(getBtnParar());
			pan_botones.add(getBtnAyuda());
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
	
	private JButton getBtnParar() {
		if (btnParar == null) {
			btnParar = new JButton("Parar");
			btnParar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnParar.addActionListener(getControlador());
			btnParar.setActionCommand("PRESS_btnParar");
		}
		return btnParar;
	}
	
	private JLabel getLabelTiempo() {
		if (labelTiempo == null) {
			labelTiempo = new JLabel("Tiempo:");
			labelTiempo.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return labelTiempo;
	}
	
	private JLabel getLabelTiempoValor() {
		if (labelTiempoValor == null) {
			labelTiempoValor = new JLabel("<Tiempo>");
		}
		return labelTiempoValor ;
	}
	
	private JCheckBox getChckbxBorrador() {
		if (chckbxBorrador == null) {
			chckbxBorrador = new JCheckBox("");
			chckbxBorrador.addActionListener(getControlador());
			chckbxBorrador.setActionCommand("PRESS_chckbxBorrador");
		}
		return chckbxBorrador;
	}
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador ;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener, MouseListener, PopupMenuListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String action = arg0.getActionCommand();
			if(action.equals("PRESS_chckbxBorrador")){
				VentanaSudoku.getVentanaSudoku().switchBorrador();
			} else if(action.equals("PRESS_btnParar")){
				VentanaSudoku.getVentanaSudoku().pausar();
			} else if(action.equals("PRESS_btnAyuda")){
				VentanaSudoku.getVentanaSudoku().ayudar();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			JMenuItem menuItem = (JMenuItem) e.getSource();
			ComponentCasillaGenerica cas = (ComponentCasillaGenerica)getMiPopupMenu().getInvoker();
		    if (menuItem.getActionCommand().equalsIgnoreCase("asignarValor")){    	
		    	VentanaSudoku.getVentanaSudoku().setValorCasilla(e, cas);
		    }
		    else if(menuItem.getActionCommand().equalsIgnoreCase("quitarValor")){
		    	VentanaSudoku.getVentanaSudoku().quitarValorCasilla(e, cas);
		    }
		    else if (menuItem.getActionCommand().equalsIgnoreCase("comprobarValor")){
		    	VentanaSudoku.getVentanaSudoku().comprobarValorCasilla(e, cas);
		    }
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			ComponentCasillaGenerica cas = (ComponentCasillaGenerica)getMiPopupMenu().getInvoker();
			System.out.println(cas);
			char valorCas = GestorPartida.getGestor().getValorCasillaSudoku(cas.getCorX(), cas.getCorY());
			if (cas.esBorrador() || valorCas=='0' || GestorPartida.getGestor().getNumCompr() == 0){
				VentanaSudoku.getVentanaSudoku().getSeparador2().setVisible(false);
				VentanaSudoku.getVentanaSudoku().getJmiComprobarValor().setVisible(false);
			} 
			else {
				VentanaSudoku.getVentanaSudoku().getSeparador2().setVisible(true);
				VentanaSudoku.getVentanaSudoku().getJmiComprobarValor().setVisible(true);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Partida){
			//tiempo
			String tiempo = GestorPartida.getGestor().tiempoAString();
			getLabelTiempoValor().setText("     "+tiempo);
			//ayudas
			int nayud = GestorPartida.getGestor().getNumAyudas();
			if (nayud<=0) getBtnAyuda().setEnabled(false);
			else getBtnAyuda().setEnabled(true);
			getLabelAyudasValor().setText("     "+nayud);
			//comprobacion
			int ncompr = GestorPartida.getGestor().getNumCompr();
			getLabelComprValor().setText("     "+ncompr);
		}
	}

	public void ayudar() {
		GestorPartida.getGestor().ayudar();
	}

	public void comprobarValorCasilla(MouseEvent e, ComponentCasillaGenerica cas) {
		// TODO Auto-generated method stub
		boolean result = GestorPartida.getGestor().comprobar(cas.getCorX(), cas.getCorY());
		if(result){
			JOptionPane.showMessageDialog(this, "OK");
		}
		else{
			JOptionPane.showMessageDialog(this, ">.<");
		}
	}

	public void quitarValorCasilla(MouseEvent e, ComponentCasillaGenerica cas) {
		System.out.println("VentSud.quitarValor -> ("+cas.getCorX()+","+cas.getCorY()+")");
        GestorPartida.getGestor().quitarValor(cas.getCorX(), cas.getCorY());
	}

	public void setValorCasilla(MouseEvent e, ComponentCasillaGenerica cas) {
		JMenuItem menuItem = (JMenuItem) e.getComponent();
		char valor= menuItem.getText().charAt(0);
		System.out.println("VentSud.setValorCasilla -> ("+cas.getCorX()+","+cas.getCorY()+")");
        GestorPartida.getGestor().setValor(valor, cas.getCorX(), cas.getCorY());
	}

	public void switchBorrador() {
		GestorPartida.getGestor().switchBorrador();
		if(GestorPartida.getGestor().estaActivoBorrador()==true) System.out.println("Borrador Activado");
		else System.out.println("Borrador Desactivado");
	}
	
	public void pausar() {
		// TODO Auto-generated method stub
		GestorPartida.getGestor().pausar();
		this.setVisible(false);
		JOptionPane.showMessageDialog(this, "PAUSA");
		this.setVisible(true);
		GestorPartida.getGestor().reanudar();
	}

	private JLabel getLblBorrador() {
		if (lblBorrador == null) {
			lblBorrador = new JLabel("Borrador:");
		}
		return lblBorrador;
	}
	private JLabel getLblAyudas() {
		if (lblAyudas == null) {
			lblAyudas = new JLabel("Ayudas:");
			lblAyudas.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblAyudas;
	}
	private JLabel getLabelAyudasValor() {
		if (labelAyudasValor == null) {
			labelAyudasValor = new JLabel("<Ayudas>");
		}
		return labelAyudasValor;
	}
	private JLabel getLblComprobaciones() {
		if (lblComprobaciones == null) {
			lblComprobaciones = new JLabel("Comprobaciones:");
			lblComprobaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblComprobaciones;
	}
	private JLabel getLabelComprValor() {
		if (labelComprValor == null) {
			labelComprValor = new JLabel("<Compr>");
			labelComprValor.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return labelComprValor;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
}