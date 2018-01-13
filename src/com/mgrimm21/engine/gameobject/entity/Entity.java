package com.mgrimm21.engine.gameobject.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.mgrimm21.engine.gameobject.GameObject;
import com.mgrimm21.engine.gfx.Sprite;
import com.mgrimm21.engine.util.Vector2f;

public abstract class Entity extends GameObject{
	
	protected Sprite sprite = null;

	public Entity(float x, float y) {
		super(x, y);
	}
	
	public Entity(Vector2f position) {
		super(position);
	}

	protected Vector2f velocity = new Vector2f();
	
	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	public void setVelX(float x) {
		this.velocity.x = x;
	}
	
	public float getVelX() {
		return velocity.x;
	}
	
	public void setVelY(float y) {
		velocity.y = y;
	}
	
	public float getVelY() {
		return velocity.y;
	}

	public void increaseVelX(float x) {
		velocity.x += x;
	}
	
	public void increaseVelY(float y) {
		velocity.y += y;
	}
	
	@Override
	public void render(Graphics2D g) {
		if (sprite!=null) {
			sprite.render(g, position.x, position.y);
		}else {
			g.setColor(Color.white);
			g.fillRect((int)position.x, (int)position.y, 32, 32);
		}
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
