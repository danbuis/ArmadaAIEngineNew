package gameComponents.Ship;

import BBDGameLibrary.Geometry2d.BBDPoint;

/**
 * A class to serve as a precursor to a proper ship class.  Primarily serves as a testing
 * bed for building new objects and the like.
 */
public class ProtoShip {
    private enum Size {SMALL, MEDIUM, LARGE}

    private BBDPoint currentLocation = new BBDPoint(0,0);
    private float orientation = 0f;
    private final String faction;
    private final Size size;

    public ProtoShip(String faction, Size size){
        this.faction = faction;
        this.size = size;
    }
}
