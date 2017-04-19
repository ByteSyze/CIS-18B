package template.algorithm.exception;

import template.algorithm.PipelinedAlgorithm;

/**
 * Indicates an issue where an algorithm pipeline returned the same data from a 
 * previous pass, but an algorithm in the pipeline still claims to be incomplete.
 * */
public class PipelineDiscrepancyException extends RuntimeException
{
	private static final long serialVersionUID = -4275969136422844151L;
	
	@SuppressWarnings("rawtypes")
	private PipelinedAlgorithm algorithm;
	
	@SuppressWarnings("rawtypes")
	public PipelineDiscrepancyException(PipelinedAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}
}
