package Qwerty;

/**
 * Represents an interface for polymorphism for AI and PlayerControl because the
 * both represent Players in the game.
 * 
 * @author Abhishek, Anish, Nikhil
 * 
 */
public interface Player
{
    /**
     * The finalize score method which both AI and PlayerControl must have.
     * 
     * @return
     */
    public int finalizeScore();
}
