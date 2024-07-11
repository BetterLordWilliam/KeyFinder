package src.entity;

import src.main.Paintable;

public abstract class Entity implements Paintable {
    public static class EntityMaker {
    	public static Entity makeEntity(String type, int eX, int eY) {
    		return null;
    	}
    }
	
    public int eX, eY;
    
	public abstract void update();
}
