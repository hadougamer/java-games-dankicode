package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	public double x,y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 0.4; 
	
	public Ball( int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 10;
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
	}
	
	public void tick() {
		x+=dx*speed;
		y+=dy*speed;
		
		//Rectangle rect1 = new Rectangle((int) x, (int) y, width, height);
	}
	
	public void render( Graphics g) {
		g.setColor( Color.yellow );
		g.fillRect((int) x, (int) y, width, height);
	}
}
