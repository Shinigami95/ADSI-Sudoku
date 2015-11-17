package packInterfazGrafica;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.border.*;

public class VentanaError extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = -696777872030093319L;
    private JPanel panel = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel panelOk = new JPanel();
    private JButton botonOk = new JButton();
    private JTextArea textMensaje = new JTextArea();
    private Border border3;
    private Controlador controlador;
   

    private static VentanaError miVentana;

    /**
     * 
     * @return
     */
    public static VentanaError obtVentanaError() {
	if (miVentana == null) {
	    miVentana = new VentanaError();
	}
	return miVentana;
    }

    /**
     * 
     * @param pFrame
     * @param pTitle
     * @param pModal
     */
    private VentanaError(Frame pFrame, String pTitle, boolean pModal) {
	super(pFrame, pTitle, pModal);
	try {
	    jbInit();
	    pack();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
 * 
 */
    private VentanaError() {
	this(null, "¡¡¡ERROR!!", true);
    }

    /**
     * 
     * @throws Exception
     */

    void jbInit() throws Exception {
	controlador = new Controlador();
	border3 = BorderFactory.createCompoundBorder(new EtchedBorder(
		EtchedBorder.RAISED, Color.white, new Color(156, 156, 158)),
		BorderFactory.createEmptyBorder(10, 10, 10, 10));
	panel.setLayout(borderLayout1);
	botonOk.setBorder(BorderFactory.createRaisedBevelBorder());
	botonOk.setPreferredSize(new Dimension(51, 25));
	botonOk.setText("OK");
	botonOk.addActionListener(controlador);
	textMensaje.setEditable(false);
	textMensaje.setPreferredSize(new Dimension(200, 80));
	textMensaje.setBackground(SystemColor.text);
	textMensaje.setFont(new java.awt.Font("Dialog", 0, 12));
	textMensaje.setBorder(border3);
	textMensaje.setWrapStyleWord(true);
	textMensaje.setLineWrap(true);
	getContentPane().add(panel);
	panel.add(panelOk, BorderLayout.SOUTH);
	panelOk.add(botonOk, null);
	panel.add(textMensaje, BorderLayout.CENTER);
    }

    /**
     * 
     * @param pMensaje
     */
    public void mostrar(String pMensaje) {
	textMensaje.setText(pMensaje);
	this.setModal(true);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();
	if (frameSize.height > screenSize.height) {
	    frameSize.height = screenSize.height;
	}
	if (frameSize.width > screenSize.width) {
	    frameSize.width = screenSize.width;
	}
	this.setLocation((screenSize.width - frameSize.width) / 2,
		(screenSize.height - frameSize.height) / 2);

	this.setVisible(true);
    }

    private class Controlador implements ActionListener {

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent pE) {
	    setVisible(false);

	}

    }

}
