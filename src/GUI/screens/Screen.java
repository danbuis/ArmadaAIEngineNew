package GUI.screens;

import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.GameEngine.MouseInputHandler;
import BBDGameLibrary.OpenGL.Window;
import engine.ArmadaGame;
import engine.GameItemSorter;
import org.joml.Vector2d;

import java.util.ArrayList;

public class Screen implements ScreenWidget{
    private GameItemSorter gameItemSorter = new GameItemSorter();
    private Window window;
    float windowWidth;
    float windowHeight;
    public ArrayList<BBDTextLine> textBlocks = new ArrayList();
    public ArrayList<GameItem> clickables = new ArrayList();
    public ArmadaGame parent;
    public MouseInputHandler selector = new MouseInputHandler();

    public Screen(Window window, ArmadaGame parent){
        this.window = window;
        this.parent = parent;
        this.windowWidth = window.getWidth();
        this.windowHeight = window.getHeight();
    }

    public void addText(BBDTextLine text){
        textBlocks.add(text);
    }

    public ArrayList<BBDTextLine> getText(){
        return textBlocks;
    }

    public void addItems(GameItemSorter newItems){
        gameItemSorter.addItems(newItems);
    }

    public void addItem(GameItem2d newItem) {gameItemSorter.addItems(newItem);}

    public GameItemSorter getItems(){
        return this.gameItemSorter;
    }

    public void addClickableItems(ArrayList<GameItem2d> clickables){
        this.clickables.addAll(clickables);
    }

    public void addClickableText(BBDTextLine clickableText){
        this.clickables.addAll(clickableText.getTextItemList());
    }

    public void addClickableItem(GameItem2d clickable){
        this.clickables.add(clickable);
    }

    @Override
    public void handleClick(Vector2d mousePos, Window window) {

    }

    @Override
    public void update(float v, MouseInput mouseInput) {

    }
}
