package gameComponents;

import org.joml.Vector2f;

public class HullZone {

    /*
    A hull zone is a collection of space on a ship that stores shields attack values and some location data.
    A hull zone can take shield damage without outside assistance
     */

    //Available enums for a ship facing. AUX arcs exist for huge ships, otherwise use the normal ones.
    public enum Facing {FRONT, LEFT, RIGHT, BACK, AUX_LEFT, AUX_RIGHT}

    //The named facing of the ship
    private Facing facing;

    //The anti-ship battery values
    private int redBattery;
    private int blueBattery;
    private int blackBattery;

    //The shield value of the hull zone
    private int shields;

    //The Line of sight offset relative to ship center for a HullZone
    private Vector2f LoSOffset;

    //The left/right origin offsets relative to ship center for a HullZone
    private Vector2f leftArcOffset;
    private Vector2f rightArcOffset;

    //The left right arc angles for a ship.
    private float leftArcAngle;
    private float rightArcAngle;

    //No null constructor

    //Constructor from string looks too complex to make an attempt with how the ship parser is laid out

    /**
     * Valued constructor
     * @param facing facing
     * @param redBattery red dice
     * @param blueBattery blue dice
     * @param blackBattery black dice
     * @param shields shields
     * @param LoSOffset .
     * @param leftArcOffset .
     * @param leftArcAngle .
     * @param rightArcOffset .
     * @param rightArcAngle .
     */

    public HullZone(Facing facing, int redBattery, int blueBattery,int blackBattery, int shields, Vector2f LoSOffset, Vector2f leftArcOffset, float leftArcAngle, Vector2f rightArcOffset, float rightArcAngle){
        this.facing = facing;
        this.redBattery = redBattery;
        this.blueBattery = blueBattery;
        this.blackBattery = blackBattery;
        this.shields = shields;
        this.LoSOffset = LoSOffset;
        this.leftArcOffset = leftArcOffset;
        this.leftArcAngle = leftArcAngle;
        this.rightArcAngle = rightArcAngle;
        this.rightArcOffset = rightArcOffset;
    }

    public int sufferDamage(int toTake) {
        if (toTake > shields) { //If we need to take more damage than shields remaining
            shields = 0;
            return (toTake - shields);
        } else if (toTake < shields) { //if we need to take less damage than shields remaining
            shields -= toTake;
        } else shields = 0; //if we reach this point we are taking exactly as much damage as shields remaining
        return 0; //in cases 2 and 3, all damage has been mitigated and nothing needs ot be suffered on hull, so this lives outside the if/else blocks
    }

    public Facing getFacing() {
        return facing;
    }

    public int getRedBattery() {
        return redBattery;
    }

    public int getBlueBattery() {
        return blueBattery;
    }

    public int getBlackBattery() {
        return blackBattery;
    }

    public int getShields() {
        return shields;
    }

    public Vector2f getLoSOffset() {
        return LoSOffset;
    }

    public Vector2f getLeftArcOffset() {
        return leftArcOffset;
    }

    public Vector2f getRightArcOffset() {
        return rightArcOffset;
    }

    public float getLeftArcAngle() {
        return leftArcAngle;
    }

    public float getRightArcAngle() {
        return rightArcAngle;
    }
}
