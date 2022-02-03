package engine.forces;

import GUI.board.*;


import java.util.ArrayList;

public class Fleet {
    private ArrayList<ShipRenderer> ships;
    private ArrayList<SquadronRenderer> squadrons;

    public Fleet (ArrayList<ShipRenderer> ships, ArrayList<SquadronRenderer> squadrons){
        this.ships = ships;
        this.squadrons = squadrons;
    }

    public ShipRenderer getShip(int index){
        return ships.get(index);
    }

    public SquadronRenderer getSquadron(int index){
        return squadrons.get(index);
    }

    public int shipsLeft(){
        return ships.size();
    }

    public int squadronsLeft(){
        return squadrons.size();
    }
}
