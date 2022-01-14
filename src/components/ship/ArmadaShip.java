package components.ship;

import components.tokens.DefenseToken;

import java.util.ArrayList;
import java.util.HashMap;


public class ArmadaShip {
    private String name;
    private String type;
    private ArrayList<String> keywords;
    private String faction;
    private String size;
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

    public ArmadaShip(String name, String type, ArrayList<String> keywords, String faction,
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

    public ArmadaShip(ArmadaShip original){
        this.name = original.name;
        this.type = original.type;
        this.keywords = original.keywords;
        this.faction = original.faction;
        this.size = original.size;
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
