package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	public int x,y, width, height;
	
	public Enemy( int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 10;
	}
	
	public void tick() {
		x+= (Game.ball.x - x);
	}
	
	public void render( Graphics g) {
		g.setColor( Color.red );
		g.fillRect(x, y, width, height);
	}
}
