package com.nolevelcap.ld31.land;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Map {
	public int D;
	public int mapW;
	public int mapH;
	
	private HashMap<Integer,String> tileData;
	private HashMap<Vector2,TileProperties> mapData;
	
	
	public Map(FileHandle file){
		tileData = new HashMap<Integer, String>();
		mapData = new HashMap<Vector2, TileProperties>();
		
		try {
			readMap(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readMap(FileHandle file) throws IOException{
		XmlReader reader = new XmlReader();
		Element topLevel = reader.parse(file);
		
		D = Integer.parseInt(topLevel.get("tilewidth"));
		mapW = Integer.parseInt(topLevel.get("width"));
		mapH = Integer.parseInt(topLevel.get("height"));
		
		handleTileData(file);
		handleMapData(topLevel.getChildByName("layer").getChildByName("data"));
	}
	
	private void handleTileData(FileHandle file) throws IOException{
		XmlReader reader = new XmlReader();
		Element topLevel = reader.parse(file.parent().child("Tiles.tsx"));
		for(Element tile:topLevel.getChildrenByName("tile")){
			int id = Integer.parseInt(tile.get("id"));
			String tileName = tile.getChildByName("properties").getChild(0).getAttribute("value");
			tileData.put(id, tileName);
		};
	}
	
	private void handleMapData(Element data){
		int dx=0, dy=0;
		for(Element tile:data.getChildrenByName("tile")){
			int gid = Integer.parseInt(tile.get("gid"));
			mapData.put(new Vector2(dx, dy), new TileProperties(dx, dy, gid, getTextureData(gid)));
			dx++;
			if(dx==mapW){
				dy++;
				dx=0;
			}
		};
	}
	
	public String getTextureData(int gid){
		if(tileData.get(gid-1)==null){
			return tileData.get(11);
		} else {
			return tileData.get(gid-1);
		}
	}
	
	public TileProperties getTile(int x, int y){
		return mapData.get(new Vector2(x, y));
	}
}
