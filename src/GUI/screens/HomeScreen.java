package GUI.screens;

import BBDGameLibrary.GUI.BBDFont;
import BBDGameLibrary.GUI.BBDTextLine;
import BBDGameLibrary.OpenGL.Window;

import java.io.FileNotFoundException;

public class HomeScreen extends Screen {
    private BBDFont font;
    private BBDTextLine textTestGame;
    private BBDTextLine textCivilWarDemo;
    private BBDTextLine textCloneWarDemo;

    public HomeScreen(Window window){
        super(window);
        try {
            font = new BBDFont("assets/text/Arial_Bold_White.bmp", "assets/text/Arial_Bold_White.csv");
        } catch(FileNotFoundException e){
            System.out.println("font file not found : " + e);
        }
        textTestGame = new BBDTextLine(font, 100, "Testing Space", 10);
        textCivilWarDemo = new BBDTextLine(font, 100, "Civil War Era Demo", 10);
        textCloneWarDemo = new BBDTextLine(font, 100, "Clone War Era Demo", 10);

        textTestGame.setPosition(-textTestGame.getTotalWidth()/2, (float) (windowHeight * 0.25));
        textCivilWarDemo.setPosition(-textCivilWarDemo.getTotalWidth()/2, 0);
        textCloneWarDemo.setPosition(-textCloneWarDemo.getTotalWidth()/2, (windowHeight * -0.25f));

        this.addText(textTestGame);
        this.addText(textCivilWarDemo);
        this.addText(textCloneWarDemo);
    }
    
}
