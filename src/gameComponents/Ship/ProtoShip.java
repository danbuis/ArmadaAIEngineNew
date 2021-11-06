package gameComponents.Ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import engine.GameConstants;

/**
 * A class to serve as a precursor to a proper ship class.  Primarily serves as a testing
 * bed for building new objects and the like.
 */
public class ProtoShip {
    private BBDPoint currentLocation = new BBDPoint(0,0);
    private float orientation = 0f;  //probably degrees
    private final String faction;
    private final ShipSize size;
    private BBDPolygon cardboard;
    private BBDPolygon plasticBase;

    public ProtoShip(String faction, ShipSize size){
        this.faction = faction;
        this.size = size;
        buildBase(size);
    }

    private void buildBase(ShipSize size) {
       this.cardboard = size.getCardboard();
       this.plasticBase = size.getPlastic();
    }


}
