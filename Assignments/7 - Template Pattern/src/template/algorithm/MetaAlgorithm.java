package template.algorithm;

import java.util.HashMap;

import template.algorithm.exception.PipelineDiscrepancyException;

/**
 * An algorithm comprised of algorithms.
 * 
 * <p>The MetaAlgorithm class acts as a simple manager for pipelined algorithms.</p>
 * */
public class MetaAlgorithm<A extends PipelinedAlgorithm<T>, T>
{
	/**The root of the algorithm pipeline.*/
	private A root;
	
	/**The primary data to be processed by the pipeline.*/
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
	 * <p>Once the pipeline returns, the MetaAlgorithm checks for any algorithms that
	 * are incomplete. If any algorithms are incomplete, the data is passed through
	 * the pipeline again until all algorithms are complete.</p>
	 * */
	public T process()
	{
		T lastData = null;
		T processedData = pass();
		
		while(!this.isComplete())
		{
			if(lastData == processedData)
				throw new PipelineDiscrepancyException(this.getFirstIncompleteAlgorithm());
			
			lastData = processedData;
			processedData = pass();
		}
		
		return processedData;
	}
	
	/**
	 * Does a single "pass" through the algorithm pipeline.
	 * */
	public T pass(Object... metadata)
	{
		return root.pipelinedAlgore(data, metadata);
	}
	
	/**
	 * @return true if the pipeline is fully finished processing data.
	 * */
	public boolean isComplete()
	{
		return (this.getFirstIncompleteAlgorithm() == null);
	}
	
	private PipelinedAlgorithm<T> getFirstIncompleteAlgorithm()
	{
		PipelinedAlgorithm<T> candidate = root;
		
		//Search the pipeline for an incomplete algorithm.
		while(candidate.isComplete())
		{
			candidate = candidate.getNextAlgorithm();
		}
		
		if(!candidate.isComplete())
			return candidate;
		else
			return null;
	}
}
