import BBDGameLibrary.GameEngine.Die;
import gameComponents.AttackPool;
import gameComponents.DiceFacings;
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
        ArrayList<Die> combined = AttackPool.getDice(3,2,3);

        AttackPool isd1Front = new AttackPool(combined);
        assertEquals(8, isd1Front.getPoolSize());

        //should be rolled as part of the constructor
        assertNotNull(isd1Front.getCurrentDiceFacings()[0]);
    }

    @Test
    public void testSetFace(){
        ArrayList<Die> combined = AttackPool.getDice(3,2,3);
        AttackPool isd1Front = new AttackPool(combined);
        isd1Front.setFace(0, DiceFacings.BLANK);
        //assertEquals(DiceFacings.BLANK, isd1Front.getCurrentDiceFacings()[0]);
        isd1Front.setFace(0, DiceFacings.HIT_CRIT);
        assertEquals(DiceFacings.HIT_CRIT, isd1Front.getCurrentDiceFacings()[0]);
    }

    @Test
    public void testSetFaceInvalid(){

    }


    @Test
    public void testDamageTotaling(){
        ArrayList<Die> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);
        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);

        assertEquals(4, test.getCurrentRolledDamage());

        test.setFace(0, DiceFacings.BLANK);
        assertEquals(2, test.getCurrentRolledDamage());

        test.setFace(1, DiceFacings.ACCURACY);
        assertEquals(0, test.getCurrentRolledDamage());

        test.setFace(0, DiceFacings.HIT);
        assertEquals(1, test.getCurrentRolledDamage());

        test.setFace(1, DiceFacings.CRIT);
        assertEquals(2, test.getCurrentRolledDamage());
    }

    @Test
    public void testRerolling(){
        ArrayList<Die> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);
        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);

        //loop in case we reroll into a double hit.  For this to fail naturally is (0.125)^20, or 1/10^18, which is basically 0
        for (int i=0; i<20; i++){
            test.rerollDie(0);
            if(test.getCurrentRolledDamage()!=4){
                break;
            }
        }

        assertNotEquals(4, test.getCurrentRolledDamage());
        assertEquals(DiceFacings.DOUBLE_HIT, test.getCurrentDiceFacings()[1]);

        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);
    }

    @Test
    public void testCanceling(){
        ArrayList<Die> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);
        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);

        assertEquals(4, test.getCurrentRolledDamage());
        test.cancelDie(0);
        assertEquals(2, test.getCurrentRolledDamage());
        assertEquals(1, test.getPoolSize());
    }

    @Test
    public void testCancellingToEmpty(){
        ArrayList<Die> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);
        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);

        assertEquals(4, test.getCurrentRolledDamage());
        test.cancelDie(0);
        assertEquals(2, test.getCurrentRolledDamage());
        test.cancelDie(0);
        assertEquals(0, test.getCurrentRolledDamage());
        assertEquals(0, test.getPoolSize());
    }

    @Test
    public void testAddDice(){
        ArrayList<Die> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);

        assertEquals(2, test.getPoolSize());
        test.addDie("red");
        assertEquals(3, test.getPoolSize());
        assertNotNull(test.getCurrentDiceFacings()[2]);
    }

    @Test
    public void testUpdatePool(){

    }

    @Test
    public void testCritEffect(){

    }

    @Test
    public void testNonLowerColorText(){

    }
}


