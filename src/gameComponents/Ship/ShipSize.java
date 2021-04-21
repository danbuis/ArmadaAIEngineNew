package gameComponents.Ship;

import BBDGameLibrary.Geometry2d.BBDPolygon;
import engine.GameConstants;
import engine.Utils;

public enum ShipSize {
    SMALL(GameConstants.SHIP_SMALL_WIDTH, GameConstants.SHIP_SMALL_LENGTH),
    MEDIUM(GameConstants.SHIP_MEDIUM_WIDTH, GameConstants.SHIP_MEDIUM_LENGTH),
    LARGE(GameConstants.SHIP_LARGE_WIDTH, GameConstants.SHIP_LARGE_LENGTH);

    private final float width;
    private final float length;

    ShipSize(float width, float length){
        this.width = width;
        this.length = length;
    }

    public BBDPolygon getCardboard(){ return Utils.buildQuad(this.width, this.length); }

    public BBDPolygon getPlastic() {
        return Utils.buildQuad(this.width + 2* GameConstants.SHIP_BASE_RAILS, this.length);
    }
}
