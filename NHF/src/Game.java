public class Game {
	private Board board;
	private Player white;
	private Player black;
	private Phase phase;
	private Puck currentTurnColor;
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

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Puck getCurrentTurnColor() {
		return currentTurnColor;
	}

	public Puck getPosition(int index){
		return board.getPositions(index).getOccupiedByPlayer();
	}
	public boolean isValidMove(int src,int dest){
		Position srcPos=board.getPositions(src);
		Position destPos=board.getPositions(dest);
		if( board.isNeighboringPosition(srcPos, destPos) && destPos.getOccupiedByPlayer()==Puck.NONE){
			return true;
		}
		return false;
	}
	public Moves makeMove(int src,int dest,Puck player){
		Position srcPos=board.getPositions(src);
		Position destPos=board.getPositions(dest);
		if(srcPos.getOccupiedByPlayer()!=player){return Moves.INVALID_SRC;}
		if(destPos.getOccupiedByPlayer()!=Puck.NONE){return Moves.INVALID_DEST;}
		if(!isValidMove(src,dest)){return Moves.INVALID_MOVE;}
		destPos.setAsOccupied(player);
		srcPos.setAsUnoccupied();
		return Moves.VALID_MOVE;
	}
	public boolean placePuck(int index,Puck player){
		Position pos= board.getPositions(index);
		if(pos.getOccupiedByPlayer()!=Puck.NONE){return false;}
		pos.setAsOccupied(player);
		if(currentTurnColor==Puck.WHITE){
			currentTurnColor=Puck.BLACK;
		}else{currentTurnColor=Puck.WHITE;}
		board.incrementPlayerPuck(player);
		board.incrementPucksOnBoard();
		return true;
	}
	
}
