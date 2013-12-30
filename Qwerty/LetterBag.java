package Qwerty;

import java.util.ArrayList;


/**
 * This class keeps track of all the Letters that have not been drawn in the
 * game yet and represents a bag in the game of Scrabble. The letter also
 * indicates to the user when the game is over, by indicating it on the draw
 * letter method.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class LetterBag
{
    /**
     * Represents the ArrayList of Letters representing the letters that have
     * not yet been drawn or used in the game.
     */
    private ArrayList<Letter> bag;


    /**
     * Constructs the LetterBag with all the letters that the game, Qwerty, has
     * at the beginning of a game.
     */
    public LetterBag()
    {
        bag = new ArrayList<Letter>();
        fillUpBag();
    }


    /**
     * Returns a random letter from the bag.
     * 
     * @return A random letter from the bag, and null if no letters are present.
     */
    public Letter drawLetter()
    {
        if ( bag.isEmpty() )
        {
            return null;
        }
        int rand = (int)( Math.random() * bag.size() );
        Letter let = bag.remove( rand );
        return let;
    }


    /**
     * Adds Letter to the bag, used with the exchange methods in the Players.
     * 
     * @param let
     */
    public void addToBag( Letter let )
    {
        bag.add( let );
    }


    /**
     * Constructor helper, which puts a certain amount of each type of letter
     * into the LetterBag.
     */
    private void fillUpBag()
    {
        for ( int i = 0; i < 12; i++ )
        {
            bag.add( new LetterE( null ) );
        }

        for ( int i = 0; i < 9; i++ )
        {
            bag.add( new LetterA( null ) );
        }

        for ( int i = 0; i < 9; i++ )
        {
            bag.add( new LetterI( null ) );
        }

        for ( int i = 0; i < 8; i++ )
        {
            bag.add( new LetterO( null ) );
        }

        for ( int i = 0; i < 6; i++ )
        {
            bag.add( new LetterN( null ) );
        }

        for ( int i = 0; i < 6; i++ )
        {
            bag.add( new LetterR( null ) );
        }

        for ( int i = 0; i < 6; i++ )
        {
            bag.add( new LetterT( null ) );
        }

        for ( int i = 0; i < 4; i++ )
        {
            bag.add( new LetterL( null ) );
        }

        for ( int i = 0; i < 4; i++ )
        {
            bag.add( new LetterS( null ) );
        }

        for ( int i = 0; i < 4; i++ )
        {
            bag.add( new LetterU( null ) );
        }

        for ( int i = 0; i < 4; i++ )
        {
            bag.add( new LetterD( null ) );
        }

        for ( int i = 0; i < 3; i++ )
        {
            bag.add( new LetterG( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterB( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterC( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterM( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterP( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterF( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterH( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterV( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterW( null ) );
        }

        for ( int i = 0; i < 2; i++ )
        {
            bag.add( new LetterY( null ) );
        }

        for ( int i = 0; i < 1; i++ )
        {
            bag.add( new LetterK( null ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            bag.add( new LetterJ( null ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            bag.add( new LetterX( null ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            bag.add( new LetterQ( null ) );
        }
        for ( int i = 0; i < 1; i++ )
        {
            bag.add( new LetterZ( null ) );
        }
    }


    /**
     * Returns how many letters are remaining in the bag, used to display the
     * amount in the message in PlayerControl.
     * 
     * @return the size of the bag
     */
    public int remaining()
    {
        return bag.size();
    }

}
