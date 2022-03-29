import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * @author Sarah Fleming
 * Eight Queens Assignment 8
 * ChessSpace class - each JLabel on the matrix in ChessSquarePanel
 * Last Edited: 3/29/22
 */
public class ChessSpace extends JLabel{
	
	/**
	 * size used for the dimensions of the space (8x8)
	 */
	private static final int SIZE = 64;
	/**
	 * sets the font that displays the queen
	 */
	private final static Font font = new Font ("MStrebuchet", Font.BOLD, 48);
	/**
	 * Unicode for the queen character
	 */
	private final static String whiteQueen = "\u2655";
	
	/**
	 * main constructor that sets the background, font, and size of the chess space
	 * @param color the background color of the chess space
	 */
	public ChessSpace(Color color) {
		super("", SwingConstants.CENTER);
		setBackground(color);
		setOpaque(true);
		setFont(font);
		setMySize(new Dimension(SIZE, SIZE));
	}
	
	/**
	 * sets the dimensions to preferred, minimum, and maximum size
	 * @param d the dimension of the chess space
	 */
	private void setMySize(Dimension d) {
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);
	}
	
	/**
	 * sets the text of the JLabel to the queen if true
	 * @param show true if the space has a queen
	 */
	public void showQueen(boolean show) {
		if (show)
			setText(whiteQueen);
		else
			setText("");
	}
}
