package com.hadou.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hadou.main.Game;

public class Entity {
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite((6*16), 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite((7*16), 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite((6*16), 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite((7*16), 16, 16, 16);
	
	protected int x, y, width, height;
	private BufferedImage sprite;
	
	public Entity( int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x; 
		this.y = y; 
		this.width = width; 
		this.height = height;
		this.sprite = sprite;
	}
	
	public void setX(int x) {
		this.x =x;
	}
	
	public void setY(int y) {
		this.y =y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public void render( Graphics g ) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}
}
