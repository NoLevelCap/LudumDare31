package com.nolevelcap.data;


import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureManager {
	private HashMap<String, TextureRegion> Textures;
	private HashMap<String, BitmapFont> Fonts;
	
	public TextureManager(){
		Textures = new HashMap<String, TextureRegion>();
		addTexture("__NO__TEXTURE__", Gdx.files.internal("NO_TEX.png"));
		
		Fonts = new HashMap<String, BitmapFont>();
	}
	
	public TextureRegion getTexture(String name){
		if(Textures.containsKey(name)){
			return Textures.get(name);
		} else {
			Gdx.app.error("No Texture Exists", "The texture with the name \""+name+"\" does not exist.");
			return Textures.get("__NO__TEXTURE__");
		}
	}
	
	public void addTexture(String name, Texture tex){
		Textures.put(name, new TextureRegion(tex));
	}
	
	public void addTexture(String name, TextureRegion tex){
		Textures.put(name, tex);
	}
	
	public void addTexture(String name, FileHandle tex){
		Textures.put(name, new TextureRegion(new Texture(tex)));
	}
	
	public void addTexture(String name, FileHandle tex, int x, int y, int w, int h){
		Textures.put(name, new TextureRegion(new Texture(tex), x, y, w, h));
	}
	
	public void removeTexture(String name){
		Textures.remove(name);
	}
	
	public void addFont(String name, BitmapFont font){
		Fonts.put(name, font);
	}
	
	public void addFont(String name, FileHandle font){
		Fonts.put(name, new BitmapFont(font));
	}
	
	public void removeFont(String name){
		Fonts.remove(name);
	}
	
	public BitmapFont getFont(String name){
		if(Fonts.containsKey(name)){
			return Fonts.get(name);
		} else {
			return new BitmapFont();
		}
	}
}
