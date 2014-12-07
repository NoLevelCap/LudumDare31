package com.nolevelcap.ld31.land;

public class TileProperties {
	public int x, y, gid;
	public String texData;
	
	public TileProperties(int x, int y, int gid, String texData){
		this.x = x;
		this.y = y;
		this.gid = gid;
		this.texData = texData;
	}
}
