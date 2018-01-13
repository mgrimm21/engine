package com.mgrimm21.engine.ui;

import java.awt.Graphics2D;
import java.awt.Point;

import com.mgrimm21.engine.core.Application;
import com.mgrimm21.engine.gameobject.GameObject;

public abstract class UIElement extends GameObject{
	
	protected int width, height;
	protected Tooltip tooltip = null;
	public UIElement(float x, float y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public void renderExtra(Graphics2D g) {
		if (mouseHovering() && tooltip != null) {
			tooltip.render(g);
		}
	}
	
	public boolean mouseHovering() {
		try {
			Point m = Application.getMousePosition();
			int x = (int) position.x;
			int y = (int) position.y;
			if (m.x >= x && m.x <= x + width && m.y >= y && m.y <= y + height) return true;
		}catch(Exception e) {
			return false;
		}
		return false;
		
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		tooltip.setX(tooltip.getX() + position.x);
		tooltip.setY(tooltip.getY() + position.y);
		this.tooltip = tooltip;
	}
	
}
