import java.io.*;

/**
 * Egyszrű fájlkezelő osztály, ami egy adott fájlbó kinyeri, hogy melyik szín hányszor nyert, illetve a döntetlenek számát.
 * A fájlokban mindegyik sorban egy szám kell legyen ami lehet 0,1 és 2. A 0 egy fekete győzelem, 1 fehér győzelem és a 2-es a döntetlen.
 */
public class FileManager {
	private int allGames;
	private int blackWins;
	private int whiteWins;
	private int draws;

	public FileManager(String fileName) {
		allGames=0;
		blackWins=0;
		whiteWins=0;
		draws=0;
		init(fileName);
	}

	/**
	 * Adott fájl végére ír egy számot az adott Puck szerint. Puck.BLACK = 0, Puck.WHITE = 1 Puck.NONE = 2.
	 * @param winner Milyen színt kell bejegyezni a fájlba.
	 * @param fileName A fájl neve.
	 */
	public static void logWinner(Puck winner, String fileName) {
		int win;
		if(winner==Puck.BLACK) win=0;
		else if(winner==Puck.WHITE) win=1;
		else win=2;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.class.getClassLoader().getResource(fileName).getFile(), true))) {
			writer.write(Integer.toString(win));
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void init(String fileName) {

		try (BufferedReader reader = new BufferedReader(new FileReader(FileManager.class.getClassLoader().getResource(fileName).getFile()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				allGames++;
				int number = Integer.parseInt(line);
				switch (number) {
					case 0:
						blackWins++;
						break;
					case 1:
						whiteWins++;
						break;
					case 2:
						draws++;
						break;
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getAllGames(){
		return allGames;
	}

	public int getBlackWins(){
		return blackWins;
	}

	public int getWhiteWins(){
		return whiteWins;
	}

	public int getDraws(){
		return draws;
	}

}
