
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActions {
	public static ActionListener newGameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
	public static ActionListener statisticsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
	public static ActionListener exitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
}
