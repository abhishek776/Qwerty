package Qwerty;

import java.awt.Color;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;


/**
 * The ScrabbleBoard class represents the board in which the SrabbleBoard occurs
 * and is the junction between the user and the AI, and essentially where all
 * the objects pertaining to the game are referenced and where everything that
 * the user does in the game is recognized, and recorded.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public class ScrabbleBoard extends Window
{

    /**
     * Represents a copy of the English dictionary.
     */
    private Dictionary dic;

    /**
     * Records the turn count. Used only in checking for board validity.
     */
    private int turn;

    /**
     * Reference to the PlayerControl which the user is controlling.
     */
    private PlayerControl play;

    /**
     * Reference to the AI which the player is playing against.
     */
    private AIControl ai;

    /**
     * Reference to the Letter Bag being used in the game.
     */
    private LetterBag bag;

    /**
     * The two dimensional array representing where PowerUps are located on the
     * board.
     */
    private int[][] boardPUps;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Number of difficulty which the user is playing.
     */
    private int diff;

    /**
     * Number representing if user wants to go first or second.
     */
    private int order;


    /**
     * Construct SrabbleBoard with all powerUps, and instantiates Dictionary,
     * and creates a PlayerControl to run the game.
     */
    public ScrabbleBoard( int diff, int order, String name )
    {
        super( new BoundedGrid<Actor>( 15, 15 ),
            0,
            0,
            790,
            910,
            "New Game",
            "End Game",
            "Dictionary Look-Up" );

        System.setProperty( "info.gridworld.gui.frametitle", name
            + "'s Game Of QWERTY" );

        constructPowerUps();
        dic = new Dictionary();
        bag = new LetterBag();
        play = ( new PlayerControl( this, name ) );
        this.name = name;
        this.diff = diff;
        this.order = order;
        if ( diff == 2 )
            ai = new AIControlHard( dic, this );
        else if ( diff == 3 )
        {
            JOptionPane.showMessageDialog( null,
                "Enter At Your Own Risk!\nTheres No Turning Back!",
                "WARNING: Danger Ahead!",
                0 );
            ai = new AIControlHard( dic, this );
        }
        else if ( diff == 1 )
            ai = new AIControlMedium( dic, this );
        else
            ai = new AIControlEasy( dic, this );
        play.setMessage();
        play.show();

        turn = 0;
        setMessageToScore();
        play.show();

        boardPUps = new int[getGrid().getNumRows()][getGrid().getNumCols()];
        setUpBoardPUps();

        this.show();

        if ( order == 1 )
        {
            ai.playTurn();
            play.setMessage();
        }
        setMessageToScore();

    }


    /**
     * Creates a 2d array where powerups are located on the original board.
     */
    private void setUpBoardPUps()
    {
        for ( int i = 0; i < getGrid().getNumRows(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumCols(); j++ )
            {
                if ( getGrid().get( new Location( i, j ) ) instanceof PowerUp )
                {
                    boardPUps[i][j] = ( (PowerUp)getGrid().get( new Location( i,
                        j ) ) ).getMult();
                }
                else
                {
                    boardPUps[i][j] = 1;
                }
            }
        }
    }


    /**
     * Puts powerups back in their original place if the Location does not
     * contain a letter.
     */
    public void restorePowerUps()
    {
        for ( int row = 0; row < getGrid().getNumRows(); row++ )
        {
            for ( int col = 0; col < getGrid().getNumCols(); col++ )
            {
                if ( this.getGrid().get( new Location( row, col ) ) == null )
                {
                    if ( boardPUps[row][col] == 2 )
                    {
                        if ( row == 7 && col == 7 )
                        {
                            this.add( new Location( row, col ),
                                new StarWord( null ) );
                        }
                        else
                        {
                            this.add( new Location( row, col ),
                                new DoubleWord( null ) );
                        }
                    }
                    if ( boardPUps[row][col] == 3 )
                    {
                        this.add( new Location( row, col ),
                            new TripleWord( null ) );
                    }
                }
            }
        }
    }


    /**
     * Calls the drawLetter method in the LetterBag. Used by players.
     * 
     * @return
     */
    public Letter drawLetter()
    {
        Letter let = bag.drawLetter();
        if ( let == null )
        {
            endGameScenario();
        }

        return let;
    }


    /**
     * Constructs all the PowerUps on the ScrabbleBoard. Used by constructor.
     */
    private void constructPowerUps()
    {
        for ( int i = 1; i <= 6; i++ )
        {
            this.add( new Location( i, i ),
                new DoubleWord( new Location( i, i ) ) );
            this.add( new Location( 14 - i, 14 - i ),
                new DoubleWord( new Location( 14 - i, 14 - i ) ) );
            this.add( new Location( i, 14 - i ),
                new DoubleWord( new Location( i, 14 - i ) ) );
            this.add( new Location( 14 - i, i ),
                new DoubleWord( new Location( 14 - i, i ) ) );
        }

        this.add( new Location( 0, 0 ), new TripleWord( new Location( 0, 0 ) ) );
        this.add( new Location( 0, 7 ), new TripleWord( new Location( 0, 7 ) ) );
        this.add( new Location( 0, 14 ), new TripleWord( new Location( 0, 14 ) ) );
        this.add( new Location( 7, 0 ), new TripleWord( new Location( 7, 0 ) ) );
        this.add( new Location( 14, 0 ), new TripleWord( new Location( 14, 0 ) ) );
        this.add( new Location( 14, 7 ), new TripleWord( new Location( 14, 7 ) ) );
        this.add( new Location( 14, 14 ),
            new TripleWord( new Location( 14, 14 ) ) );
        this.add( new Location( 7, 14 ), new TripleWord( new Location( 7, 14 ) ) );

        this.add( new Location( 7, 7 ), new StarWord( new Location( 7, 7 ) ) );
    }


    /**
     * Gets the score of all the red letters on the board, along with powerups
     * and the 50 point bonus for using all 8 letters.
     * 
     * @return
     */
    public int getScore()
    {
        int score = 0;
        int mult = 1;
        ArrayList<Location> arr = this.getGrid().getOccupiedLocations();
        int count = 0;
        for ( Location loc : arr )
        {
            if ( this.getGrid().get( loc ).getColor() != null
                && this.getGrid().get( loc ).getColor().equals( Color.RED ) )
            {
                score += ( (Letter)this.getGrid().get( loc ) ).getScore();
                count++;
                if ( boardPUps[loc.getRow()][loc.getCol()] > mult )
                {
                    mult = boardPUps[loc.getRow()][loc.getCol()];
                }
            }
        }
        score *= mult;
        if ( count == 8 )
        {
            score += 50;
        }
        return score;
    }


    /**
     * Tests all conditions of the board to be true.
     * 
     * @return true - all conditions met
     */
    public boolean boardIsValid()
    {
        boolean result = true;

        turn++;
        if ( !allWordsValid() )
        {
            result = false;
            JOptionPane.showMessageDialog( null,
                "Sorry, That Word Is Not Valid.",
                "Invalid Turn!",
                0 );
        }
        else if ( !allRedAdjacent() )
        {
            result = false;
            JOptionPane.showMessageDialog( null,
                "Sorry, All Of Your Tiles Must Be In Same Row Or Column",
                "Invalid Turn!",
                0 );

        }
        else if ( !testAllTouching() )
        {
            result = false;
            JOptionPane.showMessageDialog( null,
                "Sorry, All the tiles on the QwertyBoard must be connected.",
                "Invalid Turn!",
                0 );

        }
        else if ( turn != 1 && !testForExistingTile() )
        {
            result = false;
            JOptionPane.showMessageDialog( null,
                "Sorry, One Of Your Tiles Doesn't Touch An Existing Tile.",
                "Invalid Turn!",
                0 );

        }
        else if ( turn == 1
            && this.getGrid().get( new Location( 7, 7 ) ) instanceof PowerUp )
        {
            result = false;
            JOptionPane.showMessageDialog( null,
                "On The First Word, One Tile Must Be Placed On The Star.",
                "Invalid Turn!",
                0 );

        }

        if ( result == false )
        {
            turn--;
        }

        return result;

    }


    /**
     * The boardIsValid method for the AI which does not have the JOptionPane
     * messages for failures.
     * 
     * @return
     */
    public boolean boardIsValidAI()
    {
        boolean result = true;

        turn++;
        if ( !allWordsValid() )
        {
            result = false;
        }
        else if ( !allRedAdjacent() )
        {
            result = false;
        }
        else if ( !testAllTouching() )
        {
            result = false;
        }
        else if ( turn != 1 && !testForExistingTile() )
        {
            result = false;
        }
        else if ( turn == 1
            && this.getGrid().get( new Location( 7, 7 ) ) instanceof PowerUp )
        {
            result = false;
        }

        if ( result == false )
        {
            turn--;
        }

        return result;

    }


    /**
     * Figures out whether all the words created on the board are valid words.
     * 
     * @return boolean
     */
    private boolean allWordsValid()
    {
        ArrayList<String> hor = getHorizontalWords();
        for ( String s : hor )
        {
            if ( !dic.isWord( s ) )
            {
                return false;
            }
        }
        ArrayList<String> ver = getVerticalWords();
        for ( String s : ver )
        {
            if ( !dic.isWord( s ) )
            {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns array of all words present in the grid horizontally.
     * 
     * @return
     */
    private ArrayList<String> getHorizontalWords()
    {
        String res = "";
        for ( int i = 0; i < getGrid().getNumRows(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumCols(); j++ )
            {
                Actor actor = getGrid().get( new Location( i, j ) );
                if ( actor == null || !( actor instanceof Letter ) )
                {
                    res += " ";
                }
                else if ( actor instanceof Letter )
                {
                    res += ( (Letter)actor ).getLetter().toLowerCase();
                }
            }
        }
        StringTokenizer str = new StringTokenizer( res, " " );
        ArrayList<String> toReturn = new ArrayList<String>();
        while ( str.hasMoreTokens() )
        {
            String temp = str.nextToken();
            if ( temp.length() > 1 )
            {
                toReturn.add( temp );
            }
        }
        return toReturn;
    }


    /**
     * Returns array of all words present in the grid vertically.
     * 
     * @return
     */
    private ArrayList<String> getVerticalWords()
    {
        String res = "";
        for ( int i = 0; i < getGrid().getNumCols(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumRows(); j++ )
            {
                Actor actor = getGrid().get( new Location( j, i ) );
                if ( actor == null || !( actor instanceof Letter ) )
                {
                    res += " ";
                }
                else if ( actor instanceof Letter )
                {
                    res += ( (Letter)actor ).getLetter().toLowerCase();
                }
            }
        }
        StringTokenizer str = new StringTokenizer( res, " " );
        ArrayList<String> toReturn = new ArrayList<String>();
        while ( str.hasMoreTokens() )
        {
            String temp = str.nextToken();
            if ( temp.length() > 1 )
            {
                toReturn.add( temp );
            }
        }
        return toReturn;
    }


    /**
     * Tests if all tiles placed by user are adjacent in one direction.
     * 
     * @return
     */
    private boolean allRedAdjacent()
    {
        ArrayList<Location> arr = this.getGrid().getOccupiedLocations();
        ArrayList<Location> sor = new ArrayList<Location>();
        for ( Location loc : arr )
        {
            if ( this.getGrid().get( loc ).getColor() != null
                && this.getGrid().get( loc ).getColor().equals( Color.RED ) )
            {
                sor.add( loc );
            }
        }
        int row = sor.get( 0 ).getRow();
        int col = sor.get( 0 ).getCol();
        boolean b = true;
        boolean c = true;

        for ( Location loc : sor )
        {
            if ( loc.getCol() != col )
            {
                b = false;
            }
            if ( loc.getRow() != row )
            {
                c = false;
            }
        }

        return b || c;
    }


    /**
     * Tests whether or not word is adjacent to an existing tile if turn count
     * is > 1.
     * 
     * @return
     */
    private boolean testForExistingTile()
    {
        ArrayList<Location> arr = this.getGrid().getOccupiedLocations();
        ArrayList<Location> sor = new ArrayList<Location>();
        for ( Location loc : arr )
        {
            if ( this.getGrid().get( loc ).getColor() != null
                && this.getGrid().get( loc ).getColor().equals( Color.RED ) )
            {
                sor.add( loc );
            }
        }
        boolean check = false;
        for ( Location loc : sor )
        {
            Letter let = (Letter)( this.getGrid().get( loc ) );
            if ( let.checkForExistingTileNull() )
            {
                check = true;
            }
        }
        return check;
    }


    /**
     * Tests if all the letter tiles on the board are connected in some way by
     * using the algorithm that total number of links must be greater than or
     * equal to the number of letters times 2 minus 2.
     * 
     * @return
     */
    private boolean testAllTouching()
    {
        ArrayList<Location> arr = this.getGrid().getOccupiedLocations();
        ArrayList<Location> sor = new ArrayList<Location>();
        for ( Location loc : arr )
        {
            if ( this.getGrid().get( loc ) instanceof Letter )
            {
                sor.add( loc );
            }
        }

        int count = 0;

        for ( Location loc : sor )
        {
            Letter let = (Letter)( this.getGrid().get( loc ) );
            count += let.countAdjLoc();
        }
        if ( sor.size() * 2 - 2 <= count )
            return true;
        return false;
    }


    /**
     * When a location on World is clicked, it sees if a previous letter has
     * been clicked in the PlayerControl, and if it has, it takes the letter
     * from the PlayerControl and puts it on the location which was clicked on
     * the board.
     */
    public boolean locationClicked( Location loc )
    {
        Location loc1 = play.getClicked();
        if ( loc1 != null )
        {
            if ( play.getGrid().get( loc1 ) instanceof Letter )
            {
                ( (Letter)play.getGrid().get( loc1 ) ).send( loc );
                play.setClicked();
            }

        }
        return true;
    }


    /**
     * Sets Message on top of the board to the scores of the player and the
     * user.
     */
    public void setMessageToScore()

    {
        setMessage( "SCORES ------------ " + name + " : " + play.getScore()
            + " points" + "------------ Computer: " + ai.getScore() + " points" );

        this.show();
    }


    /**
     * Creates a new game for Qwerty with main menu. New Game.
     */
    public void step()
    {
        ArrayList<Location> locs = getGrid().getOccupiedLocations();
        for ( Location loc : locs )
        {
            getGrid().remove( loc );
        }

        constructPowerUps();
        bag = new LetterBag();
        play.reset();
        if ( diff == 2 )
            ai = new AIControlHard( dic, this );
        else if ( diff == 3 )
        {
            ai = new AIControlHard( dic, this );
        }
        else if ( diff == 1 )
            ai = new AIControlMedium( dic, this );
        else
            ai = new AIControlEasy( dic, this );
        play.setMessage();
        play.show();
        setMessageToScore();
        turn = 0;

        if ( order == 1 )
        {
            ai.playTurn();
            play.setMessage();
        }
        setMessageToScore();
        // this.dissapear();
        // play.dissapear();
        // new MainMenu().setVisible( true );

        show();

    }


    /**
     * Quits the game. End Game.
     */
    public void run()
    {
        System.exit( 0 );
    }


    /**
     * Instantiates the UI which is the dictionary lookup object. Dictionary
     * Look-Up.
     */
    public void stop()
    {
        UI ui = new UI();
        ui.setLocation( 300, 300 );
        ui.setVisible( true );
    }


    /**
     * Tells the appropriate AI to play its turn.
     */
    public void tellAIToGo()
    {
        ai.playTurn();
    }


    /**
     * Puts a letter back in the letterbag for the exchange method purposes.
     * 
     * @param let
     */
    public void addToBag( Letter let )
    {
        bag.addToBag( let );
    }


    /**
     * Checks if there are any letter on the board.
     * 
     * @return
     */
    public boolean isEmpty()
    {
        for ( int i = 0; i < getGrid().getNumCols(); i++ )
        {
            for ( int j = 0; j < getGrid().getNumRows(); j++ )
            {
                if ( getGrid().get( new Location( j, i ) ) instanceof Letter )
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Gets the number of letters remaining in the letterBag.
     * 
     * @return
     */
    public int lettersRemainingInBag()
    {
        return bag.remaining();
    }


    /**
     * Creates the endgame scenario and tells the user who wins and then quits
     * the game.
     */
    private void endGameScenario()
    {
        restorePowerUps();
        show();
        int userscore = play.finalizeScore();
        int aiscore = ai.finalizeScore();

        if ( userscore > aiscore )
        {
            JOptionPane.showMessageDialog( null, name + "Wins!\nUser: "
                + userscore + "    Computer: " + aiscore, "Game Over!", 0 );
        }
        else if ( aiscore > userscore )
        {
            JOptionPane.showMessageDialog( null, "Computer Wins!\nUser: "
                + userscore + "    Computer: " + aiscore, "Game Over!", 0 );
        }
        else
        {
            JOptionPane.showMessageDialog( null, "Its A Tie! \nUser: "
                + userscore + "    Computer: " + aiscore, "Game Over!", 0 );
        }

        System.exit( 0 );
    }


    /**
     * Calls the overridden method in PlayerControl to set the message to the
     * designated message for this game. And then shows PlayerControl.
     */
    public void setPlayMess()
    {
        play.setMessage();
        play.show();
    }


    /**
     * TESTING PURPOSES
     * 
     * @return
     */
    protected AIControl getAI()
    {
        return ai;
    }


    /**
     * TESTING PURPOSES
     * 
     * @return
     */
    protected PlayerControl getPlayer()
    {
        return play;
    }

}
