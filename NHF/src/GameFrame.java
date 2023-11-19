import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class GameFrame extends ButtonActions{
	public static JFrame gameFrame = new JFrame("Malom");
	private static final JButton[] positionButtons = new JButton[25];
	private static final JPanel iconPanel = new JPanel();
	private static Stack<Integer> moveStack=new Stack<>();
	private static final Game game=new Game();
	public GameFrame() {
		gameFrame.setSize(new Dimension(1024, 768));
		JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("/malom4.png")));
		JButton backToMainMenuButton = new JButton("Visszalépés a főmenübe");
		initButtonsPositions();
		addPositionsButtonsToPanel();
		makeButtonsInvisble();
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
		addPlacePuckButtonListeners();

		//newGame();
	}
	private  void newGame(){
		addPlacePuckButtonListeners();
	}
	private  void changeButtonIcon(int index,Puck color){
		if(color==Puck.WHITE){
			positionButtons[index].setIcon(new ImageIcon(this.getClass().getResource("/feher.png")));
		}else{
			positionButtons[index].setIcon(new ImageIcon(this.getClass().getResource("/fekete.png")));
		}
		positionButtons[index].setContentAreaFilled(false);
		positionButtons[index].setFocusPainted(false);
		positionButtons[index].setOpaque(false);
	}
	private static void initButtonsPositions(){
		for(int i=1;i<25;i++){
			positionButtons[i] = new JButton();
			positionButtons[i].putClientProperty("index", i);
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
	private static void makeButtonVisible(int index){
			positionButtons[index].setOpaque(true);
			positionButtons[index].setContentAreaFilled(true);
	}
	private  void addPlacePuckButtonListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].addActionListener(placePuckButtonListener);
			positionButtons[i].putClientProperty("index", i);
		}
	}

	public static ActionListener backToMainMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GUI.frame.setVisible(true);
			gameFrame.setVisible(false);
			gameFrame.dispose();
			System.exit(0);
		}
	};
	public  ActionListener placePuckButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clickedButton= (JButton) e.getSource();
			int buttonIndex = (int) clickedButton.getClientProperty("index");
			Puck color=game.getCurrentTurnColor();
			if(game.placePuck(buttonIndex,game.getCurrentTurnColor()) && game.getPhase()==Phase.PLACING) {
				changeButtonIcon(buttonIndex,color);
			}
			int mills=game.isInAMill(buttonIndex);
			//System.out.println(mills);
			if(mills>0 && game.canColorTake(color)) {
				removePlaceActionListeners();
				addTakePuckButtonListeners();
			}


		}
	};
	public ActionListener takePuckButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			JButton clickedButton= (JButton) e.getSource();
			int buttonIndex = (int) clickedButton.getClientProperty("index");
			if(!game.takePuck(buttonIndex,game.getPreviousTurnColor())) {
				return;}
			else {
			positionButtons[buttonIndex].setIcon(null);}
			removeTakeActionListeners();
			addPlacePuckButtonListeners();

		}
	};
	private void addTakePuckButtonListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].addActionListener(takePuckButtonListener);
			positionButtons[i].putClientProperty("index", i);
		}
	}
	private void removePlaceActionListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].removeActionListener(placePuckButtonListener);
		}
	}
	private void removeTakeActionListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].removeActionListener(takePuckButtonListener);
		}
	}
	public ActionListener movePuckButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
}
