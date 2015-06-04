package mnk;

import java.awt.Color;

import javax.swing.ImageIcon;

import views.MainFrame;

/**
 * game controller, interactive between model and game ui
 *
 */
public class MNKController {
	/**
	 * construction
	 * @param mainFrame
	 */
	public MNKController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	/**
	 * get the one who goes first: Player or Computer, according to player radio button and computer radio button
	 * @return
	 */
	public String getFirst() {
		if(this.mainFrame.playerRadioButton.isSelected()) {
			return "Player";
		}
		if(this.mainFrame.computerRadioButton.isSelected()) {
			return "Computer";
		}
		
		return "";
	}
	
	/**
	 * get the game mode: easy, normal, hard, according to easy,normal,hard button
	 * @return
	 */
	public String getMode() {
		if(this.mainFrame.easyRadioButton.isSelected()) {
			return "Easy";
		}
		if(this.mainFrame.normalRadioButton.isSelected()) {
			return "Normal";
		}
		if(this.mainFrame.hardRadioButton.isSelected()) {
			return "Hard";
		}
		
		return "";
	}
	
	/**
	 *	start the game
	 */
	public void start() {
		//set all chess button available, clear all button icons
		for(int i = 0;i < 4; i++) {
			for(int j = 0;j < 5; j++) {
				this.mainFrame.chessButtonGroup[i][j].setEnabled(true);
				this.mainFrame.chessButtonGroup[i][j].setIcon(null);
			}
		}
		
		this.gameMode = getMode(); //get game mode:easy, normal, hard
		this.gameTurn = getFirst(); //get who plays first: player or computer
		//set game on message on the game view
		this.mainFrame.meesageField.setText("Messages: Game On! First is " + this.gameTurn + ". Mode is "+this.gameMode);
		//init mnk model
		model = new MNKModel();
		model.setMode(this.gameMode);
		
		if(this.gameTurn.equals("Computer")) { //if computer goes first, then computer put one chess
			this.computerClick();
		}
	}
	
	/**
	 * end the game
	 */
	public void end() {
		//set game over message on view
		this.mainFrame.meesageField.setText("Messages: Game Over!");
		
		//set all chess not available, clean at button icons
		for(int i = 0;i < 4; i++) {
			for(int j = 0;j < 5; j++) {
				this.mainFrame.chessButtonGroup[i][j].setEnabled(false);
				this.mainFrame.chessButtonGroup[i][j].setIcon(null);
			}
		}
	}
	
	/**
	 * whether it is time to Player to play
	 * @return the turn is Player return true, else return false
	 */
	public boolean isPlayerTurn() {
		if(this.gameTurn == "Player") {
			return true;
		}
		return false;
	}
	
	/**
	 * change the turn, if current turn is Player, next turn is Computer
	 * if current turn is Computer, next turn is Player
	 */
	public void changeTurn() {
		if(this.gameTurn == "Player") {
			this.gameTurn = "Computer";
		} else {
			this.gameTurn = "Player";
		} 
		
		this.mainFrame.meesageField.setText("Messages: Turn is " + this.gameTurn);
	}
	
	/**
	 * Player put one chess on the chess board
	 * @param btn the button that player clicked
	 * @return game continue return 0, Player wins return 1, Computer wins return 2
	 */
	public int playerClick(javax.swing.JButton btn) {
		//get the the button position [i][j] that Player clicked
		for(int i = 0;i < 4; i++) {
			for(int j = 0;j < 5; j++) {
				if(this.mainFrame.chessButtonGroup[i][j] == btn) {
					MNKPos pos = new MNKPos();
					pos.row = i;
					pos.column = j;
					this.model.setNextStep('O', pos); //set the MNK model array that related to the chess board	
					break;
				}
			}
		}
		
		//judge whether the game is over
		int ret = this.model.getWinner();
		if(ret != 0) {
			return ret;
		}
		
		//game is continue, change the turn
		changeTurn();
		return 0;
	}
	
	/**
	 * Computer put one chess on the game board
	 * @return
	 */
	public int computerClick() {
		//calculate the next chess position for Computer
		MNKPos pos = new MNKPos();
		int ret = this.model.getNextStep(pos);
		if(ret != 0) {
			return 1;
		}
		
		//set the X icon on which Computer clicked on the game board
		javax.swing.JButton btn = this.mainFrame.chessButtonGroup[pos.row][pos.column];
		btn.setIcon(new ImageIcon(getClass().getResource("/views/X.png")));
		btn.setEnabled(false);
		btn.setBackground(new Color(255,255,255));
		
		//judge whether the game is over
		ret = this.model.getWinner();
		if(ret != 0) {
			return ret;
		}
		
		//game is continue, change the turn
		changeTurn();
		return 0;
	}
	
	private MainFrame mainFrame; //game ui 
	private String gameTurn; //current one who plays
	private String gameMode; //game mode: easy, normal, hard
	private MNKModel model; //game model
}
