package Qwerty;

import info.gridworld.grid.Location;

import java.util.ArrayList;


/**
 * The only algorithmic difference between this AIMedium and the AI easy from
 * the other class is that this AI runs the same word choosing algorithm for all
 * letters on the board that are only connected to two or less letters. Other
 * than that it runs exactly the same algorithm as the easy, but is more
 * skilled, as it tests more locations.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public class AIControlMedium extends AIControl
{

    /**
     * Constructs AIControlMedium with the super constructor.
     * 
     * @param dic
     * @param board
     */
    public AIControlMedium( Dictionary dic, ScrabbleBoard board )
    {
        super( dic, board );
    }


    /**
     * Selects all the locations on the board that are only connected to two
     * other letters, and returns an ArrayLits of those locations.
     */
    public ArrayList<Location> selectLocations( ArrayList<Letter> lets )
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for ( Letter let : lets )
        {
            if ( let.countAdjLoc() < 3 )
            {
                locs.add( let.getLocation() );
            }
        }
        return locs;
    }

}
