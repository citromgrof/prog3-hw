import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class GameFrame extends FileManager{
	public static JFrame gameFrame;
	private static  JButton[] positionButtons;
	private static JPanel iconPanel ;
	private static Stack<Integer> moveStack;
	private static Game game;

	public GameFrame() {
		super("winners.txt");
		reset();
	}

	/**
	 * Kezdőhelyzetbe hozza az ablakot.
	 */
	private void reset(){
		gameFrame= new JFrame("Malom");
		positionButtons= new JButton[25];
		iconPanel = new JPanel();
		game=new Game();
		moveStack=new Stack<>();
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
	}

	/**
	 * Az adott indexű gombnak egy képet állít be az alapján, hogy milyen korong szín lett átadva.
	 * @param index A gomb indexe.
	 * @param color Meghatározza, milyen képet kell beállítani.
	 */
	private  void changeButtonIcon(int index,Puck color){
		if(color==Puck.NONE){
			return;
		}
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
			positionButtons[i].setIcon(null);
		}
	}

	private  void addPlacePuckButtonListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].addActionListener(placePuckButtonListener);
			positionButtons[i].putClientProperty("index", i);
		}
	}

	public ActionListener backToMainMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gameFrame.dispose();
			GUI.frame.setVisible(true);
		}
	};
	/**
	 * A játék első fázisát bonyolítsa le, a korongok lerakását. Ha változott a játék fázis akkor az annak megfelelő ActionListener-t adja hozzá a gombokhoz. Amennyiben egy korong lerakása után malom keletkezett
	 * és a játékos aki lerakata a korongot le tud venni legalább egyet az ellenfél korngjai közül akkor
	 * a takePuckButtonListener-t adja hozzá a gombokhoz.
	 */
	public  ActionListener placePuckButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clickedButton= (JButton) e.getSource();
			int buttonIndex = (int) clickedButton.getClientProperty("index");
			Puck color=game.getCurrentTurnColor();
			Phase phase=game.getPhase();
			if(game.placePuck(buttonIndex,game.getCurrentTurnColor()) && phase==Phase.PLACING) {
				changeButtonIcon(buttonIndex,color);
			}
			int mills=game.isInAMill(buttonIndex);
			if(mills>0 && game.canColorTake(color)) {
				removePlaceActionListeners();
				addTakePuckButtonListeners();
			}
			if(game.getPhase()==Phase.MOVING) {
				removePlaceActionListeners();
				addMovePuckButtonListeners();
			}

		}
	};
	/**
	 * A lenyomott gombnak megfelelő korngot leveszi a tábláról, ha az lehetseges.
	 * Ha a művelettel a játek veget ert akkor elmenti a nyertes, valamint visszatér a főmenübe
	 * és kiírja a nyertes játékos színét, különben az aktuális fázis ActionListener-ét rendeli hozzá a gombokhoz.
	 */
	public ActionListener takePuckButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			JButton clickedButton= (JButton) e.getSource();
			int buttonIndex = (int) clickedButton.getClientProperty("index");
			if(!game.takePuck(buttonIndex,game.getPreviousTurnColor())) {
				return;}
			else {
			positionButtons[buttonIndex].setIcon(null);}

			if(game.isGameOver() && game.getPhase()!=Phase.PLACING) {
				gameFrame.setVisible(false);
				makeButtonsInvisble();
				String winner;
				if(game.getWinner()==Puck.BLACK){
					winner="A NYERTES FEKETE";
				}
				else if(game.getWinner()==Puck.WHITE){
					winner="A NYERTES FEHÉR";
				}else{
					winner="DÖNTETLEN";
				}
				logWinner(game.getWinner(),"winners.txt");
				GUI.textField.setText(winner);
				GUI.frame.setVisible(true);
				game=new Game();
			}
			removeTakeActionListeners();
			if(game.getPhase()==Phase.MOVING || game.getPhase()==Phase.LEAPING) {
				addMovePuckButtonListeners();
			}else {
				addPlacePuckButtonListeners();
			}
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

	/**
	 * A korongok mozgatását bonyolítsa le. Mivel egy korong mozgatásához két gomb lenyomása szükséges,
	 * ezért az első gomb indexét egy stack-be rakja, ez lesz az a korong, amit el szeretnénk elmozgatni,
	 * a második gombnyomás lesz az a pozíció ahová szeretnénk mozgatni a korongot.
	 * Ha érvényes a lépés a kezdőpont gombját láthatatlanná teszi és a végpontot, pedig annak a játékosnak a színére állítsa aki megtette a lépést.
	 */
	public ActionListener movePuckButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clickedButton= (JButton) e.getSource();
			int buttonIndex = (int) clickedButton.getClientProperty("index");
			if(moveStack.isEmpty()) {
				moveStack.push(buttonIndex);
			}
			else {
				if(!game.isGameOver()){
					int srcIndex=moveStack.pop();
                    Moves moveOutput=game.makeMove(srcIndex,buttonIndex,game.getCurrentTurnColor());
					if(moveOutput==Moves.VALID_MOVE){
						positionButtons[srcIndex].setIcon(null);
						changeButtonIcon(buttonIndex,game.getPreviousTurnColor());
						int mills=game.isInAMill(buttonIndex);
						if(mills>0 && game.canColorTake(game.getPreviousTurnColor())) {
							removeMoveActionListeners();
							addTakePuckButtonListeners();
						}
					}
				}
				else{
					gameFrame.setVisible(false);
					makeButtonsInvisble();
					String winner;
					if(game.getWinner()==Puck.BLACK){
						winner="A NYERTES FEKETE";
					}
					else if(game.getWinner()==Puck.WHITE){
						winner="A NYERTES FEHÉR";
					}else{
						winner="DÖNTETLEN";
					}
					logWinner(game.getWinner(),"winners.txt");
					GUI.textField.setText(winner);
					GUI.frame.setVisible(true);
					game=new Game();
				}
			}

		}
	};
	private void addMovePuckButtonListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].addActionListener(movePuckButtonListener);
			positionButtons[i].putClientProperty("index", i);
		}
	}
	private void removeMoveActionListeners(){
		for(int i=1;i<25;i++){
			positionButtons[i].removeActionListener(movePuckButtonListener);
		}
	}
}
