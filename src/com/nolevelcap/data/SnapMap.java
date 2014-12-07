package com.nolevelcap.data;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class SnapMap<K, T> implements Iterable<T>{
	
	private Array<K> keyIndex;
	private Array<T> contentIndex;
	
	public SnapMap(){
		keyIndex = new Array<K>();
		contentIndex = new Array<T>();
		
	}
	
	public void change(K key, T newContent){
		int indexOf = keyIndex.indexOf(key, false);
		contentIndex.removeIndex(indexOf);
		contentIndex.insert(indexOf, newContent);
	}
	
	public void put(K key, T content){
		contentIndex.add(content);
		keyIndex.add(key);
	}
	
	public int size(){
		return contentIndex.size;
	}
	
	public void clear(){
		contentIndex.clear();
		keyIndex.clear();
	}
	
	public boolean contains(K key){
		return contentIndex.contains(get(key), false);
	}
	
	public T get(K key){
		int indexOf = keyIndex.indexOf(key, false);
		if(indexOf != -1){
			return contentIndex.get(indexOf);
		} else {
			Gdx.app.error("Invalid Key", key+" does not exist.");
			return null;
		}
	}
	
	public K[] listKeys(){
		return keyIndex.items;
	}
	
	@Override
	public Iterator<T> iterator() {
		return contentIndex.iterator();
	}
	
	public void remove(K key){
		int indexOf = keyIndex.indexOf(key, false);
		keyIndex.removeIndex(indexOf);
		contentIndex.removeIndex(indexOf);
	}
	
	public Array<T> contents(){
		return contentIndex;
	}
}
