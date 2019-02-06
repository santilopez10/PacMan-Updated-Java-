import java.awt.Color;

import javax.swing.JFrame;

public class PacManStarter {
	public static void main(String[] args) {
		JFrame pacFrame = new JFrame();
	    
		
		
	    pacFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    pacFrame.setTitle("Pacman!");
	    
	    PacManPanel pacman = new PacManPanel(1920, 1001, 1);

	    pacFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //End program when JFrame is closed
	      
	    pacFrame.add(pacman);
	      
	    pacFrame.setVisible(true);
	      
	}
}
