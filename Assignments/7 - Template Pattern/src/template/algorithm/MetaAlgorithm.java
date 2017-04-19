package template.algorithm;

import template.algorithm.exception.PipelineDiscrepancyException;

/**
 * An algorithm comprised of algorithms.
 * 
 * <p>The MetaAlgorithm class acts as a simple manager for pipelined algorithms. The main
 * advantage to using a MetaAlgorithm is automated management of algorithms that need to
 * make multiple "passes" through some data.</p>
 * */
public class MetaAlgorithm<A extends PipelinedAlgorithm<R,D,?,?>, R, D>
{
	/**The root of the algorithm pipeline.*/
	private A root;
	private PipelinedAlgorithm tail;
	
	/**The primary data to be processed by the pipeline.*/
	private D data;
	
	public MetaAlgorithm(D data)
	{
		this.data = data;
		
		root = null;
		tail = null;
	}
	
	/**
	 * Adds {@code algorithm} to the end of the algorithm pipeline.
	 * */
	public void addAlgorithm(PipelinedAlgorithm algorithm)
	{
		if(root == null)
		{
			root = root;
			tail = root;
		}
		else
		{
			tail.setNextAlgorithm(algorithm);
			
			tail = algorithm;
		}
	}
	
	/**
	 * Sends the provided data through the algorithm pipeline.
	 * 
	 * <p>Once the pipeline returns, the MetaAlgorithm checks for any algorithms that
	 * are incomplete. If any algorithms are incomplete, the data is passed through
	 * the pipeline again until all algorithms are complete.</p>
	 * 
	 * @throws PipelineDiscrepancyException if the algorithm seems to be stuck in an infinite loop
	 * */
	public R process() throws PipelineDiscrepancyException
	{
		R lastData = null;
		R processedData = pass();
		
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
	public R pass(Object... metadata)
	{
		return root.algore(data, metadata);
	}
	
	/**
	 * @return true if the pipeline is fully finished processing data.
	 * */
	public boolean isComplete()
	{
		return (this.getFirstIncompleteAlgorithm() == null);
	}
	
	private PipelinedAlgorithm<?,?,?,?> getFirstIncompleteAlgorithm()
	{
		PipelinedAlgorithm<?,?,?,?> candidate = root;
		
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
