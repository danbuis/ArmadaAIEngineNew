package engine;

import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.GameEngine.Camera;
import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.GameEngine.MouseInputHandler;
import BBDGameLibrary.OpenGL.Renderer;
import BBDGameLibrary.OpenGL.Window;
import GUI.screens.*;
import components.DemoMap;
import engine.forces.Fleet;
import engine.parsers.ParsingException;
import engine.parsers.ShipFactory;
import engine.parsers.SquadronFactory;
import org.joml.Vector2d;
import org.joml.Vector3f;

import java.io.FileNotFoundException;


/**ArmadaGame holds the root logic for the game.  Any object present in the game is eventually attached to here.  It
 * implements the GameComponent interface, which means that contractually it MUST implement the required 5 functions.
 */
public class ArmadaGame implements GameComponent {
    //An object to handle rendering to the screen
    private final Renderer renderer;
    //An object representing our POV in space looking at GameItems.  Essentially a container for some coordinates with a
    //a few niceties built in
    private final Camera camera;
    private boolean activePan = false;
    private int currentZoom = 920;
    private Vector3f cameraLocationStart = new Vector3f(0,0, 920);
    private Vector2d mousePanStart = null;
    private Vector3f cameraPanStart = null;
    MouseInputHandler inputHandler = new MouseInputHandler();
    Vector3f mouseProjection = null;
    Vector2d mouseLocationOnMap = null;
    private Window window;
    private Screen currentScreen;
    public ShipFactory shipFactory;
    public SquadronFactory squadronFactory;
    private ScreenState currentState = ScreenState.HOME;
    private Screen homeScreen;
    private Screen allThingsScreen;
    private Screen gameScreen;

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
        this.window = window;
        window.setZFar(GameConstants.ZOOM_MAXIMUM + 5);
        try {
            squadronFactory = new SquadronFactory();
            shipFactory = new ShipFactory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        }

        homeScreen = new HomeScreen(window, this);
        allThingsScreen = new AllThingsScreen(window, this);
        this.changeScreens(ScreenState.HOME, null);
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
        if (currentState != ScreenState.HOME) {
            //zoom logic
            camera.setPosition(camera.getPosition().x, camera.getPosition().y, this.currentZoom);
            double scroll = mouseInput.getScrollAmount();
            if (scroll < 0) {
                this.currentZoom = (int) Math.min(this.currentZoom * 1.07, GameConstants.ZOOM_MAXIMUM);

            } else if (scroll > 0) {
                this.currentZoom = (int) Math.max(this.currentZoom / 1.07, GameConstants.ZOOM_MINIMUM);
            }
            mouseInput.clearScrollInput();

            //pan logic
            if (mouseInput.isRightButtonPressed()) {
                mouseProjection = inputHandler.getMouseDir(window, mouseInput.getCurrentPos(), camera);
                mouseLocationOnMap = inputHandler.mouseLocationOnPlane(camera, mouseProjection, 0);
                if (!activePan) {
                    activePan = true;
                    mousePanStart = mouseLocationOnMap;
                    cameraPanStart = camera.getPosition();
                } else {
                    float deltaX = (float) (mousePanStart.x - mouseLocationOnMap.x);
                    float deltaY = (float) (mousePanStart.y - mouseLocationOnMap.y);

                    camera.setPosition(cameraPanStart.x + deltaX, cameraPanStart.y + deltaY, camera.getPosition().z);
                }
            } else {
                activePan = false;
            }
        }

        if (mouseInput.isLeftButtonPressed()) {
            System.out.println("left button pressed");
            currentScreen.handleClick(mouseInput.getCurrentPos(), this.window);
        }
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
        for(int i = 0; i < currentScreen.getItems().getItemCount(); i++){
            renderer.renderItem(window, currentScreen.getItems().getItem(i), camera);
        }
        for(BBDTextLine text: currentScreen.getText()){
            text.renderTextLine(window, renderer, camera);
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

    public void changeScreens(ScreenState newState, Fleet[] fleets){
        if (newState == ScreenState.HOME){
            this.currentState = newState;
            camera.setPosition(cameraLocationStart.x, cameraLocationStart.y, cameraLocationStart.z);
            this.currentScreen = this.homeScreen;
        }
        if (newState == ScreenState.TEST){
            this.currentState = newState;
            camera.setPosition(cameraLocationStart.x, cameraLocationStart.y, cameraLocationStart.z);
            this.currentScreen = this.allThingsScreen;
        }
        if (newState == ScreenState.GAME_SMALL){
            this.currentState = newState;
            camera.setPosition(cameraLocationStart.x, cameraLocationStart.y, cameraLocationStart.z);
            gameScreen = new GameScreen(window, this, new DemoMap(), fleets);
            this.currentScreen = this.gameScreen;
        }
    }

    public Camera getCamera(){
        return camera;
    }
}
