import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A FileManager osztályt konstrukotrának és fájlba írásának tesztjei.
 */
public class FileManagerTest {
	@Test
	public void ctrTest(){
		FileManager fileManager = new FileManager("test.txt");
	    assertEquals (0, fileManager.getAllGames());
	}

	@Test
	public void logTest(){
		FileManager fileManager = new FileManager("test2.txt");
		fileManager.logWinner(Puck.BLACK, "test2.txt");
		assertEquals (0, fileManager.getBlackWins());
	}

	/**
	 * Helyreállítsa a fájlokat, hogy ne akadályozzák a teszt ismételt futattását.
	 * @throws FileNotFoundException
	 */
	@AfterAll
	public static void tearDown() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("test.txt");
		writer.print("");
		writer.close();
		PrintWriter writer2 = new PrintWriter("test2.txt");
		writer2.print(1);
		writer2.close();
	}

}
