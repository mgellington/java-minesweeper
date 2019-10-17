import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Grid extends JFrame implements ActionListener {
	Box[][] grid;
	int rows = 6;
	int columns = 6;
	JFrame f;
	JPanel p;
	
	public Grid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
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
		f.setSize(300,300);
		f.setVisible(true);
		
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Box) {
			Box b = (Box) e.getSource();
			b.setClicked(true);
			clicked(b);
		}
	}
	
	
	public void clicked(Box b) {
		System.out.println("Boom");
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				b = grid[x][y];
				if (b.isClicked() == true) {
					if (b.isBomb()) {
						b.setText("BOOM");
					} else {
						int touch = b.getNumberTouching();
						String text = String.format("%d", touch);
						b.setText(text);
					}
					b.setEnabled(false);
				}
			}
		}
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



}
