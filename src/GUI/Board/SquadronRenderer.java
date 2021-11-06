package GUI.Board;

import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.ShaderProgram;
import BBDGameLibrary.OpenGL.Texture;
import BBDGameLibrary.Utils.GeometryGenerators;
import BBDGameLibrary.Utils.ShaderPrograms;
import engine.GameConstants;
import engine.GameItemSorter;
import gameComponents.Squadrons.Squadron;

/**
 * A class to render a squadron object to the board.  This helps keep rendering logic separate from
 * business logic, enabling us to have cleaner code and unit tests.
 */
public class SquadronRenderer {

    private Squadron squadron;

    /**
     * Static objects to build meshes.  Long term these will be moved somewhere else once rendering is separated from the Squadron object.
     */
    private static final BBDPolygon plasticBase = GeometryGenerators.createNGon(new BBDPoint(0,0), GameConstants.SQUADRON_PLASTIC_RADIUS, 100);
    private static final float[] plasticPositions = Mesh.buildMeshPositions(plasticBase);
    private static final float[] plasticTex = Mesh.buildTextureCoordinates(plasticBase);
    private static final int[] plasticIndices = Mesh.buildIndices(plasticBase);

    private static final BBDPolygon cardboard = GeometryGenerators.createNGon(new BBDPoint(0,0), GameConstants.SQUADRON_CARDBOARD_RADIUS, 100);
    private static final float[] cardboardPositions = Mesh.buildMeshPositions(cardboard);
    private static final float[] cardboardTex = Mesh.buildTextureCoordinates(cardboard);
    private static final int[] cardboardIndices = Mesh.buildIndices(cardboard);

    private static final ShaderProgram WHITE_SOLID = ShaderPrograms.buildSolidColorShader("white");
    private static final ShaderProgram BLACK_SOLID = ShaderPrograms.buildSolidColorShader("black");

    private GameItemSorter gameItems = new GameItemSorter();


    public SquadronRenderer(Squadron squadron){
        this.squadron = squadron;
        this.buildGameItems();
    }

    /**
     * Build the GameItem objects to be used to render this object.
     */
    private void buildGameItems(){
        GameItem2d plasticBaseItem = new GameItem2d(new Mesh(plasticPositions, plasticTex, plasticIndices, null), WHITE_SOLID, plasticBase, GameConstants.LAYER_SQUADRON_PLASTIC, false);
        GameItem2d cardboardItem = new GameItem2d(new Mesh(cardboardPositions, cardboardTex, cardboardIndices, null), BLACK_SOLID, cardboard, GameConstants.LAYER_SQUADRON_CARDBOARD, false);

        BBDPolygon poly = GeometryGenerators.buildQuad(20, 20);
        ShaderProgram shader = ShaderPrograms.TEXTURED_GENERIC;
        Texture texture = new Texture("assets/images/squadrons/squad_"+squadron.buildSquadFileName());
        GameItem2d squadronGraphic = new GameItem2d(Mesh.buildMeshFromPolygon(poly, texture), shader, poly, GameConstants.LAYER_SQUADRON_GRAPHIC, false);
        this.gameItems.addItems(squadronGraphic);
        this.gameItems.addItems(cardboardItem);
        this.gameItems.addItems(plasticBaseItem);
    }



    public GameItemSorter getGameItems() {
        return gameItems;
    }


}
