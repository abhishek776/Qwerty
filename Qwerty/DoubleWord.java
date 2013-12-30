package Qwerty;

import info.gridworld.grid.Location;


/**
 * Branch of the PowerUp class, so that the DoubleWord.gif can be applied to
 * this class.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class DoubleWord extends PowerUp
{
    /**
     * Constructs the DoubleWord class and sets the multiplier to 2 by calling
     * the super constructor, and sets the color of the Actor to null.
     * 
     * @param loc
     */
    public DoubleWord( Location loc )
    {
        super( 2, loc );
        this.setColor( null );
    }
}
