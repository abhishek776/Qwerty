package Qwerty;

import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * AIControl serves as the base for all the AI levels, and contains the generic
 * methods for all the levels.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public abstract class AIControl implements Player
{
    /**
     * Reference to the Qwerty board.
     */
    private ScrabbleBoard boar;

    /**
     * Records the AI's score.
     */
    private int score;

    /**
     * A reference to the Dictionary Class.
     */
    Dictionary dict;

    /**
     * An array list of the letters the AI has.
     */
    ArrayList<Letter> letList;

    /**
     * The number of units in a side of the grid.
     */
    public final int NUM_UNIT;


    /**
     * Constructs the AI, and calls drawLetters to get the letters, this AI
     * contains.
     * 
     * @param dic
     * @param boar
     */
    public AIControl( Dictionary dic, ScrabbleBoard boar )
    {
        this.boar = boar;
        score = 0;
        dict = dic;
        NUM_UNIT = boar.getGrid().getNumRows() - 1;
        letList = new ArrayList<Letter>( 8 );
        drawLetters();
    }


    /**
     * Gets the locations of letters already on the board for which the AI will
     * find words with. Returns an ArrayList of locations of the letters it
     * wants to crosscheck.
     * 
     * @param letters
     * @return
     */
    public abstract ArrayList<Location> selectLocations(
        ArrayList<Letter> letters );


    /**
     * Returns a reference to the ScrabbleBoard being used in this game.
     * 
     * @return
     */
    public ScrabbleBoard getBoard()
    {
        return boar;
    }


    /**
     * Returns the score of the AI.
     * 
     * @return
     */
    public int getScore()
    {
        return score;
    }


    /**
     * Adds a certain point value to the AI's score.
     * 
     * @param add
     */
    public void addScore( int add )
    {
        score += add;
    }


    /**
     * Draws letters for the AI.
     */
    public void drawLetters()
    {
        for ( int i = letList.size(); i < 8; i++ )
        {
            letList.add( boar.drawLetter() );
        }
    }


    /**
     * Exchanges a random letter from letList with LetterBag for a new letter
     * when it doesn't have a word to place.
     */
    public void exchange()
    {
        int index = (int)( Math.random() * letList.size() );
        boar.addToBag( constructAppLetter( letList.get( index ).getLetter() ) );
        letList.remove( index );
        drawLetters();
    }


    /**
     * Takes the loc from which the letter from the board was used, and the word
     * which was created, and removes the letters from letList which were used
     * by the AI and then draws new letters to replace the letters that were
     * used.
     * 
     * @param maxloc
     * @param maxword
     */
    public void resetLetters( Location maxloc, String maxword )
    {
        char ch = ( (Letter)boar.getGrid().get( maxloc ) ).getLetter()
            .toLowerCase()
            .charAt( 0 );
        boolean bool = false;
        for ( int i = 0; i < maxword.length(); i++ )
        {
            char temp = maxword.charAt( i );
            if ( temp != ch || ( temp == ch && bool ) )
            {
                for ( int j = letList.size() - 1; j >= 0; j-- )
                {
                    Letter let = letList.get( j );
                    if ( let.getLetter().toLowerCase().charAt( 0 ) == temp )
                    {
                        letList.remove( j );
                        bool = false;
                        break;
                    }
                }
            }
            if ( temp == ch )
            {
                bool = true;
            }
        }
        drawLetters();
    }


    /**
     * Turn letList into an array of Strings which each represent a letter from
     * which the first element in the array is the letter from the board, and
     * the next 8 are the letters from letList. It then calls the
     * chooseRandomWord() method in the dictionary to find a word, and returns
     * the word.
     * 
     * @param loc
     * @return
     */
    public String getWordThatFits( Location loc )
    {
        ScrabbleBoard boar = getBoard();
        Letter let = (Letter)boar.getGrid().get( loc );
        String letter = let.getLetter().toLowerCase();
        String[] word = new String[9];
        word[0] = letter;
        for ( int i = 1; i < 9; i++ )
        {
            word[i] = letList.get( i - 1 ).getLetter().toLowerCase();
        }

        String possWord = dict.chooseRandomWord( word );

        return possWord;
    }


    /**
     * It takes the word that wants to be placed on the board, and the Location
     * of the existing letter, and places it on the board, and then checks its
     * validity. It then returns the score of the board, or returns -1 if that
     * particular word was not valid on the board.
     * 
     * @param word
     * @param loc
     * @return
     */
    public int placeWord( String word, Location loc )
    {
        if ( word == null )
        {
            return -1;
        }
        if ( loc == null )
        {

            for ( int i = 0; i < word.length(); i++ )
            {
                Letter let = constructAppLetter( word.substring( i, i + 1 ) );
                let.setColor( Color.RED );
                let.putSelfInGrid( boar.getGrid(), new Location( 7, i + 7 ) );

            }

            if ( !boar.boardIsValidAI() )
            {
                // System.out.println( "false" );
                boar.removeAllRed();
                return -1;
            }

            return boar.getScore();
        }
        ScrabbleBoard boar = getBoard();
        Letter l = (Letter)boar.getGrid().get( loc );
        boolean vert;

        if ( loc.getCol() == NUM_UNIT )
        {
            if ( boar.getGrid().get( loc.getAdjacentLocation( 270 ) ) != null )
            {
                vert = true;
            }
            else
            {
                vert = false;
            }
        }
        else if ( loc.getCol() == 0 )
        {
            if ( boar.getGrid().get( loc.getAdjacentLocation( 90 ) ) != null )
            {
                vert = true;
            }
            else
            {
                vert = false;
            }
        }
        else if ( boar.getGrid().get( loc.getAdjacentLocation( 90 ) ) != null
            || boar.getGrid().get( loc.getAdjacentLocation( 270 ) ) != null )
        {
            vert = true;
        }
        else
        {
            vert = false;
        }

        // System.out.println( l + ", " + word );

        int index = word.indexOf( l.getLetter().toLowerCase() );

        if ( vert )
        {
            for ( int i = loc.getRow(); i < loc.getRow() + word.length(); i++ )
            {
                // System.out.println("Limit: " + (loc.getRow() +
                // word.length()));
                if ( i - index < 0 || i - index > NUM_UNIT )
                {
                    return -1;
                }

                if ( i - index != loc.getRow() )
                {
                    if ( boar.getGrid().get( new Location( i - index,
                        loc.getCol() ) ) instanceof Letter )
                    {
                        return -1;
                    }

                    // System.out.println( i - index );
                    Letter let = constructAppLetter( word.substring( i
                        - loc.getRow(),
                        i - loc.getRow() + 1 ) );
                    let.setColor( Color.RED );
                    let.putSelfInGrid( boar.getGrid(), new Location( i - index,
                        loc.getCol() ) );
                }
            }
        }

        else
        {

            for ( int i = loc.getCol(); i < loc.getCol() + word.length(); i++ )
            {
                // System.out.println("Limit: " + (loc.getRow() +
                // word.length()));
                if ( i - index < 0 || i - index > NUM_UNIT )
                {
                    return -1;
                }

                if ( i - index != loc.getCol() )
                {
                    if ( boar.getGrid().get( new Location( loc.getRow(), i
                        - index ) ) instanceof Letter )
                    {
                        return -1;
                    }

                    // System.out.println( i - index );
                    Letter let = constructAppLetter( word.substring( i
                        - loc.getCol(),
                        i - loc.getCol() + 1 ) );
                    let.setColor( Color.RED );
                    let.putSelfInGrid( boar.getGrid(),
                        new Location( loc.getRow(), i - index ) );
                }
            }
        }

        if ( !boar.boardIsValidAI() )
        {
            // System.out.println( "false" );
            boar.removeAllRed();
            return -1;
        }

        int sc = boar.getScore();

        // System.out.println(sc + " true");

        return sc;
    }


    /**
     * Plays the turn of the AI by doing the following steps: (1) Creates the
     * array of possible locations for which the AI will test with a word. (2)
     * Finds a possible word for each of these locations. (3) Tests each word at
     * their corresponding locations and gets the score of each word. (4)
     * Records the maxword, maxscore, and maxloc for the best possible turn out
     * of the words which were tried. (5) Places the best word out of the
     * possibilities and returns the score that the AI would receive.
     */
    public void playTurn()
    {
        ScrabbleBoard boar = getBoard();

        ArrayList<Location> locs = boar.getGrid().getOccupiedLocations();
        ArrayList<Letter> lets = new ArrayList<Letter>();
        for ( Location loc : locs )
        {
            if ( boar.getGrid().get( loc ) instanceof Letter )
            {
                lets.add( (Letter)boar.getGrid().get( loc ) );
            }

        }

        if ( lets.isEmpty() )
        {
            String s = dict.getWordFirst( letList );
            int score = placeWord( s, null );
            boar.makeAllRedNull();
            boar.show();
            addScore( score );
            boar.getGrid().put( new Location( 7, 6 ), new LetterA( null ) );
            resetLetters( new Location( 7, 6 ), "a" + s );
            boar.getGrid().remove( new Location( 7, 6 ) );
            boar.setPlayMess();
            return;
        }

        // locs = getEndLocations( lets );
        locs = this.selectLocations( lets );

        int maxScore = 0;
        String maxword = "";
        Location maxloc = null;

        for ( Location loc : locs )
        {
            String word = getWordThatFits( loc );
            int score = 0;
            score = placeWord( word, loc );
            boar.removeAllRed();

            if ( maxScore < score )
            {
                maxScore = score;
                maxword = word;
                maxloc = loc;
            }
        }

        if ( maxloc != null )
        {
            placeWord( maxword, maxloc );
            boar.makeAllRedNull();
            boar.show();
            addScore( maxScore );
            resetLetters( maxloc, maxword );
            // System.out.println("-------------------");
        }
        else
        {
            exchange();
            boar.restorePowerUps();
            boar.show();
            JOptionPane.showMessageDialog( null,
                "The Computer Exchanged One Of Its Letters.\nYour Turn!" );
        }

    }


    /**
     * Gets a string of one character which is a particular letter and
     * constructs the corresponding Letter object for that Letter.
     * 
     * @param s
     * @return
     */
    public Letter constructAppLetter( String s )
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
     * Finalizes the score of the AI by taking its score acn subtracting the
     * point value of each letter it has remaining.
     */
    public int finalizeScore()
    {
        int s = score;
        for ( Letter let : letList )
        {
            s -= let.getScore();
        }
        return s;
    }

}
