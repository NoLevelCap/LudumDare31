package com.nolevelcap.ld31.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.data.Timer;
import com.nolevelcap.ld31.ui.BoostBar;
import com.nolevelcap.ld31.ui.HealthBar;
import com.nolevelcap.render.Drawable;

public class MainPlayer extends Entity{
	
	float bm, preR, spreR;
	protected boolean boost, attacking;
	private Timer BoostTimer;
	
	public Circle protectionSphere;
	
	private Sword swordy;

	public MainPlayer(float x, float y, float w, float h, float dx, float dy, float dw, float dh, Sword swordy) {
		super(x, y, w, h, dx, dy, dw, dh);
		this.boost = false;
		this.attacking = false;
		this.swordy = swordy;
		this.swordy.setParent(this);
		BoostTimer = new Timer(3000);
		boost = false;
		bm = 1f;
		
		protectionSphere = new Circle(dx + dw/2, dy + dh/2, 10f);
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.draw(TM.getTexture("TestBlob"), dx, dy, dw/2, dh/2, dw, dh, 1, 1, r);
		super.render(TM, batch);
	}

	@Override
	public void logic() {
		if(attacking){
			//Gdx.app.log("HMMM", "ORLY"+r);
			r += 480f  * bm * Gdx.graphics.getDeltaTime();
			if(r >= spreR + 360){
				swordy.r = spreR;
			} else {
				swordy.r += 960f  * bm * Gdx.graphics.getDeltaTime();
			}
			if(r >= preR + 360){
				r = preR;
				
				attacking = false;
			}
		} else {
			r = -toMouseAngleRad() * MathUtils.radiansToDegrees;
			
			if(Gdx.input.isKeyPressed(Keys.W)){
				updateY(150f * bm * (float) Math.cos(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dy += 150f * bm * (float) Math.cos(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
				updateX(-150f * bm * (float) Math.sin(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dx += -150f * bm * (float) Math.sin(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
			}
			
			if(Gdx.input.isKeyPressed(Keys.S)){
				updateY(-150f * bm * (float) Math.cos(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dy += -150f * bm * (float) Math.cos(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
				updateX(150f * bm * (float) Math.sin(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dx += 150f * bm * (float) Math.sin(-toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
			}
			
			if(Gdx.input.isKeyPressed(Keys.A)){
				updateY(150f * bm * (float) Math.sin(toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dy += 150f * bm * (float) Math.sin(toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
				updateX(-150f * bm * (float) Math.cos(toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dx += -150f * bm * (float) Math.cos(toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
			}
			
			if(Gdx.input.isKeyPressed(Keys.D)){
				updateY(-150f * bm * (float) Math.sin(toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dy += -150f * bm * (float) Math.sin(toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
				updateX(150f * bm * (float) Math.cos(toMouseAngleRad()) * Gdx.graphics.getDeltaTime());
				dx += 150f * bm * (float) Math.cos(toMouseAngleRad()) * Gdx.graphics.getDeltaTime();
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && ((BoostBar) getPS().getDrawables().get("BoostBar")).boostRequest() && !boost){
			boost = true;
			bm = 2f;
			BoostTimer.start();
		} else {
			if(BoostTimer.isComplete() && boost){
				BoostTimer.reset();
				boost = false;
				bm = 1f;
			}
		}
		
		super.logic();
	}
	
	public void triggerAttack(){
		if(!attacking && ((BoostBar) getPS().getDrawables().get("BoostBar")).boostRequest()){
			attacking = true;
			preR = r;
			spreR = swordy.r;
		}
	}
	
	public void loseHealth(){
		((HealthBar) getPS().getDrawables().get("HealthBar")).takeHealth();
	}

	@Override
	public void onClick(float x, float y) {
		super.onClick(x, y);
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

}
