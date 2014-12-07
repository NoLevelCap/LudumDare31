package com.nolevelcap.ld31.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.render.Drawable;

public class BoostBar extends Drawable {
	public static int maxboostbars = 28;
	
	public int currentboost;
	
	private float opacity;

	public BoostBar(float x, float y) {
		super(x, y, 64, 24*maxboostbars);
		currentboost = maxboostbars;
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.setColor(1, 1, 1, opacity);
		if(currentboost >= 1){
			batch.draw(TM.getTexture("Boost-TopAcitve"), getX(), getY()+24*(maxboostbars), 64, 24);
		} else {
			batch.draw(TM.getTexture("Boost-TopEmpty"), getX(), getY()+24*(maxboostbars), 64, 24);
		}
		if(currentboost == maxboostbars){
			batch.draw(TM.getTexture("Boost-TopMidFull"), getX(), getY()+24*(maxboostbars-1), 64, 24);
		} else if(currentboost == maxboostbars-1){
			batch.draw(TM.getTexture("Boost-TopMidJustEmpty"), getX(), getY()+24*(maxboostbars-1), 64, 24);
		} else {
			batch.draw(TM.getTexture("Boost-TopMidEmpty"), getX(), getY()+24*(maxboostbars-1), 64, 24);
		}
		for(int i=1; i<=maxboostbars-2; i++){
			if(currentboost == i){
				batch.draw(TM.getTexture("Boost-MidJustEmpty"), getX(), getY()+24*i, 64, 24);
			} else if(currentboost > i){
				batch.draw(TM.getTexture("Boost-MidFull"), getX(), getY()+24*i, 64, 24);
			} else {
				batch.draw(TM.getTexture("Boost-MidEmpty"), getX(), getY()+24*i, 64, 24);
			}
			
		}
		if(currentboost >= 1){
			batch.draw(TM.getTexture("Boost-BottomFull"), getX(), getY()+0, 64, 24);
		} else {
			batch.draw(TM.getTexture("Boost-BottomEmpty"), getX(), getY()+0, 64, 24);
		}
		batch.setColor(1, 1, 1, 1);
	}

	@Override
	public void logic() {
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			boostRequest();
		}
		
		if(!isHovered()){
			opacity = 1;
		}
	}
	
	public boolean boostRequest(){
		if(currentboost <= 0){
			return false;
		} else {
			currentboost--;
			return true;
		}
	}

	@Override
	public void onClick(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHover(float x, float y) {
		opacity = 0.25f;
	}

}
