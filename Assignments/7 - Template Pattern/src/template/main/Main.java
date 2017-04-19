package template.main;

import java.util.ArrayList;
import java.util.List;

import template.algorithm.*;

public class Main 
{
	
	public static void main(String[] args)
	{
		List<Integer> data = new ArrayList<Integer>();
		
		MetaAlgorithm<PipelinedAlgorithm<List<Integer>>, List<Integer>> quickSorter = 
				new MetaAlgorithm<PipelinedAlgorithm<List<Integer>>, List<Integer>>(data);
		
		
	}

}
