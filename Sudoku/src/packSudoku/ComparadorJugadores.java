package packSudoku;

import java.util.Comparator;


public class ComparadorJugadores implements Comparator<Jugador> {
    

    /**
     * ComparadorJugadores
     * 
     * <p>Comparador para jugadores</p>
     *         
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Jugador pO1, Jugador pO2)
    {
	int difPuntos = pO1.getPuntos() - pO2.getPuntos();
	if (difPuntos != 0)
	{
	    return difPuntos;
	}
	else
	{
	    return -pO1.getNombre().compareTo(pO2.getNombre());
	}
    }

}
