package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author Slav
 *
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
	 * @param bq
	 * @param rand
	 * @param poolSize
	 */
	public Consumer(BlockingQueue<Shinglable> bq, int rand, int poolSize) {

		this.bq = bq;
		this.k = rand;
		pool = Executors.newFixedThreadPool(poolSize);

		init();

	}

	@Override
	public void run() {

		int counter = 0;

		try {

			while (counter < 2) {// only 2 documents

				Shinglable s = bq.take();// blocking method until an item
											// becomes available

				if (s instanceof Poison) {// check on last item in the queue

					counter++;

				} else {

					pool.execute(new Runnable() {// execute pool of threads for
													// each shingle

						@Override
						public void run() {

							synchronized (map) {// lets only one thread at the
												// time to use map object

								minHash = map.get(s.getDocId());// get list
																// object or
																// return null

								if (minHash == null) {

									// called only once for each document and
									// initialise list with k number
									// of Max integer value
									minHash = new ArrayList<>(Collections.nCopies(k, Integer.MAX_VALUE));

									map.put(s.getDocId(), minHash);

								}

								// find the smallest for k number of random
								// integers
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

			pool.shutdown();// pool orderly shutdown

			while (!pool.isTerminated());//wait until all tasks have completed 
				

			findJaccard(map.get(1), map.get(2));//print out jaccard index

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}// end run method

	//find Jaccard index
	/**
	 * @param a
	 * @param b
	 */
	private void findJaccard(List<Integer> a, List<Integer> b) {

		List<Integer> intersection = new ArrayList<>(a);

		intersection.retainAll(b);

		float jaccard = ((float) intersection.size()) / ((k * 2) - ((float) intersection.size()));
		
		System.out.println("---------------------");
		System.out.printf("Similarity : %.2f %n", jaccard * 100);
		System.out.println("---------------------");
	}

	//initialise array with k number of random int 
	/**
	 * 
	 */
	private void init() {

		random = new Random();

		minhashes = new int[k];

		for (int i = 0; i < minhashes.length; i++) {

			minhashes[i] = random.nextInt();
		}

	}

}// end class