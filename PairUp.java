/* PairUp.java
   CSC 225 - Summer 2015
   Programming Assignment 1 - Template for PairUp
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java PairUp
	
   To conveniently test the algorithm with a large input, create a 
   text file containing space-separated integer values and run the program with
	java PairUp file.txt
   where file.txt is replaced by the name of the text file.

   B. Bird - 03/09/2015
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

//Do not change the name of the PairUp class
public class PairUp{
	/* PairUp225(A)
		The input array A will contain non-negative integers. If
		the array A can be completely divided into pairs (x,y) where
		  x + y = 225,
		then the function will return true. Otherwise, the function
		will return false.
		
		The function is permitted to modify the input array A.
		
		Do not change the function signature (name/parameters).
	*/
	public static boolean PairUp225(int[] A){
		int n = A.length;
		int count = 0;

		//check if array input is empty
		if (A.length == 0 || A == null){
			System.out.println("Array is empty");
			return false;
		}
		//check if array has an even number of values to pair up
		if (n % 2 != 0){
			System.out.println("Array length is not even");
			return false;
		}

		//check if array has all values in desired range
		for (int i = 0; i < n; i++){
			if (A[i] > 225){
				return false;
			}
		}

		//create a tally array and increment the element equal to 
		//instances of elements in input array 
		int [] Tally = new int [226];
		for (int i = 0; i < n; i++){
			Tally[A[i]]++;
		}

		//iterate through the first half of the tally array
		//pairing them with tally numbers of their pairs in the
		//second half of the array equalling to 225 
		for (int i = 0; i < 113; i++){
			if (Tally[i] == 0){
				continue;
			}
			else if (Tally[225-i] == Tally[i]){
				count = count + Tally[i];
			}
		}
		//if all the elements of the input array are paired return true
		if (count == n/2){
			return true;
		}
		return false;
	}
	
	/* main()
	   Contains code to test the PairUp225 function. Nothing in this function 
	   will be marked. You are free to change the provided code to test your 
	   implementation, but only the contents of the PairUp225() function above 
	   will be considered during marking.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();
		
		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);
		
		int[] array = new int[inputVector.size()];
		
		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);
		
		
		long startTime = System.currentTimeMillis();
		
		boolean canPairUp = PairUp225(array);
		
		long endTime = System.currentTimeMillis();
		
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		
		System.out.printf("Array values %s be paired up.\n",canPairUp? "can":"cannot");
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
	}
}
