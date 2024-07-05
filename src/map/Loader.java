package src.map;

/**
 * Loader:			used specifically during the loading of a map.
 * 
 * @author			Will Otterbein
 * @version			2024-1
 */
public interface Loader<T1, T2, T3> {
	/**
	 * loadFunction:		loads stuff. Three parameter version
	 * 
	 * @param <T1>			usually a String
	 * @param <T2>			usually an Integer
	 * @param <T3>			usually an Integer
	 * @param parm1			
	 * @param parm2
	 * @param parm3
	 */
	public void loadFunction(T1 parm1, T2 parm2, T3 parm3);
}
