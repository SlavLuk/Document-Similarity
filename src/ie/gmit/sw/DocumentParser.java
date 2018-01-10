package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;


public class DocumentParser implements Runnable{
	
	private BlockingQueue<Shingle>blockQ;
	private String fileName;
	private  int shignleSize ;
	private Deque<String> buffer;
	private int docId;
	
	
	public DocumentParser(BlockingQueue<Shingle> bq,String file,int sSize,int docId){
		this.blockQ = bq;
		this.fileName = file;
		this.shignleSize = sSize;
		this.docId = docId;
		this.buffer = new LinkedList<>();
		
	}

	@Override
	public void run() {
		
		try {
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			
			String line = "";
			
			while((line = br.readLine())!=null){
				
				
				String[]tokens = line.split("[^a-zA-Z]+");
			
					addToBuffer(tokens);
				
					Shingle s = getNextShingle();
					
					blockQ.put(s);
			
			}
			
		
				flushBuffer();
			
				br.close();
			
		} catch (FileNotFoundException e) {
			
		
			System.out.println("FileNotFoundException "+e.getMessage());
			
		} catch (IOException e) {
			
			System.out.println("IOException "+e.getMessage());
			
		} catch (InterruptedException e) {
			
			System.out.println("InterruptedException "+e.getMessage());
		}
		
		
		
	}
	
	private  void flushBuffer() throws InterruptedException {
		
		while(buffer.size() > 0) {
			
			Shingle s = getNextShingle();
			
			if(s != null) {
				
				blockQ.put(s);
			}
			
		}
		
		if(buffer.size()==0){
			blockQ.put(new Poison(0,docId));
		}
	}
	
	private  Shingle getNextShingle() throws InterruptedException{
		
		String str = "";
	
		int i = 0;
		
			while(i<shignleSize){
						
				if(buffer.peek()!=null){
					
					str+=buffer.poll();
					
				}
												
					i++;	
			}
		
				if(str.length()>0){
	
					return new Shingle(str.hashCode(),docId);
					
				}else{
					
					return null;
				}
		
	}
	
	
	
	private void addToBuffer(String[] tokens){
		
		for(String s:tokens){
		
			if(s.length()==0){
					
				continue;
					
				}
			
			s = s.toLowerCase();
						
			buffer.offer(s);
		
		}
	}
	

}
