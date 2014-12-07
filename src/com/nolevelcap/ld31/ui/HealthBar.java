package com.nolevelcap.ld31.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.data.Timer;
import com.nolevelcap.render.Drawable;

public class HealthBar extends Drawable {
	public static int maxhealthbars = 28;
	
	public int currenthealth;
	private float opacity;
	
	private Timer healthCooldown;

	public HealthBar(float x, float y) {
		super(x, y, 64, 24*maxhealthbars);
		currenthealth = maxhealthbars;
		
		healthCooldown = new Timer(250);
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.setColor(1, 1, 1, opacity);
		if(currenthealth >= 1){
			batch.draw(TM.getTexture("Health-TopAcitve"), getX(), getY()+24*(maxhealthbars), 64, 24);
		} else {
			batch.draw(TM.getTexture("Health-TopEmpty"), getX(), getY()+24*(maxhealthbars), 64, 24);
		}
		if(currenthealth == maxhealthbars){
			batch.draw(TM.getTexture("Health-TopMidFull"), getX(), getY()+24*(maxhealthbars-1), 64, 24);
		} else if(currenthealth == maxhealthbars-1){
			batch.draw(TM.getTexture("Health-TopMidJustEmpty"), getX(), getY()+24*(maxhealthbars-1), 64, 24);
		} else {
			batch.draw(TM.getTexture("Health-TopMidEmpty"), getX(), getY()+24*(maxhealthbars-1), 64, 24);
		}
		for(int i=1; i<=maxhealthbars-2; i++){
			if(currenthealth == i){
				batch.draw(TM.getTexture("Health-MidJustEmpty"), getX(), getY()+24*i, 64, 24);
			} else if(currenthealth > i){
				batch.draw(TM.getTexture("Health-MidFull"), getX(), getY()+24*i, 64, 24);
			} else {
				batch.draw(TM.getTexture("Health-MidEmpty"), getX(), getY()+24*i, 64, 24);
			}
			
		}
		if(currenthealth >= 1){
			batch.draw(TM.getTexture("Health-BottomFull"), getX(), getY()+0, 64, 24);
		} else {
			batch.draw(TM.getTexture("Health-BottomEmpty"), getX(), getY()+0, 64, 24);
		}
		batch.setColor(1, 1, 1, 1);
	}

	@Override
	public void logic() {
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			takeHealth();
		}
		
		if(!isHovered()){
			opacity = 1;
		}

	}
	
	public void takeHealth(){
		if(currenthealth > 0 && healthCooldown.isComplete() || !healthCooldown.started){
			healthCooldown.reset();
			currenthealth--;
			healthCooldown.start();
		}
	}
	
	public void forceHealth(){
		if(currenthealth > 0){
			healthCooldown.reset();
			currenthealth--;
			healthCooldown.start();
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
