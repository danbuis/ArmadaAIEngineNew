import BBDGameLibrary.GameEngine.Engine;
import BBDGameLibrary.GameEngine.GameComponent;
import BBDGameLibrary.OpenGL.Window;
import engine.ArmadaGame;

public class ArmadaAIMain {

	public static void main(String[] args) {
		try {
			GameComponent gameLogic = new ArmadaGame();
			Engine gameEng = new Engine("Star Wars Armada AI",
					1800, 980, true, new Window.WindowOptions(), gameLogic);
			gameEng.run();
		} catch (Exception excp) {
			excp.printStackTrace();
			System.exit(-1);
		}
	}

}
