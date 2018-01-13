package com.mgrimm21.engine.ui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.mgrimm21.engine.core.Application;
import com.mgrimm21.engine.scene.Scene;

public class Menu implements KeyListener{

	private boolean show = false;
	private List<UIElement> elements = new ArrayList<UIElement>();
	private boolean pausesGame = false;
	public Scene scene = null;
	private boolean down = false;
	public Menu(Scene scene) {
		this.scene = scene;
		Application.instance.canvas.addKeyListener(this);
	}

	public void tick() {
		if (!show) return;
		for (UIElement e: elements) e.tick();
	}
	
	public void render(Graphics2D g) {
		if (!show) return;
		for (UIElement e: elements) e.render(g);
	}
	
	public synchronized void add(UIElement e) {
		elements.add(e);
	}
	
	public synchronized void remove(UIElement e) {
		elements.remove(e);
	}
	
	public void setShow(boolean show) {
		if (show && pausesGame) {
			Application.instance.running = false;
		}else if (!show && pausesGame && this.show) {
			Application.instance.running = true;
			Application.instance.run();
		}
		this.show = show;
	}
	
	public boolean isShown() {
		return show;
	}
	
	public void show() {
		show = true;
		if (pausesGame) Application.instance.paused = true;
	}
	
	public void hide() {
		show = false;
		if (pausesGame) Application.instance.paused = false;
	}
	
	public void toggle() {
		if (show) hide(); else show();
	}
	
	public Menu setPausesGame(boolean pauses) {
		pausesGame = pauses;
		return this;
	}
	
	public boolean getPausesGame() {
		return pausesGame;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (Application.instance.sceneManager.getCurrentScene() != scene) return;
		if (arg0.getKeyCode() != KeyEvent.VK_ESCAPE) return;
		if (!down) {
			down = true;
			toggle();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		down = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
