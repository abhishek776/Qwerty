package Qwerty;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;


/**
 * The Letter class represents a Letter in the Qwerty game, which represents a
 * certain letter in the English Alphabet, a point value corresponding to the
 * letter, and also references which player it was drawn to. It is the primary
 * pieces used by players in the game.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class Letter extends Actor
{
    /**
     * Represents the point value which this letter represents.
     */
    private final int score;

    /**
     * Represents the Letter which this letter represents.
     */
    private final String let;

    private Player player;


    /**
     * Constructor for Letter constructs the Letter with a representing a
     * certain letter and a score value. The letter also provides a world for
     * which it originally placed in.
     * 
     * @param let
     * @param value
     * @param world
     */
    public Letter( String let, int value, Player play )
    {
        this.let = let;
        score = value;
        player = play;
        setColor( null );
    }


    /**
     * Sets which Player this Letter was drawn to.
     * 
     * @param play
     */
    public void setWorld( Player play )
    {
        player = play;
    }


    /**
     * Do nothing when acting.
     */
    public void act()
    {

    }


    /**
     * Returns the score which this letter represents.
     * 
     * @return score
     */
    public int getScore()
    {
        return score;
    }


    /**
     * Returns the letter this class represents.
     * 
     * @return String letter
     */
    public String getLetter()
    {
        return let;
    }


    /**
     * Returns player this is originally in.
     * 
     * @return
     */
    public Player getPlayer()
    {
        return player;
    }


    /**
     * Allows use to send itself from the playerControl to a location in the
     * board only if this is in PlayerControl and color is null.
     * 
     * @param loc
     */
    public void send( Location loc )
    {
        if ( player instanceof Player && this.getColor() == null )
        {
            ( (PlayerControl)player ).send( this, loc );
            // return true;
        }
        // return false;
    }


    /**
     * Counts how many other letters surround this Letter in the 4 compass
     * directions.
     * 
     * @return
     */
    public int countAdjLoc()
    {

        int count = 0;
        for ( int i = 0; i < 4; i++ )
        {
            if ( getGrid().isValid( getLocation().getAdjacentLocation( i * 90 ) ) )
            {
                Location loc = getLocation().getAdjacentLocation( i * 90 );
                if ( getGrid().get( loc ) instanceof Letter )
                {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * Checks if this Letter is surrounded by any Letters that were placed on
     * the board before.
     * 
     * @return
     */
    public boolean checkForExistingTileNull()
    {
        ArrayList<Location> list = this.getGrid()
            .getOccupiedAdjacentLocations( this.getLocation() );
        for ( Location loc : list )
        {
            if ( getGrid().get( loc ) instanceof Letter
                && getGrid().get( loc ).getColor() == null )
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Overrides the equals method to just check letter comparison with the
     * letter the Letter represents.
     * 
     * @param other
     * @return
     */
    public boolean equals( Letter other )
    {
        if ( other.getLetter().equalsIgnoreCase( this.getLetter() ) )
        {
            return true;
        }
        return false;
    }


    /**
     * Returns a String of the letter this represents.
     */
    public String toString()
    {
        return let;
    }

}
