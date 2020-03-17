package snake;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapFrame extends JFrame {
	public static final int WIDTH=600;
	public static final int HEIGHT=400;
	JPanel scoreJPanel,mapJPanel;
	
	public MapFrame() {
		Snake snake=new Snake();
		scoreJPanel=new ScoreJPanel(snake);
		mapJPanel=new MapJPanel(snake);
		
		setLayout(new BorderLayout());
		add(scoreJPanel,BorderLayout.NORTH);
		add(mapJPanel,BorderLayout.CENTER);
		pack();
		
		
		
		while (true) {
			System.out.println("H");
			scoreJPanel.repaint();
			mapJPanel.repaint();
		}
	}
}

class ScoreJPanel extends JPanel {
	Snake snake;
	
	public ScoreJPanel(Snake snake) {
		this.snake=snake;
		this.setBackground(new Color(120,120,120));
	}
	
	public Dimension getPreferredSize() { return new Dimension(MapFrame.WIDTH,30); }
}

class MapJPanel extends JPanel {
	Snake snake;
	
	public MapJPanel(Snake snake) {
		this.snake=snake;
		this.setBackground(new Color(120,120,120));
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		
		g2.setColor(new Color(67,67,67));
		for (int i=0;i<=MapFrame.HEIGHT;i+=5)
			g2.drawLine(0, i, MapFrame.WIDTH, i);
		for (int i=0;i<=MapFrame.WIDTH;i+=5)
			g2.drawLine(i, 0, i, 0+MapFrame.HEIGHT);
		
		//drawSnake(g2);
	}
	
	public void drawSnake(Graphics2D g2) {
		g2.setColor(new Color(67,67,67));
		for(Iterator iter=snake.snakebody.iterator();iter.hasNext();)  {
			Node point=(Node)iter.next();  
			System.out.println(point.px+" "+point.py);
		} 
	}
	
	public Dimension getPreferredSize() { return new Dimension(MapFrame.WIDTH,MapFrame.HEIGHT); }
}
