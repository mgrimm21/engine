package com.mgrimm21.engine.gameobject;

import com.mgrimm21.engine.util.Utils;

public class Item {

	private String unlocalizedName;
	
	public Item(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
	}
	
	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}
	
	public String getName() {
		return Utils.getLocalizedName("item", unlocalizedName);
	}
	
	
	
}
