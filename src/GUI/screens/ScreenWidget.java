package GUI.screens;

import BBDGameLibrary.GameEngine.MouseInput;
import BBDGameLibrary.OpenGL.Window;
import org.joml.Vector2d;

public interface ScreenWidget {
    public void handleClick(Vector2d mousePos, Window window);
    public void update(float v, MouseInput mouseInput);
}
