package com.nolevelcap.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureManager;

public abstract class Drawable {
	
	private Rectangle BOUNDS;
	private int ZIndex;
	private boolean isDown;
	protected Screen PS;
	protected Batch BAT;
	
	public Drawable(Rectangle BOUNDS){
		this.setBounds(BOUNDS);
	}
	
	public Drawable(float x, float y, float w, float h){
		this.setBounds(x, y, w, h);
	}
	
	public abstract void render(TextureManager TM, SpriteBatch batch);
	public abstract void logic();

	public Rectangle getBOUNDS() {
		return BOUNDS;
	}

	public void setBounds(Rectangle BOUNDS) {
		this.BOUNDS = BOUNDS;
	}
	
	public void setBounds(float x, float y, float w, float h) {
		this.setBounds(new Rectangle(x, y, w, h));
	}
	
	public float getX(){
		return getBOUNDS().x;
	}
	
	public float getY(){
		return getBOUNDS().y;
	}
	
	public float getWIDTH(){
		return getBOUNDS().width;
	}
	
	public float getHEIGHT(){
		return getBOUNDS().height;
	}

	public int getZIndex() {
		return ZIndex;
	}

	public void setZIndex(int zIndex) {
		ZIndex = zIndex;
	}
	
	public abstract void onClick(float x, float y);
	public abstract void touchDown();
	public abstract void touchUp();

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}
	
	public void setPS(Screen PS){
		this.PS = PS;
	}
	
	protected Screen getPS(){
		if(PS != null){
			return PS;
		} else {
			Gdx.app.error("Unlinked Drawable", this+" needs to be attributed to a Screen");
			return null;
		}
	}
	
	public void setBAT(Batch BAT){
		this.BAT = BAT;
	}
	
	protected Batch getBAT(){
		if(BAT != null){
			return BAT;
		} else {
			Gdx.app.error("Unlinked Drawable", this+" needs to be attributed to a Batch");
			return null;
		}
	}
}
