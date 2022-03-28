import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ChessSpace extends JLabel{
	
	//private Color backgroundColor;
	private static final int SIZE = 64;
//	private boolean showQueen;
	private final static Font font = new Font ("MStrebuchet", Font.BOLD, 48);
	private final static String whiteQueen = "\u2655";
	private final static String blackQueen = "\u265B";
	
	public ChessSpace(Color color) {
		super("", SwingConstants.CENTER);
		setBackground(color);
		setOpaque(true);
		setFont(font);
		setMySize(new Dimension(SIZE, SIZE));
//		setText(whiteQueen);
	}
	
	private void setMySize(Dimension d) {
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);
	}
	
	public void showQueen(boolean show) {
//		showQueen = show;
		if (show)
			setText(whiteQueen);
		else
			setText("");
	}
}
