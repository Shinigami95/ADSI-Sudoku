package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import packControladores.GestorAdministrador;
import packControladores.GestorSudokus;
import packExcepciones.ExcepcionConectarBD;
import packExcepciones.NoValidoException;
import packExcepciones.YaExisteException;
import packModelo.Sudoku;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class VentanaAdminSudoku extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelBorrar;
	private JScrollPane pbScrollPane;
	private JScrollPane pmScrollPane;
	private JList<String> pmListaCodigosSud;
	private JList<String> pbListaCodigosSud;
	private DefaultListModel<String> pbListModelCodigosSud;
	private DefaultListModel<String> pmListModelCodigosSud;
	private JLabel pbLabelSelectCodigo;
	private JButton pbBotonBorrarSudoku;
	private JPanel panelAnadir;
	private JLabel paLblDificultad;
	private JLabel paLblSudokuSinRes;
	private JTextField paCampoSudokuIncompleto;
	private JLabel paLblSudokuRes;
	private JTextField paCampoSudokuCompleto;
	private JButton paBotonAnadirSudoku;
	private JComboBox<String> paComboBoxDificultad;
	private JPanel paneModificar;
	private JLabel pmLabelSelectCodigo;
	private JLabel pmLblDificultad;
	private JComboBox<String> pmComboBoxDificultad;
	private JTextField pmCampoSudokuIncompleto;
	private JLabel pmLblSudokuSinRes;
	private JLabel pmLblSudokuRes;
	private JTextField pmCampoSudokuCompleto;
	private JButton pmBotonVerSudoku;
	private JButton pmBotonModificar;
	private Controlador controlador;
	private static VentanaAdminSudoku mVentana;
	private JLabel paLblActivo;
	private JCheckBox paCheckBoxActivo;
	private JLabel pmLblIdSudoku;
	private JLabel pmLblIdNum;
	private JLabel pmLblActivado;
	private JLabel pmLblActivadoValor;
	private JButton pmBtnActivardesactivar;

	private VentanaAdminSudoku() {
		initialize();
	}
	
	public static VentanaAdminSudoku getVentana(){
		if(mVentana == null){
			mVentana = new VentanaAdminSudoku();
			mVentana.cargarDatos();
		}
		return mVentana;
	}
	
	private void cargarDatos(){
		try {
			String[] dif = GestorSudokus.getGestor().getDificultades();
			for (int i = 0; i < dif.length; i++) {
				getPaComboBoxDificultad().addItem(dif[i]);
				getPmComboBoxDificultad().addItem(dif[i]);
			}
			actualizarListasSudokus();
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
	
	private void initialize(){
		addWindowListener(getControlador());
		setBounds(100, 100, 960, 450);
		setMinimumSize(new Dimension(960,450));
		setTitle("Administrar sudokus");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTabbedPane());
	}
	
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("A\u00F1adir Sudoku", null, getPanelAnadir(), null);
			tabbedPane.addTab("Borrar Sudoku", null, getPanelBorrar(), null);
			tabbedPane.addTab("Modificar Sudoku", null, getPaneModificar(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanelBorrar() {
		if (panelBorrar == null) {
			panelBorrar = new JPanel();
			GroupLayout gl_panelBorrar = new GroupLayout(panelBorrar);
			gl_panelBorrar.setHorizontalGroup(
				gl_panelBorrar.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelBorrar.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelBorrar.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelBorrar.createSequentialGroup()
								.addComponent(getPbScrollPane(), GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 749, Short.MAX_VALUE)
								.addComponent(getPbBotonBorrarSudoku()))
							.addComponent(getPbLabelSelectCodigo()))
						.addContainerGap())
			);
			gl_panelBorrar.setVerticalGroup(
				gl_panelBorrar.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelBorrar.createSequentialGroup()
						.addGap(16)
						.addGroup(gl_panelBorrar.createParallelGroup(Alignment.TRAILING)
							.addComponent(getPbBotonBorrarSudoku())
							.addGroup(Alignment.LEADING, gl_panelBorrar.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getPbLabelSelectCodigo())
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(getPbScrollPane(), GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)))
						.addContainerGap())
			);
			panelBorrar.setLayout(gl_panelBorrar);
		}
		return panelBorrar;
	}
	private JList<String> getPbListaCodigosSud() {
		if (pbListaCodigosSud == null) {
			pbListaCodigosSud = new JList<String>();
			pbListaCodigosSud.setName("List_Sudokus_Borrar");
			pbListaCodigosSud.addListSelectionListener(getControlador());
			pbListaCodigosSud.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			pbListaCodigosSud.setModel(getPbListModelCodigosSud());
			pbListaCodigosSud.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return pbListaCodigosSud;
	}
	private JLabel getPbLabelSelectCodigo() {
		if (pbLabelSelectCodigo == null) {
			pbLabelSelectCodigo = new JLabel("Seleccione un codigo:");
		}
		return pbLabelSelectCodigo;
	}
	private JButton getPbBotonBorrarSudoku() {
		if (pbBotonBorrarSudoku == null) {
			pbBotonBorrarSudoku = new JButton("Borrar Sudoku");
			pbBotonBorrarSudoku.setActionCommand("PRESS_pbBotonBorrarSudoku");
			pbBotonBorrarSudoku.addActionListener(getControlador());
		}
		return pbBotonBorrarSudoku;
	}
	private JPanel getPanelAnadir() {
		if (panelAnadir == null) {
			panelAnadir = new JPanel();
			GroupLayout gl_panelAnadir = new GroupLayout(panelAnadir);
			gl_panelAnadir.setHorizontalGroup(
				gl_panelAnadir.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelAnadir.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelAnadir.createParallelGroup(Alignment.LEADING)
							.addComponent(getPaBotonAnadirSudoku(), Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panelAnadir.createSequentialGroup()
								.addGroup(gl_panelAnadir.createParallelGroup(Alignment.LEADING)
									.addComponent(getPaLblSudokuRes(), GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPaLblSudokuSinRes(), GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPaLblDificultad(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPaLblActivo()))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelAnadir.createParallelGroup(Alignment.LEADING)
									.addComponent(getPaCheckBoxActivo())
									.addComponent(getPaComboBoxDificultad(), GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPaCampoSudokuIncompleto(), GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
									.addComponent(getPaCampoSudokuCompleto(), GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE))))
						.addContainerGap())
			);
			gl_panelAnadir.setVerticalGroup(
				gl_panelAnadir.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelAnadir.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelAnadir.createParallelGroup(Alignment.BASELINE)
							.addComponent(getPaLblDificultad())
							.addComponent(getPaComboBoxDificultad(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelAnadir.createParallelGroup(Alignment.BASELINE)
							.addComponent(getPaLblSudokuSinRes())
							.addComponent(getPaCampoSudokuIncompleto(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelAnadir.createParallelGroup(Alignment.BASELINE)
							.addComponent(getPaLblSudokuRes())
							.addComponent(getPaCampoSudokuCompleto(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(13)
						.addGroup(gl_panelAnadir.createParallelGroup(Alignment.TRAILING)
							.addComponent(getPaCheckBoxActivo())
							.addComponent(getPaLblActivo()))
						.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
						.addComponent(getPaBotonAnadirSudoku())
						.addContainerGap())
			);
			panelAnadir.setLayout(gl_panelAnadir);
		}
		return panelAnadir;
	}
	private JLabel getPaLblDificultad() {
		if (paLblDificultad == null) {
			paLblDificultad = new JLabel("Dificultad:");
		}
		return paLblDificultad;
	}
	private JLabel getPaLblSudokuSinRes() {
		if (paLblSudokuSinRes == null) {
			paLblSudokuSinRes = new JLabel("Sudoku sin resolver:");
		}
		return paLblSudokuSinRes;
	}
	private JTextField getPaCampoSudokuIncompleto() {
		if (paCampoSudokuIncompleto == null) {
			paCampoSudokuIncompleto = new JTextField();
			paCampoSudokuIncompleto.setColumns(10);
		}
		return paCampoSudokuIncompleto;
	}
	private JLabel getPaLblSudokuRes() {
		if (paLblSudokuRes == null) {
			paLblSudokuRes = new JLabel("Sudoku resuelto:");
		}
		return paLblSudokuRes;
	}
	private JTextField getPaCampoSudokuCompleto() {
		if (paCampoSudokuCompleto == null) {
			paCampoSudokuCompleto = new JTextField();
			paCampoSudokuCompleto.setColumns(10);
		}
		return paCampoSudokuCompleto;
	}
	private JButton getPaBotonAnadirSudoku() {
		if (paBotonAnadirSudoku == null) {
			paBotonAnadirSudoku = new JButton("A\u00F1adir");
			paBotonAnadirSudoku.setActionCommand("PRESS_paBotonAnadirSudoku");
			paBotonAnadirSudoku.addActionListener(getControlador());
		}
		return paBotonAnadirSudoku;
	}
	private JComboBox<String> getPaComboBoxDificultad() {
		if (paComboBoxDificultad == null) {
			paComboBoxDificultad = new JComboBox<String>();
		}
		return paComboBoxDificultad;
	}
	private JPanel getPaneModificar() {
		if (paneModificar == null) {
			paneModificar = new JPanel();
			GroupLayout gl_paneModificar = new GroupLayout(paneModificar);
			gl_paneModificar.setHorizontalGroup(
				gl_paneModificar.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_paneModificar.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_paneModificar.createSequentialGroup()
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
									.addComponent(getPmLabelSelectCodigo(), GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPmScrollPane(), GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
								.addGap(31)
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
									.addComponent(getPmBtnActivardesactivar())
									.addGroup(gl_paneModificar.createSequentialGroup()
										.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
											.addComponent(getPmLblDificultad(), GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
											.addComponent(getPmLblIdSudoku()))
										.addGap(67)
										.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
											.addComponent(getPmLblIdNum())
											.addComponent(getPmComboBoxDificultad(), GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_paneModificar.createSequentialGroup()
										.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
											.addComponent(getPmLblSudokuSinRes(), GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
											.addComponent(getPmLblSudokuRes(), GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addComponent(getPmLblActivado()))
										.addGap(18)
										.addGroup(gl_paneModificar.createParallelGroup(Alignment.LEADING)
											.addComponent(getPmLblActivadoValor())
											.addComponent(getPmCampoSudokuIncompleto(), GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
											.addComponent(getPmCampoSudokuCompleto(), GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)))))
							.addGroup(gl_paneModificar.createSequentialGroup()
								.addComponent(getPmBotonVerSudoku(), GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 683, Short.MAX_VALUE)
								.addComponent(getPmBotonModificar(), GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_paneModificar.setVerticalGroup(
				gl_paneModificar.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_paneModificar.createSequentialGroup()
						.addContainerGap()
						.addComponent(getPmLabelSelectCodigo(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_paneModificar.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_paneModificar.createSequentialGroup()
								.addComponent(getPmScrollPane(), GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
								.addGap(18))
							.addGroup(gl_paneModificar.createSequentialGroup()
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.TRAILING)
									.addComponent(getPmLblIdNum())
									.addComponent(getPmLblIdSudoku()))
								.addGap(18)
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.TRAILING)
									.addComponent(getPmComboBoxDificultad(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(getPmLblDificultad()))
								.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.BASELINE)
									.addComponent(getPmLblSudokuSinRes())
									.addComponent(getPmCampoSudokuIncompleto(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.BASELINE)
									.addComponent(getPmLblSudokuRes())
									.addComponent(getPmCampoSudokuCompleto(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addGroup(gl_paneModificar.createParallelGroup(Alignment.BASELINE)
									.addComponent(getPmLblActivado())
									.addComponent(getPmLblActivadoValor()))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(getPmBtnActivardesactivar())
								.addGap(35)))
						.addGroup(gl_paneModificar.createParallelGroup(Alignment.BASELINE)
							.addComponent(getPmBotonVerSudoku())
							.addComponent(getPmBotonModificar()))
						.addContainerGap())
			);
			paneModificar.setLayout(gl_paneModificar);
		}
		return paneModificar;
	}
	private JList<String> getPmListaCodigosSud() {
		if (pmListaCodigosSud == null) {
			pmListaCodigosSud = new JList<String>();
			pmListaCodigosSud.setName("List_Sudokus_Modificar");
			pmListaCodigosSud.addListSelectionListener(getControlador());
			pmListaCodigosSud.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			pmListaCodigosSud.setModel(getPmListModelCodigosSud());
			pmListaCodigosSud.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return pmListaCodigosSud;
	}
	private JLabel getPmLabelSelectCodigo() {
		if (pmLabelSelectCodigo == null) {
			pmLabelSelectCodigo = new JLabel("Seleccione un codigo:");
		}
		return pmLabelSelectCodigo;
	}
	private JLabel getPmLblDificultad() {
		if (pmLblDificultad == null) {
			pmLblDificultad = new JLabel("Dificultad:");
		}
		return pmLblDificultad;
	}
	private JComboBox<String> getPmComboBoxDificultad() {
		if (pmComboBoxDificultad == null) {
			pmComboBoxDificultad = new JComboBox<String>();
		}
		return pmComboBoxDificultad;
	}
	private JTextField getPmCampoSudokuIncompleto() {
		if (pmCampoSudokuIncompleto == null) {
			pmCampoSudokuIncompleto = new JTextField();
			pmCampoSudokuIncompleto.setColumns(10);
		}
		return pmCampoSudokuIncompleto;
	}
	private JLabel getPmLblSudokuSinRes() {
		if (pmLblSudokuSinRes == null) {
			pmLblSudokuSinRes = new JLabel("Sudoku sin resolver:");
		}
		return pmLblSudokuSinRes;
	}
	private JLabel getPmLblSudokuRes() {
		if (pmLblSudokuRes == null) {
			pmLblSudokuRes = new JLabel("Sudoku resuelto:");
		}
		return pmLblSudokuRes;
	}
	private JTextField getPmCampoSudokuCompleto() {
		if (pmCampoSudokuCompleto == null) {
			pmCampoSudokuCompleto = new JTextField();
			pmCampoSudokuCompleto.setColumns(10);
		}
		return pmCampoSudokuCompleto;
	}
	private JButton getPmBotonVerSudoku() {
		if (pmBotonVerSudoku == null) {
			pmBotonVerSudoku = new JButton("Ver Sudoku");
			pmBotonVerSudoku.setActionCommand("PRESS_pmBotonVerSudoku");
			pmBotonVerSudoku.addActionListener(getControlador());
		}
		return pmBotonVerSudoku;
	}
	private JButton getPmBotonModificar() {
		if (pmBotonModificar == null) {
			pmBotonModificar = new JButton("Modificar");
			pmBotonModificar.setActionCommand("PRESS_pmBotonModificar");
			pmBotonModificar.addActionListener(getControlador());
		}
		return pmBotonModificar;
	}
	
	private JScrollPane getPbScrollPane() {
		if (pbScrollPane == null) {
			pbScrollPane = new JScrollPane();
			pbScrollPane.setViewportView(getPbListaCodigosSud());
		}
		return pbScrollPane;
	}
	
	private JScrollPane getPmScrollPane() {
		if (pmScrollPane == null) {
			pmScrollPane = new JScrollPane();
			pmScrollPane.setViewportView(getPmListaCodigosSud());
		}
		return pmScrollPane;
	}
	
	private DefaultListModel<String> getPmListModelCodigosSud(){
		if (pmListModelCodigosSud==null){
			pmListModelCodigosSud = new DefaultListModel<String>();
		}
		return pmListModelCodigosSud;
	}
	
	private DefaultListModel<String> getPbListModelCodigosSud(){
		if (pbListModelCodigosSud==null){
			pbListModelCodigosSud = new DefaultListModel<String>();
		}
		return pbListModelCodigosSud;
	}
	
	private JLabel getPaLblActivo() {
		if (paLblActivo == null) {
			paLblActivo = new JLabel("Activo:");
		}
		return paLblActivo;
	}
	private JCheckBox getPaCheckBoxActivo() {
		if (paCheckBoxActivo == null) {
			paCheckBoxActivo = new JCheckBox("");
		}
		return paCheckBoxActivo;
	}
	private JLabel getPmLblIdSudoku() {
		if (pmLblIdSudoku == null) {
			pmLblIdSudoku = new JLabel("Id. Sudoku:");
		}
		return pmLblIdSudoku;
	}
	private JLabel getPmLblIdNum() {
		if (pmLblIdNum == null) {
			pmLblIdNum = new JLabel("<id>");
		}
		return pmLblIdNum;
	}
	private JLabel getPmLblActivado() {
		if (pmLblActivado == null) {
			pmLblActivado = new JLabel("Activado:");
		}
		return pmLblActivado;
	}
	private JLabel getPmLblActivadoValor() {
		if (pmLblActivadoValor == null) {
			pmLblActivadoValor = new JLabel("<activado>");
		}
		return pmLblActivadoValor;
	}
	private JButton getPmBtnActivardesactivar() {
		if (pmBtnActivardesactivar == null) {
			pmBtnActivardesactivar = new JButton("Activar/Desactivar");
			pmBtnActivardesactivar.setActionCommand("PRESS_pmBtnActivardesactivar");
			pmBtnActivardesactivar.addActionListener(getControlador());
		}
		return pmBtnActivardesactivar;
	}
	
	private Controlador getControlador(){
		if(controlador == null){
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador extends WindowAdapter implements ActionListener,ListSelectionListener{
		private String selItemSudModificar = "";
		private String selItemSudBorrar = "";
		
		@Override
		public void windowClosing(WindowEvent e) {
			VentanaAdminSudoku.getVentana().dispose();
			VentanaAdmin.getVentana().setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("PRESS_paBotonAnadirSudoku")){
				VentanaAdminSudoku.getVentana().anadir();
			} else if(arg0.getActionCommand().equals("PRESS_pbBotonBorrarSudoku")){
				VentanaAdminSudoku.getVentana().borrar(selItemSudBorrar);
			} else if(arg0.getActionCommand().equals("PRESS_pmBtnActivardesactivar")){
				VentanaAdminSudoku.getVentana().activarDesactivar(selItemSudModificar);
			} else if(arg0.getActionCommand().equals("PRESS_pmBotonVerSudoku")){
				VentanaAdminSudoku.getVentana().verSudoku(selItemSudModificar);
			} else if(arg0.getActionCommand().equals("PRESS_pmBotonModificar")){
				VentanaAdminSudoku.getVentana().modificarSud(selItemSudModificar);
			}
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() instanceof JList){
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>)e.getSource();
				if(list.getName().equals("List_Sudokus_Modificar")){
					selItemSudModificar = list.getSelectedValue();
				} else if(list.getName().equals("List_Sudokus_Borrar")){
					selItemSudBorrar = list.getSelectedValue();
				}
			}
		}
	}
	
	private void actualizarListasSudokus(){
		String[] sud;
		try {
			sud = GestorSudokus.getGestor().getSudokus();
			getPbListModelCodigosSud().clear();
			getPmListModelCodigosSud().clear();
			for (int i = 0; i < sud.length; i++) {
				getPbListModelCodigosSud().addElement(sud[i]);
				getPmListModelCodigosSud().addElement(sud[i]);
			}
			getPbListaCodigosSud().setSelectedIndex(0);
			getPmListaCodigosSud().setSelectedIndex(0);
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}

	public void anadir() {
		String mCompleta = getPaCampoSudokuCompleto().getText();
		String mIncompleta = getPaCampoSudokuIncompleto().getText();
		String dif = (String) getPaComboBoxDificultad().getSelectedItem();
		boolean estaActivo = getPaCheckBoxActivo().isSelected();
		try {
			GestorAdministrador.getGestor().anadirSudoku(mCompleta, mIncompleta, Integer.parseInt(dif), estaActivo);
			actualizarListasSudokus();
			JOptionPane.showMessageDialog(this, "Sudoku a\u00F1adido correctamente");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (YaExisteException e) {
			JOptionPane.showMessageDialog(this, "Ya existe ese mismo sudoku");
		} catch (NoValidoException e) {
			JOptionPane.showMessageDialog(this, "El sudoku no est\u00E1 bien formado");
		}
	}

	public void verSudoku(String pId) {
		Sudoku sud = GestorSudokus.getGestor().buscarSudokuPorCodigo(Integer.parseInt(pId));
		String mSol = sud.toStringMatrizSolucion();
		String mInic = sud.toStringMatrizInicial();
		int dif = sud.getDificultad();
		boolean estaActivo = sud.getEstaActivo();
		String activo;
		if(estaActivo){
			activo="S";
			getPmBtnActivardesactivar().setText("Desactivar");
		}
		else {
			activo="N";
			getPmBtnActivardesactivar().setText("Activar");
		}
		getPmLblIdNum().setText(pId);
		getPmCampoSudokuCompleto().setText(mSol);
		getPmCampoSudokuIncompleto().setText(mInic);
		getPmLblActivadoValor().setText(activo);
		
		JComboBox<String> cbox = getPmComboBoxDificultad();
		String aux;
		for (int i = 0; i < cbox.getItemCount(); i++) {
			aux = getPmComboBoxDificultad().getItemAt(i);
			if(aux.equals(dif+"")) getPmComboBoxDificultad().setSelectedItem(aux);
		}
		
	}

	public void borrar(String pId) {
		int dec = JOptionPane.showConfirmDialog(this, "\u00BFEst\00E3 seguro de que quiere borrar el sudoku con id: "+pId+"?");
		if(dec==JOptionPane.OK_OPTION){
			GestorAdministrador.getGestor().borrarSudoku(Integer.parseInt(pId));
			actualizarListasSudokus();
		}
	}

	public void modificarSud(String pId) {
		String mCompleta = getPmCampoSudokuCompleto().getText();
		String mIncompleta = getPmCampoSudokuIncompleto().getText();
		String dif = (String) getPmComboBoxDificultad().getSelectedItem();
		try {
			GestorAdministrador.getGestor().modificarSudoku(Integer.parseInt(pId), mCompleta, mIncompleta, Integer.parseInt(dif));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NoValidoException e) {
			JOptionPane.showMessageDialog(this, "El sudoku no est\u00E1 bien formado");
		}
	}

	public void activarDesactivar(String pId) {
		String action = getPmBtnActivardesactivar().getText();
		if(action.equals("Activar")){
			GestorAdministrador.getGestor().setActivoSudoku(Integer.parseInt(pId), true);
			getPmLblActivadoValor().setText("S");
			getPmBtnActivardesactivar().setText("Desactivar");
		}
		else if(action.equals("Desactivar")){
			GestorAdministrador.getGestor().setActivoSudoku(Integer.parseInt(pId), false);
			getPmLblActivadoValor().setText("N");
			getPmBtnActivardesactivar().setText("Activar");
		}
	}
}
