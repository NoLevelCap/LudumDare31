package com.nolevelcap.ld31.land;

import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.data.SnapMap;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.data.Timer;
import com.nolevelcap.ld31.game.OneScreen;
import com.nolevelcap.render.Drawable;
import com.nolevelcap.render.Screen;

public class Module extends Drawable {
	
	private static int D = 32;
	private HashMap<Vector2, Tile> Map;
	private int tileW, tileH;
	
	private boolean fadeIn;
	public boolean opaque;
	private float opacity;
	
	private long waitDuration = 1000 + (long) (5000 * MathUtils.random());
	private Timer timer;
	
	public boolean changed;
	
	public Modules currentModule;

	public Module(float x, float y, Modules module) {
		super(x, y, module.map.mapW*D, module.map.mapH*D);
		this.currentModule = module;
		this.tileW = module.map.mapW;
		this.tileH = module.map.mapH;

		Map = new HashMap<Vector2, Tile>();
		for(int dx=0; dx<tileW; dx++){
			for(int dy=0; dy<tileH; dy++){
				TileProperties tp = module.map.getTile(dx, dy);
				Map.put(new Vector2(dx, dy), new Tile(tp.texData, tp.gid, getX()+dx*D, getY()+(tileH-(dy+1))*D, dx, dy));
			}
		}
		
		if(module != Modules.BASIC){
			this.opacity = 0.0f;
			this.opaque = false;
			this.fadeIn = false;
			timer = new Timer(waitDuration);
			//timer = new Timer(0);
			changed = true;
		} else {
			this.opacity = 1.0f;
			this.opaque = true;
			this.fadeIn = true;
			timer = new Timer(0);
		}
		
	}
	
	public void changeModule(Modules module){
		Map.clear();
		
		this.currentModule = module;
		this.tileW = module.map.mapW;
		this.tileH = module.map.mapH;
		this.setBounds(getX(), getY(), module.map.mapW*D, module.map.mapH*D);
		
		for(int dx=0; dx<tileW; dx++){
			for(int dy=0; dy<tileH; dy++){
				TileProperties tp = module.map.getTile(dx, dy);
				Map.put(new Vector2(dx, dy), new Tile(tp.texData, tp.gid, getX()+dx*D, getY()+(tileH-(dy+1))*D, dx, dy));
			}
		}
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.setColor(1, 1, 1, opacity);
		for(int x=0; x<tileW; x++){
			for(int y=0; y<tileH; y++){
				Map.get(new Vector2(x, y)).render(TM, batch);
			}
		}
		batch.setColor(1, 1, 1, 1f);

	}

	@Override
	public void logic() {
		for(int x=0; x<tileW; x++){
			for(int y=0; y<tileH; y++){
				Map.get(new Vector2(x, y)).logic();
			}
		}
		
		if(fadeIn && !opaque){
			opacity += 5*Gdx.graphics.getDeltaTime();
			if(opacity >= 1.0f){
				opacity = 1.0f;
				opaque = true;
				for(int x=0; x<tileW; x++){
					for(int y=0; y<tileH; y++){
						Map.get(new Vector2(x, y)).onCreate();
					}
				}
			}
		} else if(!fadeIn && opaque){
			opacity -= 5*Gdx.graphics.getDeltaTime();
			if(opacity <= 0.0f){
				opacity = 0.0f;
				opaque = false;
			}
		} 
		
		timer.start();
		
		if(timer.isComplete()){
			toggleFade();
			if(fadeIn){
				timer.reset(1000 + (long) (15000 * MathUtils.random()));
			} else {
				timer.reset(waitDuration);
			}
		} else if(timer.isNearComplete() && opaque){
			opacity -= 5*Gdx.graphics.getDeltaTime();
			if(opacity <= 0.5f){
				opacity = 0.5f;
			}
		}
		
		if(!changed && !fadeIn && !opaque){
			randomizedModule();
		}
	}
	
	public void toggleFade(){
		if(fadeIn == false && canSpawnModule(getPS(), currentModule)){
			fadeIn = true;
		} else {
			
			fadeIn = false;
			changed = false;
		}
	}

	@Override
	public void onClick(float x, float y) {
		//Gdx.app.log("Clicked", duration+"...");
		timer.reset();
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}
	
	public boolean isPlayerOnValidTile(Rectangle playerBounds){
		boolean answer = true;
			for(Tile tile: Map.values()){
				if(tile.getBOUNDS().overlaps(playerBounds)){
					if(tile.gid == 12 || tile.gid == 16 || tile.gid == 0){
						answer = false;
					} else {
						//Gdx.app.log("Tiles", tile.getName()+"/"+tile.gid);
					}
				}
			}
		return answer;
	}
	
	public Collection<Tile> Tiles(){
		return Map.values();
		
	}
	
	public void randomizedModule(){
		Modules module = Modules.getRandomModule(currentModule.PC);
		if(canSpawnModule(getPS(), module)){
			changeModule(module);
		}
	}
	

	public static boolean canSpawnModule(Screen sc, Modules module){
		int moduleID = module.PC;
		if(moduleID == 5){
			 if(((Module) sc.getDrawables().get("BottomModule")).opaque || ((Module) sc.getDrawables().get("BottomModule")).fadeIn
					 &&
			    ((Module) sc.getDrawables().get("RightModule")).opaque  || ((Module) sc.getDrawables().get("RightModule")).fadeIn
					 ){
				 return true;
			 }
		} else if(moduleID == 6){
			if(((Module) sc.getDrawables().get("BottomModule")).opaque  || ((Module) sc.getDrawables().get("BottomModule")).fadeIn
					 &&
			    ((Module) sc.getDrawables().get("LeftModule")).opaque  || ((Module) sc.getDrawables().get("LeftModule")).fadeIn
					 ){
				 return true;
			 }
		} else if(moduleID == 7){
			if(((Module) sc.getDrawables().get("TopModule")).opaque  || ((Module) sc.getDrawables().get("TopModule")).fadeIn
					 &&
			    ((Module) sc.getDrawables().get("RightModule")).opaque  || ((Module) sc.getDrawables().get("RightModule")).fadeIn
					 ){
				 return true;
			 }
		} else if(moduleID == 8){
			if(((Module) sc.getDrawables().get("TopModule")).opaque  || ((Module) sc.getDrawables().get("TopModule")).fadeIn
					 &&
			    ((Module) sc.getDrawables().get("LeftModule")).opaque   || ((Module) sc.getDrawables().get("LeftModule")).fadeIn
					 ){
				 return true;
			 }
		} else if(moduleID < 5){
			return true;
		}
		return false;
	}

	@Override
	public void onHover(float x, float y) {
		// TODO Auto-generated method stub
		
	}

}
