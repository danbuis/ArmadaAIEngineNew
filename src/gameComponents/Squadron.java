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
}
