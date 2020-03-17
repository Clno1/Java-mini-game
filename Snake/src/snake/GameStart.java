package snake;

import java.awt.*;
import javax.swing.*;


public class GameStart {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame=new MapFrame();
			frame.setTitle("Snake");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});			
	}
}
