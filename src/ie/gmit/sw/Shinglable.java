package ie.gmit.sw;

/**
 * This interface <code>Shinglable</code> has two abstract methods to be implemented by a concrete class
 * 
 * @author Slav Lukyanov
 * @version 1.0
 *
 */
public interface Shinglable {

	/**
	 *Abstract method must be implemented by a concrete class. 
	 * 
	 * @return integer value as a hashcode
	 */
	public int getShingleHash();
	/**
	 * Abstract method must be implemented by a concrete class.
	 * 
	 * @return integer value as a docId
	 */
	public int getDocId();
}
