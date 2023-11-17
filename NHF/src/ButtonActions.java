
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActions {
	public static ActionListener newGameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GUI.frame.setVisible(false);
			GameFrame gameFrame = new GameFrame();
		}
	};

	public static ActionListener statisticsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame statisticsFrame = new JFrame("Malom");
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			GUI.frame.setVisible(false);
			statisticsFrame.setVisible(true);
			statisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			statisticsFrame.setSize(screenSize);
			JButton backToMainMenuButton = new JButton("Visszalépés a főmenübe");
			Dimension buttonSize = new Dimension(200,50);
			panel.add(backToMainMenuButton);
			statisticsFrame.add(panel);
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					statisticsFrame.setVisible(false);
					GUI.frame.setVisible(true);
				}
			};
			backToMainMenuButton.addActionListener(listener);
		}
	};
	public static ActionListener exitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
}
