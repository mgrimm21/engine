package com.mgrimm21.engine.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import com.mgrimm21.engine.audio.AudioClip;
import com.mgrimm21.engine.input.Keyboard;
import com.mgrimm21.engine.input.Mouse;
import com.mgrimm21.engine.scene.Scene;
import com.mgrimm21.engine.systems.SceneManager;
import com.mgrimm21.engine.systems.Tile;
import com.mgrimm21.engine.util.Language;
import com.mgrimm21.engine.util.RenderListener;
import com.mgrimm21.engine.util.TickListener;
/**
 * 
 * The <code>Application</code> class is the core 
 * class of the API
 * that needs to be instantiated to start the game
 *
 */
public class Application implements Runnable{
	
	public Window window;
	public Thread thread;
	public Canvas canvas = new Canvas();
	public boolean running = false;
	public final int width, height;
	private List<TickListener> tickListeners = new ArrayList<TickListener>();
	private List<RenderListener> renderListeners = new ArrayList<RenderListener>();
	public SceneManager sceneManager = new SceneManager();
	private Keyboard keyboard = new Keyboard();
	private Mouse mouse = new Mouse();
	public static Application instance = null;
	public boolean paused =false;
	public Language language = Language.EnglishUS;
	
	public Application(final int width, final int height, String title) {
		instance = this;
		AudioClip.init();
		Tile.init();
		window = new Window(width, height, title, this);
		this.width = width;
		this.height = height;
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
		canvas.setFocusable(true);
		canvas.requestFocus();
	}
	
	private int cleanupfreq = 60 * 10;
	private int sincelast = 0;
	private void tick() {
		sceneManager.tick();
		for (TickListener t: tickListeners) t.tick();
		if (sincelast++ == cleanupfreq) {
			sincelast = 0;
			AudioClip.cleanUp();
		}
	}
	
	private void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0, width, height);
		sceneManager.render(g);
		for (RenderListener r: renderListeners) r.render(g);
		g.dispose();
		bs.show();
	}
	
	public void addListener(TickListener tl) {
		tickListeners.add(tl);
	}
	
	public void addListener(RenderListener rl) {
		renderListeners.add(rl);
	}
	
	public void removeListener(TickListener tl) {
		tickListeners.remove(tl);
	}
	
	public void removeListener(RenderListener rl) {
		renderListeners.remove(rl);
	}
	
	public void setCurrentScene(String name) {
		sceneManager.setCurrentScene(name);
	}
	
	public void addScene(String name, Scene scene) {
		sceneManager.addScene(name, scene);
	}
	
	public void removeScene(String name) {
		sceneManager.removeScene(name);
	}
	
	public void setLanguage(Language lang) {
		this.language = lang;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static Point getMousePosition() {
		return instance.canvas.getMousePosition();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 360.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
}
