package com.mgrimm21.engine.gameobject.entity;

import com.mgrimm21.engine.util.Vector2f;

public abstract class LivingEntity extends Entity{

	public LivingEntity(float x, float y) {
		super(x, y);
	}
	
	public LivingEntity(Vector2f position) {
		super(position);
	}

}
