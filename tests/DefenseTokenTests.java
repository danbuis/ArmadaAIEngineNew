import components.tokens.DefenseToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefenseTokenTests {

    @Test
    public void testConstructor(){
        DefenseToken evade = new DefenseToken(DefenseToken.Type.EVADE);
        assertEquals(DefenseToken.Type.EVADE, evade.getType());
        assertEquals(DefenseToken.Status.READY, evade.getStatus());

        DefenseToken brace = new DefenseToken(DefenseToken.Type.BRACE);
        assertEquals(DefenseToken.Type.BRACE, brace.getType());
    }

    @Test
    public void testStrConstructor(){
        DefenseToken evade = new DefenseToken("Evade");
        assertEquals(DefenseToken.Type.EVADE, evade.getType());

        DefenseToken scatter = new DefenseToken("Scatter");
        assertEquals(DefenseToken.Type.SCATTER, scatter.getType());

        DefenseToken brace = new DefenseToken("Brace");
        assertEquals(DefenseToken.Type.BRACE, brace.getType());

        DefenseToken contain = new DefenseToken("Contain");
        assertEquals(DefenseToken.Type.CONTAIN, contain.getType());

        DefenseToken salvo = new DefenseToken("Salvo");
        assertEquals(DefenseToken.Type.SALVO, salvo.getType());

        DefenseToken redirect = new DefenseToken("Redirect");
        assertEquals(DefenseToken.Type.REDIRECT, redirect.getType());

        DefenseToken derp = new DefenseToken("R0sf.KLVDd");
        assertNull(derp.getType());
    }

    @Test
    public void testExhaust(){
        DefenseToken evade = new DefenseToken(DefenseToken.Type.EVADE);

        assertTrue(evade.exhaust());
        assertEquals(DefenseToken.Status.EXHAUSTED, evade.getStatus());

        assertFalse(evade.exhaust());
        assertEquals(DefenseToken.Status.EXHAUSTED, evade.getStatus());
    }

    @Test
    public void testDiscard(){
        DefenseToken evade = new DefenseToken(DefenseToken.Type.EVADE);

        assertTrue(evade.discard());
        assertEquals(DefenseToken.Status.DISCARDED, evade.getStatus());

        assertFalse(evade.discard());
        assertEquals(DefenseToken.Status.DISCARDED, evade.getStatus());
    }

    @Test
    public void testSpend(){
        DefenseToken evade = new DefenseToken(DefenseToken.Type.EVADE);

        assertTrue(evade.spend());
        assertEquals(DefenseToken.Status.EXHAUSTED, evade.getStatus());

        assertTrue(evade.spend());
        assertEquals(DefenseToken.Status.DISCARDED, evade.getStatus());

        assertFalse(evade.spend());
        assertEquals(DefenseToken.Status.DISCARDED, evade.getStatus());
    }

    @Test
    public void testReady(){
        DefenseToken evade = new DefenseToken(DefenseToken.Type.EVADE);

        assertFalse(evade.ready());
        assertEquals(DefenseToken.Status.READY, evade.getStatus());

        evade.exhaust();
        assertTrue(evade.ready());
        assertEquals(DefenseToken.Status.READY, evade.getStatus());

        evade.discard();
        assertFalse(evade.ready());
        assertEquals(DefenseToken.Status.DISCARDED, evade.getStatus());
    }

    @Test
    public void testRecover(){
        DefenseToken evade = new DefenseToken(DefenseToken.Type.EVADE);

        assertFalse(evade.recover());
        assertEquals(DefenseToken.Status.READY, evade.getStatus());

        evade.exhaust();
        assertFalse(evade.recover());
        assertEquals(DefenseToken.Status.EXHAUSTED, evade.getStatus());

        evade.discard();
        assertTrue(evade.recover());
        assertEquals(DefenseToken.Status.READY, evade.getStatus());
    }

}
