import java.util.ArrayList;
import java.util.HashMap;

public class Board {
	static public final int TOTAL_NUMBER_OF_PUCKS = 24;
	static public final int TOTAL_NUMBER_OF_MILLS = 16;
	static public final int NUMBER_OF_PUCKS_IN_MILL = 3;

	private Position[] positions=new Position[TOTAL_NUMBER_OF_PUCKS+1];
	private HashMap<Position, ArrayList<Position>> neighboringPositions=new HashMap<>();
	private HashMap<Integer, Position[]> mills;
	private int whitePucks;
	private int blackPucks;
	private int pucksOnBoard;

	public Board() {
		whitePucks = 0;
		blackPucks = 0;
		pucksOnBoard = 0;
		initializeNeighboringPositions();
		initializeMills();
	}
	/*
	 * Mindegyik tábla pozícióhoz hozzárnedli a szomszédai indexét.
	 *
	 *  1------------------2------------------3
	 *  |                  |                  |
	 *  |      4-----------5-----------6      |
	 *  |      |           |           |      |
	 *  |      |     7-----8-----9     |      |
	 *  |      |     |           |     |      |
	 *  10----11----12           13---14-----15
	 *  |      |     |           |     |      |
	 *  |      |     16----17----18    |      |
	 *  |      |           |           |      |
	 *  |      19----------20----------21     |
	 *  |                  |                  |
	 *  22----------------23-----------------24
	 * */
	private void initializeNeighboringPositions(){
		for(int i=1;i<=TOTAL_NUMBER_OF_PUCKS;i++){
			positions[i]=new Position(i);
		}
		for(int i=1;i<=TOTAL_NUMBER_OF_PUCKS;i++){
			neighboringPositions.putIfAbsent(positions[i], new ArrayList<Position>());
		}
		neighboringPositions.get(positions[1]).add(positions[10]);
		neighboringPositions.get(positions[1]).add(positions[2]);
		neighboringPositions.get(positions[2]).add(positions[1]);
		neighboringPositions.get(positions[2]).add(positions[3]);
		neighboringPositions.get(positions[2]).add(positions[5]);
		neighboringPositions.get(positions[3]).add(positions[2]);
		neighboringPositions.get(positions[3]).add(positions[15]);
		neighboringPositions.get(positions[4]).add(positions[5]);
		neighboringPositions.get(positions[4]).add(positions[11]);
		neighboringPositions.get(positions[5]).add(positions[2]);
		neighboringPositions.get(positions[5]).add(positions[4]);
		neighboringPositions.get(positions[5]).add(positions[6]);
		neighboringPositions.get(positions[5]).add(positions[8]);
		neighboringPositions.get(positions[6]).add(positions[5]);
		neighboringPositions.get(positions[6]).add(positions[14]);
		neighboringPositions.get(positions[7]).add(positions[8]);
		neighboringPositions.get(positions[7]).add(positions[12]);
		neighboringPositions.get(positions[8]).add(positions[5]);
		neighboringPositions.get(positions[8]).add(positions[7]);
		neighboringPositions.get(positions[8]).add(positions[9]);
		neighboringPositions.get(positions[9]).add(positions[8]);
		neighboringPositions.get(positions[9]).add(positions[13]);
		neighboringPositions.get(positions[10]).add(positions[1]);
		neighboringPositions.get(positions[10]).add(positions[11]);
		neighboringPositions.get(positions[10]).add(positions[22]);
		neighboringPositions.get(positions[11]).add(positions[4]);
		neighboringPositions.get(positions[11]).add(positions[10]);
		neighboringPositions.get(positions[11]).add(positions[12]);
		neighboringPositions.get(positions[11]).add(positions[19]);
		neighboringPositions.get(positions[12]).add(positions[7]);
		neighboringPositions.get(positions[12]).add(positions[11]);
		neighboringPositions.get(positions[12]).add(positions[16]);
		neighboringPositions.get(positions[13]).add(positions[9]);
		neighboringPositions.get(positions[13]).add(positions[14]);
		neighboringPositions.get(positions[13]).add(positions[18]);
		neighboringPositions.get(positions[14]).add(positions[6]);
		neighboringPositions.get(positions[14]).add(positions[13]);
		neighboringPositions.get(positions[14]).add(positions[15]);
		neighboringPositions.get(positions[14]).add(positions[21]);
		neighboringPositions.get(positions[15]).add(positions[3]);
		neighboringPositions.get(positions[15]).add(positions[14]);
		neighboringPositions.get(positions[15]).add(positions[24]);
		neighboringPositions.get(positions[16]).add(positions[12]);
		neighboringPositions.get(positions[16]).add(positions[17]);
		neighboringPositions.get(positions[17]).add(positions[16]);
		neighboringPositions.get(positions[17]).add(positions[18]);
		neighboringPositions.get(positions[17]).add(positions[20]);
		neighboringPositions.get(positions[18]).add(positions[13]);
		neighboringPositions.get(positions[18]).add(positions[17]);
		neighboringPositions.get(positions[19]).add(positions[11]);
		neighboringPositions.get(positions[19]).add(positions[20]);
		neighboringPositions.get(positions[20]).add(positions[17]);
		neighboringPositions.get(positions[20]).add(positions[19]);
		neighboringPositions.get(positions[20]).add(positions[21]);
		neighboringPositions.get(positions[20]).add(positions[23]);
		neighboringPositions.get(positions[21]).add(positions[14]);
		neighboringPositions.get(positions[21]).add(positions[20]);
		neighboringPositions.get(positions[22]).add(positions[10]);
		neighboringPositions.get(positions[22]).add(positions[23]);
		neighboringPositions.get(positions[23]).add(positions[20]);
		neighboringPositions.get(positions[23]).add(positions[22]);
		neighboringPositions.get(positions[23]).add(positions[24]);
		neighboringPositions.get(positions[24]).add(positions[15]);
		neighboringPositions.get(positions[24]).add(positions[23]);
	}

