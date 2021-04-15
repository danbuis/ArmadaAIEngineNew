import engine.GameConstants;
import gameComponents.DefenseTokens.DefenseToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefenseTokenTests {

    @Test
    public void testEnumConstructor(){
        DefenseToken evade = new DefenseToken(GameConstants.defenseTokenType.EVADE);
        assertEquals(GameConstants.defenseTokenType.EVADE, evade.getType());
        assertEquals(GameConstants.defenseTokenStatus.READY, evade.getStatus());

        DefenseToken brace = new DefenseToken(GameConstants.defenseTokenType.BRACE);
        assertEquals(GameConstants.defenseTokenType.BRACE, brace.getType());
    }

    @Test
    public void testStrConstructor(){
        DefenseToken evade = new DefenseToken("Ev");
        assertEquals(GameConstants.defenseTokenType.EVADE, evade.getType());

        DefenseToken scatter = new DefenseToken("Sc");
        assertEquals(GameConstants.defenseTokenType.SCATTER, scatter.getType());

        DefenseToken brace = new DefenseToken("Br");
        assertEquals(GameConstants.defenseTokenType.BRACE, brace.getType());

        DefenseToken contain = new DefenseToken("Cn");
        assertEquals(GameConstants.defenseTokenType.CONTAIN, contain.getType());

        DefenseToken salvo = new DefenseToken("Sal");
        assertEquals(GameConstants.defenseTokenType.SALVO, salvo.getType());

        DefenseToken redirect = new DefenseToken("Rd");
        assertEquals(GameConstants.defenseTokenType.REDIRECT, redirect.getType());

        DefenseToken derp = new DefenseToken("R0sf.KLVDd");
        assertNull(derp.getType());
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
