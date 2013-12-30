package Qwerty;

import java.util.ArrayList;
import info.gridworld.grid.Location;

/**
 * Represents the easy AI, which takes all end locations and finds a word with 
 * them and tests them, and picks the best one.
 * @author Abhishek, Aneesh, Jeffrey
 *
 */
public class AIControlEasy extends AIControl
{

    /**
     * Constructs the easy AI, by calling the super constructor of AIControl.
     * 
     * @param dic
     * @param board
     */
    public AIControlEasy( Dictionary dic, ScrabbleBoard board )
    {
        super( dic, board );
    }


    /**
     * Selects the locations for the Easy Ai, by only selecting the locations
     * that have only 1 letter adjacent to them.
     */
    public ArrayList<Location> selectLocations( ArrayList<Letter> letters )
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for ( Letter let : letters )
        {
            if ( let.countAdjLoc() == 1 )
            {
                locs.add( let.getLocation() );
            }
        }
        return locs;
    }

}
