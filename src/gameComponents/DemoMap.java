package gameComponents;

import BBDGameLibrary.GameEngine.GameItem2d;
import BBDGameLibrary.Geometry2d.BBDPolygon;
import BBDGameLibrary.OpenGL.Mesh;
import BBDGameLibrary.OpenGL.ShaderProgram;
import org.joml.Matrix4f;

/**
 * A simple class that holds the code for a demo sized map.  It extends GameItem2d, which itself implements GameComponent,
 * which you hopefully remember from ArmadaGame.class.  GameItem2d has a lot of features centered around using the BBDGeometry
 * library.  It has already implemented the required methods, so we only need to put the ones that we NEED here.  No need
 * for empty methods
 */
public class DemoMap extends GameItem2d {

    public DemoMap(Mesh mesh, ShaderProgram shaderProgram, BBDPolygon shape, int layer, boolean shapeInteracts) {
        super(mesh, shaderProgram, shape, layer, shapeInteracts);
    }

    /**
     * Populate the shader uniforms for this object.  Only the first 2 are in by default, but since we are adding a third,
     * we need to put them in.
     * @param projectionMatrix
     * @param worldMatrix
     */
    @Override
    public void setUniforms(Matrix4f projectionMatrix, Matrix4f worldMatrix) {
        this.shader.setUniform("projectionMatrix", projectionMatrix);
        this.shader.setUniform("worldMatrix", worldMatrix);
        this.shader.setUniform("texture_sampler", 0);
    }
}
