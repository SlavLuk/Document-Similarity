package ie.gmit.sw;

import java.util.Scanner;

public class Menu {

	private Scanner scan ;
	private String fileName;
	private int shingleSize;
	private int option;
	private Launcher runner =new Launcher();
	
	public Menu (){
		
		
	}
	

	
	public void show(){
		
	
			
			scan =  new Scanner(System.in);
			
			System.out.println("***Document Comparison Service***");
			
			System.out.println("Please Enter  first file name:");
			
			fileName = scan.next();
	
			runner.setFile1(validateFile(fileName));
			
			System.out.println("Please Enter  second file name:");	
			
			fileName = scan.next();
			
		
			runner.setFile2(validateFile(fileName));
			
			System.out.println("Please Enter shingle size:");		
		
			shingleSize = scan.nextInt();
			runner.setShingleSize(shingleSize);
			
			System.out.println("Processing files...");
			runner.launch();
			
			
	
			scan.close();
	
		
		
		
	}
	private String validateFile(String file){
		
		if(!file.startsWith("./")){
			
			file = "./"+file;
		}
		
		return file;
	}

	
}
