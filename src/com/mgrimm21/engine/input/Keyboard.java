package com.mgrimm21.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private static boolean[] downKeys = new boolean[10000];
	private static boolean[] newKeys = new boolean[10000];
	
	public static boolean down(int key) {
		return downKeys[key];
	}
	
	public static boolean pressed(int key) {
		if (newKeys[key]) {
			newKeys[key] = false;
			return true;
		}
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		downKeys[arg0.getKeyCode()] = true;
		newKeys[arg0.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		downKeys[arg0.getKeyCode()] = false;
		newKeys[arg0.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
