package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.OpenGL.Window;
import GUI.board.ShipRenderer;
import GUI.board.SquadronRenderer;
import components.DemoMap;
import engine.ArmadaGame;
import engine.GameConstants;
import engine.parsers.ParsingException;
import engine.parsers.ShipFactory;
import engine.parsers.SquadronFactory;
import org.joml.Vector2d;

import java.io.FileNotFoundException;

/**ArmadaGame holds the root logic for the game.  Any object present in the game is eventually attached to here.  It
 * implements the GameComponent interface, which means that contractually it MUST implement the required 5 functions.
 */
public class AllThingsScreen extends Screen implements ScreenWidget{
    private BBDFont font;
    private DemoMap demoMap;

    /**
     * A basic constructor.  Sets up the items only need one instance that is then shared between objects
     */
    public AllThingsScreen(Window window, ArmadaGame parent) {
        super(window, parent);
        try {
            font = new BBDFont("assets/text/Arial_Bold_White.bmp", "assets/text/Arial_Bold_White.csv");
        } catch(FileNotFoundException e){
            System.out.println("font file not found : " + e);
        }
        demoMap = new DemoMap();
        this.addItem(demoMap.background);
        window.setZFar(GameConstants.ZOOM_MAXIMUM + 5);
        //Temporary - just list out all the squadrons and show them all

        try {
            SquadronFactory squadronFactory = new SquadronFactory();
            SquadronRenderer temp;
            int currentCol = 0;
            int currentRow = 0;
            for (String squadronName : squadronFactory.getSquadronTypes()){
                temp = new SquadronRenderer(squadronFactory.getSquadron(squadronName));
                if(currentCol == 10){
                    currentRow++;
                    currentCol=0;
                }

                temp.relocate(new BBDPoint(currentCol * 40, currentRow * 40));
                currentCol++;
                this.addItems(temp.getGameItems());
            }

            ShipFactory shipFactory = new ShipFactory();
            ShipRenderer tempShip;
            currentCol = 0;
            currentRow = 0;
            for (String shipName : shipFactory.getShipTypes()){
                tempShip = new ShipRenderer(shipFactory.getShip(shipName));
                if(currentCol == 5){
                    currentRow++;
                    currentCol=0;
                }

                tempShip.relocate(new BBDPoint(currentCol * -80 - 80, currentRow * 150));
                currentCol++;
                this.addItems(tempShip.getGameItems());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleClick(Vector2d mousePos, Window window) {

    }

    @Override
    public void update(float v, MouseInput mouseInput) {

    }
}
