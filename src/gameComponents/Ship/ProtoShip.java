package gameComponents.Ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import engine.GameConstants;
import engine.Utils;

/**
 * A class to serve as a precursor to a proper ship class.  Primarily serves as a testing
 * bed for building new objects and the like.
 */
public class ProtoShip {
    private enum Size {SMALL, MEDIUM, LARGE}

    private BBDPoint currentLocation = new BBDPoint(0,0);
    private float orientation = 0f;  //probably degrees
    private final String faction;
    private final Size size;
    private BBDPolygon cardboard;
    private BBDPolygon plasticBase;

    public ProtoShip(String faction, Size size){
        this.faction = faction;
        this.size = size;
        buildBase(size);
    }

    private void buildBase(Size size) {
        switch(size){
            case SMALL:{
                this.cardboard = Utils.buildQuad(GameConstants.SHIP_SMALL_WIDTH, GameConstants.SHIP_SMALL_LENGTH);
                this.plasticBase = Utils.buildQuad(GameConstants.SHIP_SMALL_WIDTH + GameConstants.SHIP_BASE_RAILS*2,
                        GameConstants.SHIP_SMALL_LENGTH);
                break;
            }
            case MEDIUM:{
                this.cardboard = Utils.buildQuad(GameConstants.SHIP_MEDIUM_WIDTH, GameConstants.SHIP_MEDIUM_LENGTH);
                this.plasticBase = Utils.buildQuad(GameConstants.SHIP_MEDIUM_WIDTH + GameConstants.SHIP_BASE_RAILS*2,
                        GameConstants.SHIP_MEDIUM_LENGTH);
                break;
            }
            case LARGE:{
                this.cardboard = Utils.buildQuad(GameConstants.SHIP_LARGE_WIDTH, GameConstants.SHIP_LARGE_LENGTH);
                this.plasticBase = Utils.buildQuad(GameConstants.SHIP_LARGE_WIDTH + GameConstants.SHIP_BASE_RAILS*2,
                        GameConstants.SHIP_LARGE_LENGTH);
                break;
            }
        }
    }


}
