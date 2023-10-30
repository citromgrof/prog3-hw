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
	private int[] neighboringPositions;
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
	public int[] getNeighboringPositions(){
		return neighboringPositions;
	}
	public void setNeighboringPositions(int pos1,int pos2){
		neighboringPositions=new int[2];
		neighboringPositions[0]=pos1;
		neighboringPositions[1]=pos2;
	}
	public void setNeighboringPositions(int pos1,int pos2,int pos3){
		neighboringPositions=new int[3];
		neighboringPositions[0]=pos1;
		neighboringPositions[1]=pos2;
		neighboringPositions[2]=pos3;
	}
	public void setNeighboringPositions(int pos1,int pos2,int pos3,int pos4){
		neighboringPositions=new int[4];
		neighboringPositions[0]=pos1;
		neighboringPositions[1]=pos2;
		neighboringPositions[2]=pos3;
		neighboringPositions[3]=pos4;
	}
	/*
	* Paraméterként átvett pozícióra megállapítsa, hogy szomszédos-e az aktuális pozícióval.
	* Ha szomszédosak true-t térít vissza, ellenkező esetben false-ot.
	*/
	public boolean isNeighboringPosition(int pos){
		for(int i=0;i<neighboringPositions.length;i++){
			if(neighboringPositions[i]==pos){
				return true;
			}
		}
		return false;
	}
}
