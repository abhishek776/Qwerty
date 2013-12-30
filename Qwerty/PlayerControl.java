package Qwerty;

import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;


/**
 * Represents the PlayerControl which extends the Window, indirectly also
 * extending ActorWorld. It implements the Player class for polymorphism, as the
 * PlayerControl represents one of the "Player"s in the game. The class pictures
 * the 8 letters which the user has, and allows the user to move it to the
 * ScrabbleBoard. It allows the user to enter his/her word, and allows them to
 * exchange one of their letters, and also has a reset function which brings
 * back all the letters placed on the board back, to the original state of the
 * grid.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class PlayerControl extends Window implements Player
{
    /**
     * Represents the score of the User.
     */
    private int score = 0;

    /**
     * Reference to the board where the game is occurring.
     */
    private ScrabbleBoard boar;

    /**
     * Reference to the last location that was clicked on PlayerControl.
     */
    private Location clicked;


    /**
     * Constructs the PlayerControl object, by calling the super constructor of
     * Window class. It also draws the letters from the LetterBag which this
     * user will play with, and at the end sets the message which it has on the
     * top of the window to the player.
     * 
     * @param board
     * @param name
     */
    public PlayerControl( ScrabbleBoard board, String name )
    {
        super( new BoundedGrid<Actor>( 2, 4 ),
            790,
            0,
            450,
            370,
            "Enter",
            "Exchange",
            "Reset" );

        boar = board;
        drawLetters();
        clicked = null;

        this.setMessage();
    }


    /**
     * Adds a certain amount to the existing score of the user.
     * 
     * @param s
     */
    public void addScore( int s )
    {
        score += s;

    }


    /**
     * Sends a particular letter from this grid, to a particular Location on the
     * Scrabble board, if the location is valid on the board. If the location on
     * the board already contains a letter, it sends a message to the user, and
     * otherwise sends the letter to the board, and sets it red on the board and
     * on the PlayerControl to indicate if the letter has already been used, and
     * which letters on the board were placed in this turn. It then sets clicked
     * to null.
     * 
     * @param let
     * @param loc
     */
    public void send( Letter let, Location loc )
    {
        if ( !boar.getGrid().isValid( loc ) )
        {
            // return false;
            return;
        }
        Letter let1 = constructAppLetter( let.getLetter() );

        if ( boar.getGrid().get( loc ) instanceof Letter )
        {
            JOptionPane.showMessageDialog( null,
                "Sorry There Is Already A Letter There.",
                "Error Message",
                0 );
            return;

        }
        let1.putSelfInGrid( boar.getGrid(), loc );
        let.setColor( Color.RED );
        let1.setColor( Color.RED );
        this.show();
        boar.show();
        // return true;
    }


    /**
     * Acts as the enter button and method, and does multiple things to see if
     * the word placed on the board is valid. First it checks if the user has
     * placed anything on the board in the first place. If not, it sends and
     * error message to the user. If so, it then tests if the board is valid by
     * calling the method in the ScrabbleBoard. If it is valid, it sets the
     * letters on the board, and if not, it brings the letters back to this
     * grid. It then shows the board, and calculates the score the user receives
     * if the word is valid. Then if the move was valid, it tells the AI to play
     * its turn, and then sets both messages on the ScrabbleBoard and the
     * PlayerControl.
     */
    public void step()
    {
        if ( !boar.hasRed() )
        {
            JOptionPane.showMessageDialog( null,
                "Place letters on the board to \"Enter\" your word.",
                "Error",
                0 );
            boar.show();
            super.step();
            return;
        }
        boolean bool = boar.boardIsValid();
        if ( bool )
        {
            addScore( boar.getScore() );
            boar.makeAllRedNull();

            this.removeAllRed();

            drawLetters();

        }
        else
        {
            boar.removeAllRed();
            this.makeAllRedNull();
            boar.restorePowerUps();
        }
        show();

        boar.show();
        super.step();

        if ( bool )
        {
            boar.tellAIToGo();
        }
        boar.setMessageToScore();
        this.setMessage();
        boar.restorePowerUps();
    }


    /**
     * Exchanges the letter on the location which was clicked with another
     * letter in the bag, and then tell the AI to play its turn, and then shows
     * all the messages and grids.
     */
    public void run()
    {
        if ( clicked != null )
        {
            boar.addToBag( constructAppLetter( ( (Letter)this.getGrid()
                .remove( clicked ) ).getLetter() ) );
            drawLetters();
            show();
            clicked = null;
        }
        else
        {
            JOptionPane.showMessageDialog( null,
                "Select Letter To Exchange First",
                "Error",
                0 );
            boar.show();
            return;
        }
        boar.show();
        super.run();
        boar.tellAIToGo();
        setMessage();
        boar.setMessageToScore();
        boar.restorePowerUps();
        boar.show();
    }


    /**
     * Resets all the letters placed in the board by the user back into the
     * player control.
     */
    public void stop()
    {
        boar.removeAllRed();
        this.makeAllRedNull();
        boar.restorePowerUps();
        boar.show();
        super.stop();
    }


    /**
     * For every empty location in PlayerControl calls the draw letter method in
     * boar, and adds it to the grid.
     */
    private void drawLetters()
    {
        ArrayList<Location> list = this.getEmptyLocations();
        for ( Location loc : list )
        {
            Letter let = boar.drawLetter();
            let.setWorld( this );
            this.add( loc, let );
        }
    }


    /**
     * Constructs appropriate letter based off of the input string by the
     * client.
     * 
     * @param s
     * @return
     */
    private Letter constructAppLetter( String s )
    {
        if ( s.equalsIgnoreCase( "A" ) )
            return new LetterA( this );

        if ( s.equalsIgnoreCase( "B" ) )
            return new LetterB( this );

        if ( s.equalsIgnoreCase( "C" ) )
            return new LetterC( this );

        if ( s.equalsIgnoreCase( "D" ) )
            return new LetterD( this );

        if ( s.equalsIgnoreCase( "E" ) )
            return new LetterE( this );

        if ( s.equalsIgnoreCase( "F" ) )
            return new LetterF( this );

        if ( s.equalsIgnoreCase( "G" ) )
            return new LetterG( this );

        if ( s.equalsIgnoreCase( "H" ) )
            return new LetterH( this );

        if ( s.equalsIgnoreCase( "I" ) )
            return new LetterI( this );

        if ( s.equalsIgnoreCase( "J" ) )
            return new LetterJ( this );

        if ( s.equalsIgnoreCase( "K" ) )
            return new LetterK( this );

        if ( s.equalsIgnoreCase( "L" ) )
            return new LetterL( this );

        if ( s.equalsIgnoreCase( "M" ) )
            return new LetterM( this );

        if ( s.equalsIgnoreCase( "N" ) )
            return new LetterN( this );

        if ( s.equalsIgnoreCase( "O" ) )
            return new LetterO( this );

        if ( s.equalsIgnoreCase( "P" ) )
            return new LetterP( this );

        if ( s.equalsIgnoreCase( "Q" ) )
            return new LetterQ( this );

        if ( s.equalsIgnoreCase( "R" ) )
            return new LetterR( this );

        if ( s.equalsIgnoreCase( "S" ) )
            return new LetterS( this );

        if ( s.equalsIgnoreCase( "T" ) )
            return new LetterT( this );

        if ( s.equalsIgnoreCase( "U" ) )
            return new LetterU( this );

        if ( s.equalsIgnoreCase( "V" ) )
            return new LetterV( this );

        if ( s.equalsIgnoreCase( "W" ) )
            return new LetterW( this );

        if ( s.equalsIgnoreCase( "X" ) )
            return new LetterX( this );

        if ( s.equalsIgnoreCase( "Y" ) )
            return new LetterY( this );

        if ( s.equalsIgnoreCase( "Z" ) )
            return new LetterZ( this );

        return null;
    }


    /**
     * Sets the clicked to the parameter, and returns true to prevent the
     * showing of the dropdown options on the grid.
     */
    public boolean locationClicked( Location loc )
    {
        clicked = loc;
        return true;
    }


    /**
     * Gets the location which was clicked for the client.
     * 
     * @return
     */
    public Location getClicked()
    {
        return clicked;
    }


    /**
     * Sets the clicked object to null after it has been used.
     */
    public void setClicked()
    {
        clicked = null;
    }


    /**
     * Gets the score of the user.
     * 
     * @return
     */
    public int getScore()
    {
        return score;
    }


    /**
     * Resets the player control object for a new game.
     */
    public void reset()
    {
        ArrayList<Location> locs = getGrid().getOccupiedLocations();
        for ( Location loc : locs )
        {
            getGrid().remove( loc );
        }

        drawLetters();
        score = 0;
        setMessage();
        show();
    }


    /**
     * Sets message to the amount of letters left in the bag, and then tells the
     * User how the game ends.
     */
    public void setMessage()
    {
        super.setMessage( "Letters Remaining In Bag: "
            + boar.lettersRemainingInBag() + " (WARNING: When "
            + "bag is empty the points\non your letters "
            + "are substracted from your score.)" );
    }


    /**
     * Finalizes the users score by taking its score and subtracting the score
     * of each letter it has remaining.
     */
    public int finalizeScore()
    {
        int s = score;
        for ( int i = 0; i < getGrid().getNumCols(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumRows(); j++ )
            {
                if ( getGrid().get( new Location( j, i ) ) instanceof Letter )
                {
                    s -= ( (Letter)getGrid().get( new Location( j, i ) ) ).getScore();
                }
            }
        }

        return s;
    }
    
    /**
     * FOR TESTING PURPOSES ONLY.
     * @param loc
     */
    protected void setClicked( Location loc )
    {
        clicked = loc;
    }


}
