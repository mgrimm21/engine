package com.mgrimm21.engine.systems;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.mgrimm21.engine.core.Application;
import com.mgrimm21.engine.input.Keyboard;

public class Level {

	private int width;
	private int height;
	private int[][] tiles;
	private float offX, offY;
	private float offsetSpeed = 1;
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width][height];
		offX = (width/2)*Tile.SIZE;
		offY = (height/2)*Tile.SIZE;
	}
	
	public void tick() {
		if (Keyboard.down(KeyEvent.VK_D)) offX+=offsetSpeed;
		if (Keyboard.down(KeyEvent.VK_A)) offX-=offsetSpeed;
		if (Keyboard.down(KeyEvent.VK_S)) offY+=offsetSpeed;
		if (Keyboard.down(KeyEvent.VK_W)) offY-=offsetSpeed;
	}
	
	public void render(Graphics2D g) {
		int extra = -30;
		for (int y = extra; y < height + Math.abs(extra); y++) {
			for (int x = extra; x < width + Math.abs(extra); x++) {
				int xx = (int) (x*Tile.SIZE-offX);
				int yy = (int) (y*Tile.SIZE-offY);
				if (xx <= Application.instance.width && xx + Tile.SIZE >= 0
						&& yy <= Application.instance.height && yy + Tile.SIZE >= 0) {
					getTile(x, y).render(g, xx, yy);
					//g.drawString(x+"", xx, yy);
					//g.drawString(y + "", xx, yy-20);
				}
			}
		}
	}
	
	private Tile getTile(int x, int y) {
		if (x < 0 || y < 0) return Tile.get(1);
		return Tile.get(tiles[x][y]);
	}
	
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getTiles() {
		return tiles;
	}
	
	public void setTile(int tile, int x, int y) {
		tiles[x][y] = tile;
	}
	
	public static Level createRandomLevel(int width, int height) {
		int max = Tile.getNextID();
		Random r = new Random();
		Level m = new Level(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				m.tiles[x][y] = r.nextInt(max);
			}
		}
		return m;
	}
	
	public String toString() {
		String s = "";
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				s+=tiles[x][y] + ", ";
			}
		}
		return s;
	}
	
	
	
}
