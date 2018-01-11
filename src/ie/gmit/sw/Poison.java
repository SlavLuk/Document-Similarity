package ie.gmit.sw;

/**
 * This class is a subclass of <code>Shingle</code> class.
 * 
 * 
 * @author Slav Lukyanov
 * @version 1.0
 *
 */
public class Poison extends Shingle {

	
	
	/**
	 * Creates a new <code>Poison</code> object based on the
	 * parameters specified by calling super class constructor.
	 * 
	 * @param p integer  may be any value
	 * @param id integer document id
	 */
	public Poison(int p,int id){
		super(p,id);
	}
	
	/**
	 * Default constructor creates a new <code>Poison</code> object without
	 * any parameters.
	 */
	public Poison(){
		
	}
	
		
	
}
