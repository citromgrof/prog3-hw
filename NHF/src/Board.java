import java.util.HashMap;

public class Board {
	static public final int TOTAL_NUMBER_OF_PUCKS = 24;
	static public final int TOTAL_NUMBER_OF_MILLS = 16;
	static public final int NUMBER_OF_PUCKS_IN_MILL = 12;
	private Position[] positions;
	private HashMap<Integer, Position[]> mills;
	private int whitePucks;
	private int blackPucks;
	private int pucksOnBoard;

	public Board() {
		positions = new Position[TOTAL_NUMBER_OF_PUCKS];
		mills = new HashMap<>();
		whitePucks = 0;
		blackPucks = 0;
		pucksOnBoard = 0;
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
		positions[index].setAsOccupied(p);
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
	{
		for(int i=1;i<=TOTAL_NUMBER_OF_PUCKS;i++){
			positions[i]=new Position(i);
		}
		positions[1].setNeighboringPositions(2, 10);
		positions[2].setNeighboringPositions(1, 3, 5);
		positions[3].setNeighboringPositions(2, 15);
		positions[4].setNeighboringPositions(5, 11);
		positions[5].setNeighboringPositions(2, 4, 6, 8);
		positions[6].setNeighboringPositions(5, 14);
		positions[7].setNeighboringPositions(8, 12);
		positions[8].setNeighboringPositions(5, 7, 9);
		positions[9].setNeighboringPositions(8, 13);
		positions[10].setNeighboringPositions(1, 11, 22);
		positions[11].setNeighboringPositions(4, 10, 12, 19);
		positions[12].setNeighboringPositions(7, 16);
		positions[13].setNeighboringPositions(9, 14, 18);
		positions[14].setNeighboringPositions(6, 13, 15, 21);
		positions[15].setNeighboringPositions(3, 14, 24);
		positions[16].setNeighboringPositions(12, 17);
		positions[17].setNeighboringPositions(16, 18, 20);
		positions[18].setNeighboringPositions(13, 17);
		positions[19].setNeighboringPositions(11, 20);
		positions[20].setNeighboringPositions(17, 19, 21, 23);
		positions[21].setNeighboringPositions(14, 20);
		positions[22].setNeighboringPositions(10, 23);
		positions[23].setNeighboringPositions(20, 22, 24);
		positions[24].setNeighboringPositions(15, 23);
	}

	/*
	 * 16 darab malom létezik a játékban(4-4 a külső, középső és belső négyzeteken, valamint 4 a négyzeteket összekötő részeken)
	 * , a block mindegyik malomhoz hozzárendeli, hogy milyen pozíciók vannak benne.
	 *  További magyarázatért nézd meg a dokumentációt.
	 */ {
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
}
