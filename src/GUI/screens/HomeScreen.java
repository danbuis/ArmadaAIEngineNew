package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.OpenGL.Window;
import GUI.board.ShipRenderer;
import GUI.board.SquadronRenderer;
import components.ship.Ship;
import components.squadrons.Squadron;
import engine.ArmadaGame;
import engine.GameConstants;
import engine.forces.Fleet;
import org.joml.Vector2d;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HomeScreen extends Screen implements ScreenWidget{
    private BBDFont font;
    private BBDTextLine textTestGame;
    private BBDTextLine textCivilWarDemo;
    private BBDTextLine textCloneWarDemo;

    public HomeScreen(Window window, ArmadaGame parent){
        super(window, parent);
        try {
            font = new BBDFont("assets/text/Arial_Bold_White.bmp", "assets/text/Arial_Bold_White.csv");
        } catch(FileNotFoundException e){
            System.out.println("font file not found : " + e);
        }
        textTestGame = new BBDTextLine(font, 100, "Testing Space", 10);
        textCivilWarDemo = new BBDTextLine(font, 100, "Civil War Era Demo", 10);
        textCloneWarDemo = new BBDTextLine(font, 100, "Clone War Era Demo", 10);

        textTestGame.setPosition(-textTestGame.getTotalWidth()/2, (float) (windowHeight * 0.25));
        textCloneWarDemo.setPosition(-textCloneWarDemo.getTotalWidth()/2, 0);
        textCivilWarDemo.setPosition(-textCivilWarDemo.getTotalWidth()/2, (windowHeight * -0.25f));

        this.addText(textTestGame);
        this.addText(textCivilWarDemo);
        this.addText(textCloneWarDemo);

        this.addClickableText(textCloneWarDemo);
        this.addClickableText(textTestGame);
        this.addClickableText(textCivilWarDemo);
    }

    @Override
    public void handleClick(Vector2d mousePos, Window window) {
        System.out.println(mousePos);
        GameItem clickedItem = selector.selectItemByMouse(clickables, window, mousePos, parent.getCamera(), 0.001f);
        if (textTestGame.getTextItemList().contains(clickedItem)){
            parent.changeScreens(ScreenState.TEST, null);
        }else if (textCivilWarDemo.getTextItemList().contains(clickedItem)){
            parent.changeScreens(ScreenState.GAME_SMALL, buildCivilWarDemo());
        }else if (textCloneWarDemo.getTextItemList().contains(clickedItem)){
            parent.changeScreens(ScreenState.GAME_SMALL, buildCloneWarDemo());
        }
    }

    @Override
    public void update(float v, MouseInput mouseInput) {

    }

    public Fleet[] buildCloneWarDemo(){
        Fleet republic = null;
        Fleet separatist = null;
        try {
            ArrayList<ShipRenderer> republicShips = new ArrayList<>();
            ArrayList<SquadronRenderer> republicSquadrons = new ArrayList<>();
            republicShips.add(new ShipRenderer(new Ship("Acclamator_I_Star_Destroyer")));
            republicShips.get(republicShips.size()-1).relocate(new BBDPoint(0, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));
            republicShips.add(new ShipRenderer(new Ship("Consular_Charger_C70")));
            republicShips.get(republicShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 4f, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));
            republicShips.add(new ShipRenderer(new Ship("Consular_Charger_C70")));
            republicShips.get(republicShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -4f, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));

            republicSquadrons.add(new SquadronRenderer(new Squadron("V-19_Torrent")));
            republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/6f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
            republicSquadrons.add(new SquadronRenderer(new Squadron("V-19_Torrent")));
            republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/3f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
            republicSquadrons.add(new SquadronRenderer(new Squadron("V-19_Torrent")));
            republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-6f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
            republicSquadrons.add(new SquadronRenderer(new Squadron("V-19_Torrent")));
            republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-3f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));

            republic = new Fleet(republicShips, republicSquadrons);

            ArrayList<ShipRenderer> separatistShips = new ArrayList<>();
            ArrayList<SquadronRenderer> separatistSquadrons = new ArrayList<>();
            separatistShips.add(new ShipRenderer(new Ship("Munificent_Class_Comms_Frigate")));
            separatistShips.get(separatistShips.size()-1).relocate(new BBDPoint(0, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));
            separatistShips.add(new ShipRenderer(new Ship("Hardcell_Class_Battle_Refit")));
            separatistShips.get(separatistShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 4f, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));
            separatistShips.add(new ShipRenderer(new Ship("Hardcell_Class_Battle_Refit")));
            separatistShips.get(separatistShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -4f, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));

            separatistSquadrons.add(new SquadronRenderer(new Squadron("Vulture_Class_Droid_Fighter")));
            separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/6f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
            separatistSquadrons.add(new SquadronRenderer(new Squadron("Vulture_Class_Droid_Fighter")));
            separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/3f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
            separatistSquadrons.add(new SquadronRenderer(new Squadron("Vulture_Class_Droid_Fighter")));
            separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-6f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
            separatistSquadrons.add(new SquadronRenderer(new Squadron("Vulture_Class_Droid_Fighter")));
            separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-3f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));

            separatist = new Fleet(separatistShips, separatistSquadrons);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Fleet[] returnFleets = {republic, separatist};
        return returnFleets;
    }

    public Fleet[] buildCivilWarDemo(){
        Fleet rebel = null;
        Fleet empire = null;
        try {
            ArrayList<ShipRenderer> rebelShips = new ArrayList<>();
            ArrayList<SquadronRenderer> rebelSquadrons = new ArrayList<>();
            rebelShips.add(new ShipRenderer(new Ship("CR90_Corvette_A")));
            rebelShips.get(rebelShips.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -4f, GameConstants.SHORT_BOARD_EDGE / 2f - GameConstants.DISTANCE_2));
            rebelShips.add(new ShipRenderer(new Ship("Nebulon_B_Escort_Frigate")));
            rebelShips.get(rebelShips.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 4f, GameConstants.SHORT_BOARD_EDGE / 2f - GameConstants.DISTANCE_2));

            rebelSquadrons.add(new SquadronRenderer(new Squadron("X-wing")));
            rebelSquadrons.get(rebelSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 6f, GameConstants.SHORT_BOARD_EDGE / 2 - GameConstants.DISTANCE_3));
            rebelSquadrons.add(new SquadronRenderer(new Squadron("X-wing")));
            rebelSquadrons.get(rebelSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 3f, GameConstants.SHORT_BOARD_EDGE / 2 - GameConstants.DISTANCE_3));
            rebelSquadrons.add(new SquadronRenderer(new Squadron("X-wing")));
            rebelSquadrons.get(rebelSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -6f, GameConstants.SHORT_BOARD_EDGE / 2 - GameConstants.DISTANCE_3));
            rebelSquadrons.add(new SquadronRenderer(new Squadron("X-wing")));
            rebelSquadrons.get(rebelSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -3f, GameConstants.SHORT_BOARD_EDGE / 2 - GameConstants.DISTANCE_3));

            rebel = new Fleet(rebelShips, rebelSquadrons);

            ArrayList<ShipRenderer> empireShips = new ArrayList<>();
            ArrayList<SquadronRenderer> empireSquadrons = new ArrayList<>();
            empireShips.add(new ShipRenderer(new Ship("Victory_II-class_Star_Destroyer")));
            empireShips.get(empireShips.size()-1).relocate(new BBDPoint(0, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));

            empireSquadrons.add(new SquadronRenderer(new Squadron("TIE_Fighter")));
            empireSquadrons.get(empireSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 6, -GameConstants.SHORT_BOARD_EDGE / 2 + GameConstants.DISTANCE_2));
            empireSquadrons.add(new SquadronRenderer(new Squadron("TIE_Fighter")));
            empireSquadrons.get(empireSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 7f, -GameConstants.SHORT_BOARD_EDGE / 2 + GameConstants.DISTANCE_3));
            empireSquadrons.add(new SquadronRenderer(new Squadron("TIE_Fighter")));
            empireSquadrons.get(empireSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -7f, -GameConstants.SHORT_BOARD_EDGE / 2 + GameConstants.DISTANCE_3));
            empireSquadrons.add(new SquadronRenderer(new Squadron("TIE_Fighter")));
            empireSquadrons.get(empireSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -6f, -GameConstants.SHORT_BOARD_EDGE / 2 + GameConstants.DISTANCE_2));
            empireSquadrons.add(new SquadronRenderer(new Squadron("TIE_Fighter")));
            empireSquadrons.get(empireSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 5f, -GameConstants.SHORT_BOARD_EDGE / 2 + GameConstants.DISTANCE_3));
            empireSquadrons.add(new SquadronRenderer(new Squadron("TIE_Fighter")));
            empireSquadrons.get(empireSquadrons.size() - 1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -5f, -GameConstants.SHORT_BOARD_EDGE / 2 + GameConstants.DISTANCE_3));

            empire = new Fleet(empireShips, empireSquadrons);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Fleet[] returnFleets = {rebel, empire};
        return returnFleets;
    }
}
