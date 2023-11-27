import java.util.Arrays;
import java.util.List;

/**
 * A játék logikájáért felelős osztály, tárolja a játéktáblát, játékosokat, a játék fázisát,
 * az aktuális körben meylik játékos kell lépjen, valamint az, hogy hány korong van a táblán elhelyezeve.
 */
public class Game {
	private Board board;
	private Player white;
	private Player black;
	private Phase phase;
	private Puck currentTurnColor;
	private static int pucksPlaced=0;

	public Game(){
		board=new Board();
		white=new Player(Puck.WHITE,0);
		black=new Player(Puck.BLACK,0);
		phase=Phase.PLACING;
		currentTurnColor=Puck.WHITE;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}


	public Phase getPhase() {
		return phase;
	}


	public Puck getCurrentTurnColor() {
		return currentTurnColor;
	}

	public Puck getPosition(int index){
		return board.getPositions(index).getOccupiedByPlayer();
	}

	/**
	 * Megvizsgálja, hogy az adott játékos által lehetséges-e az src indexű pozícióban lévő korongot elmozgatni a dest pozícióhoz.
	 * A mozgatás lehetséges, ha az src pozícióba a játékos korongja áll, a dest pozíció nem foglalt és szomszédos,
	 * valamint ha már a játékos nak 3 korongja van és tud ugrállni a táblán, akkor a dest pozíció nem kell szomszédos legyen, csak szabad.
	 * Privát metódus, mert a lépést a makeMove függvény hajtja végre ténylegesen.
	 * @param src Kezdő pozíció indexe.
	 * @param dest Az a pozíció indexe, hová a felhasználó mozgassa a korngját.
	 * @param player Melyik játékos kíséreli meg a lépést.
	 * @return Igaz, ha a játékos meg tudja tenni a lépést, hamis ha nem.
	 */
	private boolean isValidMove(int src,int dest,Puck player){
		if(player==Puck.NONE){return false;}
		Player p;
		if(player==Puck.BLACK){
			p=black;
		}else{
			p=white;
		}
		if(p.getCanLeap() && !board.getPositions(dest).isOccupied()){return true;}
		Position srcPos=board.getPositions(src);
		Position destPos=board.getPositions(dest);
		if( board.isNeighboringPosition(srcPos, destPos) && destPos.getOccupiedByPlayer()==Puck.NONE){
			return true;
		}
		return false;
	}

	/**
	 * Amennyiben a lépés megengedett végrehajtja, azaz a kezdő pozícícó üres lesz és
	 * a célt pedig a paraméterként átvett színű játékos fogja birtokolni.
	 * Könnyebb tesztelés és hibakeresés céljából, paraméterként meg kell adni, hogy melyik játékos viszi véghez a lépést,
	 * a függvény, ha a lépés nem megengedett, akkor annak az okatát téríti vissza, ezek részletezve vannak a Moves enum-ben.
	 * @param src Kezdő pozíció indexe.
	 * @param dest Az a pozíció indexe, hová a felhasználó mozgassa a korngját.
	 * @param player Melyik játékos kíséreli meg a lépést.
	 * @return A lépés minősítése.
	 */
	public Moves makeMove(int src,int dest,Puck player){
		Position srcPos=board.getPositions(src);
		Position destPos=board.getPositions(dest);
		if(srcPos.getOccupiedByPlayer()!=player){return Moves.INVALID_SRC;}
		if(destPos.isOccupied()){return Moves.INVALID_DEST;}
		if(!isValidMove(src,dest,player)){return Moves.INVALID_MOVE;}
		board.setPuckToPosition(player,dest);
		board.setPuckToPosition(Puck.NONE,src);
		if(player==Puck.WHITE){
			currentTurnColor=Puck.BLACK;
		}else{currentTurnColor=Puck.WHITE;}
		return Moves.VALID_MOVE;
	}

	/**
	 * Az adott indexű pozíciót az átvett játekos fogja birtokolni, amennyiben az üres.
	 * Mivel ez a legelső fázis, ha már a játékosok leraktak összes 18 korongot, akkor a lépegetés fázisra vált.
	 * @param index A pozíció indexe ahová el szeretne a játékos elhelyezni egy korongot.
	 * @param player Melyik játékos kíséreli meg a lépést.
	 * @return Igaz ha a művelet sikeres, hamis ha az a pozíciót már birtokolják.
	 */
	public boolean placePuck(int index,Puck player){
		if(board.getPositions(index).isOccupied()){return false;}
		pucksPlaced++;
		if(pucksPlaced==18){
			phase=Phase.MOVING;
		}
		board.getPositions(index).setAsOccupied(player);
		if(currentTurnColor==Puck.WHITE){
			currentTurnColor=Puck.BLACK;
		}else{currentTurnColor=Puck.WHITE;}
		board.incrementPlayerPuck(player);
		board.incrementPucksOnBoard();
		return true;
	}

