import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board {
							   //1 = wall  ;  0 = dots  ;  2 = big dot  ;  3 = blank		28 wide, 31 high, 25 pixels
	public static int[][] board = {{ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }, //1
								  { 1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1 },	//2
								  { 1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1 },	//3
								  { 1,2,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,2,1 },	//4
								  { 1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1 },	//5
								  { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 },	//6
								  { 1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1 },	//7
								  { 1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1 },	//8
								  { 1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1 },	//9
								  { 1,1,1,1,1,1,0,1,1,1,1,1,3,1,1,3,1,1,1,1,1,0,1,1,1,1,1,1 },	//10
								  { 1,1,1,1,1,1,0,1,1,1,1,1,3,1,1,3,1,1,1,1,1,0,1,1,1,1,1,1 },	//11
								  { 1,1,1,1,1,1,0,1,1,3,3,3,3,3,3,3,3,3,3,1,1,0,1,1,1,1,1,1 },	//12
								  { 1,1,1,1,1,1,0,1,1,3,1,1,1,1,1,1,1,1,3,1,1,0,1,1,1,1,1,1 },	//13
								  { 1,1,1,1,1,1,0,1,1,3,1,3,3,3,3,3,3,1,3,1,1,0,1,1,1,1,1,1 },	//14
								  { 3,3,3,3,3,3,0,3,3,3,1,3,3,3,3,3,3,1,3,3,3,0,3,3,3,3,3,3 },	//15
								  { 1,1,1,1,1,1,0,1,1,3,1,3,3,3,3,3,3,1,3,1,1,0,1,1,1,1,1,1 },	//16
								  { 1,1,1,1,1,1,0,1,1,3,1,1,1,1,1,1,1,1,3,1,1,0,1,1,1,1,1,1 },	//17
								  { 1,1,1,1,1,1,0,1,1,3,3,3,3,3,3,3,3,3,3,1,1,0,1,1,1,1,1,1 },	//18
								  { 1,1,1,1,1,1,0,1,1,3,1,1,1,1,1,1,1,1,3,1,1,0,1,1,1,1,1,1 },	//19
								  { 1,1,1,1,1,1,0,1,1,3,1,1,1,1,1,1,1,1,3,1,1,0,1,1,1,1,1,1 },	//20
								  { 1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1 },	//21
								  { 1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1 },	//22
								  { 1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1 },	//23
								  { 1,2,0,0,1,1,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,1,1,0,0,2,1 },	//24
								  { 1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1 },	//25
								  { 1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1 },	//26
								  { 1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1 },	//27
								  { 1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1 },	//28
								  { 1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1 },	//29
								  { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 },	//30
								  { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }};	//31
	
	public BufferedImage smallDot;
	public BufferedImage bigDot;
	public static int points = 0;
	
	public Board() {
		loadContent();
	}
	
	//Draw the board
	public void renderUpdate(Graphics2D g2d, int startingX, int startingY) {
		int newX = ((startingX - 610) / 25);
		int newY = ((startingY - 113) / 25);
		int pX = (newX * 25) + 610;	//start at pixel 610
		int pY = (newY * 25) + 113;	//start at pixel 113
		for(int i = newY; i < newY + 4; i++) {
			for(int j = newX; j < newX + 3; j++) {
				if(board[i][j] ==  0) {
					g2d.drawImage(smallDot, pX, pY, null);
				}
				else if(board[i][j] == 2) {
					g2d.drawImage(bigDot, pX, pY, null);
				}
				pX += 25;
			}
			pX = (newX * 25) + 610;
			pY += 25;
		}
	}
	
	public void render(Graphics2D g2d) {
		int pX = 610;	//start at pixel 610
		int pY = 113;	//start at pixel 113
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 36));
		g2d.drawString(points + "", 800, 100);
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] ==  0) {
					g2d.drawImage(smallDot, pX, pY, null);
				}
				else if(board[i][j] == 2) {
					g2d.drawImage(bigDot, pX, pY, null);
				}
				pX += 25;
			}
			pX = 610;
			pY += 25;
		}
	}
	
	//Load the images of the dots
	public void loadContent() {
		try {
			smallDot = ImageIO.read(getClass().getResourceAsStream("/smallDot.png"));
			bigDot = ImageIO.read(getClass().getResourceAsStream("/bigDot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		
	//return the type of node
	public static boolean nextMove(int x, int y, int nD) {
		switch(nD) {
			case 0: if(board[y][x+1] == 1) return false;
				break;
			case 1: if(board[y][x-1] == 1) return false;
				break;
			case 2: if(board[y-1][x] == 1) return false;
				break;
			case 3: if(board[y+1][x] == 1) return false;
				break;
		}
		return true;
	}
	
	//make the board change depending on where player is
	public static void update(int x, int y) {
		if(board[y][x] == 0) {
			points += 10;
			board[y][x] = 3;
		}
		else if(board[y][x] == 2) {
			
		}
	}

}
