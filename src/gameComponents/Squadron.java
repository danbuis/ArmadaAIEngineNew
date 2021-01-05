package gameComponents;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Squadron {

    private final String type;
    private final String name;
    private final boolean unique;
    private int currentHealth;
    private final int fullHealth;
    private final int maxSpeed;
    private final String antiShipDice;
    private final String antiSquadronDice;
    private final ArrayList<String> keywords;
    private final int pointsValue;
    private ArrayList<String> defenseTokens;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isUnique() {
        return unique;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getFullHealth() {
        return fullHealth;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getAntiShipDice() {
        return antiShipDice;
    }

    public String getAntiSquadronDice() {
        return antiSquadronDice;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public ArrayList<String> getDefenseTokens() {
        return defenseTokens;
    }

    public Squadron(String type, String name, boolean unique, int fullHealth,
                    int maxSpeed, String antiShipDice, String antiSquadronDice,
                    ArrayList<String> keywords, int pointsValue, ArrayList<String> defenseTokens){
        this.type = type;
        this.name = name;
        this.unique = unique;
        this.fullHealth = fullHealth;
        this.currentHealth = fullHealth;
        this.maxSpeed = maxSpeed;
        this.antiShipDice = antiShipDice;
        this.antiSquadronDice = antiSquadronDice;
        this.keywords = keywords;
        this.pointsValue = pointsValue;
        this.defenseTokens = defenseTokens;
    }

    public Squadron(Squadron original){
        this.type = original.type;
        this.name = original.name;
        this.unique = original.unique;
        this.fullHealth = original.fullHealth;
        this.currentHealth = original.fullHealth;
        this.maxSpeed = original.maxSpeed;
        this.antiShipDice = original.antiShipDice;
        this.antiSquadronDice = original.antiSquadronDice;
        this.keywords = original.keywords;
        this.pointsValue = original.pointsValue;
        this.defenseTokens = original.defenseTokens;
    }
}
