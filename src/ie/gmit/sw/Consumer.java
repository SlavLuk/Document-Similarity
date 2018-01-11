package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

/**
 * This <code>Consumer</code> class creates thread pool and executes concurrently 
 * calculating minhash value and resulting in finding Jaccard index of the two documents.
 * 
 * @author Slav
 * @version 1.0
 */
public class Consumer implements Runnable {

	 
	/**
	 * Declares instance variables.
	 * 
	 */
	private BlockingQueue<Shinglable> bq;
	private int[] minhashes;
	private int k;
	private ExecutorService pool;
	private Map<Integer, List<Integer>> map = new HashMap<>();
	private List<Integer> minHash;
	private Random random = null;

	
	/**
	 * Creates a new <code>Consumer</code> object based on the
	 * parameters specified.
	 * 
	 * @param bq BlockingQueue interface thread safe
	 * @param rand k numbers of random integers
	 * @param poolSize thread pool size 
	 */
	public Consumer(BlockingQueue<Shinglable> bq, int rand, int poolSize) {

		this.bq = bq;
		this.k = rand;
		pool = Executors.newFixedThreadPool(poolSize);

		init();

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		int counter = 0;

		try {
			
			// only 2 documents
			while (counter < 2) {
				
				// blocking method until an item becomes available
				Shinglable s = bq.take();
											 
				// check on last item in the queue
				if (s instanceof Poison) {

					counter++;

				} else {
					
					// execute pool of threads for each shingle
					pool.execute(new Runnable() {
												
						@Override
						public void run() {
							
							// lets only one thread at the time to use map object
							synchronized (map) {
								
								// get list object or return null
								minHash = map.get(s.getDocId());

								if (minHash == null) {


								// called only once for each document 
								minHash = new ArrayList<>(Collections.nCopies(k, Integer.MAX_VALUE));
	
								map.put(s.getDocId(), minHash);

								}

							// find the smallest for k number of random integers
							for (int i = 0; i < minhashes.length; i++) {

								int minValue = s.getShingleHash() ^ minhashes[i];

								// set the smallest number into list
								if (minValue < minHash.get(i)) {

									minHash.set(i, minValue);

									}

								} // for loop end

								map.put(s.getDocId(), minHash);

							}//synch end

						}// end run method
						
					});// pool execute end

				}//else end

			} // while loop end

						// pool orderly shutdown
						pool.shutdown();
						
						//wait until all tasks have completed
						while (!pool.isTerminated()); 
				
						//print out jaccard index
						findJaccard(map.get(1), map.get(2));

			} catch (Exception e) {

						System.out.println(e.getMessage());

			}

		}// end run method

	
	/**
	 * Finds Jaccard index.
	 * 
	 * @param a list of document
	 * @param b list of document
	 */
	private void findJaccard(List<Integer> a, List<Integer> b) {

		List<Integer> intersection = new ArrayList<>(a);

		intersection.retainAll(b);

		float jaccard = ((float) intersection.size()) / ((k * 2) - ((float) intersection.size()));
		
		System.out.println("---------------------");
		String index = String.format("%.2f", jaccard*100);		
		System.out.println("Similarity : "+index+" %");
		System.out.println("---------------------");
	}

	
	/**
	 * Initialise array with k number of random integers. 
	 */
	private void init() {

		random = new Random();

		minhashes = new int[k];

		for (int i = 0; i < minhashes.length; i++) {

			minhashes[i] = random.nextInt();
		}

	}

}// end class