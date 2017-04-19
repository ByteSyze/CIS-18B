package template.algorithm;

import java.util.HashMap;

public abstract class PipelinedAlgorithm<T> implements Algorithm<T>
{
	private PipelinedAlgorithm<T> nextAlgorithm;
	
	private boolean complete = false;
	
	public void setNextAlgorithm(PipelinedAlgorithm<T> nextAlgorithm)
	{
		this.nextAlgorithm = nextAlgorithm;
	}
	
	protected PipelinedAlgorithm<T> getNextAlgorithm()
	{
		return this.nextAlgorithm;
	}
	
	protected void setComplete(boolean complete)
	{
		this.complete = complete;
	}
	
	/**
	 * @return true if the algorithm is finished processing data, or false if the algorithm
	 *         needs at least one more pass through the data to complete its task.
	 * */
	public boolean isComplete()
	{
		return this.complete;
	}
	
	public final T pipelinedAlgore(T data, Object... metadata)
	{
		T processedData = this.algore(data, metadata);
		
		//If the pipeline continues, give the next algorithm our processed data.
		if(nextAlgorithm != null)
		{
			return nextAlgorithm.pipelinedAlgore(processedData, metadata);
		}
		else
		{
			return processedData;
		}
	}
}
