package map;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MapFrame extends JFrame {
	public int row,col,mine;
	public int[][] map;
	public boolean[][] visit;
	public JButton[][] mine_button;
	public static final int[] dx=new int[] {-1,0,1,0,-1,-1,1,1};
	public static final int[] dy=new int[] {0,1,0,-1,-1,1,1,-1};
	
	public int total_visit;
	
	public MapFrame(Setting setting) {
		this.row=setting.row; this.col=setting.col; this.mine=setting.mine; 
		this.map=calc_map(setting);
		mine_button=new JButton[row][col];
		visit=new boolean[row][col];
		total_visit=0;
		int fwidth=row/9*400,fheight=col/9*400+70;
		setSize(fwidth,fheight);
		JPanel TimeJpanel=new JPanel();
		JPanel MapJpanel=new JPanel();
		
		JLabel TimeText=new JLabel("Time : 0");
		TimeJpanel.add(TimeText);
		
		myTimer timer =new myTimer(TimeText);
	    Timer timelistener =new Timer(1000,timer);
	    timelistener.start();  //开始定时器
		
		MapJpanel.setPreferredSize(new Dimension(row/9*400,col/9*400));
		MapJpanel.setLayout(new GridLayout(row,col));
		for (int i=0;i<row*col;i++) {
			int x=i/this.col;
			int y=i%this.row;
			mine_button[x][y]=new JButton();
			mine_button[x][y].setBackground(new Color(81,168,221));
			mine_button[x][y].addActionListener(new click_listener(x,y));
			MapJpanel.add(mine_button[x][y]);
		}
		
		setLayout(new BorderLayout());
		add(TimeJpanel,BorderLayout.NORTH);
		add(MapJpanel,BorderLayout.SOUTH);		
	}
	
	class click_listener implements ActionListener {
		public int x,y;
		public click_listener(int x,int y) {
			this.x=x; this.y=y;
		}
		
		public void actionPerformed(ActionEvent event) {
			if (visit[this.x][this.y]) return;
			int num=map[this.x][this.y];
			if (num!=-1) {
				mine_button[this.x][this.y].setBackground(Color.WHITE);
				if (num==0) whitebfs(this.x,this.y);
				else {
					if (!visit[this.x][this.y]) total_visit++; visit[this.x][this.y]=true;
					mine_button[this.x][this.y].setText(""+num);
				}
				if (total_visit+mine==row*col) {
					//游戏成功
					int selection=JOptionPane.showConfirmDialog(null,"你成功排掉所有雷，是否重新开始？","游戏成功",
							JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (selection==JOptionPane.YES_OPTION) {
						//重新开始游戏
						dispose();
						Map.NewMap();
					}
				}
			} else {
				//游戏失败
				mine_button[this.x][this.y].setIcon(new ImageIcon("./Image/mine.jpg"));
				int selection=JOptionPane.showConfirmDialog(null,"你踩到雷了，是否重新开始？","游戏失败",
						JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (selection==JOptionPane.YES_OPTION) {
					//重新开始游戏    
					dispose();
					Map.NewMap();
				}
			}
		}
	
		public void whitebfs(int x,int y) {
			 Queue<Integer> q=new LinkedList<>();
			 q.offer(x*col+y);
			 while (q.size()>0) {
				 int now=q.poll();
				 x=now/col; y=now%col;
				 if (!visit[x][y]) total_visit++; visit[x][y]=true;
				 mine_button[x][y].setBackground(Color.WHITE);
				 around(x,y);
				 for (int i=0;i<4;i++) {
					 int tx=x+dx[i],ty=y+dy[i];
					 if (tx<0 || tx>=row || ty<0 || ty>=col) continue;
					 if (visit[tx][ty]) continue;
					 if (map[tx][ty]!=0) continue;
					 q.offer(tx*col+ty);
				 }
			 }
		}
		
		public void around(int x,int y) {
			for (int i=0;i<8;i++) {
				int tx=x+dx[i],ty=y+dy[i];
				if (tx<0 || tx>=row || ty<0 || ty>=col) continue;
				if (map[tx][ty]>0) {
					if (!visit[tx][ty]) total_visit++; visit[tx][ty]=true;
					mine_button[tx][ty].setBackground(Color.WHITE);
					if (map[tx][ty]>0)mine_button[tx][ty].setText(""+map[tx][ty]);
				}
			}
		}
	}
	
	/**
	 * 计时器
	 */
	class myTimer implements ActionListener {
		JLabel text;
		long start;
		public myTimer(JLabel text) {
			this.text=text;
			start=System.currentTimeMillis();
		}
		public void actionPerformed(ActionEvent event) {
			text.setText("Time : "+(System.currentTimeMillis()-start)/1000);
		}
	}
	
	/**
	 * 计算map数字大小
	 */
	public int[][] calc_map(Setting setting) {
		int[][] map=new int[setting.row+2][setting.col+2];
		for (int i=0;i<=setting.row+1;i++)
			for (int j=0;j<=setting.col+1;j++)
				map[i][j]=0;
		
		int now_mine=0;
		Random rand=new Random(System.currentTimeMillis());
		while (now_mine<setting.mine) {
			int mine_x=rand.nextInt(setting.row);
			int mine_y=rand.nextInt(setting.col);
			if (map[mine_x][mine_y]==0) {
				map[mine_x][mine_y]=-1;
				now_mine++;
			}
		}
		
		for (int i=1;i<=setting.row;i++)
			for (int j=1;j<=setting.col;j++) {
				if (map[i][j]==-1) continue;
				for (int k=0;k<8;k++) map[i][j]+=(map[i+dx[k]][j+dy[k]]==-1 ? 1 : 0);
			}	
		
		return map;
	}
}
