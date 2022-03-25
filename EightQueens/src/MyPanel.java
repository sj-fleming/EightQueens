import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	private Color color;
	
	public MyPanel(Color color, int width, int height) {
		super();
		this.color = color;
		setPreferredSize(new Dimension(width,height));
		setMinimumSize(new Dimension(width,height));
		setMaximumSize(new Dimension(width,height));
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0,  getWidth(),  getHeight());
	}

}
