package com.mgrimm21.test;

import com.mgrimm21.serialize.Reader;
import com.mgrimm21.serialize.Writer;

public class Upgrade {

	private int id;
	private int[] required;
	private String name;
	private int cost;
	private int moneyBonus, woodBonus, foodBonus, ironBonus, coalBonus, huntBonus,
	populationBonus;
	public Upgrade(int id, int[] required, String name, int cost, int moneyBonus, int woodBonus, int foodBonus,
			int ironBonus, int coalBonus, int huntBonus, int populationBonus) {
		this.id = id;
		this.required = required;
		this.name = name;
		this.cost = cost;
		this.moneyBonus = moneyBonus;
		this.woodBonus = woodBonus;
		this.foodBonus = foodBonus;
		this.ironBonus = ironBonus;
		this.coalBonus = coalBonus;
		this.huntBonus = huntBonus;
		this.populationBonus = populationBonus;
	}
	
	public void save(byte[] data, int ptr, String path) {
		for (int i = 0; i < 8000000; i++) {
			ptr = Writer.write(data, ptr, id);
			ptr = Writer.write(data, ptr, required);
			ptr = Writer.writeStringByte(data, ptr, name);
			ptr = Writer.write(data, ptr, cost);
			ptr = Writer.write(data, ptr, moneyBonus);
			ptr = Writer.write(data, ptr, woodBonus);
			ptr = Writer.write(data, ptr, foodBonus);
			ptr = Writer.write(data, ptr, ironBonus);
			ptr = Writer.write(data, ptr, coalBonus);
			ptr = Writer.write(data, ptr, huntBonus);
			ptr = Writer.write(data, ptr, populationBonus);
		}
	}
	
	public static Upgrade load(byte[] bytes, int[] ptr) {
		int id = Reader.readInt(bytes, ptr);
		int[] required = Reader.readIntArray(bytes, ptr);
		String name = Reader.readStringByte(bytes, ptr);
		int cost = Reader.readInt(bytes, ptr);
		int moneyBonus = Reader.readInt(bytes, ptr);
		int woodBonus = Reader.readInt(bytes, ptr);
		int foodBonus = Reader.readInt(bytes, ptr);
		int ironBonus = Reader.readInt(bytes, ptr);
		int coalBonus = Reader.readInt(bytes, ptr);
		int huntBonus = Reader.readInt(bytes, ptr);
		int populationBonus = Reader.readInt(bytes, ptr);
		return new Upgrade(id, required, name, cost, moneyBonus, woodBonus, foodBonus,
				ironBonus, coalBonus, huntBonus, populationBonus);
	}

	public int getId() {
		return id;
	}

	public int[] getRequired() {
		return required;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getMoneyBonus() {
		return moneyBonus;
	}

	public int getWoodBonus() {
		return woodBonus;
	}

	public int getFoodBonus() {
		return foodBonus;
	}

	public int getIronBonus() {
		return ironBonus;
	}

	public int getCoalBonus() {
		return coalBonus;
	}

	public int getHuntBonus() {
		return huntBonus;
	}

	public int getPopulationBonus() {
		return populationBonus;
	}
	
	
}
