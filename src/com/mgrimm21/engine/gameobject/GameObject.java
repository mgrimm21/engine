package com.mgrimm21.engine.gameobject;

import java.awt.Graphics2D;

import com.mgrimm21.engine.util.Vector2f;

public abstract class GameObject {

	protected Vector2f position;
	
	public GameObject(Vector2f position) {
		this.position = position;
	}
	
	public GameObject(float x, float y) {
		this(new Vector2f(x, y));
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public void setX(float x) {
		this.position.x = x;
	}
	
	public float getX() {
		return position.x;
	}
	
	public void setY(float y) {
		position.y = y;
	}
	
	public float getY() {
		return position.y;
	}
	
	public void increaseX(float x) {
		position.x += x;
	}
	
	public void increaseY(float y) {
		position.y += y;
	}
	
}
