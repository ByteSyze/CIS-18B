package template.main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import template.algorithm.*;

public class Main 
{
	
	private static final int NUM_TESTS = 100;
	
	private static final int TEST_RANGE = 1000;
	
	public static void main(String[] args)
	{
		List<Integer> numData = new ArrayList<Integer>(TEST_RANGE);
		List<String> stringData = new ArrayList<String>(TEST_RANGE);
		
		Random random = new Random();
		
		//// Unused algorithm manager class ////
		//
		//MetaAlgorithm<PipelinedAlgorithm<List<Integer>, List<Integer>, ?, ?>, List<Integer>, List<Integer>> quickSorter = 
		//		new MetaAlgorithm<PipelinedAlgorithm<List<Integer>, List<Integer>, ?, ?>, List<Integer>, List<Integer>>(data);

		//////  Integer sorting //////
		//////////////////////////////
		
		QuickSorter<Integer> numSorter = new QuickSorter<Integer>();
		Partitioner<Integer> numPartitioner = new Partitioner<Integer>();
		
		numSorter.setNextAlgorithm(numPartitioner);
		
		System.out.print("Unsorted numbers: ( ");

		// Generate random numbers.
		for(int i = 0; i < NUM_TESTS; i++)
		{
			int next = random.nextInt() % TEST_RANGE;
			System.out.print(next + " ");
			
			numData.add(next);
		}
		
		System.out.println(")");
		
		// Let's sort!
		numSorter.algore(numData, 0, numData.size()-1);
		
		// Print the sorted numbers.
		printList("  Sorted numbers:", numData);
		
		//////  String sorting  //////
		//////////////////////////////

		QuickSorter<String> stringSorter = new QuickSorter<String>();
		Partitioner<String> stringPartitioner = new Partitioner<String>();
		
		stringSorter.setNextAlgorithm(stringPartitioner);
		
		System.out.print("Unsorted Strings: ( ");

		// Generate random Strings.
		for(int i = 0; i < NUM_TESTS; i++)
		{
			String next = new BigInteger(10, random).toString(32);
			System.out.print(next + " ");
			
			stringData.add(next);
		}
		
		System.out.println(")");
		
		// Let's sort!
		stringSorter.algore(stringData, 0, stringData.size()-1);
		
		printList("  Sorted Strings:", stringData);
	}
	
	public static void printList(String msg, List<?> list)
	{
		System.out.print(msg + " ( ");

		for(Object o : list)
			System.out.print(o + " ");

		System.out.println(")");		
	}

}
