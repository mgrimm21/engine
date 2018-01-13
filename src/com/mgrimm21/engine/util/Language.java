package com.mgrimm21.engine.util;

public enum Language {
	EnglishUS("en_US"),
	EnglishUK("en_UK"),
	Chinese("ch_CH"),
	French("fr_france");
	
	private String file;
	Language(String file) {
		this.file = file;
	}
	
	public String fileName() {
		return "res/lang/" + file + ".lang";
	}
	
}
