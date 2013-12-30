package Qwerty;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;


/**
 * Represents the PowerUp in a game of Qwerty which essentially multiples and
 * increases scores of the Players in the game. It extends Actor, but doesn't
 * act.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class PowerUp extends Actor
{
    /**
     * Refers to the multiplier of the PowerUp
     */
    private final int mult;

    /**
     * Refers to the original Location the letter was placed in, used for
     * drawing letters for the Players.
     */
    private final Location origLoc;


    /**
     * Constructs the PowerUp class and constructs the different instance
     * variables.
     * 
     * @param multiplier
     * @param loc
     */
    public PowerUp( int multiplier, Location loc )
    {
        mult = multiplier;
        origLoc = loc;
    }


    /**
     * Returns the multiplier of this PowerUp.
     * 
     * @return
     */
    public int getMult()
    {
        return mult;
    }


    /**
     * Returns the original location of this object.
     * 
     * @return
     */
    public Location getLoc()
    {
        return origLoc;
    }


    /**
     * Overrides the act method, so this object does not do anything.
     */
    public void act()
    {

    }
}
