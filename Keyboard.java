import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private static boolean[] keys = new boolean[256];
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	public static boolean isKeyDown(int k) {
		return keys[k];
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
