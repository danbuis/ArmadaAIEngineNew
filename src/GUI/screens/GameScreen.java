package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.OpenGL.Window;
import components.DemoMap;
import engine.ArmadaGame;
import engine.GameConstants;
import engine.forces.Fleet;
import org.joml.Vector2d;

import java.io.FileNotFoundException;

public class GameScreen extends Screen implements ScreenWidget{
    private BBDFont font;
    DemoMap board;
    BBDTextLine textMenu;

    public GameScreen(Window window, ArmadaGame parent, DemoMap board, Fleet[] fleets) {
        super(window, parent);
        try {
            font = new BBDFont("assets/text/Arial_Bold_White.bmp", "assets/text/Arial_Bold_White.csv");
        } catch(FileNotFoundException e){
            System.out.println("font file not found : " + e);
        }

        textMenu = new BBDTextLine(font, 60,"MENU", 100);
        textMenu.setPosition(-460,-500);
        this.addText(textMenu);
        this.addClickableText(textMenu);

        this.board = board;
        this.addItem(board.background);
        window.setZFar(GameConstants.ZOOM_MAXIMUM + 5);

        for (Fleet fleet: fleets){
            for (int i = 0; i<fleet.shipsLeft(); i++){
                this.addItems(fleet.getShip(i).getGameItems());
            }
            for (int j = 0; j<fleet.squadronsLeft(); j++){
                this.addItems(fleet.getSquadron(j).getGameItems());
            }
        }
    }

    @Override
    public void handleClick(Vector2d mousePos, Window window) {
        GameItem clickedItem = selector.selectItemByMouse(clickables, window, mousePos, parent.getCamera(), 0.001f);
        if (textMenu.getTextItemList().contains(clickedItem)){
            parent.changeScreens(ScreenState.HOME, null);
        }
    }

    @Override
    public void update(float v, MouseInput mouseInput) {

    }
}
