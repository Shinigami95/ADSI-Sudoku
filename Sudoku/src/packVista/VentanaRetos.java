package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import packControladores.GestorPartida;
import packControladores.GestorRetos;
import packExcepciones.ExcepcionConectarBD;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaRetos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaRetos mVent;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JList<String> listRetos;
	private JLabel lblIdReto;
	private JLabel lblFecha;
	private JButton btnAceptar;
	private JButton btnRechazar;
	private JLabel lblEstado;
	private JLabel lblIdRetoValor;
	private JLabel lblEstadoValor;
	private JLabel lblFechaValor;
	private JLabel lblRetadorValor;
	private JLabel lblRetador;
	private Controlador controlador;
	private JLabel lblIdSudoku;
	private JLabel lblIdSudokuValor;
	private DefaultListModel<String> defListModelRetos;

	private VentanaRetos(){
		initialize();
	}
	
	private void initialize(){
		setBounds(100, 100, 480, 300);
		setMinimumSize(new Dimension(480, 300));
		addWindowListener(getControlador());
		setTitle("Lista de retos pendientes");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	public static VentanaRetos getVentana(){
		if(mVent==null){
			mVent = new VentanaRetos();
			mVent.cargarDatos();
		}
		return mVent;
	}
	
	private void cargarDatos(){
		this.actualizarListaRetosPendientes();
	}
	
	private void actualizarValoresReto(String pIdReto){
		try {
			String[] reto = GestorRetos.getGestor().getInfoReto(pIdReto);
			String idReto = reto[0];
			String nombreRetador = reto[1];
			String idSud = reto[2];
			String estado = reto[3];
			String fecha = reto[4];
			getLblIdRetoValor().setText(idReto);
			getLblRetadorValor().setText(nombreRetador);
			getLblIdSudokuValor().setText(idSud);
			getLblEstadoValor().setText(estado);
			getLblFechaValor().setText(fecha);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarListaRetosPendientes(){
		try {
			String[] listaRetPend = GestorRetos.getGestor().obtenerListadoRetosPendientesUsuarioSesion();
			getDefListModelRetos().clear();
			for(int i=0; i<listaRetPend.length; i++){
				getDefListModelRetos().addElement(listaRetPend[i]);;
			}
			if(getDefListModelRetos().size()==0){
				getBtnAceptar().setEnabled(false);
				getBtnRechazar().setEnabled(false);
			} else {
				this.getListRetos().setSelectedIndex(0);
			}
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private JScrollPane getScrollPane(){
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getListRetos());
		}
		return scrollPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(getBtnAceptar(), GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
								.addComponent(getBtnRechazar(), GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(getLblIdReto(), GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLblEstado(), GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLblFecha())
									.addComponent(getLblRetador(), GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLblIdSudoku()))
								.addGap(24)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(getLblIdSudokuValor())
									.addComponent(getLblRetadorValor())
									.addComponent(getLblEstadoValor(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLblFechaValor(), GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addComponent(getLblIdRetoValor(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblIdReto(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblIdRetoValor(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblEstado(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblEstadoValor(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblFecha(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblFechaValor(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblRetador(), GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblRetadorValor()))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblIdSudoku())
							.addComponent(getLblIdSudokuValor()))
						.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getBtnAceptar(), GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(getBtnRechazar(), GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}
	private JList<String> getListRetos(){
		if (listRetos == null) {
			listRetos = new JList<String>();
			listRetos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listRetos.addListSelectionListener(getControlador());
			listRetos.setModel(getDefListModelRetos());
		}
		return listRetos;
	}
	private DefaultListModel<String> getDefListModelRetos(){
		if (defListModelRetos == null) {
			defListModelRetos = new DefaultListModel<String>();
		}
		return defListModelRetos;
	}
	private JLabel getLblIdReto() {
		if (lblIdReto == null) {
			lblIdReto = new JLabel("Id Reto:");
		}
		return lblIdReto;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
		}
		return lblFecha;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setActionCommand("PRESS_btnAceptar");
			btnAceptar.addActionListener(getControlador());
		}
		return btnAceptar;
	}
	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.setActionCommand("PRESS_btnRechazar");
			btnRechazar.addActionListener(getControlador());
		}
		return btnRechazar;
	}
	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado:");
		}
		return lblEstado;
	}
	private JLabel getLblIdRetoValor() {
		if (lblIdRetoValor == null) {
			lblIdRetoValor = new JLabel("<idReto>");
		}
		return lblIdRetoValor;
	}
	private JLabel getLblEstadoValor() {
		if (lblEstadoValor == null) {
			lblEstadoValor = new JLabel("<estado>");
		}
		return lblEstadoValor;
	}
	private JLabel getLblFechaValor() {
		if (lblFechaValor == null) {
			lblFechaValor = new JLabel("<fecha>");
		}
		return lblFechaValor;
	}
	private JLabel getLblRetadorValor() {
		if (lblRetadorValor == null) {
			lblRetadorValor = new JLabel("<retador>");
		}
		return lblRetadorValor;
	}
	private JLabel getLblRetador() {
		if (lblRetador == null) {
			lblRetador = new JLabel("Retador:");
		}
		return lblRetador;
	}
	
	private JLabel getLblIdSudoku() {
		if (lblIdSudoku == null) {
			lblIdSudoku = new JLabel("Id Sudoku:");
		}
		return lblIdSudoku;
	}
	
	private JLabel getLblIdSudokuValor() {
		if (lblIdSudokuValor == null) {
			lblIdSudokuValor = new JLabel("<idSud>");
		}
		return lblIdSudokuValor;
	}
	private Controlador getControlador(){
		if(controlador==null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {
		private String selItem = "";
		
		@Override
		public void windowClosing(WindowEvent e) {
			VentanaJugador.getVentana().setVisible(true);
			VentanaRetos.getVentana().dispose();
			mVent = null;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("PRESS_btnRechazar")){
				rechazar(selItem);
			} else if(arg0.getActionCommand().equals("PRESS_btnAceptar")){
				aceptar(selItem);
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() instanceof JList){
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>)e.getSource();
				selItem = list.getSelectedValue();
				actualizarValoresReto(selItem);
			}
		}
	}
	
	private void aceptar(String pIdReto){
		try {
			boolean hayPartidaPendiente = GestorPartida.getGestor().tienePartidaPendienteUserSesion();
			if(hayPartidaPendiente){
				String msg1 = "Ya hay una partida empezada, si empieza un sudoku nuevo se borrar\u00E1n los datos de la misma.";
				int respuesta = JOptionPane.showConfirmDialog(this, msg1);
				if(respuesta!=JOptionPane.OK_OPTION){
					return;
				} else {
					GestorPartida.getGestor().borrarPartidaUsuarioSesion();
				}	
			}
			GestorRetos.getGestor().aceptarReto(pIdReto);
			GestorPartida.getGestor().cargarRetoParaUsSesion(Integer.parseInt(pIdReto));
			VentanaSudoku.getVentana().setVisible(true);
			dispose();
			mVent=null;
		} catch (ExcepcionConectarBD e1) {
			e1.printStackTrace();
		}
	}
	
	private void rechazar(String pIdR){
		int idR = Integer.parseInt(pIdR);
		try {
			GestorRetos.getGestor().rechazarReto(idR);
			actualizarListaRetosPendientes();
			JOptionPane.showMessageDialog(null, "Has rechazado el reto.");
		} catch (ExcepcionConectarBD e1) {
			e1.printStackTrace();
		}
	}
}
