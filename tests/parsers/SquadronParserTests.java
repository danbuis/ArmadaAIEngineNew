package parsers;

import engine.parsers.ParsingException;
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
    public void testParseBasicFunctionality() throws FileNotFoundException, ParsingException {
        Squadrons testParser = new Squadrons();

        Squadron arc = testParser.getSquadron("ARC-170 Starfighter");
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

    @Test
    public void testSquadsActuallyCopies() throws FileNotFoundException, ParsingException {
        Squadrons testParser = new Squadrons();
        Squadron tie1 = testParser.getSquadron("TIE Fighter");
        Squadron tie2 = testParser.getSquadron("TIE Fighter");

        assertNotNull(tie1);
        assertNotNull(tie2);

        //checks the memory locations
        assertNotEquals(tie1, tie2);
    }

    @Test
    public void testBadUnique() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new Squadrons("assets/data/test/squadrons_bad_unique.txt"));
        String expectedMessage = "Illegal unique value : Maybe.  Legal values are Y and N";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBadFaction() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new Squadrons("assets/data/test/squadrons_bad_faction.txt"));
        String expectedMessage = "Illegal faction : Weasel.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBadHull() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new Squadrons("assets/data/test/squadrons_bad_hull.txt"));
        String expectedMessage = "Non integer value found for Hull : Guac.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNegativeHull() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new Squadrons("assets/data/test/squadrons_bad_hull_negative.txt"));
        String expectedMessage = "Illegal integer value found for Hull : -1.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


}
