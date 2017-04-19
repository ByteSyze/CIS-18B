package template.algorithm;

public abstract class PipelinedAlgorithm<T> extends Algorithm<T>
{
	private PipelinedAlgorithm<T> nextAlgorithm;
	
	private boolean complete = false;
	
	public void setNextAlgorithm(PipelinedAlgorithm<T> nextAlgorithm)
	{
		this.nextAlgorithm = nextAlgorithm;
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
	
	public final T pipelinedAlgore(T data)
	{
		T processedData = this.algore(data);
		
		//If the pipeline continues, give the next algorithm our processed data.
		if(nextAlgorithm != null)
		{
			nextAlgorithm.setData(processedData);
			
			return nextAlgorithm.pipelinedAlgore(processedData);
		}
		else
		{
			return processedData;
		}
	}
}
