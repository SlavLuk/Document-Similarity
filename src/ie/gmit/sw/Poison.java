package ie.gmit.sw;

public class Poison extends Shingle {
	
	private int id;
	private int poisonPill;
	
	
	public Poison(int p,int id){
		
		poisonPill = p;
		this.id = id;
		
	
	}
	
	public Poison(){
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPoisonPill() {
		return poisonPill;
	}
	public void setPoisonPill(int poisonPill) {
		this.poisonPill = poisonPill;
	}


	
	
}
