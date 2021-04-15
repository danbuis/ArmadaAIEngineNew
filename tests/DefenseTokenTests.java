import gameComponents.DefenseTokens.DefenseToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefenseTokenTests {

    @Test
    public void testConstructor(){
        DefenseToken evade = new DefenseToken(DefenseToken.defenseTokenType.EVADE);
        assertEquals(DefenseToken.defenseTokenType.EVADE, evade.getType());
        assertEquals(DefenseToken.defenseTokenStatus.READY, evade.getStatus());

        DefenseToken brace = new DefenseToken(DefenseToken.defenseTokenType.BRACE);
        assertEquals(DefenseToken.defenseTokenType.BRACE, brace.getType());
    }

    @Test
    public void testExhaust(){
        DefenseToken evade = new DefenseToken(DefenseToken.defenseTokenType.EVADE);

        assertTrue(evade.exhaust());
        assertEquals(DefenseToken.defenseTokenStatus.EXHAUSTED, evade.getStatus());

        assertFalse(evade.exhaust());
        assertEquals(DefenseToken.defenseTokenStatus.EXHAUSTED, evade.getStatus());
    }

    @Test
    public void testDiscard(){
        DefenseToken evade = new DefenseToken(DefenseToken.defenseTokenType.EVADE);

        assertTrue(evade.discard());
        assertEquals(DefenseToken.defenseTokenStatus.DISCARDED, evade.getStatus());

        assertFalse(evade.discard());
        assertEquals(DefenseToken.defenseTokenStatus.DISCARDED, evade.getStatus());
    }

    @Test
    public void testSpend(){
        DefenseToken evade = new DefenseToken(DefenseToken.defenseTokenType.EVADE);

        assertTrue(evade.spend());
        assertEquals(DefenseToken.defenseTokenStatus.EXHAUSTED, evade.getStatus());

        assertTrue(evade.spend());
        assertEquals(DefenseToken.defenseTokenStatus.DISCARDED, evade.getStatus());

        assertFalse(evade.spend());
        assertEquals(DefenseToken.defenseTokenStatus.DISCARDED, evade.getStatus());
    }

    @Test
    public void testReady(){
        DefenseToken evade = new DefenseToken(DefenseToken.defenseTokenType.EVADE);

        assertFalse(evade.ready());
        assertEquals(DefenseToken.defenseTokenStatus.READY, evade.getStatus());

        evade.exhaust();
        assertTrue(evade.ready());
        assertEquals(DefenseToken.defenseTokenStatus.READY, evade.getStatus());

        evade.discard();
        assertFalse(evade.ready());
        assertEquals(DefenseToken.defenseTokenStatus.DISCARDED, evade.getStatus());
    }

    @Test
    public void testRecover(){
        DefenseToken evade = new DefenseToken(DefenseToken.defenseTokenType.EVADE);

        assertFalse(evade.recover());
        assertEquals(DefenseToken.defenseTokenStatus.READY, evade.getStatus());

        evade.exhaust();
        assertFalse(evade.recover());
        assertEquals(DefenseToken.defenseTokenStatus.EXHAUSTED, evade.getStatus());

        evade.discard();
        assertTrue(evade.recover());
        assertEquals(DefenseToken.defenseTokenStatus.READY, evade.getStatus());
    }

}
