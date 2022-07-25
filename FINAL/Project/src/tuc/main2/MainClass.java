package tuc.main2;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException {

		
		Methods instance = new Methods();
		
		//First Part----------------------------------
		
		
		// Fill the file with 100.000 random numbers
		
		instance.makeTheFile();
		
		// Second part-------------------------------- 
		
		
		//Split the File in 10 Files
		
		instance.SplitFilein10files();
		
		// merge the 10 files we created in the disk
		
		instance.Merge10files();
		
		
		// 3rd part----------------------------------

		instance.LinearSearch();
		
		instance.BinarySearch();
		
		
}
	
	//Quick sort from : https://www.geeksforgeeks.org/quick-sort/
	//Binary search from : https://www.geeksforgeeks.org/binary-search/

}