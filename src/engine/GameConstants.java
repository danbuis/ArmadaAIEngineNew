package engine;

public class GameConstants {
    /*
      This is going to be a static class object to contain common values that need to be accessible across many
      different types of objects.
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

    /**
     * Ship sizes were sourced from
     * http://starwars-armada.wikia.com/wiki/Size_Class
     * 13/2/2017
     */
    public static final float SHIP_SMALL_WIDTH = 41;
    public static final float SHIP_SMALL_LENGTH = 71;
    public static final float SHIP_MEDIUM_WIDTH = 61;
    public static final float SHIP_MEDIUM_LENGTH = 102;
    public static final float SHIP_LARGE_WIDTH = 76;
    public static final float SHIP_LARGE_LENGTH = 129;
    public static final float SHIP_BASE_RAILS = 2;
    public static final float SQUADRON_PLASTIC_RADIUS = 12.7f;
    public static final float SQUADRON_CARDBOARD_RADIUS = 12.4f;
    public static final float HULLZONE_DIVIDER_WIDTH = 0.4f;

    /**
     * Rendering criteria
     */
    public static final int ZOOM_MINIMUM = 100;
    public static final int ZOOM_MAXIMUM = 1200;

    /**
     * Layers, how far from the camera the associated items are drawn
     */
    public static final int LAYER_SQUADRON_PLASTIC = 25;
    public static final int LAYER_SQUADRON_CARDBOARD = 24;
    public static final int LAYER_HULLZONE_DETAILS = 23;
    public static final int LAYER_SQUADRON_GRAPHIC = 22;
    public static final int LAYER_MAP_BACKGROUND = 26;
}

