package snake;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Node {
	public int x,y;		//ĞĞÁĞ
	public double px,py,len;	//×óÉÏ½ÇÏñËØ
	
	public Node(int x,int y) {
		this.x=x; this.y=y;
		px=x*5;
		py=y*5;
		len=5;
	}
}
