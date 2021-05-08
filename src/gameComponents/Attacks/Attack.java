package gameComponents.Attacks;

/**
 * Class to hold information about an attack action and all related data.
 */
public class Attack {
    public final Attackable attacker;
    public final Attackable defender;
    public AttackPool attackPool = null;

    public Attack(Attackable attacker, Attackable defender){
        this.attacker = attacker;
        this.defender = defender;
    }

    public void setInitialAttackPool(AttackPool newPool){
        if (this.attackPool == null){
            this.attackPool = null;
        }
    }
}
