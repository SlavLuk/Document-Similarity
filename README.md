# Document Similarity Project

 
Java API for Measuring Document Similarity.

Java API that can rapidly compare two large text files by
computing their Jaccard Index.

All Java API consists of 7 concrete classes and one interface.

Shinglable interface plays role as one of the ways to construct modular and reusable software.
By using polymorphism.

DocumentParser class uses two threads to concurrently get each document parsed.
Consumer class uses reusable fixed size thread pool for rapid calculation of minhash.

To run application you need to run Runner class
at the menu user would be asked to enter 4 parameters (file name 1,file name 2,shingle size,random number of minhashes) after processing files a user will see Jaccard Index Similarity of two compared documents.


## Installation

1. You can clone your repository to create a local copy on your computer and sync between the two locations 
2. On GitHub, navigate to the main page of the repository
3. Clone or download button Under the repository name, click Clone or download
4. Clone URL button In the Clone with HTTPs section, click to copy the clone URL for the repository. 
5. Open Git Bash. 
6. Change the current working directory to the location where you want the cloned directory to be made.
7. Type git clone, and then paste the URL you copied in Step 2.

git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY 

8. Press Enter. Your local clone will be created.


## License

Educational 