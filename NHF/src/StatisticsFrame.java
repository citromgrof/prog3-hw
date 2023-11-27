import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Egyszerű statisztikákat jelenít meg grafikus formában,
 * a szövegeket JLabel-ek tartalmazzák, valamint tartalmaz egyetlen gombot, amivel vissza lehet térni a főmenübe.
 */
public class StatisticsFrame extends FileManager{
	public static JFrame statisticsFrame =new JFrame();

	public StatisticsFrame(){
		super("winners.txt");
		JPanel panel = new JPanel();
		panel.setBackground(new Color(189,154,122));
		panel.setLayout(null);
		GUI.frame.setVisible(false);
		statisticsFrame.setVisible(true);
		statisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		statisticsFrame.setSize(screenSize);
		JButton backToMainMenuButton = new JButton("Visszalépés a főmenübe");
		backToMainMenuButton.setBounds(0,0,200,50);
		JLabel label = new JLabel("Statisztikák");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(0,50,200,50);
		JLabel allGamesLabel = new JLabel("Összes játék "+getAllGames());
		allGamesLabel.setFont(new Font("Arial", Font.BOLD, 20));
		allGamesLabel.setBounds(0,100,300,50);
		final DecimalFormat df = new DecimalFormat("#.##");
		JLabel whiteGamesLabel = new JLabel("Fehér nyert "+getWhiteWins()+" játékot, ez a játékok "+ df.format( (double) getWhiteWins()*100/getAllGames())+"%-a");
		whiteGamesLabel.setFont(new Font("Arial", Font.BOLD, 20));
		whiteGamesLabel.setBounds(0,150,1000,50);
		JLabel blackGamesLabel = new JLabel("Fekete nyert "+getBlackWins()+" játékot, ez a játékok "+ df.format( (double) getBlackWins()*100/getAllGames())+"%-a");
		blackGamesLabel.setFont(new Font("Arial", Font.BOLD, 20));
		blackGamesLabel.setBounds(0,200,1000,50);
		JLabel drawsLabel = new JLabel("Döntetlenek: "+getDraws());
		drawsLabel.setFont(new Font("Arial", Font.BOLD, 20));
		drawsLabel.setBounds(0,250,300,50);
		panel.add(drawsLabel);
		panel.add(drawsLabel);
		panel.add(allGamesLabel);
		panel.add(label);
		panel.add(whiteGamesLabel);
		panel.add(blackGamesLabel);
		panel.add(backToMainMenuButton);
		statisticsFrame.add(panel);
		backToMainMenuButton.addActionListener(backToMainMenuListener);
	}

	public static ActionListener backToMainMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GUI.frame.setVisible(true);
			statisticsFrame.setVisible(false);
			statisticsFrame.dispose();
		}
	};
}
