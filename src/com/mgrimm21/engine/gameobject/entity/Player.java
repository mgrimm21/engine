package com.mgrimm21.engine.gameobject.entity;

import java.awt.event.KeyEvent;

import com.mgrimm21.engine.gfx.Sprite;
import com.mgrimm21.engine.input.Keyboard;
import com.mgrimm21.engine.util.Vector2f;

public class Player extends LivingEntity{
	
	private float speed = .33f;
	private float health;

	public Player(float x, float y) {
		super(x, y);
		init();
	}
	
	public Player(Vector2f position) {
		super(position);
		init();
	}
	
	private void init() {
		sprite = Sprite.createSprite("res/road.jpg");
	}
	
	@Override
	public void tick() {
		boolean up = Keyboard.down(KeyEvent.VK_W) || Keyboard.down(KeyEvent.VK_UP);
		boolean down = Keyboard.down(KeyEvent.VK_S) || Keyboard.down(KeyEvent.VK_DOWN);
		boolean left = Keyboard.down(KeyEvent.VK_A) || Keyboard.down(KeyEvent.VK_LEFT);
		boolean right = Keyboard.down(KeyEvent.VK_D) || Keyboard.down(KeyEvent.VK_RIGHT);
		velocity = new Vector2f();
		if (up && !down)velocity.y = -speed;
		if (down && !up) velocity.y = speed;
		if (left && !right) velocity.x = -speed;
		if (right && !left) velocity.x = speed;
		position.x += velocity.x;
		position.y += velocity.y;
	}

}
