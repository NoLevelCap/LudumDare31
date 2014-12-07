package com.nolevelcap.ld31.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nolevelcap.data.TextureManager;

public class Goblin extends Entity {
	
	Animation goblin_A;
	private float timeSince;
	Vector2 target;
	boolean canSeePlayer = true;
	
	
	public Goblin(float x, float y, float w, float h, float dx, float dy,
			float dw, float dh, Array<TextureRegion> keyFrames) {
		super(x, y, w, h, dx, dy, dw, dh);

		goblin_A = new Animation(0.125f, keyFrames, PlayMode.LOOP);
		this.timeSince = goblin_A.getAnimationDuration()-goblin_A.getFrameDuration();
		
		
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.draw(goblin_A.getKeyFrame(timeSince, true), dx, dy, dw/2, dh/2, dw, dh, 1, 1, r);
		//batch.draw(TM.getTexture("__NO__TEXTURE__"), getX(), getY(), getWIDTH(), getHEIGHT());
		super.render(TM, batch);
	}

	@Override
	public void logic() {
		MainPlayer Player = (MainPlayer) getPS().getDrawables().get("Player");
		
		target = new Vector2(Player.getX()-Player.getWIDTH()/2,Player.getY()-Player.getHEIGHT()/2);
		
		if(canSeePlayer){
			r = VecAngleRad(target, new Vector2(getX()-getWIDTH()/2, getY()-getHEIGHT()/2)) * MathUtils.radiansToDegrees - 90;
		}else {
			r = 0;
		}
		
		
		
		if(canSeePlayer){
			updateY(100f * (float) MathUtils.cos(r*MathUtils.degreesToRadians) * Gdx.graphics.getDeltaTime());
			dy += 100f  * (float) MathUtils.cos(r*MathUtils.degreesToRadians) * Gdx.graphics.getDeltaTime();
			updateX(-100f * (float) MathUtils.sin(r*MathUtils.degreesToRadians) * Gdx.graphics.getDeltaTime());
			dx += -100f * (float) MathUtils.sin(r*MathUtils.degreesToRadians) * Gdx.graphics.getDeltaTime();
		}
		
		//Gdx.app.log(r+"", (float) MathUtils.cos(r*MathUtils.degreesToRadians)* MathUtils.radiansToDegrees + "");
		//Gdx.app.log(r+"", (float) MathUtils.sin(r*MathUtils.degreesToRadians)* MathUtils.radiansToDegrees + "");
		
		timeSince+=Gdx.graphics.getDeltaTime();
		super.logic();
	}

}
