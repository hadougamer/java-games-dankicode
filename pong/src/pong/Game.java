package pong;

import java.awt.Canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	// Version
	private static final long serialVersionUID = 1L;
	
	// Dimensions
	public static int WIDTH  = 240;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
	
	// Player
	public Player player;
	public Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension((WIDTH * SCALE), (HEIGHT  * SCALE)));
		this.addKeyListener(this);
		player = new Player(((WIDTH/2) * SCALE), ((HEIGHT -10) * SCALE));
		enemy = new Enemy(100, 0);
		ball = new Ball(110, 20);
	}
	
	public static void main( String[] args ) {
		Game game = new Game();
		
		// Creates the Window object JFRame
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start(); 
	}

	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null ) {
			this.createBufferStrategy(3);;
			return;
		}
		Graphics g = layer.getGraphics();		
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		bs.show();
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(100/30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			player.right = true;
		} else if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
			player.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			player.right = false;
		} else if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
			player.left = false;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
