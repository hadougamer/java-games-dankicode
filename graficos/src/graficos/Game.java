package graficos;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public static JFrame frame;
	
	private final int WIDTH  = 160;
	private final int HEIGHT = 120;
	private final int SCALE  = 4;
	
	public Game() {
		this.setPreferredSize(new Dimension((WIDTH*SCALE), (HEIGHT*SCALE)));
		
		frame = new JFrame("Game #1");
		frame.add(this); 									  // Add this canvas to frame
		frame.setResizable(false); 							  // Disallow resize
		frame.pack();										  // Pack the canvas
		frame.setLocationRelativeTo(null); 					  // To center
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
		frame.setVisible(true);
	}
	
	public static void main ( String[] args ) {
		Game game = new Game();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
