import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PacMan {
	double x, y;
	boolean canMove;
	
	BufferedImage pacmanR, pacmanL, pacmanU, pacmanD;	
	int direction, nextDirection;
	int boardX;
	int boardY;
	Board board;
	
	public PacMan(double sx, double sy) {
		x = sx;
		y = sy;
		boardX = (int)((x-610)/25);
		boardY = (int)((y-113)/25);
		direction = 0;
		loadContent();
	}
	
	public void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_UP)){
		 	nextDirection = 2;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN)){
			nextDirection = 3;
	    }
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT)){
			nextDirection = 0;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT)){
			nextDirection = 1;
		}
		//If at a node then it can change direction
		if(((x-610)%25) == 0 && ((y-113)%25) == 0) {
			boardX = (int)((x-610)/25);
			boardY = (int)((y-113)/25);
			Board.update(boardX, boardY);
			canMove = Board.nextMove(boardX,boardY, nextDirection);
			direction = nextDirection;
		}
		move();
	}
	
	//Move pacman
	public void move() {
		if(canMove) {
			switch(direction) {
				case 0: x+=2.5;
					break;
				case 1:	x+=-2.5;
					break;
				case 2:	y+=-2.5;
					break;
				case 3:	y+=2.5;
					break;
			}
		}
	}
	
	//Change pacman's location
	public void setLocation(int dx, int dy) {
		x = dx;
		y = dy;
	}
	
	//Render : draw Pacman
	public void render(Graphics2D g2d) {
		switch(direction) {
			case 0: g2d.drawImage(pacmanR, (int)x, (int)(y), null);
				break;
			case 1: g2d.drawImage(pacmanL, (int)x, (int)(y), null);
				break;
			case 2: g2d.drawImage(pacmanU, (int)x, (int)(y), null);
				break;
			case 3: g2d.drawImage(pacmanD, (int)x, (int)(y), null);
				break;
			//FIXME: Add other animational pacmen here
		}
	}
	
	//Load all images of movement of pacman
	public void loadContent() {
		try {
			pacmanR = ImageIO.read(getClass().getResourceAsStream("/pacmanR.png"));
			pacmanL = ImageIO.read(getClass().getResourceAsStream("/pacmanL.png"));
			pacmanU = ImageIO.read(getClass().getResourceAsStream("/pacmanU.png"));
			pacmanD = ImageIO.read(getClass().getResourceAsStream("/pacmanD.png"));
			//FIXME: Add other directional images here
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}