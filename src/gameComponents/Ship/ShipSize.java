package gameComponents.Ship;

import BBDGameLibrary.Geometry2d.BBDPolygon;
import engine.GameConstants;
import engine.Utils;

/**
 * An enum for holding the predifined ship sizes and associated logic.
 */
public enum ShipSize {
    FLOTILLA(GameConstants.SHIP_SMALL_WIDTH, GameConstants.SHIP_SMALL_LENGTH),
    SMALL(GameConstants.SHIP_SMALL_WIDTH, GameConstants.SHIP_SMALL_LENGTH),
    MEDIUM(GameConstants.SHIP_MEDIUM_WIDTH, GameConstants.SHIP_MEDIUM_LENGTH),
    LARGE(GameConstants.SHIP_LARGE_WIDTH, GameConstants.SHIP_LARGE_LENGTH);

    private final float width;
    private final float length;

    ShipSize(float width, float length){
        this.width = width;
        this.length = length;
    }

    /**
     * Get a polygon representing the cardboard rectangle for a ship
     * @return cardboard
     */
    public BBDPolygon getCardboard(){ return Utils.buildQuad(this.width, this.length); }

    /**
     * Get a polygon representing the plastic base of a ship
     * @return plastic
     */
    public BBDPolygon getPlastic() {
        return Utils.buildQuad(this.width + 2* GameConstants.SHIP_BASE_RAILS, this.length);
    }
}
