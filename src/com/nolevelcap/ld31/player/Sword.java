package com.nolevelcap.ld31.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.nolevelcap.data.TextureManager;
import com.nolevelcap.render.Drawable;

public class Sword extends Drawable {
	
	public Vector2 playerCoords, ray1, ray2, ray3, ray4;
	public float r;
	
	private MainPlayer parent;

	public Sword(float x, float y, float w, float h) {
		super(x, y, w, h);
		playerCoords = new Vector2();
		ray1 = new Vector2(getX(), getY());
		ray2 = new Vector2(getX()+15, getY()+28);
		ray3 = new Vector2(getX(), getY());
		ray4 = new Vector2(getX()+15, getY()+28);
	}
	
	public void setParent(MainPlayer parent){
		this.parent = parent;
	}

	@Override
	public void render(TextureManager TM, SpriteBatch batch) {
		batch.draw(TM.getTexture("Sword"), getX()-4, getY()+36, getWIDTH()/2, -24, getWIDTH(), getHEIGHT(), 1, 1, r);
		batch.draw(TM.getTexture("__NO__TEXTURE__"), ray1.x, ray1.y, 1, 1);
		batch.draw(TM.getTexture("__NO__TEXTURE__"), ray2.x, ray2.y, 1, 1);
		batch.draw(TM.getTexture("__NO__TEXTURE__"), ray3.x, ray3.y, 1, 1);
		batch.draw(TM.getTexture("__NO__TEXTURE__"), ray4.x, ray4.y, 1, 1);
	}

	@Override
	public void logic() {
		if(!parent.attacking){
			r = -toMouseAngleRad() * MathUtils.radiansToDegrees;
		}
		
		ray1 = new Vector2(getX()+12, getY()+12);
		ray2= new Vector2(getX()+12, getY()+62);
		ray3 = new Vector2(getX()+12, getY()+112);
		ray4 = new Vector2(getX()+12, getY()+162);
		
		ray2 = new Vector2(getX()+12+(50*((float)MathUtils.sinDeg(-r))), getY()+12+(50*((float)MathUtils.cosDeg(-r))));
		ray3 = new Vector2(getX()+12+(100*((float)MathUtils.sinDeg(-r))), getY()+12+(100*((float)MathUtils.cosDeg(-r))));
		ray4 = new Vector2(getX()+12+(150*((float)MathUtils.sinDeg(-r))), getY()+12+(150*((float)MathUtils.cosDeg(-r))));

		playerCoords = new Vector2(parent.getX(), parent.getY());
		setX(playerCoords.x);
		setY(playerCoords.y);
	}
	
	protected float toMouseAngleRad() {
		double mouseAngle = Math.atan2((double) Gdx.input.getX() - (getX()+getWIDTH()/2),(double)  (720-Gdx.input.getY())-(getY()+getHEIGHT()/2));
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
	
	public static boolean intersectLineRectangle(Vector2 p1, Vector2 p2, Rectangle box){
		return Intersector.intersectSegments(p1, p2, new Vector2(box.x, box.y), new Vector2(box.x+box.width, box.y), new Vector2()) ||
				Intersector.intersectSegments(p1, p2, new Vector2(box.x+box.width, box.y), new Vector2(box.x+box.width, box.y+box.height), new Vector2()) ||
				Intersector.intersectSegments(p1, p2, new Vector2(box.x, box.y), new Vector2(box.x, box.y+box.height), new Vector2()) ||
				Intersector.intersectSegments(p1, p2, new Vector2(box.x, box.y+box.height), new Vector2(box.x+box.width, box.y+box.height), new Vector2()) ||
				box.contains(p1) || box.contains(p2);
	}
	
	public static boolean cintersectLineRectangle(Vector2 p1, Vector2 p2, Rectangle box){
		return Intersector.intersectSegments(p1, p2, new Vector2(box.x, box.y), new Vector2(box.x+box.width, box.y), new Vector2()) ||
				Intersector.intersectSegments(p1, p2, new Vector2(box.x+box.width, box.y), new Vector2(box.x+box.width, box.y+box.height), new Vector2()) ||
				Intersector.intersectSegments(p1, p2, new Vector2(box.x, box.y), new Vector2(box.x, box.y+box.height), new Vector2()) ||
				Intersector.intersectSegments(p1, p2, new Vector2(box.x, box.y+box.height), new Vector2(box.x+box.width, box.y+box.height), new Vector2()) ||
				box.contains(p2);
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
		// TODO Auto-generated method stub
		
	}

}
