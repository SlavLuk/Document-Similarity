package ie.gmit.sw;

public class Shingle{
	
	private int shingleHash;
	private int id;
	
	public Shingle(int s,int docId){
		this.shingleHash = s;
		this.id = docId;
	}
	public Shingle(){
		
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShingleHash() {
		return shingleHash;
	}
	public void setShingleHash(int shingleHash) {
		this.shingleHash = shingleHash;
	}

}
