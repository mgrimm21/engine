package com.mgrimm21.engine.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mgrimm21.engine.util.Vector2f;

public class Sprite {
	
	private BufferedImage image = null;
	
	private Sprite(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Could not load file: " + path);
		}
	}
	
	public static Sprite createSprite(String path) {
		return new Sprite(path);
	}

	public void render(Graphics2D g, float x, float y) {
		if (image != null)g.drawImage(image, (int)x, (int)y, null);
	}
	
	public Vector2f getSize() {
		return new Vector2f(image.getWidth(), image.getHeight());
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	
}
