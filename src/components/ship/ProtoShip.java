package components.ship;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import components.tokens.DefenseToken;

import java.util.ArrayList;

/**
 * A class to serve as a precursor to a proper ship.  Primarily serves as a data placeholder
 * before the base attributes are processed into a real ship object
 */
public class ProtoShip {
    String name;
    String type;
    ArrayList<String> keywords;
    String faction;
    String size;
    int points;
    int hull;
    ArrayList<DefenseToken> defenseTokens;
    int command;
    int squad;
    int engineering;
    String speed;
    String shields;
    String antiShipDice;
    String antiSquadronDice;
    String upgrades;
    float frontConjunction;
    float rearConjunction;
    float frontOffset;
    float rearOffset;

    public ProtoShip(String name, String type, ArrayList<String> keywords, String faction,
                String size, int points, int hull, ArrayList<DefenseToken> defenseTokens,
                int command, int squad, int engineering, String speed, String shields,
                String antiShipDice, String antiSquadronDice, String upgrades, float frontOffset,
                float frontConjunction, float rearOffset, float rearConjunction){
        this.name = name;
        this.type = type;
        this.keywords = keywords;
        this.faction = faction;
        this.size = size;
        this.points = points;
        this.hull = hull;
        this.defenseTokens = defenseTokens;
        this.command = command;
        this.squad = squad;
        this.engineering = engineering;
        this.speed = speed;
        this.shields = shields;
        this.antiShipDice = antiShipDice;
        this.antiSquadronDice = antiSquadronDice;
        this.upgrades = upgrades;
        this.frontConjunction = frontConjunction;
        this.frontOffset = frontOffset;
        this.rearConjunction = rearConjunction;
        this.rearOffset = rearOffset;
    }

    public String getName(){
        return name;
    }
}
