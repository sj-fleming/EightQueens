import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingWorker;

//import javax.swing.SwingWorker;
//import java.util.List;
//import java.util.ArrayList;

/**
 * @author Sarah Fleming
 * Eight Queens Assignment 8
 * EightQueens class - places the eight queens on the board
 * Last Edited: 3/29/22
 */
public class EightQueens {
	
	/**
	 * tracks the number of solutions that have been found
	 */
	public  static int numSolutions = 0;
	/**
	 * JPanel that holds the chess spaces
	 */
	public  static ChessSquarePanel c;
	/**
	 * number of rows on the board
	 */
	private static final int ROWS = 8;
	/**
	 * number of columns on the board
	 */
	private static final int COLS = 8;


	/**
	 * instantiates the chess board boolean matrix and creates a swing worker that finds the solutions and displays them
	 * @param args n/a
	 */
	public static void main(String[] args) {
		c = new ChessSquarePanel();
		boolean[][] chessBoard = new boolean[ROWS][COLS];
		
		SwingWorker<String, boolean[][]> worker = new SwingWorker<String, boolean[][]> () {

			/**
			 * calls placeQueens for the first space on chessBoard
			 * @return type matches the SwingWorker first type (can be Void)
			 */
			@Override
			protected String doInBackground() {
				//call placeQueens and then publish if it is a valid board
				placeQueens(chessBoard, 0, 0, 0);
				return null;
			}

			/**
			 * executes on publish, but not every publish
			 * calls displayBoard, which updates the JFrame
			 * @param board List datatype matches the SwingWorker second type, holds the boolean matrix for queens
			 */
			@Override 
			protected void process(List<boolean[][]> board) {
				displayBoard(board.get(board.size() - 1));
			}

			/**
			 * executes when finished, prints the number of solutions found
			 */
			@Override
			protected void done() {
				System.out.println("Solutions found: " + numSolutions);
			}
			
			/**
			 * recursive method that places 8 queens on a chess board so that none of them can capture another
			 * @param currentBoard boolean matrix representing the chess board, space holds true if there is a queen there
			 * @param row the current empty row that the queen is being placed on
			 * @param col if there was a queen previously on the row, stores the column where this queen was
			 * @param numQueens the current number of queens on the board
			 * @return true if a queen is placed successfully
			 */
			public  boolean placeQueens (boolean[][] currentBoard, int row, int col, int numQueens) {
				//base cases
				if (row < 0)
					return false;
					if (numQueens == ROWS) {
						publish(currentBoard);
						numSolutions++;
						pause(2000);
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
				currentBoard[row][col] = false;
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

			/**
			 * checks to see if there are no queens in the same column or diagonal
			 * @param currentBoard boolean matrix representing the chess board, space holds true if there is a queen there
			 * @param row row of the space that is being checked
			 * @param col column of the space that is being checked
			 * @return true if the space is not in the path of any queen already on the board
			 */
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
			
			/**
			 * sets a queen in the row to false and returns the column it removed from
			 * @param board boolean matrix representing the chess board, space holds true if there is a queen there
			 * @param row the row the queen is being removed from
			 * @return the column that the queen that was removed was placed in
			 */
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

			/**
			 * traverse the boolean matrix and if a space is true, sets the text of the JLabel in the graphics matrix to display a queen
			 * @param board boolean matrix representation of the chess board
			 */
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
				pause(200);
			}
			
			/**
			 * pauses the thread for ms milliseconds
			 * @param ms milliseconds that the thread pauses for
			 */
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
	
	/**
	 * creates a new action listener for the JButton that executes the swing worker when pressed
	 * @param button JButton on the ChessSquarePanel
	 * @param sw the swing worker created in main
	 */
	private static void changeListener(JButton button, SwingWorker<String, boolean[][]> sw) {
		button.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sw.execute();
		}
	});
	}
}