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

    public static ArrayList<Die> getDice(int red, int blue, int black){
        ArrayList<Die> redDice = getDice("red", red);
        ArrayList<Die> blueDice = getDice("blue", blue);
        ArrayList<Die> blackDice = getDice("black", black);

        ArrayList<Die> combined = new ArrayList<>();
        combined.addAll(blackDice);
        combined.addAll(blueDice);
        combined.addAll(redDice);

        return combined;
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
            // TODO execption if an invalid color is tossed in
        }
        return returnDice;
    }

    private void rollPool(){
        for (Die die : this.currentPool){
            die.roll();
        }
        this.updatePool();
    }

    public void rerollDie(Die die){
        die.roll();
        updatePool();
    }

    public void rerollDie(int index){
        Die die = this.currentPool.get(index);
        rerollDie(die);
    }

    public void addDie(String color){
        Die newDie = getDice(color, 1).get(0);
        newDie.roll();
        this.currentPool.add(newDie);
        updatePool();
    }

    public void cancelDie(Die die){
        this.currentPool.remove(die);
        updatePool();
    }

    public void cancelDie(int index){
        this.currentPool.remove(index);
        updatePool();
    }

    public void setFace(Die die, DiceFacings newFacing){
        die.setToFace(newFacing);
        updatePool();
    }

    public void setFace(int index, DiceFacings newFacing){
        setFace(this.currentPool.get(index), newFacing);
    }

    private void updatePool(){
        int totalDamage = 0;
        for(Die die:this.currentPool){
            DiceFacings currentFace = (DiceFacings) die.getCurrentFace();
            totalDamage += currentFace.getDamage();
        }
        this.currentRolledDamage = totalDamage;
    }

    public int getCurrentRolledDamage() {
        return currentRolledDamage;
    }

    public ArrayList<Die> getCurrentPool() {
        return currentPool;
    }
}
