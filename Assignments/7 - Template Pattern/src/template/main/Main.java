package template.main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import template.algorithm.sort.PipelinedGrapher;
import template.algorithm.sort.PipelinedLomutoPartitioner;
import template.algorithm.sort.PipelinedQuicksorter;
import template.sort.HoareQuicksorter;
import template.sort.LomutoQuicksorter;
import template.sort.Quicksorter;

public class Main 
{
	
	private static final int NUM_TESTS = 100;
	
	private static final int TEST_RANGE = 1000;
	
	public static void main(String[] args)
	{
		//Create 2 sets of identical lists to compare the different quicksort implementations.
		List<Integer> numData = new ArrayList<Integer>(TEST_RANGE);
		List<String> stringData = new ArrayList<String>(TEST_RANGE);
		
		List<Integer> lameNumData = new ArrayList<Integer>(TEST_RANGE);
		List<String> lameStringData = new ArrayList<String>(TEST_RANGE);
		
		Random random = new Random();

		// Generate random numbers.
		for(int i = 0; i < NUM_TESTS; i++)
		{
			int num = random.nextInt() % TEST_RANGE;
			
			numData.add(num);
			lameNumData.add(num);
		}
		
		// Generate random Strings.
		for(int i = 0; i < NUM_TESTS; i++)
		{
			String str = new BigInteger(10, random).toString(32);
			
			stringData.add(str);
			lameStringData.add(str);
		}
		
		doPipelinedTest(numData, stringData);
		doLameTest(lameNumData, lameStringData);
	}
	
	public static void doPipelinedTest(List<Integer> numData, List<String> stringData)
	{
		// Unused algorithm manager class
		//
		//MetaAlgorithm<PipelinedAlgorithm<List<Integer>, List<Integer>, ?, ?>, List<Integer>, List<Integer>> quickSorter = 
		//		new MetaAlgorithm<PipelinedAlgorithm<List<Integer>, List<Integer>, ?, ?>, List<Integer>, List<Integer>>(data);

		//////////////////////////////
		//////  Integer sorting //////
		//////////////////////////////
		
		System.out.println("---Pipelined quicksort algorithm test---");
		
		
		PipelinedQuicksorter<Integer> numSorter = new PipelinedQuicksorter<Integer>();
		PipelinedLomutoPartitioner<Integer> numPartitioner = new PipelinedLomutoPartitioner<Integer>();
		PipelinedGrapher grapher = new PipelinedGrapher(graph("Pipelined Numbers", numData));
		
		numSorter.setNextAlgorithm(numPartitioner);
		numPartitioner.setNextAlgorithm(grapher);
		
		printList("Unsorted numbers:", numData);
		
		// Let's sort!
		numSorter.algore(numData, 0, numData.size()-1);
		
		printList("  Sorted numbers:", numData);
		
		
		//////////////////////////////
		//////  String sorting  //////
		//////////////////////////////
		
		
		PipelinedQuicksorter<String> stringSorter = new PipelinedQuicksorter<String>();
		PipelinedLomutoPartitioner<String> stringPartitioner = new PipelinedLomutoPartitioner<String>();
		
		stringSorter.setNextAlgorithm(stringPartitioner);

		printList("Unsorted Strings:", stringData);
		
		// Let's sort!
		stringSorter.algore(stringData, 0, stringData.size()-1);
		
		printList("  Sorted Strings:", stringData);
	}
	
	public static void doLameTest(List<Integer> numData, List<String> stringData)
	{
		System.out.println("---Lame quicksort algorithm test---");
		
		Quicksorter<Integer> numSorter = new HoareQuicksorter<Integer>();
		
		printList("Unsorted numbers:", numData);
		graph("Unsorted Numbers", numData);
		
		numSorter.sort(numData, 0, numData.size()-1);
		
		printList("  Sorted numbers:", numData);
		graph("Sorted Numbers", numData);
		
		LomutoQuicksorter<String> stringSorter = new LomutoQuicksorter<String>();
		
		printList("Unsorted Strings:", stringData);
		
		stringSorter.sort(stringData, 0, stringData.size()-1);
		
		printList("  Sorted numbers:", stringData);
	}
	
	public static void printList(String msg, List<?> list)
	{
		System.out.print(msg + " ( ");

		for(Object o : list)
			System.out.print(o + " ");

		System.out.println(")");		
	}
	
	/**
	 * Convenience method for creating and returning a new BarGraph of the given data.
	 * */
	public static BarGraph graph(String title, List<Integer> numbers)
	{
		return new BarGraph(title, numbers);
	}
}
