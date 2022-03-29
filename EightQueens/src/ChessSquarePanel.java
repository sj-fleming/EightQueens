import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;

/**
 * @author Sarah Fleming
 * Eight Queens Assignment 8
 * Last Edited: 3/29/22
 */
public class ChessSquarePanel extends JPanel {
	
	/**
	 * the two alternating colors used on the chess board
	 */
	private final static Color PRIMARY = Color.RED, ALTERNATE  = Color.BLACK;
	/**
	 * number of rows on the chess board
	 */
	private static int ROWS = 8;
	/**
	 * number of columns on the chess board
	 */
	private static int COLS = 8;
	/**
	 * matrix of ChessSpaces (JLabels) that make up the chess board
	 */
	private static ChessSpace[][] board;
	/**
	 * button that finds and displays all the solutions when pressed
	 */
	private JButton button;
	
	/**
	 * main constructor - creates the JFrame, calls setupPanel, and adds the JPanel and button to the JFrame
	 */
	public ChessSquarePanel() {
		super();
		JFrame frame = createFrame();
		setupPanel();
//		createSidePanels(frame);
		frame.add(this, BorderLayout.CENTER);
		button = new JButton("Find Solutions");
		frame.add(button, BorderLayout.PAGE_START);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * instantiates the matrix of chess spaces and sets their colors to red and black (alternating)
	 */
	private void setupPanel() {
		setLayout(new GridLayout(ROWS, COLS, 5, 5));
		board = new ChessSpace[ROWS][COLS];
		Color currentColor = PRIMARY;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				ChessSpace space = new ChessSpace(currentColor);
				add(space);
				board[r][c] = space;
				currentColor = swapColor(currentColor, PRIMARY, ALTERNATE);
			}
			currentColor = swapColor(currentColor, PRIMARY, ALTERNATE);
		}
	}
	
	/**
	 * @param color the color of the previous chess space
	 * @param first red
	 * @param second black
	 * @return the opposite color as was sent in
	 * switches black to red and red to black
	 */
	private Color swapColor(Color color, Color first, Color second) {
		return color.equals(first) ? second : first;
//		if (color.equals(first))
//			return second;
//		else
//			return first;
	}
	
	/**
	 * @return the created and instantiated JFrame
	 * sets default close operation, location, and size of the JFrame
	 */
	private JFrame createFrame() {
		JFrame f = new JFrame("Eight Queens");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //keeps the jframe open and event listeners listening until Xd
		f.setLocation(250, 50); // on your monitor screen: x,y
		f.setSize(800, 600); //width, height	
		//possible addition: add other things to frame (button that switches to the next solution, text that says how long solutions are taking, letter/number for each row/column, etc)	
		return f;
	}
	
	/**
	 * @return the private field board, matrix of chess spaces
	 * returns the board
	 */
	public ChessSpace[][] getBoard(){
		return board;
	}
	
	/**
	 * @return the private field button, a JButton that starts finding the solutions
	 * returns the JFrame's JButton
	 */
	public JButton getButton() {
		return button;
	}
}
