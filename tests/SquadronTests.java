import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import engine.parsers.ParsingException;
import engine.parsers.SquadronFactory;
import gameComponents.Squadrons.Squadron;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class SquadronTests {

    @Test
    public void testMoving() throws FileNotFoundException, ParsingException {
        SquadronFactory testParser = new SquadronFactory(false);

        Squadron arc = testParser.getSquadron("ARC-170 Starfighter");

        //test original location
        assertEquals(new BBDPoint(0,0), arc.getLocation());

        //test a straight relocate
        arc.relocate(10,10);
        assertEquals(new BBDPoint(10,10), arc.getLocation());
        arc.relocate(12,-10);
        assertEquals(new BBDPoint(12,-10), arc.getLocation());

        //recenter and test moveOffsets
        arc.relocate(0,0);
        arc.moveOffsets(10, 1);
        assertEquals(new BBDPoint(10,1), arc.getLocation());
        arc.moveOffsets(-5,7);
        assertEquals(new BBDPoint(5,8), arc.getLocation());

        //recenter ad test moveAngle
        arc.relocate(0,0);
        arc.moveAngle(10,0);
        assertEquals(new BBDPoint(10,0), arc.getLocation());
        arc.moveAngle(10, (float) (Math.PI/2));
        assertEquals(new BBDPoint(10,10), arc.getLocation());
    }
}
