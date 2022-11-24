import components.ship.ManeuverTemplate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ManeuverTemplateTests {

    @Test
    public void testBuildSmallManeuverChart(){
        ManeuverTemplate template = new ManeuverTemplate("1 01 - -");
        int[] expectedSpeed1 = new int[]{1};
        int[] expectedSpeed2 = new int[]{0,1};
        int[] expectedSpeedEmpty = new int[]{};

        assertTrue(Arrays.equals(expectedSpeed1, template.getClicks(1)));
        assertTrue(Arrays.equals(expectedSpeed2, template.getClicks(2)));
        assertTrue(Arrays.equals(expectedSpeedEmpty, template.getClicks(3)));
        assertTrue(Arrays.equals(expectedSpeedEmpty, template.getClicks(4)));
    }

    @Test
    public void testBuildLargeManeuverChart(){
        ManeuverTemplate template = new ManeuverTemplate("2 12 012 0112");
        int[] expectedSpeed1 = new int[]{2};
        int[] expectedSpeed2 = new int[]{1,2};
        int[] expectedSpeed3 = new int[]{0,1,2};
        int[] expectedSpeed4 = new int[]{0,1,1,2};

        assertTrue(Arrays.equals(expectedSpeed1, template.getClicks(1)));
        assertTrue(Arrays.equals(expectedSpeed2, template.getClicks(2)));
        assertTrue(Arrays.equals(expectedSpeed3, template.getClicks(3)));
        assertTrue(Arrays.equals(expectedSpeed4, template.getClicks(4)));
    }

    @Test
    public void testValidateManeuver(){
        ManeuverTemplate template = new ManeuverTemplate("2 12 012 0112");

        assertTrue(template.validateManeuver(new int[]{0,0,0,0}, 0));
        assertTrue(template.validateManeuver(new int[]{0,0,0}, 0));
        assertTrue(template.validateManeuver(new int[]{0,1,1,2}, 0));
        assertTrue(template.validateManeuver(new int[]{2}, 0));
        assertFalse(template.validateManeuver(new int[]{1,0,0,0}, 0));
        assertFalse(template.validateManeuver(new int[]{1,0,0}, 0));
    }

    @Test
    public void testValidateManeuverExtraClick(){
        ManeuverTemplate template = new ManeuverTemplate("2 12 012 0112");
        assertTrue(template.validateManeuver(new int[]{1,0,0,0}, 1));
        assertTrue(template.validateManeuver(new int[]{1,0,0}, 1));
    }

    @Test
    public void testValidateManeuverExtraClickDifferentSpot(){
        ManeuverTemplate template = new ManeuverTemplate("2 12 012 0112");
        assertTrue(template.validateManeuver(new int[]{1,0,0,0}, 1));
        assertTrue(template.validateManeuver(new int[]{0,2,0}, 1));
    }

    @Test
    public void testValidateManeuverTooFast(){
        ManeuverTemplate template = new ManeuverTemplate("1 01 - -");
        assertFalse(template.validateManeuver(new int[]{1,0,0}, 1));
    }

    @Test
    public void testValidateManeuverNotEnoughExtraClicks(){
        ManeuverTemplate template = new ManeuverTemplate("2 12 012 0112");
        assertFalse(template.validateManeuver(new int[]{1,2,0}, 1));
    }

    @Test
    public void testValidateManeuverThreeClicks(){
        ManeuverTemplate template = new ManeuverTemplate("2 12 012 0112");
        assertFalse(template.validateManeuver(new int[]{0,1,3}, 1));
    }

    @Test
    public void testValidSpeed(){
        ManeuverTemplate template = new ManeuverTemplate("1 01 - -");
        assertTrue(template.validSpeed(1));
        assertTrue(template.validSpeed(2));
        assertFalse(template.validSpeed(3));
        assertFalse(template.validSpeed(4));
    }
}
