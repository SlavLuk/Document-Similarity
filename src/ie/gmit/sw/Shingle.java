package ie.gmit.sw;

public class Shingle implements Shinglable {//implement interface

	//declare instance variables
	private int shingleHash;
	private int docId;

	public Shingle(int s, int id) {//two argument constructor
		this.shingleHash = s;
		this.docId = id;
	}

	public Shingle() {//default constructor

	}
	
	//getters setters methods

	@Override
	public int getShingleHash() {
		return shingleHash;
	}

	public void setShingleHash(int shingleHash) {
		this.shingleHash = shingleHash;
	}
	
	@Override
	public int getDocId() {

		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

}
