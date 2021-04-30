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
import gameComponents.DefenseTokens.DefenseToken;

import java.util.ArrayList;

/**
 * A class representing a Squadron object
 */
public class Squadron implements GameComponent {

    private final String type;
    private final String name;
    private final boolean unique;
    private final String faction;
    private int currentHealth;
    private final int fullHealth;
    private final int maxSpeed;
    private final String antiShipDice;
    private final String antiSquadronDice;
    private final ArrayList<String> keywords;
    private final int pointsValue;
    private ArrayList<DefenseToken> defenseTokens;
    private ArrayList<GameItem> gameItems = new ArrayList<>();
    private BBDPoint currentLocation = new BBDPoint(0,0);
    private boolean renderSquadrons;


    /**
     * Static objects to build meshes.  Long term these will be moved somewhere else once rendering is separated from the Squadron object.
     */
    private static BBDPolygon plasticBase = BBDGeometryUtils.createCircle(new BBDPoint(0,0), GameConstants.SQUADRON_PLASTIC_RADIUS, 100);
    private static float[] plasticPositions = Mesh.buildMeshPositions(plasticBase);
    private static float[] plasticTex = Mesh.buildTextureCoordinates(plasticBase);
    private static int[] plasticIndices = Mesh.buildIndices(plasticBase);

    private static BBDPolygon cardboard = BBDGeometryUtils.createCircle(new BBDPoint(0,0), GameConstants.SQUADRON_CARDBOARD_RADIUS, 100);
    private static float[] cardboardPositions = Mesh.buildMeshPositions(cardboard);
    private static float[] cardboardTex = Mesh.buildTextureCoordinates(cardboard);
    private static int[] cardboardIndices = Mesh.buildIndices(cardboard);


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
    public Squadron(String type, String name, boolean unique, String faction, int fullHealth,
                    int maxSpeed, String antiShipDice, String antiSquadronDice,
                    ArrayList<String> keywords, int pointsValue, ArrayList<DefenseToken> defenseTokens){
        this.type = type;
        this.name = name;
        this.unique = unique;
        this.faction = faction;
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
        this.faction = original.faction;
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

    /**
     * Build the GameItem objects to be used to render this object.
     */
    private void buildGameItems(){
        GameItem plasticBaseItem = new GameItem2d(new Mesh(plasticPositions, plasticTex, plasticIndices, null), Utils.WHITE_SOLID, plasticBase, GameConstants.SQUADRON_PLASTIC, false);
        GameItem cardboardItem = new GameItem2d(new Mesh(cardboardPositions, cardboardTex, cardboardIndices, null), Utils.BLACK_SOLID, cardboard, GameConstants.SQUADRON_CARDBOARD, false);

        BBDPolygon poly = Utils.buildQuad(20, 20);
        ShaderProgram shader = Utils.TEXTURED_GENERIC;
        Texture texture = new Texture("assets/images/squadrons/squad_"+buildSquadFileName());
        GameItem squadronGraphic = new GameItem2d(Mesh.buildMeshFromPolygon(poly, texture), shader, poly, GameConstants.SQUADRON_GRAPHIC, false);
        this.gameItems.add(squadronGraphic);
        this.gameItems.add(cardboardItem);
        this.gameItems.add(plasticBaseItem);
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

    /**
     * Root movement function.  All movement functions eventually lead to here.  Function is private because it is the one
     * that modifies items of the class.
     * @param newPoint new BBDPoint to use for the location of the squadron
     */
    private void moveNew(BBDPoint newPoint){
        this.currentLocation = newPoint;
        float newX = newPoint.getXLoc();
        float newY = newPoint.getYLoc();
        if(this.renderSquadrons) {
            for(GameItem gameItem:this.gameItems){
                gameItem.setPosition(newX, newY, gameItem.getPosition().z);
            }
        }
    }

    /**
     * Movement function for when we know the relative offset
     * @param deltaX change in X coordinate
     * @param deltaY change in Y coordinate
     */
    public void moveOffsets(float deltaX, float deltaY){
        this.currentLocation.translate(deltaX, deltaY);
        //gotta call the private one so that we update the underlying widgets
        moveNew(this.currentLocation);
    }

    /**
     * Movement function for when we know angle and distance
     * @param distance movement length
     * @param angle angle of movement
     */
    public void moveAngle(float distance, float angle){
        float deltaX = (float) (Math.cos(angle) * distance);
        float deltaY = (float) (Math.sin(angle) * distance);
        moveOffsets(deltaX, deltaY);
    }

    /**
     * A public facing function to feed into the root movement function.
     * @param newLocation new location for the squadron
     */
    public void relocate(BBDPoint newLocation){
        moveNew(newLocation);
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

    public ArrayList<GameItem> getGameItems() {
        return gameItems;
    }

    public BBDPoint getLocation(){
        return this.currentLocation;
    }
}
