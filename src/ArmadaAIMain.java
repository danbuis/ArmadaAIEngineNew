import BBDGameLibrary.GameEngine.Engine;
import BBDGameLibrary.GameEngine.GameComponent;
import engine.ArmadaGame;

public class ArmadaAIMain {

	public static void main(String[] args) {
		try {
			GameComponent gameLogic = new ArmadaGame();
			Engine gameEng = new Engine("Star Wars Aramda AI",
					1800, 980, true, gameLogic);
			gameEng.run();
		} catch (Exception excp) {
			excp.printStackTrace();
			System.exit(-1);
		}
	}

}