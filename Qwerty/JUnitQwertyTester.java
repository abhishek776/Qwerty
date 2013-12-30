package Qwerty;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.grid.Location;

import org.junit.*;

import static org.junit.Assert.*;


public class JUnitQwertyTester
{
    // fields.
    private String name = "asdfjkl;";

    // private Window window = new Window(0,0,100,200,"A","B","C");
    private ScrabbleBoard board = new ScrabbleBoard( 0, 0, name );

    private PlayerControl pc = new PlayerControl( board, name );

    private Dictionary dic = new Dictionary();

    private LetterBag bag = new LetterBag();

    private Letter letterA = new LetterA( pc );

    private Letter letterB = new LetterB( pc );

    private Letter letterC = new LetterC( pc );

    private AIControl ai = new AIControlEasy( dic, board );

    private PowerUp p = new PowerUp( 1, new Location( 0, 0 ) );


    /**
     * testing Dictionary
     * 
     * Used fields: Dictionary dic
     */

    @Test
    public void DictionaryConstructor()
    {
        Dictionary dictionary = new Dictionary();
        assertTrue( dictionary.getDictionary().equals( dic.getDictionary() ) );
    }


    @Test
    public void DictionaryIsWord()
    {
        assertTrue( dic.isWord( "toad" ) );
        assertFalse( dic.isWord( "asdfjkl;" ) );
    }


    @Test
    public void DictionaryChooseRandomWord()
    {
        String[] word = { "h", "a", "t" };
        assertNotNull( dic.chooseRandomWord( word ) );
    }


    @Test
    public void DictionaryGetFirstWord()
    {
        ArrayList<Letter> word = new ArrayList<Letter>( 8 );
        word.add( new LetterA( null ) );
        word.add( new LetterA( null ) );
        word.add( new LetterR( null ) );
        word.add( new LetterD( null ) );
        word.add( new LetterV( null ) );
        word.add( new LetterA( null ) );
        word.add( new LetterR( null ) );
        word.add( new LetterK( null ) );

        assertEquals( dic.getWordFirst( word ).length(), 8 );

    }


    @Test
    public void DictionaryChooseLongest()
    {
        String[] word = { "a", "b", "a", "s", "e", "m", "e", "n", "t" };

        assertEquals( dic.chooseLongestRandomWord( word ).length(), 9 );
        assertEquals( dic.chooseLongestRandomWord( word, 8 ).length(), 8 );
    }


    /**
     * Window test.
     * 
     * Used fields: board, pc
     */

    @Test
    public void WindowMakeAllRedNullTest()
    {
        // TESTED IN THE PC Step.
        pc.send( letterA, new Location( 7, 7 ) );
        assertTrue( letterA.getColor().equals( Color.RED ) );
        board.makeAllRedNull();
        assertNull( board.getGrid().get( new Location( 7, 7 ) ).getColor() );
    }


    @Test
    public void WindowRemoveAllRedTest()
    {
        pc.send( letterA, new Location( 7, 7 ) );
        assertTrue( letterA.getColor().equals( Color.RED ) );
        board.removeAllRed();
        assertFalse( board.getGrid().get( new Location( 7, 7 ) ) instanceof Letter );
    }


    @Test
    public void WindowHasRedTest()
    {
        assertFalse( board.hasRed() );
        pc.send( letterA, new Location( 7, 7 ) );
        assertTrue( board.hasRed() );
    }


    /**
     * ScrabbleBoard test.
     * 
     * Used fields: ScrabbleBoard board = new ScrabbleBoard(0,0,name);
     */

