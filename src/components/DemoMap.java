package components;

import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.ShaderProgram;
import BBDGameLibrary.OpenGL.Texture;
import BBDGameLibrary.Utils.GeometryGenerators;
import BBDGameLibrary.Utils.ShaderPrograms;
import engine.GameConstants;

/**
 * A simple class that holds the code for a demo sized map.  It extends GameItem2d, which itself implements GameComponent,
 * which you hopefully remember from ArmadaGame.class.  GameItem2d has a lot of features centered around using the BBDGeometry
 * library.  It has already implemented the required methods, so we only need to put the ones that we NEED here.  No need
 * for empty methods
 */
public class DemoMap {

    public final float topEdge = GameConstants.SHORT_BOARD_EDGE/2f;
    public final float bottomEdge = -GameConstants.SHORT_BOARD_EDGE/2f;
    public final float rightEdge = GameConstants.SHORT_BOARD_EDGE/2f;
    public final float leftEdge = -GameConstants.SHORT_BOARD_EDGE/2f;
    
    public final GameItem2d background;
    private BBDPolygon poly = GeometryGenerators.buildQuad(GameConstants.SHORT_BOARD_EDGE, GameConstants.SHORT_BOARD_EDGE);
    private ShaderProgram shader = ShaderPrograms.buildBasicTexturedShaderProgram();
    private Texture texture = new Texture("assets/images/maps/map1.jpg");
    public DemoMap() {
        background = new GameItem2d(Mesh.buildMeshFromPolygon(poly, texture), shader, poly, GameConstants.LAYER_MAP_BACKGROUND, true);
    }

}
