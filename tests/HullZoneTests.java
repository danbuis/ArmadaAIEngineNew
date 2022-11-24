import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDSegment;
import components.ship.HullZone;
import components.ship.Ship;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HullZoneTests {
    @Test
    public void testConstruction() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        assertEquals(4, ship.getHullZones().size());
    }

    @Test
    public void testBasicAttributes() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(0);
        assertEquals("RRB", zoneToTest.getArmament());
        assertEquals(2, zoneToTest.getRemainingShields());
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(1)));
        assertTrue(zoneToTest.getAdjacentHullZones().contains(ship.getHullZones().get(3)));
    }

    @Test
    public void testAdjacencyIsCorrect() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
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
    public void testFrontGeometry() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(0);
        ArrayList<BBDPoint> points = zoneToTest.getHullzoneGeometry().getPoints();
        assertEquals(new BBDPoint(0, -7.5f), points.get(0));
        assertEquals(new BBDPoint(-20.5f, 15.7f), points.get(1));
        assertEquals(new BBDPoint(-20.5f, 35.5f), points.get(2));
        assertEquals(new BBDPoint(20.5f, 35.5f), points.get(3));
        assertEquals(new BBDPoint(20.5f, 15.7f), points.get(4));
        assertEquals(5, points.size());
    }

    @Test
    public void testFrontGeometryOutside() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(0);
        ArrayList<BBDSegment> edge = zoneToTest.getOutsideEdge();
        assertEquals(3, edge.size());
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(-20.5f, 15.7f), new BBDPoint(-20.5f, 35.5f))));
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(20.5f, 35.5f), new BBDPoint(-20.5f, 35.5f))));
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(20.5f, 15.7f), new BBDPoint(20.5f, 35.5f))));
    }

    @Test
    public void testRearGeometry() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(2);
        ArrayList<BBDPoint> points = zoneToTest.getHullzoneGeometry().getPoints();
        assertEquals(new BBDPoint(0, -7.5f), points.get(0));
        assertEquals(new BBDPoint(20.5f, -29.9f), points.get(1));
        assertEquals(new BBDPoint(20.5f, -35.5f), points.get(2));
        assertEquals(new BBDPoint(-20.5f, -35.5f), points.get(3));
        assertEquals(new BBDPoint(-20.5f, -29.9f), points.get(4));
        assertEquals(5, points.size());
    }

    @Test
    public void testRearGeometryOutside() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(2);
        ArrayList<BBDSegment> edge = zoneToTest.getOutsideEdge();
        assertEquals(3, edge.size());
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(20.5f, -29.9f), new BBDPoint(20.5f, -35.5f))));
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(20.5f, -35.5f), new BBDPoint(-20.5f, -35.5f))));
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(-20.5f, -29.9f), new BBDPoint(-20.5f, -35.5f))));
    }

    @Test
    public void testRightGeometry() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(1);
        ArrayList<BBDPoint> points = zoneToTest.getHullzoneGeometry().getPoints();
        assertEquals(new BBDPoint(0, -7.5f), points.get(0));
        assertEquals(new BBDPoint(20.5f, 15.7f), points.get(1));
        assertEquals(new BBDPoint(20.5f, -29.9f), points.get(2));
        assertEquals(new BBDPoint(0, -7.5f), points.get(3));
        assertEquals(4, points.size());
    }

    @Test
    public void testRightGeometryOutside() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(1);
        ArrayList<BBDSegment> edge = zoneToTest.getOutsideEdge();
        assertEquals(1, edge.size());
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(20.5f, 15.7f), new BBDPoint(20.5f, -29.9f))));
    }

    @Test
    public void testLeftGeometry() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(3);
        ArrayList<BBDPoint> points = zoneToTest.getHullzoneGeometry().getPoints();
        assertEquals(new BBDPoint(0, -7.5f), points.get(0));
        assertEquals(new BBDPoint(-20.5f, -29.9f), points.get(1));
        assertEquals(new BBDPoint(-20.5f, 15.7f), points.get(2));
        assertEquals(new BBDPoint(0, -7.5f), points.get(3));
        assertEquals(4, points.size());
    }

    @Test
    public void testLeftGeometryOutside() throws IOException, ParseException {
        Ship ship = new Ship("CR90_Corvette_A");
        HullZone zoneToTest = ship.getHullZones().get(3);
        ArrayList<BBDSegment> edge = zoneToTest.getOutsideEdge();
        assertEquals(1, edge.size());
        assertTrue(edge.contains(new BBDSegment(new BBDPoint(-20.5f, 15.7f), new BBDPoint(-20.5f, -29.9f))));
    }
}
