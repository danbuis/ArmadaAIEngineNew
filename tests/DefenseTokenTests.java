import engine.GameConstants;
import gameComponents.DefenseTokens.DefenseToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefenseTokenTests {

    @Test
    public void testConstructor(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);
        assertEquals(GameConstants.defenseTokenType.EVADE, evade.getType());
        assertEquals(GameConstants.defenseTokenStatus.READY, evade.getStatus());

        DefenseToken brace = new DefenseToken(GameConstants.defenseTokenType.BRACE);
        assertEquals(GameConstants.defenseTokenType.BRACE, brace.getType());
    }

    @Test
    public void testExhaust(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);

        assertTrue(evade.exhaust());
        assertEquals(GameConstants.defenseTokenStatus.EXHAUSTED, evade.getStatus());

        assertFalse(evade.exhaust());
        assertEquals(GameConstants.defenseTokenStatus.EXHAUSTED, evade.getStatus());
    }

    @Test
    public void testDiscard(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);

        assertTrue(evade.discard());
        assertEquals(GameConstants.defenseTokenStatus.DISCARDED, evade.getStatus());

        assertFalse(evade.discard());
        assertEquals(GameConstants.defenseTokenStatus.DISCARDED, evade.getStatus());
    }

    @Test
    public void testSpend(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);

        assertTrue(evade.spend());
        assertEquals(GameConstants.defenseTokenStatus.EXHAUSTED, evade.getStatus());

        assertTrue(evade.spend());
        assertEquals(GameConstants.defenseTokenStatus.DISCARDED, evade.getStatus());

        assertFalse(evade.spend());
        assertEquals(GameConstants.defenseTokenStatus.DISCARDED, evade.getStatus());
    }

    @Test
    public void testReady(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);

        assertFalse(evade.ready());
        assertEquals(GameConstants.defenseTokenStatus.READY, evade.getStatus());

        evade.exhaust();
        assertTrue(evade.ready());
        assertEquals(GameConstants.defenseTokenStatus.READY, evade.getStatus());

        evade.discard();
        assertFalse(evade.ready());
        assertEquals(GameConstants.defenseTokenStatus.DISCARDED, evade.getStatus());
    }

    @Test
    public void testRecover(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);

        assertFalse(evade.recover());
        assertEquals(GameConstants.defenseTokenStatus.READY, evade.getStatus());

        evade.exhaust();
        assertFalse(evade.recover());
        assertEquals(GameConstants.defenseTokenStatus.EXHAUSTED, evade.getStatus());

        evade.discard();
        assertTrue(evade.recover());
        assertEquals(GameConstants.defenseTokenStatus.READY, evade.getStatus());
    }

}
