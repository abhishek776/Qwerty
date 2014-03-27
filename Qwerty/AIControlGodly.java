package Qwerty;

import info.gridworld.grid.Location;

import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * The AIControlGodly runs a very long algorithm which finds a word for each
 * location it uses in every length varying from 2-9, and then places the best
 * word out of those on the board.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public class AIControlGodly extends AIControl
{
    /**
     * Constructs AIControlGodly by calling the super constructor.
     * 
     * @param dic
     * @param boar
     */
    public AIControlGodly( Dictionary dic, ScrabbleBoard boar )
    {
        super( dic, boar );
    }


    /**
     * Selects locations of all letters that are connected to 2 or less other
     * letters.
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


    /**
     * Overrides the playTurn of the super class in order to find a word of each
     * length and to test each of them.
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

        locs = this.selectLocations( lets );

        int maxScore = 0;
        String maxword = "";
        Location maxloc = null;

        for ( Location loc : locs )
        {
            for ( int i = 2; i <= 9; i++ )
            {

                String word = getWordThatFits( loc, i );

                int score = placeWord( word, loc );
                boar.removeAllRed();

                if ( maxScore < score )
                {
                    maxScore = score;
                    maxword = word;
                    maxloc = loc;
                }
            }
        }

        if ( maxloc != null )
        {
            placeWord( maxword, maxloc );
            boar.makeAllRedNull();
            boar.show();
            addScore( maxScore );
            resetLetters( maxloc, maxword );
        }
        else
        {
            boar.restorePowerUps();
            exchange();
            boar.show();
            JOptionPane.showMessageDialog( null,
                "The Computer Exchanged One Of Its Letters.\nYour Turn!" );
        }

    }


    private String getWordThatFits( Location loc, int length )
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

        String possWord = dict.chooseLongestRandomWord( word, length );

        return possWord;
    }

}
