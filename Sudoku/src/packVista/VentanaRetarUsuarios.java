package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import packControladores.GestorPartida;
import packControladores.GestorRetos;
import packExcepciones.ExcepcionConectarBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaRetarUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPaneUsuarios;
	private JPanel panelSur;
	private JButton btnRetar;
	private JList<String> listUsuarios;
	private DefaultListModel<String> defaultListModelUsuarios;
	private Controlador controlador;
	private static VentanaRetarUsuarios mVent;
	private JPanel panelDatos;
	private JLabel lblSudoku;
	private JLabel labelIdSud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRetarUsuarios frame = VentanaRetarUsuarios.getVentana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private VentanaRetarUsuarios(){
		initialize();
	}
	
	public static VentanaRetarUsuarios getVentana() throws ExcepcionConectarBD{
		if(mVent == null){
			mVent = new VentanaRetarUsuarios();
			mVent.cargarDatos();
		}
		return mVent;
	}
	
	private void cargarDatos() throws ExcepcionConectarBD{
		int idSud = GestorPartida.getGestor().getIdSud();
		getLabelIdSud().setText(idSud+"");
		this.actualizarListaUsuarios();
	}
	
	private void actualizarListaUsuarios() throws ExcepcionConectarBD{
		getDefaultListModelUsuarios().clear();
		int idSud = GestorPartida.getGestor().getIdSud();
		String[] listaUsRetables = GestorRetos.getGestor().getUsuariosRetablesAlSudoku(idSud);
		for(int i=0; i<listaUsRetables.length; i++){
			getDefaultListModelUsuarios().addElement(listaUsRetables[i]);
		}
		getListUsuarios().setSelectedIndex(0);
		if(listaUsRetables.length==0){
			getBtnRetar().setEnabled(false);
		}
	}
	
	private void initialize() {
		addWindowListener(getControlador());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ventana retar usuarios: ");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPaneUsuarios(), BorderLayout.WEST);
		contentPane.add(getPanelSur(), BorderLayout.SOUTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
	}

	private JScrollPane getScrollPaneUsuarios(){
		if (scrollPaneUsuarios == null) {
			scrollPaneUsuarios = new JScrollPane();
			scrollPaneUsuarios.setPreferredSize(new Dimension(200, 300));
			scrollPaneUsuarios.setViewportView(getListUsuarios());
		}
		return scrollPaneUsuarios;
	}
	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelSur.add(getBtnRetar());
		}
		return panelSur;
	}
	private JButton getBtnRetar() {
		if (btnRetar == null) {
			btnRetar = new JButton("Retar");
			btnRetar.setActionCommand("PRESS_btnRetar");
			btnRetar.addActionListener(getControlador());
		}
		return btnRetar;
	}
	private JList<String> getListUsuarios(){
		if (listUsuarios == null) {
			listUsuarios = new JList<String>();
			listUsuarios.setModel(getDefaultListModelUsuarios());
			listUsuarios.setVisibleRowCount(getDefaultListModelUsuarios().getSize());
			listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listUsuarios.addListSelectionListener(getControlador());
		}
		return listUsuarios;
	}
	private DefaultListModel<String> getDefaultListModelUsuarios(){
		if (defaultListModelUsuarios == null) {
			defaultListModelUsuarios = new DefaultListModel<String>();
		}
		return defaultListModelUsuarios;
	}
	
	private JPanel getPanel_1() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			GroupLayout gl_panelDatos = new GroupLayout(panelDatos);
			gl_panelDatos.setHorizontalGroup(
				gl_panelDatos.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDatos.createSequentialGroup()
						.addContainerGap()
						.addComponent(getLblSudoku())
						.addGap(18)
						.addComponent(getLabelIdSud())
						.addContainerGap(95, Short.MAX_VALUE))
			);
			gl_panelDatos.setVerticalGroup(
				gl_panelDatos.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDatos.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelDatos.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblSudoku())
							.addComponent(getLabelIdSud()))
						.addContainerGap(181, Short.MAX_VALUE))
			);
			panelDatos.setLayout(gl_panelDatos);
		}
		return panelDatos;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("Sudoku:");
		}
		return lblSudoku;
	}
	private JLabel getLabelIdSud() {
		if (labelIdSud == null) {
			labelIdSud = new JLabel("<idSud>");
		}
		return labelIdSud;
	}
	
	private Controlador getControlador(){
		if(controlador == null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {
		private String selItemJugador = "";
		
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				VentanaFinal.getVentana().setVisible(true);
				VentanaRetarUsuarios.getVentana().setVisible(false);
			} catch (ExcepcionConectarBD e1) {
				e1.printStackTrace();
			}
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("PRESS_btnRetar")){
				try {
					GestorRetos.retarJugadorAlSudokuHechoPorUsuarioSesion(selItemJugador);
					VentanaRetarUsuarios.getVentana().actualizarListaUsuarios();
				} catch (ExcepcionConectarBD e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() instanceof JList){
				JList<String> list = (JList<String>)e.getSource();
				selItemJugador = list.getSelectedValue();
			}
		}
	}
}
