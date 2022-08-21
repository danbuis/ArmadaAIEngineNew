package components.squadrons;

import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.OpenGL.Window;
import components.tokens.DefenseToken;
import engine.forces.Faction;
import engine.parsers.ParsingUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class representing a Squadron object
 */
public class Squadron implements GameComponent {

    private final String type;
    private final String name;
    private final boolean unique;
    private final Faction faction;
    private int currentHealth;
    private final int fullHealth;
    private final int maxSpeed;
    private final String antiShipDice;
    private final String antiSquadronDice;
    private final ArrayList<String> keywords;
    private final int pointsValue;
    private ArrayList<DefenseToken> defenseTokens;
    private BBDPoint currentLocation = new BBDPoint(0,0);


    /**
     * Constructor to read in the appropriate json file for a squadron
     * @param shipName what squadron to create
     */
    public Squadron(String shipName) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("assets/data/squadrons/"+shipName+".json"));
        JSONObject json = (JSONObject) obj;
        this.type = (String)json.get("Type");
        this.name = (String)json.get("Name");
        this.unique = (json.get("Unique")).equals("Y");
        this.faction = Faction.getFaction((String)json.get("Faction"));
        this.fullHealth = Integer.parseInt((String) json.get("Hull"));
        this.currentHealth = Integer.parseInt((String) json.get("Hull"));
        this.maxSpeed = Integer.parseInt((String) json.get("Speed"));;
        this.antiShipDice = (String)json.get("AntiShipDice");
        this.antiSquadronDice = (String)json.get("AntiSquadronDice");
        this.keywords = ParsingUtils.buildArrayListFromJson("Keywords", json);
        this.pointsValue = Integer.parseInt((String) json.get("Points"));
        this.defenseTokens = ParsingUtils.buildDefenseTokensFromStrings(ParsingUtils.buildArrayListFromJson("DefenseTokens", json));
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

    public void moveNew(BBDPoint newPoint){
        this.currentLocation = newPoint;
    }



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

    public ArrayList<DefenseToken> getDefenseTokens() {
        return defenseTokens;
    }

    public BBDPoint getLocation(){
        return this.currentLocation;
    }

    /**
     * Build a string based on the squadron object's properties to grab the appropriate descriptively named file.
     * Concatenates a few fields and cleans up outstanding chars like spaces, quotes etc.
     * @return image file to be used from the assets directory
     */
    public String buildSquadFileName() {
        String baseFileName = this.type;
        if(this.unique){
            baseFileName = baseFileName+"_"+this.name;
        }

        String cleanedFileName = baseFileName.toLowerCase().replace(" ", "-").replace("\"", "");

        return cleanedFileName+".png";
    }
}
