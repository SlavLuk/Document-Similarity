package ie.gmit.sw;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu class to get user's inputs.
 * 
 * @author Slav Lukyanov
 * @version 1.0
 *
 */
public class Menu {

	
	/**
	 * declare variables
	 */
	private Scanner scan ;
	private String fileName;
	private int shingleSize;
	private int kRand;
	private String option;
	private Launcher launch =new Launcher();
	
	/**
	 * Default constructor.
	 */
	public Menu (){
		
		
	}
	
	/**
	 * Display menu.
	 */
	public void show(){
		
	try{
			
			scan =  new Scanner(System.in);
			
			System.out.println("***Document Comparison Service***");
			
			System.out.println("Please Enter a file name:");
			
			fileName = scan.next();
	
			launch.setFile1(validateFile(fileName));
			
			System.out.println("Please Enter a file name:");	
			
			fileName = scan.next();
				
			launch.setFile2(validateFile(fileName));
			
			System.out.println("Please Enter shingle size:(0-9)");		
		
			shingleSize = scan.nextInt();
			
			launch.setShingleSize(shingleSize);
			
			System.out.println("Please Enter a number for k random minhash:(200-500)");		
			
			kRand = scan.nextInt();
			
			launch.setkShingle(kRand);
			
			
			System.out.println("Processing files...");
			launch.runThreads();
			
			System.out.println("Enter -1 to exit or 1 to continue");
			option = scan.next();
			
	while(!option.equalsIgnoreCase("-1")){
				
			System.out.println("Please Enter a file name:");
			
			fileName = scan.next();
	
			launch.setFile1(validateFile(fileName));
			
			System.out.println("Please Enter a file name:");	
			
			fileName = scan.next();
				
			launch.setFile2(validateFile(fileName));
			
			System.out.println("Please Enter shingle size:(0-9)");		
		
			shingleSize = scan.nextInt();
			
			launch.setShingleSize(shingleSize);
			
			System.out.println("Please Enter a number for k random minhash:(200-500)");		
			
			kRand = scan.nextInt();
			
			launch.setkShingle(kRand);
			
			System.out.println("Processing files...");
			launch.runThreads();
			
			System.out.println("Enter -1 to exit or 1 to continue");
			option = scan.next();
		}
	}catch(InputMismatchException e){
		
		
		System.out.println("InputMismatchException occured "+e.getMessage());
	}

			scan.close();
	
	}
	
	/**
	 * Validates file name.
	 * 
	 * @param file name of the specified file
	 * @return file name of the file in current directory
	 */
	private String validateFile(String file){
		
		if(!file.startsWith("./")){
			
			file = "./"+file;
		}
		
		return file;
	}

	
}
