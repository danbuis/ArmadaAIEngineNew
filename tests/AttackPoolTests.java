import components.ArmadaDie;
import components.AttackPool;
import components.DiceFacings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AttackPoolTests {

    @Test
    public void testDiceGathering(){
        ArrayList<ArmadaDie> blackDie = AttackPool.getDice(0,0,1);
        assertEquals(1, blackDie.size());

        //die should not yet be rolled, just collected
        assertTrue(blackDie.get(0).isUnrolled());

        ArrayList<ArmadaDie> redDie = AttackPool.getDice(17,0,0);
        assertEquals(17, redDie.size());
    }

    @Test
    public void testBuildAttackPool(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(3,2,3);

        AttackPool isd1Front = new AttackPool(combined);
        assertEquals(8, isd1Front.getPoolSize());

        //should be rolled as part of the constructor
        assertNotNull(isd1Front.getCurrentDiceFacings()[0]);
    }

    @Test
    public void testSetFace(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(3,2,3);
        AttackPool isd1Front = new AttackPool(combined);
        isd1Front.setFace(0, DiceFacings.BLANK);
        assertEquals(DiceFacings.BLANK, isd1Front.getCurrentDiceFacings()[0]);
        isd1Front.setFace(0, DiceFacings.HIT_CRIT);
        assertEquals(DiceFacings.HIT_CRIT, isd1Front.getCurrentDiceFacings()[0]);
    }

    @Test
    public void testSetFaceInvalid(){

    }


    @Test
    public void testDamageTotaling(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
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
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
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
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
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
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
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
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);

        assertEquals(2, test.getPoolSize());
        test.addDie(AttackPool.DiceColor.RED);
        assertEquals(3, test.getPoolSize());
        assertNotNull(test.getCurrentDiceFacings()[2]);
    }

    @Test
    public void testCritEffect(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);
        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);

        assertFalse(test.getCritEffect());

        test.setFace(0, DiceFacings.CRIT);
        assertTrue(test.getCritEffect());
        test.setFace(1, DiceFacings.CRIT);
        assertTrue(test.getCritEffect());

        combined = AttackPool.getDice(0,0,2);
        test = new AttackPool(combined);
        test.setFace(0, DiceFacings.HIT);
        test.setFace(1, DiceFacings.HIT_CRIT);
        assertTrue(test.getCritEffect());
    }

    @Test
    public void countSymbols(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(2,0,0);
        AttackPool test = new AttackPool(combined);
        test.setFace(0, DiceFacings.DOUBLE_HIT);
        test.setFace(1, DiceFacings.DOUBLE_HIT);

        assertEquals(4, test.getSymbolCount(AttackPool.DiceSymbol.HIT));
        assertEquals(0, test.getSymbolCount(AttackPool.DiceSymbol.CRIT));
        assertEquals(0, test.getSymbolCount(AttackPool.DiceSymbol.ACCURACY));

        combined = AttackPool.getDice(0,0,2);
        test = new AttackPool(combined);
        test.setFace(0, DiceFacings.HIT);
        test.setFace(1, DiceFacings.HIT_CRIT);
        assertEquals(2, test.getSymbolCount(AttackPool.DiceSymbol.HIT));
        assertEquals(1, test.getSymbolCount(AttackPool.DiceSymbol.CRIT));
        assertEquals(0, test.getSymbolCount(AttackPool.DiceSymbol.ACCURACY));
    }

    @Test
    public void checkColors(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(1,1,1);
        assertEquals(AttackPool.DiceColor.RED, combined.get(2).getColor());
        assertEquals(AttackPool.DiceColor.BLUE, combined.get(1).getColor());
        assertEquals(AttackPool.DiceColor.BLACK, combined.get(0).getColor());
    }

    @Test
    public void checkCountColors(){
        ArrayList<ArmadaDie> combined = AttackPool.getDice(3,2,1);
        AttackPool pool  = new AttackPool(combined);
        assertEquals(3, pool.countDiceOfColor(AttackPool.DiceColor.RED));
        assertEquals(2, pool.countDiceOfColor(AttackPool.DiceColor.BLUE));
        assertEquals(1, pool.countDiceOfColor(AttackPool.DiceColor.BLACK));

    }
}


