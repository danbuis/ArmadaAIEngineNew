package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.OpenGL.Window;
import engine.ArmadaGame;
import org.joml.Vector2d;

import java.io.FileNotFoundException;

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
            System.out.println("Clicked a test char");
            parent.changeScreens(ScreenState.TEST);
        }else if (textCivilWarDemo.getTextItemList().contains(clickedItem)){
            System.out.println("Clicked a civil war char");
            parent.changeScreens(ScreenState.GAME_SMALL);
        }else if (textCloneWarDemo.getTextItemList().contains(clickedItem)){
            System.out.println("Clicked a clone char");
            parent.changeScreens(ScreenState.GAME_SMALL);
        }else{
            System.out.println("Clicked a NONE char");
        }
    }

    @Override
    public void update(float v, MouseInput mouseInput) {

    }
}
