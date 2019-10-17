import javax.swing.JButton;

public class Box extends JButton {
	private int row;
	private int column;
	private boolean isClicked;
	private boolean isBomb;
	private boolean isTouching;
	private int numberTouching;
	
	public Box(int row, int column) {
		super("x");
		this.row = row;
		this.column = column;
		isClicked = false;
		isBomb = false;
	}
	
	public Box() {
		row = 0;
		column = 0;
		isClicked = false;
		isBomb = false;
		isTouching = false;
		numberTouching = 0;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	
	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(Box[] bombs) {
		boolean bomb = false;
		for (int k = 0; k < bombs.length; k++) {
			if ((row == bombs[k].getRow()) &&
			(column == bombs[k].getColumn())) {
				bomb = true;
			}
		}
		this.isBomb = bomb;
	}

	public int getNumberTouching() {
		return numberTouching;
	}

	public void setNumberTouching(int numberTouching) {
		this.numberTouching = numberTouching;
	}

	public boolean isTouching() {
		return isTouching;
	}

	public void setTouching(boolean isTouching) {
		this.isTouching = isTouching;
	}
	
	

}
