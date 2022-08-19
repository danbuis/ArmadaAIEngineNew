package parsers;

import components.ship.Ship;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class ShipParserTests {

    /**
     * Testing if the file parses correctly, and then if a squadron is built correctly
     * @throws FileNotFoundException
     */
    @Test
    public void testParseBasicFunctionality() throws IOException, ParseException {
        Ship consular = new Ship("Consular_Charger_C70");
        assertNotNull(consular);
        assertEquals("Consular Charger C70", consular.getName());
        assertEquals("Consular Charger", consular.getType());
        assertTrue(consular.getKeywords().contains("Clone"));

    }

    @Test
    public void testShipsActuallyCopies() throws IOException, ParseException {
        Ship consular1 = new Ship("Consular_Charger_C70");
        Ship consular2 = new Ship("Consular_Charger_C70");

        assertNotNull(consular1);
        assertNotNull(consular2);

        //checks the memory locations
        assertNotEquals(consular1, consular2);
    }
}
