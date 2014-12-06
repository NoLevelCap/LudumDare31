package com.nolevelcap.ld31.game;

import com.badlogic.gdx.Gdx;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.render.Batch;
import com.nolevelcap.render.Screen;

public class OneScreen extends Screen {
	
	private Batch LandBatch;

	public OneScreen(TextureManager TM) {
		super(TM);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start() {
		LandBatch = new Batch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//LandBatch.addObject("Player", new D);
		addBatch(LandBatch);
	}
	

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}



	@Override
	public void logic() {
		// TODO Auto-generated method stub
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
