package template.algorithm;

import java.util.List;

public class Partitioner<T extends Comparable<? super T>> extends PipelinedAlgorithm<Integer, List<T>, Void, Void>
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
			if(data.get(j).compareTo(pivot) < 0)
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
		
		
		return i + 1;
	}

}
