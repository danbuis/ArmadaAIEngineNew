package components;

import BBDGameLibrary.GameEngine.Die;
import java.util.ArrayList;

/*
 * A class to handle data regarding the attack pool of dice.  It assumes that the intiator of the attack will determine
 * what dice to roll, all this object handles is its own data and properties.  A number of functions are provided to
 * interact with the dice.  Dice are represented by a list of Die objects.
 */

public class AttackPool {
    public enum DiceSymbol {CRIT, HIT, ACCURACY}

    public enum DiceColor {RED, BLUE, BLACK;}

    private int currentRolledDamage;
    private ArrayList<ArmadaDie> currentPool;
    private boolean critEffect;


    /**
     * General constructor for an attack pool.
     * @param dicePool An ArrayList of Die objects.
     */
    public AttackPool(ArrayList<ArmadaDie> dicePool){
        this.currentPool = dicePool;
        this.rollPool();
    }

    /**
     * Typical helper method for creating a list of dice for a new pool.  Basically calls the monochrome function 3x.
     * @param red How many red dice are in the pool
     * @param blue How many blue dice are in the pool
     * @param black How many black dice are in the pool
     * @return List of new Die objects
     */
    public static ArrayList<ArmadaDie> getDice(int red, int blue, int black){
        ArrayList<ArmadaDie> redDice = getDice(DiceColor.RED, red);
        ArrayList<ArmadaDie> blueDice = getDice(DiceColor.BLUE, blue);
        ArrayList<ArmadaDie> blackDice = getDice(DiceColor.BLACK, black);

        ArrayList<ArmadaDie> combined = new ArrayList<>();
        combined.addAll(blackDice);
        combined.addAll(blueDice);
        combined.addAll(redDice);

        return combined;
    }

    /**
     * Helper method for making monochrome lists of dice for a new pool
     * @param color What color is the pool.
     * @param quantity How many dice are in the pool.
     * @return List of new Die objects
     */
    private static ArrayList<ArmadaDie> getDice(DiceColor color, int quantity){
        DiceFacings[] redFacings = {DiceFacings.HIT, DiceFacings.HIT, DiceFacings.DOUBLE_HIT, DiceFacings.CRIT, DiceFacings.CRIT, DiceFacings.ACCURACY, DiceFacings.BLANK, DiceFacings.BLANK};
        DiceFacings[] blueFacings = {DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.CRIT, DiceFacings.CRIT, DiceFacings.ACCURACY, DiceFacings.ACCURACY};
        DiceFacings[] blackFacings = {DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT, DiceFacings.HIT_CRIT, DiceFacings.HIT_CRIT, DiceFacings.BLANK, DiceFacings.BLANK};

        ArrayList<ArmadaDie> returnDice = new ArrayList<>();

        for(int i = 0; i < quantity; i++){
            if(color == DiceColor.RED){
                returnDice.add(new ArmadaDie<DiceFacings>(redFacings, color));
            }else if (color == DiceColor.BLUE){
                returnDice.add(new ArmadaDie<DiceFacings>(blueFacings, color));
            }else if (color == DiceColor.BLACK){
                returnDice.add(new ArmadaDie<DiceFacings>(blackFacings, color));
            }
        }
        return returnDice;
    }

    /**
     * Roll all the dice in the pool
     */
    private void rollPool(){
        for (Die die : this.currentPool){
            die.roll();
        }
        this.updatePool();
    }

    /**
     * Roll the die at the given index
     * @param index Index of the die to be rolled
     */
    public void rerollDie(int index){
        this.currentPool.get(index).roll();
        updatePool();
    }

    /**
     * Add a new die to the pool
     * @param color Color of the new die
     */
    public void addDie(DiceColor color){
        ArmadaDie newDie = getDice(color, 1).get(0);
        newDie.roll();
        this.currentPool.add(newDie);
        updatePool();
    }

    /**
     * Remove the die at the given index
     * @param index Index of the die to be removed
     */
    public void cancelDie(int index){
        this.currentPool.remove(index);
        updatePool();
    }

    /**
     * Set the die at the given index to a specific face
     * @param index Index of the die to be changed
     * @param newFacing Desired face to be changed to
     */
    public void setFace(int index, DiceFacings newFacing){
        this.currentPool.get(index).setToFace( newFacing);
        updatePool();
    }

    /**
     * Do some behind the scenes bookkeeping to keep internal state up to date.  Should be called after every die change
     * and modification.
     */
    private void updatePool(){
        int totalDamage = 0;
        for(Die die:this.currentPool){
            DiceFacings currentFace = (DiceFacings) die.getCurrentFace();
            totalDamage += currentFace.getDamage();
        }
        this.currentRolledDamage = totalDamage;

        this.critEffect = getSymbolCount(DiceSymbol.CRIT) != 0;
    }

    /**
     * Get the damage shown on the dice
     * @return Damage shown on the dice
     */
    public int getCurrentRolledDamage() {
        return currentRolledDamage;
    }

    /**
     * Get the count of a specific symbol on the dice
     * @param diceSymbol What symbol do you want counted
     * @return How many instances of that symbol in the dice pool
     */
    public int getSymbolCount(DiceSymbol diceSymbol){
        String symbolLabel = "";

        if (diceSymbol == DiceSymbol.HIT){ symbolLabel = "hit";}
        else if (diceSymbol == DiceSymbol.CRIT){ symbolLabel = "crit";}
        else if (diceSymbol == DiceSymbol.ACCURACY){ symbolLabel = "accuracy";}

        int count = 0;
        for(Die die:currentPool){
            DiceFacings currentFace = (DiceFacings) die.getCurrentFace();
            String label = currentFace.getLabel();
            int fromIndex = 0;
            while((fromIndex = label.indexOf(symbolLabel, fromIndex)) != -1) {
                count++;
                fromIndex++;
            }
        }

        return count;
    }

    public int countDiceOfColor(DiceColor color){
        int count = 0;
        for (ArmadaDie die: this.currentPool){
            if (die.getColor() == color){
                count++;
            }
        }
        return count;
    }

    /**
     * Get the number of dice in the pool
     * @return Number of dice in the pool
     */
    public int getPoolSize(){
        return currentPool.size();
    }

    /**
     * Get a summary of the current dice.  Use this to look at the dice and find the index of the die you want to modify
     * @return An array of the current dice facings.
     */
    public DiceFacings[] getCurrentDiceFacings(){
        DiceFacings[] currentDice = new DiceFacings[currentPool.size()];

        for (int i=0; i<currentPool.size(); i++){
            currentDice[i] = (DiceFacings) currentPool.get(i).getCurrentFace();
        }

        return currentDice;
    }

    /**
     * Is there a crit in the attack pool
     * @return crit in the attack pool
     */
    public boolean getCritEffect(){
        return critEffect;
    }

}
