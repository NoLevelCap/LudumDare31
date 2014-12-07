package com.nolevelcap.ld31.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.ld31.land.Map;
import com.nolevelcap.ld31.land.Module;
import com.nolevelcap.ld31.land.Modules;
import com.nolevelcap.ld31.land.Tile;
import com.nolevelcap.ld31.player.Entity;
import com.nolevelcap.ld31.player.Goblin;
import com.nolevelcap.ld31.player.MainPlayer;
import com.nolevelcap.ld31.player.Sword;
import com.nolevelcap.ld31.ui.BoostBar;
import com.nolevelcap.ld31.ui.HealthBar;
import com.nolevelcap.render.Batch;
import com.nolevelcap.render.Drawable;
import com.nolevelcap.render.Screen;

public class OneScreen extends Screen {
	
	private Batch LandBatch, UIBatch;
	
	private float centerW, centerH;

	public OneScreen(TextureManager TM) {
		super(TM);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start() {
		load(Gdx.files.internal("module-set.png"), "module-set");
		load("module-set", 0, 0, 32, 32, "TILE_TL");
		load("module-set", 32, 0, 32, 32, "TILE_TC");
		load("module-set", 64, 0, 32, 32, "TILE_TR");
		
		load("module-set", 0, 32, 32, 32, "TILE_ML");
		load("module-set", 32, 32, 32, 32, "TILE_MC");
		load("module-set", 64, 32, 32, 32, "TILE_MR");
		
		load("module-set", 0, 64, 32, 32, "TILE_BL");
		load("module-set", 32, 64, 32, 32, "TILE_BC");
		load("module-set", 64, 64, 32, 32, "TILE_BR");
		
		load("module-set", 0, 96, 32, 32, "TILE_TO");
		load("module-set", 32, 96, 32, 32, "TILE_RO");
		load("module-set", 0, 128, 32, 32, "TILE_LO");
		load("module-set", 32, 128, 32, 32, "TILE_BO");
		
		load("module-set", 64, 96, 32, 32, "TILE_BLANK");
		load("module-set", 0, 160, 32, 32, "TILE_UNDER");
		
		load(Gdx.files.internal("person.png"), "MainCharacterSheet");
		load("MainCharacterSheet", 0, 0, 64, 64, "TestBlob");
		
		load(Gdx.files.internal("Enemy_1.png"), "Goblin");
		load("Goblin", 0, 0, 64, 64, "Goblin_A_1");
		load("Goblin", 64, 0, 64, 64, "Goblin_A_2");
		load("Goblin", 128, 0, 64, 64, "Goblin_A_3");
		load("Goblin", 192, 0, 64, 64, "Goblin_A_4");
		load("Goblin", 256, 0, 64, 64, "Goblin_A_5");
		
		load("Goblin", 0, 64, 64, 64, "Goblin_A_6");
		load("Goblin", 64, 64, 64, 64, "Goblin_A_7");
		load("Goblin", 128, 64, 64, 64, "Goblin_A_8");
		load("Goblin", 192, 64, 64, 64, "Goblin_A_9");
		load("Goblin", 256, 64, 64, 64, "Goblin_A_10");
		
		load("Goblin", 0, 128, 64, 64, "Goblin_A_11");
		load("Goblin", 64, 128, 64, 64, "Goblin_A_12");
		load("Goblin", 128, 128, 64, 64, "Goblin_A_13");
		
		load(Gdx.files.internal("sword.png"), "Sword");
		
		load(Gdx.files.internal("boost-bar.png"), "BoostBar");
		load("BoostBar", 0, 0, 32, 12, "Boost-TopAcitve");
		load("BoostBar", 64, 0, 32, 12, "Boost-TopEmpty");
		
		load("BoostBar", 0, 12, 32, 12, "Boost-TopMidFull");
		load("BoostBar", 0, 24, 32, 12, "Boost-TopMidJustEmpty");
		load("BoostBar", 64, 12, 32, 12, "Boost-TopMidEmpty");
		
		load("BoostBar", 32, 0, 32, 12, "Boost-MidFull");
		load("BoostBar", 32, 24, 32, 12, "Boost-MidJustEmpty");
		load("BoostBar", 64, 24, 32, 12, "Boost-MidEmpty");
		
		load("BoostBar", 32, 12, 32, 12, "Boost-BottomFull");
		load("BoostBar", 64, 36, 32, 12, "Boost-BottomEmpty");
		
		load("BoostBar", 0, 48, 32, 12, "Health-TopAcitve");
		load("BoostBar", 64, 48, 32, 12, "Health-TopEmpty");
		
		load("BoostBar", 0, 60, 32, 12, "Health-TopMidFull");
		load("BoostBar", 0, 72, 32, 12, "Health-TopMidJustEmpty");
		load("BoostBar", 64, 60, 32, 12, "Health-TopMidEmpty");
		
		load("BoostBar", 32, 48, 32, 12, "Health-MidFull");
		load("BoostBar", 32, 72, 32, 12, "Health-MidJustEmpty");
		load("BoostBar", 64, 72, 32, 12, "Health-MidEmpty");
		
		load("BoostBar", 32, 60, 32, 12, "Health-BottomFull");
		load("BoostBar", 64, 84, 32, 12, "Health-BottomEmpty");
		
		centerW = 1280/2; centerH = 720/2 - 12;
	
		LandBatch = new Batch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this);		
		
		LandBatch.addObject("TopRightModule", new Module((centerW-Modules.BASIC.cW)+Modules.TEST_TOP_RIGHT.map.D*7, (centerH-Modules.BASIC.cH)+Modules.TEST_TOP_RIGHT.map.D*6, Modules.TEST_TOP_RIGHT));
		LandBatch.addObject("TopModule", new Module(centerW-Modules.TEST_TOP.cW, (centerH-Modules.BASIC.cH)+Modules.TEST_TOP.map.D*6, Modules.TEST_TOP));
		LandBatch.addObject("TopLeftModule", new Module((centerW-Modules.BASIC.cW)-Modules.TEST_TOP_LEFT.W-Modules.TEST_RIGHT.map.D, (centerH-Modules.BASIC.cH)+Modules.TEST_TOP_LEFT.map.D*6, Modules.TEST_TOP_LEFT));
		
		LandBatch.addObject("RightModule", new Module((centerW-Modules.BASIC.cW)+Modules.TEST_RIGHT.map.D*6, centerH-Modules.TEST_RIGHT.cH, Modules.TEST_RIGHT));
		LandBatch.addObject("CenterModule", new Module(centerW-Modules.BASIC.cW, centerH-Modules.BASIC.cH, Modules.BASIC));
		LandBatch.addObject("LeftModule", new Module((centerW-Modules.BASIC.cW)-Modules.TEST_LEFT.W, centerH-Modules.TEST_LEFT.cH, Modules.TEST_LEFT));

		LandBatch.addObject("BottomRightModule", new Module((centerW-Modules.BASIC.cW)+Modules.TEST_BOTTOM_RIGHT.map.D*7, (centerH-Modules.BASIC.cH)-Modules.TEST_BOTTOM_RIGHT.H+Modules.TEST_BOTTOM_RIGHT.map.D, Modules.TEST_BOTTOM_RIGHT));
		LandBatch.addObject("BottomModule", new Module(centerW-Modules.TEST_BOTTOM.cW, (centerH-Modules.BASIC.cH)-Modules.TEST_BOTTOM.H+Modules.TEST_BOTTOM_RIGHT.map.D, Modules.TEST_BOTTOM));
		LandBatch.addObject("BottomLeftModule", new Module((centerW-Modules.BASIC.cW)-Modules.TEST_BOTTOM_LEFT.W-Modules.TEST_RIGHT.map.D, (centerH-Modules.BASIC.cH)-Modules.TEST_BOTTOM_LEFT.H+Modules.TEST_BOTTOM_LEFT.map.D, Modules.TEST_BOTTOM_LEFT));
		
		Sword sword =  new Sword(0, 0, 32, 128);
		LandBatch.addObject("Sword", sword);
		LandBatch.addObject("Player", new MainPlayer(centerW+12-90, centerH+12-90, 24, 24, centerW-90, centerH-90, 48, 48, sword));
		
		Array<TextureRegion> Goblin = new Array<TextureRegion>();
		Goblin.addAll(
				TM.getTexture("Goblin_A_1"), TM.getTexture("Goblin_A_2"),
				TM.getTexture("Goblin_A_3"), TM.getTexture("Goblin_A_4"),
				TM.getTexture("Goblin_A_5"), TM.getTexture("Goblin_A_6"),
				TM.getTexture("Goblin_A_7"), TM.getTexture("Goblin_A_8"),
				TM.getTexture("Goblin_A_9"), TM.getTexture("Goblin_A_10"),
				TM.getTexture("Goblin_A_11"), TM.getTexture("Goblin_A_12"),
				TM.getTexture("Goblin_A_13")
				);
		
		
		
		LandBatch.addObject("Goblin", new Goblin(centerW+16, centerH+16, 32, 32, centerW, centerH, 64, 64, Goblin));
		addBatch(LandBatch);
		
		UIBatch = new Batch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this);
		
