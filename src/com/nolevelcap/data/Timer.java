package com.nolevelcap.data;

import com.badlogic.gdx.Gdx;

public class Timer {
	
	private long duration, start, finish;
	public boolean started = false, invalid;
	
	public Timer(long duration){
		if(duration == 0){
			invalid = true;
		}
		this.duration = duration;
		this.start = System.currentTimeMillis();
	}
	
	public void start(){
		if(!started && !invalid){
			started = true;
			this.start = System.currentTimeMillis();
			this.finish = start + duration;
		}
	}
	
	public boolean isComplete(){
		if(invalid){
			return false;
		}
		if(finish - System.currentTimeMillis() > 0 && started){
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isNearComplete(){
		if(invalid){
			return false;
		}
		if(finish - System.currentTimeMillis() > duration*0.1f && started){
			return false;
		} else {
			return true;
		}
	}
	
	public void reset(){
		this.started = false;
	}
	
	public void reset(long newDuration){
		this.duration = newDuration;
		this.started = false;
	}
}
