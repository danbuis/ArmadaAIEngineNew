package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.OpenGL.Window;
import GUI.board.ShipRenderer;
import GUI.board.SquadronRenderer;
import components.DemoMap;
import components.ship.Ship;
import components.squadrons.Squadron;
import engine.ArmadaGame;
import engine.GameConstants;
import org.joml.Vector2d;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
            SquadronRenderer temp;
            int currentCol = 0;
            int currentRow = 0;

            File folder = new File("assets/data/squadrons");
            File[] listOfFiles = folder.listFiles();

            for (File squadFile : listOfFiles){
                if (!squadFile.getName().equals("ship_template.json")) {
                    temp = new SquadronRenderer(new Squadron(squadFile.getName().replaceFirst("[.][^.]+$", "")));
                    if (currentCol == 10) {
                        currentRow++;
                        currentCol = 0;
                    }

                    temp.relocate(new BBDPoint(currentCol * 40, currentRow * 40));
                    currentCol++;
                    this.addItems(temp.getGameItems());
                }
            }

            ShipRenderer tempShip;
            currentCol = 0;
            currentRow = 0;
            folder = new File("assets/data/ships");
            listOfFiles = folder.listFiles();

            for (File shipFile : listOfFiles){
                if (!shipFile.getName().equals("ship_template.json")){
                    tempShip = new ShipRenderer(new Ship(shipFile.getName().replaceFirst("[.][^.]+$", "")));
                    if(currentCol == 5){
                        currentRow++;
                        currentCol=0;
                    }

                    tempShip.relocate(new BBDPoint(currentCol * -80 - 80, currentRow * 150));
                    currentCol++;
                    this.addItems(tempShip.getGameItems());
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
