package com.mgrimm21.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener{

	private static boolean[] downButtons = new boolean[100];
	private static boolean[] newButtons = new boolean[100];
	
	public static boolean down(int button) {
		return downButtons[button];
	}
	
	public static boolean clicked(int button) {
		if (newButtons[button]) {
			newButtons[button] = false;
			return true;
		}
		return false;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		downButtons[arg0.getButton()]=true;
		newButtons[arg0.getButton()]=true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		downButtons[arg0.getButton()]=false;
		newButtons[arg0.getButton()]=false;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
