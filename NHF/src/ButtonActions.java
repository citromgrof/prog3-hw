
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
			StatisticsFrame statisticsFrame = new StatisticsFrame();
		}
	};
	public static ActionListener exitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
}

