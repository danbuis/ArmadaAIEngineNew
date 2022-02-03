package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.OpenGL.Window;
import GUI.board.ShipRenderer;
import GUI.board.SquadronRenderer;
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
        ArrayList<ShipRenderer> republicShips = new ArrayList<>();
        ArrayList<SquadronRenderer> republicSquadrons = new ArrayList<>();
        republicShips.add(new ShipRenderer(parent.shipFactory.getShip("Acclamator I-class Assault Ship")));
        republicShips.get(republicShips.size()-1).relocate(new BBDPoint(0, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));
        republicShips.add(new ShipRenderer(parent.shipFactory.getShip("Consular Charger C70")));
        republicShips.get(republicShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 4f, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));
        republicShips.add(new ShipRenderer(parent.shipFactory.getShip("Consular Charger C70")));
        republicShips.get(republicShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -4f, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));

        republicSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("V-19 Torrent")));
        republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/6f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
        republicSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("V-19 Torrent")));
        republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/3f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
        republicSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("V-19 Torrent")));
        republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-6f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
        republicSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("V-19 Torrent")));
        republicSquadrons.get(republicSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-3f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));

        Fleet republic = new Fleet(republicShips, republicSquadrons);

        ArrayList<ShipRenderer> separatistShips = new ArrayList<>();
        ArrayList<SquadronRenderer> separatistSquadrons = new ArrayList<>();
        separatistShips.add(new ShipRenderer(parent.shipFactory.getShip("Munificent-class Comms Frigate")));
        separatistShips.get(separatistShips.size()-1).relocate(new BBDPoint(0, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));
        separatistShips.add(new ShipRenderer(parent.shipFactory.getShip("Hardcell-class Battle Refit")));
        separatistShips.get(separatistShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 4f, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));
        separatistShips.add(new ShipRenderer(parent.shipFactory.getShip("Hardcell-class Battle Refit")));
        separatistShips.get(separatistShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -4f, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));

        separatistSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("Vulture-class Droid Fighter")));
        separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/6f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
        separatistSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("Vulture-class Droid Fighter")));
        separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/3f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
        separatistSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("Vulture-class Droid Fighter")));
        separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-6f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
        separatistSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("Vulture-class Droid Fighter")));
        separatistSquadrons.get(separatistSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-3f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));

        Fleet separatist = new Fleet(separatistShips, separatistSquadrons);

        Fleet[] returnFleets = {republic, separatist};
        return returnFleets;
    }

    public Fleet[] buildCivilWarDemo(){
        ArrayList<ShipRenderer> rebelShips = new ArrayList<>();
        ArrayList<SquadronRenderer> rebelSquadrons = new ArrayList<>();
        rebelShips.add(new ShipRenderer(parent.shipFactory.getShip("CR90 Corvette A")));
        rebelShips.get(rebelShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / -4f, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));
        rebelShips.add(new ShipRenderer(parent.shipFactory.getShip("Nebulon B Escort Frigate")));
        rebelShips.get(rebelShips.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE / 4f, GameConstants.SHORT_BOARD_EDGE/2f - GameConstants.DISTANCE_2));

        rebelSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("X-wing")));
        rebelSquadrons.get(rebelSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/6f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
        rebelSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("X-wing")));
        rebelSquadrons.get(rebelSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/3f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
        rebelSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("X-wing")));
        rebelSquadrons.get(rebelSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-6f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));
        rebelSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("X-wing")));
        rebelSquadrons.get(rebelSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-3f, GameConstants.SHORT_BOARD_EDGE/2 - GameConstants.DISTANCE_3));

        Fleet rebel= new Fleet(rebelShips, rebelSquadrons);

        ArrayList<ShipRenderer> empireShips = new ArrayList<>();
        ArrayList<SquadronRenderer> empireSquadrons = new ArrayList<>();
        empireShips.add(new ShipRenderer(parent.shipFactory.getShip("Victory II-class Star Destroyer")));
        empireShips.get(empireShips.size()-1).relocate(new BBDPoint(0, -GameConstants.SHORT_BOARD_EDGE/2f + GameConstants.DISTANCE_2));

        empireSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("TIE Fighter")));
        empireSquadrons.get(empireSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/6, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_2));
        empireSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("TIE Fighter")));
        empireSquadrons.get(empireSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/7f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
        empireSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("TIE Fighter")));
        empireSquadrons.get(empireSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-7f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
        empireSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("TIE Fighter")));
        empireSquadrons.get(empireSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-6f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_2));
        empireSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("TIE Fighter")));
        empireSquadrons.get(empireSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/5f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));
        empireSquadrons.add(new SquadronRenderer(parent.squadronFactory.getSquadron("TIE Fighter")));
        empireSquadrons.get(empireSquadrons.size()-1).relocate(new BBDPoint(GameConstants.SHORT_BOARD_EDGE/-5f, -GameConstants.SHORT_BOARD_EDGE/2 + GameConstants.DISTANCE_3));

        Fleet empire = new Fleet(empireShips, empireSquadrons);

        Fleet[] returnFleets = {rebel, empire};
        return returnFleets;
    }
}
