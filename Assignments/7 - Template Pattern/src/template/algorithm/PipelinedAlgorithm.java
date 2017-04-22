package template.algorithm;

/**
 * @param <R> Return data from this algorithm
 * @param <D> Data given to this algorithm
 * @param <P> Data given to the next algorithm
 * @param <V> Return data from the next algorithm
 * */
public abstract class PipelinedAlgorithm<R,D,P,V> implements Algorithm<R,D>
{
	private PipelinedAlgorithm<V,P,?,?> nextAlgorithm;
	
	private boolean complete = true;
	
	public void setNextAlgorithm(PipelinedAlgorithm<V,P,?,?> nextAlgorithm)
	{
		this.nextAlgorithm = nextAlgorithm;
	}
	
	protected PipelinedAlgorithm<V,P,?,?> getNextAlgorithm()
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
}
