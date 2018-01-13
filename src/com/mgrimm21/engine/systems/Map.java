package com.mgrimm21.engine.systems;

public class Map {

	private int width;
	private int height;
	private Tile[] tiles;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width*height];
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

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}
	
	
	
}
