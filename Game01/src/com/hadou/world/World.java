package com.hadou.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.hadou.entities.Bullet;
import com.hadou.entities.Enemy;
import com.hadou.entities.Entity;
import com.hadou.entities.Lifepack;
import com.hadou.entities.Weapon;
import com.hadou.main.Game;

public class World {
	
	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	
	public World( String path ) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			// Image Pixels ( 20w x 20h )
			int[] pixels = new int[WIDTH * HEIGHT];
			
			// Init the tiles
			tiles = new Tile[WIDTH * HEIGHT];
			
			// Save the pixels into pixels array
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, map.getWidth());
			
			// Maps the coord for each pixel
			for (int xx=0; xx<WIDTH; xx++) {
				for (int yy=0; yy<HEIGHT; yy++) {
					int currentPixel = pixels[xx + (yy * WIDTH)];
					// Floor
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
					if ( currentPixel == 0xFF000000 ) {
						// Black = Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
					} else if ( currentPixel == 0xFFFFFFFF ) {
						// White = wall
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL);
					} else if ( currentPixel == 0xFF0063FF) {
						// Blue = player
						System.out.println("Player");
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					} else if ( currentPixel == 0xFFFF0000 ) {
						// Red = Enemy
						Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY_EN));
					} else if ( currentPixel == 0xFFcfca47 ) {
						// Orange = Weapon
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON_EN));
					} else if ( currentPixel == 0xFFffb200 ) {
						// Salmon = Lifepack
						Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN));
					} else if ( currentPixel == 0xFFbc93da ) {
						// Yellow = Bullet
						Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET_EN));
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g ) {
		for (int xx=0; xx<WIDTH; xx++) {
			for (int yy=0; yy<HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
