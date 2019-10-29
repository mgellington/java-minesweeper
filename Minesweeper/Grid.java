import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;


public class Grid extends JFrame implements ActionListener {
	Box[][] grid;
	int rows;
	int columns;
	int numBombs;
	JFrame f;
	JPanel p;
	boolean hasWon;
	
	public Grid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		hasWon = false;
		setGrid();	
	}
	
	
	public void setGrid() {
		
		f = new JFrame("Minesweeper");


		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		p.setLayout(new GridLayout(rows,columns));
		

		
		grid = new Box[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Box(i, j);
				grid[i][j].addActionListener(this);
				p.add(grid[i][j]);
			}
		}
		
		f.add(p);
		f.setSize(500,500);
		f.setVisible(true);
		
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Box) {
			Box b = (Box) e.getSource();
			b.setClicked(true);
			b.setMarked(false);
			clicked(b);
			popUp(b);
		}
	}
	
	
	public void clicked(Box b) {
		if (b.isMarked()) {
			b.setText("???");
			b.setEnabled(true);
			handleResult(b);
		} else if (b.isUncovered()) {
			if (b.isBomb()) {
				b.setText("BOOM");
				handleResult(b);
			} else {
				int touch = b.getNumberTouching();
				if (touch == 0) {
					clearZeros(b);
				}
				String text = String.format("%d", touch);
				b.setText(text);
				handleResult(b);
			}
			b.setEnabled(false);
		}
	}
	
	
	public void popUp(Box b) {
		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(400,400);
		JPopupMenu popupbox = new JPopupMenu();
		JMenuItem mark = new JMenuItem("Flag");
		JMenuItem uncover = new JMenuItem("Uncover");
		popupbox.add(mark);
		popupbox.add(uncover);
		b.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				popupbox.show(b, e.getX(), e.getY());
			}
		});
		mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setMarked(true);
				clicked(b);
				popupbox.setVisible(false);
			}
		});
		uncover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setUncovered(true);
				clicked(b);
				popupbox.setVisible(false);
			}
		});
		f.add(label);
		f.setLayout(null);
		f.add(popupbox);
	}
	
	// only clears zeros once?
	public void clearZeros(Box b) {
		int x = b.getRow();
		int y = b.getColumn();
		for (int i=x-1; i<=x+1; i++) {
			for (int j=y-1; j<=y+1; j++) {
				if (i <= rows - 1 && i >= 0 && j >= 0  && j <= columns - 1) {
					if (!grid[i][j].isClicked()) {
						if (grid[i][j].getNumberTouching() == 0) {
							grid[i][j].setClicked(true);
							grid[i][j].setUncovered(true);
							clicked(grid[i][j]);
						}
					}
				}
			}
		}
	}
	
	public void handleResult(Box b) {
		setHasWon();
		
		// for losers
		if (b.isUncovered() && b.isBomb()) { 
			
			if (JOptionPane.showConfirmDialog(null, "You Lose", "Would you like to play again?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				startOver();
			} else {
				System.exit(0);
			};

			
		// for winners
		} else if (isHasWon()) {
			
			if (JOptionPane.showConfirmDialog(null, "You Won", "Would you like to play again?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				startOver();
			} else {
				System.exit(0);
			};

		}
		
		

	}
	
	public void startOver() {
		f.setVisible(false);
		new Game();
		
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}

	public Box[][] getGrid() {
		return grid;
	}
	
	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon() {
		boolean won = false;
		int flaggedBombCounter = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j].isMarked() && grid[i][j].isBomb()) {
					flaggedBombCounter++;
				}
			}
		}
		if (flaggedBombCounter == numBombs) {
			won = true;
		}
		this.hasWon = won;
	}


	public int getNumBombs() {
		return numBombs;
	}


	public void setNumBombs(int numBombs) {
		this.numBombs = numBombs;
	}
	
	




}
