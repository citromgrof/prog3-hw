import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tesztek a Game osztályra.
 */
public class GameTest {

	@Test
	public void ctorTest(){
		Game game=new Game();
		assertNotNull(game);
		assertEquals(Puck.WHITE, game.getCurrentTurnColor());
		assertEquals(Phase.PLACING, game.getPhase());
	}

	/**
	 * Egy korong lerakása.
	 */
	@Test
   public void placePuckTest(){
		Game game=new Game();
		game.placePuck(1, Puck.BLACK);
		assertEquals(Puck.BLACK, game.getPosition(1));
   }

	/**
	 * Egy malom alkotását teszteli a függvény. Lehelyezünk három fehér korngot, úgy, hogy malmot alkossanak.
	 * Az isInAMill() függvény 1-et kell visszatérítsen, bármely korongra, mert mindegyik egyetlen malomban van benne.
	 */
	@Test
	public void millTest(){
		Game game=new Game();
		game.placePuck(1, Puck.BLACK);
		game.placePuck(2, Puck.BLACK);
		game.placePuck(3, Puck.BLACK);
		assertEquals(1,game.isInAMill(1));
		assertEquals(1,game.isInAMill(2));
		assertEquals(1,game.isInAMill(3));
   }

	/**
	 * Érvényes korong mozgatásának tesztelése.
	 */
	@Test
	public void makeValidMoveTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		assertEquals(Moves.VALID_MOVE,game.makeMove(1,2,Puck.WHITE));
	}

	/**
	 * Korong mozgatása érvénytelen kezdőpontról.
	 */
	@Test
	public void makeInvalidSrcMoveTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		assertEquals(Moves.INVALID_SRC,game.makeMove(2,1,Puck.WHITE));
	}

	/**
	 * Korong mozgatása érvénytelen célpontra.
	 */
	@Test
	public void makeInvalidDstMoveTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		game.placePuck(2,Puck.WHITE);
		assertEquals(Moves.INVALID_DEST,game.makeMove(1,2,Puck.WHITE));
	}

	/**
	 * Érvénytelen mozgatása, a mozgatás akkor érvénytelen, ha a játékos nem tud ugrálni a táblán és nem szomszédos pozícióra lép.
	 */
	@Test
	public void makeInvalidMoveTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		assertEquals(Moves.INVALID_MOVE,game.makeMove(1,3,Puck.WHITE));
	}

	/**
	 * Ha az összes fehér korong malomban van, akkor a fekete játékos nem tud korongot levenni.
	 */
	@Test
	public void canColorTakeFalseTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		game.placePuck(2,Puck.WHITE);
		game.placePuck(3,Puck.WHITE);
		assertFalse(game.canColorTake(Puck.BLACK));
	}

	/**
	 * Ha legalább egy fehér korong nincs malomban, akkor a fekete játékos tud levenni korongot.
	 */
	@Test
	public void canColorTakeTrueTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		game.placePuck(2,Puck.WHITE);
		game.placePuck(3,Puck.WHITE);
		game.placePuck(4,Puck.WHITE);
		assertTrue(game.canColorTake(Puck.BLACK));
	}

	/**
	 * Ha az összes fehér korong malomban van, akkor a fekete játékos nem tud korongot levenni.
	 */
	@Test
	public void takePuckFalseTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		game.placePuck(2,Puck.WHITE);
		game.placePuck(3,Puck.WHITE);
		assertFalse(game.takePuck(1,Puck.BLACK));
		assertEquals(Puck.WHITE,game.getPosition(1));
	}

	/**
	 * Ha legalább egy fehér korong nincs malomban, akkor a fekete játékos tud levenni korongot.
	 */
	@Test
	public void takePuckTrueTest(){
		Game game=new Game();
		game.placePuck(1,Puck.WHITE);
		game.placePuck(2,Puck.WHITE);
		game.placePuck(3,Puck.WHITE);
		game.placePuck(4,Puck.WHITE);
		assertTrue(game.takePuck(4,Puck.BLACK));
		assertEquals(Puck.NONE,game.getPosition(4));
	}
}