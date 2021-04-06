package engine;

import BBDGameLibrary.GameEngine.GameItem2d;

import java.util.ArrayList;

public class GameItemSorter {

    private ArrayList<GameItem2d> items = new ArrayList<>();

    public void addItems(ArrayList<GameItem2d> items){
        for(GameItem2d item: items){
            insertItem(item);
        }
    }

    public void addItems(GameItem2d item){
        insertItem(item);
    }

    public void addItems(GameItemSorter sorter){
        for(GameItem2d item:sorter.items){
            insertItem(item);
        }
    }

    public int getItemCount(){
        return this.items.size();
    }

    public GameItem2d getItem(int index){
        return this.items.get(index);
    }

    private void insertItem(GameItem2d newItem){
        int insertIndex = -1;
        for (int i = 0; i<this.items.size(); i++){
            if (this.items.get(i).getLayer() > newItem.getLayer()){
                insertIndex = i;
                break;
            }
        }
        if(insertIndex == -1){
            this.items.add(newItem);
        }else {
            this.items.add(insertIndex, newItem);
        }
    }
}
