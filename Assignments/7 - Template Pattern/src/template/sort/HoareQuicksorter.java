package template.sort;

import java.util.List;

public class HoareQuicksorter<T extends Comparable<? super T>> extends Quicksorter<T>
{

	@Override
	protected int getLowBranchIndex(int partitionIndex)
	{
		return partitionIndex;
	}

	@Override
	protected int getHighBranchIndex(int partitionIndex)
	{
		return partitionIndex + 1;
	}

	@Override
	protected int partition(List<T> items, int from, int to)
	{
		T pivot = items.get(from);
		
		int i = from - 1;
		int j = to + 1;
		
		while(true)
		{
			do
			{
				i += 1;
			}while(items.get(i).compareTo(pivot) < 0);
			
			do
			{
				j -= 1;
			}while(items.get(j).compareTo(pivot) > 0);
			
			if(i >= j)
				return j;
			
			T item1 = items.get(i);
			T item2 = items.get(j);
			
			items.set(j, item1);
			items.set(i, item2);
		}
	}

}
