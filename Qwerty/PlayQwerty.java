package Qwerty;

/**
 * Class that runs the Qwerty game.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class PlayQwerty
{
    /**
     * The main method simply calls and sets a MainMenu object to true, and the
     * game begins from there.
     * 
     * @param args
     */
    public static void main( String[] args )
    {
        new MainMenu().setVisible( true );
    }
}
