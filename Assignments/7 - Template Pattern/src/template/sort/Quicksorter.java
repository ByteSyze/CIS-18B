package template.sort;

import java.util.List;

public abstract class Quicksorter<T extends Comparable<? super T>> 
{
	/**
	 * Sorts a list by some form of the quicksort implementation.
	 * */
	public final void sort(List<T> items, int from, int to)
	{
		if(from < to)
		{
			int p = partition(items, from, to);
			
			this.sort(items, from, getLowBranchIndex(p));
			this.sort(items, getHighBranchIndex(p), to);
		}
	}
	
	/**
	 * Determines the new upper-bound of the lower half of the list.
	 * 
	 * @param partitionIndex the output from the previous call to {@link Quicksorter#partition(List, int, int) partition()}
	 * */
	protected abstract int getLowBranchIndex(int partitionIndex);
	
	/**
	 * Determines the new lower-bound of the upper half of the list.
	 * 
	 * @param partitionIndex the output from the previous call to {@link Quicksorter#partition(List, int, int) partition()}
	 * */
	protected abstract int getHighBranchIndex(int partitionIndex);
	
	protected abstract int partition(List<T> items, int from, int to);
}
