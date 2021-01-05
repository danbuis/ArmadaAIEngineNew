package parsers;

import engine.parsers.Squadrons;
import gameComponents.Squadron;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class SquadronParserTests {

    /**
     * Testing if the file parses correctly, and then if a squadron is built correctly
     * @throws FileNotFoundException
     */
    @Test
    public void testParseBasicFunctionality() throws FileNotFoundException {
        Squadrons testParser = new Squadrons();

        Squadron arc = testParser.squadronMap.get("ARC-170 Starfighter");
        assertNotNull(arc);
        assertFalse(arc.isUnique());
        assertEquals("ARC-170 Starfighter", arc.getName());
        assertEquals("ARC-170 Starfighter", arc.getType());
        assertEquals(7, arc.getCurrentHealth());
        assertEquals(7, arc.getFullHealth());
        assertEquals(2, arc.getMaxSpeed());
        assertEquals("BB", arc.getAntiShipDice());
        assertEquals("RBB", arc.getAntiSquadronDice());
        assertTrue(arc.getKeywords().contains("Bomber"));
        assertTrue(arc.getKeywords().contains("Counter1"));
        assertFalse(arc.getKeywords().contains("Swarm"));
        assertEquals(15, arc.getPointsValue());
        assertEquals(0, arc.getDefenseTokens().size());
    }


}
