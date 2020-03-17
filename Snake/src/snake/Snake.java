package snake;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Snake {
	public int direction;	//0¡ü  1¡ú  2¡ý  3¡û
	public LinkedList<Node> snakebody;
	public static final int[] dx=new int[] {-1,0,1,0};
	public static final int[] dy=new int[] {0,1,0,-1};
	
	public Snake() {
		snakebody=new LinkedList<>();
		snakebody.addLast(new Node(30,30));
		direction=3;
	}
	
	
}
