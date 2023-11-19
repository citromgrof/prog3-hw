import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Puck getCurrentTurnColor() {
		return currentTurnColor;
	}

	public Puck getPosition(int index){
		return board.getPositions(index).getOccupiedByPlayer();
	}
	private boolean isValidMove(int src,int dest){
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
		if(board.getPositions(index).getOccupiedByPlayer()!=Puck.NONE){return false;}
		pucksPlaced++;
		if(pucksPlaced>18){
			phase=Phase.MOVING;
			return false;
		}
		board.getPositions(index).setAsOccupied(player);
		if(currentTurnColor==Puck.WHITE){
			currentTurnColor=Puck.BLACK;
		}else{currentTurnColor=Puck.WHITE;}
		board.incrementPlayerPuck(player);
		board.incrementPucksOnBoard();
		return true;
	}
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
	public boolean takePuck(int index,Puck player){
		Position pos=null;
		pos=board.getPositions(index);
		if(pos.getOccupiedByPlayer()==Puck.NONE || pos.getOccupiedByPlayer()==player || isInAMill(index)>0){return false;}
		pos.setAsOccupied(Puck.NONE);
		if(player==Puck.WHITE){
			board.decrementPlayerPuck(Puck.BLACK);
		}else{board.decrementPlayerPuck(Puck.WHITE);}
		board.decrementPucksOnBoard();
		return true;
	}
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
}
