package engine;

import BBDGameLibrary.GameEngine.Camera;
import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.*;
import engine.parsers.ParsingException;
import engine.parsers.SquadronFactory;
import gameComponents.DemoMap;
import gameComponents.Squadrons.Squadron;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**ArmadaGame holds the root logic for the game.  Any object present in the game is eventually attached to here.  It
 * implements the GameComponent interface, which means that contractually it MUST implement the required 5 functions.
 */
public class ArmadaGame implements GameComponent {
    //An object to handle rendering to the screen
    private final Renderer renderer;
    //An object representing our POV in space looking at GameItems.  Essentially a container for some coordinates with a
    //a few niceties built in
    private final Camera camera;
    //An object representing the 3x3 mat a demo game is played on
    private DemoMap demoMap;

    private ArrayList<Squadron> squadrons;

    /**
     * A basic constructor.  Sets up the items only need one instance that is then shared between objects
     */
    public ArmadaGame() {
        renderer = new Renderer();
        camera = new Camera();
    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It acts similar to a constructor
     * in that this holds logic needed on initialization.  Most of the time logic could go here or the constructor.
     * @param window
     */
    @Override
    public void init(Window window) {

        demoMap = initializeDemoMap();

        squadrons = new ArrayList<>();
        try {
            SquadronFactory squadronFactory = new SquadronFactory();
            Squadron temp;
            int currentCol = 0;
            int currentRow = 0;
            for (String squadronName : squadronFactory.getSquadronTypes()){
                temp = squadronFactory.getSquadron(squadronName);
                if(currentCol == 10){
                    currentRow++;
                    currentCol=0;
                }

                temp.relocate(currentCol * 100, currentRow * 100);
                currentCol++;
                System.out.println(temp.getLocation());
                squadrons.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        }

    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It handles input from the user
     * and directs it to objects.  For instance the below code passes input to the demoMap, even though the demoMap
     * doesn't do anything with the input
     * @param window window object everything is being rendered to
     * @param mouseInput object to handle input from the mouse
     */
    @Override
    public void input(Window window, MouseInput mouseInput) {
        demoMap.input(window, mouseInput);
    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It handles the updates to gameItems
     * between ticks.  As an example the current code makes sure that the camera is always centered on the map.
     * @param v time since last update
     * @param mouseInput object handling mouse input
     * @parma window Window object where everything is rendered
     */
    @Override
    public void update(float v, MouseInput mouseInput, Window window) {
        Vector3f mapCenter = demoMap.getPosition();
        camera.setPosition(mapCenter.x, mapCenter.y, 920);
    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It handles rendering objects.
     * Child objects can take care of rendering themselves via their own render function.  Right now it renders our map
     * using one of the built in renderer functions.  The general requirements for rendering are the item/data structure
     * you want to render, the game window so that there is a target to render to, and the camera, so that the item is
     * rotated/scaled etc appropriately.
     * @param window
     */
    @Override
    public void render(Window window) {
        renderer.resetRenderer(window);
        renderer.renderItem(window, demoMap, camera);
        for(Squadron squadron: squadrons){
            for(GameItem item: squadron.getGameItems()){
                renderer.renderItem(window, item, camera);
            }
        }
    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It handles anything that must
     * happen before a GameItem is removed from the game.  Maybe you want to make sure that an item doesn't leave any
     * orphaned child objects before it is removed, in that case you would call cleanup() on the children in this function
     */
    @Override
    public void cleanup() {

    }

    /**
     * Build a basic GameItem2d.  Takes a polygon, a shader program, and a texture.
     * @return
     */
    private DemoMap initializeDemoMap(){
        BBDPolygon poly = Utils.buildQuad(GameConstants.SHORT_BOARD_EDGE, GameConstants.SHORT_BOARD_EDGE);
        ShaderProgram shader = Utils.buildBasicTexturedShaderProgram();
        Texture texture = new Texture("assets/images/maps/map1.jpg");

        return new DemoMap(Mesh.buildMeshFromPolygon(poly, texture), shader, poly, GameConstants.MAP_BACKGROUND_LAYER, true);
    }
}
