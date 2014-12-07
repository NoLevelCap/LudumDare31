package com.nolevelcap.ld31.land;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public enum Modules {
	BASIC("modules/module.tmx", 0, true, true, true, true), 
	
	TEST_TOP("modules/top_module.tmx", 1, true, false, true, true), 
	TEST_RIGHT("modules/right_module.tmx", 2, true, true, false, true),
	TEST_LEFT("modules/left_module.tmx", 3, false, true, true, true),
	TEST_BOTTOM("modules/bottom_module.tmx", 4, true, true, true, false),
	
	TEST_BOTTOM_RIGHT("modules/bottom_right_module.tmx", 5, true, true, false, false),
	TEST_BOTTOM_LEFT("modules/bottom_left_module.tmx", 6, false, true, true, false),
	TEST_TOP_RIGHT("modules/top_right_module.tmx", 7, true, false, false, true),
	TEST_TOP_LEFT("modules/top_left_module.tmx", 8, false, false, true, true),
	TEST_TOP_LEFT_2("modules/top_left_module_2.tmx", 8, false, false, true, false);
	
	public Map map;
	public int cW, cH, W, H, PC;
	public boolean left, top, right, bottom;
	private Modules(String mapID, int PositionCode, boolean left,  boolean top,  boolean right,  boolean bottom) {
		this.map = new Map(Gdx.files.internal(mapID));
		this.H = map.mapH*map.D;
		this.W = map.mapW*map.D;
		this.cH = this.H/2;
		this.cW = this.W/2;
		this.PC = PositionCode;
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public static Modules getRandomModule(int moduleCode){
		Modules random = values()[MathUtils.random(values().length-1)];
		while(random.PC != moduleCode){
			random = values()[MathUtils.random(values().length-1)];
		}
		return random;
	}
}
