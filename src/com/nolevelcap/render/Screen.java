package com.nolevelcap.render;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nolevelcap.data.SnapMap;
import com.nolevelcap.data.TextureManager;

public abstract class Screen {
	/*
	 * Add object requires a grouping and a object and a name, A group handles camera functions for those drawables
	 * but the drawables are inside the general screen class.
	 * 
	 */
	
	protected SnapMap<String, Drawable> DRAWABLES;
	private Array<Batch> BATCHES;
	protected TextureManager TM;

	public Screen(TextureManager TM) {
		DRAWABLES =  new SnapMap<String, Drawable>();
		BATCHES = new Array<Batch>();
		this.TM = TM;
		
		this.start();
	}

	public void render(){
		for(Batch bat: BATCHES){
			bat.render(getTextureManager());
		}
	}

	public void logic(){
		for(Drawable obj: DRAWABLES){
			obj.logic();
		}
		
		handleHover(Gdx.input.getX(), Gdx.input.getY());
		
		if(Gdx.input.isTouched()){
			handleMouseInput(Gdx.input.getX(), Gdx.input.getY());
		}
		
		for(Batch bat: BATCHES){
			bat.update();
		}
	}

	public void addObject(String name, Drawable obj){
		obj.setPS(this);
		obj.setZIndex(DRAWABLES.size());
		DRAWABLES.put(name, obj);
	}
	
	public void changeObject(String name, Drawable newObj){
		newObj.setPS(this);
		DRAWABLES.change(name, newObj);
	}
	
	public void removeObject(String name){
		DRAWABLES.remove(name);
	}
	
	public void addBatch(Batch value){
		value.setSC(this);
		BATCHES.add(value);
	}
	
	public void insertBatch(int index, Batch value){
		value.setSC(this);
		BATCHES.insert(index, value);
	}
	
	public void removeBatch(Batch value){
		BATCHES.removeValue(value, false);
	}
	
	/*public void addObject(Drawable obj, int Z){
		obj.setZIndex(Z);
		DRAWABLES.put(name, obj);
	}*/

	/*public void moveObject(Drawable obj, int Z){
		removeObject(obj);
		obj.setZIndex(Z);
		DRAWABLES.insert(Z, obj);
	}*/

	/*public void removeObject(String name){
		DRAWABLES.remove(name);
	}*/

	/*public void removeObject(int Z){
		DRAWABLES.removeIndex(Z);
	}*/

	public void load(TextureRegion[] textures, String... names){
		if(textures.length != names.length){
			Gdx.app.error("Loading", "Textures not loaded, needs less/more names");
			return;
		} else {
			for(int i=0; i<textures.length; i++){
				TM.addTexture(names[i], textures[i]);
			}
		}
	}
	
	public void load(Texture[] textures, String... names){
		if(textures.length != names.length){
			Gdx.app.error("Loading", "Textures not loaded, needs less/more names");
			return;
		} else {
			for(int i=0; i<=textures.length; i++){
				TM.addTexture(names[i], textures[i]);
			}
		}
	}
	
	public void load(Texture tex, String name){
		TM.addTexture(name, tex);
	}
	
	public void load(TextureRegion tex, String name){
		TM.addTexture(name, tex);
	}
	
	public void load(FileHandle handle, String name){
		TM.addTexture(name, handle);
	}
	
	public void loadFont(FileHandle handle, String name){
		TM.addFont(name, handle);
	}
	
	public void loadFont(BitmapFont font, String name){
		TM.addFont(name, font);
	}
	
	public void load(FileHandle handle, int x, int y, int w, int h, String name){
		TM.addTexture(name, handle, x, y, w, h);
	}
	
	public void load(String handle, int x, int y, int w, int h, String name){
		TM.addTexture(name, handle, x, y, w, h);
	}
	
	public TextureManager getTextureManager(){
		return TM;
	}

	public abstract void start();

	public void stop(){
		for(Batch bat: BATCHES){
			bat.dispose();
		}
		DRAWABLES.clear();
	}
	
	public void handleMouseInput(int x, int y){
		Vector2 s = new Vector2(x, y);
		Vector2 d = new Vector2();
		
		Array<Drawable> OnClickDrawables = new Array<Drawable>();
		for(Drawable obj: DRAWABLES){

			//Gdx.app.log("INFO", obj.getBOUNDS().y+"/"+obj);
			if(obj.BAT != null){
				d = project(obj.BAT.getVP(), s);
				if(obj.getBOUNDS().contains(d)){
					obj.setDown(true);
					obj.onClick(d.x, d.y);
				} else {
					obj.setDown(false);
				}
				//Gdx.app.log("INFO", "BATCH: "+obj.getBAT()+"//"+obj.getBOUNDS().y+"?"+obj.getBOUNDS().contains(d)+"???"+d.y+"/"+d.x);
			} else {
				Gdx.app.error("No Batch", obj + "has no attributed batch");
			}
		}
	}
	
	public void handleHover(int x, int y){
		Vector2 s = new Vector2(x, y);
		Vector2 d = new Vector2();
		
		Array<Drawable> OnClickDrawables = new Array<Drawable>();
		for(Drawable obj: DRAWABLES){

			//Gdx.app.log("INFO", obj.getBOUNDS().y+"/"+obj);
			if(obj.BAT != null){
				d = project(obj.BAT.getVP(), s);
				if(obj.getBOUNDS().contains(d)){
					if(!obj.isHovered()){
						obj.onHover(d.x, d.y);
						obj.setHovered(true);
					}
				} else {
					obj.setHovered(false);
				}
				//Gdx.app.log("INFO", "BATCH: "+obj.getBAT()+"//"+obj.getBOUNDS().y+"?"+obj.getBOUNDS().contains(d)+"???"+d.y+"/"+d.x);
			} else {
				Gdx.app.error("No Batch", obj + "has no attributed batch");
			}
		}
	}

	public abstract void resume();

	public abstract void pause();
	
	public abstract void dragged(int screenX, int screenY, int pointer);
	
	public static Vector2 project(OrthographicCamera cam, Vector2 s){
		Vector2 d = new Vector2(s);
		d.x = (s.x - cam.position.x)+(cam.viewportWidth/2);
		d.y = ((cam.viewportHeight - s.y) + cam.position.y)-(cam.viewportHeight/2);
		return d;
		
	}
	
	public SnapMap<String, Drawable> getDrawables(){
		return DRAWABLES;
	}
}
