package mnk;

/**
 * game mode, handle game data
 *
 */
public class MNKModel {
	//chess score, used to evaluate the chess position 
	public static int SCOREZERO = 0; //0 continuous same chess, score 0 
	public static int SCOREONE = 1; //1 continuous same chess, score 1 
	public static int SCORETWO = 20; //2 continuous same chess, score 20 
	public static int SCORETHREE = 100; //3 continuous same chess, score 100 
	public static int SCOREFOUR = 1000; //4 continuous same chess, score 1000 
	
	/**
	 * construction
	 */
	public MNKModel() {
		this.depth = 1; //initialized search depth is one
		
		//init the game matrix, every element is '-', standing for the chess is not put 
		this.gameMaxtrix = new char[4][5];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				this.gameMaxtrix[i][j] = '-';
			}
		}
		
		this.bestStep = new MNKPos();
	}
	
	/**
	 * set search depth, 3 for Easy, 5 for Normal, 7 for Hard
	 * @param mode, game mode: Easy, Normal, Hard
	 * 
	 */
	public void setMode(String mode) {
		if(mode.equals("Easy")) {
			this.depth = 3;
		} else if(mode.equals("Normal")) {
			this.depth = 5;
		} else if(mode.equals("Hard")) {
			this.depth = 7;
		} else {
			this.depth = 1;
		}
		
	}
	
	/**
	 * Computer or Player put one chess on the board, the set the related matrix
	 * @param chess, the Computer chess is 'X' or 'O"
	 * @param pos, the chess position, including row and column
	 */
	public void setNextStep(char chess, MNKPos pos) {
		this.gameMaxtrix[pos.row][pos.column] = chess;
	}
	
	/**
	 * get the computer click position, according to the alpha-beta search
	 * @param pos, the best chess position for Computer
	 * @return success return 0, failed return -1
	 */
	public int getNextStep(MNKPos pos) {
		this.bestStep.row = -1;
		this.bestStep.column = -1;
		//get the best position
		this.alphaBetaSearch(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, 'X');
		if(this.bestStep.row < 0 || this.bestStep.column < 0) {
			return -1;
		}
		
		//the set the related matrix
		pos.row = this.bestStep.row;
		pos.column = this.bestStep.column;
		this.gameMaxtrix[this.bestStep.row][this.bestStep.column] = 'X';
		return 0;
	}
	
	/**
	 * get the game winner, according to the game matrix
	 * @return game continue return 0, Player wins return 1, Computer wins return 2, No one wins return -1
	 */
	public int getWinner() {
		//row, get every 4 continuous chess, the 4 continuous chess is all the same, game over
		for(int i = 0; i < 4; i++) {
			String row1 = String.valueOf(this.gameMaxtrix[i][0]) +  String.valueOf(this.gameMaxtrix[i][1]) 
						+  String.valueOf(this.gameMaxtrix[i][2]) +  String.valueOf(this.gameMaxtrix[i][3]);
			if(row1.equals("OOOO")) {
				return 1; //4 'O', Player wins
			}
			if(row1.equals("XXXX")) {
				return 2; //4 'X', Computer wins
			}
			
			String row2 = String.valueOf(this.gameMaxtrix[i][1]) +  String.valueOf(this.gameMaxtrix[i][2]) 
					+  String.valueOf(this.gameMaxtrix[i][3]) +  String.valueOf(this.gameMaxtrix[i][4]);
			if(row2.equals("OOOO")) {
				return 1; //4 'O', Player wins
			}
			if(row2.equals("XXXX")) {
				return 2; //4 'X', Computer wins
			}
			
		}
		
		//column, get every 4 continuous chess, the 4 continuous chess is all the same, game over
		for(int i = 0; i < 5; i++) {
			String column = String.valueOf(this.gameMaxtrix[0][i]) +  String.valueOf(this.gameMaxtrix[1][i]) 
						+  String.valueOf(this.gameMaxtrix[2][i]) +  String.valueOf(this.gameMaxtrix[3][i]);
			if(column.equals("OOOO")) {
				return 1; //4 'O', Player wins
			} 
			if(column.equals("XXXX")) {
				return 2; //4 'X', Computer wins
			}
			
		}
		
		//diagonal, for 4*5 matrix, only 4 diagonals
		String diagonal1 = String.valueOf(this.gameMaxtrix[0][0]) +  String.valueOf(this.gameMaxtrix[1][1]) 
				+  String.valueOf(this.gameMaxtrix[2][2]) +  String.valueOf(this.gameMaxtrix[3][3]);
		if(diagonal1.equals("OOOO")) {
			return 1; //4 'O', Player wins
		}
		if(diagonal1.equals("XXXX")) {
			return 2; //4 'X', Computer wins
		}
		
		String diagonal2 = String.valueOf(this.gameMaxtrix[0][1]) +  String.valueOf(this.gameMaxtrix[1][2]) 
				+  String.valueOf(this.gameMaxtrix[2][3]) +  String.valueOf(this.gameMaxtrix[3][4]);
		if(diagonal2.equals("OOOO")) {
			return 1; //4 'O', Player wins
		} 
		if(diagonal2.equals("XXXX")) {
			return 2; //4 'X', Computer wins
		}
		
		String diagonal3 = String.valueOf(this.gameMaxtrix[3][0]) +  String.valueOf(this.gameMaxtrix[2][1]) 
				+  String.valueOf(this.gameMaxtrix[1][2]) +  String.valueOf(this.gameMaxtrix[0][3]);
		if(diagonal3.equals("OOOO")) {
			return 1; //4 'O', Player wins
		}
		if(diagonal3.equals("XXXX")) {
			return 2; //4 'X', Computer wins
		}
		
		String diagonal4 = String.valueOf(this.gameMaxtrix[3][1]) +  String.valueOf(this.gameMaxtrix[2][2]) 
				+  String.valueOf(this.gameMaxtrix[1][3]) +  String.valueOf(this.gameMaxtrix[0][4]);
		if(diagonal4.equals("OOOO")) {
			return 1; //4 'O', Player wins
		}
		if(diagonal4.equals("XXXX")) {
			return 2; //4 'X', Computer wins
		}
		
		//no one wins, no available chess on the board, return -1 for draw
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				if(this.gameMaxtrix[i][j] != '-') {					
					cnt++;
				}
			}
		}
		if(cnt == 20) {
			return -1;
		}
		
		//game still on
		return 0;
	}
	
	/**
	 * return the next chess
	 * @param curChess
	 * @return current 'X' return 'O', current 'O' return X 
	 */
	private char changeChess(char curChess) {
		if(curChess == 'X') {
			return 'O';
		} else if(curChess == 'O') {
			return 'X';
		}
		return 'X';
	}
	
	/**
	 * score the String(consist of four characters, like O-X-), and return the score
	 * @param str, consisting of 4 chess
	 * @param chess, scored chess, 'X' or 'O'
	 * @return final score
	 */
	private int getStringScore(String str, char chess) {
		if(chess == 'X') {
			if(str.equals("X---") || str.equals("-X--") || str.equals("--X-") || str.equals("---X")) {
				return SCOREONE; //1 continuous same chess
			}
			if(str.equals("XX--") || str.equals("-XX-") || str.equals("-XX")) {
				return SCORETWO; //2 continuous same chess
			}
			if(str.equals("XXX-") || str.equals("-XXX")) {
				return SCORETHREE; //3 continuous same chess
			}
			if(str.equals("XXXX")) {
				return SCOREFOUR; //4 continuous same chess
			}
		}
		if(chess == 'O') {
			if(str.equals("O---") || str.equals("-O--") || str.equals("--O-") || str.equals("---O")) {
				return SCOREONE; //1 continuous same chess
			}
			if(str.equals("OO--") || str.equals("-OO-") || str.equals("-OO")) {
				return SCORETWO; //2 continuous same chess
			}
			if(str.equals("OOO-") || str.equals("-OOO")) {
				return SCORETHREE; //3 continuous same chess
			}
			if(str.equals("OOOO")) {
				return SCOREFOUR; //4 continuous same chess
			}
		}
		return 0;
	}
	
	/**
	 * evaluate function for alpha-beta search, score all the chess according the game matrix
	 * score is higher, the winning chance is bigger
	 * @param chess, scored the chess
	 * @return the whole board score
	 */
	private int evaluate(char chess) {
		int XScore = 0;
		int OScore = 0;
		
		//row, score every 4 continuous chess, both 'X' and 'O'
		for(int i = 0; i < 4; i++) {
			String row1 = String.valueOf(this.gameMaxtrix[i][0]) +  String.valueOf(this.gameMaxtrix[i][1]) 
						+  String.valueOf(this.gameMaxtrix[i][2]) +  String.valueOf(this.gameMaxtrix[i][3]);
			
			
			String row2 = String.valueOf(this.gameMaxtrix[i][1]) +  String.valueOf(this.gameMaxtrix[i][2]) 
					+  String.valueOf(this.gameMaxtrix[i][3]) +  String.valueOf(this.gameMaxtrix[i][4]);
			
			OScore += getStringScore(row1, 'O') + getStringScore(row2, 'O');
			XScore += (getStringScore(row1, 'X') + getStringScore(row2, 'X'));
		}
		
		//column, score every 4 continuous chess, both 'X' and 'O'
		for(int i = 0; i < 5; i++) {
			String column = String.valueOf(this.gameMaxtrix[0][i]) +  String.valueOf(this.gameMaxtrix[1][i]) 
						+  String.valueOf(this.gameMaxtrix[2][i]) +  String.valueOf(this.gameMaxtrix[3][i]);
			
			OScore += getStringScore(column, 'O');
			XScore += getStringScore(column, 'X');
		}
		
		//diagonal, for 4*5 matrix, only 4 diagonals, score all the diagonals
		String diagonal1 = String.valueOf(this.gameMaxtrix[0][0]) +  String.valueOf(this.gameMaxtrix[1][1]) 
				+  String.valueOf(this.gameMaxtrix[2][2]) +  String.valueOf(this.gameMaxtrix[3][3]);
		OScore += getStringScore(diagonal1, 'O');
		XScore += getStringScore(diagonal1, 'X');
		
		String diagonal2 = String.valueOf(this.gameMaxtrix[0][1]) +  String.valueOf(this.gameMaxtrix[1][2]) 
				+  String.valueOf(this.gameMaxtrix[2][3]) +  String.valueOf(this.gameMaxtrix[3][4]);
		OScore += getStringScore(diagonal2, 'O');
		XScore += getStringScore(diagonal2, 'X');
		
		String diagonal3 = String.valueOf(this.gameMaxtrix[3][0]) +  String.valueOf(this.gameMaxtrix[2][1]) 
				+  String.valueOf(this.gameMaxtrix[1][2]) +  String.valueOf(this.gameMaxtrix[0][3]);
		OScore += getStringScore(diagonal3, 'O');
		XScore += getStringScore(diagonal3, 'X');
		
		String diagonal4 = String.valueOf(this.gameMaxtrix[3][1]) +  String.valueOf(this.gameMaxtrix[2][2]) 
				+  String.valueOf(this.gameMaxtrix[1][3]) +  String.valueOf(this.gameMaxtrix[0][4]);
		OScore += getStringScore(diagonal4, 'O');
		XScore += getStringScore(diagonal4, 'X');
			
		if(chess == 'X')
			return (XScore - OScore); //if scored chess is X, then X score max, O score min
		else 
			return (OScore -XScore); //if scored chess is O, then O score max, X score min
	}
	
	/**
	 * alpha beta search, search the best chess position(highest score) for Computer
	 * @param depth, current search depth 
	 * @param alpha, maximum value
	 * @param beta, minimum value
	 * @param chess, current chess, 'O' or 'X'
	 * @return the the best value for current depth
	 */
	private int alphaBetaSearch(int depth, int alpha, int beta, char chess) {
		if(depth == 0 || getWinner() != 0) {
			return evaluate(chess); //if search depth is 0 or game is over, return the current evaluated score
		}
		
		int best = Integer.MIN_VALUE; //init the best value, min int value 
		
		//scan the game matrix, get every available chess position
		//get every score if Computer or Player put the chess on every available chess position
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				if(this.gameMaxtrix[i][j] == '-') {
					this.gameMaxtrix[i][j] = chess; //put one  available chess position
					int value = -1 * alphaBetaSearch(depth - 1, -1 * beta, -1 * alpha, changeChess(chess)); //get the score
					this.gameMaxtrix[i][j] = '-'; //undo putting
					if(value > best) { //the score is higher than the best value, then update the best value, and record the current best choice
						 best = value;
						 bestStep.row = i;
						 bestStep.column = j;
					}		
					
					//cut the useless search branch
			        if(best > alpha) 
			             alpha = best;
			        if(best >= beta)
			              break;
					
				}
			}
		}
		return best; //return the current best value
	}
	
	private int depth; //search depth, according to the game mode
	private MNKPos bestStep; //computer best choice for current matrix
	private char [][]gameMaxtrix; //4*5 matrix, stand for the game board
}
