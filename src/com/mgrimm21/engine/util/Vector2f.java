package com.mgrimm21.engine.util;

public class Vector2f {

	public float x, y;
	
	public Vector2f(Vector2f vec) {
		x = vec.x;
		y = vec.y;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f() {
		x = y = 0;
	}
	
	public boolean equals(Object o) {
        if(!(o instanceof Vector2f)) return false;
        Vector2f g = (Vector2f)o;
        return x == g.x && y == g.y;

    }

    public int hashCode() {
    	float remX = 5 * (int)((x % 1) * 1000000);
    	float remY = 5 * (int)((y % 1) * 1000000);
    	int hash = ((int)x << 32) | ((int)y << 24) | ((int)remX << 16) | ((int)remY << 8);
        return hash;
    }
	
}
