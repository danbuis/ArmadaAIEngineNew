import BBDGameLibrary.Geometry2d.BBDPoint;
import components.ship.HullZone;
import components.ship.Ship;
import engine.parsers.ParsingException;
import engine.parsers.ShipFactory;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HullZoneTests {
    @Test
    public void testConstruction() throws FileNotFoundException, ParsingException {
        ShipFactory test = new ShipFactory();
        Ship ship = test.getShip("CR90 Corvette A");
        assertEquals(4, ship.getHullZones().size());
    }

    @Test
    public void testBasicAttributes() throws FileNotFoundException, ParsingException {
        ShipFactory test = new ShipFactory();
        Ship ship = test.getShip("CR90 Corvette A");
        HullZone zoneToTest = ship.getHullZones().get(0);
        assertEquals("RRB", zoneToTest.getArmament());
        assertEquals(2, zoneToTest.getRemainingShields());
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(1)));
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(3)));
    }

    @Test
    public void testAdjacencyIsCorrect() throws FileNotFoundException, ParsingException {
        ShipFactory test = new ShipFactory();
        Ship ship = test.getShip("CR90 Corvette A");
        HullZone zoneToTest = ship.getHullZones().get(0);
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(1)));
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(3)));

        zoneToTest = ship.getHullZones().get(1);
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(2)));
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(0)));

        zoneToTest = ship.getHullZones().get(2);
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(1)));
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(3)));

        zoneToTest = ship.getHullZones().get(3);
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(2)));
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(0)));
    }

    @Test
    public void testFrontGeometry() throws FileNotFoundException, ParsingException {
        ShipFactory test = new ShipFactory();
        Ship ship = test.getShip("CR90 Corvette A");
        HullZone zoneToTest = ship.getHullZones().get(0);
        ArrayList<BBDPoint> points = zoneToTest.getHullzoneGeometry().getPoints();
        assertEquals(new BBDPoint(0, -7.5f), points.get(0));
        assertEquals(new BBDPoint(-20.5f, 15.7f), points.get(1));
        assertEquals(new BBDPoint(-20.5f, 35.5f), points.get(2));
        assertEquals(new BBDPoint(20.5f, 35.5f), points.get(3));
        assertEquals(new BBDPoint(20.5f, 15.7f), points.get(4));
    }

    @Test
    public void testRearGeometry() throws FileNotFoundException, ParsingException {
        ShipFactory test = new ShipFactory();
        Ship ship = test.getShip("CR90 Corvette A");
        HullZone zoneToTest = ship.getHullZones().get(2);
        ArrayList<BBDPoint> points = zoneToTest.getHullzoneGeometry().getPoints();
        assertEquals(new BBDPoint(0, -7.5f), points.get(0));
        assertEquals(new BBDPoint(20.5f, -29.9f), points.get(1));
        assertEquals(new BBDPoint(20.5f, -35.5f), points.get(2));
        assertEquals(new BBDPoint(-20.5f, -35.5f), points.get(3));
        assertEquals(new BBDPoint(-20.5f, -29.9f), points.get(4));
    }
}
