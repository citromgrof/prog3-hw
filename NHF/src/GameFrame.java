import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class GameFrame extends ButtonActions{
	public static JFrame gameFrame = new JFrame("Malom");
	private static final JButton[] positionButtons = new JButton[25];
	private static JPanel iconPanel = new JPanel();
	private static Queue<Integer> pressedPositions=new LinkedList<>();
	public GameFrame() {
		gameFrame.setSize(new Dimension(1024, 768));
		JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/malom4.png")));
		JButton backToMainMenuButton = new JButton("Visszalépés a főmenübe");
		initButtonsPositions();
		addPositionsButtonsToPanel();
		makeButtonsInvisble();
		addPositionButtonListeners();
		backToMainMenuButton.addActionListener(backToMainMenuListener);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setContentPane(icon);
		iconPanel.setOpaque(false);
		iconPanel.setLayout(null);
		iconPanel.add(backToMainMenuButton);
		backToMainMenuButton.setBounds(0,0,170,30);
		GUI.frame.dispose();
		gameFrame.setBackground(new Color(189,154,122));
		gameFrame.setLayout(new BorderLayout());
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
		gameFrame.add(iconPanel);
	}
	private static void newGame(){

	}
	private static void initButtonsPositions(){
		for(int i=1;i<25;i++){
			positionButtons[i] = new JButton();
		}
		positionButtons[1].setBounds(200,57,60,60);
		positionButtons[2].setBounds(478,57,60,60);
		positionButtons[3].setBounds(750,57,60,60);
		positionButtons[4].setBounds(286,147,60,60);
		positionButtons[5].setBounds(478,147,60,60);
		positionButtons[6].setBounds(666,147,60,60);
		positionButtons[7].setBounds(374,230,60,60);
		positionButtons[8].setBounds(478,230,60,60);
		positionButtons[9].setBounds(582,230,60,60);
		positionButtons[10].setBounds(200,337,60,60);
		positionButtons[11].setBounds(286,337,60,60);
		positionButtons[12].setBounds(374,337,60,60);
		positionButtons[13].setBounds(582,337,60,60);
		positionButtons[14].setBounds(666,337,60,60);
		positionButtons[15].setBounds(750,337,60,60);
		positionButtons[16].setBounds(374,444,60,60);
		positionButtons[17].setBounds(478,444,60,60);
		positionButtons[18].setBounds(582,444,60,60);
		positionButtons[19].setBounds(286,527,60,60);
		positionButtons[20].setBounds(478,527,60,60);
		positionButtons[21].setBounds(666,527,60,60);
		positionButtons[22].setBounds(200,610,60,60);
		positionButtons[23].setBounds(478,610,60,60);
		positionButtons[24].setBounds(750,610,60,60);
	}
	private static void addPositionsButtonsToPanel(){
		for(int i=1;i<25;i++){
			iconPanel.add(positionButtons[i]);
		}
	}
	private static void makeButtonsInvisble(){
		for(int i=1;i<25;i++){
			positionButtons[i].setOpaque(false);
			positionButtons[i].setContentAreaFilled(false);
		}
	}
	private static void addPositionButtonListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].addActionListener(positionButtonsListener);
			positionButtons[i].putClientProperty("index", i);
		}
	}
	public boolean isRunning(){
		return gameFrame.isShowing();
	}
	public int getFirstPressedButton(){
		if(pressedPositions.isEmpty()){
			return 0;
		}
		return pressedPositions.poll();
	}
	public static ActionListener backToMainMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GUI.frame.setVisible(true);
			gameFrame.setVisible(false);
			gameFrame.dispose();
		}
	};
	public static ActionListener positionButtonsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clickedButton= (JButton) e.getSource();
			int buttonIndex = (int) clickedButton.getClientProperty("index");
			pressedPositions.add(buttonIndex);
		}
	};
}
