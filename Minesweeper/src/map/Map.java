package map;

import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Map {
	public static final int[] dx=new int[]{-1,-1,-1,0,1,1,1,0};
	public static final int[] dy=new int[]{-1,0,1,1,1,0,-1,-1};
	public Setting setting;
	public int[][] map;
	
	public Map() {
		NewMap();
	}
	
	public static void NewMap() {
		Setting setting=new Setting(9,9,10);
		EventQueue.invokeLater(()->{
			JFrame frame=new MapFrame(setting);
			frame.setTitle("Minesweeper");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});		
	}
}
