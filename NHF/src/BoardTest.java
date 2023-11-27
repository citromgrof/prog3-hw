import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Board osztályt konstruktorának, isNeighboringPosition() és setPuckToPosition() metódusainak tesztjei.
 */
public class BoardTest {
	@Test
	public void ctrTest(){
		Board board = new Board();
		assertEquals (0, board.getPucksOnBoard());
		assertEquals(0, board.getNumberOfPlayerPucks(Puck.BLACK));
		assertEquals(0, board.getNumberOfPlayerPucks(Puck.WHITE));
		assertFalse(board.getPositions(1).isOccupied());
	}

	@Test
	public void neighborTest(){
		Board board=new Board();
		Position pos1=board.getPositions(1);
		Position pos2=board.getPositions(2);
		assertTrue(board.isNeighboringPosition(pos1,pos2));
	}

	@Test
	public void setPuckTest(){
		Board board=new Board();
		board.setPuckToPosition(Puck.BLACK,1);
		assertEquals(Puck.BLACK,board.getPositions(1).getOccupiedByPlayer());
	}
}
