package com.hadou.entities;

import java.awt.image.BufferedImage;

import com.hadou.main.Game;
import com.hadou.world.World;

public class Enemy extends Entity {

	private double speed = 1;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		// 50% move probability
		if ( Game.rand.nextInt(100) < 30 ) {
			if( x < Game.player.getX() && World.isFree((int)(x+speed), this.getY()) ) {
				x += speed;
			} else if( x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())) {
				x -= speed;
			}
			
			else if( y < Game.player.getY() && World.isFree( this.getX(), (int)(y+speed) ) ) {
				y += speed;
			} else if( x > Game.player.getY() && World.isFree( this.getX(), (int)(y-speed) ) ) {
				y -= speed;
			}	
		}
	}
}
