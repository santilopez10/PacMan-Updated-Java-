import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.swing.JPanel;

public class PacManPanel extends JPanel implements Runnable {
	public final int WIDTH;
	public final int HEIGHT;
	public final int SCALE;
	
	Graphics2D g2d;
	BufferedImage image;
	Board board;
	PacMan pacman;
	Keyboard keyboard;
	
	public PacManPanel(int w, int h, int s) {
		WIDTH = w;
		HEIGHT = h;
		SCALE = s;
		
	}
	
	//Game Loop
	private Thread thread;
	private boolean running;
	private final int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	@Override
	public void addNotify() {
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		init();
		long startTime, elapsed, wait;
		while(running) {	
			//Update : make the changes to the screen
			update();
			//Render : draw everything again
			requestPaint();
			startTime = System.nanoTime();
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		pacman.update();
	}
	
	//Not sure why I need this before render
	private void requestPaint() {
		Graphics g = getGraphics();
		render(g2d);
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
	}

	//Where the screen is actually made and drawn
	private void render(Graphics2D g2d) {
		g2d.clearRect(0, 0, WIDTH, HEIGHT); 	//Delete old stuff only around
		board.render(g2d);
		pacman.render(g2d);	
	}
	
	//Initializes the game and the Graphics2D
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
		loadContent();
		board.render(g2d); //load the board
		setFocusable(true);
		requestFocus();
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		
	}
	
	//Where to add the underlying content to be added
	private void loadContent() {
		board = new Board();
		pacman = new PacMan(610 + (25 * 14), 113 + (25 * 23));
	}
	
	
}
