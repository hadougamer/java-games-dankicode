package com.hadou.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	public World( String path ) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			// Image Pixels ( 20w x 20h )
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			
			// Save the pixels into pixels array
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for (int i=0; i<pixels.length; i++) {
				// Red pixel
				if ( pixels[i] == 0xFFFF0000 ) {
					System.out.println("Estou no vermelho");	
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
