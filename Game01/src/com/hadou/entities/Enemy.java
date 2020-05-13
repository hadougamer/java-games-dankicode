package com.hadou.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.hadou.main.Game;
import com.hadou.world.Camera;
import com.hadou.world.World;

public class Enemy extends Entity {

	private double speed = 1;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		if( x < Game.player.getX() && World.isFree((int)(x+speed), this.getY()) && !isColliding((int)(x+speed), this.getY()) ) {
			x += speed;
		} else if( x > Game.player.getX() && World.isFree((int)(x-speed), this.getY()) && !isColliding((int)(x-speed), this.getY())) {
			x -= speed;
		}
		
		else if( y < Game.player.getY() && World.isFree( this.getX(), (int)(y+speed) ) && !isColliding(this.getX(), (int)(y+speed)) ) {
			y += speed;
		} else if( x > Game.player.getY() && World.isFree( this.getX(), (int)(y-speed) ) && !isColliding(this.getX(), (int)(y-speed))) {
			y -= speed;
		}
	}
	
	public boolean isColliding(int xnext, int ynext ) {
		Rectangle enemyCurrent = new Rectangle(
			xnext, 
			xnext, 
			World.TILE_SIZE, 
			World.TILE_SIZE
		);
		
		for ( int i=0; i< Game.enemies.size(); i++ ) {
			Enemy e = Game.enemies.get(i);
			
			if ( e == this )
				continue;
			
			Rectangle targetEnemy = new Rectangle(
				e.getX(), 
				e.getY(), 
				World.TILE_SIZE, 
				World.TILE_SIZE
			);
			
			if ( enemyCurrent.intersects( targetEnemy ) ) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render( Graphics g ) {
		super.render(g);
		
		g.setColor(Color.blue);
		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, 16, 16);
	}
}
