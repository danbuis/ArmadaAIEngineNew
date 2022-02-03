package components.ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import components.tokens.DefenseToken;

import java.util.ArrayList;
import java.util.Arrays;


public class Ship {
    private String name;
    private String type;
    private ArrayList<String> keywords;
    private String faction;
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
    private BBDPoint location;


    private ArrayList<HullZone> hullzones = new ArrayList<>();
    private BBDPolygon cardboard;
    private BBDPolygon plasticBase;
    private BBDPoint location = new BBDPoint(0,0);

    public Ship(ProtoShip original){
        this.name = original.name;
        this.type = original.type;
        this.keywords = original.keywords;
        this.faction = original.faction;
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

    private ArrayList<HullZone> buildHullZones(ShipSize size, float frontOffset, float frontConjunction, float rearOffset, float rearConjunction, String shields, String antiShipDice) {
        BBDPolygon cardboard = size.getCardboard();
        BBDPoint FR = cardboard.getPoints().get(0);
        BBDPoint FL = cardboard.getPoints().get(1);
        BBDPoint RL = cardboard.getPoints().get(2);
        BBDPoint RR = cardboard.getPoints().get(3);
        BBDPoint front = new BBDPoint(0, size.getLength()/2f - frontConjunction);
        BBDPoint rear = new BBDPoint(0, size.getLength() / -2f + rearConjunction);
        BBDPoint FLintercept = new BBDPoint(size.getWidth()/-2f, size.getLength()/2f-frontOffset);
        BBDPoint FRintercept = new BBDPoint(size.getWidth()/2f, size.getLength()/2f-frontOffset);
        BBDPoint RLintercept = new BBDPoint(size.getWidth()/-2f, size.getLength()/-2f+rearOffset);
        BBDPoint RRintercept = new BBDPoint(size.getWidth()/2f, size.getLength()/-2f+rearOffset);
        BBDPoint[] frontPerimeter = {front, FLintercept, FL, FR, FRintercept};
        BBDPoint[] rightPerimeter = {front, FRintercept, RRintercept, rear};
        BBDPoint[] rearPerimeter = {rear, RRintercept, RR, RL, RLintercept};
        BBDPoint[] leftPerimeter = {rear, RLintercept, FLintercept, front};

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

    public void moveNew(BBDPoint newLocation){
        this.location = newLocation;
    }
}
