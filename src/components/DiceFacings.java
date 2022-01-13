package components;

public enum DiceFacings {

    HIT("hit", 1, false),
    CRIT("crit", 1, true),
    DOUBLE_HIT("hit hit", 2, false),
    HIT_CRIT("hit crit", 2, true),
    BLANK("blank", 0, false),
    ACCURACY("accuracy", 0, false);
    

    private final String label;
    private final int damage;
    private final boolean critical;

    DiceFacings(String label, int damage, boolean critical){
        this.label = label;
        this.damage = damage;
        this.critical = critical;
    }

    public int getDamage(){
        return this.damage;
    }

    public boolean isCritical(){
        return this.critical;
    }

    public String getLabel(){
        return this.label;
    }
}
