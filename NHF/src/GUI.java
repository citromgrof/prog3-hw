import javax.swing.*;
import java.awt.*;

public class GUI {
	public GUI() {
		JFrame frame = new JFrame();
		JButton newGame =new JButton("Új játék");
		JButton statistics = new JButton("Statisztikák");
		JButton exit = new JButton("Kilépés");
		newGame.setBounds(100,100,100,100);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
		panel.setLayout(new GridLayout(0,1));
		panel.add(newGame);
		panel.add(statistics);
		panel.add(exit);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Malom");
		frame.pack();
		frame.setVisible(true);
	}
}
