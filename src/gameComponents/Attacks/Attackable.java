package gameComponents.Attacks;

import java.util.ArrayList;

/**
 * A interface describing the requried functions for objects that can participate in attacks.
 */
public interface Attackable {
    /**
     * Create an attackpool
     * @param target  target that is being attacked
     * @return initial attack pool
     */
    public AttackPool gatherAndRollDice(Attackable target);

    /**
     * Calculate how much damage is in the pool.  Calculation method depends on what the attacker and defender are and
     * any special circumstances that might arice
     * @param dicePool
     * @return total damage to be applied to the target
     */
    public int totalDamage(AttackPool dicePool);

    /**
     * Suffer damage from the attack.  Squadrons and ships suffer damage differently, and care about crits in different
     * ways.
     * @param totalDamge
     * @param crit
     */
    public void sufferDamage(int totalDamge, boolean crit);

    /**
     * Get a list of all valid attacks regardless of range and other criteria.
     * @return list of available valid attacks
     */
    public ArrayList<Attack> collectValidAttacks();
}
