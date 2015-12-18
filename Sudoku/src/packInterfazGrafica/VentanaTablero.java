/*
 * 
 */
package packInterfazGrafica;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import packSudoku.excepciones.ExcepcionCoordenadaNoValida;
import packSudoku.excepciones.ExcepcionNoHaySudokuCargado;
import packSudoku.excepciones.ExcepcionNoHayValorEnCasilla;
import packSudokuAntiguo.Tablero;
import packSudokuAntiguo.TipoArea;


/**
 * The Class VentanaTablero.
 */
public class VentanaTablero extends JDialog implements Observer
{

    /**
     * 
     */
    private static final long serialVersionUID = -1034960608603782970L;
    /** The packframe. */
    private boolean packframe = false;
    // Atributos
    /** The NUMFILAS. */
    private final static int NUMFILAS = 9;
    
    /** The NUMCOLUMNAS. */
    private final static  int NUMCOLUMNAS = 9;
    
    /** The NUMZONAS. */
    private final static int NUMZONAS = 9;


    /** The lista paneles. */
    private Vector<JPanel> listaPaneles = new Vector<JPanel>();
    
    /** The lista casillas. */
    private Vector<JTextField> listaCasillas = new Vector<JTextField>();

     /** The panel casillas. */
    private JPanel panelCasillas = new JPanel();
    
    /** The panel terminar. */
    private JPanel panelTerminar = new JPanel();
    
    /** The boton terminar. */
    private JButton botonTerminar = new JButton();
    
    /** The boton ayuda. */
    private JButton botonAyuda = new JButton();

    /** The border layout1. */
    private BorderLayout borderLayout1 = new BorderLayout();

    /** The mi menu. */
    private JPopupMenu miMenu = new JPopupMenu();


    /** The grid layout casillas. */
    private GridLayout gridLayoutCasillas = new GridLayout();
    
    /** The border1. */
    private Border border1 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    
    /** The panel info. */
    private JPanel panelInfo = new JPanel();
    
    /** The lbl errores etiq. */
    private JLabel lblErroresEtiq = new JLabel();
    
    /** The lbl id sudoku. */
    private JLabel lblIDSudoku = new JLabel();
    
    /** The grid layout info. */
    private GridLayout gridLayoutInfo = new GridLayout();
    
    /** The lbl sudoku. */
    private JLabel lblSudoku = new JLabel();
    
    /** The lbl etiq nivel. */
    private JLabel lblEtiqNivel = new JLabel();
    
    /** The lbl nivel. */
    private JLabel lblNivel = new JLabel();
    
    /** The lbl errores. */
    private JLabel lblErrores = new JLabel();

    /** The COLORRESALTADO. */
    private final static Color COLORRESALTADO = Color.YELLOW;

    /** The mi ventana. */
    private static VentanaTablero miVentana;
    
     
    /** The border3. */
    private Border border3 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    
    /** The controlador. */
    private Controlador controlador = new Controlador();
    
    /** The tablero. */
    private Tablero tablero = Tablero.getTablero();
    
