package GUI.screens;

import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.OpenGL.Window;
import engine.GameItemSorter;

import java.util.ArrayList;

public class Screen {
    private GameItemSorter gameItemSorter = new GameItemSorter();
    private Window window;
    float windowWidth;
    float windowHeight;
    private ArrayList<BBDTextLine> textBlocks = new ArrayList();

    public Screen(Window window){
        this.window = window;
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

    public GameItemSorter getItems(){
        return this.gameItemSorter;
    }

}
