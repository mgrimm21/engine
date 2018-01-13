package com.mgrimm21.engine.audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.mgrimm21.engine.core.Window;
import com.mgrimm21.engine.util.CloseListener;

public class AudioClip  implements CloseListener{
	
	private static List<AudioInputStream> streams = new ArrayList<AudioInputStream>();
	private static List<Clip> streams2 = new ArrayList<Clip>();
	static {
		Window.addCloseListener(new AudioClip());
	}
	
	private Clip clip;
	private File soundFile;
	private AudioInputStream audioIn;
	
	public AudioClip(String path) {
		soundFile = new File(path);
		try {
			clip = AudioSystem.getClip();
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			streams2.add(clip);
			audioIn.close();
		} catch (Exception e) {
			System.err.println("Could not load audio clip from " + path);
		}
	}
	
	private AudioClip() {}
	
	public void play() {
		try {
			audioIn.close();
			clip.close();
			clip = AudioSystem.getClip();
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onWindowClose() {
		int num =0;
		for (int i = 0; i < streams.size(); i++) {
			try {
				num++;
				streams.get(i).close();
				streams2.get(i).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(num + " audio streams closed.");
	}
	
	public static void playSound(String url) {
		try {
		Clip clip = AudioSystem.getClip();
		streams2.add(clip);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res/audio/" + url));
        streams.add(inputStream);
        clip.open(inputStream);
        clip.start(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void cleanUp() {
		int num =0;
		ArrayList<Clip> removeClips = new ArrayList<Clip>();
		ArrayList<AudioInputStream> removeStreams = new ArrayList<AudioInputStream>();
		for (int i = 0; i < streams.size(); i++) {
			try {
				if (!streams2.get(i).isActive()) {
					num++;
					streams.get(i).close();
					removeStreams.add(streams.get(i));
					streams2.get(i).close();
					removeClips.add(streams2.get(i));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		streams2.removeAll(removeClips);
		streams.removeAll(removeStreams);
		System.out.println(num + " audio streams closed.");
	}
	
	public static void init() {}
}
