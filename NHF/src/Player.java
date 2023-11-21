public class Player {
	private int numberOfPieces;
	private int NumberOfPiecesOnBoard;
	private Puck color;
	private boolean canLeap;

	public Player(Puck col, int numPieces) {
		color = col;
		numberOfPieces = numPieces;
	}

	public int getNumberOfPiecesOnBoard() {
		return NumberOfPiecesOnBoard;
	}

	public Puck getColor() {
		return color;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}
	public boolean getCanLeap() {
		return canLeap;
	}
	public void setCanLeap(boolean canLeap) {
		this.canLeap = canLeap;
	}
}
