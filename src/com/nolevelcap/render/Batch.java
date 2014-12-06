package com.nolevelcap.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.nolevelcap.data.TextureManager;

public class Batch {
	protected Screen SC;
	
	private Array<Drawable> DRAWABLES;
	private SpriteBatch BATCH;
	private OrthographicCamera VP;
	
	public Batch(float W, float H){
		DRAWABLES = new Array<Drawable>();
		BATCH = new SpriteBatch();
		VP = new OrthographicCamera(W, H);
		VP.setToOrtho(false);	
	}
	
	public void render(TextureManager TM){
		BATCH.setProjectionMatrix(VP.combined);
		BATCH.begin();
		for(Drawable obj: DRAWABLES){
			obj.render(TM, BATCH);
		}
		BATCH.end();
	}
	
	public void update(){
		VP.update();
	}
	
	public void addObject(String name, Drawable value){
		DRAWABLES.add(value);
		value.setBAT(this);
		SC.addObject(name, value);
	}
	
	public void changeObject(String name, Drawable oldValue, Drawable value){
		int indexOf = DRAWABLES.indexOf(oldValue, false);
		DRAWABLES.removeIndex(indexOf);
		DRAWABLES.insert(indexOf, value);
		value.setBAT(this);
		SC.changeObject(name, value);
	}
	
	public void removeObject(String name){
		int indexOf = DRAWABLES.indexOf(SC.DRAWABLES.get(name), false);
		DRAWABLES.removeIndex(indexOf);
		SC.removeObject(name);
	}
	
	public void iChangeObject(Drawable value){
		int index = DRAWABLES.indexOf(value, false);
		DRAWABLES.insert(index, value);
		DRAWABLES.removeValue(value, false);
	}
	
	public void dispose(){
		BATCH.dispose();
		DRAWABLES.clear();
	}
	
	public OrthographicCamera getVP(){
		return VP;
	}
	
	public void setSC(Screen SC){
		this.SC = SC;
	}
	
	public void setShader(String vert, String frag){
		BATCH.setShader(new ShaderProgram(vert, frag));
	}
	
	public void setShader(ShaderProgram p){
		BATCH.setShader(p);
	}
}
