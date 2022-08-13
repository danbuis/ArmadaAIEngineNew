package components.ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.Geometry2d.BBDSegment;
import components.tokens.DefenseToken;
import engine.forces.Faction;

import java.util.ArrayList;
import java.util.Arrays;


public class Ship {
    private String name;
    private String type;
    private ArrayList<String> keywords;
    private Faction faction;
    private ShipSize size;
    private int points;
    private int hull;
    private ArrayList<DefenseToken> defenseTokens;
    private int command;
    private int squad;
    private int engineering;
    private String speed;
    private String shields;
    private String antiShipDice;
    private String antiSquadronDice;
    private String upgrades;
    private float frontConjunction;
    private float rearConjunction;
    private float frontOffset;
    private float rearOffset;
    private ArrayList<HullZone> hullzones = new ArrayList<>();
    private BBDPolygon cardboard;
    private BBDPolygon plasticBase;
    private BBDPoint location = new BBDPoint(0,0);

    public Ship(ProtoShip original){
        this.name = original.name;
        this.type = original.type;
        this.keywords = original.keywords;
        this.faction = Faction.getFaction(original.faction);
        this.points = original.points;
        this.hull = original.hull;
        this.defenseTokens = original.defenseTokens;
        this.command = original.command;
        this.squad = original.squad;
        this.engineering = original.engineering;
        this.speed = original.speed;
        this.shields = original.shields;
        this.antiShipDice = original.antiShipDice;
        this.antiSquadronDice = original.antiSquadronDice;
        this.upgrades = original.upgrades;


        if(original.size.toLowerCase().equals("small")){
            buildBase(ShipSize.SMALL);
            this.size = ShipSize.SMALL;
        }else if(original.size.toLowerCase().equals("medium")){
            buildBase(ShipSize.MEDIUM);
            this.size = ShipSize.MEDIUM;
        }else if(original.size.toLowerCase().equals("large")){
            buildBase(ShipSize.LARGE);
            this.size = ShipSize.LARGE;
        }else if(original.size.toLowerCase().equals("flotilla")) {
            buildBase(ShipSize.FLOTILLA);
            this.size = ShipSize.FLOTILLA;
        }
        this.hullzones = buildHullZones(this.size, original.frontOffset, original.frontConjunction, original.rearOffset, original.rearConjunction, original.shields, original.antiShipDice);
        this.location = new BBDPoint(0,0);
    }

    public void moveNew(BBDPoint newPoint){
        this.location = newPoint;
       
    }

    public BBDSegment[] getHullZoneLines(){
        BBDPolygon front = this.hullzones.get(0).getHullzoneGeometry();
        BBDPolygon back = this.hullzones.get(2).getHullzoneGeometry();
        return new BBDSegment[]{
                front.getSegments().get(0),
                front.getSegments().get(front.getSegments().size()-1),
                back.getSegments().get(0),
                back.getSegments().get(back.getSegments().size()-1)
        };
    }

    private ArrayList<HullZone> buildHullZones(ShipSize size, float frontOffset, float frontConjunction, float rearOffset, float rearConjunction, String shields, String antiShipDice) {
        BBDPolygon cardboard = size.getCardboard();
        BBDPoint FR = cardboard.getPoints().get(0);
        BBDPoint FL = cardboard.getPoints().get(1);
        BBDPoint RL = cardboard.getPoints().get(2);
        BBDPoint RR = cardboard.getPoints().get(3);
        BBDPoint front = new BBDPoint(0, size.getLength()/2f - frontConjunction);
        BBDPoint rear = new BBDPoint(0, size.getLength() / -2f + rearConjunction);
        BBDPoint RLintercept = new BBDPoint(size.getWidth()/-2f, size.getLength()/-2f+rearOffset);
        BBDPoint RRintercept = new BBDPoint(size.getWidth()/2f, size.getLength()/-2f+rearOffset);
        BBDPoint[] rearPerimeter = {rear, RRintercept, RR, RL, RLintercept};
        BBDPoint[] frontPerimeter = null;
        BBDPoint[] rightPerimeter = null;
        BBDPoint[] leftPerimeter = null;
        if (frontOffset >= 0){
            BBDPoint FLintercept = new BBDPoint(size.getWidth()/-2f, size.getLength()/2f-frontOffset);
            BBDPoint FRintercept = new BBDPoint(size.getWidth()/2f, size.getLength()/2f-frontOffset);
            frontPerimeter = new BBDPoint[]{front, FLintercept, FL, FR, FRintercept};
            rightPerimeter = new BBDPoint[]{front, FRintercept, RRintercept, rear};
            leftPerimeter = new BBDPoint[]{rear, RLintercept, FLintercept, front};
        } else {
            BBDPoint FLintercept = new BBDPoint(size.getWidth()/-2f - frontOffset, size.getLength()/2f);
            BBDPoint FRintercept = new BBDPoint(size.getWidth()/2f + frontOffset, size.getLength()/2f);
            frontPerimeter = new BBDPoint[]{front, FLintercept, FRintercept};
            rightPerimeter = new BBDPoint[]{front, FRintercept, FR, RRintercept, rear};
            leftPerimeter = new BBDPoint[]{rear, RLintercept, FL, FLintercept, front};
        }

        String[] shieldValues = shields.split(" ");
        String[] armaments = antiShipDice.split(" ");

        HullZone frontHullZone = new HullZone(frontPerimeter, armaments[0], Integer.parseInt(shieldValues[0]), this);
        HullZone rightHullZone = new HullZone(rightPerimeter, armaments[1], Integer.parseInt(shieldValues[1]), this);
        HullZone leftHullZone = new HullZone(leftPerimeter, armaments[1], Integer.parseInt(shieldValues[1]), this);
        HullZone rearHullZone = new HullZone(rearPerimeter, armaments[2], Integer.parseInt(shieldValues[2]), this);

        frontHullZone.addAdjacentHullZone(rightHullZone, leftHullZone);
        rightHullZone.addAdjacentHullZone(frontHullZone, rearHullZone);
        rearHullZone.addAdjacentHullZone(rightHullZone, leftHullZone);
        leftHullZone.addAdjacentHullZone(frontHullZone, rearHullZone);

        ArrayList<HullZone> returnList = new ArrayList<HullZone>(Arrays.asList(new HullZone[]{frontHullZone, rightHullZone, rearHullZone, leftHullZone}));
        return returnList;
    }

    private void buildBase(ShipSize size) {
        this.cardboard = size.getCardboard();
        this.plasticBase = size.getPlastic();
    }

    public ArrayList<HullZone> getHullZones(){
        return this.hullzones;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public BBDPoint getLocation(){
        return this.location;
    }

    public ShipSize getSize(){
        return this.size;
    }

    /**
     * Build a string based on the ship object's properties to grab the appropriate descriptively named file.
     * Concatenates a few fields and cleans up outstanding chars like spaces, quotes etc.
     * @return image file to be used from the assets directory
     */
    public String buildShipFileName() {
        String baseFileName = this.faction + "_" + this.type;

        String cleanedFileName = baseFileName.toLowerCase().replace(" ", "_");

        return "ship_" + cleanedFileName;
    }

    public Faction getFaction(){
        return this.faction;
    }
}
