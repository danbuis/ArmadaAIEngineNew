package components.ship;

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