	/**
	 * Visszatéríti, hogy hány molmban van benne az adott indexű pozíció.
	 * @param index A pozíció indexe.
	 * @return Hány molmban van benne az adott indexű pozíció.
	 */
	public int isInAMill(int index){
		Position pos=null;
		pos=board.getPositions(index);
		int millCntr=0;
		for (int i=1;i<=board.TOTAL_NUMBER_OF_MILLS;i++){
			List<Position> mill= Arrays.asList(board.getMills(i));
			if(mill.contains(pos)){
				int cntr=0;
				for(Position p:mill){

					if(p.getOccupiedByPlayer().equals(pos.getOccupiedByPlayer())){
						cntr++;
					}
					if(cntr==3) millCntr++;
				}
			}
		}
		return millCntr;
	}

	public Puck getPreviousTurnColor(){
		if(currentTurnColor==Puck.WHITE){
			return Puck.BLACK;
		}else{
			return Puck.WHITE;
		}
	}

	/**
	 * Az adott indexű pozíciót üresre állítsa, ha azt az ellenkező játékos birtokolja(egy korong levétele a tábláról).
	 *
	 * @param index A pozíció indexe.
	 * @param player Melyik játékos veszi le a korongot.
	 * @return Igaz, ha le sikerült venni a korongot, hamis, ha nem.
	 */
	public boolean takePuck(int index,Puck player){
		Position pos=null;
		pos=board.getPositions(index);
		if(pos.getOccupiedByPlayer()==Puck.NONE || pos.getOccupiedByPlayer()==player || isInAMill(index)>0){return false;}
		board.setPuckToPosition(Puck.NONE,index);
		if(player==Puck.WHITE){
			board.decrementPlayerPuck(Puck.BLACK);
		}
		if(player==Puck.BLACK){
			board.decrementPlayerPuck(Puck.WHITE);
		}
		if(board.getNumberOfPlayerPucks(Puck.BLACK)==3 && phase!=Phase.PLACING){
			phase=Phase.LEAPING;
			black.setCanLeap(true);
		}
		if(board.getNumberOfPlayerPucks(Puck.WHITE)==3 && phase!=Phase.PLACING){
			phase=Phase.LEAPING;
			white.setCanLeap(true);
		}
		board.decrementPucksOnBoard();
		return true;
	}

	/**
	 * A függvény megvizsgálja, hogy az adott játékos tud-e levenni az ellenfél korongjai közül.
	 * Más szavakkal, ha az ellenfélnek az összes korongja malomban van akkor nem lehet levenni közülük egyet sem.
	 * @param color A játékos, amiről meg kell vizsgálni, hogy le tud-e venni az elenfél korongjai közül.
	 * @return Igaz, ha le tud venni, ellenkező esetben hamis.
	 */
	public boolean canColorTake(Puck color){
		Puck otherPlayersColor;
		int otherPlayersPucksInMills=0;
		if(color==Puck.WHITE){
			otherPlayersColor=Puck.BLACK;
		}else{
			otherPlayersColor=Puck.WHITE;
		}
		Position[] positions=board.getPositions();
		for(int i=1;i<=board.TOTAL_NUMBER_OF_PUCKS;i++){
			if(positions[i].getOccupiedByPlayer()==otherPlayersColor){
				if(isInAMill(i)==0){
					return true;
				}else{
					otherPlayersPucksInMills++;
				}
			}
		}
		if(otherPlayersPucksInMills== board.getNumberOfPlayerPucks(otherPlayersColor)){
			return false;
		}
		return true;
	}

	/**
	 * Megvizsgálja, hogy a játékosoknak, van-e érvényes lépésük, illetve legalább három korongjuk.
	 * @return Igaz, ha van érvényes lépés és legalább három korong, ellenkező esetben hamis.
	 */
	public boolean isGameOver(){
		if(board.getNumberOfPlayerPucks(Puck.BLACK)<=2 || board.getNumberOfPlayerPucks(Puck.WHITE)<=2){
			return true;
		}
		Position[] boardPositions=board.getPositions();
		for(int i=1;i<=board.TOTAL_NUMBER_OF_PUCKS;i++){
			if(boardPositions[i].isOccupied()) {
				for (Position pos : board.getNeighboringPositions(i)) {
					if(!pos.isOccupied()){
						return false;
					}

				}
			}
		}
		return true;
	}

	public Puck getWinner(){
		if(board.getNumberOfPlayerPucks(Puck.BLACK)==board.getNumberOfPlayerPucks(Puck.WHITE)){
			return Puck.NONE;
		}
		if(board.getNumberOfPlayerPucks(Puck.BLACK)<board.getNumberOfPlayerPucks(Puck.WHITE)){
			return Puck.WHITE;
		}else{
			return Puck.BLACK;
		}
	}
}
