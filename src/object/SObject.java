package src.object;

import src.main.Paintable;

public abstract class SObject implements Paintable {
	public static class SObjectMaker {
		public static SObject makeSObject(String type, int oX, int oY) {
			return null;
		}
	}
	
	public int oX, oY;
}
