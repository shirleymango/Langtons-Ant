public class Board {
	private static char[][] board;
	private static char redChar;
	
	//CONSTRUCTOR for board with two colors
	public Board(int rSize, int cSize) {
		board = new char[rSize][cSize];
		for (int i = 0; i < rSize; i++) {
			for (int j = 0; j < cSize; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	//CONSTRUCTOR for board with three colors
		public Board(int rSize, int cSize, char c) {
			board = new char[rSize][cSize];
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					board[i][j] = ' ';
				}
			}
			redChar = c;
		}
	
	//RETURNS the COLOR of the tile at given coordinates
	public char checkColor (int rCoord, int cCoord) {
		return board[rCoord][cCoord];
	}
	
	//TOGGLES the COLOR of the tile at given coordinates
	public void changeColor (int rCoord, int cCoord, int numOfColors) {
		if (numOfColors == 2) {
			if (board[rCoord][cCoord] == ' ') {
				board[rCoord][cCoord] = '#';
			}
			else {
				board[rCoord][cCoord] = ' ';
			}
		}
		if (numOfColors == 3) {
			if (board[rCoord][cCoord] == ' ') {
				board[rCoord][cCoord] = '#';
			}
			else if (board[rCoord][cCoord] == '#') {
				board[rCoord][cCoord] = redChar;
			}
			else {
				board[rCoord][cCoord] = ' ';
			}
		}
	}
	
	//PRINTS the current board, ant is printed at given coordinates
	public void print (int rCoord, int cCoord) {
		//prints the top horizontal edge ----------
	    for (int i = 0; i < board[0].length + 2; i++) {
	      System.out.print("-");
	    }
	    System.out.println();
	    
	    //prints the board, ' ' for white and '#' for black
		for (int i = 0; i < board.length; i++) {
	      System.out.print("|"); //left vertical edge
				for (int j = 0; j < board[i].length; j++) {
					//'*' for the ant
					if (i == rCoord && j == cCoord) System.out.print('*');
					else System.out.print(board[i][j]);
				}
				System.out.println("|"); //right vertical edge
			}
		
		//prints the bottom horizontal edge ----------
	    for (int i = 0; i < board[0].length + 2; i++) {
	      System.out.print("-");
	    }
	    System.out.println();
	}

}