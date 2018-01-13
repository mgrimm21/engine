package com.mgrimm21.engine.scene;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mgrimm21.engine.core.Application;
import com.mgrimm21.engine.gameobject.GameObject;
import com.mgrimm21.engine.systems.Level;
import com.mgrimm21.engine.ui.Menu;
import com.mgrimm21.engine.ui.UIElement;

public class Scene {
	
	private List<GameObject> objects = new ArrayList<GameObject>();
	private List<UIElement> uiElements = new ArrayList<UIElement>();
	private Map<String, Menu> menus = new HashMap<String, Menu>();
	private Level level = null;
	
	public synchronized void tick() {
		if (level!=null&&!Application.instance.paused)level.tick();
		for (GameObject o: objects) {
			if (Application.instance.paused && (o instanceof UIElement)){
				o.tick();
			}else if (!Application.instance.paused) {
				
				o.tick();
			}
		}
		for (UIElement e: uiElements) e.tick();
		for (Menu m: menus.values()) m.tick();
	}

	public synchronized void render(Graphics2D g) {
		if (level!=null)level.render(g);
		for (GameObject o: objects) {
			o.render(g);
		}
		for (UIElement e: uiElements) e.render(g);
		for (Menu m: menus.values()) m.render(g);
	}
	
	public synchronized void add(GameObject o) {
		objects.add(o);
	}
	
	public synchronized void remove(GameObject o) {
		objects.remove(o);
	}
	
	public synchronized void addElement(UIElement uiElement) {
		uiElements.add(uiElement);
	}
	
	public synchronized void removeElement(UIElement e) {
		uiElements.remove(e);
	}
	
	public synchronized void addMenu(String name, Menu menu) {
		menus.put(name, menu);
	}
	
	public synchronized void removeMenu(String name) {
		menus.remove(name);
	}
	
	public void setMap(Level level) {
		this.level = level;
	}

}
