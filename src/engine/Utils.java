package engine;

import BBDGameLibrary.Geometry2d.BBDPoint;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.ShaderProgram;

import java.util.ArrayList;

/**
 * A class to hold several utility functions so that we don't have to have the same function in several places.  All functions
 * should be static so that we don't need to create an object to use them.
 */
public class Utils {

    /**
     * Creates a ShaderProgram, which is essentially a set of instructions to the graphics card on how to render vertices.
     * In this case it takes in a shader to tell it how to handle 3d data points, a shader to tell it how to determine what color
     * to draw for each pixel, and then attaches them to the program.  Then it creates "uniforms" which are basically
     * variables for shaders.  The first 2 are for rendering vertices in the right spot, the third one is for getting the
     * texture put on.  You can make your own shader programs with their own uniforms, but you should probably use this
     * one as a base.
     * @return
     */
    public static ShaderProgram buildBasicTexturedShaderProgram(){
        ShaderProgram returnProgram = null;
        try {
            returnProgram = new ShaderProgram();

            //create and attach shaders
            returnProgram.createVertexShader(BBDGameLibrary.OpenGL.Utils.loadShaderScript("/shaders/vertex.vs"));
            returnProgram.createFragmentShader(BBDGameLibrary.OpenGL.Utils.loadShaderScript("/shaders/fragment.fs"));

            //give the shader program an id
            returnProgram.link();

            // Create uniforms for world and projection matrices and texture
            returnProgram.createUniform("projectionMatrix");
            returnProgram.createUniform("modelViewMatrix");
            returnProgram.createUniform("texture_sampler");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnProgram;
    }

    /**
     * Does what it says on the tin.  Builds a rectangle of the given dimensions centered on the origin.
     * @param width width of the quad
     * @param height height of the quad
     * @return a polygon of the given dimesnions centered at 0,0
     */
    public static BBDPolygon buildQuad(float width, float height) {
        ArrayList<BBDPoint> returnList = new ArrayList<>();
        returnList.add(new BBDPoint(width / 2, height / 2));
        returnList.add(new BBDPoint(-width / 2, height / 2));
        returnList.add(new BBDPoint(-width / 2, -height / 2));
        returnList.add(new BBDPoint(width / 2, -height / 2));
        return new BBDPolygon(returnList);
    }
}
