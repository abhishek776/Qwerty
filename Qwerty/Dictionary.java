package Qwerty;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


/**
 * Represents the english dictionary, and also helps the AI 
 * find words which it can place on the board.
 * 
 * @author Abhishek, Anish, Nikhil
 *
 */
public class Dictionary
{
    /**
     * Represents the English dictionary.
     */
    private HashSet<String> dictionary;


    /**
     * Constructs The Dictionary with text file: dictionary.txt
     */
    public Dictionary()
    {
        dictionary = new HashSet<String>();
        Scanner scanner = null;
        try
        {
            scanner = new Scanner( new File( "dictionary.txt" ) );
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        while ( scanner.hasNextLine() )
        {
            dictionary.add( scanner.nextLine() );
        }
    }


    /**
     * Tests whether or not the parameter is a valid word.
     * 
     * @param word
     * @return boolean isWord
     */
    public boolean isWord( String word )
    {
        if ( dictionary.contains( word ) )
        {
            return true;
        }
        return false;
    }


    /**
     * Returns the hashset representing dictionary
     * 
     * @return dictionary
     */
    public HashSet<String> getDictionary()
    {
        return dictionary;
    }


    /**
     * Finds a random word that has the letters inside the letList of AI.
     * 
     * @param word
     * @return the random word
     */
    public String chooseRandomWord( String[] word )
    {
        String first = word[0];
        int[] letterNums;
        letterNums = constructNums( word );

        for ( String s : dictionary )
        {
            if ( s.length() > 9 || !s.contains( first ) )
            {
                continue;
            }

            int[] temp = letterNums.clone();

            boolean bool = true;

            for ( int i = 0; i < s.length(); i++ )
            {
                char let = s.charAt( i );
                int val = Character.getNumericValue( let )
                    - Character.getNumericValue( 'a' );
                temp[val]--;

                if ( temp[val] < 0 )
                {
                    bool = false;
                    break;
                }
            }

            if ( bool )
            {
                return s;
            }
        }

        return null;

    }


    /**
     * Helper method for chooseRandomWord which makes an array telling how many
     * of each type of letter the AI has.
     * 
     * @param word
     * @return
     */
    private int[] constructNums( String[] word )
    {
        int[] letterNums = new int[26];

        for ( String s : word )
        {
            int charValue = Character.getNumericValue( s.charAt( 0 ) )
                - Character.getNumericValue( 'a' );
            letterNums[charValue]++;
        }

        return letterNums;
    }


    /**
     * Gets the longest word possible for the AI, if the AI is going first with
     * its 8 letters.
     * 
     * @param letList
     * @return
     */
    public String getWordFirst( ArrayList<Letter> letList )
    {
        int[] letterNums = new int[26];
        String[] word = new String[8];
        int i = 0;
        for ( Letter let : letList )
        {

            word[i] = let.getLetter();
            i++;
        }
        letterNums = constructNums( word );
        String max = null;

        for ( String s : dictionary )
        {
            if ( s.length() > 8 )
            {
                continue;
            }

            int[] temp = letterNums.clone();

            boolean bool = true;

            for ( i = 0; i < s.length(); i++ )
            {
                char let = s.charAt( i );
                int val = Character.getNumericValue( let )
                    - Character.getNumericValue( 'a' );
                temp[val]--;

                if ( temp[val] < 0 )
                {
                    bool = false;
                    break;
                }
            }

            if ( bool )
            {
                if(s.length() == 8)
                {
                    return s;
                }
                if ( max == null || s.length() > max.length() )
                {
                    max = s;
                }
            }
        }

        return max;
    }


    /**
     * Picks the longest word out of String[] word array, from the dictionary,
     * for the first turn on the board if the AI plays that turn.
     * 
     * @param word
     * @return
     */
    public String chooseLongestRandomWord( String[] word )
    {
        return chooseLongestRandomWord( word, 9 );
    }


    /**
     * It picks the first word that comes up from the dictionary from the array
     * of letters in word, and keeps searching until it finds a word of length,
     * it picks the next longest word.
     * 
     * @param word
     * @param length
     * @return
     */
    public String chooseLongestRandomWord( String[] word, int length )
    {
        String first = word[0];

        int[] letterNums;
        letterNums = constructNums( word );
        String max = null;

        for ( String s : dictionary )
        {
            if ( s.length() > length || !s.contains( first ) || (max != null && s.length() <= max.length()))
            {
                continue;
            }

            int[] temp = letterNums.clone();

            boolean bool = true;

            for ( int i = 0; i < s.length(); i++ )
            {
                char let = s.charAt( i );
                int val = Character.getNumericValue( let )
                    - Character.getNumericValue( 'a' );
                temp[val]--;

                if ( temp[val] < 0 )
                {
                    bool = false;
                    break;
                }
            }

            if ( bool && s.length() == length )
            {
                return s;
            }

            if ( bool && ( max == null || max.length() < s.length() ) )
            {
                max = s;
            }
        }
        return max;
    }
}