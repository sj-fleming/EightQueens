//import javax.swing.SwingWorker;
//import java.util.List;
//import java.util.ArrayList;

public class SwingWorker {

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

	public void placeQueens (boolean[][] currentBoard, int row, int column, int numQueens) {
		//base cases
		if (row > 7 || column > 7) {
			if (numQueens == 8) {
				displayBoard(currentBoard);
				//set the last queen placed to false
			}
			removeQueen(currentBoard, row--);
			placeQueens (currentBoard, row--, 0, numQueens--);
		}
		for (int i = 0; i <= 7; i++) {
			if (isSafe (currentBoard, row, i)) {
				currentBoard[row] [i] = true;
				placeQueens (currentBoard, row++, 0, numQueens++);
			}	
		}
		//if none of the spaces in the row can be filled, backtrack and move previous piece
		removeQueen(currentBoard, row--);
		placeQueens (currentBoard, row--, 0, numQueens--);	
	}

	public boolean isSafe (boolean[][] currentBoard, int row, int col) {
		//checks column
		for(int r = 0; r < 7; r++) {
			if(currentBoard[r][col] == true)
				return false;
		}
		//checks diagonal
		int r = row;
		int c = col;
		while(r <= 7 && c <= 7) {
			if (currentBoard[r][c] == true)
				return false;
			r++;
			c++;
		}
		r = 7 - c;
		c = 0;
		while(r < row && c < col) {
			if (currentBoard[r][c] == true)
				return false;
			r++;
			c++;
		}
		//checks other diagonal
		int r2 = row;
		int c2 = col;
		while(r2 <= 7 && c2 >= 0) {
			if (currentBoard[r2][c2] == true)
				return false;
			r2++;
			c2--;
		}
		int temp = r2;
		r2 = c2;
		c2 = temp;
		while(r2 <= row && c2 >= col) {
			if (currentBoard[r2][c2] == true)
				return false;
			r2++;
			c2--;
		}
		return true;
	}
	
	public void removeQueen (boolean[][] board, int row) {
		for (int i = 0; i < 7; i++) {
			if (board[row][i] == true) {
				board[row][i] = false;
				return;
			}
		}	
	}

	public void displayBoard (boolean[][] board) {
		//will be implemented later once graphics are completed
		for (int r = 0; r <= 7; r++) {
			for (int c = 0; c <= 7; c++) {
				if (board[r][c] == true)
					System.out.print("Q");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
//		new SwingWorker();
	}
}