    //Construcci�n del tablero
    /**
     * Instantiates a new ventana tablero.
     */
    private VentanaTablero() {

	Tablero.getTablero().addObserver(this);
        // Crear Menu
        crearMenu();

        // CrearPanelesZonas
        crearPanelesZonas();

        // Crear CasillasGraficas
        crearCasillas();

        //Inicializaci�n de la recepci�n de eventos en la ventana
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obt ventana tablero.
     * 
     * @return the ventana tablero
     */
    public static VentanaTablero obtVentanaTablero() {
        if (miVentana == null) {
            miVentana = new VentanaTablero();
        }
        return miVentana;
    }

    /**
     * crearPanelesZonas.
     */
    private void crearPanelesZonas() {
        // Generaci�n de Paneles
        for (int i = 0; i < NUMZONAS; i++) {
            listaPaneles.addElement(crearPanel());
        }
    }

    /**
     * crearPanel.
     * 
     * @return JPanel
     */
    private JPanel crearPanel() {
        JPanel panelZona = new JPanel();
        // panelZona.setBorder(BorderFactory.createLineBorder(Color.black));
        GridLayout gridLayoutZona = new GridLayout();
        gridLayoutZona.setColumns(3);
        gridLayoutZona.setRows(3);
        //  gridLayoutZona.setHgap(2);
        // gridLayoutZona.setVgap(2);
        panelZona.setLayout(gridLayoutZona);
        return panelZona;
    }

    /**
     * Crear casillas.
     */
    private void crearCasillas() {
        //Generaci�n de las casillas
        int columna = 0;
        int fila = 0;
        for (fila = 0; fila < NUMFILAS; fila++) {
            for (columna = 0; columna < NUMCOLUMNAS; columna++) {
                listaCasillas.addElement(crearCasilla());
            }
        }

    }

    //M�todo privado para crear una casilla vac�a del tablero
    /**
     * Crear casilla.
     * 
     * @return the j text field
     */
    private JTextField crearCasilla() {
        JTextField cuadroTexto = new JTextField();
        cuadroTexto.setMaximumSize(new Dimension(50, 50));
        cuadroTexto.setMinimumSize(new Dimension(50, 50));
        cuadroTexto.setSize(new Dimension(50, 50));
        cuadroTexto.setHorizontalAlignment(JTextField.CENTER);
        cuadroTexto.setFont(new Font("Helvetica", Font.PLAIN, 16));
        cuadroTexto.setComponentPopupMenu(miMenu);
        cuadroTexto.setFocusable(false);
        return cuadroTexto;
    }


    //M�todo privado para obtener la casilla
    /**
     * Obt casilla.
     * 
     * @param pFila the fila
     * @param pColumna the columna
     * 
     * @return the j text field
     */
    private JTextField getCasilla(int pFila, int pColumna) {
        return listaCasillas.elementAt((pFila * NUMCOLUMNAS) +
                pColumna);
    }

    /**
     * Obt panel fila col.
     * 
     * @param pFila the fila
     * @param pCol the col
     * 
     * @return the j panel
     */
    private JPanel getPanelFilaCol(int pFila, int pCol) {
        JPanel panel = listaPaneles.elementAt((pFila / 3 * 3) +
                pCol / 3);
        return panel;
    }

    //Inicializaci�n de componentes
    /**
     * Jb init.
     * 
     * @throws Exception the exception
     */
    private void jbInit() throws Exception {
        this.setModal(true);
        this.setTitle("Sudoku: ");
        this.getContentPane().setLayout(borderLayout1);

        botonTerminar.setBorder(BorderFactory.createRaisedBevelBorder());
        botonTerminar.setPreferredSize(new Dimension(60, 30));
        botonTerminar.setMargin(new Insets(5, 25, 5, 25));
        botonTerminar.setText("Finalizar");
        botonTerminar.addActionListener(controlador);
        botonTerminar.setActionCommand("botonTerminar");
  
        botonAyuda.setBorder(BorderFactory.createRaisedBevelBorder());
        botonAyuda.setPreferredSize(new Dimension(60, 30));
        botonAyuda.setMargin(new Insets(5, 25, 5, 25));

        botonAyuda.setText("Ayuda");
        botonAyuda.addActionListener(controlador);
        botonAyuda.setActionCommand("botonAyuda");

        // panelTerminar.setPreferredSize(new Dimension(70, 50));
        borderLayout1.setVgap(10);

        panelCasillas.setLayout(gridLayoutCasillas);
        gridLayoutCasillas.setColumns(3);
        gridLayoutCasillas.setHgap(5);
        gridLayoutCasillas.setRows(3);
        gridLayoutCasillas.setVgap(5);

        // A�adir Paneles
        anadirPanelesZona();
        // A�adir Casillas a los paneles
        anadirCasillasAPaneles();
        // A�adir Men�
        // crearMenu();

        panelCasillas.setBorder(border1);
        panelCasillas.setMaximumSize(new Dimension(250, 300));
        panelCasillas.setMinimumSize(new Dimension(250, 300));
        panelCasillas.setPreferredSize(new Dimension(250, 300));
        panelInfo.setLayout(gridLayoutInfo);
        lblIDSudoku.setText("Sudoku:");
        gridLayoutInfo.setColumns(2);
        gridLayoutInfo.setRows(3);
        lblSudoku.setText("");
        lblErroresEtiq.setText("Errores:");
        lblEtiqNivel.setText("Nivel:");
        lblNivel.setText("");
        lblErrores.setText("");
        panelInfo.setBorder(border3);

        this.getContentPane().add(panelTerminar, BorderLayout.SOUTH);
        panelTerminar.add(botonAyuda);
        panelTerminar.add(botonAyuda);
        panelTerminar.add(botonTerminar);
        this.getContentPane().add(panelCasillas, BorderLayout.CENTER);
        this.getContentPane().add(panelInfo, java.awt.BorderLayout.NORTH);
        panelInfo.add(lblIDSudoku);
        panelInfo.add(lblSudoku);
        panelInfo.add(lblEtiqNivel);
        panelInfo.add(lblNivel);
        panelInfo.add(lblErroresEtiq);
        panelInfo.add(lblErrores);
        lblErrores.setVisible(false);
        lblErroresEtiq.setVisible(false);
        this.setSize(new Dimension(450, 300));

        this.setResizable(false);

    }

    /**
     * Crear menu.
     */
    private void crearMenu() {
        JMenuItem menuItem;
        for (int i = 1; i <= 9; i++) {
            menuItem = new JMenuItem("" + i + "");
            menuItem.setActionCommand("asignarValor");
            menuItem.addMouseListener(controlador);
            miMenu.add(menuItem);
        }
        miMenu.add(new JPopupMenu.Separator());
        menuItem = new JMenuItem("Quitar Valor");
        menuItem.setActionCommand("quitarValor");
        menuItem.addMouseListener(controlador);
        miMenu.add(menuItem);
        miMenu.setFocusable(true);
        miMenu.addPopupMenuListener(controlador);

    }

    /**
     * Anadir paneles zona.
     */
    private void anadirPanelesZona() {
        for (int i = 0; i < listaPaneles.size(); i++) {
            JPanel panel = listaPaneles.elementAt(i);
            panelCasillas.add(panel);
        }
    }

    /**
     * Anadir casillas a paneles.
     */
    private void anadirCasillasAPaneles() {

        int columna = 0;
        int fila = 0;
        for (fila = 0; fila < NUMFILAS; fila++) {
            for (columna = 0; columna < NUMCOLUMNAS; columna++) {

                JPanel panel = getPanelFilaCol(fila, columna);
                panel.add(getCasilla(fila, columna),
                          new GridBagConstraints(columna, fila, 1, 1, 0.0, 0.0,
                                                 GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(2, 2, 2, 2), 0, 0));
            }
        }

    }

    //Para salir de la aplicaci�n cuando se cierra la ventana
    /**
     * @see javax.swing.JDialog#processWindowEvent(java.awt.event.WindowEvent)
     */
    protected void processWindowEvent(WindowEvent pEvent) {
        super.processWindowEvent(pEvent);
        if (pEvent.getID() == WindowEvent.WINDOW_CLOSING) {
            this.setVisible(false);
        }
    }



    /**
     * Bloquear.
     */
    public void bloquear() {
        for (int i = 0; i < NUMFILAS * NUMCOLUMNAS; i++) {
            JTextField etiq = listaCasillas.elementAt(i);
            etiq.setComponentPopupMenu(null);
        }
    }



    /**
     * Quitar info ayuda.
     */
    private void quitarInfoAyuda() {
        lblErroresEtiq.setVisible(false);
        lblErrores.setVisible(false);
        JTextField cuadroTexto;
        for (int fila = 0; fila < NUMFILAS; fila++) {
            for (int columna = 0; columna < NUMCOLUMNAS; columna++) {
                cuadroTexto = getCasilla(fila, columna);
                cuadroTexto.setBackground(Color.WHITE);
            }
        }
    }

    /**
     * Inicializar tablero.
     */
    public void inicializarTablero() {
        JTextField casilla;
        for (int fila = 1; fila <= NUMFILAS; fila++) {
            for (int columna = 1; columna <= NUMCOLUMNAS; columna++) {
                quitarValor(fila, columna);
                casilla = getCasilla(fila - 1, columna - 1);
                casilla.setForeground(Color.BLACK);
                casilla.setFont(new Font("Helvetica", Font.PLAIN, 14));
                casilla.setComponentPopupMenu(miMenu);
            }
        }
    }

    /**
     * Resaltar area.
     * 
     * @param pTipo the tipo
     * @param pId the id
     */
    private void resaltarArea(TipoArea pTipo, int pId) {
        switch (pTipo) {
        case FILA:
            resaltarFila(pId);
            break;
        case COLUMNA:
            resaltarColumna(pId);
            break;
        case ZONA:
            resaltarZona(pId);
            break;
        }
    }

    /**
     * Resaltar columna.
     * 
     * @param pIdCol the id col
     */
    private void resaltarColumna(int pIdCol) {
        int columna = pIdCol - 1;
        JTextField cuadroTexto;
        for (int fila = 0; fila < NUMFILAS; fila++) {
            cuadroTexto = getCasilla(fila, columna);
            cuadroTexto.setBackground(COLORRESALTADO);
        }
    }


    /**
     * Resaltar fila.
     * 
     * @param pIdFila the id fila
     */
    private void resaltarFila(int pIdFila) {
        int fila = pIdFila - 1;
        JTextField cuadroTexto;
        for (int columna = 0; columna < NUMCOLUMNAS; columna++) {
            cuadroTexto = getCasilla(fila, columna);
            cuadroTexto.setBackground(COLORRESALTADO);
        }
    }

    /**
     * Resaltar zona.
     * 
     * @param pIdZona the id zona
     */
    private void resaltarZona(int pIdZona) {
        pIdZona = pIdZona - 1; //S�lo si las zonas se numeran de 1-9
        int auxZona = pIdZona / 3;
        int filaIni = auxZona * 3;
        int filaFin = filaIni + 3;
        int colIni = (pIdZona % 3) * 3;
        int colFin = colIni + 3;
        JTextField cuadroTexto;
        for (int fila = filaIni; fila < filaFin; fila++) {
            for (int columna = colIni; columna < colFin; columna++) {
                cuadroTexto = getCasilla(fila, columna);
                cuadroTexto.setBackground(COLORRESALTADO);
            }
        }
    }

    /**
     * Asg valor.
     * 
     * @param pFila the fila
     * @param pCol the col
     * @param pValor the valor
     */
    private void setValor(int pFila, int pCol, int pValor) {
        quitarInfoAyuda();

        JTextField cuadroTexto = getCasilla(pFila - 1, pCol - 1);
        cuadroTexto.setText(String.valueOf(pValor));
    }

    /**
     * Asg valor inicial.
     * 
     * @param pFila the fila
     * @param pCol the col
     * @param pValor the valor
     */
    private void getValorInicial(int pFila, int pCol, int pValor) {
        quitarInfoAyuda();
        JTextField cuadroTexto = getCasilla(pFila - 1, pCol - 1);
        cuadroTexto.setForeground(Color.RED);
        cuadroTexto.setFont(new Font("Helvetica", Font.BOLD, 16));
        cuadroTexto.setText(String.valueOf(pValor));
        cuadroTexto.setComponentPopupMenu(null);
    }

    /**
     * Quitar valor.
     * 
     * @param pFila the fila
     * @param pCol the col
     */
    private void quitarValor(int pFila, int pCol) {
        quitarInfoAyuda();
        JTextField cuadroTexto = getCasilla(pFila - 1, pCol - 1);
        cuadroTexto.setText("");
    }

   

    /**
     * Poner nivel sudoku.
     * 
     * @param pNivel the nivel
     */
    public void ponerNivelSudoku(int pNivel) {
        lblNivel.setText(String.valueOf(pNivel));
    }

    /**
     * Mostrar errores.
     * 
     * @param pErrores the errores
     */
    public void mostrarErrores(int pErrores) {
        lblErrores.setText("" + pErrores + "");
        if (pErrores > 0) {
            lblErrores.setForeground(Color.RED);
            lblErroresEtiq.setForeground(Color.RED);
        } else {
            lblErrores.setForeground(Color.BLACK);
            lblErroresEtiq.setForeground(Color.BLACK);
        }
        lblErroresEtiq.setVisible(true);
        lblErrores.setVisible(true);

    }

    /**
     * Asignar valor casilla.
     * 
     * @param pEvent the e
     */
    private void setValorCasilla(MouseEvent pEvent) {

        JTextField casilla = (JTextField) miMenu.getInvoker();
        int posicion = listaCasillas.indexOf(casilla);
        System.out.println("Posicion en la que pincha con el derecho: " +
                           posicion);
        int filaInicial = (int) (posicion / NUMCOLUMNAS);
        int columnaInicial = posicion % NUMCOLUMNAS;
        System.out.println("Posicion de la casilla: " + filaInicial + ", " +
                           columnaInicial);

        JMenuItem menuItem = (JMenuItem) pEvent.getComponent();
        int val = Integer.parseInt(menuItem.getText());

        try {
            Tablero.getTablero().setValor(filaInicial + 1, columnaInicial + 1,
                                          val);
        } catch (Exception ex) {
            // En principio aqu� habr�a que tratar cada una de las excepciones
            // por separado. Como no se vana a producir y algunos hab�is definido
            // las excepciones en distintos paquetes, lo hemos simplificado
            VentanaError.obtVentanaError().mostrar(ex.getMessage());

        }
        miMenu.setVisible(false);
    }

    /**
     * Quitar valor casilla.
     * 
     * @param pEvent the e
     */
    private void quitarValorCasilla(MouseEvent pEvent) {
        JTextField casilla = (JTextField) miMenu.getInvoker();
        int posicion = listaCasillas.indexOf(casilla);
        System.out.println("Posicion en la que pincha con el derecho: " +
                           posicion);
        int filaInicial = (int) (posicion / NUMCOLUMNAS);
        int columnaInicial = posicion % NUMCOLUMNAS;
        System.out.println("Posicion de la casilla: " + filaInicial + ", " +
                           columnaInicial);

        try {
            Tablero.getTablero().quitarValor(filaInicial + 1,
                                             columnaInicial + 1);
        } catch (Exception ex) {
            // En principio aqu� habr�a que tratar cada una de las excepciones
            // por separado. Como no se vana a producir y algunos hab�is definido
            // las excepciones en distintos paquetes, lo hemos simplificado
            VentanaError.obtVentanaError().mostrar(ex.getMessage());
        }
        miMenu.setVisible(false);
    }

    

    /**
     * Mostrar.
     */
    public void mostrar() {
        if (packframe) {
            this.pack();
        } else {
            this.validate();
        }
        this.setModal(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventanaTableroSize = this.getSize();
        if (ventanaTableroSize.height > screenSize.height) {
            ventanaTableroSize.height = screenSize.height;
        }
        if (ventanaTableroSize.width > screenSize.width) {
            ventanaTableroSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - ventanaTableroSize.width) / 2,
                         (screenSize.height - ventanaTableroSize.height) / 2);
        update(null,null);

        this.setVisible(true);
    
    }

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    
    public void update(Observable pO, Object pArg)
    {
	inicializarTablero();
	try
	{
	    this.lblSudoku.setText(tablero.getIdSudoku());
	    this.lblNivel.setText(String.valueOf(tablero.getNivel()));
	} catch (ExcepcionNoHaySudokuCargado e)
	{
	}
	
	this.lblErrores.setText("");
	try {
	    if (!tablero.finalDeJuego())
	    {
	        mostrarCasillas();
	    }
	    else
	    {JOptionPane.showMessageDialog(this, "Congratulations");
	        descubrirCasillas();
	    }
	} catch (ExcepcionNoHaySudokuCargado e) {
	}
	
    }
    
    /**
     * Descubrir casillas.
     */
    private void descubrirCasillas()
    {
	for (int i = 1; i <= NUMFILAS; i++)
	{
	    for (int j = 1; j <= NUMCOLUMNAS; j++)
	    {
		try
		{
		    if (!tablero.isValorInicial(i, j) )
		    {
		        setValor(i, j,tablero.getValor(i,j));
		    }
		    else if (tablero.isValorInicial(i, j))
		    {
		        getValorInicial(i,j,tablero.getValor(i,j));
		    }
		} catch (ExcepcionCoordenadaNoValida e)
		{
		} catch (ExcepcionNoHayValorEnCasilla e)
		{
		} catch (ExcepcionNoHaySudokuCargado e) {
		}
	    }
	}
	bloquear();
    }

    /**
     * Mostrar casillas.
     */
    private void mostrarCasillas()
    {
	for (int i = 1; i <= NUMFILAS; i++)
	{
	    for (int j = 1; j <= NUMCOLUMNAS; j++)
	    {
		try
		{
		    if (!tablero.isValorInicial(i, j) && !tablero.isLibre(i, j))
		    {
		        setValor(i, j,tablero.getValor(i,j));
		    }
		    else if (tablero.isValorInicial(i, j))
		    {
		        getValorInicial(i,j,tablero.getValor(i,j));
		    }
		} catch (ExcepcionCoordenadaNoValida e)
		{
		} catch (ExcepcionNoHayValorEnCasilla e)
		{
		} catch (ExcepcionNoHaySudokuCargado e) {
		}
	    }
	}
	
    }
    
    /**
     * Mostrar errores inconsistencias.
     */
    private void mostrarErroresInconsistencias()
    {
	try
	{
	    mostrarErrores(tablero.getNumErrores());
	    resaltarInconsistencias();
	} catch (ExcepcionNoHaySudokuCargado e)
	{
	}
    }

    /**
     * Resaltar inconsistencias.
     */
    private void resaltarInconsistencias()
    {
	for (int i = 1; i <= 9; i++)
	{
	    try
	    {
		if (tablero.hayInconsistencias(TipoArea.FILA, i))
		resaltarArea(TipoArea.FILA, i);
		if (tablero.hayInconsistencias(TipoArea.COLUMNA, i))
			resaltarArea(TipoArea.COLUMNA, i);
		if (tablero.hayInconsistencias(TipoArea.ZONA, i))
			resaltarArea(TipoArea.ZONA, i);
	    } catch (ExcepcionCoordenadaNoValida e)
	    {
	    } catch (ExcepcionNoHaySudokuCargado e) {
	  }
	}
	
    }

    /**
     * The Class Controlador.
     */
    private class Controlador implements ActionListener, PopupMenuListener, MouseListener{

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	
	public void actionPerformed(ActionEvent pE)
	{
	    if (pE.getActionCommand().equalsIgnoreCase("botonTerminar"))
		setVisible(false);
	    else
		mostrarErroresInconsistencias();
	}

	/**
	 * @see javax.swing.event.PopupMenuListener#popupMenuCanceled(javax.swing.event.PopupMenuEvent)
	 */
	
	public void popupMenuCanceled(PopupMenuEvent pE)
	{
	}

	/**
	 * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent)
	 */
	
	public void popupMenuWillBecomeInvisible(PopupMenuEvent pE)
	{
    
	}

	/**
	 * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent)
	 */
	
	public void popupMenuWillBecomeVisible(PopupMenuEvent pE)
	{
	    JTextField casilla = (JTextField) miMenu.getInvoker();
            int posicion = listaCasillas.indexOf(casilla);
            System.out.println("Posicion en la que pincha con el derecho: " +
                               posicion);
            int filaInicial = (int) (posicion / NUMCOLUMNAS);
            int columnaInicial = posicion % NUMCOLUMNAS;
            Component comp[] = miMenu.getComponents();
            JMenuItem menuItem = (JMenuItem) comp[comp.length - 1];

            try
	    {
		if (Tablero.getTablero().isLibre(filaInicial + 1,
		        columnaInicial + 1)) {

		    menuItem.setEnabled(false);

		} else {
		    menuItem.setEnabled(true);
		}
	    } catch (ExcepcionCoordenadaNoValida e)
	    {
	    } catch (ExcepcionNoHaySudokuCargado e) {
	   }
	}

	/**
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	
	public void mouseClicked(MouseEvent pE)
	{
	}

	/**
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	
	public void mouseEntered(MouseEvent pE)
	{
	    
	}

	/**
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	
	public void mouseExited(MouseEvent pE)
	{
		}

	/**
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	
	public void mousePressed(MouseEvent pE)
	{
	    JMenuItem menuItem = (JMenuItem) pE.getSource();
	    if (menuItem.getActionCommand().equalsIgnoreCase("asignarValor"))
	    {
		setValorCasilla(pE);
	    }
	    else
	    {
		quitarValorCasilla(pE);
	    }
	}

	/**
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	
	public void mouseReleased(MouseEvent pE)
	{
	     
	}
	
    }

}
