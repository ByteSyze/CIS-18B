package template.main;

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
		List<Integer> data = new ArrayList<Integer>(TEST_RANGE);
		List<String> stringData = new ArrayList<String>(TEST_RANGE);
		
		Random random = new Random();
		
		//// Unused algorithm manager class ////
		//
		//MetaAlgorithm<PipelinedAlgorithm<List<Integer>, List<Integer>, ?, ?>, List<Integer>, List<Integer>> quickSorter = 
		//		new MetaAlgorithm<PipelinedAlgorithm<List<Integer>, List<Integer>, ?, ?>, List<Integer>, List<Integer>>(data);
		
		QuickSorter<Integer> sorter = new QuickSorter<Integer>();
		Partitioner<Integer> partitioner = new Partitioner<Integer>();
		
		sorter.setNextAlgorithm(partitioner);
		
		System.out.print("Unsorted numbers: ( ");

		// Generate random numbers.
		for(int i = 0; i < NUM_TESTS; i++)
		{
			int next = random.nextInt() % TEST_RANGE;
			System.out.print(next + " ");
			
			data.add(next);
		}
		
		System.out.println(")");
		
		// Let's sort!
		sorter.algore(data, 0, data.size()-1);
		
		// Print the sorted numbers.
		System.out.print("  Sorted numbers: ( ");
		
		for(int num : data)
			System.out.print(num + " ");
		
		System.out.println(")");
	}

}
