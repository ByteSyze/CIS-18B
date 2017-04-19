package template.algorithm;

import java.util.List;

/**
 * Sorts a list of comparable items. QuickSorter must be followed by a partitioner
 * in the algorithm pipeline in order to make sense.
 * */
public class QuickSorter<T extends Comparable<? super T>> extends PipelinedAlgorithm<List<T>, List<T>, List<T>, Integer>
{

	@Override
	public List<T> algore(List<T> data, Object... metadata)
	{
		int lo = (int)metadata[0];
		int hi = (int)metadata[1];
		
		if(lo < hi)
		{
			int p = this.getNextAlgorithm().algore(data, lo, hi);
			
			this.algore(data, lo, p - 1);
			this.algore(data, p + 1, hi);
		}
		
		return data;
	}

}
