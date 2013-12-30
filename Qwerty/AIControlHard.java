package Qwerty;

import info.gridworld.grid.Location;

import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * This class represents the hard class, which runs the same algorithm as the
 * easy and medium, but also tries to find a 9 letter word to put on the board,
 * and if it cannot find it, it runs the original algorithm.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class AIControlHard extends AIControl
{

    /**
     * Constructs the AIControlHard class by calling super constructor.
     * 
     * @param dic
     * @param boar
     */
    public AIControlHard( Dictionary dic, ScrabbleBoard boar )
    {
        super( dic, boar );
    }


    /**
     * Selects the same way as the medium level. All locations that are
     * connected to 2 or less other letters.
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
     * Overrides the play turn method, so that it also tries to find a nine
     * letter word to put on the board.
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
            String word = getLongestWordThatFits( loc );

            int score = placeWord( word, loc );
            boar.removeAllRed();

            if ( maxScore < score )
            {
                maxScore = score;
                maxword = word;
                maxloc = loc;
            }
        }

        for ( Location loc : locs )
        {
            String word = getWordThatFits( loc );

            int score = placeWord( word, loc );
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


    private String getLongestWordThatFits( Location loc )
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

        String possWord = dict.chooseLongestRandomWord( word );

        return possWord;

    }

}
