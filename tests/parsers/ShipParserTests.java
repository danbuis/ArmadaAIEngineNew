package parsers;

import components.ship.Ship;
import engine.parsers.ParsingException;
import engine.parsers.ShipFactory;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class ShipParserTests {

    /**
     * Testing if the file parses correctly, and then if a squadron is built correctly
     * @throws FileNotFoundException
     */
    @Test
    public void testParseBasicFunctionality() throws FileNotFoundException, ParsingException {
        ShipFactory testParser = new ShipFactory();

        Ship consular = testParser.getShip("Consular Charger C70");
        assertNotNull(consular);
        assertEquals("Consular Charger C70", consular.getName());
        assertEquals("Consular Charger", consular.getType());
        assertTrue(consular.getKeywords().contains("Clone"));

    }

    @Test
    public void testShipsActuallyCopies() throws FileNotFoundException, ParsingException {
        ShipFactory testParser = new ShipFactory();

        Ship consular1 = testParser.getShip("Consular Charger C70");
        Ship consular2 = testParser.getShip("Consular Charger C70");

        assertNotNull(consular1);
        assertNotNull(consular2);

        //checks the memory locations
        assertNotEquals(consular1, consular2);
    }
/**
    @Test
    public void testPartialSquadron() throws FileNotFoundException, ParsingException {
        Exception exception = assertThrows(ParsingException.class, () -> new SquadronFactory("assets/data/test/squadrons_bad_partial.txt"));
        String expectedMessage = "Reached end of file with a partially built object, null, missing: anti-squadron dice fields.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
*/
}
