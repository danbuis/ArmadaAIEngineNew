package gameComponents.Ship;

import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.Utils.GeometryGenerators;
import engine.GameConstants;

/**
 * An enum for holding the predifined ship sizes and associated logic.
 */
public enum ShipSize {
    FLOTILLA(GameConstants.SHIP_SMALL_WIDTH, GameConstants.SHIP_SMALL_LENGTH,1),
    SMALL(GameConstants.SHIP_SMALL_WIDTH, GameConstants.SHIP_SMALL_LENGTH,1),
    MEDIUM(GameConstants.SHIP_MEDIUM_WIDTH, GameConstants.SHIP_MEDIUM_LENGTH,2),
    LARGE(GameConstants.SHIP_LARGE_WIDTH, GameConstants.SHIP_LARGE_LENGTH,3);

    private final float width;
    private final float length;
    private final int size;

    ShipSize(float width, float length, int size){
        this.width = width;
        this.length = length;
        this.size = size;
    }

    /**
     * Get a polygon representing the cardboard rectangle for a ship
     * @return cardboard
     */
    public BBDPolygon getCardboard(){ return GeometryGenerators.buildQuad(this.width, this.length); }

    /**
     * Get a polygon representing the plastic base of a ship
     * @return plastic
     */
    public BBDPolygon getPlastic() {
        return GeometryGenerators.buildQuad(this.width + 2* GameConstants.SHIP_BASE_RAILS, this.length);
    }

    /**
     * Is this ShipSize smaller than another one.  Primarily used for evades and the occasional collision
     * @param other
     * @return is this ship smaller than the other one
     */
    public boolean isSmaller(ShipSize other){
        return this.size < other.size;
    }
}
