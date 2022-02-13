package engine.forces;

import resources.SolidColorShaders;

public enum Faction {

    IMPERIAL("Imperial",51,166,63),
    REBEL("Rebel",243,79,50),
    SEPARATIST("Separatist",204,108,21),
    REPUBLIC("Republic",64,142,230);

    private final String label;
    private final float[] colorData;

    Faction(String label, int red, int green, int blue){
        this.label = label;
        this.colorData = new float[]{red, green, blue, 1f};
        SolidColorShaders.createSolidColor(label, colorData);
    }

    public static Faction getFaction(String label){
        for(Faction faction:Faction.values()){
            if (label.equals(faction.label)){
                return faction;
            }
        }
        return null;
    }
}
