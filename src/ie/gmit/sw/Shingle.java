package ie.gmit.sw;

/**
 * This Shingle class holds two instance variables and implements Shinglable interface.
 * 
 * @author Slav Lukyanov
 * @version 1.0
 *
 */
public class Shingle implements Shinglable {

	
	/**
	 * Declare instance variables.
	 */
	private int shingleHash;
	private int docId;

	/**
	 * Creates a new <code>Shingle</code> object based on the
	 * parameters specified.
	 * 
	 * @param s integer hash derived from token string
	 * @param id integer document id
	 */
	public Shingle(int s, int id) {
		this.shingleHash = s;
		this.docId = id;
	}

	/**
	 * Default constructor creates a new <code>Shingle</code> object without
	 * any parameters.
	 * 
	 */
	public Shingle() {

	}
	

	/* (non-Javadoc)
	 * @see ie.gmit.sw.Shinglable#getShingleHash()
	 */
	public int getShingleHash() {
		return shingleHash;
	}

	/**
	 * Sets instance shingleHash.
	 * 
	 * @param shingleHash integer as a hashcode
	 */
	public void setShingleHash(int shingleHash) {
		this.shingleHash = shingleHash;
	}
	
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Shinglable#getDocId()
	 */
	@Override
	public int getDocId() {

		return docId;
	}

	/**
	 * Sets instance docId.
	 * 
	 * @param docId document id
	 */
	public void setDocId(int docId) {
		this.docId = docId;
	}

}
