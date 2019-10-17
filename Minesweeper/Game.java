import java.util.Random;

public class Game {
	private int numBombs;
	private int rows;
	private int columns;
	private Box[] bombs;
	private Box[][] boxes;
	
	public Game(int numBombs, int rows, int columns) {
		this.numBombs = numBombs;
		this.rows = rows;
		this.columns = columns;
		this.bombs = new Box[numBombs];
		setUp();
	}
	
	public void setUp() {
		Grid grid = new Grid(rows, columns);
		boxes = grid.getGrid();
		Random random = new Random();
		for (int i = 0; i < numBombs; i++) {
			this.bombs[i] = new Box();
			int r = random.nextInt(rows-1);
			int c = random.nextInt(columns-1);
			this.bombs[i].setRow(r);
			this.bombs[i].setColumn(c);
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				boxes[i][j].setBomb(bombs);
				this.numberTouching(boxes[i][j]);
			}
		}
		
	}
	
	
	

	
	public Box[] getBombs() {
		return bombs;
	}



	public int numberTouching(Box box) {
		
		int counter = 0;
		
		for (int j = 0; j < numBombs; j++) {
			if (box.getRow() == bombs[j].getRow()) {
				if ((box.getColumn() == bombs[j].getColumn() + 1) ||
					(box.getColumn() == bombs[j].getColumn() - 1)) {
						counter++;
				}
			} else
			if (box.getColumn() == bombs[j].getColumn()) {
				if ((box.getRow() == bombs[j].getRow() + 1) ||
					(box.getRow() == bombs[j].getRow() - 1)) {
						counter++;
				}
			} else
			if (box.getRow() == bombs[j].getRow() - 1) {
				if ((box.getColumn() == bombs[j].getColumn() + 1) ||
					(box.getColumn() == bombs[j].getColumn() - 1)) {
						counter++;
				}
			} else
			if (box.getRow() == bombs[j].getRow() + 1) {
				if ((box.getColumn() == bombs[j].getColumn() + 1) ||
					(box.getColumn() == bombs[j].getColumn() - 1)) {
						counter++;
				}
			} 
		}
		
		box.setNumberTouching(counter);
		return counter;
	}
	
	public boolean isTouching(Box box) {
		boolean touching = false;
		if (numberTouching(box) > 0) {
			touching = true;
		}
		box.setTouching(touching);
		return touching;
	}
	
	

}
