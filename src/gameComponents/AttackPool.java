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
        int totalDamage = 0;
        for(Die die:this.currentPool){
            DiceFacings currentFace = (DiceFacings) die.getCurrentFace();
            totalDamage += currentFace.getDamage();
        }
        this.currentRolledDamage = totalDamage;
    }

    public static ArrayList<Die> getDice(String color, int quantity){
        DiceFacings[] redFacings = {DiceFacings.HIT, DiceFacings.HIT, DiceFacings.DOUBLE_HIT, DiceFacings.CRIT, DiceFacings.CRIT, DiceFacings.ACCURACY, DiceFacings.BLANK, DiceFacings.BLANK};
        DiceFacings[] blueFacings = {DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.CRIT, DiceFacings.CRIT, DiceFacings.ACCURACY, DiceFacings.ACCURACY};
        DiceFacings[] blackFacings = {DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT_CRIT, DiceFacings.HIT_CRIT, DiceFacings.BLANK, DiceFacings.BLANK};

        ArrayList<Die> returnDice = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            if(color.toLowerCase().equals("red")){
                returnDice.add(new Die<DiceFacings>(redFacings));
            }else if (color.toLowerCase().equals("blue")){
                returnDice.add(new Die<DiceFacings>(blueFacings));
            }else if (color.toLowerCase().equals("black")){
                returnDice.add(new Die<DiceFacings>(blackFacings));
            }
        }
        return returnDice;
    }
}
