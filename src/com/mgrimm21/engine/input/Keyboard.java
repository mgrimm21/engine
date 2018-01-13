package com.mgrimm21.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

public class Keyboard implements KeyListener{

	private static Set<Integer> pressedKeys = new TreeSet<Integer>();
	private static Set<Integer> heldKeys = new TreeSet<Integer>();
	public static boolean down(int key) {
		return heldKeys.contains(key);
	}
	
	public static boolean pressed(int key) {
		if (heldKeys.contains(key) && !pressedKeys.contains(key)) {
			pressedKeys.add(key);
			return true;
		}
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (heldKeys.contains(arg0.getKeyCode())) return;
		heldKeys.add(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		heldKeys.remove(arg0.getKeyCode());
		pressedKeys.remove(arg0.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
