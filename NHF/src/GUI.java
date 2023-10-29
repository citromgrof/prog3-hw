import javax.swing.*;
import java.awt.*;

public class GUI extends ButtonActions {
	public GUI() {
		JFrame frame = new JFrame("Malom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setSize(screenSize);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);

		JButton newGameButton = new JButton("Új játék");
		JButton statisticsButton = new JButton("Statisztikák");
		JButton exitButton = new JButton("Kilépés");

		Dimension buttonSize = new Dimension(200, 50);
		newGameButton.setPreferredSize(buttonSize);
		statisticsButton.setPreferredSize(buttonSize);
		exitButton.setPreferredSize(buttonSize);

		newGameButton.addActionListener(newGameListener);
		statisticsButton.addActionListener(statisticsListener);
		exitButton.addActionListener(exitListener);


		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(newGameButton, gbc);

		gbc.gridy = 1;
		panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc); // Add vertical spacing

		gbc.gridy = 2;
		panel.add(statisticsButton, gbc);

		gbc.gridy = 3;
		panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc); // Add vertical spacing

		gbc.gridy = 4;
		panel.add(exitButton, gbc);


		frame.setLocationRelativeTo(null);

		frame.add(panel);

		frame.setVisible(true);
	}
}
