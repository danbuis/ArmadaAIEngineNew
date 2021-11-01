import BBDGameLibrary.GameEngine.GameItem;
import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.Window;
import BBDGameLibrary.TestUtils;
import engine.GameItemSorter;
import engine.parsers.ParsingException;
import engine.parsers.SquadronFactory;
import gameComponents.Squadrons.Squadron;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameItemSorterTests {
    Window window = null;
    boolean windowInit = false;
    public void initWindow(){
        window = new Window("test", 5, 5, true, new Window.WindowOptions());
        window.init();
    }

    public BBDPolygon buildSquare() {
        BBDPoint point1 = new BBDPoint(1, 1);
        BBDPoint point2 = new BBDPoint(1, -1);
        BBDPoint point3 = new BBDPoint(-1, -1);
        BBDPoint point4 = new BBDPoint(-1, 1);

        ArrayList<BBDPoint> points = new ArrayList<>(Arrays.asList(point1, point2, point3, point4));

        return new BBDPolygon(points);
    }

    public GameItem2d buildItem(int layer){
        BBDPolygon poly = TestUtils.buildSquare();
        Mesh mesh = Mesh.buildMeshFromPolygon(poly, null);

        GameItem2d item = new GameItem2d(mesh, null, poly, layer, true);
        return item;
    }

    @Test
    public void testBuildList(){
        if(!windowInit){
            initWindow();
        }

        GameItem2d item1 = buildItem(1);
        GameItem2d item2 = buildItem(2);
        GameItem2d item3 = buildItem(3);

        ArrayList<GameItem2d> itemList = new ArrayList<GameItem2d>();
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item1);

        GameItemSorter sorter = new GameItemSorter();
        sorter.addItems(itemList);

        //the items should be rearranged by layer, with the bigger numbers first
        assertEquals(item1, sorter.getItem(0));
        assertEquals(item2, sorter.getItem(1));
        assertEquals(item3, sorter.getItem(2));
    }

    @Test
    public void testBuildSorter(){
        if(!windowInit){
            initWindow();
        }

        GameItem2d item1 = buildItem(1);
        GameItem2d item2 = buildItem(2);
        GameItem2d item3 = buildItem(3);

        ArrayList<GameItem2d> itemList = new ArrayList<GameItem2d>();
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item1);

        GameItemSorter sorter = new GameItemSorter();
        sorter.addItems(itemList);
        //borrowed from above test to build a sorter ^^^^^^

        GameItemSorter newSorter = new GameItemSorter();
        newSorter.addItems(sorter);
        //the items should be rearranged by layer, with the bigger numbers first
        assertEquals(item1, sorter.getItem(0));
        assertEquals(item2, sorter.getItem(1));
        assertEquals(item3, sorter.getItem(2));
    }

    @Test
    public void testAddItem(){
        if(!windowInit){
            initWindow();
        }

        GameItem2d item1 = buildItem(1);
        GameItem2d item2 = buildItem(2);
        GameItem2d item4 = buildItem(4);

        ArrayList<GameItem2d> itemList = new ArrayList<GameItem2d>();
        itemList.add(item2);
        itemList.add(item4);
        itemList.add(item1);

        GameItemSorter sorter = new GameItemSorter();
        sorter.addItems(itemList);

        GameItem2d item3 = buildItem(3);
        sorter.addItems(item3);

        assertEquals(item3, sorter.getItem(2));
    }

    @Test
    public void testItemCount(){
        if(!windowInit){
            initWindow();
        }

        GameItem2d item1 = buildItem(1);
        GameItem2d item2 = buildItem(2);
        GameItem2d item4 = buildItem(4);

        ArrayList<GameItem2d> itemList = new ArrayList<GameItem2d>();
        itemList.add(item2);
        itemList.add(item4);
        itemList.add(item1);

        GameItemSorter sorter = new GameItemSorter();
        sorter.addItems(itemList);
        assertEquals(3, sorter.getItemCount());

        GameItem2d item3 = buildItem(3);
        sorter.addItems(item3);

        assertEquals(4, sorter.getItemCount());
    }
}
