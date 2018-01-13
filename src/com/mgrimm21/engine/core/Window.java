package com.mgrimm21.engine.core;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.mgrimm21.engine.util.CloseListener;

public class Window {
	
	protected int width, height;
	private String windowName;
	public JFrame frame = new JFrame();
	private static List<CloseListener> listeners = new ArrayList<CloseListener>();
	
	public Window(int width, int height, String name, Application engine) {
		this.width = width;
		this.height = height;
		this.windowName = name;
		frame.setTitle(windowName);
		Dimension d = new Dimension(width, height);
		frame.setMinimumSize(d);
		frame.setMaximumSize(d);
		frame.setPreferredSize(d);
		frame.setResizable(false);
		frame.add(engine.canvas);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public synchronized void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        for (CloseListener l: listeners) {
		        	l.onWindowClose();
		        }
		    }
		});
		frame.setVisible(true);
		engine.start();
	}
	
	public static synchronized void addCloseListener(CloseListener l) {
		listeners.add(l);
	}
	
	public static synchronized void removeCloseListener(CloseListener l) {
		listeners.remove(l);
	}
}
