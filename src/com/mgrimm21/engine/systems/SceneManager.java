package com.mgrimm21.engine.systems;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.mgrimm21.engine.scene.Scene;

public class SceneManager {
	
	private Map<String, Scene> scenes = new HashMap<String, Scene>();
	private Scene currentScene = null;
	
	public void addScene(String name, Scene scene) {
		if (currentScene == null) currentScene = scene;
		scenes.put(name, scene);
	}
	
	public void setCurrentScene(String name) {
		currentScene = scenes.get(name);
	}
	
	public void removeScene(String name) {
		scenes.remove(name);
	}
	
	public void tick() {
		if (currentScene != null) currentScene.tick();
	}
	
	public void render(Graphics2D g) {
		if (currentScene != null) currentScene.render(g);
	}
	
	public Scene getCurrentScene() {
		return currentScene;
	}

}
