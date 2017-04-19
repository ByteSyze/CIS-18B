package template.algorithm.exception;

import template.algorithm.PipelinedAlgorithm;

/**
 * Indicates an issue where an algorithm pipeline returned the same data from a 
 * previous pass, but an algorithm in the pipeline still claims to be incomplete.
 * */
@SuppressWarnings("rawtypes")
public class PipelineDiscrepancyException extends RuntimeException
{
	private static final long serialVersionUID = -4275969136422844151L;
	
	private PipelinedAlgorithm algorithm;
	
	public PipelineDiscrepancyException(PipelinedAlgorithm algorithm)
	{
		super("Algorithm Pipeline is incomplete but data was unchanged in the last pass.");
		this.algorithm = algorithm;
	}
	
	public PipelinedAlgorithm getAlgorithm()
	{
		return this.algorithm;
	}
}
