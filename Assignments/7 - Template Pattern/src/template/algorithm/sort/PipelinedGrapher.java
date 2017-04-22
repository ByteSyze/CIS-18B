package template.algorithm.sort;

import java.util.List;

import template.algorithm.PipelinedAlgorithm;
import template.main.BarGraph;

public class PipelinedGrapher extends PipelinedAlgorithm<Void, Object, Void, Void>
{
	private BarGraph graph;
	
	public PipelinedGrapher(BarGraph graph)
	{
		this.graph = graph;
	}

	@Override
	public Void algore(Object data, Object... metadata)
	{
		this.graph.setData((List<Integer>)data);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.graph.repaint();
		return null;
	}

}
