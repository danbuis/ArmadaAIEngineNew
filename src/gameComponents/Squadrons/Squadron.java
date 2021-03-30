package gameComponents.Squadrons;

import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDGeometryUtils;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.ShaderProgram;
import BBDGameLibrary.OpenGL.Texture;
import BBDGameLibrary.OpenGL.Window;
import engine.GameConstants;
import engine.Utils;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<GameItem> gameItems = new ArrayList<>();
    private float currentX = 0;
    private float currentY = 0;
    private boolean renderSquadrons;

    /**
     * Constructor used by the SquadronFactory to build a new object.  Could also be used to build something programmatically
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
     * Constructor used to clone another Squadron.  Used to get a Squadron from the SquadronFactory and prepare it for
     * being used in the game
     * @param original original Squadron to use as a template
     */
    public Squadron(Squadron original, boolean renderSquadrons){
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
        this.renderSquadrons = renderSquadrons;

        if (renderSquadrons) {
            this.buildGameItems();
        }
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

    private void buildGameItems(){
        BBDPolygon plasticBase = BBDGeometryUtils.createCircle(new BBDPoint(this.currentX,this.currentY), GameConstants.SQUADRON_PLASTIC_RADIUS, 100);
        BBDPolygon cardboard = BBDGeometryUtils.createCircle(new BBDPoint(this.currentX,this.currentY), GameConstants.SQUADRON_CARDBOARD_RADIUS, 100);

        GameItem plasticBaseItem = new GameItem2d(Mesh.buildMeshFromPolygon(plasticBase, null), engine.Utils.buildSolidColorShader("white"), plasticBase, GameConstants.SQUADRON_PLASTIC, true);

        GameItem cardboardItem = new GameItem2d(Mesh.buildMeshFromPolygon(cardboard, null), engine.Utils.buildSolidColorShader("black"), cardboard, GameConstants.SQUADRON_CARDBOARD, true);

        BBDPolygon poly = Utils.buildQuad(20, 20);
        ShaderProgram shader = Utils.buildBasicTexturedShaderProgram();
        Texture texture = new Texture("assets/images/squadrons/squad_"+buildSquadFileName());
        GameItem squadronGraphic = new GameItem2d(Mesh.buildMeshFromPolygon(poly, texture), shader, poly, GameConstants.SQUADRON_GRAPHIC, false);
        this.gameItems.add(squadronGraphic);
        this.gameItems.add(cardboardItem);
        this.gameItems.add(plasticBaseItem);
    }

    public String buildSquadFileName() {
        String baseFileName = this.type;
        if(this.unique){
            baseFileName = baseFileName+"_"+this.name;
        }

        String cleanedFileName = baseFileName.toLowerCase().replace(" ", "-").replace("\"", "");

        return cleanedFileName+".png";
    }

    private void moveNew(float newX, float newY){
        this.currentX = newX;
        this.currentY = newY;
        if(this.renderSquadrons) {
            for(GameItem gameItem:this.gameItems){
                gameItem.setPosition(newX, newY, gameItem.getPosition().z);
            }
        }
    }

    public void moveOffsets(float deltaX, float deltaY){
        moveNew(this.currentX + deltaX, this.currentY + deltaY);
    }

    public void moveAngle(float distance, float angle){
        float deltaX = (float) (Math.cos(angle) * distance);
        float deltaY = (float) (Math.sin(angle) * distance);
        moveOffsets(deltaX, deltaY);
    }

    public void relocate(float newX, float newY){
        moveNew(newX, newY);
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

    public ArrayList<String> getDefenseTokens() {
        return defenseTokens;
    }

    public ArrayList<GameItem> getGameItems() {
        return gameItems;
    }

    public BBDPoint getLocation(){
        return new BBDPoint(currentX, currentY);
    }
}