		UIBatch.addObject("BoostBar", new BoostBar(12, 12));
		UIBatch.addObject("HealthBar", new HealthBar(1280-72, 12));
		
		addBatch(UIBatch);
	}


	@Override
	public void logic() {
		Array<Drawable> CollectedModules = new Array<Drawable>();
		
		for(Drawable obj: DRAWABLES){
			if(obj instanceof Module){
				CollectedModules.add(obj);
			}
		}
		
		for(Drawable obj: DRAWABLES){
			if(obj instanceof Entity){
				((Entity) obj).overlapped = false;
				for(Drawable module: CollectedModules){
					Rectangle intersect = new Rectangle(0,0,0,0);
					if(Intersector.intersectRectangles(module.getBOUNDS(), obj.getBOUNDS(), intersect) && ((Module) module).opaque){	
						((Entity) obj).overlapped = true;
						if(intersect.area()>200f){
							((Entity) obj).vaildPosition = ((Module) module).isPlayerOnValidTile(obj.getBOUNDS());
						} else {
							((Entity) obj).vaildPosition = true;
						}
						//Gdx.app.log("INFO", intersect.area()+"");
						}
				}
			}
		}
		
		Sword s = (Sword) DRAWABLES.get("Sword");
		MainPlayer player = (MainPlayer) DRAWABLES.get("Player");
		
		for(Drawable obj: DRAWABLES){
			if(obj instanceof Entity && !(obj instanceof MainPlayer)){
				
				Rectangle intersection = new Rectangle();
				if(Intersector.intersectRectangles(player.getBOUNDS(), obj.getBOUNDS(), intersection)){
					if(intersection.width>intersection.height){ 
						if(intersection.x > player.getBOUNDS().x) {
							((Entity) obj).shiftPosition(intersection.width+5, 0);
							}                                            
						if(intersection.x + intersection.width < player.getBOUNDS().x + player.getBOUNDS().width) {
							((Entity) obj).shiftPosition(-intersection.width-5, 0);
							}                      
					} else {
						if(intersection.y > player.getBOUNDS().y) {
							((Entity) obj).shiftPosition(0, intersection.height+5);
						}
						if(intersection.y + intersection.height < player.getBOUNDS().y + player.getBOUNDS().height) {
							((Entity) obj).shiftPosition(0, -intersection.height-5);
							}
					}
					
					player.loseHealth();
				}
				
				if(Sword.intersectLineRectangle(s.ray1, s.ray2, obj.getBOUNDS()) ||
						Sword.intersectLineRectangle(s.ray1, s.ray3, obj.getBOUNDS()) ||
						Sword.intersectLineRectangle(s.ray1, s.ray4, obj.getBOUNDS())
						)
				{	
					((Entity)obj).movePlayer(new Vector2(1280/2, 720/2));
				};
				
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			((Module) DRAWABLES.get("TopModule")).changeModule(Modules.values()[MathUtils.random(Modules.values().length-1)]);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			((Module) DRAWABLES.get("TopModule")).toggleFade();
			((Module) DRAWABLES.get("RightModule")).toggleFade();
			((Module) DRAWABLES.get("LeftModule")).toggleFade();
			((Module) DRAWABLES.get("BottomModule")).toggleFade();
			((Module) DRAWABLES.get("BottomRightModule")).toggleFade();
			((Module) DRAWABLES.get("TopRightModule")).toggleFade();
			((Module) DRAWABLES.get("BottomLeftModule")).toggleFade();
			((Module) DRAWABLES.get("TopLeftModule")).toggleFade();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.E)){
			((Module) DRAWABLES.get("TopLeftModule")).randomizedModule();
			((Module) DRAWABLES.get("TopModule")).changeModule(Modules.getRandomModule(1));
			((Module) DRAWABLES.get("RightModule")).changeModule(Modules.getRandomModule(2));
			((Module) DRAWABLES.get("LeftModule")).changeModule(Modules.getRandomModule(3));
			((Module) DRAWABLES.get("BottomModule")).changeModule(Modules.getRandomModule(4));
			((Module) DRAWABLES.get("BottomRightModule")).changeModule(Modules.getRandomModule(5));
			((Module) DRAWABLES.get("TopRightModule")).changeModule(Modules.getRandomModule(7));
			((Module) DRAWABLES.get("BottomLeftModule")).changeModule(Modules.getRandomModule(6));
			((Module) DRAWABLES.get("TopLeftModule")).changeModule(Modules.getRandomModule(8));
		}
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			((MainPlayer) DRAWABLES.get("Player")).triggerAttack();
		}
		super.logic();
	}
	

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub

	}
	
	

}
