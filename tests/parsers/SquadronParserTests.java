package parsers;

import engine.GameConstants;
import engine.parsers.ParsingException;
import engine.parsers.SquadronFactory;
import gameComponents.DefenseTokens.DefenseToken;
import gameComponents.Squadrons.Squadron;
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
        SquadronFactory testParser = new SquadronFactory();

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
        SquadronFactory testParser = new SquadronFactory();
        Squadron tie1 = testParser.getSquadron("TIE Fighter");
        Squadron tie2 = testParser.getSquadron("TIE Fighter");

        assertNotNull(tie1);
        assertNotNull(tie2);

        //checks the memory locations
        assertNotEquals(tie1, tie2);
    }

    @Test
    public void testDefenseTokenParse() throws FileNotFoundException, ParsingException {
        SquadronFactory testParser = new SquadronFactory();
        Squadron howl = testParser.getSquadron("\"Howlrunner\"");
        assertEquals(2, howl.getDefenseTokens().size());

        assertEquals(DefenseToken.Type.BRACE, howl.getDefenseTokens().get(0).getType());
        assertEquals(DefenseToken.Type.SCATTER, howl.getDefenseTokens().get(1).getType());

    }

    @Test
    public void testBadDefenseTokenParse() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_def_token.txt"));
        String expectedMessage = "Invalid defense token passed in : Derp.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testBadUnique() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_unique.txt"));
        String expectedMessage = "Illegal unique value : Maybe.  Legal values are Y and N";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBadFaction() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_faction.txt"));
        String expectedMessage = "Illegal faction : Weasel.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBadHull() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_hull.txt"));
        String expectedMessage = "Non integer value found for Hull : Guac.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNegativeHull() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_hull_negative.txt"));
        String expectedMessage = "Illegal integer value found for Hull : -1.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBadSpeed() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_speed.txt"));
        String expectedMessage = "Non integer value found for Speed : Poodles.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNegativeSpeed() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_speed_negative.txt"));
        String expectedMessage = "Illegal integer value found for Speed : -1.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBadPoints() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_points.txt"));
        String expectedMessage = "Non integer value found for Points : HarryPotter.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNegativePoints() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_points_negative.txt"));
        String expectedMessage = "Illegal integer value found for Points : -11.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNoPipe() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_row_no_pipe.txt"));
        String expectedMessage = "All rows must have 1 '|' character with content both before and after. : UniqueY.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testExtraPipe() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_row_extra_pipe.txt"));
        String expectedMessage = "All rows must have 1 '|' character with content both before and after. : SquadType||TIE Interceptor.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testEdgePipe() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_row_edge_pipe.txt"));
        String expectedMessage = "All rows must have 1 '|' character with content both before and after. : Unique|.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testPartialSquadron() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_partial.txt"));
        String expectedMessage = "Reached end of file with a partially built object, null, missing: anti-squadron dice fields.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
