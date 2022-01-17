package components.ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import components.tokens.DefenseToken;

import java.util.ArrayList;


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

    private BBDPolygon cardboard;
    private BBDPolygon plasticBase;

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
        this.frontConjunction = original.frontConjunction;
        this.frontOffset = original.frontOffset;
        this.rearConjunction = original.rearConjunction;
        this.rearOffset = original.rearOffset;

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

        buildHullZones(this.size, original.frontOffset, original.frontConjunction, original.rearOffset, original.rearConjunction, original.shields, original.antiShipDice);
    }

    private void buildHullZones(ShipSize size, float frontOffset, float frontConjunction, float rearOffset, float rearConjunction, String shields, String antiShipDice) {
        BBDPolygon cardboard = size.getCardboard();
        BBDPoint FL = cardboard.getPoints().get(0);
        BBDPoint FR = cardboard.getPoints().get(1);
        BBDPoint RR = cardboard.getPoints().get(2);
        BBDPoint RL = cardboard.getPoints().get(3);
        BBDPoint front = new BBDPoint(0, frontConjunction);
        BBDPoint rear = new BBDPoint(0, rearConjunction);
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

        frontHullZone.addAdjacentHullZone(new HullZone[]{rightHullZone, leftHullZone});
        rightHullZone.addAdjacentHullZone(new HullZone[]{frontHullZone, rearHullZone});
        rearHullZone.addAdjacentHullZone(new HullZone[]{rightHullZone, leftHullZone});
        leftHullZone.addAdjacentHullZone(new HullZone[]{frontHullZone, rearHullZone});
    }

    private void buildBase(ShipSize size) {
        this.cardboard = size.getCardboard();
        this.plasticBase = size.getPlastic();
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
}
