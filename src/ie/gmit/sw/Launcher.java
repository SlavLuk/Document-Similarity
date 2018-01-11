package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {

	// declare instance variables
	private String file1;
	private String file2;
	private int docId1 = 1;
	private int docId2 = 2;
	private int shingleSize;
	private int kShingle = 300;// k number random shingles
	private int poolSize = 20;// pool threads
	private int blockQSize = 200;// capacity for blocking queue
	private BlockingQueue<Shinglable> blockQ = null;

	public Launcher() {

		this.blockQ = new LinkedBlockingQueue<>(getBlockQSize());
	}

	public void launch() {

		try {

			// create threads and pass in runnable objects
			Thread t1 = new Thread(new DocumentParser(getBlockQ(), getFile1(), getShingleSize(), getDocId1()));
			Thread t2 = new Thread(new DocumentParser(getBlockQ(), getFile2(), getShingleSize(), getDocId2()));
			Thread t3 = new Thread(new Consumer(getBlockQ(), getkShingle(), getPoolSize()));

			// start threads
			t1.start();
			t2.start();
			t3.start();

			// call join to wait for this thread to die.
			t1.join();
			t2.join();
			t3.join();

		} catch (InterruptedException e) {

			System.out.println("InterruptedException occured " + e.getMessage());
		}

	}

	// getters setters methods
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

	public int getDocId1() {
		return docId1;
	}

	public void setDocId1(int docId1) {
		this.docId1 = docId1;
	}

	public int getDocId2() {
		return docId2;
	}

	public void setDocId2(int docId2) {
		this.docId2 = docId2;
	}

	public int getkShingle() {
		return kShingle;
	}

	public void setkShingle(int kShingle) {
		this.kShingle = kShingle;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getBlockQSize() {
		return blockQSize;
	}

	public void setBlockQSize(int blockQSize) {
		this.blockQSize = blockQSize;
	}

	public BlockingQueue<Shinglable> getBlockQ() {
		return blockQ;
	}

}
