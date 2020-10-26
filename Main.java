import java.util.*;

public class Main {
	//instance variables used in main function and menu methods
	private static Scanner sc;
	private static String nameOfUser;
	private static int numOfRuns;
	private static int cont;
	private static int stepSize;
	//board size
	private static int numOfCols; 
	private static int numOfRows;
	//board colors
	private static int numOfColors;
	private static char redChar; //character to represent a red tile
	//ant initial position and orientation and name
	private static int rCoord; //row coordinate (which is like the y-coord)
	private static int cCoord; //column coordinate (which is like the x-coord)
	private static int initialOrient; //0 is south, 1 is west, 2 is north, 3 is east
	private static String nameOfAnt;
	
	
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		boolean continueProgram = true;	
		while (continueProgram) {
			//instantiating the board
			//receiving user input to determine board height, width, the ant's starting location, and the number of iterations
			menu_InstantiatingBoard();
			
			//constructing the board and ant
			Board board = new Board(numOfRows, numOfCols);;
			Ant ant = new Ant(rCoord, cCoord, initialOrient);
			if (numOfColors == 3) {
				board = new Board(numOfRows, numOfCols, redChar);
				ant = new Ant(rCoord, cCoord, initialOrient);
			}			
			
			//print the starting board
			System.out.println("STEP 0");
			board.print(rCoord, cCoord);
			
			//implementing Langton's Ant program
			for (int i = 1; i <= numOfRuns; i++) {
				
				board.changeColor(rCoord, cCoord, numOfColors);
				
				//if the ant was on a white tile (currently on black tile), turn right
				if (board.checkColor(rCoord, cCoord) == '#') {
					ant.turnRight();
				}
				//if the ant was on a black tile (currently on white tile), turn left
				else {
					ant.turnLeft();
				}
				
				//array to store the ant's new position [row, column]
				int[] newCoords = ant.move(numOfRows - 1, numOfCols - 1);
				rCoord = newCoords[0]; cCoord = newCoords[1];
				
				//printing the board every stepSize given by user
				if (i % stepSize == 0) {
					System.out.println("STEP " + i);
					board.print(rCoord, cCoord);
				}
			}
			
			//determine whether the user wants to run the entire program again or not
			menu_PlayAgain();		
			if (cont == 0) {
				continueProgram = false;
			}
		}
		
	}
	
	//menu interacting with the user - instantiating the board
	public static void menu_InstantiatingBoard() {
		System.out.println("Hello, please enter your name: ");
		nameOfUser = sc.nextLine();
		
		//ant's name
		System.out.println(nameOfUser + ", you will run the Langton's Ant simulation. What would you like the name of your ant to be?");
		nameOfAnt = sc.nextLine();
		
		//number of colors
		System.out.println("The board will start off with all white tiles. Would you like there to be two tile colors "
				+ "(white and black) or three tile colors (white, black, and red)? Please enter 2 or 3.");
		numOfColors = -1;
		//validate input
		while (numOfColors == -1) {
			numOfColors = validateInputNumber(2, 3);
		}
		//explain the characters that represent the tile colors
		if (numOfColors == 2) {
			System.out.println("Great! Your board will have two tile colors where ' ' represents white and '#' represents black.");
		}
		else {
			System.out.println("Great! Your board will have three tile colors. ' ' will represent white and '#' will represent black. "
					+ "What character would you like to use to represent a red tile?");
			//validate input
			int valid = -1;
			while (valid == -1) {
				valid = validateInputRedChar();
			}
			System.out.println("Awesome! When " + nameOfAnt + " lands on " + redChar+ ", " + nameOfAnt + " will continue moving in its current direction.");
		}
		
		//number of columns
		System.out.println("How many columns would you like the board to have? Please enter an integer between 0 and 100.");
		numOfCols = -1;
		//validate input
		while (numOfCols == -1) {
			numOfCols = validateInputNumber(1, 100);
		}
		
		//number of rows
		System.out.println("How many rows would you like the board to have? Please enter an integer between 0 and 100.");
		numOfRows = -1;
		//validate input
		while (numOfRows == -1) {
			numOfRows = validateInputNumber(1, 100);
		}
		
		//ant's initial column
		System.out.println("Please enter an integer between 0 and " + (numOfCols - 1) + " to be " + nameOfAnt + "'s initial x position.");
		cCoord = -1;
		//validate input
		while (cCoord == -1) {
			cCoord = validateInputNumber(0, numOfCols - 1);
		}
				
		//ant's initial row
		System.out.println("Please enter an integer between 0 and " + (numOfRows - 1) + " to be " + nameOfAnt + "'s initial y position.");
		rCoord = -1;
		//validate input
		while (rCoord == -1) {
			rCoord = validateInputNumber(0, numOfRows - 1);
		}
		
		//ant's initial orientation
		System.out.println("What initial direction would you like " + nameOfAnt + " to be facing? Please enter 0 for south, 1 for west, 2 for north, or 3 for south.");
		initialOrient = -1;
		//validate input
		while (initialOrient == -1) {
			initialOrient = validateInputNumber(0, 3);
		}
		
		//number of iterations
		System.out.println("How many steps do you want the simulation to run, " + nameOfUser +"? Please enter an integer between 1 and 20000.");
		numOfRuns = -1;
		//validate input
		while (numOfRuns == -1) {
			numOfRuns = validateInputNumber(1, 20000);
		}
		
		//step size
		System.out.println("This program will print the board every n steps. What step size n would you like? Please enter an integer between 1 and " + numOfRuns + ".");
		stepSize = -1;
		//validate input
		while (stepSize == -1) {
			stepSize = validateInputNumber(1, numOfRuns);
		}
	}
	
	//menu interacting with the user - determining whether to run the program again or not
	public static void menu_PlayAgain() {
		System.out.println("Would you like to run the Langton's Ant program again? Please enter Y for yes, N for no.");
		cont = -1;
		while (cont == -1) {
			cont = validateInputEndGameChar();
		}
	}
	
	//validates the inputs that are supposed to be numbers
	//if the input is not an integer or is out of bounds -> returns -1
	//otherwise -> returns the valid input
	public static int validateInputNumber (int lowerBound, int upperBound) {
		String input = sc.nextLine();
		int n = -1;
		try {
			n = Integer.parseInt(input);
			if (n < lowerBound || n > upperBound) {
				System.out.println("Please enter an integer between " + lowerBound + " and " + upperBound + " (inclusive).");
				n = -1;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a nonnegative integer - do not include commas, decimal points, or spaces.");
		}
		return n;
	}
	
	//validates the answer to the question at the end of running the program
	//0 -> quit, 1 -> play again, -1 -> invalid input
	public static int validateInputEndGameChar() {
		String input = sc.nextLine();
		if (input.equals("Y")) {
			return 1;
		}
		if (input.equals("N")) {
			return 0;
		}
		System.out.println("Please enter either Y or N.");
		return -1;
	}
	
	//validates input for representing a third color on the board
	public static int validateInputRedChar() {
		String input = sc.nextLine();
		if (input.length() != 1) {
			System.out.println("Please enter only one character.");
			return -1;
		}
		if (input.charAt(0) == ' ' || input.charAt(0) == '#' || input.charAt(0) == '*') {
			System.out.println("Please enter a character other than #, *, or a blank space.");
			return -1;
		}
		redChar = input.charAt(0);
		return 1;
	}
	

}