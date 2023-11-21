import java.io.*;

public class FileManager {
	private int allGames;
	private int blackWins;
	private int whiteWins;
	public FileManager(){
		allGames=0;
		blackWins=0;
		whiteWins=0;
		init();
	}
	private int draws;
	public static void logWinner(Puck winner){
		int win;
		if(winner==Puck.BLACK) win=0;
		else if(winner==Puck.WHITE) win=1;
		else win=2;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("winners.txt", true))) {
			writer.write(Integer.toString(win));
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void init(){
		try (BufferedReader reader = new BufferedReader(new FileReader("winners.txt"))) {
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
