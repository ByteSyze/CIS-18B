package template.algorithm.sort;

import java.util.List;

import template.algorithm.PipelinedAlgorithm;

public class PipelinedLomutoPartitioner<T extends Comparable<? super T>> extends PipelinedAlgorithm<Integer, List<T>, Object, Void>
{
	@Override
	public Integer algore(List<T> data, Object... metadata)
	{
		int lo = (int)metadata[0];
		int hi = (int)metadata[1];
		
		int i = lo - 1;
		
		T pivot = data.get(hi);
		
		for(int j = lo; j < hi; j++)
		{
			if(data.get(j).compareTo(pivot) <= 0)
			{
				i++;
				
				T dataI = data.get(i);
				T dataJ = data.get(j);

				data.set(j, dataI);
				data.set(i, dataJ);
			}
		}
		
		T temp1 = data.get(i+1);
		T temp2 = data.get(hi);
		
		data.set(hi, temp1);
		data.set(i+1, temp2);

		if(this.getNextAlgorithm() != null)
			this.getNextAlgorithm().algore(data, metadata);
		
		return i + 1;
	}
}
