public class Ant {
	private static int orientation; //0 is south, 1 is west, 2 is north, 3 is east
	private static int r; //the ROW that the ant is on, where 0 is the top row
	private static int c; //the COLUMN that the ant is on, where 0 is the leftmost column
	
	//CONSRUCTOR for board with two colors
	public Ant(int rVal, int cVal, int initialOrient) {
		orientation = initialOrient; 
		r = rVal; c = cVal;
	}
	
	//turn LEFT by incrementing the orientation by 3 in mod 4
		//ex. 0 -> 3 would mean facing south to facing east
	public void turnLeft() {
		orientation = (orientation + 3) % 4;
	}
	
	//turn RIGHT by incrementing the orientation by 3 in mod 4
		//ex. 0 -> 1 would mean facing south to facing west
	public void turnRight() {
		orientation = (orientation + 1) % 4;
	}
	
	//moves the ant's POSITION by changing r and c
	public int[] move(int rBound, int cBound) {
		if (orientation == 0) {
			if (r + 1 <= rBound) r += 1;
			else r = 0;
		}
		else if (orientation == 1) {
			if (c - 1 >= 0) c -= 1;
			else c = cBound;
		}
		else if (orientation == 2) {
			if (r - 1 >= 0)r -= 1;
			else r = rBound;
		}
		else {
			if (c + 1 <= cBound) c += 1;
			else c = 0;
		}
		
		//returns the ant's new coordinates
		int[] coordinates = new int[2];
		coordinates[0] = r; coordinates[1] = c;
		return coordinates;
	}
}
