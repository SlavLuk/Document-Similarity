package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
	
	private String file1;
	private String file2;
	private int docId1 = 1;
	private int docId2 = 2;
	private int shingleSize;
	private int kShingle = 300;
	private int poolSize = 20;
	private int blockQSize = 200;
	private BlockingQueue<Shingle> blockQ = null;
	
	
	public Launcher(){
		
		this.blockQ = new LinkedBlockingQueue<>(blockQSize);
	}
	
	
	
	public void launch(){
		
		Thread t1 = new Thread(new DocumentParser(blockQ, file1, shingleSize, docId1));
		Thread t2 = new Thread(new DocumentParser(blockQ, file2, shingleSize, docId2));
		Thread t3 = new Thread(new Consumer(blockQ,kShingle,poolSize));
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			
			
			t1.join();
			t2.join();
			t3.join();
			
		} catch (InterruptedException e) {
			
			System.out.println("InterruptedException occured "+e.getMessage());
		}
		
	

		
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}


	public int getShingleSize() {
		return shingleSize;
	}


	public void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}

}
