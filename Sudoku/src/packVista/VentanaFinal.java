package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.swing.JButton;

import packControladores.GestorLogros;
import packControladores.GestorPartida;
import packControladores.GestorSesion;
import packControladores.GestorTiempo;
import packControladores.GestorTwitter;
import packExcepciones.ExcepcionConectarBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class VentanaFinal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaFinal mVent;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel labelTiempoValor;
	private JLabel labelTiempo;
	private JLabel labelPuntuacionValor;
	private JLabel labelPuntuacion;
	private JPanel panel_1;
	private JButton btnRetar;
	private JButton btnFinalizar;
	private JPanel panel_2;
	private JList<String> listLogrosNuevos;
	private DefaultListModel<String> defListModelLogrosNuevos;
	private JLabel lblSudoku;
	private JLabel lblDescripcion;
	private JLabel lblSudokuValor;
	private JButton btnCompartirPuntuacion;
	private JButton btnCompartirLogro;
	private Controlador controlador;
	private JTextArea textAreaDescrLogro;

	/**
	 * Create the frame.
	 * @throws ExcepcionConectarBD 
	 * @throws SQLException 
	 */
	private VentanaFinal(){
		initialize();
	}
	
	private void initialize() {
		addWindowListener(getControlador());
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(450, 300));
		setTitle("Ventana final");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getPanel_2(), BorderLayout.CENTER);
	}

	public static VentanaFinal getVentana(){
		if(mVent==null){
			mVent = new VentanaFinal();
			mVent.cargarDatos();
		}
		return mVent;
	}
	//Este metodo se encarga de cargar el tiempo,la puntuacion, el usuario, el id del sudoku y los logros que ha conseguido el usuario al terminar la partida.
	private void cargarDatos(){
		int pto = GestorPartida.getGestor().calcularPuntuacion();
		String user = GestorSesion.getGestor().getUserSesion();
		int idSud = GestorPartida.getGestor().getIdSud();
		getLblSudokuValor().setText(idSud+"");
		getLabelPuntuacionValor().setText(pto+"");
		DefaultListModel<String> logros = GestorLogros.getGestor().logrosConseguidos(user, idSud+"", pto+"");
		getListLogrosNuevos().setModel(logros);
		getListLogrosNuevos().setSelectedIndex(0);
		if(logros.getSize()>0){
			getBtnCompartirLogro().setEnabled(true);
		}
	}
	private JScrollPane getScrollPane(){
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(200, 300));
			scrollPane.setViewportView(getListLogrosNuevos());
		}
		return scrollPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.add(getLabelTiempo());
			panel.add(getLabelTiempoValor());
			panel.add(getLabelPuntuacion());
			panel.add(getLabelPuntuacionValor());
			panel.add(getBtnCompartirPuntuacion());
		}
		return panel;
	}
	private JLabel getLabelTiempoValor() {
		//En este label se aÃ±ade el tiempo que ha tardado en hacer el ultimo sudoku
		if (labelTiempoValor == null) {
			labelTiempoValor = new JLabel(GestorTiempo.getGestor().tiempoAString());
			labelTiempoValor.setBounds(0, 0, 51, 14);
		}
		return labelTiempoValor;
	}
	private JLabel getLabelTiempo() {
		if (labelTiempo == null) {
			labelTiempo = new JLabel("Tiempo:");
			labelTiempo.setBounds(0, 0, 38, 14);
		}
		return labelTiempo;
	}
	private JLabel getLabelPuntuacionValor() {
		if (labelPuntuacionValor == null) {
			labelPuntuacionValor = new JLabel("<pto>");
			labelPuntuacionValor.setBounds(0, 0, 49, 14);
		}
		return labelPuntuacionValor;
	}
	private JLabel getLabelPuntuacion() {
		if (labelPuntuacion == null) {
			labelPuntuacion = new JLabel("Puntuaci\u00F3n:");
			labelPuntuacion.setBounds(0, 0, 57, 14);
		}
		return labelPuntuacion;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setHgap(50);
			panel_1.add(getBtnRetar());
			panel_1.add(getBtnFinalizar());
		}
		return panel_1;
	}
	private JButton getBtnRetar() {
		//Este boton llama a la ventana que permite retar a otros usuarios
		if (btnRetar == null) {
			btnRetar = new JButton("Retar");
			btnRetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentanaRetarUsuarios.getVentana().setVisible(true);
						VentanaFinal.getVentana().setVisible(false);
					} catch (ExcepcionConectarBD e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnRetar;
	}
	private JButton getBtnFinalizar() {
		//Este boton nos lleva a la ventanaJugador
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setActionCommand("PRESS_btnFinalizar");
			btnFinalizar.addActionListener(getControlador());
		}
		return btnFinalizar;
	}
	private JPanel getPanel_2(){
		if (panel_2 == null) {
			panel_2 = new JPanel();
			GroupLayout gl_panel_2 = new GroupLayout(panel_2);
			gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(10)
								.addComponent(getLblDescripcion(), GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(38)
								.addComponent(getBtnCompartirLogro(), GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(10)
									.addComponent(getLblSudoku(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(66)
									.addComponent(getLblSudokuValor())))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addContainerGap()
								.addComponent(getTextAreaDescrLogro(), GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
						.addContainerGap())
			);
			gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addGap(11)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addComponent(getLblSudokuValor())
							.addComponent(getLblSudoku()))
						.addGap(11)
						.addComponent(getLblDescripcion())
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getTextAreaDescrLogro(), GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(getBtnCompartirLogro()))
			);
			panel_2.setLayout(gl_panel_2);
		}
		return panel_2;
	}
	private JList<String> getListLogrosNuevos(){
		//La lista se llena con los IDs de los logros existentes
		if (listLogrosNuevos == null) {
			listLogrosNuevos = new JList<String>();
			listLogrosNuevos.setVisibleRowCount(100);
			listLogrosNuevos.setModel(getDefListModelLogrosNuevos());
			listLogrosNuevos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listLogrosNuevos.addListSelectionListener(getControlador());
			
		}
		return listLogrosNuevos;
	}
	private DefaultListModel<String> getDefListModelLogrosNuevos(){
		if (defListModelLogrosNuevos == null) {
			defListModelLogrosNuevos = new DefaultListModel<String>();
		}
		return defListModelLogrosNuevos;
	}
	private JTextArea getTextAreaDescrLogro() {
		if (textAreaDescrLogro == null) {
			textAreaDescrLogro = new JTextArea();
			textAreaDescrLogro.setEditable(false);
		}
		return textAreaDescrLogro;
	}
	private JLabel getLblSudoku() {
		if (lblSudoku == null) {
			lblSudoku = new JLabel("Sudoku:");
		}
		return lblSudoku;
	}
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripci\u00F3n:");
		}
		return lblDescripcion;
	}
	private JLabel getLblSudokuValor(){
		if (lblSudokuValor == null) {
			lblSudokuValor = new JLabel("<id sud>");
		}
		
		return lblSudokuValor;
	}
	private JButton getBtnCompartirPuntuacion() {
		if (btnCompartirPuntuacion == null) {
			btnCompartirPuntuacion = new JButton("Compartir Puntuacion");
			btnCompartirPuntuacion.setActionCommand("PRESS_btnCompartirPuntuacion");
			btnCompartirPuntuacion.addActionListener(getControlador());
		}
		return btnCompartirPuntuacion;
	}
	private JButton getBtnCompartirLogro() {
		if (btnCompartirLogro == null) {
			btnCompartirLogro = new JButton("Compartir Logro");
			btnCompartirLogro.setEnabled(false);
			btnCompartirLogro.setActionCommand("PRESS_btnCompartirLogro");
			btnCompartirLogro.addActionListener(getControlador());
		}
		return btnCompartirLogro;
	}
	
	private Controlador getControlador(){
		if(controlador == null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener,ListSelectionListener{
		String selItemLogro = "";
		//Al cerrarse la ventana llama a la ventanaJugador
		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
			VentanaJugador.getVentana().setVisible(true);
		}
		//Comparte el logro que se ha seleccionado de la lista de los logros conseguidos.
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("PRESS_btnCompartirLogro")){
				try {
					String user = GestorSesion.getGestor().getUserSesion();
					String descr = VentanaFinal.getVentana().getTextAreaDescrLogro().getText();
					String str = user+"+ha+conseguido+el+logro+"+selItemLogro+"+por:+"+descr;
					GestorTwitter.getGestorTwitter().compartirEnTwitter(str);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			} else if(arg0.getActionCommand().equals("PRESS_btnCompartirPuntuacion")){
				try {
					String user = GestorSesion.getGestor().getUserSesion();
					int idSud = GestorPartida.getGestor().getIdSud();
					int pto = GestorPartida.getGestor().calcularPuntuacion();
					String str = user+"+ha+conseguido+"+pto+"+puntos+en+el+sudoku+"+idSud;
					GestorTwitter.getGestorTwitter().compartirEnTwitter(str);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			} else if(arg0.getActionCommand().equals("PRESS_btnFinalizar")){
				VentanaJugador.getVentana().setVisible(true);
				dispose();
				mVent=null;
			}
		}
		
		//Rellena el textarea con la descripci�n del logro seleccionado
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() instanceof JList){
				try {
					@SuppressWarnings("unchecked")
					JList<String> list = (JList<String>)e.getSource();
					selItemLogro = list.getSelectedValue();
					String descr = GestorLogros.getGestor().getDescripcionDe(selItemLogro);
					getTextAreaDescrLogro().setText(descr);
				} catch (ExcepcionConectarBD e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
}

