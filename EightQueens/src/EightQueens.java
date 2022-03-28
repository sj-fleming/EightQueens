//import javax.swing.SwingWorker;
//import java.util.List;
//import java.util.ArrayList;

public class EightQueens {
	
	public static int numSolutions = 0;
	public static ChessSquarePanel c;
	private static final int ROWS = 8;
	private static final int COLS = 8;

//	public SwingWorker() {
//
//	}

//	private void todo(){
//
//		SwingWorker<String, Integer> worker = new SwingWorker<String, Integer> () {
//
//			@Override // note that the return type matches the SwingWorker first type, 
//			// can be Void (capital V)
//
//			protected String doInBackground() {
//				//call placeQueens and then publish if it is a valid board
//
//				//    publish((Integer) 34);
//				return null;
//			}
//
//
//			@Override  // executes on publish, but not for EVERY publish
//			// Note that the List datatype matches the SwingWorker second type
//
//			protected void process(List<Integer> digits) {
//			}
//
//			@Override  // executes when finished
//			protected void done() {
//
//			}
//
//		};
//
//		worker.execute();
//	}

	public static boolean placeQueens (boolean[][] currentBoard, int row, int col, int numQueens) {
		//base cases
//		System.out.println("row: " + row);
//		System.out.println("col: " + col);
		if (row < 0)
			return false;
//		if (row > 7) {
			if (numQueens == ROWS) {
				displayBoard(currentBoard);	
				numSolutions++;
				return true;
			}
			//set the last two queens placed to false (might only need to do one?)
//			col = removeQueen(currentBoard, row--);
//			if (col == -1)
//				return false;
//			currentBoard[row][col] = false;
//			numQueens--;
//			col = removeQueen(currentBoard, row-2);
//			if (col == -1)
//				return false;
//			currentBoard[row][col] = false;
//			numQueens--;
//			placeQueens (currentBoard, row-2, col, numQueens);
//			return false;
//		}
		for (int i = col; i < COLS; i++) {
			if (isSafe (currentBoard, row, i)) {
				currentBoard[row] [i] = true;
			//	row++;
				numQueens++;
//				System.out.println("numQueens: " + numQueens);
				//displayBoard(currentBoard);
				if(!placeQueens (currentBoard, ++row, 0, numQueens))
						return false;
			}	
		}
		//if none of the spaces in the row can be filled, backtrack and move previous piece
		//System.out.println("backtracking: " + row);
		//displayBoard(currentBoard);
		row--;
		col = removeQueen(currentBoard, row);
		if (col == -1)
			return false;
		currentBoard[row][col] = false; //not working properly?
		numQueens--;
//		System.out.println("calling remove queen from row: " + row + " and column: " + col);
		while (col == COLS-1) {
			row--;
			col = removeQueen(currentBoard, row);
			if (col == -1)
				return false;
			currentBoard[row][col] = false;
			numQueens--;
//			System.out.println("calling remove queen 2 from row: " + row + " and column: " + col);
		}
		if(!placeQueens(currentBoard, row, col+1, numQueens))
			return false;
		return false;
//		if(col == 7) {
//			row--;
//			col = removeQueen(currentBoard, row);
//		}
//		placeQueens (currentBoard, row--, col, numQueens--);	
	}

	public static boolean isSafe (boolean[][] currentBoard, int row, int col) {
		//System.out.println("testing row: " + row);
		//System.out.println("testing col: " + col);
		//checks column
		for(int r = 0; r <= 7; r++) {
			if(currentBoard[r][col] == true) {
//				System.out.println("same column: " + col);
				return false;
			}
		}
		//checks diagonal
		int r = row;
		int c = col;
		while(r <= 7 && c <= 7) {
			if (currentBoard[r][c] == true) {
//				System.out.println("diagonal 1: " + r + c);
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
//				System.out.println("diagonal 2: " + r + c);
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
//				System.out.println("diagonal 3: " + r2 + c2);
				return false;
			}
			r2++;
			c2--;
		}
		//System.out.println("c2: " + c2);
		//System.out.println("r2: " + r2);
		int temp = r2-1;
		r2 = c2+1;
		c2 = temp;
		//System.out.println("row: " + r2);
		//System.out.println("col: " + c2);
		while(r2 <= row && c2 >= col) {
			if (currentBoard[r2][c2] == true) {
//				System.out.println("diagonal 4: " + r2 + c2);
				return false;
			}
			r2++;
			c2--;
		}
		return true;
	}
	
	public static int removeQueen (boolean[][] board, int row) {
		if (row < 0)
			return -1;
		for (int i = 0; i < COLS; i++) {
			if (board[row][i] == true) {
				board[row][i] = false;
				return i;
				//return board;
			}
		}
		return -1;
//		System.out.println("queen removed: " + row);
//		displayBoard(board);
//		return board;
	}

	public static void displayBoard (boolean[][] board) {
		//will be implemented later once graphics are completed
		ChessSpace[][] graphicsBoard = c.getBoard();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (board[r][c] == true)
					graphicsBoard[r][c].showQueen(true);
				else
					graphicsBoard[r][c].showQueen(false);
			}
//			System.out.println();
		}
//		System.out.println("\n");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		c = new ChessSquarePanel();
//		new SwingWorker();
		boolean[][] chessBoard = new boolean[ROWS][COLS];
		placeQueens(chessBoard, 0, 0, 0);
		System.out.println("Solutions found: " + numSolutions);
	}
}