import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Sarah Fleming
 * Eight Queens Assignment 8
 * Last Edited: 3/17/22
 */
public class ChessSquarePanel extends JPanel {
	
	private final static Color PRIMARY = Color.RED, ALTERNATE  = Color.BLACK;
	private static int ROWS = 8;
	private static int COLS = 8;
	public static ChessSpace[][] board;
	public JButton button;
	
	public ChessSquarePanel() {
		super();
		JFrame frame = createFrame();
		setupPanel();
//		createSidePanels(frame);
		frame.add(this, BorderLayout.CENTER);
		button = new JButton("Next Solution");
		frame.add(button, BorderLayout.PAGE_START);
		frame.pack();
		frame.setVisible(true);
	}
	
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
	
	private Color swapColor(Color color, Color first, Color second) {
		return color.equals(first) ? second : first;
//		if (color.equals(first))
//			return second;
//		else
//			return first;
	}
	
	private JFrame createFrame() {
		JFrame f = new JFrame("Eight Queens");
		
		//add an icon to top left corner
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //keeps the jframe open and event listeners listening until Xd
		f.setLocation(250, 50); // on your monitor screen: x,y
		f.setSize(800, 600); //width, height
		
		//to do: add other things to frame (button that switches to the next solution, text that says how long solutions are taking, letter/number for each row/column, etc)
		
		return f;
	}
	
//	private void createSidePanels(JFrame f) {
//		f.add(createButton("Hello"), BorderLayout.PAGE_START);
//		//f.add(new MyPanel(Color.GREEN, 600, 20), BorderLayout.PAGE_START);
//		//f.add(new MyPanel(Color.YELLOW, 20, 20), BorderLayout.LINE_START);
//	}
//	
//	private JButton createButton(String title) {
//		JButton button = new JButton(title);
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("I hit the button");
//			}
//		});
//		return button;
//	}
	
	public ChessSpace[][] getBoard(){
		return board;
	}
}
