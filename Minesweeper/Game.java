import java.util.Random;

import javax.swing.JOptionPane;

public class Game {
	private int numBombs;
	private int rows;
	private int columns;
	private Box[] bombs;
	private Box[][] boxes;
	private Grid grid;
	private Level level;
	
	public Game() {
		requestLevel();
		setLevel(level);
		this.bombs = new Box[numBombs];
		grid = new Grid(rows, columns);
		setUp();
	}
	
	public void requestLevel() {
		Object[] options = {"Easy", "Medium", "Hard"};
		int n = JOptionPane.showOptionDialog(null, "Pick A Level", null,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
		if (n == JOptionPane.YES_OPTION) {
			this.level = Level.EASY;
		} else if (n == JOptionPane.NO_OPTION) {
			this.level = Level.MEDIUM;
		} else {
			this.level = Level.HARD;
		}
	}
	
	public void setLevel(Level level) {
		switch (level) {
		case EASY:
			this.rows = 7;
			this.columns = 7;
			this.numBombs = 2;
			break;
			
		case MEDIUM:
			this.rows = 10;
			this.columns = 10;
			this.numBombs = 7;
			break;
			
		case HARD:
			this.rows = 20;
			this.columns = 20;
			this.numBombs = 12;
			break;
		}
		
	}
	
	public void setUp() {
		// Grid grid = new Grid(rows, columns);
		boxes = grid.getGrid();
		grid.setNumBombs(numBombs);
		Random random = new Random();
		int counter = 0;
		for (int i = 0; i < numBombs; i++) {
			this.bombs[i] = new Box();
			// same row/column combination cannot be repeated
			int r = random.nextInt(rows-1);
			int c = random.nextInt(columns-1);
			r = this.changeBombDuplicates(r, c, counter);
			this.bombs[i].setRow(r);
			this.bombs[i].setColumn(c);
			counter++;
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				boxes[i][j].setBomb(bombs);
				this.numberTouching(boxes[i][j]);
			}
		}
		
	}
	
	public int changeBombDuplicates(int r, int c, int counter) {
		for (int i = 0; i < counter; i++) {
			if (r == this.bombs[i].getRow()) {
				if (c == this.bombs[i].getColumn()) {
					Random random = new Random();
					int newRow = random.nextInt(rows-1);
					return newRow;
				}
			}
		}
		return r;
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
