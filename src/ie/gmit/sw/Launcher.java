package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class creates and executes three threads concurrently.
 * 
 * 
 * @author Slav Lukyanov
 * @version 1.0
 */
public class Launcher {

	
	/**
	 * Declares instance variables.
	 * 
	 */
	private String file1;
	private String file2;
	private int docId1 = 1;
	private int docId2 = 2;
	private int shingleSize;
	private int kShingle ;
	private int poolSize = 20;// pool threads
	private int blockQSize = 200;// capacity for blocking queue
	private BlockingQueue<Shinglable> blockQ = null;

	/**
	 * Default constructor creates <code>Launcher</code> object and initialises <code>BlockingQueue</code>
	 * interface to concrete class <code>LinkedBlockingQueue</code> without any parameters.
	 * 
	 * 
	 */
	public Launcher() {

		this.blockQ = new LinkedBlockingQueue<>(getBlockQSize());
	}

	/**
	 * Creates threads objects and executes them.
	 * 
	 */
	public void runThreads() {

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

	
	/**
	 * Gets file name.
	 * 
	 * @return file name as a string
	 */
	public String getFile1() {
		return file1;
	}

	/**
	 * Sets file name.
	 * 
	 * @param file1 file name
	 */
	public void setFile1(String file1) {
		this.file1 = file1;
	}

	/**
	 * Gets file name.
	 * 
	 * @return file name as a string
	 */
	public String getFile2() {
		return file2;
	}
	
	/**
	 * Sets file name.
	 * 
	 * @param file2 file name
	 */
	public void setFile2(String file2) {
		this.file2 = file2;
	}

	/**
	 * Gets shingle size.
	 * 
	 * @return integer value
	 */
	public int getShingleSize() {
		return shingleSize;
	}

	/**
	 * Sets shingle size.
	 * 
	 * @param shingleSize integer value
	 */
	public void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}

	/**
	 * Gets document id.
	 * 
	 * @return integer value
	 */
	public int getDocId1() {
		return docId1;
	}

	/**
	 * Sets document id.
	 * 
	 * @param docId1 integer value
	 */
	public void setDocId1(int docId1) {
		this.docId1 = docId1;
	}

	/**
	 * Gets document id.
	 * 
	 * @return integer value
	 */
	public int getDocId2() {
		return docId2;
	}

	/**
	 * Sets document id.
	 * 
	 * @param docId2 integer value
	 */
	public void setDocId2(int docId2) {
		this.docId2 = docId2;
	}

	/**
	 * Gets a number of random shingles for each document.
	 * 
	 * @return integer value
	 */
	public int getkShingle() {
		return kShingle;
	}

	/**
	 * Sets a number of random shingles for each document.
	 * 
	 * @param kShingle integer value
	 */
	public void setkShingle(int kShingle) {
		this.kShingle = kShingle;
	}

	/**
	 * Gets threads pool size.
	 * 
	 * @return integer value
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * Sets threads pool size.
	 * 
	 * @param poolSize integer value
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * Gets BlockingQueue capacity size.
	 * 
	 * @return integer value
	 */
	public int getBlockQSize() {
		return blockQSize;
	}

	/**
	 * Sets capacity size for BlockingQueue.
	 * 
	 * @param blockQSize integer value for BlockingQueue size
	 */
	public void setBlockQSize(int blockQSize) {
		this.blockQSize = blockQSize;
	}

	/**
	 * Gets BlockingQueue reference.
	 * 
	 * @return BlockingQueue 
	 */
	public BlockingQueue<Shinglable> getBlockQ() {
		return blockQ;
	}

}
