package GUI.board;

import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.Geometry2d.BBDSegment;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.ShaderProgram;
import BBDGameLibrary.OpenGL.Texture;
import BBDGameLibrary.Utils.GeometryGenerators;
import BBDGameLibrary.Utils.ShaderPrograms;
import components.ship.Ship;
import components.ship.ShipSize;
import engine.GameConstants;
import engine.GameItemSorter;
import resources.SolidColorShaders;

import java.util.ArrayList;

/**
 * A class to render a ship object to the board.  This helps keep rendering logic separate from
 * business logic, enabling us to have cleaner code and unit tests.
 */
public class ShipRenderer {
    private Ship ship;

    private static final ShaderProgram SOLID = ShaderPrograms.buildSolidColorShader();


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
        GameItem2d cardboardItem = new GameItem2d(Mesh.buildMeshFromPolygon(cardboardPoly, null), SOLID, cardboardPoly, GameConstants.LAYER_SQUADRON_CARDBOARD, false);
        cardboardItem.addSolidColorUniform(SolidColorShaders.getSolidColor("black"));
        BBDPolygon plasticPoly = size.getPlastic();
        GameItem2d plasticBaseItem = new GameItem2d(Mesh.buildMeshFromPolygon(plasticPoly, null), SOLID, plasticPoly, GameConstants.LAYER_SQUADRON_PLASTIC, false);
        plasticBaseItem.addSolidColorUniform(SolidColorShaders.getSolidColor("white"));

        ShaderProgram shader = ShaderPrograms.TEXTURED_GENERIC;
        Texture texture = new Texture("assets/images/ships/"+ship.buildShipFileName()+".png");
        BBDPolygon shipPoly = GeometryGenerators.buildQuad(40, 80);
        GameItem2d shipGraphic = new GameItem2d(Mesh.buildMeshFromPolygon(shipPoly, texture), shader, shipPoly, GameConstants.LAYER_SQUADRON_GRAPHIC, false);

        ArrayList<GameItem2d> hullZoneDividers = buildHullZoneDividers();

        this.gameItems.addItems(shipGraphic);
        this.gameItems.addItems(cardboardItem);
        this.gameItems.addItems(plasticBaseItem);
        this.gameItems.addItems(hullZoneDividers);
    }

    private ArrayList<GameItem2d> buildHullZoneDividers(){
        BBDSegment[] segs = ship.getHullZoneLines();
        ArrayList<GameItem2d> returnList = new ArrayList<>();

        for (BBDSegment seg:segs){
            float currentAngle = seg.getStartPoint().angleToOtherPoint(seg.getEndPoint());
            BBDSegment offset1 = new BBDSegment(seg, GameConstants.HULLZONE_DIVIDER_WIDTH/2f, (currentAngle-(float)Math.PI/2));
            BBDSegment offset2 = new BBDSegment(seg, GameConstants.HULLZONE_DIVIDER_WIDTH/-2f, (currentAngle-(float)Math.PI/2));

            offset2.rotate((float)Math.PI);
            BBDPolygon line = new BBDPolygon(new BBDPoint[]{offset1.getStartPoint(), offset1.getEndPoint(), offset2.getStartPoint(), offset2.getEndPoint()});
            GameItem2d divider = new GameItem2d(Mesh.buildMeshFromPolygon(line, null), SOLID, line, GameConstants.LAYER_HULLZONE_DETAILS, false);
            divider.addSolidColorUniform(SolidColorShaders.getSolidColor(ship.getFaction().getLabel()));
            returnList.add(divider);
        }

        return returnList;
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
