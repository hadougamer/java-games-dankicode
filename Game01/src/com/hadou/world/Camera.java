package com.hadou.world;

public class Camera {
	
	public static int x, y;
	
	// Camera constraint
	public static int clamp( int cur, int min, int max ) {
		if ( cur < min ) {
			cur = min;
		}
		
		if ( cur > max ) {
			cur = max;
		}
		
		return cur;
	}
}
