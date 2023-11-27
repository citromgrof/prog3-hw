public class Player {
	private int numberOfPieces;
	private Puck color;
	private boolean canLeap;

	public Player(Puck col, int numPieces) {
		color = col;
		numberOfPieces = numPieces;
	}

	public boolean getCanLeap() {
		return canLeap;
	}


	public void setCanLeap(boolean canLeap) {
		this.canLeap = canLeap;
	}
}
