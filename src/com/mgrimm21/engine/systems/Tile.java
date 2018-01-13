package com.mgrimm21.engine.systems;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.mgrimm21.engine.gfx.Sprite;

public class Tile {
	
	public static Tile grass;
	public static Tile water;
	public static Tile tree;
	public static Tile iron;
	public static Tile bush;
	
	public static final int SIZE = 32;
	
	private static int nextID = 0;
	private int id;
	private Sprite sprite;
	
	private static Map<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	
	private Tile(String name) {
		id = getNextID();
		sprite = Sprite.createSprite("res/tiles/" + name + ".png");
		tiles.put(id, this);
	}
	
	public int getID() {
		return id;
	}
	
	public void render(Graphics2D g, float x, float y) {
		sprite.render(g, x, y);
	}
	
	public static int getNextID() {
		return nextID++;
	}
	
	public static void init() {
		grass = new Tile("grass");
		water = new Tile("water");
		tree = new Tile("tree");
		iron = new Tile("iron");
		bush = new Tile("bush");
	}
	
	public static Tile get(int id) {
		return tiles.get(id);
	}

}
