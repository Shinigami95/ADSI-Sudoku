package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
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
	
	private JPopupMenu getMiPopupMenu() {
		if (miPopupMenu==null){
			miPopupMenu = new JPopupMenu();
	        JMenuItem menuItem;
	        for (int i = 1; i <= 9; i++) {
	            menuItem = new JMenuItem("" + i + "");
	            menuItem.setActionCommand("asignarValor");
	            menuItem.addMouseListener(this.getControlador());
	            miPopupMenu.add(menuItem);
	        }
	        miPopupMenu.add(new JPopupMenu.Separator());
	        //Menu quitar valor
	        menuItem = new JMenuItem("Quitar Valor");
	        menuItem.setActionCommand("quitarValor");
	        menuItem.addMouseListener(this.getControlador());
	        miPopupMenu.add(menuItem);
	        miPopupMenu.add(new JPopupMenu.Separator());
	        //Menu comprobar valor
	        menuItem = new JMenuItem("Comprobar Valor");
	        menuItem.setActionCommand("comprobarValor");
	        menuItem.addMouseListener(this.getControlador());
	        miPopupMenu.add(menuItem);
	        miPopupMenu.setFocusable(true);
	        miPopupMenu.addPopupMenuListener(this.getControlador());
System.out.println("PopUp creado");
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
			pan_botones.add(getChckbxBorrador());
			pan_botones.add(getBtnAyuda());
			pan_botones.add(getBtnRendirse());
			pan_botones.add(getLabelTiempo());
			pan_botones.add(getLabelTiempoValor());
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
	
	private JLabel getLabelTiempo() {
		if (labelTiempo == null) {
			labelTiempo = new JLabel("Tiempo");
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
			chckbxBorrador = new JCheckBox("Borrador");
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
			}
		}

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
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Partida){
			getLabelTiempoValor().setText((String)arg1);
		}
	}

	public void comprobarValorCasilla(MouseEvent e, ComponentCasillaGenerica cas) {
		// TODO Auto-generated method stub
		
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
}