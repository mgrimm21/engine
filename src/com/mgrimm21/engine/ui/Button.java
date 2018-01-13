package com.mgrimm21.engine.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.mgrimm21.engine.audio.AudioClip;
import com.mgrimm21.engine.gfx.Sprite;
import com.mgrimm21.engine.input.Mouse;
import com.mgrimm21.engine.util.Vector2f;

public class Button extends UIElement{

	private boolean clicked = false;
	private boolean hovering = false;
	private String text = "";
	private Sprite hoverSprite = null, normalSprite = null, clickedSprite = null; 
	private AudioClip hoverSound = new AudioClip("res/audio/click3.wav");
	private AudioClip clickSound = null;
	
	public Button(float x, float y, int width, int height, String text) {
		super(x, y, width, height);
		this.text = text;
	}
	
	public Button(Vector2f position, int width, int height, String text) {
		super(position.x, position.y, width, height);
		this.text = text;
	}
	
	public Button(Vector2f position, Sprite normal, Sprite hover, Sprite click) {
		super(position.x, position.y, (int)normal.getSize().x, (int)normal.getSize().y);
		hoverSprite = hover;
		normalSprite = normal;
		clickedSprite = click;
	}
	
	public Button(float x, float y, Sprite normal, Sprite hover, Sprite click) {
		super(x, y, (int)normal.getSize().x, (int)normal.getSize().y);
		hoverSprite = hover;
		normalSprite = normal;
		clickedSprite = click;
	}

	@Override
	public void tick() {
		if (mouseHovering() && !hovering) {
			if (hoverSound !=null) {
				hoverSound.play();
			}
			hovering = true;
		}else if(mouseHovering() && hovering){
			if (!clicked) {
				if (Mouse.clicked(MouseEvent.BUTTON1)) {
					clicked = true;
				}
			}else {
				if (!Mouse.down(MouseEvent.BUTTON1)) {
					if (clickSound!=null) {
						clickSound.play();
					}
					onClick();
					clicked = false;
				}
			}
		}else {
			hovering = false;
			clicked = false;
		}
		
		if (tooltip != null) {
			if (hovering && !tooltip.show) tooltip.show = true;
			if (!hovering && tooltip.show) tooltip.show = false;
		}
	}
	
	public void onClick() {
		System.out.println("clicked");
	}
	
	@Override
	public void render(Graphics2D g) {
		if (normalSprite==null||clickedSprite==null||hoverSprite==null) {
			g.setColor(Color.red);
			if (clicked) {
				g.setColor(Color.green);
			}
			else if (hovering) {
				g.setColor(Color.ORANGE);
			}
			g.fillRect((int)position.x, (int)position.y, width, height);
			g.setColor(Color.white);
			//int twidth = g.getFontMetrics().stringWidth(text);
			//g.drawString(text, position.x + (width - twidth) / 2, position.y + (height / 2) + (height / 6));
			Vector2f pos = getCenteredText(text, position, new Vector2f(width, height), g);
			g.drawString(text, pos.x, pos.y);
		}else {
			if (clicked) {
				clickedSprite.render(g, position.x, position.y);
			}else if (hovering) {
				hoverSprite.render(g, position.x, position.y);
			}else {
				normalSprite.render(g, position.x, position.y);
			}
		}
		
		if (tooltip!= null)getTooltip().render(g);
	}
	
	public static Vector2f getCenteredText
	(String text, Vector2f pos, Vector2f size, Graphics2D g) {
		int twidth = g.getFontMetrics().stringWidth(text);
		float x = pos.x + (size.x - twidth) / 2;
		float y = pos.y + (size.y / 2) + (size.y/6);
		
		
		return new Vector2f(x, y);
	}

	public AudioClip getHoverSound() {
		return hoverSound;
	}

	public void setHoverSound(AudioClip hoverSound) {
		this.hoverSound = hoverSound;
	}

	public AudioClip getClickSound() {
		return clickSound;
	}

	public void setClickSound(AudioClip clickSound) {
		this.clickSound = clickSound;
	}

}
