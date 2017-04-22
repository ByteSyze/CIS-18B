package template.sort;

import java.util.List;

public class LomutoQuicksorter<T extends Comparable<? super T>> extends Quicksorter<T>
{
	protected int getLowBranchIndex(int partitionIndex)
	{
		return partitionIndex - 1;
	}
	
	protected int getHighBranchIndex(int partitionIndex)
	{
		return partitionIndex + 1;
	}

	@Override
	protected int partition(List<T> items, int from, int to)
	{
		T pivot = items.get(to);
		
		int i = from - 1;
		
		for(int j = from; j < to; j++)
		{
			if(items.get(j).compareTo(pivot) <= 0)
			{
				i++;
				
				T itemI = items.get(i);
				T itemJ = items.get(j);

				items.set(j, itemI);
				items.set(i, itemJ);
			}
		}
		
		T temp1 = items.get(i+1);
		T temp2 = items.get(to);
		
		items.set(to, temp1);
		items.set(i+1, temp2);
		
		return i + 1;
	}
	
}
