package template.algorithm;

/**
 * An algorithm comprised of algorithms.
 * 
 * The MetaAlgorithm class acts as a simple manager for pipelined algorithms.
 * */
public class MetaAlgorithm<A extends PipelinedAlgorithm<T>, T>
{
	private A root;
	
	private T data;
	
	public MetaAlgorithm(T data)
	{
		this.data = data;
	}
	
	/**
	 * Adds {@code algorithm} to the end of the algorithm pipeline.
	 * */
	public void addAlgorithm(A algorithm)
	{
		root.setNextAlgorithm(algorithm);
		
		root = algorithm;
	}
	
	/**
	 * Sends the provided data through the algorithm pipeline.
	 * 
	 * Once the pipeline returns, the MetaAlgorithm checks for any algorithms that
	 * are incomplete. If any algorithms are incomplete, the data is passed through
	 * the pipeline again until all algorithms are complete.
	 * */
	public T process()
	{
		T lastData;
		T proccessedData = pass();
		
		//TODO Run as many passes as necessary to complete all algorithms, 
		//     or throw a PipelinedDiscrepancyException if lastData == processedData
		//     and an algorithm still claims to be incomplete.
		
		return null;
	}
	
	/**
	 * Does a single "pass" through the algorithm pipeline.
	 * */
	public T pass()
	{
		return root.algore(data);
	}
	
	public boolean isComplete()
	{
		return false; //TODO check the pipeline for incomplete algorithms.
	}
}
