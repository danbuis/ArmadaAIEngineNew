import BBDGameLibrary.Geometry2d.BBDPoint;
import GUI.board.SquadronRenderer;
import engine.parsers.*;
import components.squadrons.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class SquadronTests {

    @Test
    public void testMoving() throws FileNotFoundException, ParsingException {
        SquadronFactory testParser = new SquadronFactory();

        Squadron arc = testParser.getSquadron("ARC-170 Starfighter");
        SquadronRenderer renderer = new SquadronRenderer(arc);

        //test original location
        assertEquals(new BBDPoint(0,0), arc.getLocation());

        //test a straight relocate
        arc.moveNew(new BBDPoint(10,10));
        assertEquals(new BBDPoint(10,10), arc.getLocation());
        arc.moveNew(new BBDPoint(12,-10));
        assertEquals(new BBDPoint(12,-10), arc.getLocation());

        //recenter and test moveOffsets
        renderer.relocate(new BBDPoint(0,0));
        renderer.moveOffsets(10, 1);
        assertEquals(new BBDPoint(10,1), arc.getLocation());
        renderer.moveOffsets(-5,7);
        assertEquals(new BBDPoint(5,8), arc.getLocation());

        //recenter ad test moveAngle
        renderer.relocate(new BBDPoint(0,0));
        renderer.moveAngle(10,0);
        assertEquals(new BBDPoint(10,0), arc.getLocation());
        renderer.moveAngle(10, (float) (Math.PI/2));
        assertEquals(new BBDPoint(10,10), arc.getLocation());
    }

    @Test
    public void testSquadFileNames() throws FileNotFoundException, ParsingException {
        SquadronFactory testParser = new SquadronFactory();

        Squadron arc = testParser.getSquadron("ARC-170 Starfighter");
        String expectedFileName = "arc-170-starfighter.png";
        assertEquals(expectedFileName, arc.buildSquadFileName());

        Squadron haor = testParser.getSquadron("Haor Chall Prototypes");
        expectedFileName = "vulture-class-droid-fighter_haor-chall-prototypes.png";
        assertEquals(expectedFileName, haor.buildSquadFileName());
    }
}
