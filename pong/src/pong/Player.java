package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public int x, y;
	
	private int WIDTH  = 40;
	private int HEIGHT = 10;
	private int SCALE  = 3;
	
	public Player( int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if ( right ) {
			x++;
		} else if ( left ) {
			x--;
		}
		
		// Colision
		if ( (x-WIDTH * SCALE ) > ( Game.WIDTH * SCALE ) ) {
			x = ( Game.WIDTH * SCALE ) - ( WIDTH * SCALE );
		} else if( x < 0 ) {
			x = 0;
		}
		
	}
	
	public void render( Graphics g ) {
		// Draw the player
		g.setColor(Color.BLUE);
		g.fillRect(x, y, (WIDTH * SCALE), (HEIGHT * SCALE) );
	}
}
