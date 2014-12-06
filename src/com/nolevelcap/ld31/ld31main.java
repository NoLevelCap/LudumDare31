package com.nolevelcap.ld31;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.ld31.game.OneScreen;
import com.nolevelcap.render.Screen;

public class ld31main extends ApplicationAdapter {
	private TextureManager manager;
	private OneScreen TheOneScreen;
	
	@Override
	public void create () {
		manager = new TextureManager();
		TheOneScreen = new OneScreen(manager);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		TheOneScreen.render();
	}
}
