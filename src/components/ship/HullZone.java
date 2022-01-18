package components.ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.Geometry2d.BBDSegment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HullZone {

    private String armament;
    private int shields;
    private Ship parent;
    private BBDSegment[] outsideEdge;
    private BBDPolygon hullzoneGeometry;
    private ArrayList<HullZone> adjacentHullZones = new ArrayList<>();

    public HullZone(BBDPoint[] perimeter, String armament, int shields, Ship parent){
        this.armament = armament;
        this.shields = shields;
        this.parent = parent;
        this.hullzoneGeometry = new BBDPolygon(perimeter);
        ArrayList<BBDSegment> segments = hullzoneGeometry.getSegments();
        segments.remove(0);
        segments.remove(segments.size()-1);
        this.outsideEdge = segments.toArray(new BBDSegment[0]);
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
}
