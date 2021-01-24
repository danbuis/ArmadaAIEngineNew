package gameComponents.Squadrons;

import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.OpenGL.Window;

import java.util.ArrayList;

/**
 * A class representing a Squadron object
 */
public class Squadron implements GameComponent {

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

    /**
     * Constructor used by the SquadronFactory to build a new object.  Could also be used to build something programmitically
     * if you feel so inclined.
     *
     * @param type What type/hull is the Squadron, ie X-wing
     * @param name The name of the Squadron, ie Biggs Darklighter, or X-wing
     * @param unique Is this squadron unique
     * @param fullHealth How much health does it start with
     * @param maxSpeed How much distance can it move at full speed
     * @param antiShipDice anti ship armament
     * @param antiSquadronDice anti squadron armament
     * @param keywords keywords the Squadron has access to. Must have a value, even if it is an empty list
     * @param pointsValue How many points is it worth
     * @param defenseTokens What defense tokens, if any, does it have?  Must have a value, even if it is an empty list
     */
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

    /**
     * Constructor used to clone another Squadron.  Used to get a Squadron from the SquadronFactory
     * @param original original Squadron to use as a template
     */
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

    @Override
    public void init(Window window) {

    }

    @Override
    public void input(Window window, MouseInput mouseInput) {

    }

    @Override
    public void update(float v, MouseInput mouseInput, Window window) {

    }

    @Override
    public void render(Window window) {

    }

    @Override
    public void cleanup() {

    }
}
