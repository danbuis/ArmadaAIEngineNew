package engine;

import java.util.ArrayList;
import java.util.Arrays;

public class GameConstants {
    /**
     * This is going to be a static class object to contain common values that need to be accessible across many
     * different types of objects.
     */

    // Dimensions in millimeters
    /**
     * Ranges and distances were sourced from FFG user leerat at
     * https://community.fantasyflightgames.com/topic/173990-exact-measurements-on-range-ruler/
     * 13/2/2017
     */
    public static final float DISTANCE_1 = 71.5f;
    public static final float DISTANCE_2 = 125;
    public static final float DISTANCE_3 = 185;
    public static final float DISTANCE_4 = 245;
    public static final float DISTANCE_5 = 305;

    public static final float RANGE_CLOSE = 123;
    public static final float RANGE_MEDIUM = 187;
    public static final float RANGE_LONG = 305;
    public static final float SHORT_BOARD_EDGE = 914.4f;
    public static final float LONG_BOARD_EDGE = 1828.8f;

    public static final ArrayList<String> CURRENT_FACTIONS = new ArrayList<String>(Arrays.asList(new String[]{"Rebel", "Imperial", "Separatist", "Republic"}));

    /**
     * Ship sizes were sourced from
     * http://starwars-armada.wikia.com/wiki/Size_Class
     * 13/2/2017
     */
    public static final float SHIP_SMALL_WIDTH = 41;
    public static final float SHIP_SMALL_LENGTH =71;
    public static final float SHIP_MEDIUM_WIDTH = 61;
    public static final float SHIP_MEDIUM_LENGTH = 102;
    public static final float SHIP_LARGE_WIDTH = 76;
    public static final float SHIP_LARGE_LENGTH = 129;
    public static final float SHIP_BASE_RAILS = 2;

    /**Layers, how far from the camera the associated items are drawn
     *
     */
    public static final int MAP_BACKGROUND_LAYER = 100;
}
