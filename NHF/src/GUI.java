import javax.swing.*;
import java.awt.*;

public class GUI extends ButtonActions {
	public static JFrame frame = new JFrame("Malom");
	public static JLabel textField = new JLabel();

	public GUI() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(189,154,122));
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setPreferredSize(new Dimension(200,50));
		textField.setFont(new Font("Arial", Font.BOLD, 20));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
		textField.setBackground(new Color(189,154,122));
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(189,154,122));
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
		panel.add(textField, gbc); // Add the JTextField above newGameButton


		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(newGameButton, gbc);
		gbc.gridy = 2;
		panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc); // Add vertical spacing
		gbc.gridy = 3;
		panel.add(statisticsButton, gbc);
		gbc.gridy = 4;
		panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
		gbc.gridy = 5;
		panel.add(exitButton, gbc);

		frame.setLocationRelativeTo(null);
		frame.add(panel);
		frame.setVisible(true);
	}
}
