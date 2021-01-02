package engine;

import BBDGameLibrary.GameEngine.Camera;
import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.*;
import gameComponents.Background.DemoMap;
import org.joml.Vector3f;

/**ArmadaGame holds the root logic for the game.  Any object present in the game is eventually attached to here.  It
 * implements the GameComponent interface, which means that contractually it MUST implement the required 5 functions.
 */
public class ArmadaGame implements GameComponent {
    //An object to handle rendering to the screen
    private final Renderer renderer;
    //An object representing our POV in space looking at GameItems.  Essentially a container for some coordinates with a
    //a few nicetiese built in
    private final Camera camera;
    //An object representing the 3x3 mat a demo game is played on
    private DemoMap demoMap;

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
    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It handles input from the user
     * and directs it to objects.  For instance the below code passes input to the demoMap, even though the demoMap
     * doesn't do anything with the input
     * @param window
     */
    @Override
    public void input(Window window) {
        demoMap.input(window);
    }

    /**
     * All GameComponents and objects that implement GameComponent need this function.  It handles the updates to gameItems
     * between ticks.  As an example the current code makes sure that the camera is always centered on the map.
     * @param v
     */
    @Override
    public void update(float v) {
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
        System.out.println("rendering");
        renderer.resetRenderer(window);
        renderer.renderItem(window, demoMap, camera);
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
        Texture texture = new Texture("assets/images/map1.jpg");

        return new DemoMap(Mesh.buildMeshFromPolygon(poly, texture), shader, poly, GameConstants.MAP_BACKGROUND_LAYER, true);
    }
}
