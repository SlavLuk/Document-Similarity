package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * This is a text parser class for parsing a text file into shingles.
 * 
 * @author Slav Lukyanov
 * @version 1.0
 * 
 */
public class DocumentParser implements Runnable {

	
	/**
	 * Declare instance variables.
	 * 
	 */
	private BlockingQueue<Shinglable> blockQ;
	private String fileName;
	private int shignleSize;
	private Deque<String> buffer = new LinkedList<>();
	private int docId;
	private BufferedReader br = null;

	
	/**
	 * Creates a new <code>DocumentParser</code> object based on the
	 * parameters specified.
	 * 
	 * @param bq data structure BlockingQueue implementations are thread-safe
	 * @param file file name
	 * @param sSize shingle size
	 * @param docId document id
	 */
	public DocumentParser(BlockingQueue<Shinglable> bq, String file, int sSize, int docId) {
		this.blockQ = bq;
		this.fileName = file;
		this.shignleSize = sSize;
		this.docId = docId;

	}
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		try {
			// create buffer reader and open a file
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

			String line = "";

			// read in lines until cursor reached EOF
			while ((line = br.readLine()) != null) {

				if (line.length() == 0) {// skip empty lines

					continue;

				}
				// split lines into a token and store in an array
				String[] tokens = line.split("[^a-zA-Z]+");

				addToBuffer(tokens);// pass token array to add to buffer list

				Shinglable s = getNextShingle();// create and return a new
												// shingle

				blockQ.put(s);// add shingle to blocking queue

			}

			flushBuffer();// create remaining shingles and mark last empty
							// shingle as poison

			br.close();// close buffer reader stream

		} catch (FileNotFoundException e) {// handle exceptions

			System.out.println("FileNotFoundException " + e.getMessage());

			System.exit(1);

		} catch (IOException e) {

			System.out.println("IOException " + e.getMessage());

		} catch (InterruptedException e) {

			System.out.println("InterruptedException " + e.getMessage());
		}

	}

	/**
	 * Inserts remaining items from buffer into BlockingQueue and marks the end of file by inserting Poison
	 * object.
	 * 
	 * @throws InterruptedException if interrupted while waiting
	 */
	private void flushBuffer() throws InterruptedException {

		while (buffer.size() > 0) {// check on buffer for more stuff

			Shinglable s = getNextShingle();// get shingle

			if (s != null) {

				blockQ.put(s);// put into blocking queue
			}
		}

		if (buffer.size() == 0) {// no more items put in poison instance to mark
									// the end of a document

			blockQ.put(new Poison(0, docId));
		}
	}

	/**
	 * Create a shingle object by specified size.
	 * 
	 * @return Shingle object
	 * @throws InterruptedException  if interrupted while waiting
	 */
	private Shingle getNextShingle() throws InterruptedException {

		String str = "";

		int i = 0;

		while (i < shignleSize) {

			if (buffer.peek() != null) {// get and not remove

				str += buffer.poll();// get and remove item

			}

			i++;
		}

		if (str.length() > 0) {

			return new Shingle(str.hashCode(), docId);// return new shingle

		} else {

			return null;
		}

	}

	/**
	 * Converts a string token to lower case and inserts into buffer list.
	 * 
	 * @param tokens an array of strings 
	 */
	private void addToBuffer(String[] tokens) {

		for (String s : tokens) {// traverse through array

			if (s.length() == 0) {// skip remaining empty space

				continue;
			}

			s = s.toLowerCase();

			buffer.offer(s);// add to buffer list

		}
	}

}
