import BBDGameLibrary.GameEngine.Engine;
import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.OpenGL.Window;
import engine.ArmadaGame;
import resources.SolidColorShaders;

public class ArmadaAIMain {

	public static void main(String[] args) {
		try {
			GameComponent gameLogic = new ArmadaGame();
			SolidColorShaders.initialize();
			Engine gameEng = new Engine("Star Wars Armada AI",
					1800, 980, true, new Window.WindowOptions(), gameLogic);
			gameEng.run();
		} catch (Exception excp) {
			excp.printStackTrace();
			System.exit(-1);
		}
	}

}
