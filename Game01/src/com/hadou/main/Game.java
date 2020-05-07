package com.hadou.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.hadou.entities.Entity;
import com.hadou.entities.Player;
import com.hadou.graphics.Spritesheet;
import com.hadou.world.World;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	private Thread thread;
	
	private boolean isRunning = true;
	
	private double fps 	     = 60.0;
	private final int WIDTH  = 240;
	private final int HEIGHT = 160;
	private final int SCALE  = 4;
	
	private BufferedImage image;
	
	public ArrayList<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public Player player;
	
	public Game() {
		addKeyListener(this);
		setPreferredSize(new Dimension((WIDTH*SCALE), (HEIGHT*SCALE)));
		initFrame();
		
		// Initiate Objects
		world = new World("/map.png");
		image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_BGR);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0, 0, 16,16, spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);	
	}
	
	public void initFrame() {
		frame = new JFrame("Game #1");
		frame.add(this); 									  // Add this canvas to frame
		frame.setResizable(false); 							  // Disallow resize
		frame.pack();										  // Pack the canvas
		frame.setLocationRelativeTo(null); 					  // To center
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		this.isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main ( String[] args ) {
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		for( int i=0; i<entities.size(); i++ ) {
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	public void render() {
		//System.out.println("Game render ...");
		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		// Draw the background retangle
		g.setColor(new Color(0, 255, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/* Game Render */
		//Graphics2D g2 = (Graphics2D) g;
		for( int i=0; i<entities.size(); i++ ) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		/****/
		
		g.dispose(); // Garbage collector
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
		bs.show();
	}
	
	@Override
	public void run() {
		//FPS
		long lastTime = System.nanoTime();
		double amountOfTicks = this.fps;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while( this.isRunning ) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if ( delta >= 1 ) {
				tick();
				render();
				
				// Frame test
				frames++;
				delta--;
			}
			
			if ((System.currentTimeMillis()-timer) >= 1000 ) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ( 
				e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D 
		) {
			player.right = true;
		} else if ( 
			e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A 
		) {
			player.left = true;
		}
		
		if ( 
			e.getKeyCode() == KeyEvent.VK_UP  || e.getKeyCode() == KeyEvent.VK_W 
		) {
			player.up = true;
		} else if ( 
			e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S 
		) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ( 
				e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D 
		) {
			player.right = false;
		} else if ( 
			e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A 
		) {
			player.left = false;
		}
		
		if ( 
			e.getKeyCode() == KeyEvent.VK_UP  || e.getKeyCode() == KeyEvent.VK_W 
		) {
			player.up = false;
		} else if ( 
			e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S 
		) {
			player.down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
