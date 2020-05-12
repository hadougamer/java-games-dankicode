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
	
	private static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static int TILE_SIZE=16;
	
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
	
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1)/TILE_SIZE;
		int y2 = ynext/TILE_SIZE;

		int x3 = ynext/TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		int x4 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		return !(
			(tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
			(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
			(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
			(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile)
		);
	}
	
	public void render(Graphics g ) {
		// Initial camera position
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		// Final camera position
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for (int xx=xstart; xx<=xfinal; xx++) {
			for (int yy=ystart; yy<=yfinal; yy++) {
				if( xx < 0 || yy <0 || xx >= WIDTH || yy >= HEIGHT )
					// Prevent out of bounds
					continue;

				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
