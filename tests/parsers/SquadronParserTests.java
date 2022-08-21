package parsers;

import components.squadrons.Squadron;
import components.tokens.DefenseToken;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class SquadronParserTests {

    /**
     * Testing if the file parses correctly, and then if a squadron is built correctly
     * @throws FileNotFoundException
     */
    @Test
    public void testParseBasicFunctionality() throws IOException, ParseException {
        Squadron v19 = new Squadron("V-19_Torrent");
        assertNotNull(v19);
        assertFalse(v19.isUnique());
        assertEquals("V-19 Torrent", v19.getName());
        assertEquals("V-19 Torrent", v19.getType());
        assertEquals(5, v19.getCurrentHealth());
        assertEquals(5, v19.getFullHealth());
        assertEquals(3, v19.getMaxSpeed());
        assertEquals("K", v19.getAntiShipDice());
        assertEquals("RBB", v19.getAntiSquadronDice());
        assertTrue(v19.getKeywords().contains("Escort"));
        assertFalse(v19.getKeywords().contains("Counter1"));
        assertTrue(v19.getKeywords().contains("Swarm"));
        assertEquals(12, v19.getPointsValue());
        assertEquals(0, v19.getDefenseTokens().size());
    }

    @Test
    public void testSquadsActuallyCopies() throws IOException, ParseException {
        Squadron tie1 = new Squadron("TIE_Fighter");
        Squadron tie2 = new Squadron("TIE_Fighter");

        assertNotNull(tie1);
        assertNotNull(tie2);

        //checks the memory locations
        assertNotEquals(tie1, tie2);
    }

    @Test
    public void testDefenseTokenParse() throws IOException, ParseException {
        Squadron howl = new Squadron("Howlrunner");
        assertEquals(2, howl.getDefenseTokens().size());

        assertEquals(DefenseToken.Type.BRACE, howl.getDefenseTokens().get(0).getType());
        assertEquals(DefenseToken.Type.SCATTER, howl.getDefenseTokens().get(1).getType());

    }
}
