package com.nolevelcap.ld31.land;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.render.Drawable;

public class Tile extends Drawable {
	private static int D = 32;
	
	private String TextureName;
	public int gid, tx, ty;

	public Tile(String TextureName, int gid, float x, float y, int tx, int ty) {
		super(x, y, D, D);
		this.TextureName = TextureName;
		this.gid = gid;
		this.tx = tx;
		this.ty = ty;
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.draw(TM.getTexture(TextureName), getX(), getY(), getWIDTH(), getHEIGHT());
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub

	}
	
	public String getName(){
		return TextureName;
	}

	@Override
	public void onClick(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}
	
	public void onCreate() {
		//Gdx.app.log("", "I'm Alive");
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHover(float x, float y) {
		// TODO Auto-generated method stub
		
	}

}
