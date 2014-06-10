package gameserver.server.executors;

public abstract class GameController {
	
	private ModusEnum modus;
	
	public GameController() {}

	public ModusEnum getModus() {
		return modus;
	}

	public void setModus(ModusEnum modus) {
		this.modus = modus;
	}
	
}
