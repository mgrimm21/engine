package com.mgrimm21.engine.ui;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tooltip extends UIElement{

	protected boolean show = false;
	private String text = "";
	public Tooltip(float x, float y, String text) {
		super(x, y, 0,0);
		this.text = text;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g) {
		if (show) {
			g.setColor(Color.white);
			g.drawString(text, position.x, position.y);
		}
	}
	
	public void setShow(boolean show) {
		this.show = show;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public Tooltip copy() {
		return new Tooltip(position.x, position.y, text);
	}

}
