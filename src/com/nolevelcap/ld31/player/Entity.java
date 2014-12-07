package com.nolevelcap.ld31.player;

import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.ld31.land.Tile;
import com.nolevelcap.render.Drawable;

public class Entity extends Drawable{
	
	protected float r, dw, dh, dx, dy;
	public boolean overlapped, vaildPosition;
	public Rectangle intersection;

	public Entity(float x, float y, float w, float h, float dx, float dy, float dw, float dh) {
		super(x, y, w, h);
		this.dw = dw;
		this.dh = dh;
		this.dx = dx;
		this.dy= dy;
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	public float getR(){
		return r;
	}

	@Override
	public void logic() {
		if(!overlapped || !vaildPosition){
			//DEATH
			movePlayer(new Vector2(1280/2, 720/2));
		}


	}
	
	public void respawn(){
		movePlayer(new Vector2(1280/2, 720/2));
	}
	
	
	protected float toMouseAngleRad() {
		double mouseAngle = Math.atan2((double) Gdx.input.getX() - (dx+dw/2),(double)  (720-Gdx.input.getY())-(dy+dh/2));
		return (float) mouseAngle;
	}
	
	protected float VecAngleRad(Vector2 a, Vector2 b) {
		double mangle = Math.atan2((double) a.y - b.y,(double) a.x - b.x);
		return (float) mangle;
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
	
	public void movePlayer(Vector2 move_position){
		setX(move_position.x+dw/4);
		dx = (move_position.x);
		setY(move_position.y+dh/4);
		dy = (move_position.y);
	}
	
	public void shiftPosition(float incrementX, float incrementY){
		updateX(incrementX);
		dx += (incrementX);
		updateY(incrementY);
		dy += (incrementY);
	}

	@Override
	public void onHover(float x, float y) {
		// TODO Auto-generated method stub
		
	}

}