    @Test
    public void ScrabbleBoardConstructor()
    {
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof PowerUp );
        assertTrue( board.getAI() instanceof AIControlEasy );
        assertNotNull( board.getPlayer() );
    }


    @Test
    public void ScrabbleBoardPUpSetUp()
    {
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof StarWord );
        assertTrue( board.getGrid().get( new Location( 6, 6 ) ) instanceof DoubleWord );
        assertTrue( board.getGrid().get( new Location( 0, 0 ) ) instanceof TripleWord );
    }


    @Test
    public void ScrabbleBoardPowerUpRestore()
    {
        LetterA a = new LetterA( pc );
        a.putSelfInGrid( board.getGrid(), new Location( 7, 7 ) );
        a.removeSelfFromGrid();
        board.restorePowerUps();
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof StarWord );
        a.putSelfInGrid( board.getGrid(), new Location( 6, 6 ) );
        a.removeSelfFromGrid();
        board.restorePowerUps();
        assertTrue( board.getGrid().get( new Location( 6, 6 ) ) instanceof DoubleWord );
        a.putSelfInGrid( board.getGrid(), new Location( 0, 0 ) );
        a.removeSelfFromGrid();
        board.restorePowerUps();
        assertTrue( board.getGrid().get( new Location( 0, 0 ) ) instanceof TripleWord );

    }


    @Test
    public void ScrabbleBoardDrawLetterTest()
    {
        int size = board.lettersRemainingInBag();
        if ( size > 0 )
        {
            assertNotNull( board.drawLetter() );
        }
        else
        {
            assertNull( board.drawLetter() );
        }
    }


    @Test
    public void ScrabbleBoardGetScoreTest()
    {
        pc.send( letterA, new Location( 0, 0 ) );
        pc.send( letterB, new Location( 0, 1 ) );
        pc.send( letterC, new Location( 0, 2 ) );

        assertEquals( 18, board.getScore() );

    }


    /**
     * There is an occasional null-pointer exception on this test on the GUI but
     * the test is still successful.
     */
    @Test
    public void ScrabbleBoardBoardIsValidTest()
    {
        pc.send( letterB, new Location( 7, 7 ) );
        pc.send( letterC, new Location( 7, 8 ) );
        assertFalse( board.boardIsValidAI() );
        pc.stop();

        pc.send( letterA, new Location( 7, 7 ) );
        pc.send( letterB, new Location( 7, 8 ) );
        pc.send( letterC, new Location( 7, 6 ) );
        assertTrue( board.boardIsValidAI() );

        pc.send( new LetterA( pc ), new Location( 1, 2 ) );
        assertFalse( board.boardIsValidAI() );

        pc.stop();

        pc.send( letterA, new Location( 0, 0 ) );
        assertFalse( board.boardIsValidAI() );
    }


    @Test
    public void ScrabbleBoardStepTest()
    {
        pc.send( letterA, new Location( 7, 7 ) );
        pc.send( letterB, new Location( 7, 8 ) );
        pc.send( letterC, new Location( 7, 6 ) );
        pc.step();

        board.step();

        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof PowerUp );
    }


    /**
     * PowerUp Tester
     * 
     * Used fields: PowerUp p = new PowerUp(1, new Location(0,0));
     */

    @Test
    public void PowerUpConstructorTest()
    {
        assertEquals( p.getMult(), 1 );
        assertEquals( p.getLoc(), new Location( 0, 0 ) );
    }


    /**
     * PlayerControl Tester
     * 
     * Used fields: ScrabbleBoard board = new ScrabbleBoard(0,0,name);
     * PlayerControl pc = new PlayerControl(board, name); Letter letterA = new
     * LetterA( pc ); Letter letterB = new LetterB( pc ); Letter letterC = new
     * LetterC( pc );
     */

    @Test
    public void PCAddScoreTest()
    {
        int score = pc.getScore();
        pc.addScore( 1 );
        assertEquals( score + 1, pc.getScore() );
    }


    @Test
    public void PCSendTest()
    {
        pc.send( letterA, new Location( -1, -1 ) ); // should return.
        assertNull( letterA.getGrid() );

        pc.send( letterB, new Location( 7, 7 ) );
        pc.send( letterA, new Location( 7, 7 ) );
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof LetterB );
        assertNull( letterA.getGrid() );
        board.getGrid().get( new Location( 7, 7 ) ).removeSelfFromGrid();

        pc.send( letterA, new Location( 7, 7 ) );
        assertTrue( letterA.getColor().equals( Color.red ) );
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof LetterA );
    }


    @Test
    public void PCStepTest()
    {
        pc.send( letterB, new Location( 7, 7 ) );
        pc.send( letterC, new Location( 7, 8 ) );
        pc.step();
        assertTrue( !( board.getGrid().get( new Location( 7, 7 ) ) instanceof LetterB ) );

        pc.send( letterA, new Location( 7, 7 ) );
        pc.send( letterB, new Location( 7, 8 ) );
        pc.send( letterC, new Location( 7, 6 ) );
        pc.step();
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ) instanceof LetterA );
        assertTrue( board.getGrid().get( new Location( 7, 7 ) ).getColor() == null );
    }


    @Test
    public void PCRunTest()
    {

        Location loc = new Location( 0, 0 );
        Letter letter = (Letter)pc.getGrid().get( loc );

        pc.setClicked();
        pc.run();
        assertTrue( letter == pc.getGrid().get( loc ) );

        pc.setClicked( loc );
        pc.run();

        assertTrue( letter != pc.getGrid().get( loc ) );
    }


    @Test
    public void PCStopTest()
    {
        pc.send( letterA, new Location( 0, 0 ) );
        pc.stop();
        assertFalse( board.getGrid().get( new Location( 0, 0 ) ) instanceof Letter );
        assertNull( letterA.getGrid() );
    }


    @Test
    public void PCLocationClickedTest()
    {
        pc.locationClicked( new Location( 0, 0 ) );
        assertEquals( pc.getClicked(), new Location( 0, 0 ) );
        assertTrue( pc.locationClicked( new Location( 0, 0 ) ) );
    }


    @Test
    public void PCFinalizeScoreTest()
    {
        int score = pc.getScore();
        ArrayList<Location> list = pc.getGrid().getOccupiedLocations();

        for ( Location loc : list )
        {
            score -= ( (Letter)( pc.getGrid().get( loc ) ) ).getScore();
        }
        // this is a different method than the one inside the code.

        assertEquals( pc.finalizeScore(), score );
    }


    /**
     * Letter class tester
     * 
     * Used fields: letterA, pc
     */

    @Test
    public void LetterConstructor()
    {
        Letter l = new Letter( "A", 1, pc );
        assertEquals( l.getPlayer(), pc );
        assertEquals( l.getLetter(), "A" );
        assertEquals( l.getScore(), 1 );
    }


    @Test
    public void LetterSetWorld()
    {
        Letter l = new Letter( "A", 1, ai );
        assertEquals( l.getPlayer(), ai );
        l.setWorld( pc );
        assertEquals( l.getPlayer(), pc );
    }


    @Test
    public void LetterAct()
    {
        Letter let = letterA;
        let.act();
        assertEquals( let, letterA );
    }


    @Test
    public void LetterSendTest()
    {
        letterA.send( new Location( 1, 1 ) );
        assertTrue( board.getGrid().get( new Location( 1, 1 ) ) instanceof LetterA );
        assertTrue( board.getGrid()
            .get( new Location( 1, 1 ) )
            .getColor()
            .equals( Color.RED ) );
    }


    @Test
    public void LetterCountAdjLocs()
    {
        pc.send( letterA, new Location( 7, 7 ) );
        assertEquals( ( (Letter)board.getGrid().get( new Location( 7, 7 ) ) ).countAdjLoc(),
            0 );
        pc.send( letterB, new Location( 7, 8 ) );
        assertEquals( ( (Letter)board.getGrid().get( new Location( 7, 7 ) ) ).countAdjLoc(),
            1 );
        pc.send( letterC, new Location( 7, 6 ) );
        assertEquals( ( (Letter)board.getGrid().get( new Location( 7, 7 ) ) ).countAdjLoc(),
            2 );

        pc.stop();
    }


    @Test
    public void LetterTileNullTest()
    {
        pc.send( letterC, new Location( 7, 6 ) );
        assertFalse( ( (Letter)board.getGrid().get( new Location( 7, 6 ) ) ).checkForExistingTileNull() );
        pc.stop();

        board.add( new Location( 7, 7 ), new LetterA( pc ) );
        pc.send( letterC, new Location( 7, 6 ) );
        assertTrue( ( (Letter)board.getGrid().get( new Location( 7, 6 ) ) ).checkForExistingTileNull() );
    }


    @Test
    public void LetterEqualsTest()
    {
        assertTrue( letterA.equals( new Letter( "A", 1, pc ) ) );
    }


    @Test
    public void LetterToString()
    {
        assertEquals( letterA.toString(), "A" );
    }


    /**
     * LetterBag tester.
     * 
     * Used fields: bag, letterA
     */

    @Test
    public void LetterBagConstructorTest()
    {
        assertEquals( bag.remaining(), 98 );
    }


    @Test
    public void LetterBagDrawLettersTest()
    {
        while ( bag.remaining() >= 1 )
        {
            assertNotNull( bag.drawLetter() );
        }
        assertNull( bag.drawLetter() );
    }


    @Test
    public void LetterBagAddToBagTest()
    {
        int size = bag.remaining();
        bag.addToBag( letterA );
        assertEquals( size + 1, bag.remaining() );
    }


    /**
     * AIControl Test.
     * 
     * Used fields: ai
     */

    @Test
    public void AIControlConstructorTest()
    {
        assertTrue( ai.getBoard().equals( board ) );
    }


    @Test
    public void AIControlSelectLocations()
    {
        pc.send( letterA, new Location( 7, 7 ) );
        pc.send( letterB, new Location( 7, 8 ) );
        pc.send( letterC, new Location( 7, 6 ) );
        board.makeAllRedNull();
        ArrayList<Letter> letlist = new ArrayList<Letter>();
        letlist.add( (Letter)board.getGrid().get( new Location( 7, 7 ) ) );
        letlist.add( (Letter)board.getGrid().get( new Location( 7, 8 ) ) );
        letlist.add( (Letter)board.getGrid().get( new Location( 7, 6 ) ) );
        ArrayList<Location> list = ai.selectLocations( letlist );
        assertTrue( list.size() > 0 );
        assertTrue( board.getGrid().get( list.get( 0 ) ) instanceof Letter );

        AIControl temp = new AIControlMedium( dic, board );
        ArrayList<Location> list2 = temp.selectLocations( letlist );
        assertTrue( list2.size() == 3 );
        assertTrue( board.getGrid().get( list2.get( 0 ) ) instanceof Letter );

    }


    @Test
    public void AIControlAddScoreTest()
    {
        int score = ai.getScore();
        pc.addScore( 1 );
        assertEquals( score + 1, pc.getScore() );
    }


    @Test
    public void AIDrawLettersTest()
    {
        assertTrue( ai.letList.size() == 8 );
    }


    @Test
    public void AIControlExchangeTest()
    {
        ArrayList<Letter> letters = new ArrayList<Letter>();
        for ( Letter let : ai.letList )
        {
            letters.add( let );
        }

        ai.exchange();
        ai.exchange();
        ai.exchange();
        // exchange 3 times in hopes that at least 1 is not exchanging the same
        // letter.
        boolean bool = false;
        for ( Letter let : ai.letList )
        {
            for ( Letter letter : letters )
            {
                if ( !letter.equals( let ) )
                {
                    bool = true;
                }
            }
        }

        assertTrue( bool );
    }

    /**
     * Tests all the methods that helps the AI play a turn.
     */
    @Test
    public void AIControlPlayTurnPlaceWordGetWordFitsResetLetters()
    {
        board.step();
        pc.send(letterA, new Location(7,8));
        pc.send(letterB, new Location(7,9));
        pc.send(letterC, new Location(7,7));

        pc.step();
        assertTrue(board.getGrid().get( new Location(8,7) ) instanceof Letter ||
            board.getGrid().get( new Location(8,8) ) instanceof Letter ||
            board.getGrid().get( new Location(8,9) ) instanceof Letter ||
            board.getGrid().get( new Location(6,7) ) instanceof Letter ||
            board.getGrid().get( new Location(6,8) ) instanceof Letter ||
            board.getGrid().get( new Location(6,9) ) instanceof Letter);

    }

}
