package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;


public class Consumer implements Runnable{

	private BlockingQueue<Shingle> bq;
	private int[] minhashes;
	private  int k ;
	private ExecutorService pool;
	private ConcurrentMap<Integer,List<Integer>> map = new ConcurrentHashMap<>();
	private List<Integer> minHash;
	private Random random = null;
	int j = 0;
	public Consumer(BlockingQueue<Shingle> bq,int rand,int poolSize){
		
		this.bq = bq;
		this.k = rand;
		pool = Executors.newFixedThreadPool(poolSize);
		
		init();
		
		
	}

	
	
			@Override
			public void run() {
		
				int counter =0;
			
				
					try {
					
						while(counter<2){
				
							Shingle s = bq.take();
						
								
						if(s instanceof Poison){
							
							counter++;
							
							}else{
							
							pool.execute(new Runnable() {
								
							@Override
							public void run() {
								
							synchronized (map) {
								
							
								minHash = map.get(s.getId());
								
								if(minHash==null){
			
								minHash = new ArrayList<>(Collections.nCopies(k,Integer.MAX_VALUE ));
								
								
								map.put(s.getId(), minHash);
								
								}
								
							for(int i= 0;i<minhashes.length;i++){
								
								int minValue = s.getShingleHash()^minhashes[i];
													
								 if(minValue <minHash.get(i)){
									
											minHash.set(i, minValue);
											
										}
					
				
							}//end for loop
						
					
							
							map.put(s.getId(), minHash);
							
							
							}
																
						}//run method
				});
							
						
							
						}
				
			}//while loop end
	
				
			pool.shutdown();
			
			while(!pool.isTerminated()){
				
				
			}
			
			List<Integer> a = map.get(1);
			List<Integer> b = map.get(2);
			
			System.out.printf("Similarity : %.2f %n",findJaccard(a,b));
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			}
		
	}//end run method
			
			private float findJaccard(List<Integer> a,List<Integer>b){
				
				List<Integer> intersection = new ArrayList<>(a);
				
				intersection.retainAll(b);

				float jaccard = ((float)intersection.size()) / ((k*2) - ((float)intersection.size()));
			
				
				return 	jaccard;
			}
			
			
			private void init(){
				
				random = new Random();
				
				minhashes = new int[k]; 
				
				for(int i = 0; i < minhashes.length; i++) {
					
					minhashes[i] = random.nextInt();
				}
			
			}
	
}//end class