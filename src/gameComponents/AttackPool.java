package gameComponents;

import BBDGameLibrary.GameEngine.Die;

import java.util.ArrayList;

public class AttackPool {
    private boolean spentBrace = false;
    private boolean spentEvade = false;
    private boolean spentContain = false;
    private boolean spentScatter = false;
    private boolean spentRedirect = false;
    private int currentRolledDamage;
    private ArrayList<Die> currentPool;


    public AttackPool(ArrayList<Die> dicePool){
        this.currentPool = dicePool;
        this.rollPool();
    }

    private void rollPool(){
        for (Die die : this.currentPool){
            die.roll();
        }
        this.updatePool();
    }

    public void rerollDie(){

    }

    public void addDie(){

    }

    public void cancelDie(){

    }

    private void updatePool(){
        //TODO stuff to update rolled damage and any other housekeeping things.
    }
}
