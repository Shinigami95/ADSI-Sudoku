package packSudoku;


public class CondJugadorIgual implements ICondicion<Jugador> {

    private Jugador jugador1;
    
    public CondJugadorIgual(Jugador pJug1)
    {
	jugador1 = pJug1;
    }

    /* (non-Javadoc)
     * @see packSudoku.ICondicion#satisfaceCondicion(java.lang.Object)
     */
    @Override
    public boolean satisfaceCondicion(Jugador pElem)
    {
	
	return jugador1.isIgual(pElem);
    }

}
