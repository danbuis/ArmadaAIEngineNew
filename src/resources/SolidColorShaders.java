package resources;

import java.util.HashMap;

public class SolidColorShaders {
    public static HashMap<String, float[]> solidShadervalues = new HashMap<String, float[]>();

    public static void initialize(){
        createSolidColor("black", new float[]{0f, 0f, 0f, 1f});
        createSolidColor("white", new float[]{1f, 1f, 1f, 1f});
    }

    public static void createSolidColor(String key, float[] input){
        solidShadervalues.put(key, input);
    }

    public static float[] getSolidColor(String key){
        return solidShadervalues.get(key);
    }
}
