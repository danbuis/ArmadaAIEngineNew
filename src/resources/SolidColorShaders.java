package resources;

import java.util.HashMap;

public class SolidColorShaders {
    public static HashMap<String, float[]> solidShadervalues = new HashMap<String, float[]>();

    public void createSolidColor(String key, float[] input){
        solidShadervalues.put(key, input);
    }

    public float[] getSolidColor(String key){
        return solidShadervalues.get(key);
    }
}
