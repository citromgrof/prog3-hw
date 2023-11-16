/*
* A malom tábláján egy helyet reprezantáló osztály.
* @param occupiedByPlayer A helyen lévő játékos(fekete vagy fehér)
* @param isOccupied Szabad vagy foglalt a hely
* @param positionIndex A hely indexe
* @param neighboringPositions Egy pozíció szomszédai legálább 2 és legfeljebb 4 szomszédja lehet egy helynek.
*/
public class Position {
	private Puck occupiedByPlayer;
	private boolean isOccupied;
	private int positionIndex;

	//Inicializáláskor egy pozíció sem foglalt.
	public Position(int position) {
		occupiedByPlayer=Puck.NONE;
		isOccupied=false;
		positionIndex=position;
	}
	public Puck getOccupiedByPlayer() {
		return occupiedByPlayer;
	}
	public boolean isOccupied(){
		return isOccupied;
	}
	public int getPositionIndex(){
		return positionIndex;
	}
	public void setAsOccupied(Puck player) {
		occupiedByPlayer=player;
		isOccupied=true;
	}
	public void setAsUnoccupied() {
		occupiedByPlayer=Puck.NONE;
		isOccupied=false;
	}

}
