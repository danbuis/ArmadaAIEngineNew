package components.ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.Geometry2d.BBDSegment;

import java.util.ArrayList;

/**
 * A HullZone contains data for a the hull zones of a ship
 */
public class HullZone {

    private String armament;
    private int shields;
    private Ship parent;
    private ArrayList<BBDSegment> outsideEdge;
    private BBDPolygon hullzoneGeometry;
    private ArrayList<HullZone> adjacentHullZones = new ArrayList<>();

    /**
     * Standard constructor
     * @param perimeter edges of the hullzone.  First point is the conjunction and proceeding clockwise.  The first segment
     *                  should be from the center of the ship and end at the outside.
     * @param armament what dice does this hullzone have
     * @param shields how many shields
     * @param parent what ship does this belong to
     */
    public HullZone(BBDPoint[] perimeter, String armament, int shields, Ship parent){
        this.armament = armament;
        this.shields = shields;
        this.parent = parent;
        this.hullzoneGeometry = new BBDPolygon(perimeter);
        ArrayList<BBDSegment> segments = new ArrayList<>();
        for (BBDSegment seg:hullzoneGeometry.getSegments()){
            segments.add(new BBDSegment(seg));
        }
        if (segments.size() == 5) {
            segments.remove(0);
            segments.remove(segments.size() - 1);
        } else { // if 4 segments then we want only one of them
            segments.remove(0);
            segments.remove(segments.size() - 1);
            segments.remove(segments.size() - 1);
        }
        this.outsideEdge = segments;
    }

    public void addAdjacentHullZone(HullZone zone1, HullZone zone2){
        this.adjacentHullZones.add(zone1);
        this.adjacentHullZones.add(zone2);
    }

    public String getArmament(){
        return this.armament;
    }

    public int getRemainingShields(){
        return this.shields;
    }

    public ArrayList<HullZone> getAdjacentHullZones(){
        return this.adjacentHullZones;
    }

    public BBDPolygon getHullzoneGeometry(){
        return hullzoneGeometry;
    }

    public ArrayList<BBDSegment> getOutsideEdge(){
        return this.outsideEdge;
    }
}
