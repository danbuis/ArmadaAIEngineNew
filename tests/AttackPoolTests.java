import BBDGameLibrary.GameEngine.Die;
import gameComponents.AttackPool;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AttackPoolTests {

    @Test
    public void testDiceGathering(){
        ArrayList<Die> blackDie = AttackPool.getDice("black", 1);
        assertEquals(1, blackDie.size());

        //die should not yet be rolled, just collected
        assertTrue(blackDie.get(0).isUnrolled());

        ArrayList<Die> redDie = AttackPool.getDice("red", 17);
        assertEquals(17, redDie.size());
    }

    @Test
    public void testBuildAttackPool(){
        ArrayList<Die> blackDice = AttackPool.getDice("black", 3);
        ArrayList<Die> blueDice = AttackPool.getDice("blue", 2);
        ArrayList<Die> redDice = AttackPool.getDice("red", 3);

        ArrayList<Die> combined = new ArrayList<>();
        combined.addAll(blackDice);
        combined.addAll(blueDice);
        combined.addAll(redDice);
        AttackPool isd1Front = new AttackPool(combined);
        assertEquals(8, isd1Front.getCurrentPool().size());

        //should be rolled as part of the constructor
        assertFalse(isd1Front.getCurrentPool().get(0).isUnrolled());
    }

    @Test
    public void testGetCurrentPoolNewObject(){
        //this is important so that we don't accidentally modify the pool outside of the object
    }

    @Test
    public void testSetFace(){

    }

    @Test
    public void testDamageTotaling(){

    }

    @Test
    public void testRerolling(){

    }

    @Test
    public void testCanceling(){

    }

    @Test public void testAddDice(){

    }
}


