package com.mgrimm21.engine.util;

import java.io.BufferedReader;
import java.io.FileReader;

import com.mgrimm21.engine.core.Application;

public class Utils {

	
	
	public static Vector2f center(int width, int height) {
		int sw = Application.instance.width;
		int sh = Application.instance.height;
		
		return new Vector2f((sw - width)/2, (sh - height)/2);
	}
	
	public static String getLocalizedName(String type, String unlocalizedName) {
		String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(Application.instance.getLanguage().fileName());

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(type + "." + unlocalizedName + ".name=")) {
                	String name = line.split("=")[1];
                	bufferedReader.close();
                	return name;
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(Exception ex) {
            System.out.println(Application.instance.getLanguage().fileName());                
        }
        return "null";
	}
	
	public static int clamp(int val, int min, int max) {
		if (val < min) return min;
		if (val > max) return max;
		return val;
	}
	
	public static float clamp(float val, float min, float max) {
		if (val < min) return min;
		if (val > max) return max;
		return val;
	}
	
}
