package packInterfazGrafica;

import java.awt.*;

import javax.swing.*;

import packSudokuAntiguo.Jugador;
import packSudokuAntiguo.ListaJugadores;

import java.awt.event.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class VentanaPuntuaciones extends JFrame  implements Observer{
  /**
     * 
     */
    private static final long serialVersionUID = 8818852033751589854L;
private JPanel jPanel1 = new JPanel();
  private JButton botonOK = new JButton();
  private JList jListUsuarios = new JList();
  private ListaJugadores listaJugadores = ListaJugadores.getListaJugadores();
  private Controlador  controlador = new Controlador();

  private static VentanaPuntuaciones miVentana;

/**
 * 
 * @return
 */
  public static VentanaPuntuaciones obtVentanaPuntuaciones()
  {
      if (miVentana == null)
      {
          miVentana= new VentanaPuntuaciones();
      }
      return miVentana;
  }
  /**
   * Construcci�n de la ventana con la lista de usuarios
   */
  private VentanaPuntuaciones() {
   listaJugadores.addObserver(this);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    update(null,null);
  }
/**
 * 
 * @throws Exception
 */
  private void jbInit() throws Exception {
    botonOK.setBorder(BorderFactory.createRaisedBevelBorder());
    botonOK.setMinimumSize(new Dimension(21, 21));
    botonOK.setPreferredSize(new Dimension(41, 21));
    botonOK.setHorizontalTextPosition(SwingConstants.CENTER);
    botonOK.setMargin(new Insets(20, 14, 20, 14));
    botonOK.setText("OK");
    botonOK.addActionListener(controlador);
    this.setTitle("Lista de puntuaciones");
    jListUsuarios.setVisibleRowCount(10);
    jListUsuarios.setAutoscrolls(true);
    jListUsuarios.setFont(new Font("Monospaced", Font.BOLD,12));
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(botonOK, null);
    this.getContentPane().add(jListUsuarios, BorderLayout.CENTER);
    this.setSize(255, 255);
  }

/**
 * 
 */
  public void mostrar()
  {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Dimension frameSize = this.getSize();
     if (frameSize.height > screenSize.height) {
       frameSize.height = screenSize.height;
     }
     if (frameSize.width > screenSize.width) {
       frameSize.width = screenSize.width;
     }
     this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

     this.setVisible(true);
     update(null,null);

  }
/**
 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
 */

public void update(Observable pO, Object pArg)
{
    Vector<String> lista = new Vector<String>();
    Iterator<Jugador> it = listaJugadores.getIterador();
    int cantidad = 0;
    while (it.hasNext() && cantidad < 10)
    {
	Jugador jugador = it.next();
	String linea = String.format("\t %1$,8d \t\t <--- %2$s",jugador.getPuntos(), jugador.getNombre());
	lista.add(linea);
	cantidad++;
    }
    jListUsuarios.setListData(lista);
}

private class Controlador implements ActionListener
{

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    
    public void actionPerformed(ActionEvent pE)
    {
	System.exit(0);
    }
    
}
}
