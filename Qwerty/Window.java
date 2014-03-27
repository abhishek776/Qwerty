package Qwerty;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.gui.WorldFrame;


/**
 * Extends actor world for both PlayerControl and ScrabbleBoard inorder to help
 * set up the GUI involved in both the classes, as the GUI is especially
 * similar. It also contains some generic methods which are used by both
 * PlayerControl and ScrabbleBoard.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public class Window extends ActorWorld
{
    /**
     * Represents the frame of the Window class which extends ActorWorld.
     */
    private JFrame frame;

    /**
     * Represents the different attributes of the JFrame on where to place it on
     * the screen.
     */
    int x, y, width, height;


    /**
     * Constructs the window class, by calling the super and with an input size
     * and location. The Strings represent the titles on the buttons of the
     * GridWorld frame, for use by the ScrabbleBoard and PlayerControl.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param step
     * @param run
     * @param stop
     */
    public Window(
        int x,
        int y,
        int width,
        int height,
        String step,
        String run,
        String stop )
    {
        super( step, run, stop );
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    /**
     * Constructs the window class, by calling the super and with an input size
     * and location. The Strings represent the titles on the buttons of the
     * GridWorld frame, for use by the ScrabbleBoard and PlayerControl. However,
     * it calls a different super constructor with grid as one of the
     * parameters.
     * 
     * @param grid
     * @param x
     * @param y
     * @param width
     * @param height
     * @param s
     * @param r
     * @param st
     */
    public Window(
        Grid<Actor> grid,
        int x,
        int y,
        int width,
        int height,
        String s,
        String r,
        String st )
    {
        super( grid, s, r, st );
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    /**
     * Shows the Window class, in a similar way as the super class, but it sets
     * new bounds and location for the Window to be showed.
     */
    public void show()
    {
        if ( frame == null )
        {
            frame = new WorldFrame<Actor>( this );
            frame.setBounds( x, y, width, height );
            frame.setVisible( true );
        }
        else
            frame.repaint();
    }


    /**
     * Makes all the actors colored red in the grid to a null color.
     */
    public void makeAllRedNull()
    {
        for ( int i = 0; i < getGrid().getNumRows(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumCols(); j++ )
            {
                Actor actor = getGrid().get( new Location( i, j ) );
                if ( actor != null && actor.getColor() != null
                    && actor.getColor().equals( Color.RED ) )
                {
                    actor.setColor( null );
                }
            }
        }

        this.show();
    }


    /**
     * Removes all the actors from the Grid that are colored red.
     */
    public void removeAllRed()
    {
        for ( int i = 0; i < getGrid().getNumRows(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumCols(); j++ )
            {
                Actor actor = getGrid().get( new Location( i, j ) );
                if ( actor != null && actor.getColor() != null
                    && actor.getColor().equals( Color.RED ) )
                {
                    actor.removeSelfFromGrid();
                }
            }
        }

        this.show();
    }

    /**
     * Gets an array of all the empty locations in the grid where no actor is contained.
     * @return
     */
    public ArrayList<Location> getEmptyLocations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        for ( int i = 0; i < getGrid().getNumRows(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumCols(); j++ )
            {
                Actor actor = getGrid().get( new Location( i, j ) );
                if ( actor == null )
                {
                    res.add( new Location( i, j ) );
                }
            }
        }
        return res;

    }


    /**
     * Shuts down the program.
     */
    public void close()
    {
        System.exit( 0 );
    }


    /**
     * Returns a boolean determining whether there are any red actors on the grid.
     * @return True: if there is at least one red actor
     *         False: if there is no red actors
     */
    public boolean hasRed()
    {
        for ( int i = 0; i < getGrid().getNumRows(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumCols(); j++ )
            {
                Actor actor = getGrid().get( new Location( i, j ) );
                if ( actor instanceof Letter && actor.getColor() != null
                    && actor.getColor().equals( Color.RED ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
}
