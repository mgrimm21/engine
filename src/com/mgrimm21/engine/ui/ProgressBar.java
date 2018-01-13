package com.mgrimm21.engine.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.mgrimm21.engine.gfx.Sprite;

public class ProgressBar extends UIElement{

	private int[] trackedValue;
	private int maxValue;
	private Sprite sprite;
	private float renderAmt=0;
	
	public ProgressBar(float x, float y, int[] trackedValue, int maxValue, Sprite sprite) {
		super(x, y, (int)sprite.getSize().x, (int)sprite.getSize().y);
		this.trackedValue = trackedValue;
		this.maxValue = maxValue;
		this.sprite = sprite;
		
	}

	@Override
	public void tick() {
		updateRenderAmount();
	}

	@Override
	public void render(Graphics2D g) {
		if (renderAmt<=0) return;
		BufferedImage image = sprite.getImage();
		image = image.getSubimage(0, 0, (int)(width * renderAmt), height);
		g.drawImage(image, (int)position.x, (int)position.y, null);
	}
	
	private void updateRenderAmount() {
		float val = trackedValue[0];
		renderAmt = val / maxValue;
	}

	public int[] getTrackedValue() {
		return trackedValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

}
