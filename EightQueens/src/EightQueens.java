import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingWorker;

//import javax.swing.SwingWorker;
//import java.util.List;
//import java.util.ArrayList;

public class EightQueens {
	
	public  static int numSolutions = 0;
	public  static ChessSquarePanel c;
	private static final int ROWS = 8;
	private static final int COLS = 8;



	public static void main(String[] args) {
		c = new ChessSquarePanel();
		boolean[][] chessBoard = new boolean[ROWS][COLS];
		
		SwingWorker<String, boolean[][]> worker = new SwingWorker<String, boolean[][]> () {

			@Override // note that the return type matches the SwingWorker first type, 
			// can be Void (capital V)

			protected String doInBackground() {
				//call placeQueens and then publish if it is a valid board
				placeQueens(chessBoard, 0, 0, 0);
				publish(chessBoard);
				return null;
			}


			@Override  // executes on publish, but not for EVERY publish
			// Note that the List datatype matches the SwingWorker second type
			protected void process(List<boolean[][]> board) {
				displayBoard(board.get(board.size() - 1));
			}

			@Override  // executes when finished
			protected void done() {
				System.out.println("Solutions found: " + numSolutions);
			}
			
			public  boolean placeQueens (boolean[][] currentBoard, int row, int col, int numQueens) {
				//base cases
				if (row < 0)
					return false;
					if (numQueens == ROWS) {
						publish(currentBoard);
						numSolutions++;
						pause(5000);
						return true;
					}

				for (int i = col; i < COLS; i++) {
					if (isSafe (currentBoard, row, i)) {
						currentBoard[row] [i] = true;
						numQueens++;
						publish(currentBoard);
						pause(50);
						if(!placeQueens (currentBoard, ++row, 0, numQueens))
								return false;
					}	
				}
				//if none of the spaces in the row can be filled, backtrack and move previous piece
				row--;
				col = removeQueen(currentBoard, row);
				if (col == -1)
					return false;
				currentBoard[row][col] = false; //not working properly?
				numQueens--;
				while (col == COLS-1) {
					row--;
					col = removeQueen(currentBoard, row);
					if (col == -1)
						return false;
					currentBoard[row][col] = false;
					numQueens--;
				}
				if(!placeQueens(currentBoard, row, col+1, numQueens))
					return false;
				return false;
			}

			public  boolean isSafe (boolean[][] currentBoard, int row, int col) {
				//checks column
				for(int r = 0; r <= 7; r++) {
					if(currentBoard[r][col] == true) {
						return false;
					}
				}
				//checks diagonal
				int r = row;
				int c = col;
				while(r <= 7 && c <= 7) {
					if (currentBoard[r][c] == true) {
						return false;
					}
					r++;
					c++;
				}
				if (r > c) {
					r = r - c;
					c = 0;
				}
				else {
					c = c - r;
					r = 0;
				}
				while(r < row && c < col) {
					if (currentBoard[r][c] == true) {
						return false;
					}
					r++;
					c++;
				}
				//checks other diagonal
				int r2 = row;
				int c2 = col;
				while(r2 <= 7 && c2 >= 0) {
					if (currentBoard[r2][c2] == true) {
						return false;
					}
					r2++;
					c2--;
				}
				int temp = r2-1;
				r2 = c2+1;
				c2 = temp;
				while(r2 <= row && c2 >= col) {
					if (currentBoard[r2][c2] == true) {
						return false;
					}
					r2++;
					c2--;
				}
				return true;
			}
			
			public  int removeQueen (boolean[][] board, int row) {
				if (row < 0)
					return -1;
				for (int i = 0; i < COLS; i++) {
					if (board[row][i] == true) {
						board[row][i] = false;
						return i;
					}
				}
				return -1;
			}

			public  void displayBoard (boolean[][] board) {
				//will be implemented later once graphics are completed
				ChessSpace[][] graphicsBoard = c.getBoard();
				for (int r = 0; r < ROWS; r++) {
					for (int c = 0; c < COLS; c++) {
						if (board[r][c] == true)
							graphicsBoard[r][c].showQueen(true);
						else
							graphicsBoard[r][c].showQueen(false);
					}
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			private void pause(int ms) {
				try {
					Thread.sleep(ms);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		
		changeListener(c.getButton(), worker);
	}
	
	private static void changeListener(JButton button, SwingWorker<String, boolean[][]> sw) {
		button.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sw.execute();
		}
	});
	}
}