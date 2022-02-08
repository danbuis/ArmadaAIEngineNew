package components;

import BBDGameLibrary.GameEngine.Die;


public class ArmadaDie<T> extends Die {
    private AttackPool.DiceColor color;

    public ArmadaDie(T[] facings, AttackPool.DiceColor color){
        super(facings);
        this.color = color;
    }

    public AttackPool.DiceColor getColor() {
        return color;
    }
}
