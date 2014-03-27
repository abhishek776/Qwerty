package Qwerty;

import info.gridworld.grid.Location;


/**
 * Branch of the PowerUp class, so that the TripleWord.gif can be applied to
 * this class.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public class TripleWord extends PowerUp
{
    /**
     * Constructs the TripleWord class and sets the multiplier to 3 by calling
     * the super constructor, and sets the color of the Actor to null.
     * 
     * @param loc
     */
    public TripleWord( Location loc )
    {
        super( 3, loc );
        this.setColor( null );
    }
}