	/*
	 * 16 darab malom létezik a játékban(4-4 a külső, középső és belső négyzeteken, valamint 4 a négyzeteket összekötő részeken)
	 * , a block mindegyik malomhoz hozzárendeli, hogy milyen pozíciók vannak benne.
	 *  További magyarázatért nézd meg a dokumentációt.
	 */
	private  void initializeMills() {
		mills = new HashMap<>();
		mills.put(1, new Position[]{positions[1], positions[2], positions[3]});
		mills.put(2, new Position[]{positions[3], positions[15], positions[24]});
		mills.put(3, new Position[]{positions[22], positions[23], positions[24]});
		mills.put(4, new Position[]{positions[1], positions[10], positions[22]});
		mills.put(5, new Position[]{positions[4], positions[5], positions[6]});
		mills.put(6, new Position[]{positions[6], positions[14], positions[21]});
		mills.put(7, new Position[]{positions[19], positions[20], positions[21]});
		mills.put(8, new Position[]{positions[4], positions[11], positions[19]});
		mills.put(9, new Position[]{positions[7], positions[8], positions[9]});
		mills.put(10, new Position[]{positions[9], positions[13], positions[18]});
		mills.put(11, new Position[]{positions[16], positions[17], positions[18]});
		mills.put(12, new Position[]{positions[7], positions[12], positions[16]});
		mills.put(13, new Position[]{positions[2], positions[5], positions[8]});
		mills.put(14, new Position[]{positions[10], positions[11], positions[12]});
		mills.put(15, new Position[]{positions[13], positions[14], positions[15]});
		mills.put(16, new Position[]{positions[17], positions[20], positions[23]});
	}
	public Position getPositions(int index){
		if(index<1 || index>TOTAL_NUMBER_OF_PUCKS){
			return null;
		}
		return positions[index];
	}
	public boolean isOccupied(int index){
		if(index<1 || index>TOTAL_NUMBER_OF_PUCKS){
			return false;
		}
		return positions[index].isOccupied();
	}
	public void setPuckToPosition(Puck p,int index){
		if(index<1 || index>TOTAL_NUMBER_OF_PUCKS){
			return;
		}
		if(p==Puck.NONE){positions[index].setAsUnoccupied();}
		else {
			positions[index].setAsOccupied(p);
		}
	}
	public int incrementPucksOnBoard(){
		return ++pucksOnBoard;
	}
	public int decrementPucksOnBoard(){
		return --pucksOnBoard;
	}
	public int incrementPlayerPuck(Puck p){
		if(p==Puck.BLACK){
			return ++blackPucks;
		}
		if(p==Puck.WHITE){
			return ++whitePucks;
		}
		return 0;
	}
	public int decrementPlayerPuck(Puck p){
		if(p==Puck.BLACK){
			return --blackPucks;
		}
		if(p==Puck.WHITE){
			return --whitePucks;
		}
		return 0;
	}
	public int getNumberOfPlayerPucks(Puck p){
		if(p==Puck.BLACK){
			return blackPucks;
		}
		if(p==Puck.WHITE){
			return whitePucks;
		}
		return 0;
	}
	public Position[] getMills(int index){
		return mills.get(index);
	}
	/*
	 * Paraméterként átvett pozícióra megállapítsa, hogy szomszédos-e az aktuális pozícióval.
	 * Ha szomszédosak true-t térít vissza, ellenkező esetben false-ot.
	 */
	public boolean isNeighboringPosition(Position pos1, Position pos2){
		if(neighboringPositions.get(pos1).contains(pos2)){
			return true;
		}
		return false;
	}
	public ArrayList<Position> getNeighboringPositions(int index){
		return neighboringPositions.get(positions[index]);
	}
	public Position[] getPositions(){
		return positions;
	}
}
