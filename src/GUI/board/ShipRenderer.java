package GUI.board;

import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.Geometry2d.BBDGeometryHelpers;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.ShaderProgram;
import BBDGameLibrary.OpenGL.Texture;
import BBDGameLibrary.Utils.GeometryGenerators;
import BBDGameLibrary.Utils.ShaderPrograms;
import components.ship.Ship;
import components.ship.ShipSize;
import engine.GameConstants;
import engine.GameItemSorter;

/**
 * A class to render a ship object to the board.  This helps keep rendering logic separate from
 * business logic, enabling us to have cleaner code and unit tests.
 */
public class ShipRenderer {
    private Ship ship;

    private static final ShaderProgram WHITE_SOLID = ShaderPrograms.buildSolidColorShader("white");
    private static final ShaderProgram BLACK_SOLID = ShaderPrograms.buildSolidColorShader("black");

    private GameItemSorter gameItems = new GameItemSorter();

    public ShipRenderer(Ship ship){
        this.ship = ship;
        this.buildGameItems(ship);
    }

    /**
     * Build the GameItem objects to be used to render this object.
     */
    private void buildGameItems(Ship ship){
        ShipSize size = ship.getSize();
        
        BBDPolygon cardboardPoly = size.getCardboard();
        GameItem2d cardboardItem = new GameItem2d(Mesh.buildMeshFromPolygon(cardboardPoly, null), BLACK_SOLID, cardboardPoly, GameConstants.LAYER_SQUADRON_CARDBOARD, false);

        BBDPolygon plasticPoly = size.getPlastic();
        GameItem2d plasticBaseItem = new GameItem2d(Mesh.buildMeshFromPolygon(plasticPoly, null), WHITE_SOLID, plasticPoly, GameConstants.LAYER_SQUADRON_PLASTIC, false);

        ShaderProgram shader = ShaderPrograms.TEXTURED_GENERIC;
        Texture texture = new Texture("assets/images/ships/"+ship.buildShipFileName()+".png");
        BBDPolygon shipPoly = GeometryGenerators.buildQuad(40, 80);
        GameItem2d shipGraphic = new GameItem2d(Mesh.buildMeshFromPolygon(shipPoly, texture), shader, shipPoly, GameConstants.LAYER_SQUADRON_GRAPHIC, false);

        this.gameItems.addItems(shipGraphic);
        this.gameItems.addItems(cardboardItem);
        this.gameItems.addItems(plasticBaseItem);
    }

    /**
     * Root movement function.  All movement functions eventually lead to here.  Function is private because it is the one
     * that modifies items of the class.
     * @param newPoint new BBDPoint to use for the location of the squadron
     */
    private void moveNew(BBDPoint newPoint){
        ship.moveNew(newPoint);
        //move the visuals
        float newX = newPoint.getXLoc();
        float newY = newPoint.getYLoc();
        for(int i =0; i<gameItems.getItemCount(); i++){
            gameItems.getItem(i).setPosition(newX, newY, gameItems.getItem(i).getPosition().z);
        }
    }

    /**
     * Movement function for when we know the relative offset
     * @param deltaX change in X coordinate
     * @param deltaY change in Y coordinate
     */
    public void moveOffsets(float deltaX, float deltaY){
        BBDPoint newPoint = ship.getLocation();
        newPoint.translate(deltaX, deltaY);
        moveNew(newPoint);
    }

    /**
     * Movement function for when we know angle and distance
     * @param distance movement length
     * @param angle angle of movement
     */
    public void moveAngle(float distance, float angle){
        float deltaX = (float) (Math.cos(angle) * distance);
        float deltaY = (float) (Math.sin(angle) * distance);
        moveOffsets(deltaX, deltaY);
    }

    /**
     * A public facing function to feed into the root movement function.
     * @param newLocation new location for the squadron
     */
    public void relocate(BBDPoint newLocation){
        moveNew(newLocation);
    }

    public BBDPoint getLocation(){
        return ship.getLocation();
    }

    public GameItemSorter getGameItems() {
        return gameItems;
    }

}
