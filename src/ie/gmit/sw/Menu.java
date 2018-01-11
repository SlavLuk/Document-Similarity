package ie.gmit.sw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	//declare variables
	private Scanner scan ;
	private String fileName;
	private int shingleSize;
	private String option;
	private Launcher runner =new Launcher();
	
	public Menu (){//default constructor
		
		
	}
	

	//menu method for user inputs
	public void show(){
		
	try{//handle InputMismatchException exception
			
			scan =  new Scanner(System.in);
			
			System.out.println("***Document Comparison Service***");
			
			System.out.println("Please Enter a file name:");
			
			fileName = scan.next();
	
			runner.setFile1(validateFile(fileName));
			
			System.out.println("Please Enter a file name:");	
			
			fileName = scan.next();
				
			runner.setFile2(validateFile(fileName));
			
			System.out.println("Please Enter shingle size:");		
		
			shingleSize = scan.nextInt();
			
			runner.setShingleSize(shingleSize);
			
			System.out.println("Processing files...");
			runner.launch();
			
			System.out.println("Enter -1 to exit or 1 to continue");
			option = scan.next();
			
			while(!option.equalsIgnoreCase("-1")){
				
			System.out.println("Please Enter a file name:");
			
			fileName = scan.next();
	
			runner.setFile1(validateFile(fileName));
			
			System.out.println("Please Enter a file name:");	
			
			fileName = scan.next();
				
			runner.setFile2(validateFile(fileName));
			
			System.out.println("Please Enter shingle size:");		
		
			shingleSize = scan.nextInt();
			
			runner.setShingleSize(shingleSize);
			
			System.out.println("Processing files...");
			runner.launch();
			
			System.out.println("Enter -1 to exit or 1 to continue");
			option = scan.next();
		}
	}catch(InputMismatchException e){
		
		
		System.out.println("InputMismatchException occured "+e.getMessage());
	}

			scan.close();
	
	}
	//validate file name 
	private String validateFile(String file){
		
		if(!file.startsWith("./")){
			
			file = "./"+file;
		}
		
		return file;
	}

	
}
